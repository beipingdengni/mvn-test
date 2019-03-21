package com.tian.spring.dubbo.http;

import com.alibaba.dubbo.rpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author tianbeiping
 * @Title: MyFilter
 * @ProjectName mvn-test
 * @Description:
 * @date 19/3/21上午10:57
 */
public class MyFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(MyFilter.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        logger.info("MyFilter====> start ====>");

        Result invoke = invoker.invoke(invocation);

        logger.info("MyFilter====> Invocation" + invocation + "  Result===>" + invoke);

        return invoke;
    }
}
