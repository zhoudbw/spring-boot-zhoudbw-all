package cn.zhoudbw.service;

import cn.zhoudbw.bean.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhoudbw
 * 将service写成接口，通过子类去实现，这里给出，需要提供的服务
 * @Service 声明是Service
 */

@Service
public interface EmployeeService {
    /**
     * 这个方法，是为了得到所有的员工信息的。
     * @return 返回一个存储了所有员工的列表
     */
    List<Employee> employList();

    /**
     * 这个方法，是为了增加一个员工的
     * @param employee 实例化的员工类，有数据
     */
    void add(Employee employee);

    /**
     * 这个方法，是为了根据employeeName的信息，修改原有employee的信息的。
     * 由于name和job都是readonly，所以修改的信息只有birthday和sex
     * 所以通过update.html传递过来的employee，还是可以通过名字去确定具体是哪一个的。
     * @param employee 和原先的employee名字和职位相同的employee
     */
    void update(Employee employee);

    /**
     * 通过employeeName找到employeeList中，对应的Employee
     * @param employName Employee的name
     * @return 返回名字和传递名字一致的Employee对象
     */
    Employee get(String employName);

    /**
     * 删除employeeList内，名字为name的对象
     * @param name
     */
    void delete(String name);
}
