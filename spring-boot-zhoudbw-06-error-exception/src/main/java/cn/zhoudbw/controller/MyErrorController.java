package cn.zhoudbw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhoudbw
 * 处理错误码对应的请求
 * 此处处理MyCustomizer.java中定义的/error404请求
 */
@Controller
public class MyErrorController {

    @RequestMapping("/error404")
    public String error404() {
        return "error404";
    }

    /**
     * 该controller存在异常，为了演示出现异常，然后被自定义的全局异常处理器MyExceptionHandler得知，
     * 然后返回处理该异常的页面。
     */
    @RequestMapping("/testerror")
    public String error() throws Exception {
        throw new Exception("测试异常...");
        /**
         * 这里会抛出异常，这个异常会被 MyExceptionHandler监听到。
         * 其实@ControllerAdvice是一个切面的注解，监听到了抛出的异常，然后会执行@ExceptionHandler()注解的方法。
         * 这个方法会创建ModelAndView，跳转到error这个页面来，对应的就是src/resources/templates/error.html
         */
    }
}
