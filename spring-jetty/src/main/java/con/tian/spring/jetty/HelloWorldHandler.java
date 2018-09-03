package con.tian.spring.jetty;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author tianbeiping
 * @Title: HelloWorldHandler
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/3下午11:23
 */
public class HelloWorldHandler extends AbstractHandler {

    @Override
    public void handle(String s, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
// 声明response的编码和文件类型
        response.setContentType("text/html; charset=utf-8");

        // 声明返回状态码
        response.setStatus(HttpServletResponse.SC_OK);

        // 请求的返回值
        response.getWriter().println("<h1>Hello World</h1>");

        // 通知Jettyrequest使用此处理器处理请求
        baseRequest.setHandled(true);
    }

}
