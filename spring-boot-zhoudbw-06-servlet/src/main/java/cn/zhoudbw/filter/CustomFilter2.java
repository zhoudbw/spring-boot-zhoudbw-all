package cn.zhoudbw.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author zhoudw
 * 使用@WebFilter注解，配置自定义过滤器名字和过滤模式
 * 并在入口类中配置扫描该类的注解ServletComponentScan
 */
@WebFilter(filterName = "customFilter2", urlPatterns = {"/*"})
public class CustomFilter2 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("..........CustomFilter init2..........");
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         // 如果我们希望doFilter()向下继续执行，需要执行一下FilterChain这个过滤器链
                         FilterChain filterChain)
            throws IOException, ServletException {
        System.out.println("..........CustomFilter doFilter2..........");
        // 执行完当前Filter后，继续向下执行Filter
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {
        System.out.println("..........CustomFilter destroy2..........");
    }
}
