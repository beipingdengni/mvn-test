package com.tian.spring.thrift.pool2;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * @author tianbeiping
 * @Title: IconLabelData
 * @ProjectName mwee_paidui_soa
 * @Description: TODO
 * @date 2018/10/3 14:04
 */
public class AutoClearGenericObjectPool<T> extends GenericObjectPool<T> {

    public AutoClearGenericObjectPool(PooledObjectFactory<T> factory) {
        super(factory);
    }

    public AutoClearGenericObjectPool(PooledObjectFactory<T> factory, GenericObjectPoolConfig<T> config) {
        super(factory, config);
    }

    @Override
    public void returnObject(T obj) {
        if (getNumIdle() >= getNumActive()) {
            clear();
        }
    }
}
