package cn.zhoudbw.tk.mapper;

import cn.zhoudbw.model.Employee;
import tk.mybatis.mapper.common.Mapper;

/**
 * @author zhoudbw
 * 通用mapper类似于jpa的设计，所以继承一个接口，调用就可以调用其中通用的增删改查方法。
 * 将该实现类写成接口，这样可以不自己实现，而直接可以调用通用的mapper方法
 * Mapper<T> 这里的T，泛型，需要传递的是 处理的实体类
 */
public interface TkEmployeeMapper extends Mapper<Employee> {
    /**
     * 直接调用通用增删改查方法。
     */
}
