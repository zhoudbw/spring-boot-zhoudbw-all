package cn.zhoudbw.repository;

import cn.zhoudbw.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author zhoudbw
 * 集成JPA提供给我们的JpaRepository接口，该接口存在泛型 <需要进行增删改查的类, 主键类型的类>
 * 利用接口来继承 这样就可以不用去实现接口方法了。这个接口内定义了很多增删改查的操作，直接调用方法就好了。
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    /**
     * 该接口可以直接调用，JpaRepository<T, ID>提供给我们的增改查方法
     * 虽然，我们说接口不能做事，但是这里只是多态的写法，真正做事情的是该接口的具体子类。
     */
}
