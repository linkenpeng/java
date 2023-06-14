package com.intecsec.java.basic.net.aio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.UUID;

/**
//JDK11
public class AioClient {

	public static void main(String[] a) {
		try
		{
			AsynchronousSocketChannel channel = AsynchronousSocketChannel.open();
			
			//18行连接成功后，自动做20行任务
			channel.connect(new InetSocketAddress("localhost", 8001), null, new CompletionHandler<Void, Void>() {

				public void completed(Void result, Void attachment) {
					String str = UUID.randomUUID().toString();
					
					//24行向服务器写数据成功后，自动做28行任务
					channel.write(ByteBuffer.wrap(str.getBytes()), null,
							new CompletionHandler<>() {

								@Override
								public void completed(Integer result, Object attachment) {
									try {
										System.out.println("write " + str + ", and wait response");
										//等待服务器响应
										ByteBuffer buffer = ByteBuffer.allocate(1024); //准备读取空间
										//开始读取服务器反馈内容，一旦读取结束，做39行任务
										channel.read(buffer, buffer, new CompletionHandler<>() {
											@Override
											public void completed(Integer result_num, ByteBuffer attachment) {
												attachment.flip(); //反转此Buffer
												CharBuffer charBuffer = CharBuffer.allocate(1024);
												CharsetDecoder decoder = Charset.defaultCharset().newDecoder();
												decoder.decode(attachment, charBuffer, false);
												charBuffer.flip();
												String data = new String(charBuffer.array(), 0, charBuffer.limit());
												System.out.println("server said: " + data);
												try {
													channel.close();
												} catch (Exception e) {
													e.printStackTrace();
												}
											}

											@Override
											public void failed(Throwable exc, ByteBuffer attachment) {
												System.out.println("read error " + exc.getMessage());
											}
										});

										channel.close();
									} catch (Exception e) {
										e.printStackTrace();
									}
								}

								@Override
								public void failed(Throwable exc, Object attachment) {
									System.out.println("write error");
								}

							});
				}

				public void failed(Throwable exc, Void attachment) {
					System.out.println("fail");
				}

			});
			Thread.sleep(10000);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
*/