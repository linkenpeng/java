package com.intecsec.java.basic.net.netty2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandler;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
 
import java.nio.charset.Charset;
 
public class EchoServerOutHandler extends ChannelOutboundHandlerAdapter {
 
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println("EchoServerOutHandler write: "
           +((ByteBuf)msg).toString(Charset.defaultCharset()));
        ctx.write(msg, promise);
    }
}