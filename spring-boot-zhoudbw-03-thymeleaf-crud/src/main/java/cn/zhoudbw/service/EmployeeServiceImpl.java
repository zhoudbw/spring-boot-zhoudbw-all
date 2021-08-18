package cn.zhoudbw.service;

import cn.zhoudbw.bean.Employee;
import cn.zhoudbw.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhoudbw
 * 实现了EmployeeService接口，重写其所有的方法，真正的做事情的类
 */

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDao;

    @Autowired
    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public List<Employee> employList() {
        // service 调用 dao去真正增删改查，通过dao拿到所有的人员列表
        return employeeDao.employeeList();
    }

    @Override
    public void add(Employee employee) {
        // 添加进employList，需要dao的支持
        employeeDao.add(employee);
    }

    @Override
    public void update(Employee employee) {
        // 用传递过来的employee替换原先的employee
        employeeDao.update(employee);
    }

    @Override
    public Employee get(String employName) {
        // 通过名字找Employee, 借助Dao
        return employeeDao.get(employName);
    }

    @Override
    public void delete(String name) {
        employeeDao.delete(name);
    }
}
