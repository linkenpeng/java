package com.intecsec.java.basic.net.bio;
import java.net.*;
import java.util.UUID;
import java.io.*;

public class TcpClient {
	public static void main(String[] args) {
		try {
			Socket s = new Socket(InetAddress.getByName("127.0.0.1"), 8001); 
			
			//同一个通道，服务端的输出流就是客户端的输入流；服务端的输入流就是客户端的输出流
			InputStream ips = s.getInputStream();    //开启通道的输入流
			BufferedReader brNet = new BufferedReader(new InputStreamReader(ips));
			
			OutputStream ops = s.getOutputStream();  //开启通道的输出流
			DataOutputStream dos = new DataOutputStream(ops);			

			while (true) 
			{
				String strWord = UUID.randomUUID().toString();
				if (strWord.equalsIgnoreCase("quit"))
				{
					break;
				}
				System.out.println("I want to send: " + strWord);
				dos.writeBytes(strWord + System.getProperty("line.separator"));					
				System.out.println("Server said: " + brNet.readLine());	
				Thread.sleep(3000);
			}
			
			dos.close();
			brNet.close();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
