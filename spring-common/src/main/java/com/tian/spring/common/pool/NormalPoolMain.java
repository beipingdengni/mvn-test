package com.tian.spring.common.pool;

import com.tian.spring.common.pool.normal.ConnectionFactory;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.stream.IntStream;

/**
 * @author tianbeiping
 * @Title: NormalPoolMain
 * @ProjectName mvn-test
 * @Description:
 * @date 19/3/8上午11:32
 */
public class NormalPoolMain {


    public static void main(String[] args) throws Exception {


        GenericObjectPoolConfig config = new GenericObjectPoolConfig();

        PooledObjectFactory objectFactory = new ConnectionFactory("127.0.0.1", 7788);

        GenericObjectPool genericObjectPool = new GenericObjectPool(objectFactory, config);


        for (int i = 0; i < 10; i++) {
            int i1 = i;
            new Thread(() -> {
                try {
                    Socket s = (Socket) genericObjectPool.borrowObject();

                    InputStream is = s.getInputStream();
                    OutputStream os = s.getOutputStream();

                    //向服务器端发送一条消息
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
                    bw.write("客户端发送消息===>>>>>" + i1 + "\n");
                    bw.flush();

                    //读取服务器返回的消息
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    String mess = br.readLine();
                    System.out.println("客户端收到：" + mess);

                    genericObjectPool.returnObject(s);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }).start();
        }

    }
}
