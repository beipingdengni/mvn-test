package com.tian.spring.resteasy.client;

import com.tian.spring.resteasy.demo.UserSvc;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author tianbeiping
 * @Title: UserClient
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/29下午5:33
 */
public class UserClient {

    public static void start() {

//        Client client = ClientBuilder.newClient();
//        //... or...
//        Client client = ClientBuilder.newBuilder().build();
//        WebTarget target = client.target("http://foo.com/resource");
//        Response response = target.request().post(Entity.entity("",MediaType.APPLICATION_JSON_TYPE));
//        String value = response.readEntity(String.class);
//        response.close();  // You should close connections!
//        ResteasyClient client = new ResteasyClientBuilder().build();
//        ResteasyWebTarget target = client.target("http://foo.com/resource");

//        Client client = ClientFactory.newClient();
//        WebTarget target = client.target("http://example.com/base/uri");
//        ResteasyWebTarget rtarget = (ResteasyWebTarget) target;
//        UserSvc simple = rtarget.proxy(UserSvc.class);
//        client.putBasic("hello world");

        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://0.0.0.0:8081");

        UserSvc proxy = target.proxy(UserSvc.class);

        String s = proxy.get();

        System.out.println(s);
    }


    public static void main(String[] args) {

        start();
    }


}
