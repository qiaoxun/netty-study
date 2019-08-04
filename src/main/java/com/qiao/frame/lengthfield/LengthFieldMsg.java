package com.qiao.frame.lengthfield;

public class LengthFieldMsg {

    /**
     * length is 1 byte
     * 0XAB stands for System A and 0XBA stands for System B
     */
    private byte type;

    /**
     * length is 1 byte
     * 0XAB stands for heart beat message, 0XAA stands for normal message...
     */
    private byte flag;

    /**
     * length is 4 bytes
     * Stands for body's length not include header
     * So
     *  lengthFieldOffset = 2
     *  lengthFieldLength = 4
     *  lengthAdjustment = 0
     *  initialBytesToStrip = 0 // we want the header
     */
    private int length;

    private String body;

    public LengthFieldMsg() {
    }

    public LengthFieldMsg(byte type, byte flag, int length, String body) {
        this.type = type;
        this.flag = flag;
        this.length = length;
        this.body = body;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public byte getFlag() {
        return flag;
    }

    public void setFlag(byte flag) {
        this.flag = flag;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "LengthFieldMsg{" +
                "type=" + type +
                ", flag=" + flag +
                ", length=" + length +
                ", body='" + body + '\'' +
                '}';
    }
}
