package cn.zhoudbw.config;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhoudbw
 * @Configuration 声明该类是一个配置类
 */
@Configuration
public class WebConfig {

    /**
     * @Bean 声明bean放入spring容器，让我们可以使用
     * WebServerFactoryCustomer允许我们自己定制配置
     * 传递泛型和配置有关ConfigurableWebServerFactory
     */
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> customizer() {
        /**
          @FunctionalInterface 函数型接口，只有一个方法，只是为了声明这个方法的。
          public interface WebServerFactoryCustomizer<T extends WebServerFactory> {
              // Customize the specified {@link WebServerFactory}.
              // @param factory the web server factory to customize
              void customize(T factory);
          }
         本质是一个接口，泛型需要去实现WebServerFactory，其实就是WebServer的一个工厂
         其中只有唯一一个定制的方法，定制方法所操作的方法也就是它的泛型
         */
        // 因为我们需要创建一个WebServerFactoryCustomizer<ConfigurableWebServerFactory>的bean
        // 所以我们new，但由于是一个接口，所以需要我们实现这个接口的方法
        // 我们通过customize就可以操作这个factory了。
        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
            @Override
            public void customize(ConfigurableWebServerFactory factory) {
                // 设置端口
                factory.setPort(8899);
            }
        };
    }
}
