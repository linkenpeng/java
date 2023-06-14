package com.intecsec.java.basic.security;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketPermission;
import java.security.Permissions;
import java.security.Policy;

public class NetworkTest {

	public static void main(String[] args) {
		testListen();
	}

	public static void testListen() {
		// connect, listen, accept, resolve
		SocketPermission p = new SocketPermission("localhost:80", "listen,resolve");
		Permissions ps = new Permissions();
		ps.add(p);
		Policy.setPolicy(new NetworkPolicy(p));
		System.setSecurityManager(new SecurityManager());

		try {
			ServerSocket server = new ServerSocket(80);

			Socket socket = server.accept();
			InputStream is = socket.getInputStream();
			byte[] bytes = new byte[1024];
			int len;
			StringBuilder sb = new StringBuilder();
			while ((len = is.read(bytes)) != -1) {
				sb.append(new String(bytes, 0, len, "UTF-8"));
			}
			System.out.println(sb.toString());
			is.close();
			socket.close();
			server.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
