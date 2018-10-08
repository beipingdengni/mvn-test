package com.tian.spring.analysis.aop;


import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author tianbeiping
 * @Title: IconLabelData
 * @ProjectName mwee_paidui_soa
 * @Description: TODO
 * @date 2018/10/3 20:48
 */
public class AopProxy implements MethodInterceptor {

    private final static int MAX_LEVEL = 3;
    private final static String DOT = ".";

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        System.out.println("start aop " + method.getName());

        Object process = methodProxy.invokeSuper(o, objects);
        System.out.println("return proxy " + process);

        System.out.println("end aop " + method.getName());

        return process;
    }


    public static String getMethodName(Method method) {
        if (method == null) {
            return null;
        }
        String[] arr = method.toString().split(" ");
        String methodName = arr[2].split("\\(")[0] + "()";
        String[] arr2 = methodName.split("\\.");
        if (arr2.length > MAX_LEVEL) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < arr2.length; i++) {
                if (i <= MAX_LEVEL) {
                    sb.append(arr2[i].substring(0, 1) + DOT);
                } else {
                    sb.append(arr2[i] + DOT);
                }
            }
            String temp = sb.toString();
            if (temp.endsWith(DOT)) {
                temp = temp.substring(0, temp.length() - 1);
            }
            return temp;
        }
        return methodName;
    }
}
