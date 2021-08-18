package cn.zhoudbw.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
 *
 * 添加了JPARepository之后，以下事情必须做：
 * @Entity 声明该类是JpaRepository可以使用的实体类
 * @Id 声明该属性是主键
 * @Column 声明该属性是列
 * 必须有无参的构造方法
 * 必须实现序列化接口 Serializable
 */
@Entity
public class Employee implements Serializable {

    public Employee() {}

    @Id     private Long id;
    @Column private String name;
    @Column private String job;
    @Column private String birthday;
    @Column private String sex;
}
