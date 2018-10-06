package com.tian.spring.ribbon.feign;

import com.netflix.config.ConfigurationManager;
import com.tian.spring.ribbon.feign.config.SelfRequestInterceptor;
import com.tian.spring.ribbon.feign.provider.UserServiceProvider;
import com.tian.spring.ribbon.feign.vo.UserVo;
import feign.*;
import feign.auth.BasicAuthRequestInterceptor;
import feign.hystrix.HystrixFeign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.ribbon.RibbonClient;
import feign.slf4j.Slf4jLogger;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

/**
 * @author tianbeiping
 * @Title: FeignMainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/30下午1:57
 */
public class FeignMainTest {

    public static void main(String[] args) throws IOException {

        /**
         *  可以参考的博客 ： https://blog.csdn.net/u010862794/article/details/73649616
         */

//        ConfigurationManager.loadProperties(new Properties());
        ConfigurationManager.loadPropertiesFromResources("resteasy-client.properties");

        UserServiceProvider service = Feign.builder()   // 默认传递
//        UserServiceProvider service = HystrixFeign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .logger(new Slf4jLogger())
                .options(new Request.Options(1000, 3500))
                .retryer(new Retryer.Default(5000, 5000, 3))
                .client(RibbonClient.create())   // 负载均衡
                .requestInterceptor(new SelfRequestInterceptor())
                .requestInterceptor(new BasicAuthRequestInterceptor("name", "password"))
//                .logLevel(Logger.Level.BASIC)
                .target(UserServiceProvider.class, "http://resteasy-client");


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
