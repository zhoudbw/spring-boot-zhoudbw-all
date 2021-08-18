package cn.zhoudbw.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author zhoudbw
 * 实体类，代表员工
 * @ApiModel("描述") 声明实例
 * @APIModelProperty("描述") 声明实例属性，用于接口参数的描述显示
 */

@Getter
@Setter
@AllArgsConstructor

@ApiModel("员工")
public class Employee {

    @ApiModelProperty("名字")
    private String name;
    @ApiModelProperty("职业")
    private String job;
    @ApiModelProperty("生日")
    private String birthday;
    @ApiModelProperty("性别")
    private String sex;
}
