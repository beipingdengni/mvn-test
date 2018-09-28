package com.tian.spring.netty.proxy;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.*;

/**
 * @author tianbeiping
 * @Title: JDKProxy
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/28下午4:52
 */
public class JDKProxy implements InvocationHandler {


//    public JDKProxy() {
//        Type type = getClass().getGenericSuperclass();
//        if (type instanceof ParameterizedType) {
//            try {
//                ParameterizedType parameterizedType = (ParameterizedType) type;
//                Type classType = parameterizedType.getActualTypeArguments()[0];
//                if (classType instanceof Class) {
//                    Class<T> tClass = (Class<T>) classType;
//                    tClass.newInstance();
//                }
//            } catch (InstantiationException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    private Object obj;

    public JDKProxy(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("JDKProxy ===> start ");
        System.out.println("method ==>" + JSON.toJSONString(method));
        Object invoke = method.invoke(this.obj, args);
        System.out.println("JDKProxy ===> end ");
        return invoke;
    }


    public static <T> T proxyClass(Class<?> aClass, Object obj) {
        return (T) Proxy.newProxyInstance(aClass.getClassLoader(), new Class[]{aClass}, new JDKProxy(obj));
    }

}
