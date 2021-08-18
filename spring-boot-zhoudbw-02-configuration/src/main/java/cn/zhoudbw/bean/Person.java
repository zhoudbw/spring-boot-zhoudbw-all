package cn.zhoudbw.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * @author zhodubw
 * 这个实体类，代表一个人
 * 这个类是为了JsonController服务的，实现在页面返回一个人的信息
 */

@Getter@Setter
public class Person {
    private String name;
    private String birthday;
    private String sex;
    private String quality;

    /**
     * 给构造方法，不给set方法了
     * @param name 姓名
     * @param birthday 生日
     * @param sex 性别
     * @param quality 特征
     */
    public Person(String name, String birthday, String sex, String quality) {
        this.name = name;
        this.birthday = birthday;
        this.sex = sex;
        this.quality = quality;
    }
}
