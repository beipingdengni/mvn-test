package com.tian.spring.thrift;

import com.tian.spring.thrift.api.BaseService;
import com.tian.spring.thrift.service.BaseServiceImpl;
import com.tian.spring.thrift.vo.base.PersonVo;
import org.apache.thrift.TException;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransportException;

/**
 * @author tianbeiping
 * @Title: IconLabelData
 * @ProjectName mwee_paidui_soa
 * @Description: TODO
 * @date 2018/10/3 12:52
 */
public class ConsumerThreadSelectorClient {

    public static void main(String[] args) throws TException, InterruptedException {


        TFramedTransport transport = new TFramedTransport(new TSocket("127.0.0.1", 9009, 5000));

        TProtocol protocol = new TCompactProtocol(transport);

        BaseService.Client client = new BaseService.Client(protocol);

        transport.open();

        PersonVo result = client.dealPerson("liyao", "nan", 18);

        System.out.println(result.toString());

        transport.close();

    }
}
