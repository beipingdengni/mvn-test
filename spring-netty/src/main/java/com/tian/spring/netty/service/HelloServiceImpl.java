package com.tian.spring.netty.service;

import com.tian.spring.netty.handler.HelloHandler;

import java.util.List;

/**
 * @author tianbeiping
 * @Title: HelloServiceImpl
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/28下午2:31
 */
public class HelloServiceImpl implements HelloService {

    private HelloHandler helloHandler = new HelloHandler();

    @Override
    public List<String> getLs() {
        return helloHandler.getPersonName();
    }
}
