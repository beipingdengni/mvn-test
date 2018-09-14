package com.tian.spring.dubbo.consumer;

import com.tian.spring.dubbo.BaseInterface.UserService;
import com.tian.spring.dubbo.vo.UserVo;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author tianbeiping
 * @Title: ConsumerMainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/14下午12:48
 */
public class ConsumerMainTest {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-consumer.xml");

        context.start();

        UserService bean = context.getBean(UserService.class);

        UserVo userVo = bean.createUser("田北平", 12, (byte) 3);

        System.out.println("consumer 收到回复信息 ===》" + userVo.toString());


    }
}
