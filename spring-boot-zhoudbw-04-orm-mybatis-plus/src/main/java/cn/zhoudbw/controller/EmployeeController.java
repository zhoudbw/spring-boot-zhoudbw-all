package cn.zhoudbw.controller;

import cn.zhoudbw.model.Employee;
import cn.zhoudbw.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
        // 可以直接调用ServiceImpl提供给我们的通用方法
        List<Employee> employeeList = employeeService.list();
        model.addAttribute("employeeList", employeeList);
        // 返回员工列表页
        return "employee-list";
    }

    /**
     * get请求，直接返回一个add视图
     * @return
     */
    @GetMapping("/toadd") // 实际请求："/employee/toadd"
    public String toAdd() {
        return "/add";
    }
    /**
     * 测试事务
     * 添加对象是一个POST请求，发送该请求，
     * 根据输入框的name，自动装配Employee，传入该方法
     */
    @Transactional
    @PostMapping("")
    public String add(Employee employee) {
        // 将employee保存
        employeeService.save(employee);
        // 算数异常，如果不添加@Transactional，上面save()语句生效
        // 添加了@Transactional后，上面save()语句回滚回最初状态，不生效
        int res = 1/0;
        // 重定向，以get方式发送请求，表示查询，返回的是employee-list视图
        return "redirect:/employee";
    }
}
