package cn.zhoudbw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author zhoudbw
 */
@SpringBootApplication
@ServletComponentScan
public class SpringBootZhoudbw06ServletApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootZhoudbw06ServletApplication.class, args);
    }
}
