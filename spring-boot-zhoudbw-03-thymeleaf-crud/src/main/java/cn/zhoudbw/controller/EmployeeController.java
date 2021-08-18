package cn.zhoudbw.controller;

import cn.zhoudbw.bean.Employee;
import cn.zhoudbw.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author zhoudbw
 * 业务控制类 返回视图
 */

@Controller
public class EmployeeController {

    private final EmployeeService employeeService;

    /**
     * 使用构造方法上添加@Autowire的方式，将EmployeeService注入EmployeeController
     * @param employeeService
     */
    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * 发送 /employee/list请求，该请求，被controller接收到，通过service_dao_service，最终controller拿到数据，传递给前台。
     * 最终返回employ-list视图
     * @param model 传递employeeList数据给模板
     * @return 返回employ-list视图
     */
    @RequestMapping("/employee/list")
    public String employeeList(Model model) {
        // 员工列表页，需要人员信息数据 —— controller调用service获取数据
        List<Employee> employeeList = employeeService.employList();
        // 将结果返回给页面
        model.addAttribute("employeeList", employeeList);
        // 返回员工列表页
        return "employee-list";
    }

    /**
     * 增加按钮的toadd请求，跳转到add.html
     */
    @RequestMapping("/employee/toadd")
    public String toAdd() {
        return "add";
    }

    /**
     * 处理add请求，跳转回 /employee/list请求
     */
    @RequestMapping("/employee/add")
    public String add(Employee employee) {
        // 需要在employList中添加一条数据，所以需要service 和 dao的支持
        employeeService.add(employee);
        // 跳转回employee-list请求
        // 跳转到这个请求之后，controller接收到了，执行employList()方法
        // 所以我们可以看到我们新添加的信息也在展示列表中了。
        return "redirect:/employee/list";
    }


    /**
     * 处理修改按钮的toupdate请求，跳转到update.html
     * @param model 为了传递一个employee给update.html，帮助update.html进行默认值的赋值
     * @param name 通过这个名字来找到一个Employee对象，通过model传递给update.html
     * @return 返回update视图
     */
    @RequestMapping("/employee/toupdate")
    /**
     * 在这个留个难受的事情，这也说明了一点，对于底层实现原理了解是多么重要。
     * 对于这个方法签名，我最初写是public String toUpdate(Model model, String employName)
     * 看这个employName是不是很见名知意，但是不幸的是，问题就出在这个名字上面。
     * 我的employee-list中是这样定义的：
     *    <a th:href="@{/employee/toupdate(name=${employee.name})}" class="btn btn-info">update</a>
     * 打印输出的结果都是，employName==null。无语。
     * 后来，改成name之后，解决问题。 —— 请求携带参数，被controller接收到，那么controller怎么就知道对应的参数都是谁的呢？
     * 通过参数名和变量名的匹配。这样可以找到了，所以，参数名，和所需的变量名一定要相同。
     *
     * 唉，基础稀碎，底层的渣渣。
     */
    public String toUpdate(Model model, String name) {
        System.out.println("controller:" + "name:" + name);
        // 通过employeeName找到一个具体的employee
        // 然后将这个employee传递给update视图，帮助进行默认值的初始化
        Employee employee = employeeService.get(name);
        model.addAttribute("employee", employee);
        return "update";
    }

    /**
     * 处理update请求，跳转回 /employee/list请求
     * 参数Employee不是我们手动组装的，我们通过input填写好了之后，自动拼装成Employee对象
     * 这个Employee对象，以参数的形式传递到update()方法，update()通过这个对象的名字，在employeeList中找到对应的employee对象
     * 然后通过利用这个新传递的Employee对象的信息，去修改原有Employee
     * @param employee 新的employee对象
     * @return 跳转到 /employee/list请求
     */
    @RequestMapping("/employee/update")
    public String update(Employee employee) {
        // 修改employList内对应的employee对象
        employeeService.update(employee);
        return "redirect:/employee/list";
    }



    @RequestMapping("/employee/delete")
    public String update(String name) {
        // 删除employList内对应的name的employee对象
        employeeService.delete(name);
        return "redirect:/employee/list";
    }
}
