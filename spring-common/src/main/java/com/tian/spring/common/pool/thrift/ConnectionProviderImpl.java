package com.tian.spring.common.pool.thrift;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.thrift.protocol.TProtocol;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author tianbeiping
 * @Title: ConnectionProviderImpl
 * @ProjectName mvn-test
 * @Description:
 * @date 19/3/8上午11:25
 */
public class ConnectionProviderImpl implements ConnectionProvider, InitializingBean {

    // thrift服务器的配置
    private ThriftServiceConfigItem thriftServiceConfigItem;

    // 连接池
    private GenericObjectPool<TProtocol> objectPool;

    @Override
    public TProtocol getConnection() {
        try {
            return objectPool.borrowObject();
        } catch (Exception e) {
            throw new RuntimeException("getConnection出现异常", e);
        }
    }

    @Override
    public void returnConnection(TProtocol tProtocol) {
        try {
            // 将对象放回对象池
            objectPool.returnObject(tProtocol);
        } catch (Exception e) {
            throw new RuntimeException("returnConnection出现异常", e);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // 初始化连接工厂
        ThriftPooledObjectFactory thriftPooledObjectFactory
                = new ThriftPooledObjectFactory(thriftServiceConfigItem);
        // 初始化连接池
        objectPool = new GenericObjectPool<>(thriftPooledObjectFactory);
        // TODO:设置连接池的参数，否则使用默认的配置

    }

    public void setThriftServiceConfigItem(ThriftServiceConfigItem thriftServiceConfigItem) {
        this.thriftServiceConfigItem = thriftServiceConfigItem;
    }
}
