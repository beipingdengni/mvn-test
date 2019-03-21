package com.tian.spring.common.chain;

/**
 * @author tianbeiping
 * @Title: TwoFilter
 * @ProjectName mvn-test
 * @Description:
 * @date 19/3/21下午2:51
 */
public class TwoFilter implements Filter {

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) {

        System.out.println("TwoFilter=======before");

        Result invoke = invoker.invoke(invocation);

        System.out.println("TwoFilter=======after");

        return invoke;
    }
}
