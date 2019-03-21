package com.tian.spring.common.chain;



/**
 * @author tianbeiping
 * @Title: Filter
 * @ProjectName mvn-test
 * @Description:
 * @date 19/3/21下午2:51
 */
public interface Filter {

    Result invoke(Invoker<?> invoker, Invocation invocation);

}
