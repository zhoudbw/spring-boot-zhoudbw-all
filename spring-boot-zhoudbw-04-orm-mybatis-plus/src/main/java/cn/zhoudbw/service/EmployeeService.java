package cn.zhoudbw.service;

import cn.zhoudbw.model.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;

///**
// * @author zhoudbw
// * 将service写成接口，通过子类去实现，这里给出，需要提供的服务
// */
//
//@Service
//public interface EmployeeService {
//    /**
//     * 这个方法，是为了得到所有的员工信息的。
//     * @return 返回一个存储了所有员工的列表
//     */
//    List<Employee> employList();
//}

/**
 * 通过Mybatis-plus实现的一种IService的通用service机制
 * IService<T> 泛型传递的也是数据库表对应的实体类
 * IService提供了一些业务常用的方法
 * 使用接口继承之后，传递泛型类，然后就可以调用使用了
 * @author zhoudbw
 */
public interface EmployeeService extends IService<Employee> {
    /**
     * 该类可以直接调用方法实现常见的业务场景
     */
}