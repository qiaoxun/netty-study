package com.qiao.frame.decodetest;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class ChatInboundHandler extends SimpleChannelInboundHandler<LengthFieldMsg> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LengthFieldMsg msg) throws Exception {
        System.out.println("Msg is " + msg);
    }
}
