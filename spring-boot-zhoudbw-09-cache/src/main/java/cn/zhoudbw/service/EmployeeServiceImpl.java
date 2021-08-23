package cn.zhoudbw.service;


import cn.zhoudbw.mapper.EmployeeMapper;
import cn.zhoudbw.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author zhoudbw
 * @CacheConfig 设置通用属性值。 设置
 */
@CacheConfig(cacheNames = "employees")
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public List<Employee> employList() {
        return employeeMapper.employeeList();
    }


    /**
     * @Cacheable 作用：
     * 若缓存中不存在该资源，则通过方法查询，查询到之后存入缓存中，
     * 若缓存中存在该资源，则直接通过缓存查到资源。
     */
//    @Cacheable("employees")
//    @Override
//    public Employee getEmployeeById(int id) {
//        System.out.println("查询第" + id + "号员工");
//        return employeeMapper.getEmployeeById(id);
//    };
    @SuppressWarnings("all")
    /**
     *对@Cacheable中参数的使用演示
     * value ： 缓存的名字
     * key ：缓存的键
     * 这个key是作为键，存入cachename中，也就是value中的。
     * condition ： 存储缓存需要满足什么条件
     * unless 和condition相反 ：不满足给定条件是存储缓存
     */
//    @Cacheable(value = "employees", key = "#id", condition = "#id > 1")
    @Cacheable(/*value = "employees",*/ key = "#id", unless = "#id > 1")
    @Override
    public Employee getEmployeeById(int id) {
        System.out.println("查询第" + id + "号员工");
        return employeeMapper.getEmployeeById(id);
    }

    /**
     * 更改员工信息。
     *
     * @CachePut 将该方法的返回值放入缓存用于更新已经存在的缓存。
     * 缓存的名字，需要一致，要不然就是两个空间了，就不存在修改一说了。
     * 缓存的键，需要一致。保证在同一个空间了，需要保证能够对应上是同一个，这样才能够留一个。
     * 如果缓存中存在该员工的值，那么也要对应修改。
     */
    @CachePut(cacheNames = "employees", key = "#newEmployee.id")
    @Override
    public Employee updateEmployee(Employee newEmployee) {
        System.out.println("更新第" + newEmployee.getId() + "号员工");
        // 更新数据库
        employeeMapper.updateEmployee(newEmployee);
        // 返回已有的修改员工
        return newEmployee;
    }

    /**
     * 删除删除员工，同时删除缓存中的数据
     *
     * @param id 员工id 唯一标识
     * @CacheEvict evict驱逐，从缓存中删除该记录
     */
    @CacheEvict(/*cacheNames = "employees",*/ key = "#id")
    @Override
    public void deleteEmployee(int id) {
        System.out.println("删除第" + id + "号员工");
        employeeMapper.deleteEmployee(id);
    }

    /**
     * 删除所有的员工
     * 同时删除缓存中的所有员工记录
     * @CacheEvict 属性allEntries 设置为true，表示删除所有的。
     */
    @CacheEvict(/*cacheNames = "employees", */allEntries = true)
    @Override
    public void deleteAllEmployee() {
        System.out.println("删除所有员工");
        employeeMapper.deleteAllEmployee();
    }


}
