package com.tian.spring.common.chain;

/**
 * @author tianbeiping
 * @Title: OneFilter
 * @ProjectName mvn-test
 * @Description:
 * @date 19/3/21下午2:50
 */
public class OneFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) {
        System.out.println("OneFilter=======before");

        Result invoke = invoker.invoke(invocation);

        System.out.println("OneFilter=======after");

        return invoke;
    }
}
