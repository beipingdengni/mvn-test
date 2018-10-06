package com.tian.spring.resteasy.server;

import com.google.common.collect.Lists;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.util.ServerInfo;
import org.apache.coyote.http11.Http11NioProtocol;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;
import org.jboss.resteasy.spi.ResteasyDeployment;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import java.nio.file.Files;
import java.util.Enumeration;

/**
 * @author tianbeiping
 * @Title: TomcatServer
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/29上午10:33
 */
public class TomcatServer {

    private final HttpServletDispatcher dispatcher = new HttpServletDispatcher();
    private final ResteasyDeployment deployment = new ResteasyDeployment();


    public void start() {

        Tomcat tomcat = new Tomcat();
        try {
            String tempPath = Files.createTempDirectory("tomcat").toString();
//            String tempPath = System.getProperty("java.io.tmpdir");
            tomcat.setBaseDir(tempPath);
            tomcat.setPort(9090);

            Connector connector = tomcat.getConnector();

            Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
            //设置最大连接数
            protocol.setMaxConnections(2000);
            //设置最大线程数
            protocol.setMaxThreads(2000);
            protocol.setConnectionTimeout(30000);

            /**
             * context
             *
             * tomcat.getHost().addChild(context);
             * StandardContext context = new StandardContext();
             * context.setPath(contextPath);
             * context.addLifecycleListener(new Tomcat.FixContextListener());
             * // context.addLifecycleListener(new Tomcat.DefaultWebXmlListener());
             */
            Context context = tomcat.addContext("", tempPath);

<<<<<<< HEAD
//            ServletContext servletContext = context.getServletContext();
//            servletContext.setAttribute(ResteasyDeployment.class.getName(), deployment);
//            dispatcher.init(new SimpleServletConfig(servletContext));

            context.addLifecycleListener(new Tomcat.FixContextListener());
            context.addParameter("resteasy.resources", "com.tian.spring.resteasy.impl.UserServiceImpl");
//            context.addParameter("resteasy.providers", "com.tian.spring.resteasy.excaption.NotFoundException");
            context.addApplicationListener("org.jboss.resteasy.plugins.server.servlet.ResteasyBootstrap");
            //resteasy和spring整合，有了这个，ContextLoaderListener就不要了
            //context.addApplicationListener("org.jboss.resteasy.plugins.spring.SpringContextLoaderListener");
=======
            ServletContext servletContext = context.getServletContext();
            context.addLifecycleListener(new Tomcat.FixContextListener());
            
            /**
             * 方案一 解决 推荐
             */
            Class<?> aClass = Class.forName("com.tian.spring.resteasy.impl.UserServiceImpl");
            deployment.setActualResourceClasses(Lists.newArrayList(aClass));
            servletContext.setAttribute(ResteasyDeployment.class.getName(), deployment);
            dispatcher.init(new SimpleServletConfig(servletContext));

            /**
             * 方案二 可以手动配置
             */
//            context.addParameter("resteasy.resources", "com.tian.spring.resteasy.impl.UserServiceImpl");
//            context.addParameter("resteasy.providers", "com.tian.spring.resteasy.excaption.NotFoundException");
//            context.addApplicationListener("org.jboss.resteasy.plugins.server.servlet.ResteasyBootstrap");
            // resteasy和spring整合，有了这个，ContextLoaderListener就不要了
//            context.addApplicationListener("org.jboss.resteasy.plugins.spring.SpringContextLoaderListener");
>>>>>>> 0a0bb18289d453574c6d3b6414da08d3c155f13b

            Tomcat.addServlet(context, "DispatcherServlet", dispatcher);
            context.addServletMappingDecoded("/*", "DispatcherServlet");



            tomcat.start();
            tomcat.getServer().await();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    private static class SimpleServletConfig implements ServletConfig {

        private final ServletContext servletContext;

        public SimpleServletConfig(ServletContext servletContext) {
            this.servletContext = servletContext;
        }

        @Override
        public String getServletName() {
            return "DispatcherServlet";
        }

        @Override
        public ServletContext getServletContext() {
            return servletContext;
        }

        @Override
        public String getInitParameter(String s) {
            return null;
        }

        @Override
        public Enumeration getInitParameterNames() {
            return new Enumeration() {
                @Override
                public boolean hasMoreElements() {
                    return false;
                }

                @Override
                public Object nextElement() {
                    return null;
                }
            };
        }
    }

}
