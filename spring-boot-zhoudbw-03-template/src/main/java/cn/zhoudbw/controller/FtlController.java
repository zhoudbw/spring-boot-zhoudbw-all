package cn.zhoudbw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * @author zhoudbw
 * 演示freemarker整合的controller类
 * 需要跳转到一个视图所以使用@Controller，而不是RestController
 * @Controller 视图层使用的注解
 * @RestController 返回JSON数据使用的注解
 */

@Controller
public class FtlController {

    /**
     * 请求ftl，返回index视图，这个index是freemarker书写的
     * @param model Model就是数据层，是模板文件中需要的数据。
     * @return 返回名字为index的视图层文件
     */
    @RequestMapping("/ftl")
    public String index(Model model) {
        // 给模板文件传递数据
        model.addAttribute("now", new Date().toString());
        return "/freemarker/index";
    }
}
