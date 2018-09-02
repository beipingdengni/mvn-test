package com.tian.spring.tomcat;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.FileAttributeView;
import java.nio.file.attribute.PosixFilePermissions;

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
        tempDir=tempDirectory.toString();
        System.out.println(tempDir);


        Tomcat tomcat = new Tomcat();
        tomcat.setPort(9080);
        tomcat.setBaseDir(tempDir);
        tomcat.getHost().setAutoDeploy(false);

        String contextPath = "/book";
        StandardContext context = new StandardContext();
        context.setPath(contextPath);
        context.addLifecycleListener(new Tomcat.FixContextListener());
        tomcat.getHost().addChild(context);

        tomcat.addServlet(contextPath, "homeServlet", new HomeServlet());
        context.addServletMappingDecoded("/home", "homeServlet");
        tomcat.start();



//        Tomcat tomcat = new Tomcat();
//        tomcat.setBaseDir(tempDir);
//        tomcat.setPort(8080);
//        tomcat.getHost().setAutoDeploy(false);
//
//        String contextPath = "/book";
//        Context context = new StandardContext();
//        context.setPath(contextPath);
//        context.addLifecycleListener(new Tomcat.FixContextListener());
//
//        tomcat.getHost().addChild(context);
//
//        tomcat.addServlet(contextPath, "HelloServlet", new HelloServlet());
////        context.addServletMappingDecoded("/hello", "HelloServlet");
//        context.addServletMapping("/hello", "HelloServlet");
//
//        System.out.println(" start ....... ");
//        context.start();
//        tomcat.getServer().await();
    }

}
