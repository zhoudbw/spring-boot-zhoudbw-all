# Mybatis拓展

在这样Mybatis的使用基础之上，还有什么更加便捷的使用方式呢？Mybatis的强大也在与其开源、包容，开发出了很多插件和增强包供我们使用。这里说明两个，插件`tk.mybatis`、增强包`mybatis-plus`。这两者在不同的场景各有其优缺点，但是这两者代表了一种代码简捷的思路，我们当前做web开发能做到的最简的一种思想。

## Mybatis整合之`tk.mybatis`插件

tk.mybatis是一种通用mapper的解决方案（tk.mybatis也称为通用mapper），参考了jpa的通用增删改查思想。在jpa中，我们只需要使用接口继承JpaRepository接口就可以使用其中的通用mapper了——也就是通用的单表的增删改查方法。tk.mybatis也是通过这种方式，通过继承一个Mapper，来调用通用的增删改查方法，这样就不需要我们自己来书写SQL了。

首页地址：`https://github.com/godlike110/tk-mybatis`

### 集成`tk.mybatis`

`1. 引入依赖`
`注意：引入该 starter 时，和 MyBatis 官方的 starter 没有冲突，但是官方的自动配置不会生效！`

```xml
<dependency>
  <groupId>tk.mybatis</groupId>
  <artifactId>mapper-spring-boot-starter</artifactId>
  <version>2.1.4</version>
</dependency>
```

`2. 在SpringBoot的启动类上或者在带有@Configuration的类，配置`
`@tk.mybatis.spring.annotation.MapperScan(basePackages = "扫描包")`

```java
@SpringBootApplication
@tk.mybatis.spring.annotation.MapperScan(basePackages = "cn.zhoudbw.tk.mapper")
public class SpringBootZhoudbw04OrmMybatisApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootZhoudbw04OrmMybatisApplication.class, args);
    }
}
```

`3. 创建通用mapper接口继承Mapper类，形如：extends Mapper<T>`

```java
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
```

`4. 在EmployeeService中注入该接口的，使用进行增删改查操作`

```java
import cn.zhoudbw.model.Employee;
import cn.zhoudbw.tk.mapper.TkEmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhoudbw
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    /**
     * 将tk.mybatis注入
     */
    private final TkEmployeeMapper tkEmployeeMapper;
    @Autowired
    public EmployeeServiceImpl(TkEmployeeMapper tkEmployeeMapper) {
        this.tkEmployeeMapper = tkEmployeeMapper;
    }

    @Override
    public List<Employee> employList() {
        return tkEmployeeMapper.selectAll();
    }
}
```

在进行单表操作的简单应用时，这种方式是很便捷的。