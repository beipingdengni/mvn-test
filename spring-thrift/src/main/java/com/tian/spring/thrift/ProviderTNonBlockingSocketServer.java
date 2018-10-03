package com.tian.spring.thrift;

import com.tian.spring.thrift.api.BaseService;
import com.tian.spring.thrift.service.BaseServiceImpl;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.transport.TFastFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransportException;

/**
 * @author tianbeiping
 * @Title: IconLabelData
 * @ProjectName mwee_paidui_soa
 * @Description: TODO
 * @date 2018/10/3 13:12
 */
public class ProviderTNonBlockingSocketServer {

    public static void main(String[] args) throws TTransportException {

        TProcessor processor = new BaseService.Processor<BaseService.Iface>(new BaseServiceImpl());

        TNonblockingServerSocket serverSocket = new TNonblockingServerSocket(9009);

        TNonblockingServer.Args nonblockingServer = new TNonblockingServer.Args(serverSocket);

        nonblockingServer.processor(processor);
        nonblockingServer.protocolFactory(new TBinaryProtocol.Factory());
        nonblockingServer.transportFactory(new TFastFramedTransport.Factory());

        TNonblockingServer server = new TNonblockingServer(nonblockingServer);

        server.serve();


    }

}
