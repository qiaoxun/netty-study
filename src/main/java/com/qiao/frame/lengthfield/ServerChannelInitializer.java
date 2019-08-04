package com.qiao.frame.lengthfield;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    private int maxFrameLength = 1024 * 1024;
    private int lengthFieldOffset = 2;
    private int lengthFieldLength = 4;
    private int lengthAdjustment = 0;
    private int initialBytesToStrip = 0;
    private boolean failFast = true;

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("frame", new LengthFieldMsgDecoder(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip, failFast));
        pipeline.addLast("handler", new ChatInboundHandler());
    }
}
