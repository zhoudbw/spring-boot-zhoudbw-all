package cn.zhoudbw.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhoudbw
 * 自定义端点
 * @Component 被Spring接收并处理，首先是一个组件，
 * @EndPoint(id = "端点名") SpringBoot提供，允许我们自定义端点. 声明是一个端点，通过id来命名
 */
@Component
@Endpoint(id = "custom")
public class CustomEndPoint {
    /**
     * 该方法将会是我们访问该端点时执行的。
     * 会显示该方法返回值的结果
     * 注意：端点返回的结果需是JSON类型，所以我们这里返回值定为Map
     *
     * @ReadOperation 代表我们访问该端点时，会找到该注解，从而找到对应的方法
     *                端点访问时的处理方法，通过@ReadOperation注解查找
     * @ResponseBody 返回JSON数据
     */
    @ReadOperation
    @ResponseBody
    public Map<String, String> custom() {
        Map<String, String> result = new HashMap<>();
        result.put("name", "Hello custom endpoint!");
        return result;
    }
}
