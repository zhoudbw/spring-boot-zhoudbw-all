package cn.zhoudbw.controller;

import cn.zhoudbw.bean.Employee;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;


/**
 * @author zhoudbw
 * 业务控制类 返回JSON
 * @RequestMapping("/xxx") 放在类上，代表类里面的方法都是以此开头的。
 * @Api() 注解该controller，给定描述信息，文档生成会对controller生成的描述信息
 *           换言之，也就是声明一系列接口的功能，放在controller之上
 * @ApiOperation() 注解在controller方法上，用来说明一个具体的方法，文档生成时会对该方法生成描述
 *          换言之，声明具体接口的功能，放在controller的方法之上
 */

@RestController
@RequestMapping("/employee")
@Api(tags = "员工相关接口", description = "对员工进行增删改查的接口")
public class EmployeeController {

    @ApiOperation("显示员工列表接口")
    @RequestMapping(method = RequestMethod.GET)
    public String employeeList() {
        return "employee-list";
    }


    @ApiIgnore
    @GetMapping("/toadd")
    public String toAdd() {
        return "add";
    }


    @ApiOperation("添加员工的接口")
    @PostMapping
    public String add(Employee employee) {
        return "add:employee";
    }


    /**
     * @ApiIgnore 文档中不显示该接口
     */
    @ApiIgnore
    @RequestMapping(path = "/toupdate/{name}", method = RequestMethod.GET)
    public String toUpdate(@PathVariable("name") String name) {
        // 视图update内的请求需要发送put请求。
        return "update";
    }

    @ApiOperation("对员工信息进行修改接口")
    @PutMapping
    public String update(Employee employee) {
        return "update:employee";
    }

    /**
     * @ApiImplicitParam 含蓄的介绍一下name 属性
     */
    @ApiImplicitParam(name = "name",value = "姓名")
    @ApiOperation("删除员工信息接口")
    @DeleteMapping("/{name}")
    public String update(@PathVariable("name") String name) {
        return "delete:employee";
    }
}
