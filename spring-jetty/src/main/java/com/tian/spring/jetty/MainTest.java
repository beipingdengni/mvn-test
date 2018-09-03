package com.tian.spring.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tianbeiping
 * @Title: MainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/3下午11:13
 */
public class MainTest {

    public static void main(String[] args) throws Exception {

        Path tempDirectory = Files.createTempDirectory("tomcat");
        String tempDir = tempDirectory.toString();
        System.out.println(tempDir);

        Server server = new Server();
        //server.setHandler(new HelloWorldHandler());

        // 创建一个HTTP的连接，配置监听主机，端口，以及超时时间
        ServerConnector http = new ServerConnector(server);
        http.setHost("localhost");
        http.setPort(8080);
        http.setIdleTimeout(30000);

        // 将此连接添加到Server
        server.addConnector(http);
        // 处理完 servlet
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("");
        context.setResourceBase(tempDir);
        server.setHandler(context);
        // context param
        context.setInitParameter("contextConfigLocation", "classpath:spring-context.xml");
        // context listener
        context.addEventListener(new ContextLoaderListener());

        ServletHolder servletHolder = new ServletHolder();
        Map<String, String> mapParam = new HashMap<>();
        mapParam.put("contextConfigLocation", "classpath:spring-mvc.xml");
        servletHolder.setInitParameters(mapParam);
        servletHolder.setServlet(new DispatcherServlet());
        servletHolder.setName("DispatcherServlet");
        context.addServlet(servletHolder, "/*");
        // 增加一个 dump servlet
        //context.addServlet(HelloServlet.class, "/hello");
        // 增加一个默认的servlet
        //context.addServlet(DefaultServlet.class, "/");


        server.start();
        server.join();

    }


    @SuppressWarnings("serial")
    public static class HelloServlet extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
            response.setContentType("text/html");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println("<h1>Hello from HelloServlet</h1>");
        }
    }
}
