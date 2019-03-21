package com.tian.spring.common.chain;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author tianbeiping
 * @Title: Invcation
 * @ProjectName mvn-test
 * @Description:
 * @date 19/3/21下午3:09
 */
public class Invocation {

    private String methodName;

    private Object[] params;

    private Object object;

    private Class[] classes;


    public Invocation(String methodName, Object object, Object[] params, Class[] classes) {
        this.methodName = methodName;
        this.params = params;
        this.object = object;
        this.classes = classes;
    }


    public Result invokeMethod() {

        Result invoke = new Result();
        try {
            Method method = object.getClass().getMethod(methodName, classes);
            Object dt = method.invoke(object, this.params);
            invoke.setData(dt);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return invoke;
    }


}
