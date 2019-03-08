package com.tian.spring.common.pool.normal;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Objects;

/**
 * @author tianbeiping
 * @Title: PoolObj
 * @ProjectName mvn-test
 * @Description:
 * @date 19/3/8上午11:32
 */
public class ConnectionFactory extends BasePooledObjectFactory<Socket> {


    private InetSocketAddress address;

    public ConnectionFactory(String host, int port) {
        address = new InetSocketAddress(host, port);
    }

    @Override
    public Socket create() throws Exception {
        Socket socket = new Socket();
        socket.connect(address);
        return socket;
    }

    @Override
    public PooledObject<Socket> wrap(Socket socket) {
        return new DefaultPooledObject<>(socket);
    }

    @Override
    public void destroyObject(PooledObject<Socket> p) throws Exception {
        Socket obj = p.getObject();
        if (Objects.nonNull(obj)) {
            obj.close();
        }

    }

    @Override
    public boolean validateObject(PooledObject<Socket> p) {
        Socket socket = p.getObject();
        return socket != null && socket.isBound() && !socket.isClosed() && socket.isConnected() && !socket.isInputShutdown() && !socket.isOutputShutdown();
    }
}
