package com.tian.spring.thrift.pool2;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * @author tianbeiping
 * @Title: IconLabelData
 * @ProjectName mwee_paidui_soa
 * @Description: TODO
 * @date 2018/10/3 13:47
 */
public class TProtocolFactory extends BasePooledObjectFactory<TProtocol> {


    private String host;
    private int port;
    private boolean keepAlive = true;
    private boolean isNonBlocking = true;


    public TProtocolFactory(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public TProtocolFactory(String host, int port, boolean keepAlive, boolean isNonBlocking) {
        this.host = host;
        this.port = port;
        this.keepAlive = keepAlive;
        this.isNonBlocking = isNonBlocking;
    }

    public TProtocol create() throws Exception {

        TSocket socket = new TSocket(host, port);

        TTransport transport = null;

        TProtocol protocol;
        if (isNonBlocking) {
            transport = new TFramedTransport(socket);
            transport.open();
            protocol = new TCompactProtocol(transport);
        } else {
            socket.open();
            protocol = new TCompactProtocol(socket);
        }
        return protocol;
    }

    public PooledObject<TProtocol> wrap(TProtocol tProtocol) {
        return new DefaultPooledObject<TProtocol>(tProtocol);
    }

    @Override
    public void passivateObject(PooledObject<TProtocol> p) throws Exception {
        if (!keepAlive) {
            p.getObject().getTransport().flush();
            p.getObject().getTransport().close();
        }
    }

    @Override
    public void activateObject(PooledObject<TProtocol> p) throws Exception {
        if (!p.getObject().getTransport().isOpen()) {
            p.getObject().getTransport().open();
        }
    }

    @Override
    public void destroyObject(PooledObject<TProtocol> p) throws Exception {
        passivateObject(p);
        p.markAbandoned();
    }


    @Override
    public boolean validateObject(PooledObject<TProtocol> p) {
        if (p.getObject() != null) {
            if (p.getObject().getTransport().isOpen()) {
                return true;
            }
            try {
                p.getObject().getTransport().open();
                return true;
            } catch (TTransportException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
