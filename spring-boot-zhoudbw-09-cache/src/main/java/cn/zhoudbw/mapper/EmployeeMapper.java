package cn.zhoudbw.mapper;

import cn.zhoudbw.model.Employee;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author zhoudbw
 * 通过mybatis 操作h2数据库
 */
@Mapper
public interface EmployeeMapper {


    /**
     * 查询全部员工
     *
     * @return 返回员工列表
     */
    @Select("SELECT id, name, job, birthday, sex FROM employee")
    List<Employee> employeeList();

    /**
     * 通过id查找员工
     *
     * @param id 员工id
     * @return 返回员工对象
     */
    @Select("select * from employee where id = #{id}")
    Employee getEmployeeById(int id);

    /**
     * 修改员工信息
     * @param newEmployee 这个传递的员工内的信息就是我们要修改数据库的信息
     * @return 此时可以返回int了，因为我们传递来的employee实际上我们需要修改的employee信息。
     */
    @Update("update employee set name=#{name}, job=#{job}, birthday=#{birthday}, sex=#{sex} where id=#{id}")
    int updateEmployee(Employee newEmployee);

    /**
     * 从数据库中根据id删除员工信息
     * @param id
     * @return
     */
    @Delete("delete from employee where id = #{id}")
    int deleteEmployee(int id);

    /**
     * 删除所有的员工
     * @return
     */
    @Delete("delete from employee")
    int deleteAllEmployee();
}
