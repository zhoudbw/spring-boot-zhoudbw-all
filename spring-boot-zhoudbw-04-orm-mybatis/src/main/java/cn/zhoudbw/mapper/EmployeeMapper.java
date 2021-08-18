package cn.zhoudbw.mapper;

import cn.zhoudbw.model.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author zhoudbw
 * 使用mybatis整合，就需要dao层的支持了
 * @Repository 声明该类是DAO层
 * <p>
 * 由于是mybatis的整合，所以dao层命名为mapper。实际上是一样的。
 */

//@Repository
//public class EmployeeMapper {
//
//    /**
//     * 查询全部
//     * @return 返回存有所有Employee的列表
//     */
//    public List<Employee> employeeList() {
//        return null;
//    }
//}

/**
 * @author zhoudbw
 * 现在这个方法整个就变化了，利用 @Mapper声明，变成接口，没有具体的方法实现
 *
 * @Mapper
 *  1. 在启动类中使用@MapperScan("mapper接口所在包全名")即可，不用一个一个的在Mapper接口中加@Mapper注解。
 *  2. @Mapper注解声明该接口是mybatis的mapper接口，能够自动的把加@Mapper注解的接口生成动态代理类。
 *  3. 让springboot认识你的mapper层，也可以在启动类上面加MapperScan("mapper层所在包的全名")
 *  4. 不用写Mapper映射文件（XML）
 */

@Mapper
public interface EmployeeMapper {

    /**
     * 查询全部
     * @Select 该注解是为了取代EmployeeMapper.xml中的<select></>标签的
     *   通过 @Mapper @Select 就可以不用写EmployeeMapper.xml了。
     * @return 返回存有所有Employee的列表
     */
    @Select("SELECT id, name, job, birthday, sex FROM employee")
    List<Employee> employeeList();
}
