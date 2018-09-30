package com.tian.spring.ribbon.feign;

import com.tian.spring.ribbon.feign.provider.UserServiceProvider;
import com.tian.spring.ribbon.feign.vo.UserVo;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author tianbeiping
 * @Title: MainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/30下午1:48
 */
public class MainTest {


    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-feign.xml");

        UserServiceProvider service = context.getBean(UserServiceProvider.class);

        UserVo userVo = new UserVo();
        userVo.setId("client");
        userVo.setName("tian");

        UserVo userVos = service.addUser(userVo);
        System.out.println(userVos);

        List<UserVo> userAll = service.getUserAll();
        System.out.println(userAll);

        UserVo owner = service.getUser("12");
        System.out.println(owner);


    }
}
