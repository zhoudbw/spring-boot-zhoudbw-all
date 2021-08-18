package cn.zhoudbw.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author zhoudbw
 * 自定义Filter
 * 在学习Struts的时候，Struts将Filter使用到了极致。
 * 我们会在web.xml中配置拦截器，比如说：
 * <filter>
 * <filter-name>struts2</filter-name>
 * <filter-class>com.zhoudbw.filter.CustomFilter</filter-class>
 * </filter>
 * <p>
 * <filter-mapping>
 * <filter-name>struts2</filter-name>
 * <url-pattern>/*</url-pattern>
 * </filter-mapping>
 * 这是最早期的时候我们使用Filter的时候，web.xml提供给我们的一种配置方式，
 * <p>
 * 我们这里并不需要再像早期那样，配置web.xml而是实现Filter接口，引入包，实现方法
 * import javax.servlet.Filter
 * init() doFilter() destory()
 */
public class CustomFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("..........CustomFilter init..........");
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         // 如果我们希望doFilter()向下继续执行，需要执行一下FilterChain这个过滤器链
                         FilterChain filterChain)
            throws IOException, ServletException {
        System.out.println("..........CustomFilter doFilter..........");
        // 执行完当前Filter后，继续向下执行Filter
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {
        System.out.println("..........CustomFilter destroy..........");
    }
}
