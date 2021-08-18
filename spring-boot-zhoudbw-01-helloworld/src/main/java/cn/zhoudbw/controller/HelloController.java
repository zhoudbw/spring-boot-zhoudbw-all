package cn.zhoudbw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhoudbw
 * @Controller 声明当前类是一个控制类（业务逻辑、请求分发、组装响应）
 */
@Controller
public class HelloController {

    /**
     * @return 运行该方法，直接返回 字符串"hello world !"
     * 因为返回的是一个字符串，使用@Controller声明当前类之后，会去找视图，而不是直接返回字符串，
     * 所以添加注解 @ResponseBody 直接返回JSON字符串
     * @RequestMapping 指定方法和请求之间的映射关系。
     */
    @RequestMapping("/hello") //方法hello()和请求hello，产生映射关系，请求hello找到hello()方法
    @ResponseBody
    public String hello() {
        return "hello 呆呆 !";
    }
}
