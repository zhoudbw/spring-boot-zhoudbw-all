package cn.zhoudbw.controller;

import cn.zhoudbw.bean.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhoudbw
 * 演示thymeleaf整合的controller类
 * 需要跳转到一个视图所以使用@Controller，而不是RestController
 * @Controller 视图层使用的注解
 * @RestController 返回JSON数据使用的注解
 */

@Controller
public class ThymeController {

    /**
     * 请求thyme，响应视图：thymeleaf文件夹下的名为index的模板文件
     * 该视图的数据，通过Model传递过去
     * @param model 传递模板文件中，所需的数据
     * @return 返回视图
     */
    @RequestMapping("/thyme")
    public String index(Model model) {
        model.addAttribute("name", "<h1 align='center'>thymeleaf</h1>");
        return "/thymeleaf/index";
    }


    /**
     * 变量表达式
     * 传递一个对象的数据信息给模板文件
     */
    @RequestMapping("/thyme2")
    public String index2(Model model) {
        Student student = new Student("呆小甜", "一年级", "女");
        model.addAttribute("student", student);
        return "/thymeleaf/index2";
    }

    /**
     * 链接url表达式
     */
    @RequestMapping("/thyme3")
    public String index3() {
        return "/thymeleaf/index3";
    }
    @RequestMapping("/welcome")
    public String welcome(Model model) {
        return "/thymeleaf/welcome";
    }

    /**
     * 消息表达式
     */
    @RequestMapping("/thyme4")
    public String index4() {
        return "/thymeleaf/index4";
    }

    /**
     * 片段表达式
     */
    @RequestMapping("/thyme5")
    public String index5() {
        return "/thymeleaf/index5";
    }

}
