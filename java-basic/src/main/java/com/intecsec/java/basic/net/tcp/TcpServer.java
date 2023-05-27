package com.intecsec.java.basic.net.tcp;

import java.net.*;
import java.io.*;
public class TcpServer
{
	public static void main(String [] args) 
	{
		try
		{
			ServerSocket ss = new ServerSocket(8001); //驻守在8001端口
			Socket s = ss.accept();                   //阻塞，等到有客户端连接上来
			System.out.println("welcome to the java world");
			InputStream ips = s.getInputStream();     //有人连上来，打开输入流
			OutputStream ops = s.getOutputStream();   //打开输出流
			//同一个通道，服务端的输出流就是客户端的输入流；服务端的输入流就是客户端的输出流
			
			ops.write("Hello, Client!".getBytes());  //输出一句话给客户端

			BufferedReader br = new BufferedReader(new InputStreamReader(ips));
			//从客户端读取一句话			
			System.out.println("Client said: " + br.readLine());
			
			ips.close();          
			ops.close();
			s.close();
			ss.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}