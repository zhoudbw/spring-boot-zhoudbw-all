package cn.zhoudbw.controller;

import cn.zhoudbw.model.Employee;
import cn.zhoudbw.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhoudbw
 * 业务控制类 返回JSON
 */

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Employee> employeeList() {
        List<Employee> employeeList = employeeService.employList();
        return employeeList;
    }

    @GetMapping("/{id}")
    public Employee employee(@PathVariable(name = "id")int id) {
        Employee employee = employeeService.getEmployeeById(id);
        return employee;
    }

    /**
     * 更改员工信息
     */
    @GetMapping("/update")
    public Employee updateEmployee(Integer id, String name, String job, String birthday, String sex) {
        Employee employee = new Employee(id, name, job, birthday, sex);
        // 更新数据库
        employeeService.updateEmployee(employee);
        return employeeService.updateEmployee(employee);
    }

//    /**
//     * 删除员工信息
//     * 传id值，通过id来删除
//     */
//    @GetMapping("/delete/{id}")
//    public String deleteEmployee(@PathVariable(name = "id")int id) {
//        employeeService.deleteEmployee(id);
//        return "success";
//    }

    @SuppressWarnings("all")
    /**
     * 删除员工信息
     * 传id值，判断是否删除指定的员工，还是全部的员工
     * 如果id != 0 那么就删除对应id的员工
     * 如果id == 0 那么就删除所有的员工
     */
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable(name = "id")int id) {
        if (id != 0) {
            employeeService.deleteEmployee(id);
        } else {
            employeeService.deleteAllEmployee();
        }
        return "success";
    }
}
