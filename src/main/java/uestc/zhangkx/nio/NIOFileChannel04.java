package uestc.zhangkx.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * 使用transferFrom拷贝
 *
 * @author zhangkx
 * @version 1.0
 * @date 2020/9/27 14:53
 */
public class NIOFileChannel04 {
    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("F:\\a.jpg");
        FileOutputStream fileOutputStream = new FileOutputStream("F:\\b.jpg");
        FileChannel inChannel = fileInputStream.getChannel();
        FileChannel outChannel = fileOutputStream.getChannel();
        outChannel.transferFrom(inChannel,0, inChannel.size());
        fileOutputStream.close();
        fileInputStream.close();
    }
}
