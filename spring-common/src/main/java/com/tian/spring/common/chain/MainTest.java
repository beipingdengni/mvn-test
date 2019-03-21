package com.tian.spring.common.chain;

import com.tian.spring.common.chain.proxy.CglibProxy;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author tianbeiping
 * @Title: MainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 19/3/21下午2:50
 */
public class MainTest {


    public static void main(String[] args) {


        List<Filter> filters = new ArrayList<>();
        filters.add(new OneFilter());
        filters.add(new TwoFilter());


        // 责任链模式
        Invoker<Invocation> next = Invocation::invokeMethod;
        for (Filter filter : filters) {
            Invoker<Invocation> finalNext = next;
            next = invocation -> filter.invoke(finalNext, invocation);
        }


        TestService service = CglibProxy.getProxy(TestService.class);
        // 拦截器
        Result getUserName = next.invoke(new Invocation("getUserName", service, new Object[]{UUID.randomUUID().toString(), "age ===>"}, new Class[]{String.class, String.class}));

        System.out.println(getUserName.getData());


    }
}
