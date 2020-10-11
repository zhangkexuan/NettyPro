package uestc.zhangkx.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 只用一个缓冲区实现粘贴复制
 *
 * @author zhangkx
 * @version 1.0
 * @date 2020/9/27 14:05
 */
public class NIOFileChannel03 {
    public static void main(String[] args) throws Exception {

        FileInputStream fileInputStream = new FileInputStream("f:\\1.txt");
        FileChannel inputStreamChannel = fileInputStream.getChannel();
        FileOutputStream fileOutputStream = new FileOutputStream("f:\\2.txt");
        FileChannel outputStreamChannel = fileOutputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        while(true){
            byteBuffer.clear();
            //入buffer
            int read = inputStreamChannel.read(byteBuffer);
            if(read == -1){
                break;
            }
            byteBuffer.flip();
            //出buffer
            outputStreamChannel.write(byteBuffer);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }
}
