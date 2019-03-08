package com.tian.spring.common.pool;

import org.apache.thrift.protocol.TProtocol;

/**
 * @author tianbeiping
 * @Title: ThriftPoolMain
 * @ProjectName mvn-test
 * @Description:
 * @date 19/3/8上午11:29
 */
public class ThriftPoolMain {


    public static void main(String[] args) {


        // 从连接池中取得连接
//        TProtocol tProtocol = connectionProvider.getConnection();
        // 使用取得的连接创建Client对象
//        ThriftProxy.Client client = new ThriftProxy.Client(tProtocol);

        // do something with the client ...

        // 将连接归还到池中
//        connectionProvider.returnConnection(tProtocol);

        // do some other things ...


    }
}
