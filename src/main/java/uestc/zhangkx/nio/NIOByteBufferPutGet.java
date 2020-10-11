package uestc.zhangkx.nio;

import java.nio.ByteBuffer;

/**
 * @author zhangkx
 * @version 1.0
 * @date 2020/9/27 15:00
 */
public class NIOByteBufferPutGet {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(64);

        buffer.putInt(100);
        buffer.putLong(23);
        buffer.putChar('张');
        buffer.putShort((short) 4);

        //取出
        buffer.flip();

        System.out.println();

        System.out.println(buffer.getInt());
        System.out.println(buffer.getLong());
        System.out.println(buffer.getChar());
        System.out.println(buffer.getShort());
    }
}

