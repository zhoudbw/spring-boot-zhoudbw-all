package cn.zhoudbw;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author zhoudw
 * Servlet初始化器，继承了SpringBoot的Servlet初始化器
 * 重写了返回SpringApplicationBuilder的configure()方法
 * 这是一个配置方法，这个配置方法的参数也是SpringApplicationBuilder
 * 这个SpringApplicationBuilder可以将我们主程序类放入到sources里面，这也说明启动方式会有变化，也就是说不在使用内置的容器了。
 * 有了这些修改，我们打包，打成的就是wa包而不是jar包了
 */
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootZhoudbw05WebWarApplication.class);
    }
}
