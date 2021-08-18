package cn.zhoudbw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhoudbw
 * <p>
 * 这个DemoApplication是程序的入口，所以需要主方法main()
 * 通过SpringApplication类的run()方法，启动SpringBoot程序
 * 通过@SpringBootApplication 声明这个类是SpringBoot类的入口类
 */
@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        // 使用SpringApplication类的静态方法，启动SpringBoot程序
        // 方法参数：程序入口类 main函数的参数
        SpringApplication.run(DemoApplication.class, args);
    }

    /**
     * @SpringBootApplication is a convenience annotation that adds all of the following:
     *        @SpringBootApplication是一个方便的注释，它添加了以下所有内容:
     *
     * @Configuration: Tags the class as a source of bean definitions for the application context.
     *
     * @EnableAutoConfiguration: Tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings.
     * For example, if spring-webmvc is on the classpath, this annotation flags the application as a web application and activates key behaviors,
     * such as setting up a DispatcherServlet.
     *
     * @ComponentScan: Tells Spring to look for other components, configurations, and services in the com/example package, letting it find the controllers.
     *
     * The main() method uses Spring Boot’s SpringApplication.run() method to launch an application.
     * Did you notice that there was not a single line of XML?
     * There is no web.xml file, either.
     * This web application is 100% pure Java and you did not have to deal with configuring any plumbing or infrastructure.
     */
}
