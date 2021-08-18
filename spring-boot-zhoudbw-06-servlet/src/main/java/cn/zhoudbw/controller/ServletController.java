package cn.zhoudbw.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhoudbw
 */
@RestController
public class ServletController {
    @RequestMapping("/servlet")
    public String servlet() {
        System.out.println("ServletController servlet..................");
        return "hello servlet";
    }
}
