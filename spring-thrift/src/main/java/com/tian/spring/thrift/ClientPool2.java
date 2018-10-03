package com.tian.spring.thrift;

import com.google.common.util.concurrent.Runnables;
import com.tian.spring.thrift.api.BaseService;
import com.tian.spring.thrift.pool2.AutoClearGenericObjectPool;
import com.tian.spring.thrift.pool2.TProtocolFactory;
import com.tian.spring.thrift.vo.base.PersonVo;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.thrift.protocol.TProtocol;

import java.util.concurrent.TimeUnit;

/**
 * @author tianbeiping
 * @Title: IconLabelData
 * @ProjectName mwee_paidui_soa
 * @Description: TODO
 * @date 2018/10/3 13:47
 */
public class ClientPool2 {

    public static void main(String[] args) throws Exception {

        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(100);
        config.setMinIdle(1);
        TProtocolFactory protocolFactory = new TProtocolFactory("127.0.0.1", 9009);
        AutoClearGenericObjectPool objectPool = new AutoClearGenericObjectPool(protocolFactory, config);

        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(new PoolClient(objectPool), "thread--" + (i + 1));
            thread.start();
        }

    }

    public static class PoolClient implements Runnable {

        AutoClearGenericObjectPool pool;

        public PoolClient(AutoClearGenericObjectPool pool) {
            this.pool = pool;
        }

        public void run() {
            try {
                TProtocol protocol = (TProtocol) pool.borrowObject();
                BaseService.Client client = new BaseService.Client(protocol);

                while (true) {
                    PersonVo result = client.dealPerson("liyao", "nan", 18);
                    System.out.println(result.toString() + " : " + Thread.currentThread().getName());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
