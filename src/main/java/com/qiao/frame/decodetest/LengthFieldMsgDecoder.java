package com.qiao.frame.decodetest;

import io.netty.buffer.ByteBuf;
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

    /**
     * 粘包的问题并没有解决，如果一条信息被两次传送过来，那么这里就会报错，因为第一次已经开始读取，而且第一次数据不全
     * 第二次读取也没有考虑到前面一次剩余的部分
     * @param ctx
     * @param in
     * @return
     * @throws Exception
     */
    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        if (in == null) {
            return null;
        }
        int readableBytes = in.readableBytes();

        // 如果不读 in 中的数据，下次执行这个方法，in 里面上次传过来的数据还在
        System.out.println("readableBytes is : " + readableBytes);

        return null;
    }
}
