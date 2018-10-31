package com.tian.spring.netty;

import java.nio.ByteBuffer;

/**
 * @author tianbeiping
 * @Title: ByteBufferMainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/10/31上午10:23
 */
public class ByteBufferMainTest {


    public static void main(String[] args) {


        ByteBuffer buffer = ByteBuffer.allocate(1024);

        buffer.put("测试数据".getBytes());

        // 写-->读 ， 读-->写
        buffer.flip();

        String str = new String(buffer.array(), 0, buffer.limit());

        System.out.println(str);


    }

}
