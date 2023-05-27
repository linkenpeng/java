package com.intecsec.java.basic.net.http;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpComponentsPostTest {

    public final static void main(String[] args) throws Exception {
    	
    	//获取可关闭的 httpCilent
        //CloseableHttpClient httpClient = HttpClients.createDefault();
    	CloseableHttpClient httpClient = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy()).build();
    	//配置超时时间
        RequestConfig requestConfig = RequestConfig.custom().
                setConnectTimeout(10000).setConnectionRequestTimeout(10000)
                .setSocketTimeout(10000).setRedirectsEnabled(false).build();
         
        HttpPost httpPost = new HttpPost("https://tools.usps.com/go/ZipLookupAction.action");
        //设置超时时间
        httpPost.setConfig(requestConfig);
        
        //装配post请求参数
        List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>(); 
        list.add(new BasicNameValuePair("tAddress", URLEncoder.encode("1 Market Street", "UTF-8")));  //请求参数
        list.add(new BasicNameValuePair("tCity", URLEncoder.encode("San Francisco", "UTF-8"))); //请求参数
        list.add(new BasicNameValuePair("sState", "CA")); //请求参数
        try {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,"UTF-8"); 
            //设置post求情参数
            httpPost.setEntity(entity);
            httpPost.setHeader("User-Agent", "HTTPie/0.9.2");
            //httpPost.setHeader("Content-Type","application/form-data");
            HttpResponse httpResponse = httpClient.execute(httpPost);
            String strResult = "";
            if(httpResponse != null){ 
                System.out.println(httpResponse.getStatusLine().getStatusCode());
                if (httpResponse.getStatusLine().getStatusCode() == 200) {
                    strResult = EntityUtils.toString(httpResponse.getEntity());
                }
                else {
                    strResult = "Error Response: " + httpResponse.getStatusLine().toString();
                } 
            }else{
                 
            }
            System.out.println(strResult);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(httpClient != null){
                    httpClient.close(); //释放资源
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

