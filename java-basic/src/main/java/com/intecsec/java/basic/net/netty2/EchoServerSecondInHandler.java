package com.intecsec.java.basic.net.netty2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

 
import java.util.concurrent.ScheduledExecutorService;
 
public class EchoServerSecondInHandler extends ChannelInboundHandlerAdapter {
 
 
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("SecondHandler: 注册事件");
    }
 
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("SecondHandler: 取消注册事件");
    }
 
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("SecondHandler: 有新客户端连接接入。。。"+ctx.channel().remoteAddress());
    }
 
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("SecondHandler: 失去连接");
    }
 
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf in = (ByteBuf) msg;
        System.out.println("SecondHandler: 读客户端传入数据="+in.toString(CharsetUtil.UTF_8));
        ctx.writeAndFlush(Unpooled.copiedBuffer("SecondHandler channelRead data!", CharsetUtil.UTF_8));
        //ctx.fireChannelActive();
    }
 
    public void channelReadComplete(ChannelHandlerContext ctx){
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()) {                    
                }else { 
                }
            }
        });
        ctx.writeAndFlush(Unpooled.copiedBuffer("SecondHandler: channelReadComplete data!", CharsetUtil.UTF_8)).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess())  {
                	System.out.println("SecondHandler: 执行成功="+future.isSuccess());
                }else {
 
                }
            }
        });
    }
 
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("SecondHandler userEventTriggered");
    }
 
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        System.out.println("SecondHandler channelWritabilityChanged");
    }
 
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}