package cn.zhoudbw.controller;

import cn.zhoudbw.model.Employee;
import cn.zhoudbw.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author zhoudbw
 * 业务控制类 返回视图
 * @RequestMapping("/xxx") 放在类上，代表类里面的方法都是以此开头的。
 */

@Controller
@RequestMapping("/employee") // 将RequestMapping提前到类上，表示该类下的所有请求方法都是以employee开头的
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping(method = RequestMethod.GET) // 指明该请求的HTTP方法，用于确定该请求的作用，不指定默认也是GET <=> @GetMapping
    public String employeeList(Model model) {
        List<Employee> employeeList = employeeService.employList();
        // 将结果返回给页面
        model.addAttribute("employeeList", employeeList);
        // 返回员工列表页
        return "employee-list";
    }
}
