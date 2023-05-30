package com.intecsec.java.basic.example.testcard.crawler;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpUtil {
	
	public String getHtml(String u){
		CloseableHttpClient httpClient = HttpClients.createDefault();
	    HttpGet httpGet = new HttpGet(u);
	    CloseableHttpResponse response = null;
	    try {
	        response = httpClient.execute(httpGet);
		    InputStream in = response.getEntity().getContent();
		    InputStreamReader reader = new InputStreamReader(in, "utf-8");
			BufferedReader breader = new BufferedReader(reader);
			StringBuffer sb = new StringBuffer();
			String json = null;
			while ((json = breader.readLine()) != null) {
				sb.append(json);
			}
			breader.close();
			reader.close();
			in.close();
			//conn.disconnect();
			String html = sb.toString();
			log(u+" : "+html+"\n\t");
			
		    response.close();
		    httpClient.close();
			return html;
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	    return "";
	}
	
	/**
	 * 输出成文件
	 */
	public static String logFile="htmllog.txt";
	public static void log(Object obj)
	{
		PrintWriter out = null;
		try {
			out = new PrintWriter(new FileWriter(logFile,true));
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.append(obj.toString());
		out.close();
	}
}