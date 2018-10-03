package com.tian.spring.thrift;

import com.tian.spring.thrift.api.BaseService;
import com.tian.spring.thrift.vo.base.PersonVo;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * @author tianbeiping
 * @Title: ProviderMainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/18下午4:07
 */
public class SimpleConsumerClient {
    public static void main(String[] args) {
        System.out.println("客户端启动....");
        TTransport transport = null;
        try {
            transport = new TSocket("localhost", 50005, 5000);
            TProtocol protocol = new TBinaryProtocol(transport);
            BaseService.Client client = new BaseService.Client(protocol);
            // 连接
            transport.open();

            PersonVo result = client.dealPerson("liyao", "nan", 18);

            System.out.println(result.toString());


        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        } finally {
            if (null != transport) {
                transport.close();
            }
        }
    }


}
