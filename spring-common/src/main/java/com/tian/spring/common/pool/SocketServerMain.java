package com.tian.spring.common.pool;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author tianbeiping
 * @Title: SocketServerMain
 * @ProjectName mvn-test
 * @Description:
 * @date 19/3/8下午2:17
 */
public class SocketServerMain {


    public static void main(String[] args) throws IOException {


        ServerSocket serverSocket = new ServerSocket();

        serverSocket.bind(new InetSocketAddress(7788));


        while (true) {
            Socket s = serverSocket.accept();

            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            //读取客户端发送来的消息
            String mess = br.readLine();
            System.out.println("服务端：" + mess);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            bw.write("服务端发送信息 :" + mess + "\n");
            bw.flush();
        }


    }


}
