package cn.zhoudbw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhoudbw
 */
@SpringBootApplication
@MapperScan("cn.zhoudbw.mapper")
public class SpringBootZhoudbw04MybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootZhoudbw04MybatisPlusApplication.class, args);
    }
}
