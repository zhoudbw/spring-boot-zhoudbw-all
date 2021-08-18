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
