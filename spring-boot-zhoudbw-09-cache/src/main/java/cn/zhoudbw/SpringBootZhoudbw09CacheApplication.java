package cn.zhoudbw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author zhoudw
 * @EnableCache 启动缓存
 */
@SpringBootApplication
@EnableCaching
public class SpringBootZhoudbw09CacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootZhoudbw09CacheApplication.class, args);
    }

}
