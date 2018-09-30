## mvn-test


#### resteasy validate
```text
<dependency>   
    <groupId>org.jboss.resteasy</groupId>   
    <artifactId>resteasy-hibernatevalidator-provider</artifactId>   
    <version>2.3.1.GA</version>
</dependency>
```


#### resteasy client api 
```java

public class UserClient {

    public static void start() {

//        Client client = ClientBuilder.newClient();
//        //... or...
//        Client client = ClientBuilder.newBuilder().build();
//        WebTarget target = client.target("http://foo.com/resource");
//        Response response = target.request().post(Entity.entity("",MediaType.APPLICATION_JSON_TYPE));
//        String value = response.readEntity(String.class);
//        response.close();  // You should close connections!
       
        /**
        *  可以像rpc 一样处理
        */
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://0.0.0.0:8081");
        UserSvc userSvc = target.proxy(UserSvc.class);
        String s = userSvc.get();
        System.out.println(s);
    }


    public static void main(String[] args) {

        start();
    }
}
```

