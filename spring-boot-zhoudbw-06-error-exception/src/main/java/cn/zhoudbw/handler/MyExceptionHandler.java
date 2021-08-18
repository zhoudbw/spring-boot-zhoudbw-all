package cn.zhoudbw.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author zhoudbw
 * 自定义的异常处理器
 * Spring3.2 给我们提供了一个注解，让我们可以获取全局任何地方出现的异常。 这个注解就是@ControllerAdvice
 * @ControllerAdvice 控制器通知，也就是我们在处理controller的时候抛出的任何异常都会被这个注解所标识的这个类接收到。
 * @ControllerAdvice 内嵌了 @Component 这个注解，所以这个类可以被Spring容器所接管了。
 */
@ControllerAdvice
public class MyExceptionHandler {

    /*
     * 处理什么类型的异常，通过 @ExceptionHandler("异常类型.class")指定
     * 比如处理所有异常的父类：@ExceptionHandler(Exception.class)
     * 该注解下的方法就是用来处理该异常的处理方法。
     */

    /**
     * 通过@ExceptionHandler()确定具体需要处理何种异常
     * 通过handler()方法处理该类型的异常
     * @param e 需要处理的异常类型
     * @return 返回MVC的模型和视图，控制返回内容
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView handler(Exception e) {
        ModelAndView mv = new ModelAndView();
        // 返回templates下的名称为error的视图
        mv.setViewName("/error");
        // 将异常信息显示在页面上
        mv.addObject("message", e.getMessage());
        return mv;
    }
}
