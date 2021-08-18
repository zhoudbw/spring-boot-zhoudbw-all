package cn.zhoudbw.mapper2;

import cn.zhoudbw.model.Employee;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author zhoudbw
 * 演示多数据源配置的Mapper
 * 声明了@MapperScan() , 所以不写@Mapper也没关系
 */
public interface EmployeeMapper2 {
    /**
     * 查询全部
     * @return
     */
    @Select("SELECT id, name, job, birthday, sex FROM employee")
    List<Employee> employeeList();
}
