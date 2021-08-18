package cn.zhoudbw.service;

import cn.zhoudbw.mapper.EmployeeMapper;
import cn.zhoudbw.model.Employee;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

///**
// * @author zhoudbw
// */
//@Service
//public class EmployeeServiceImpl implements EmployeeService {
//
//    private final EmployeeMapper employeeMapper;
//
//    @Autowired
//    public EmployeeServiceImpl(EmployeeMapper employeeMapper) {
//        this.employeeMapper = employeeMapper;
//    }
//
//    @Override
//    public List<Employee> employList() {
//        return employeeMapper.selectList(null);
//    }
//}

/**
 * 基于mybatis-plus的通用serviceImpl实现方式
 * mybatis-plus也提供了在具体的service实现类中实现的方式
 * 通过继承ServiceImpl<T, E> T: 需要调用的mapper类   E:需要操作的实体类
 * @author zhoudbw
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService{
    /**
     * 可直接调用方法不用写啦。
     */
}
