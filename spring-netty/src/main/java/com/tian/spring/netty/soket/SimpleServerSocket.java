package com.tian.spring.netty.soket;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author tianbeiping
 * @Title: SimpleServerSocket
 * @ProjectName mvn-test
 * @Description:
 * @date 18/10/30下午2:27
 */
public class SimpleServerSocket {


    public static void main(String[] args) throws IOException {


        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(9099));

        Socket accept = serverSocket.accept();

        System.out.println("start===");

        InputStream inputStream = accept.getInputStream();

        InputStreamReader reader = new InputStreamReader(inputStream);

        BufferedReader bufferedReader = new BufferedReader(reader);

        StringBuffer buffer = new StringBuffer();

        String str = null;
        if ((str = bufferedReader.readLine()) != null) {
            buffer.append(str);
        }
//        byte[] data = new byte[1024];
//        while (inputStream.read(data) != -1) {
//            buffer.append(new String(data));
//        }

        System.out.println(buffer.toString());
        accept.shutdownInput();  //关闭输入流

        System.out.println("read end===");


        OutputStream outputStream = accept.getOutputStream();


        PrintWriter writer = new PrintWriter(outputStream);

        writer.write("欢迎您!");

        writer.flush();

        System.out.println("write end===");

        writer.close();
        outputStream.close();

        bufferedReader.close();
        reader.close();
        inputStream.close();

    }


}
