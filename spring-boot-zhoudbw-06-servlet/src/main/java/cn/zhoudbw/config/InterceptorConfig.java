package cn.zhoudbw.config;

import cn.zhoudbw.interceptor.CustomInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author zhoudbw
 * 将拦截器注册到Spring容器中，通过该配置类
 * 该配置类需要实现WebMVCConfigurer
 *  重写 addInterceptors()方法，添加注册类。
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    /**
     * 我们自定义的拦截器，已经交由Spring管理了，所以不使用new的方式，而是用注入的方式
     */
    @Autowired
    private CustomInterceptor customInterceptor;

    /**
     * 注册自定义的拦截器，并定义拦截规则
     * @param registry 拦截器的注册器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // InterceptorRegistry拦截器的注册中心，我们需要将我们自定义的拦截器放入到注册中心中
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(customInterceptor);
        // 指定拦截的规则 /** 表示全部拦截
        interceptorRegistration.addPathPatterns("/**");
    }
}
