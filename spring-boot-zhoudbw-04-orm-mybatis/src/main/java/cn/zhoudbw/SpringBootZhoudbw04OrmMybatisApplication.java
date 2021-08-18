package cn.zhoudbw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhoudw
 * <p>
 * 关于spring boot自动注入出现Consider defining a bean of type ‘xxx’ in your configuration问题解决方案
 * 使用springboot搭建项目的时候出现了springboot无法扫描到mapper文件的问题。
 * 经过查找，发现出现这个情况都基本是mapping.xml文件中的namespace路径出现问题，或者是没有指定扫描mapper文件的路径。
 * <p>
 * 解决办法：在启动类上添加@MapperScan("cn.zhoudbw.mapper")注解，指定扫描路径
 * <p>
 * * 有了MapperScan()就不需要为每个Mapper接口指定@Mapper了。
 */
@SpringBootApplication
//@tk.mybatis.spring.annotation.MapperScan(basePackages = "cn.zhoudbw.tk.mapper")
public class SpringBootZhoudbw04OrmMybatisApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootZhoudbw04OrmMybatisApplication.class, args);
    }
}
