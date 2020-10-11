package uestc.zhangkx.nio;

import java.nio.ByteBuffer;

/**
 * @author zhangkx
 * @version 1.0
 * @date 2020/9/27 15:18
 */
public class ReadOnlyBuffer {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(64);

        for (int i = 0; i < 64; i++) {
            byteBuffer.put((byte) i);
        }

        byteBuffer.flip();

        ByteBuffer readOnlyBuffer = byteBuffer.asReadOnlyBuffer();
        System.out.println(readOnlyBuffer.getClass());

        while(readOnlyBuffer.hasRemaining()){
            System.out.println(readOnlyBuffer.get());
        }
    }
}
