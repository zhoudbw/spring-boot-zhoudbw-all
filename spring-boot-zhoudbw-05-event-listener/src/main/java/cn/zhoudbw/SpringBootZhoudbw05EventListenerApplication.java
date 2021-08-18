package cn.zhoudbw;

import cn.zhoudbw.listener.CustomListener1;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author zhoudw
 */
@SpringBootApplication
public class SpringBootZhoudbw05EventListenerApplication {

    public static void main(String[] args) {
        /**
         * 加载监听。
         * 首先我们需要清楚一件事情：run方法返回给我们的是ConfigurableApplicationContext容器的。
         * 我们可以直接在这个容器中加载，所以我们需要接收这个容器，然后操作这个容器加载。
         */
        ConfigurableApplicationContext context = SpringApplication.run(SpringBootZhoudbw05EventListenerApplication.class, args);
        // 添加监听器 => 这样就通过获取启动后的容器，加载了自定义监听器。
        context.addApplicationListener(new CustomListener1());
        /**
         * 怎么触发这个监听呢？ => controller
         * 在controller中注入ApplicationEventPublisher
         */
    }

}
