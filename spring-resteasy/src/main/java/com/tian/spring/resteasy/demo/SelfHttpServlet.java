package com.tian.spring.resteasy.demo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author tianbeiping
 * @Title: SelfHttpServlet
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/29下午2:30
 */
public class SelfHttpServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");

        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter writer = resp.getWriter();

        writer.print("测试数据");
        System.out.println("ok");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req, resp);
    }
}
