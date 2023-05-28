package com.intecsec.java.basic.net.netty1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class EchoServer {
    public static void main(String[] args) throws Exception {
        int port = 8001;
        final EchoServerHandler serverHandler = new EchoServerHandler();
        EventLoopGroup group = new NioEventLoopGroup();
        try {
        	//ServerBootstrap是netty中的一个服务器引导类
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                .channel(NioServerSocketChannel.class)  //设置通道类型
                .localAddress(new InetSocketAddress(port))  //设置监听端口
                .childHandler(new ChannelInitializer<SocketChannel>() { //初始化责任链
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(serverHandler); //添加处理类
                    }
                });

            ChannelFuture f = b.bind().sync();  //开启监听
            if(f.isSuccess()){
            	System.out.println(EchoServer.class.getName() +
                        " started and listening for connections on " + f.channel().localAddress());
            }
            
            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }    
}
