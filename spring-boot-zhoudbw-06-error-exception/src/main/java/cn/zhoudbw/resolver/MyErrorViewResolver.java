package cn.zhoudbw.resolver;

import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author zhoudbw
 * 自定义错误视图解析器
 * 实现 ErrorViewResolver接口， 重写resolverErrorView，解析错误视图
 *
 * 重要：必须声明@Component 才能说明这是一个组件，才能够被Spring识别（是一个bean），这样定义才会生效。
 */
@Component
public class MyErrorViewResolver implements ErrorViewResolver {
    /**
     * 该方法我们只处理404
     * @param request
     * @param status 状态码
     * @param model
     * @return 返回ModelAndView，是MVC的返回结果，用来显示视图
     */
    @Override
    public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model) {
        // 处理404  	NOT_FOUND(404, "Not Found")
        if (status.equals(HttpStatus.NOT_FOUND)) {
            // 创建ModelAndView
            ModelAndView modelAndView = new ModelAndView();
            // 通过setViewName("xxx")方法，设置返回页面
            // 我们将自定义的文件放在了templates下，因此直接传递路径下的文件名可以找到对饮名字的视图
            modelAndView.setViewName("/resolver404");
            return modelAndView;
        }
        return null;
    }
}
