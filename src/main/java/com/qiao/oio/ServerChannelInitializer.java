package com.qiao.oio;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;

public class ServerChannelInitializer extends ChannelInitializer {

    private ByteBuf byteBuf;

    public ServerChannelInitializer(ByteBuf byteBuf) {
        this.byteBuf = byteBuf;
    }
    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new ServerHandler(byteBuf));
    }
}
