package cn.zhoudbw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;


/**
 * @author zhoudw
 */
@SpringBootApplication
public class SpringBootZhoudbw05WebContainerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootZhoudbw05WebContainerApplication.class, args);
    }

    /**
     * 响应式的DEMO
     * 为什么可以直接在入口类中声明一个bean呢？
     *
     * @SpringBootApplication 是一个组合注解，其中的注解之一是@SpringBootConfiguration，
     * 该注解封装的是@Configuration,所以可以不声明@Configuration直接在入口类中声明@Bean
     * 给定一个方法返回路由的方法，指定泛型ServerResponse
     */
    @Bean
    public RouterFunction<ServerResponse> hello() {
        // 可以直接使用route()返回
        // 引入来自org.springframework.web.reactive.function.server.RouterFunctions的静态方法
        // 导入方式：import static org.springframework.web.reactive.function.server.RouterFunctions.route
        /**
         * 可以声明HTTP方法和请求的路径
         *   import static org.springframework.web.reactive.function.server.RequestPredicates.GET
         * 给定处理方法，指定响应的内容类型
         *   import static org.springframework.web.reactive.function.server.ServerResponse.ok
         *   import reactor.core.publisher.Mono
         *   Mono的just()方法，可以返回一个字符串类型的值。
         *   return 什么东西，完全取决于，ok()方法的处理方式
         */
        return route(
                // HTTP请求为GET，请求名为/hello
                GET("/hello"),
                serverRequest -> ok().body(
                   // 返回 Hello World，再声明返回的是字符串类型
                   Mono.just("Hello World"), String.class
                ));
        /**
         * 通过上述几行代码我们就能够写出一个简单的响应式编程的DEMO了。
         * 上述代码代表：
         * @Bean 声明的这个RouterFunction的bean，这个bean可以直接到达一个路由方法route()中，
         * 这个route()方法，可以通过获取请求地址"/hello", 给出一个响应ok()代表200，
         * 内容(.body)是 Mono.just("Hello World"), String.class 给出的内容，也就是Hello World这样的字符串。
         */
    }
}
