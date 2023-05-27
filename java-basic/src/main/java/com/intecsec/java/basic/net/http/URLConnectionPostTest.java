package com.intecsec.java.basic.net.http;

import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.*;

public class URLConnectionPostTest
{
   public static void main(String[] args) throws IOException
   {
      String urlString = "https://tools.usps.com/go/ZipLookupAction.action";
      Object userAgent = "HTTPie/0.9.2";
      Object redirects = "1";
      CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
      
      Map<String, String> params = new HashMap<String, String>();
      params.put("tAddress", "1 Market Street");  
      params.put("tCity", "San Francisco");
      params.put("sState", "CA");
      String result = doPost(new URL(urlString), params, 
         userAgent == null ? null : userAgent.toString(), 
         redirects == null ? -1 : Integer.parseInt(redirects.toString()));
      System.out.println(result);
   }   

   public static String doPost(URL url, Map<String, String> nameValuePairs, String userAgent, int redirects)
         throws IOException
   {        
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      if (userAgent != null)
         connection.setRequestProperty("User-Agent", userAgent);
      
      if (redirects >= 0)
         connection.setInstanceFollowRedirects(false);
      
      connection.setDoOutput(true);
      
      //输出请求的参数
      try (PrintWriter out = new PrintWriter(connection.getOutputStream()))
      {
         boolean first = true;
         for (Map.Entry<String, String> pair : nameValuePairs.entrySet())
         {
        	//参数必须这样拼接 a=1&b=2&c=3
            if (first) 
            {
            	first = false;
            }
            else
            {
            	out.print('&');
            }
            String name = pair.getKey();
            String value = pair.getValue();
            out.print(name);
            out.print('=');
            out.print(URLEncoder.encode(value, "UTF-8"));
         }
      }      
      String encoding = connection.getContentEncoding();
      if (encoding == null) 
      {
    	  encoding = "UTF-8";
      }
            
      if (redirects > 0)
      {
         int responseCode = connection.getResponseCode();
         System.out.println("responseCode: " + responseCode);
         if (responseCode == HttpURLConnection.HTTP_MOVED_PERM 
               || responseCode == HttpURLConnection.HTTP_MOVED_TEMP
               || responseCode == HttpURLConnection.HTTP_SEE_OTHER) 
         {
            String location = connection.getHeaderField("Location");
            if (location != null)
            {
               URL base = connection.getURL();
               connection.disconnect();
               return doPost(new URL(base, location), nameValuePairs, userAgent, redirects - 1);
            }
            
         }
      }
      else if (redirects == 0)
      {
         throw new IOException("Too many redirects");
      }
      
      //接下来获取html 内容
      StringBuilder response = new StringBuilder();
      try (Scanner in = new Scanner(connection.getInputStream(), encoding))
      {
         while (in.hasNextLine())
         {
            response.append(in.nextLine());
            response.append("\n");
         }         
      }
      catch (IOException e)
      {
         InputStream err = connection.getErrorStream();
         if (err == null) throw e;
         try (Scanner in = new Scanner(err))
         {
            response.append(in.nextLine());
            response.append("\n");
         }
      }

      return response.toString();
   }
}
