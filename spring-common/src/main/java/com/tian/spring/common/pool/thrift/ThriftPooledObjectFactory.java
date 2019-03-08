package com.tian.spring.common.pool.thrift;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * @author tianbeiping
 * @Title: ThriftPooledObjectFactory
 * @ProjectName mvn-test
 * @Description:
 * @date 19/3/8上午11:23
 */
public class ThriftPooledObjectFactory implements PooledObjectFactory<TProtocol> {

    private String serverIP;
    private int serverPort;
    private int timeOut;

    ThriftPooledObjectFactory(ThriftServiceConfigItem thriftServiceConfigItem){
        this.serverIP = thriftServiceConfigItem.getServerIP();
        this.serverPort = thriftServiceConfigItem.getServerPort();
        this.timeOut = thriftServiceConfigItem.getTimeOut();
    }


    @Override
    public PooledObject<TProtocol> makeObject() throws Exception {
        TSocket tSocket = new TSocket(serverIP, serverPort, timeOut);
        TTransport tTransport = new TFramedTransport(tSocket);
        TProtocol tProtocol = new TBinaryProtocol(tTransport);
        tProtocol.getTransport().open();
        return new DefaultPooledObject<>(tProtocol);
    }

    @Override
    public void destroyObject(PooledObject<TProtocol> pooledObject) throws Exception {
        TProtocol tProtocol = pooledObject.getObject();
        if(tProtocol.getTransport().isOpen()){
            tProtocol.getTransport().close();
        }
    }

    @Override
    public boolean validateObject(PooledObject<TProtocol> pooledObject) {
        // 这里确保返回的是已打开的连接
        TProtocol tProtocol = pooledObject.getObject();
        return tProtocol.getTransport().isOpen();
    }

    @Override
    public void activateObject(PooledObject<TProtocol> pooledObject) throws Exception {

    }

    @Override
    public void passivateObject(PooledObject<TProtocol> pooledObject) throws Exception {

    }
}
