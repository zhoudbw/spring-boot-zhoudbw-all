package cn.zhoudbw.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zhoudbw
 * 实体类，代表员工
 */

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Employee {

    private String name;
    private String job;
    private String birthday;
    private String sex;
}
