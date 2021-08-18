package cn.zhoudbw.config;

import cn.zhoudbw.filter.CustomFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhoudbw
 * 实现Filter接口，创建好一个Filter。
 * 那么怎么使用这个Filter呢？
 * SpringBoot给我们提供了一个类 —— FilterRegistrationBean，过滤器的注册bean
 * 我们创建配置类，将这个bean拿出来，然后将我们自己创建的Filter，通过这个注册过滤器的bean，set进去。
 * 这样就可以使用了。
 */
@Configuration
public class FilterConfig {
    /**
     * 创建注册过滤器的bean，传递需要注册的过滤器为泛型
     * 这个类，也就替代了 web.xml中配置的
     * <filter>
     *      <filter-name></filter-name>
     *      <filter-class></filter-class>
     * </filter>
     * <filter-mapping>
     *      <filter-name></filter-name>
     *      <url-pattern></url-pattern>
     * </filter-mapping>
     */
    @Bean
    public FilterRegistrationBean<CustomFilter> filterFilterRegistrationBean() {
        FilterRegistrationBean<CustomFilter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        // 注册我们自定义的Filter
        filterFilterRegistrationBean.setFilter(new CustomFilter());
        // 设置过滤规则 /* 表示全部过滤
        filterFilterRegistrationBean.addUrlPatterns("/*");
        // 决定注册的优先级，order越小，优先级越高
//        filterFilterRegistrationBean.setOrder(0)
        return filterFilterRegistrationBean;
    }
}
