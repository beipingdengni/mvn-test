package com.tian.spring.analysis.aop;

import net.sf.cglib.proxy.Enhancer;

/**
 * @author tianbeiping
 * @Title: IconLabelData
 * @ProjectName mwee_paidui_soa
 * @Description: TODO
 * @date 2018/10/3 20:54
 */
public class ProxyUtils {


    public static <T> T createProxyObject(Class<T> type) {
        AopProxy aopProxy = new AopProxy();
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(type);
        enhancer.setCallback(aopProxy);
        return (T) enhancer.create();
    }


}
