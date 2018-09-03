package con.tian.spring.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author tianbeiping
 * @Title: MainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/3下午11:13
 */
public class MainTest {

    public static void main(String[] args) throws Exception {


        Server server = new Server();
        //server.setHandler(new HelloWorldHandler());

        // 创建一个HTTP的连接，配置监听主机，端口，以及超时时间
        ServerConnector http = new ServerConnector(server);
        http.setHost("localhost");
        http.setPort(8080);
        http.setIdleTimeout(30000);

        // 将此连接添加到Server
        server.addConnector(http);

        //在/hello路径上增加一个处理器
//        ContextHandler context = new ContextHandler();
//        context.setContextPath("/hl");
//        context.setHandler(new HelloWorldHandler());
//        //context.addEventListener(new ContextLoaderListener());
//        //可以通过http://localhost:8080/hello访问
//        server.setHandler(context);

        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);
        //http servlet
        handler.addServletWithMapping(DispatcherServlet.class, "/hello");

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
