package com.intecsec.java.basic.net.netty2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.ReferenceCounted;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
 
public class EchoServerFirstInHandler extends ChannelInboundHandlerAdapter {
 
 
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("FirstHandler: 注册事件");
        ctx.fireChannelRegistered();
    }
 
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        System.out.println("FirstHandler: 取消注册事件");
        ctx.fireChannelUnregistered();
    }
 
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("FirstHandler: 有新客户端连接接入。。。"+ctx.channel().remoteAddress());
        ctx.fireChannelActive();
    }
 
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("FirstHandler: 失去连接");
        ctx.fireChannelInactive();
    }
 
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf in = (ByteBuf) msg;
        System.out.println("FirstHandler: 读客户端传入数据="+in.toString(CharsetUtil.UTF_8));
        final ByteBuf byteBuf = Unpooled.copiedBuffer("FirstHandler channelRead data!", CharsetUtil.UTF_8);
        ctx.writeAndFlush(byteBuf);
        ctx.fireChannelRead(msg);
        //ReferenceCountUtil.release(msg);
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
        final ByteBuf byteBuf = Unpooled.copiedBuffer("FirstHandler channelReadComplete data!", CharsetUtil.UTF_8);
        ctx.writeAndFlush(byteBuf).addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess())  {
                	System.out.println("FirstHandler: 执行成功="+future.isSuccess());
                }else {
 
                }
                //ReferenceCountUtil.release(byteBuf);
            }
        });
        ctx.fireChannelReadComplete();
    }
 
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("FirstHandler userEventTriggered");
    }
 
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        System.out.println("FirstHandler: channelWritabilityChanged");
    }
 
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
