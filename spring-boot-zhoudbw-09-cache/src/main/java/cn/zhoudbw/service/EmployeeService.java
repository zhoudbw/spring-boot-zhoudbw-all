package cn.zhoudbw.service;

import cn.zhoudbw.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhoudbw
 * 将service写成接口，通过子类去实现，这里给出，需要提供的服务
 */

@Service
public interface EmployeeService {
    /**
     * 查询全部员工
     *
     * @return 返回员工列表
     */
    List<Employee> employList();

    /**
     * 通过id查找员工
     *
     * @param id 员工id
     * @return 返回员工对象
     */
    Employee getEmployeeById(int id);

    /**
     * 修改Employee
     * @param newEmployee 新的employee，也就是存储了修改信息的employee
     * @return 返回Employee
     * 注意，返回值必须是Employee这个对象。
     * 因为使用缓存时，会把方法的返回值放入缓存中存储。
     * 此时的返回值是特殊的，不再是原先我们做修改时返回的void or int or boolean 了。
     */
    Employee updateEmployee(Employee newEmployee);

    /**
     * 根据id删除员工
     * @param id 员工id 唯一标识
     */
    void deleteEmployee(int id);

    /**
     * 删除所有的员工
     */
    void deleteAllEmployee();
}
