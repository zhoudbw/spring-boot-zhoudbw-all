## Mybatis整合之Mybatis-Plus工具包

与tk.mybatis插件不同，Mybatis-Plus是一个Mybatis的增强工具包，在Mybatis的基础上只做增强不做改变。简化了CRUD操作，提供了代码生成器,强大的条件构造器，同时内置了多个实用插件：标配的分页插件、性能分析插件、全局拦截插件等。

Mybatis-Plus同时也提供了一种通用mapper的解决方案。刚开始的通用mapper和tk.mybatis有异曲同工之妙，甚至和mybatis本身集成的更好。但是不支持分布式事务，对于多数据源的支持也不是很良好。新版本的mybatis-plus和springboot集成的也不是很好。

`1. 引入依赖`

```xml
<!-- mybatis集成springboot启动器-->
<dependency>
  <groupId>org.mybatis.spring.boot</groupId>
  <artifactId>mybatis-spring-boot-starter</artifactId>
  <version>2.2.0</version>
</dependency>
<!--MySQL数据库的JDBC依赖-->
<dependency>
  <groupId>mysql</groupId>
  <artifactId>mysql-connector-java</artifactId>
  <scope>runtime</scope>
  <version>5.1.49</version>
</dependency>

<!--mybatis-plus增强工具包的依赖-->
<dependency>
  <groupId>com.baomidou</groupId>
  <artifactId>mybatis-plus-boot-starter</artifactId>
  <version>3.4.3.1</version>
</dependency>
```

`2. 创建mapper类，并配置@MapperScan("需要扫描注解的mapper类")`

mapper的设计仿照了jpa的设计思想，直接继承一个通用的Mapper即可`BaseMapper`

```java
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper // 需要传递操作的实体对象的类型
public interface EmployeeMapper extends BaseMapper<Employee> {
    /**
     * 此时这个mapper就可以使用
     */
}
```

```java
@SpringBootApplication
@MapperScan("cn.zhoudbw.mapper")
public class SpringBootZhoudbw04MybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootZhoudbw04MybatisPlusApplication.class, args);
    }
}
```

```java
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    @Override
    public List<Employee> employList() {
        return employeeMapper.selectList(null);
    }
}
```

通过上述例子我们可以发现，mybatis-plus和tk.mybatis使用方法几乎一样，既然如此为什么我们还要特地去学习这个呢？

我们看，其实我们的service也相当于是样板代码：有个service接口，然后通过一个具体的类去实现接口中的方法。具体的ServiceImpl类调用mapper的方法。

实际上MyBatis-Plus提供给我们一种IService的通用service的机制。

现在我们想要实现service就只需要通过接口继承IService，通过具体的实现类继承ServiceImpl就可以了。

```java
import com.baomidou.mybatisplus.extension.service.IService;

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
```

```java
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 基于mybatis-plus的通用serviceImpl实现方式
 * mybatis-plus也提供了在具体的service实现类中实现的方式
 * 通过继承ServiceImpl<T, E> T: 需要调用的mapper类   E:需要操作的实体类
 * @author zhoudbw
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService{
    /**
     * 可直接调用方法不用写啦。
     */
}

```

然后就可以在controller中直接调用service层的方法了。

通过上述代码，我们演示了通用的Mapper的实现方案，和通用的Service的实现方案。我们需要做的就是 extends 或 implements 某个类。具体的功能实现代码，别人已经帮助我们实现完毕了。

除此以外，正如我们上面说的，该增强包还为我们提供了自动的主键生成、代码生成、分页插件等功能。

文档：`https//mp.baomidou.com/guide/`

对于简单的开发任务使用mybatis-plus确实是减少生产的利器。

---

到这里我们知道了tk.mybatis、mybatis-plus这样的插件利器，那么它们的意义在于什么呢？并不在于以后是否使用，而在于明白业务或者技术发展的趋势是什么？技术发展的趋势就是越来越为业务服务。让我们用最简略的代码，最省事的方式，快捷的去实现业务。
