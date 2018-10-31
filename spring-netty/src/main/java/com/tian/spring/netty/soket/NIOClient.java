package com.tian.spring.netty.soket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;

/**
 * @author tianbeiping
 * @Title: NIOClient
 * @ProjectName mvn-test
 * @Description:
 * @date 18/10/30下午6:39
 */
public class NIOClient {

    public static void main(String[] args) throws IOException {
        SocketChannel clntChan = SocketChannel.open();
        clntChan.configureBlocking(false);
        if (!clntChan.connect(new InetSocketAddress("localhost", 10083))) {
            //不断地轮询连接状态，直到完成连接
            while (!clntChan.finishConnect()) {
                //在等待连接的时间里，可以执行其他任务，以充分发挥非阻塞IO的异步特性
                //这里为了演示该方法的使用，只是一直打印"."
                System.out.print("*");
            }
            System.out.println("连接成功");
        }
        // 分割开
        System.out.println();

        //分别实例化用来读写的缓冲区

        ByteBuffer writeBuf = ByteBuffer.wrap("我是客户端发送的信息，send".getBytes());
        ByteBuffer readBuf = ByteBuffer.allocate(1024);

        while (writeBuf.hasRemaining()) {
            //如果用来向通道中写数据的缓冲区中还有剩余的字节，则继续将数据写入信道
            clntChan.write(writeBuf);

        }
        StringBuffer stringBuffer = new StringBuffer();
        //如果read（）接收到-1，表明服务端关闭，抛出异常
        while ((clntChan.read(readBuf)) > 0) {
            readBuf.flip();
            stringBuffer.append(new String(readBuf.array(), 0, readBuf.limit()));
            readBuf.clear();
        }

        //打印出接收到的数据
        System.out.println("Client Received: " + stringBuffer.toString());
        //关闭信道
        clntChan.close();
    }

}
