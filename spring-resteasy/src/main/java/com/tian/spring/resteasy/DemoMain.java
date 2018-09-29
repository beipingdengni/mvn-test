package com.tian.spring.resteasy;

import com.tian.spring.resteasy.server.TomcatServer;
import org.apache.catalina.Context;
import org.apache.catalina.Wrapper;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.apache.coyote.http11.Http11NioProtocol;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;
import org.jboss.resteasy.spi.ResteasyDeployment;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import java.lang.reflect.Field;
import java.util.Enumeration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author tianbeiping
 * @Title: DemoMain
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/29下午1:16
 */
public class DemoMain {

    private final HttpServletDispatcher dispatcher = new HttpServletDispatcher();
    private final ResteasyDeployment deployment = new ResteasyDeployment();


    public static void main(String[] args) {
        DemoMain demoMain = new DemoMain();
        demoMain.start();
    }

    public void start() {

        Tomcat tomcat = new Tomcat();
        try {
            String tempPath = System.getProperty("java.io.tmpdir");
            System.out.println(tempPath);
            tomcat.setBaseDir(tempPath);
            tomcat.setPort(9999);
            Connector connector = tomcat.getConnector();
            Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
            //设置最大连接数
            protocol.setMaxConnections(2000);
            //设置最大线程数
            protocol.setMaxThreads(2000);
            protocol.setConnectionTimeout(30000);

            System.out.println();
            Context context = tomcat.addContext("", tempPath);

            context.addParameter("resteasy.resources", "com.tian.spring.resteasy.UserSvc");
//            context.addParameter("javax.ws.rs.core.Application", "com.tian.spring.resteasy.UserSvcApp");
            context.addApplicationListener("org.jboss.resteasy.plugins.server.servlet.ResteasyBootstrap");

            ServletContext servletContext = context.getServletContext();
            servletContext.setAttribute(ResteasyDeployment.class.getName(), deployment);
            /**
             * servlet
             */
//            dispatcher.init(new TomcatServer.SimpleServletConfig(servletContext));

            Tomcat.addServlet(context, "DefaultDispatcher", dispatcher);
            context.addServletMappingDecoded("/*", "DefaultDispatcher");

            tomcat.start();
            tomcat.getServer().await();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public class SimpleServletConfig implements ServletConfig {

        private final ServletContext servletContext;

        private Map<String, String> map = new ConcurrentHashMap<>();

        public SimpleServletConfig(ServletContext servletContext) {
            this.servletContext = servletContext;
            map.put("javax.ws.rs.core.Application", "com.tian.spring.resteasy.UserSvcApp");
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
            return map.get(s);
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
