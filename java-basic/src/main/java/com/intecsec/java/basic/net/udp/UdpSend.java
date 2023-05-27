package com.intecsec.java.basic.net.udp;

import java.net.*;
public class UdpSend
{
	public static void main(String [] args) throws Exception
	{
		DatagramSocket ds=new DatagramSocket();
		String str="hello world";
		DatagramPacket dp=new DatagramPacket(str.getBytes(),str.length(),
				InetAddress.getByName("127.0.0.1"),3000);
		
		System.out.println("UdpSend: 我要发送信息");
		ds.send(dp);
		System.out.println("UdpSend: 我发送信息结束");
		
		Thread.sleep(1000);
		byte [] buf=new byte[1024];
		DatagramPacket dp2=new DatagramPacket(buf,1024);
		System.out.println("UdpSend: 我在等待信息");
		ds.receive(dp2);
		System.out.println("UdpSend: 我接收到信息");
		String str2=new String(dp2.getData(),0,dp2.getLength()) +
				" from " + dp2.getAddress().getHostAddress()+":"+dp2.getPort(); 
		System.out.println(str2);
				
		ds.close();
	}
}