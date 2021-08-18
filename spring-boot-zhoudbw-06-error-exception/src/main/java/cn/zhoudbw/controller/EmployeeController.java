package cn.zhoudbw.controller;

import cn.zhoudbw.bean.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author zhoudbw
 */
@RestController
public class EmployeeController {

    /**
     * 通过add一个新的员工的方式来验证数据格式
     * 使用RESTful风格，利用POST请求代表增加
     * 需要在controller中验证传递过来的Employee，那么怎么验证呢？
     *   提供给我们一个注解 @Valid ： 直接放在bean前面，校验其是否符合注解规则
     *  校验不通过时，返回400和错误信息。
     *  处理方式是，遍历全部属性，失败结果全部返回。
     */
    @PostMapping("/employee")
    public String add(@Valid Employee employee) {
        return "success";
    }

    /**
     * 自定义处理方式，通过 BindingResult实现
     */
    @PostMapping("/employee2")
    public String add2(@Valid Employee employee, BindingResult result) {
        // 通过result.getErrorCount()方法可以得到检验错误的数目
        if (result.getErrorCount() > 0) {
            // >0 表示有校验错误的条目
            // 拿到所有属性失败的失败结果
            List<FieldError> errorList = result.getFieldErrors();
            // 返回值
            StringBuffer stringBuffer = new StringBuffer();
            // 遍历
            for (FieldError fieldError : errorList) {
                // 获取出错属性本身
                String fieldString = fieldError.getField();
                // 获取错误信息
                String errorString = fieldError.getDefaultMessage();
                stringBuffer.append(fieldString);
                stringBuffer.append("\t");
                stringBuffer.append(errorString);
                stringBuffer.append("\n");
            }
            return  stringBuffer.toString();
        }
        return "success";
    }
}
