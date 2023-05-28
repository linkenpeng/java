package com.intecsec.java.basic.net.netty2;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.oio.OioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.channel.socket.oio.OioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
 
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
 
public class EchoClient {
 
    public static void main(String[] argsw) throws Exception {
    	int port = 8001;
        final EchoClientHandler clientHandler = new EchoClientHandler();
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(eventLoopGroup).
                    channel(NioSocketChannel.class).
                    remoteAddress(new InetSocketAddress("localhost",port)).
                    handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(clientHandler);
                        }
                    });
            
            //异步连接远程服务，连接远程服务成功后，输出"已经连接到服务器！"
            final ChannelFuture f = b.connect();
            f.addListener(new GenericFutureListener<Future<? super Void>>() {
                @Override
                public void operationComplete(Future<? super Void> future) throws Exception {
                    if (future.isSuccess()) {
                        System.out.println("已经连接到服务器！");
                        ByteBuf byteBuf = Unpooled.copiedBuffer("aaaaaaaaaaaaaaaa", Charset.defaultCharset());
                        ChannelFuture channelFuture = f.channel().writeAndFlush(byteBuf);
                    }else {
                        Throwable throwable = future.cause();
                        throwable.printStackTrace();
                    }
                }
            });
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            eventLoopGroup.shutdownGracefully().sync();
        }
    }
}
