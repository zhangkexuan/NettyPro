package uestc.zhangkx.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhangkx
 * @version 1.0
 * @date 2020/9/25 20:57
 */
public class BIOServer {
    public static void main(String[] args) throws IOException {
        //线程池机制

        //思路
        //1. 创建线程池
        //2. 如果有客户端连接，就创建一个线程，与之通讯（单独写一个方法）

        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();

        //创建ServerSocket
        ServerSocket serverSocket = new ServerSocket(6666);

        System.out.println("服务器启动了。。。");

        while (true) {
            //监听，等待 客户端 连接
            final Socket socket = serverSocket.accept();
            System.out.println("连接到一个客户端了。。。");
            //线程池分配出线程
            newCachedThreadPool.execute(()->handler(socket));
        }
    }

    //编写一个handler方法
    public static void handler(Socket socket) {
        try {
            System.out.println("线程信息ID="+ Thread.currentThread().getId()+"名字"+Thread.currentThread().getName());
            byte[] bytes = new byte[1024];
            //通过socket获取输入流
            InputStream inputStream = socket.getInputStream();
            //循环读取客户端发送的数据
            while(true){
                System.out.println("线程信息ID="+ Thread.currentThread().getId()+"名字"+Thread.currentThread().getName());
                int read = inputStream.read(bytes);
                if(read!=-1){
                    System.out.println(new String(bytes,0,read));//输出客户端发送的数据
                }else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            System.out.println("关闭client连接");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
