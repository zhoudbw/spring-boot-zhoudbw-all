package cn.zhoudbw.controller;

import cn.zhoudbw.bean.Employee;
import cn.zhoudbw.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 使用构造方法上添加@Autowire的方式，将EmployeeService注入EmployeeController
     *
     * @param employeeService
     */
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    /**
     * 使用RESTful接口的方式来实现跳转到list页面
     *
     * @param model 传递数据给模板文件，用于填充其内需要的数据
     * @return 放回列表展示页视图
     * @RequestMapping(methode = "") , 指明该请求使用的HTTP请求
     * 如果指定method = "RequestMethod.GET"，那么这一行的写法等价于@GetMapping
     */
    @RequestMapping(method = RequestMethod.GET) // 指明该请求的HTTP方法，用于确定该请求的作用，不指定默认也是GET <=> @GetMapping
    public String employeeList(Model model) {
        List<Employee> employeeList = employeeService.employList();
        // 将结果返回给页面
        model.addAttribute("employeeList", employeeList);
        // 返回员工列表页
        return "employee-list";
    }

    /**
     * 增加按钮的toadd请求，跳转到add.html
     * GET方法，请求为toadd（/employee被提前了）
     */
    @GetMapping("/toadd")
    public String toAdd() {
        return "add";
    }

    /**
     * 处理add请求，跳转回 /employee/list请求
     * 接收到add.html发送的/employee POST请求，由于/employee已经提前了，可以映射到每个方法上, 所有不需要特别指明请求
     */
    @PostMapping
    public String add(Employee employee) {
        employeeService.add(employee);
        // 使用GET方法请求/employee，跳转到列表展示页
        return "redirect:/employee";
    }

    /**
     * 原先传统的api是：/employee/toupdate?name=name，现在要改造成RESTful api的形式:/employee/toupdate/{name}
     * 那么参数怎么传递呢？
     * 通过在参数String name前添加注解@PathVariable("name")，来映射请求的参数和方法签名传递的参数
     * <p>
     * 使用path指定请求，使用method指定HTTP方法，发送/employee/toupdate请求，跳转到update.html
     *
     * @param model
     * @param name  用于从employeeList内寻找对应Employee对象的name
     * @return 返回update视图
     */
    @RequestMapping(path = "/toupdate/{name}", method = RequestMethod.GET)
    public String toUpdate(Model model, @PathVariable("name") String name) {
        // 通过employeeName找到一个具体的employee
        // 然后将这个employee传递给update视图，帮助进行默认值的初始化
        Employee employee = employeeService.get(name);
        model.addAttribute("employee", employee);
        // 视图update内的请求需要发送put请求。
        return "update";
    }

    /**
     * 在RESTfulAPI中，修改方法对应PUT方法，所以设置PutMapping，
     * 又因为修改是对自身的修改，所以请求为/employee
     * 由于employee被提前了，所以PUTMapping不需要指定请求了
     *
     * @param employee 需要被修改的新资源信息
     * @return 跳转到GET方法的employee下。我们的GET方法下的employee方法是唯一的，所以定位必然是列表展示页
     */
    @PutMapping
    public String update(Employee employee) {
        // 修改employList内对应的employee对象
        employeeService.update(employee);
        return "redirect:employee";
    }

    /**
     * 使用RESTfulAPI的方式修改传统API，
     * 即，将/employee/delete/name=name 修改为/employee/{name} <DELETE>
     *     关键在与， 如何将employee-list内，"delete" 对应的请求改为<DELETE>
     * @param name 所要删除的employee对象的名字
     * @return 跳转列表展示页
     */
    @DeleteMapping("/{name}")
    public String update(@PathVariable("name") String name) {
        // 删除employList内对应的name的employee对象
        employeeService.delete(name);
        return "redirect:/employee";
    }
}
