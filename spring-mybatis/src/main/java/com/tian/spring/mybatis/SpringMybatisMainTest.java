package com.tian.spring.mybatis;

import com.tian.spring.mybatis.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author tianbeiping
 * @Title: SpringMybatisMainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/10下午1:22
 */
public class SpringMybatisMainTest {


    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-mybatis.xml");

        UserService userService = context.getBean(UserService.class);

        userService.insertPerson();

        System.out.println("====end====");


    }

}
