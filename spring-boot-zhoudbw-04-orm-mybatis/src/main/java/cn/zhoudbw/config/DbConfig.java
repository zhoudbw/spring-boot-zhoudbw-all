package cn.zhoudbw.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author zhoudbw
 * 将配置文件中的数据读取到该类中
 * 首先> 得是一个一个配置文件类，利用 @Configuration声明
 * 然后> 在类利用@Bean声明一个bean，该bean代表的就是DataSource，通过依赖注入直接赋值了
 * 然后> 利用ConfigurationProperties("prefix")，指定该数据源对应配置文件中的哪个前缀
 * 因为我们返回的DataSource和我们需要的DataSource是完全一致的，配置文件中配置的属性值和DataSource需要的属性值是一致的，
 * 所以我们可以直接返回。
 * 这样就是将prefix下的配置传递进来，然后去创建一个DataSource。
 */
@Configuration // 声明该类是对应一个配置文件
public class DbConfig {

    @Bean(name = "db1") // 将该方法创建的对象交给Spring管理，为了好识别给定名字
    @ConfigurationProperties("spring.datasource.db1") // 该对象对应的是该配置文件下，以该前缀开头的配置
    public DataSource dataSource1() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "db2")
    @ConfigurationProperties("spring.datasource.db2")
    public DataSource dataSource2() {
        return DataSourceBuilder.create().build();
    }
}
