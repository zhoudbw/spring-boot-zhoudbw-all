package cn.zhoudbw.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author zhoudbw
 * 处理db1的配置
 */

@Configuration // 声明该类是配置，如果未添加该注解，会导致@Bean之间相互调用出错
@MapperScan(basePackages = "cn.zhoudbw.mapper", sqlSessionFactoryRef = "sqlSessionFactory1")
// 该类需要声明对应的mapper文件的位置(配置后可以找到真正需要使用的mapper文件的位置了)，
// 和关联的SqlSessionFactory是谁(在这里就是我们底下的"sqlSessionFactory1")
public class DbOneConfig {
    /**
     * 由于我们在DBConfig.java中已经声明了 name="db1"的DataSource，
     * db1通过将spring.dataSource.db1下的配置注入其属性获得了DataSource
     * 所以我们可以直接通过名字来获取对象=> 通过name获取bean，使用 @Autowire 和 @Qualifier("name")
     */
    private final DataSource dataSource;

    @Autowired
    public DbOneConfig(@Qualifier("db1") DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 通过SqlSessionFactory的创建需要DataSource。
     * 通过注入DataSource，我们可以创建SqlSessionFactory。
     * 使用@Bean 将SqlSessionFactory的创建交给Spring。
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory1() throws Exception {
        // 创建SqlSessionFactoryBean
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        // 为SqlSessionFactoryBean设置配置数据源
        sqlSessionFactoryBean.setDataSource(dataSource);
        // getObject()返回的就是SqlSessionFactory
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * 再声明一个Bean，用来解决SqlSessionTemplate的创建
     */
    @Bean
    public SqlSessionTemplate sessionTemplate1() throws Exception {
        // 创建SqlSessionTemplate需要SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = sqlSessionFactory1();
        SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory);
        // 直接返回SqlSessionTemplate
        return sqlSessionTemplate;
    }

    /*
    先把DBConfig中配置的DataSource，通过name注入到当前的类的DataSource中；
    在把DataSource注入到SqlSessionFactory中，创建SqlSessionFactory中；
    在把SqlSessionFactory注入SqlSessionTemplate中，创建SqlSessionTemplate中。
    这样就可以通过Spring更好的使用Mybatis了。
     */
}
