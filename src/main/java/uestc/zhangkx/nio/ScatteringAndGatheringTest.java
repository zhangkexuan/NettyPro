package uestc.zhangkx.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Scattering: 将数据写入到buffer时，可以采用buffer数组，依次写入【分散】
 * Gathering： 从buffer读取数据时，可以采用buffer数组，依次读取 【聚合】
 *
 * @author zhangkx
 * @version 1.0
 * @date 2020/10/10 20:24
 */
public class ScatteringAndGatheringTest {
    public static void main(String[] args) throws IOException {
        //使用ServerSocketChannel和SocketChannel(网络编程)

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(7000);

        //绑定端口到socket 并启动
        serverSocketChannel.socket().bind(inetSocketAddress);

        //创建buffer数组
        ByteBuffer[] byteBuffers = new ByteBuffer[2];
        byteBuffers[0] = ByteBuffer.allocate(5);
        byteBuffers[1] = ByteBuffer.allocate(3);

        //等客户端连接（telnet）
        SocketChannel socketChannel = serverSocketChannel.accept();
        int messageLength = 8;
        while (true) {
            int byteRead = 0;
            while (byteRead < messageLength ) {
                long read = socketChannel.read(byteBuffers);
                byteRead += read;//累计所读的字节数
                System.out.println("byteRead=" + byteRead);
                //流打印 查看buffers的position和limit
                Arrays.asList(byteBuffers).stream().map(buffer -> "postion=" + buffer.position() + ",limit=" + buffer.limit()).forEach(System.out::println);
            }

            //将所有buffer都翻转
            Arrays.asList(byteBuffers).stream().forEach(byteBuffer -> byteBuffer.flip());

            //将数据读出显示到客户端
            long byteWirte = 0;
            while (byteWirte < messageLength) {
                long l = socketChannel.write(byteBuffers);
                byteWirte += l;
            }

            //将所有的buffer 进行clear
            Arrays.asList(byteBuffers).forEach(buffer-> {buffer.clear();});

            System.out.println("byteRead:=" + byteRead + " byteWrite=" + byteWirte + ", messagelength" + messageLength);
        }
    }
}