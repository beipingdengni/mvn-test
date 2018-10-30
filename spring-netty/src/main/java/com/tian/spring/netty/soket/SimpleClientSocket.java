package com.tian.spring.netty.soket;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author tianbeiping
 * @Title: SimpleClientSocket
 * @ProjectName mvn-test
 * @Description:
 * @date 18/10/30下午2:28
 */
public class SimpleClientSocket {

    public static void main(String[] args) throws IOException {


        Socket socket = new Socket("localhost", 9099);
//        socket.connect(new InetSocketAddress("localhost", 9099));

        OutputStream outputStream = socket.getOutputStream();

        PrintWriter writer = new PrintWriter(outputStream);

        writer.write("用户名：admin；密码：123");

        writer.flush();

        System.out.println("write end===");

        socket.shutdownOutput(); // 关闭输入流

        InputStream inputStream = socket.getInputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String info = null;
        while ((info = reader.readLine()) != null) {
            System.out.println("我是客户端，服务器说：" + info);
        }

        System.out.println("read end===");

        reader.close();
        inputStream.close();
        outputStream.close();


    }
}
