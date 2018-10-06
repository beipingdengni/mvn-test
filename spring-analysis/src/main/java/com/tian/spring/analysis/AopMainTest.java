package com.tian.spring.analysis;

import com.tian.spring.analysis.aop.AopClassTest;
import com.tian.spring.analysis.aop.AopProxy;
import com.tian.spring.analysis.aop.ProxyUtils;

/**
 * @author tianbeiping
 * @Title: IconLabelData
 * @ProjectName mwee_paidui_soa
 * @Description: TODO
 * @date 2018/10/3 21:01
 */
public class AopMainTest {


    public static void main(String[] args) {

//        AopClassTest test=new AopClassTest();

        AopClassTest proxyObject = ProxyUtils.createProxyObject(AopClassTest.class);

        String start = proxyObject.start();

        System.out.println(start);


    }

}
