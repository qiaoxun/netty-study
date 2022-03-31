package com.qiao.hello.world;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

// Marks this class as one whose instance can be shared among channels
@ChannelHandler.Sharable
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // When noticed that the channel is active, send message
        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty Rocks".getBytes(CharsetUtil.UTF_8)));
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        // Logs a dump of the received message
        System.out.println("Client received: " + msg.toString(CharsetUtil.UTF_8));
    }

    // On exception, logs the error and closes channel
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
