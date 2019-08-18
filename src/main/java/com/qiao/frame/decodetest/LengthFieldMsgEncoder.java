package com.qiao.frame.decodetest;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.CharsetUtil;

@ChannelHandler.Sharable
public class LengthFieldMsgEncoder extends MessageToByteEncoder<LengthFieldMsg> {
    @Override
    protected void encode(ChannelHandlerContext ctx, LengthFieldMsg msg, ByteBuf out) throws Exception {
        if (null == msg) {
            throw new RuntimeException("msg is null");
        }

        String body = msg.getBody();
        byte[] bodyBytes = body.getBytes(CharsetUtil.UTF_8);
        out.writeByte(msg.getType());
        out.writeByte(msg.getFlag());
        out.writeInt(msg.getLength());
        out.writeBytes(bodyBytes);
    }
}
