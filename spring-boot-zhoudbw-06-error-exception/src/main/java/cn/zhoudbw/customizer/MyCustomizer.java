package cn.zhoudbw.customizer;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * @author zhoudbw
 * 使用WebServerFactoryCustomizer设置错误页面
 * @COnfiguration 声明这是一个配置文件，需要使用这个类声明bean，代替xml文件的
 */
@Configuration
public class MyCustomizer {
    /**
     * 因为是配置相关的，所以泛型自然要传递配置相关的，要求<T extends WebServerFactory>，传递ConfigurableWebServerFactory
     */
    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> customizer() {
        return new WebServerFactoryCustomizer<ConfigurableWebServerFactory>() {
            @Override
            public void customize(ConfigurableWebServerFactory factory) {

                /*  // 创建错误页，使用下述构造方法
                	public ErrorPage(HttpStatus status, String path) {
                        this.status = status;
                        this.exception = null;
                        this.path = path;
	                }
	                status表示状态码
	                path表示请求路径
	                当status==404时，转发到/error404请求
                 */
                ErrorPage errorPage = new ErrorPage(HttpStatus.NOT_FOUND, "/error404");
                // 设置错误页
                factory.addErrorPages(errorPage);
            }
        };
    }
}
