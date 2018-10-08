package com.tian.spring.thrift;

import com.tian.spring.thrift.api.BaseService;
import com.tian.spring.thrift.service.BaseServiceImpl;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

/**
 * @author tianbeiping
 * @Title: IconLabelData
 * @ProjectName mwee_paidui_soa
 * @Description: TODO
 * @date 2018/10/3 12:52
 */
public class ThreadPoolServerMainTest {

    public static void main(String[] args) throws TTransportException, InterruptedException {


        TProcessor processor = new BaseService.Processor<BaseService.Iface>(new BaseServiceImpl());
//        TNonblockingServerTransport transport=new T

        TNonblockingServerSocket.NonblockingAbstractServerSocketArgs nonblockingAbstractServerSocketArgs = new TNonblockingServerSocket.NonblockingAbstractServerSocketArgs();
        nonblockingAbstractServerSocketArgs.clientTimeout(5000);
        nonblockingAbstractServerSocketArgs.port(9009);
        nonblockingAbstractServerSocketArgs.wait(5000);
        nonblockingAbstractServerSocketArgs.backlog(1000);
        TNonblockingServerSocket serverSocket = new TNonblockingServerSocket(nonblockingAbstractServerSocketArgs);

        TThreadPoolServer.Args poolArg = new TThreadPoolServer.Args(serverSocket);
        poolArg.maxWorkerThreads(200);
        poolArg.processor(processor);
        poolArg.transportFactory(new TFramedTransport.Factory());
        poolArg.protocolFactory(new TBinaryProtocol.Factory());

        TServer server = new TThreadPoolServer(poolArg);
        server.serve();


    }
}
