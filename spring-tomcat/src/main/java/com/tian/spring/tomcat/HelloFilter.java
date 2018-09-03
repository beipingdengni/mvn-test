package com.tian.spring.tomcat;


import javax.servlet.*;
import java.io.IOException;

/**
 * @author tianbeiping
 * @Title: HelloFilter
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/3下午2:43
 */
public class HelloFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("hello filter init ");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("hello filter doFilter ");

        System.out.println("filter do =====> start ");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("filter do =====> end ");
    }

    @Override
    public void destroy() {
        System.out.println("hello filter destroy ");
    }
}
