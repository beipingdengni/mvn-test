package com.tian.spring.thrift;

import com.tian.spring.thrift.api.BaseService;
import com.tian.spring.thrift.service.BaseServiceImpl;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;

/**
 * @author tianbeiping
 * @Title: IconLabelData
 * @ProjectName mwee_paidui_soa
 * @Description: TODO
 * @date 2018/10/3 12:52
 */
public class ProviderThreadSelectorServer {

    public static void main(String[] args) throws TTransportException, InterruptedException {


        TProcessor processor = new BaseService.Processor<BaseService.Iface>(new BaseServiceImpl());

        TNonblockingServerSocket.NonblockingAbstractServerSocketArgs nonblockingAbstractServerSocketArgs = new TNonblockingServerSocket.NonblockingAbstractServerSocketArgs();
//        nonblockingAbstractServerSocketArgs.clientTimeout(5000);
        nonblockingAbstractServerSocketArgs.port(9009);
//        nonblockingAbstractServerSocketArgs.wait(5000);
        nonblockingAbstractServerSocketArgs.backlog(1000);
        TNonblockingServerSocket serverSocket = new TNonblockingServerSocket(nonblockingAbstractServerSocketArgs);

        TThreadedSelectorServer.Args poolArg = new TThreadedSelectorServer.Args(serverSocket);
        poolArg.workerThreads(200);
        poolArg.selectorThreads(4);
        poolArg.acceptQueueSizePerThread(256);
        poolArg.processor(processor);
        poolArg.transportFactory(new TFramedTransport.Factory());
        poolArg.protocolFactory(new TCompactProtocol.Factory());

        TServer server = new TThreadedSelectorServer(poolArg);
        server.serve();
    }
}
