package cn.zhoudbw;

import cn.zhoudbw.config.PersonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author zhoudbw
 */
@SpringBootApplication
@EnableConfigurationProperties(PersonConfig.class)//这个注解，告诉主程序要主动引入配置，配置文件对应的类作为它的参数
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}

