package com.tian.spring.thrift;

import com.tian.spring.thrift.api.BaseService;
import com.tian.spring.thrift.vo.base.PersonVo;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
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
 * @date 2018/10/3 13:16
 */
public class ConsumerTNonBlockingSocketClient {

    public static void main(String[] args) throws TException {


        TTransport tFramedTransport = new TFramedTransport(new TSocket("127.0.0.1", 9009, 5000));

        TProtocol protocol = new TBinaryProtocol(tFramedTransport);

        BaseService.Client client = new BaseService.Client(protocol);
        // 连接
        tFramedTransport.open();

        PersonVo result = client.dealPerson("liyao", "nan", 18);

        System.out.println(result.toString());


    }
}
