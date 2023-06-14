package com.intecsec.java.basic.net.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
JDK11
public class AioServer {

    public static void main(String[] args) throws IOException {  
    	AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open();   
        server.bind(new InetSocketAddress("localhost", 8001));  
        System.out.println("服务器在8001端口守候");
        
        //开始等待客户端连接，一旦有连接，做26行任务
        server.accept(null, new CompletionHandler<>() {
            @Override
            public void completed(AsynchronousSocketChannel channel, Object attachment) {
                server.accept(null, this); //持续接收新的客户端请求

                ByteBuffer buffer = ByteBuffer.allocate(1024); //准备读取空间
                //开始读取客户端内容，一旦读取结束，做33行任务
                channel.read(buffer, buffer, new CompletionHandler<>() {
                    @Override
                    public void completed(Integer result_num, ByteBuffer attachment) {
                        attachment.flip(); //反转此Buffer
                        CharBuffer charBuffer = CharBuffer.allocate(1024);
                        CharsetDecoder decoder = Charset.defaultCharset().newDecoder();
                        decoder.decode(attachment, charBuffer, false);
                        charBuffer.flip();
                        String data = new String(charBuffer.array(), 0, charBuffer.limit());
                        System.out.println("client said: " + data);
                        channel.write(ByteBuffer.wrap((data + " 666").getBytes())); //返回结果给客户端
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
            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                System.out.print("failed: " + exc.getMessage());
            }
        });  

        while(true){
        	try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
    }  
}
*/