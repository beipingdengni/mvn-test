package com.tian.spring.common.pool.thrift;

import org.apache.thrift.protocol.TProtocol;

/**
 * @author tianbeiping
 * @Title: ConnectionProvider
 * @ProjectName mvn-test
 * @Description:
 * @date 19/3/8上午11:24
 */
public interface ConnectionProvider {

    /**
     * 从连接池里获取一个TProtocol对象
     * @return
     */
    TProtocol getConnection();

    /**
     * 将一个TProtocol对象放回连接池
     * @param tProtocol
     */
    void returnConnection(TProtocol tProtocol);


}
