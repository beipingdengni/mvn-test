package com.tian.spring.thrift;

import com.tian.spring.thrift.api.BaseService;
import com.tian.spring.thrift.service.BaseServiceImpl;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

/**
 * @author tianbeiping
 * @Title: ProviderMainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/18下午4:07
 */
public class SimpleProviderServer {
    public static void main(String[] args) {

        try {
            System.out.println("服务端开启....");

            TProcessor processor = new BaseService.Processor<BaseService.Iface>(new BaseServiceImpl());
            TServerSocket tServerSocket = new TServerSocket(50005);

            TServer.Args tArgs = new TServer.Args(tServerSocket);
            tArgs.processor(processor);
            tArgs.protocolFactory(new TBinaryProtocol.Factory());
            TServer server = new TSimpleServer(tArgs);
            server.serve();

        } catch (TTransportException e) {
            e.printStackTrace();
        }

    }
}
