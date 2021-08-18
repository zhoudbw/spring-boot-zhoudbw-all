package cn.zhoudbw.config;


/**
 * @author zhoudbw
 * 这个类是为了绑定application.properties中的常量的
 * 主要问题就是，怎么说明，这个类就是我们配置文件对应的类？—— 通过@ConfigurationProperties声明 —— 声明这是一个配置文件对应的类
 * 注意，需要引入依赖：看看官方文档怎么说：
 * You can easily generate your own configuration metadata file
 * from items annotated with @ConfigurationProperties by using the spring-boot-configuration-processor jar.
 * The jar includes a Java annotation processor which is invoked as your project is compiled.
 * To use the processor, include a dependency on spring-boot-configuration-processor.
 */

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 在@ConfigurationProperties中指定prefix，代表我们从配置文件的哪个路径下去查找，也就是一级目录
 * 我们的配置文件是这样配置的：
 * person.name=甜${random.int}
 * person.birthday=02-10
 * person.sex=女
 * person.quality=呆呆${person.name}
 * 所以，一级目录就是 person
 * @author zhoudw
 */
@ConfigurationProperties(prefix = "person")
public class PersonConfig {

    private String name;
    private String birthday;
    private String sex;
    private String quality;

    /**
     * 只写有参数构造方法，不写无参构造方法造成如下问题：
     * Parameter 0 of constructor in cn.zhoudbw.cn.zhoudbw.config.PersonConfig required a bean of type 'java.lang.String' that could not be found.
     *
     * The injection point has the following annotations:
     * 	- @org.springframework.beans.factory.annotation.Autowired(required=true)
     *
     * 这个问题是因为在创建实体类时创建了一个多参的构造方法，但是springboot 自动注入的时候使用的是无参构造方法。
     * 解决办法：在实体类中添加无参构造方法。
     */
    public PersonConfig() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getSex() {
        return sex;
    }

    public String getQuality() {
        return quality;
    }
}
