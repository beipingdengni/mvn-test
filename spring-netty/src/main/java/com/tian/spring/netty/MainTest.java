package com.tian.spring.netty;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.tian.spring.netty.proxy.JDKProxy;
import com.tian.spring.netty.service.HelloService;
import com.tian.spring.netty.service.HelloServiceImpl;
import com.tian.spring.netty.support.TransportData;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author tianbeiping
 * @Title: MainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/4上午5:00
 */
public class MainTest {

    public static void main(String[] args) throws Exception {


        HelloService service = JDKProxy.proxyClass(HelloService.class, new HelloServiceImpl());

        System.out.println(service.getLs());


//        Map<String, List<TransportData>> data = new ConcurrentHashMap<>();
//        Class<HelloService> aClass = HelloService.class;
//        String interfaceName = aClass.getName();
//        Method[] methods = aClass.getMethods();
//        List<TransportData> ls = Lists.newArrayList();
//
//        for (int i = 0; i < methods.length; i++) {
//            Method method = methods[i];
//            TransportData transportData = new TransportData();
//            transportData.setInterfaceName(interfaceName);
//            transportData.setMethodName(method.getName());
//            transportData.setArgumentTypes(method.getParameterTypes());
//            transportData.setMethod(method);
//            transportData.setResult(method.getAnnotatedReturnType().getType());
//            ls.add(transportData);
//
//        }
//        data.put(interfaceName, ls);
//        System.out.println(JSON.toJSONString(data));

    }


}
