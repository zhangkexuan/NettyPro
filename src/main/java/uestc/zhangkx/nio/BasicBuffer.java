package uestc.zhangkx.nio;

import java.nio.IntBuffer;

/**
 * @author zhangkx
 * @version 1.0
 * @date 2020/9/26 9:27
 */
public class BasicBuffer {
    public static void main(String[] args) {
        //说明buffer的使用
        //创建一个buffer,大小为5，之后还会详细讲
        IntBuffer intBuffer = IntBuffer.allocate(5);

        //向Buffer中存放数据
      /*intBuffer.put(10);
        intBuffer.put(11);
        intBuffer.put(12);
        intBuffer.put(13);
        intBuffer.put(14);*/

        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i*2);
        }

        //从buffer中读取数据
        //讲buffer转换，读写切换
        intBuffer.flip();

        while(intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }
    }
}
