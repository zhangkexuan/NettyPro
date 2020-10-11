package uestc.zhangkx.bio;

import java.io.File;

/**
 * File是对应硬盘中实际存在的一个文件或者目录
 * 不管该路径下是否有该文件或者目录 都不影响file对象的产生
 * @author zhangkx
 * @version 1.0
 * @date 2020/10/8 15:35
 */
public class ClassFile {
    public static void main(String[] args) {
        File file1 = new File("f:/a.txt");

        //获取相关
        System.out.println("文件绝对路径:"+file1.getAbsolutePath());
        System.out.println("文件构造路径:"+file1.getPath());
        System.out.println("文件名称:"+file1.getName());
        System.out.println("文件长度:"+file1.length()+"字节");

        //判断功能
        System.out.println("file1存在吗："+file1.exists());
        System.out.println("file1是文件吗："+file1.isFile());

    }
}
