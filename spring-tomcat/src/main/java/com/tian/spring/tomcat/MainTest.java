package com.tian.spring.tomcat;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardWrapper;
import org.apache.catalina.startup.Tomcat;
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


        String tempDir = System.getProperty("user.dir");
        System.out.println(tempDir);

        Path tempDirectory = Files.createTempDirectory("tomcat");
        tempDir = tempDirectory.toString();
        System.out.println(tempDir);


        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir(tempDir);
        tomcat.setPort(9080);
        tomcat.getHost().setAutoDeploy(false);

        String contextPath = "";
        StandardContext context = new StandardContext();
        context.setPath(contextPath);
        context.addLifecycleListener(new Tomcat.FixContextListener());

//        context.addParameter("contextConfigLocation", "classpath:spring-context.xml");
//        ContextLoaderListener contextLoaderListener = new ContextLoaderListener();
//        context.addApplicationLifecycleListener(contextLoaderListener);

        tomcat.getHost().addChild(context);

        // servlet
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
