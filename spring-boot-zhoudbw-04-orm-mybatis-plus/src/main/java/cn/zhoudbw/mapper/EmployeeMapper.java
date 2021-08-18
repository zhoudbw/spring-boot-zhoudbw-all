package cn.zhoudbw.mapper;

import cn.zhoudbw.model.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhoudbw
 */
@Mapper // 需要传递操作的实体对象的类型
public interface EmployeeMapper extends BaseMapper<Employee> {
    /**
     * 此时这个mapper就可以使用
     */
}
