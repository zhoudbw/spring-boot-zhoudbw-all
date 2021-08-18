package cn.zhoudbw.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author zhoudbw
 * 通过三个注解去将该配置类和配置文件构成映射关系
 * food.potato=土豆
 * food.eggplant=茄子
 * food.greenPepper=青椒
 *
 * @Configuration  声明这是一个配置类，在spring中可以替代xml文件，因为springboot说，绝对不要xml，所以我们用这个来声明的
 * @ConfigurationProperties(prefix = "food")  声明该类对应一个配置文件，并且声明前缀
 * @PropertySource("classpath:food.properties")   声明配置文件所在的地址，创建在了resource目录下面，所以使用classpath代表resource，跟文件名字
 *
 */

@Configuration
@ConfigurationProperties(prefix = "food")
@PropertySource("classpath:food.properties")
public class FoodConfig {

    private String potato;
    private String eggplant;
    private String greenPepper;

    public FoodConfig() {}

    public String getPotato() {
        return potato;
    }

    public void setPotato(String potato) {
        this.potato = potato;
    }

    public String getEggplant() {
        return eggplant;
    }

    public void setEggplant(String eggplant) {
        this.eggplant = eggplant;
    }

    public String getGreenPepper() {
        return greenPepper;
    }

    public void setGreenPepper(String greenPepper) {
        this.greenPepper = greenPepper;
    }
}
