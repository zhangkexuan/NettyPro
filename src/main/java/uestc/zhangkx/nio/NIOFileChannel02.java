package uestc.zhangkx.nio;


import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 这里的读取只是和写入在逻辑上翻转了一下
 * 想要读取就是从磁盘上建立出FileInputStream，和通道绑定
 * 这个通道内就自动是磁盘文件的内容，只需要将内容从通道通向缓冲区
 * 缓冲区直接连接到sout
 *
 * @author zhangkx
 * @version 1.0
 * @date 2020/9/27 11:05
 */
public class NIOFileChannel02 {
    public static void main(String[] args) throws Exception {
        //使用new file的理由  --> file.length()方法可以让Buffer.allocate()更加有精确
        File file = new File("f:\\file01.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        System.out.println(fileInputStream);

        //Channel是存在于Stream中的
        FileChannel channel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());

        //read是出channel，入buffer
        channel.read(byteBuffer);

        //从buffer中输出到sout
        System.out.println(new String(byteBuffer.array()));

        fileInputStream.close();
    }
}
