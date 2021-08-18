package cn.zhoudbw.interceptor;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhoudbw
 * 自定义拦截器
 *  实现HandlerInterceptor
 *  该接口有三个方法，请求之前、处理之中、处理之后
 *  default修饰，但是我们对其进行重写
 *
 *  拦截器，我们说是交给Spring管理的，那么需要是Spring中的一个组件
 *  我们将其看做是一种服务，用@Service声明该组件
 */
@Service
public class CustomInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("...................CustomInterceptor preHandle...................");
        return true;
    }

    /**
     * 处理之后，我们看到这个方法既可以处理请求又可以处理响应，这也能看出来拦截器和过滤器的不同。
     * @param request  请求
     * @param response 响应
     * @param handler 处理器
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("...................CustomInterceptor postHandle...................");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("...................CustomInterceptor afterHandle...................");
    }
}
