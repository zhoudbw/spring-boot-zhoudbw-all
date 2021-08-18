package cn.zhoudbw.restservice;


import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhoudbw
 */
@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    /**
     * This controller is concise and simple,             这个controller简洁而简单
     * but there is plenty going on under the hood.       但在引擎盖下还有很多事情在进行。
     * We break it down step by step.                     我们一步一步地分解它。
     *
     * The @GetMapping annotation ensures that                               @GetMapping注解保证这个
     * HTTP GET requests to /greeting are mapped to the greeting() method.   对/greeting的HTTP GET请求被映射到greeting()方法。
     *
     * There are companion annotations for other HTTP verbs (e.g. @PostMapping for POST).      其他HTTP动词也有相应的注解（例如：@PostMapping代表POST）。
     * There is also a @RequestMapping annotation that                                         还有一个@RequestMaping注解
     * they all derive from, and can serve as a synonym (e.g. @RequestMapping(method=GET)).    它们都是从这个注释派生出来的，并且可以作为同义词(例如@RequestMapping(method=GET))。
     *
     * @RequestParam binds the value of the query string parameter name into the name parameter of the greeting() method.  @RequestParam将查询字符串参数名称的值绑定到greeting()方法的name参数中。
     * If the name parameter is absent in the request, the defaultValue of World is used. 如果请求中没有为name传递，则使用defaultValue=World。
     * 对上面这句话，举个例子说明，1）传递name参数：localhost:8080/greeting?name=zhoudbw     2)没有传递name参数：localhost:8080/greeting，此时使用defaultValue，即：localhost:8080/greeting?name=World
     *
     * The implementation of the method body
     * creates and returns a new Greeting object with id and content attributes
     * based on the next value from the counter and formats the given name by using the greeting template.  基于计数器的下一个值，并使用欢迎语模板格式化给定的名称。
     * 对上面这段话进行解释，incrementAndGet()自增，也就是每次访问，都会返回一个之前加1的值，从而保证唯一。传递name参数，替换模板"Hello %s"中的%s
     *
     * A key difference between a traditional MVC controller and the RESTful web service controller shown earlier is the way that the HTTP response body is created.
     *          传统MVC控制器和前面介绍的RESTful web服务控制器之间的一个关键区别是创建HTTP响应体的方式。
     * Rather than relying on a view technology to perform server-side rendering of the greeting data to HTML, this RESTful web service controller populates and returns a Greeting object.
     *          这个RESTful web服务控制器填充并返回一个greeting对象，而不是依赖于视图技术来执行将欢迎数据呈现到HTML的服务器端
     * The object data will be written directly to the HTTP response as JSON.              对象数据将以JSON的形式直接写入HTTP响应。
     *
     * This code uses Spring @RestController annotation,
     * which marks the class as a controller where every method returns a domain object instead of a view.
     * It is shorthand for including both @Controller and @ResponseBody.
     *
     * The Greeting object must be converted to JSON.
     * Thanks to Spring’s HTTP message converter support, you need not do this conversion manually.
     * Because Jackson 2 is on the classpath, Spring’s MappingJackson2HttpMessageConverter is automatically chosen to convert the Greeting instance to JSON.
     * 说明：@ResponseBody的作用就是上面描述的那样。让controller找的不是视图，而是找返回JSON字符串
     */
}
