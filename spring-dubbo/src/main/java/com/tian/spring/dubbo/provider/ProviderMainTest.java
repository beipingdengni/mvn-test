package com.tian.spring.dubbo.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author tianbeiping
 * @Title: ProviderMainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/14下午12:48
 */
public class ProviderMainTest {

    public static void main(String[] args) throws IOException {


        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-provider.xml");
        context.start();
        // 按任意键退出
        System.in.read();


    }
}
