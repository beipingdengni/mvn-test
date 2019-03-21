package com.tian.spring.common.chain;

/**
 * @author tianbeiping
 * @Title: Invoker
 * @ProjectName mvn-test
 * @Description:
 * @date 19/3/21下午2:52
 */
public interface Invoker<T> {


    Result invoke(Invocation invocation);

}
