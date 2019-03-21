package com.tian.spring.dubbo.http;

import com.tian.spring.dubbo.vo.UserVo;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author tianbeiping
 * @Title: HttpMainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/14下午1:47
 */
public class HttpMainConsumerTest {
    public static void main(String[] args) throws IOException {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-consumer-http.xml");

        HttpUserService bean = context.getBean(HttpUserService.class);

        UserVo user = bean.createUser();

        System.out.println(user);


    }
}
