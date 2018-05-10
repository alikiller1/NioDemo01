package com.liuqh.NioDemo01;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * 获取通道
 * 
 * @author zhq
 * 
 */
public class ByteBufferTest {
    private static final int SIZE = 1024;

    public static void main(String[] args) throws Exception {
        // 获取通道，该通道允许写操作
        FileChannel fc = new FileOutputStream("data.txt").getChannel();
        // 将字节数组包装到缓冲区中
        fc.write(ByteBuffer.wrap("Some text中国".getBytes()));
        // 关闭通道
        fc.close();

        // 随机读写文件流创建的管道
        fc = new RandomAccessFile("data.txt", "rw").getChannel();
        // fc.position()计算从文件的开始到当前位置之间的字节数
        System.out.println("此通道的文件位置：" + fc.position());
        System.out.println("此通道的文件size：" + fc.size());
        // 设置此通道的文件位置,fc.size()此通道的文件的当前大小,该条语句执行后，通道位置处于文件的末尾
        fc.position(fc.size()+1);
        // 在文件末尾写入字节
        fc.write(ByteBuffer.wrap("Some more".getBytes()));
        fc.close();

        // 用通道读取文件
        fc = new FileInputStream("data.txt").getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(SIZE);
        // 将文件内容读到指定的缓冲区中
        fc.read(buffer);
       
        System.out.println("buffer.limit="+buffer.limit());
        System.out.println("buffer.position="+buffer.position());
        buffer.flip();
        System.out.println("buffer.limit2="+buffer.limit());
        System.out.println("buffer.position2="+buffer.position());
        buffer.clear();
        System.out.println("buffer content="+new String(buffer.array()));
        buffer.put("3".getBytes());
        System.out.println("buffer content2="+new String(buffer.array()));
        ByteBuffer bf= ByteBuffer.allocate(SIZE);
        fc.read(bf);
        System.out.println("bf content="+new String(bf.array()));
        fc.close();
        
        buffer.clear();
        FileChannel fc2 = new FileInputStream("data2.txt").getChannel();
        fc2.read(buffer);
        System.out.println("buffer content3="+new String(buffer.array()));
        fc2.close();
    }
}