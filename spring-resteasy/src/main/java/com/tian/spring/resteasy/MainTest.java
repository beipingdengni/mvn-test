package com.tian.spring.resteasy;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author tianbeiping
 * @Title: MainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/29上午10:19
 */
public class MainTest {

    /*
       * LogonRequest lr = new LogonRequest();
         lr.setPrincipal("admin");
         lr.setPassword("123456");
         lr.setPrincipalType(PrincipalType.UniqueName);
         ResteasyClient _restClient =  (new ResteasyClientBuilder()).build();
        ResteasyWebTarget target = _restClient.target("http://127.0.0.1:9080/logon");
        Builder post = target.request().header("Content-Type", "application/json;charset=utf-8");
        post.accept(MediaType.APPLICATION_JSON);
        post.acceptEncoding("UTF-8");
        Response response =  post.post(Entity.entity(lr, MediaType.APPLICATION_JSON));
        LogonResponse logon = response.readEntity(LogonResponse.class);

        best demo 使用 ：resteasy demo;
        https://blog.csdn.net/u011411069/article/details/77119918

       * */

    public static void main(String[] args) {

        String property = System.getProperty("java.io.tmpdir");
        System.out.println(property);


        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("spring-resteasy.xml");

    }
}
