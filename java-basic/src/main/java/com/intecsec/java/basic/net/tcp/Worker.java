package com.intecsec.java.basic.net.tcp;

import java.net.*;
import java.io.*;

class Worker implements Runnable {
	Socket s;

	public Worker(Socket s) {
		this.s = s;
	}

	public void run() {
		try {
			System.out.println("服务人员已经启动");
			InputStream ips = s.getInputStream();
			OutputStream ops = s.getOutputStream();

			BufferedReader br = new BufferedReader(new InputStreamReader(ips));
			DataOutputStream dos = new DataOutputStream(ops);
			while (true) {
				String strWord = br.readLine();
				System.out.println("client said:" + strWord +":" + strWord.length());
				if (strWord.equalsIgnoreCase("quit"))
					break;
				String strEcho = strWord + " 666";
				// dos.writeBytes(strWord +"---->"+ strEcho +"\r\n");
				System.out.println("server said:" + strWord + "---->" + strEcho);
				dos.writeBytes(strWord + "---->" + strEcho + System.getProperty("line.separator"));
			}
			br.close();
			// 关闭包装类，会自动关闭包装类中所包装的底层类。所以不用调用ips.close()
			dos.close();
			s.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}