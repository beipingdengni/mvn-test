package com.tian.spring.tomcat;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardWrapper;
import org.apache.catalina.startup.Tomcat;
import org.apache.coyote.http11.Http11NioProtocol;
import org.apache.tomcat.util.descriptor.web.FilterDef;
import org.apache.tomcat.util.descriptor.web.FilterMap;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.FileAttributeView;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Arrays;

/**
 * @author tianbeiping
 * @Title: IconLabelData
 * @ProjectName mwee_paidui_soa
 * @Description: TODO
 * @date 2018/9/2 11:54
 */
public class MainTest {


    public static void main(String[] args) throws LifecycleException, IOException {

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

        String tempDir = System.getProperty("user.dir");
        System.out.println(tempDir);

        Path tempDirectory = Files.createTempDirectory("tomcat");
        tempDir = tempDirectory.toString();
        System.out.println(tempDir);


        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir(tempDir);
        tomcat.setPort(9080);
        tomcat.getHost().setAutoDeploy(false);

        Connector connector = tomcat.getConnector();
//        connector.setProperty("minProcessors", "10");
//        connector.setProperty("maxProcessors", "75");
//        connector.setProperty("acceptCount", "100");
//        connector.setProperty("enableLookups", "false");
//        connector.setProperty("connectionTimeout", "30000");
//        connector.setProtocol("org.apache.coyote.http11.Http11NioProtocol");

        Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
        //设置最大连接数
        protocol.setMaxConnections(2000);
        //设置最大线程数
        protocol.setMaxThreads(2000);
        protocol.setConnectionTimeout(30000);

        /**
         * 默认protocol:  org.apache.coyote.http11.Http11NioProtocol
         * minProcessors：最小空闲连接线程数，用于提高系统处理性能，默认值为10
         * maxProcessors：最大连接线程数，即：并发处理的最大请求数，默认值为75
         * acceptCount：允许的最大连接数，应大于等于maxProcessors，默认值为100
         * enableLookups：是否反查域名，取值为：true或false。为了提高处理能力，应设置为false
         * connectionTimeout：网络连接超时，单位：毫秒。设置为0表示永不超时，这样设置有隐患的。通常可设置为30000毫秒。
         * URIEncoding:UTF-8
         */

        String contextPath = "";
        StandardContext context = new StandardContext();
        context.setPath(contextPath);
        context.addLifecycleListener(new Tomcat.FixContextListener());

        // context-param
        //context.addParameter("contextConfigLocation", "classpath:spring-context.xml");
        // listener
        //context.addApplicationListener("org.springframework.web.context.ContextLoaderListener");

        // filter class
        FilterDef filterDef = new FilterDef();
        filterDef.setFilter(new HelloFilter());
        filterDef.setFilterName("hello-filter");
        context.addFilterDef(filterDef);
        // filter map
        FilterMap filterMap = new FilterMap();
        filterMap.addURLPattern("/*");
        filterMap.setFilterName("hello-filter");
        context.addFilterMap(filterMap);


        tomcat.getHost().addChild(context);

        // spring servlet
        Wrapper wrapper = new Tomcat.ExistingStandardWrapper(new DispatcherServlet());
        context.addChild(wrapper);
        wrapper.setName("defaultServletName");
        wrapper.addMapping("/*");
        wrapper.addInitParameter("contextConfigLocation", "classpath:spring-mvc.xml");
        wrapper.setLoadOnStartup(1);


        System.out.println(" start ....... ");

        tomcat.start();
        tomcat.getServer().await();
    }

}
