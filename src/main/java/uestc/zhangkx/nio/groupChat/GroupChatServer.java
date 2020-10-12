package uestc.zhangkx.nio.groupChat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

/**
 * 群聊系统groupChat
 * 服务器端
 *
 * @author zhangkx
 * @version 1.0
 * @date 2020/10/12 17:00
 */
public class GroupChatServer {

    //定义相关属性
    private Selector selector;
    private ServerSocketChannel listenChannel;
    private static final int PORT = 6667;


    /**
     * 初始化构造器（各种资源的申请）
     */
    public GroupChatServer() {
        try {
            //得到选择器
            selector = Selector.open();
            //得到serverSocketChannel
            listenChannel = ServerSocketChannel.open();
            //绑定
            listenChannel.bind(new InetSocketAddress(PORT));
            //设置非阻塞
            listenChannel.configureBlocking(false);
            //将listenChannel注册
            listenChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listen() {
        System.out.println("监听线程：" + Thread.currentThread().getName());

        try {
            while (true) {
                int count = selector.select();
                if (count > 0) {
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();

                        if (key.isAcceptable()) {
                            SocketChannel socketChannel = listenChannel.accept();
                            socketChannel.configureBlocking(false);
                            socketChannel.register(selector, SelectionKey.OP_READ);
                            System.out.println(socketChannel.getRemoteAddress() + "上线");
                        }

                        if (key.isReadable()) {
                            readData(key);
                        }

                        iterator.remove();
                    }
                } else {
                    System.out.println("等待...");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //异常处理
        }
    }

    private void readData(SelectionKey key) {
        SocketChannel channel = null;
        try{
            channel = (SocketChannel) key.channel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            int read = channel.read(byteBuffer);
            if (read>0){
                String msg = new String(byteBuffer.array());
                System.out.println("form 客户端"+msg);
                sendInfoToOtherClients(msg,channel);
            }
        } catch (IOException e) {
            try {
                System.out.println(channel.getRemoteAddress()+"离线了");
                key.cancel();
                channel.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    private void sendInfoToOtherClients(String msg, SocketChannel channel) {
        System.out.println("服务器消息转发中...");
        System.out.println("服务器消息转发数据给客户端线程:"+ Thread.currentThread().getName());
        selector.keys().stream().forEach(key->{
            Channel targetChannel = key.channel();
            if (targetChannel instanceof SocketChannel && targetChannel != channel){
                SocketChannel dest = (SocketChannel)targetChannel;
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                try {
                    dest.write(buffer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        new GroupChatServer().listen();
    }
}
