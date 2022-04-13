package per.study.filter;

import javax.servlet.*;
import java.io.IOException;

public class CharacterEncodingFilter implements Filter {

    /*初始化*/
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("初始化");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        System.out.println("执行前");
        chain.doFilter(request, response);
        System.out.println("执行后");

    }

    /*销毁*/
    @Override
    public void destroy() {
        System.out.println("销毁");
    }
}
