package com.qiao.chat1;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class ChatServerHandler extends SimpleChannelInboundHandler<String> {

    private static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel inComing = ctx.channel();
        System.out.println("handlerAdded");
        for (Channel channel : channels) {
            channel.writeAndFlush("[Server]" + inComing.localAddress() + " has joined!\n");
        }
        channels.add(inComing);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerRemoved");
        Channel inComing = ctx.channel();
        for (Channel channel : channels) {
            channel.writeAndFlush("[Server]" + inComing.localAddress() + " has left!\n");
        }
        channels.remove(inComing);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel inComing = ctx.channel();
        System.out.println("channelRead0");
        for (Channel channel : channels) {
            if (channel != inComing)
                channel.writeAndFlush("[" + inComing.localAddress() + "]" + msg + "\n");
//            channel.writeAndFlush()
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.channel().closeFuture().sync();
    }
}
