package com.intecsec.java.basic.net.http;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * 需要 JDK11
 */
public class JDKHttpClientPostTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		doPost();
	}
	
	public static void doPost() {
		try {
			HttpClient client = HttpClient.newBuilder().build();
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create("https://tools.usps.com/go/ZipLookupAction.action"))
					//.header("Content-Type","application/x-www-form-urlencoded")
					.header("User-Agent", "HTTPie/0.9.2")
					.header("Content-Type","application/x-www-form-urlencoded;charset=utf-8")
					//.method("POST", HttpRequest.BodyPublishers.ofString("tAddress=1 Market Street&tCity=San Francisco&sState=CA"))
					//.version(Version.HTTP_1_1)
					.POST(HttpRequest.BodyPublishers.ofString("tAddress=" 
					    + URLEncoder.encode("1 Market Street", "UTF-8") 
					    + "&tCity=" + URLEncoder.encode("San Francisco", "UTF-8") + "&sState=CA"))
					//.POST(HttpRequest.BodyPublishers.ofString("tAddress=" + URLEncoder.encode("1 Market Street", "UTF-8") + "&tCity=" + URLEncoder.encode("San Francisco", "UTF-8") + "&sState=CA"))
					.build();
			HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
			System.out.println(response.statusCode());
			System.out.println(response.headers());
			System.out.println(response.body().toString());

		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}	
}
