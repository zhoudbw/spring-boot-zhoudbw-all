## MyBatis整合

### mybatis简介

MyBatis 是一款优秀的持久层框架，它支持定制化 SQL、存储过程以及高级映射。 

MyBatis 避免了几乎所有的 JDBC 代码和手动设置参数以及获取结果集。MyBatis 可以使用简单的XML或注解来配置和映射原生类型、接口和 Java 的 POJO（Plain Old Java Object s，普通老式 Java 对象）为数据库中的记录。 

#### **Mybatis与Hibernate比较** 

MyBatis可以进行更为细致的SQL优化，更为灵活，对复杂查询支持较好。有运行速度上的优势，并且开放了插件接口。但是数据库移植性不好，不同的数据库需要写不同SQL。 

Hibernate的DAO层开发比MyBatis简单，而且对象的维护和缓存要比MyBatis好，对增删改查的对象的维护方便。数据库移植性好。有更好的二级缓存机制，可以使用第三方缓存。但是多表关联的查询很复杂。 容易在项目中前期顺利，后期抓狂。

### mybatis整合

#### xml方式

**`1. 引入依赖pom.xml`**

```xml
<!--mybatis集成springboot的依赖，根据命名方式就可以看出来了-->
<!--mybatis-spring-boot-starter-->
<!--spring-boot-start-thymeleaf 就是springboot去集成thymeleaf-->
<!--谁在前面，就是说谁集成谁-->
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
```

**`2. 在配置文件中配置数据源application.yaml`**

```yaml
# 配置端口
server:
  port: 8090

# 数据库连接配置
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/springdemo?useSSL=false
    username: root
    password: zhoudbw_tian
    # 可以不配置，能够根据url默认识别
    driver-class-name: com.mysql.jdbc.Driver

# 使用mybatis集成springboot是需要的配置 _ XML的方式
mybatis:
  # 配置mybatis的xml配置文件的位置
  # mybatis.cn.zhoudbw.config-location=classpath:mybatis-cn.zhoudbw.config.xml
  cn.zhoudbw.config-location: classpath:mybatis-cn.zhoudbw.config.xml # 这么写，需要将配置文件放在src/resources目录下
```

**`3. 编写mybatis的配置文件mybatis-cn.zhoudbw.config.xml`**

```xml
<?xml version="1.0" encoding="UTF-8" ?>

<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-cn.zhoudbw.config.dtd">

<configuration>
    <!--实体类的位置-->
    <typeAliases>
        <package name="cn.zhoudbw.model"/>
    </typeAliases>

    <!--实体类对应的mapper的位置-->
    <mappers>
        <!--这种路径写法需要在src/resource目录下创建相应的文件-->
        <mapper resource="mybatis/mapper/EmployeeMapper.xml" />
    </mappers>
</configuration>
```

**`4. 编写实体类（model）Employee.java并配置对应的xml文件EmployeeMapper.xml`**

```java
package cn.zhoudbw.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author zhoudbw
 * 实体类，代表员工
 */

@Getter
@Setter
@AllArgsConstructor
@ToString

/**
 * @author zhoudbw
 */
public class Employee implements Serializable {
    private Long id;
    private String name;
    private String job;
    private String birthday;
    private String sex;
}
```

```xml
<?xml version="1.0" encoding="UTF-8" ?>

<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<!--该文件本质就是ＳＱＬ的执行文件-->

<!--namespace指定哪个实体类找到这个xml文件-->
<mapper namespace="cn.zhoudbw.mapper.EmployeeMapper">

    <!--id对应SQL语句对应的mapper中的方法-->
    <!--resultType指定该方法执行之后的返回结果类型-->
    <!--当调用mapper中的方法是，会根据这个mapper文件，映射方法，从而执行得到最终的结果-->
    <!--也就是这个mapper文件会映射到cn.zhoudbw.mapper.EmployeeMapper类-->
    <select id="employeeList" resultType="cn.zhoudbw.model.Employee">
        <!--该方法需要的SQL-->
        SELECT id, name, job, birthday, sex FROM employee
    </select>
</mapper>
```

**`5. 在service中使用mapper文件EmployeeService.java和EmployeeServiceImpl`**

```java
package cn.zhoudbw.service;

import cn.zhoudbw.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhoudbw
 * 将service写成接口，通过子类去实现，这里给出，需要提供的服务
 */

@Service
public interface EmployeeService {
    /**
     * 这个方法，是为了得到所有的员工信息的。
     * @return 返回一个存储了所有员工的列表
     */
    List<Employee> employList();
}
```

```java
package cn.zhoudbw.service;


import cn.zhoudbw.mapper.EmployeeMapper;
import cn.zhoudbw.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhoudbw
 * 实现了EmployeeService接口，重写其所有的方法，真正的做事情的类
 */

@Service
public class EmployeeServiceImpl implements EmployeeService {
    /**
     * 将mapper注入，作为service属性，调用和数据库交互
     */
    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }

    @Override
    public List<Employee> employList() {
        return employeeMapper.employeeList();
    }
}
```

**`controller和视图即EmployeeController和employee-list.html未做修改`**

**`6. 启动项目`**

```java
package cn.zhoudbw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhoudw
 *
 * 关于spring boot自动注入出现Consider defining a bean of type ‘xxx’ in your configuration问题解决方案
 * 使用springboot搭建项目的时候出现了springboot无法扫描到mapper文件的问题。
 * 经过查找，发现出现这个情况都基本是mapping.xml文件中的namespace路径出现问题，或者是没有指定扫描mapper文件的路径。
 *
 * 解决办法：在启动类上添加@MapperScan(“com.springboot_ssm.mapper”)注解，指定扫描路径
 */
@SpringBootApplication
@MapperScan("cn.zhoudbw.mapper")
public class SpringBootZhoudbw04OrmMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootZhoudbw04OrmMybatisApplication.class, args);
    }
}
```