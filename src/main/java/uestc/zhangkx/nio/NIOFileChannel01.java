package uestc.zhangkx.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 原生io里就有通道
 * 此时的通道是和内存io绑定了（内存io中维护一个内部channel类） fileOutputStream.getChannel()
 * NIO的写入磁盘的操作， 先将内容从内存放入到缓冲区 byteBuffer.put()
 * 理解一下什么是通道-->此时的通道是和fileOutputStream绑定的，那么写入到通道内，会自动通到OutputStream，也就是写入到磁盘了
 *
 * @author zhangkx
 * @version 1.0
 * @date 2020/9/27 10:47
 */
public class NIOFileChannel01 {
    public static void main(String[] args) throws Exception {
        String str = "hello,zhangkx";

        //创建一个输入流->channel
        FileOutputStream fileOutputStream = new FileOutputStream("f:\\file01.txt");

        //通过fileOutputSteam获取对应的FileChannel
        //实际FileChannel是虚拟类，真实使用的是FileChannelImpl
        FileChannel fileChannel = fileOutputStream.getChannel();

        //创建缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //将str 放入到 byteBuffer
        byteBuffer.put(str.getBytes());

        //byteBuffer翻转
        byteBuffer.flip();

        //从byteBuffer到fileChannel
        fileChannel.write(byteBuffer);

        fileOutputStream.close();

    }
}
