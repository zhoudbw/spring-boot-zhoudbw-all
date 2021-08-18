package cn.zhoudbw.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

/**
 * @author zhoudbw
 * 对bean进行的校验
 */
@Getter
@Setter
@AllArgsConstructor
@ToString
public class Employee {

    /**
     * @NotBlank 不为空串
     * 可以指定校验失败时给出的信息通过message给出
     */
    @NotBlank
    private String name;
    @NotBlank(message = "必须有职务")
    private String job;
    private String birthday;
    /**
     * 如何使用配置文件中的信息，作为错误校验的提示信息呢，使用 {key}
     */
    @NotBlank(message = "{employee.sex.notblank}")
    private String sex;
}
