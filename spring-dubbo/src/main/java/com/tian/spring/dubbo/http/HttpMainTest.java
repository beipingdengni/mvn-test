package com.tian.spring.dubbo.http;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author tianbeiping
 * @Title: HttpMainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/14下午1:47
 */
public class HttpMainTest {
    public static void main(String[] args) throws InterruptedException, IOException {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-provider-http.xml");

        context.start();

        System.in.read();

//        while (true) {
//            TimeUnit.SECONDS.sleep(30);
//        }


    }
}
