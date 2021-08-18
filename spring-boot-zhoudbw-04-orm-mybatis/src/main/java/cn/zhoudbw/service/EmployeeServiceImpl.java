package cn.zhoudbw.service;


//import cn.zhoudbw.mapper.EmployeeMapper;
//import cn.zhoudbw.model.Employee;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
///**
// * @author zhoudbw
// * 实现了EmployeeService接口，重写其所有的方法，真正的做事情的类
// */
//
//@Service
//public class EmployeeServiceImpl implements EmployeeService {
//    /**
//     * 将mapper注入，作为service属性，调用和数据库交互
//     */
//    private final EmployeeMapper employeeMapper;
//
//    @Autowired
//    public EmployeeServiceImpl(EmployeeMapper employeeMapper) {
//        this.employeeMapper = employeeMapper;
//    }
//
//    @Override
//    public List<Employee> employList() {
//        return employeeMapper.employeeList();
//    }
//}

import cn.zhoudbw.mapper.EmployeeMapper;
import cn.zhoudbw.mapper2.EmployeeMapper2;
import cn.zhoudbw.model.Employee;
import cn.zhoudbw.tk.mapper.TkEmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhoudbw
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

//    /**
//     * 将tk.mybatis注入
//     */
//    private final TkEmployeeMapper tkEmployeeMapper;
//    @Autowired
//    public EmployeeServiceImpl(TkEmployeeMapper tkEmployeeMapper) {
//        this.tkEmployeeMapper = tkEmployeeMapper;
//    }
//
//    @Override
//    public List<Employee> employList() {
//        return tkEmployeeMapper.selectAll();
//    }


    /**
     * 测试多数据源的使用
     */
    @Autowired // springdemo库
    private EmployeeMapper mapper;
    @Autowired // bak_springdemo库
    private EmployeeMapper2 mapper2;

    @Override
    public List<Employee> employList() {
        return mapper.employeeList();
    }
}
