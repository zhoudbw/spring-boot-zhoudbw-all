package cn.zhoudbw.dao;

import cn.zhoudbw.bean.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoudbw
 * 实际和数据库交互的类，这里我们使用固定初始化，不涉及数据库的逻辑。
 * @Repository 声明这是dao
 */

@Repository
public class EmployeeDao {

    /**
     * // 初始化List<Employee>
     */
    static List<Employee> employeeList = new ArrayList<>();

    static {
        employeeList.add(new Employee("呆小甜", "首席执行官", "02-10", "女"));
        employeeList.add(new Employee("棒小甜", "首席财务官", "02-10", "女"));
        employeeList.add(new Employee("聪小威", "首席技术官", "04-12", "男"));
    }

    /**
     * 获取所有员工的信息
     *
     * @return 返回员工信息列表
     */
    public List<Employee> employeeList() {
        return employeeList;
    }

    /**
     * 向employList插入一条信息
     *
     * @param employee 需要插入的员工
     */
    public void add(Employee employee) {
        employeeList.add(employee);
    }

    /**
     * 通过employeeName在employeeList内找到对应的Employee对象
     *
     * @param employName
     * @return
     */
    public Employee get(String employName) {
        for (Employee employee : employeeList()) {
            if (employee.getName().equals(employName)) {
                return employee;
            }
        }
        // 因为update按钮是和所要找的记录在同一行，也就是说，如果toupdate请求可以发送，
        // 那么一定可以在employeeList中，找到对应employeeName的Employee对象。
        // 之所以找不到时（虽然一定找得到）返回这个值，是为了防止什么误操作出现了空指针异常。（反着这条return不会走，给什么都可以，保险点）
        return new Employee("", "", "", "");
    }

    /**
     * 用传递过来的employee替换原先的employee
     *
     * @param employee 传递过来的employee
     */
    public void update(Employee employee) {
        // 通过名字在employeeList中找到对应名字的Employee
        Employee oldEmployee = get(employee.getName());
        // 通过Setter方法对不同的属性值进行设置
        if (employee.getBirthday() != null && employee.getBirthday() != "") {
            oldEmployee.setBirthday(employee.getBirthday());
        }
        if (employee.getSex() != null && employee.getSex() != "") {
            oldEmployee.setSex(employee.getSex());
        }
    }

    /**
     * 删除名字为name的employee对象
     * @param name
     */
    public void delete(String name) {
        employeeList.remove(this.get(name));
    }
}
