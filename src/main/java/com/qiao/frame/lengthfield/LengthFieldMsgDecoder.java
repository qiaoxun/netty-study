package com.qiao.frame.lengthfield;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.TooLongFrameException;
import io.netty.util.CharsetUtil;

public class LengthFieldMsgDecoder extends LengthFieldBasedFrameDecoder {

    private static final int HEAD_SIZE = 6; // lengthFieldOffset + lengthFieldLength
    private byte type;
    private byte flag;
    private int length;
    private String body;

    /**
     * @param maxFrameLength The maximum number of the message
     * @param lengthFieldOffset The length before Length field
     * @param lengthFieldLength The length of Length field
     * @param lengthAdjustment The compensation value to add to the value of the length field
     * @param initialBytesToStrip The number of first bytes to strip out from the decoded frame
     * @param failFast
     *  If <tt>true</tt>, a {@link TooLongFrameException} is thrown as
     *  soon as the decoder notices the length of the frame will exceed
     *  <tt>maxFrameLength</tt> regardless of whether the entire frame
     *  has been read.  If <tt>false</tt>, a {@link TooLongFrameException}
     *  is thrown after the entire frame that exceeds <tt>maxFrameLength</tt>
     *  has been read.
     */
    public LengthFieldMsgDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength,
                                 int lengthAdjustment, int initialBytesToStrip, boolean failFast) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength,
                lengthAdjustment, initialBytesToStrip, failFast);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        if (in == null) {
            return null;
        }
        int readableBytes = in.readableBytes();
        // check if the message's size is larger then head size
        if (readableBytes < HEAD_SIZE) {
            throw new RuntimeException("readable bytes can not be less then HEAD_SIZE " + HEAD_SIZE);
        }

        type = in.readByte();
        flag = in.readByte();
        length = in.readInt();

        readableBytes = in.readableBytes();
        if (readableBytes != length) {
            throw new RuntimeException("The message's length in header is " + length + ", but the message's real length is " + readableBytes);
        }

        ByteBuf messageBuf = in.readBytes(length);
        String body = messageBuf.toString(CharsetUtil.UTF_8);

        LengthFieldMsg lengthFieldMsg = new LengthFieldMsg(type, flag, length, body);

        return lengthFieldMsg;
    }
}
