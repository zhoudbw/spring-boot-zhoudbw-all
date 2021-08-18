package cn.zhoudbw.service;

import cn.zhoudbw.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhoudbw
 * 将service写成接口，通过子类去实现，这里给出，需要提供的服务
 * @Service 声明是Service
 * 使用JPA就不需要ＤＡＯ层了。
 */

@Service
public interface EmployeeService {
    /**
     * 这个方法，是为了得到所有的员工信息的。
     * @return 返回一个存储了所有员工的列表
     */
    List<Employee> employList();
}
