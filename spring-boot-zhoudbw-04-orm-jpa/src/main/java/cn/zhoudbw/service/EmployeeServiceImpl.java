package cn.zhoudbw.service;


import cn.zhoudbw.model.Employee;
import cn.zhoudbw.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhoudbw
 * 实现了EmployeeService接口，重写其所有的方法，真正的做事情的类
 * <p>
 * 使用JpaRepository之前：
 * 必须将JpaRepository注入该service类
 * 然后就可以调用仓库中的增删改查方法啦。
 */

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> employList() {
        // 直接通过JpaRepository获取结果，不需要dao的支持了。
        return employeeRepository.findAll();
    }
}
