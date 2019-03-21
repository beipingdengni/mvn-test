package com.tian.spring.common.chain.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author tianbeiping
 * @Title: CglibProxy
 * @ProjectName mvn-test
 * @Description:
 * @date 19/3/21下午3:40
 */
public class CglibProxy {


    public static <T> T getProxy(Class<T> aClass) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(aClass);
        enhancer.setCallback(new MethodInterceptor() {

            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

                String name = method.getName();
                if (Arrays.asList("toString","hashCode").contains(name)) {
                    return methodProxy.invokeSuper(o, objects);
                }
                System.out.println("  执行方法前  CglibProxy  method======>>>>" + method.getName() + "  methodProxy===>>>" + methodProxy.getSuperName());
                Object objKd = methodProxy.invokeSuper(o, objects);
                System.out.println("  执行方法后  CglibProxy  method======>>>>" + method.getName() + "  methodProxy===>>>" + methodProxy.getSuperName());
                return objKd;
            }
        });
        return (T) enhancer.create();
    }

}
