package uestc.zhangkx.nio;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.RandomAccess;

/**
 * MappedByteBuffer
 * 直接在内存修改（堆外内存） os不需要再拷贝一次
 * @author zhangkx
 * @version 1.0
 * @date 2020/9/27 15:22
 */
public class MappedByteBufferTest {
    public static void main(String[] args) throws Exception {
        RandomAccessFile randomAccessFile = new RandomAccessFile("f:\\1.txt", "rw");
        //获取对应的文件通道
        FileChannel channel = randomAccessFile.getChannel();
        /**
         * map方法
         *  建立文件-内存的映射
         * @params: mode--使用读写模式
         *          position--可以直接修改的起始位置
         *          size--映射到内存的大小 即将文件的多少个字节映射到内存
         */
        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        mappedByteBuffer.put(0, (byte) 'H');
        mappedByteBuffer.put(3, (byte) '9');

        randomAccessFile.close();
        System.out.println("success");

    }
}
