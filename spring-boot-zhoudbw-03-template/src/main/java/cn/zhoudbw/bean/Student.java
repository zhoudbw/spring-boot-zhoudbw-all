package cn.zhoudbw.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhoudbw
 * 使用Thymeleaf返回这个对象
 */

@AllArgsConstructor
@Getter //getter方法必须得写，因为Thymeleaf，获取属性值就是利用getter方法获取的，如果不写，会出现问题。
@Setter
public class Student {

    private String name;
    private String grade;
    private String sex;
}
