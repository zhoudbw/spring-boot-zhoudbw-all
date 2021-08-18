package cn.zhoudbw.config;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhoudbw
 * 在应用启动后，通过WebServerApplicationContext对象获取 （非web应用时失败）
 */
@Configuration
public class WebServerName {
    @Bean
    public ApplicationRunner runner(WebServerApplicationContext context) {
        return args -> {
            System.out.println("当前web容器的实现类是: " +
                    context.getWebServer().getClass().getName());
        };
    }
}
