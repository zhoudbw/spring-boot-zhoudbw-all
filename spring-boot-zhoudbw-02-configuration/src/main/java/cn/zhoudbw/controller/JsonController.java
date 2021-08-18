package cn.zhoudbw.controller;

import cn.zhoudbw.bean.Food;
import cn.zhoudbw.bean.Person;
import cn.zhoudbw.config.FoodConfig;
import cn.zhoudbw.config.PersonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhodbw
 * 这个类，是用来控制返回的数据类型是ＪＳＯＮ的。
 * <p>
 * 如果我们像spring-boot-zhoudbw-01-helloworld中的HelloController一样，用@Controller声明该类是控制类
 * 那么我们使用@RequestMapping映射方法时，方法返回的不是一个字符串，而是一个视图，通过返回的这个字符串去找是否存在这样一个同名的视图
 * <p>
 * 这里，使用@RestController专门用来返回JSON字符串形式的响应结果，而不是视图（像，jsp，html...）
 * 因为@RestControllery源码如下：
 * @Target({ElementType.TYPE})
 * @Retention(RetentionPolicy.RUNTIME)
 * @Documented
 * @Controller
 * @ResponseBody
 * public @interface RestController {
 *     @AliasFor( annotation = Controller.class)
 *     String value() default "";
 * }
 * @RestController实际上就是@Controller和@ResponseBody的组合
 */

@RestController
public class JsonController {

    /**
     * 请求json，返回"Hello Json !"
     * @return  "Hello Json !"
     */
    @RequestMapping("/json")
    public String json() {
        return "Hello Json !";
    }

    /**
     * 只是返回一个单独的字符串，效果还不够明显
     * 我们来看返回一个对象，结果是什么样的？
     *      结果：{"name":"甜","birthday":"02-10","sex":"女","quality":"呆呆"}
     * @return 返回对象信息的json形式
     */
    @RequestMapping("/person")
    public Person person() {
        Person person = new Person("甜", "02-10", "女", "呆呆");
        return person;
    }


    /**
     * 获取application.properties中已经定义的常量
     * 通过 @Value("${key}") 为对应的属性赋值
     */
    @Value("${person.name}")     private String name;
    @Value("${person.birthday}") private String birthday;
    @Value("${person.sex}")      private String sex;
    @Value("${person.quality}")  private String quality;

    /**
     * 直接通过上面注入的值进行赋值
     * @return person信息的json表现形式
     * 展示结果：{"name":"甜","birthday":"02-10","sex":"女","quality":"呆呆"}
     */
    @RequestMapping("/value")
    public Person value() {
        Person person = new Person(name, birthday, sex, quality);
        return person;
    }


    /**
     * 为PersonConfig注入值，并使用PersonConfig初始化Person
     * 注意springboot注入的时候使用的是无参构造方法，所以PersonConfig内的无参构造器不能少。
     */
    @Autowired
    private PersonConfig personConfig;
    @RequestMapping("/bean")
    public Person bean() {
        Person person = new Person(personConfig.getName(),
                                    personConfig.getBirthday(),
                                    personConfig.getSex(),
                                    personConfig.getQuality());
        return person;
    }


    /**
     * 具有多个配置文件时，如何进行bean的绑定
     * 通过@Configuration @ConfigurationProperties(prefix = "xxx") @PropertiesSource("classpath:xxx.properties")
     *
     * 访问：localhost:8090/beans
     * 展示：{"potato":"土豆","eggplant":"茄子","greenPepper":"青椒"}
     */
    @Autowired
    private FoodConfig foodConfig;
    @RequestMapping("/beans")
    public Food beans() {
        return new Food(foodConfig.getPotato(),
                        foodConfig.getEggplant(),
                        foodConfig.getGreenPepper());
    }


    /**
     * 获取解密后的username 和 password
     */
    @Value("${info.username}")  private String username;
    @Value("${info.password}")  private String password;

    @RequestMapping("/jasypt")
    public String jasypt() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(username);
        stringBuffer.append("\t");
        stringBuffer.append(password);
        return stringBuffer.toString();
    }

}
