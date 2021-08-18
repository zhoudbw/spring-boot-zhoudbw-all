package cn.zhoudbw;

import cn.zhoudbw.bean.Employee;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class EmployeeValidTest {

    /**
     * 普通模式
     */
    public static void main0(String[] args) {
        // 通过创建默认的Validator工厂获取一个Validator
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        // 创建需要验证的bean, 故意将name给空串, 故意将job给空串
        Employee employee = new Employee("", "", "xxxx", "xxxxx");
        // 通过validate()方法验证bean，看是否符合注解
        // 方法声明：<T> Set<ConstraintViolation<T>> validate(T object, Class<?>... groups)
        // 所以返回值是 Set<ConstraintViolation<T>> ，泛型是验证的bean
        Set<ConstraintViolation<Employee>> violationSet =  validator.validate(employee);
        // 遍历验证结果
        for (ConstraintViolation violation : violationSet) {
            // 获取出问题的属性
            Path property = violation.getPropertyPath();
            // 获取问题信息
            String message = violation.getMessage();
            // 打印出错属性和出错信息
            System.out.println(property + ", " + message);
        }
    }
    /**
     * 快速失败
     */
    public static void main(String[] args) {
        // 通过validation获取默认的Provider从而获取配置，添加快速失败配置，通过该配置获取默认的Validator工厂，从而获取Validator
        Validator validatorFastFail = Validation.byDefaultProvider().configure()
                .addProperty("hibernate.validator.fail_fast", "true")
                .buildValidatorFactory().getValidator();
        Employee employee = new Employee("", "", "xxxx", "xxxxx");
        Set<ConstraintViolation<Employee>> violationFastFailSet =  validatorFastFail.validate(employee);
        for (ConstraintViolation violation : violationFastFailSet) {
            System.out.println(violation.getPropertyPath() + ", " + violation.getMessage());
        }
    }
}
