package cn.zhoudbw.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author zhoudbw
 * 将Swagger注入Spring
 * @Configuration 首先声明是配置文件类
 * @EnableSwagger2 开启swagger功能
 *          * 注意为了保险起见，最好将@EnableSwagger2添加到SpringBoot的启动类上
 *          * 因为放置在这里，不一定能够扫描到@EnableSwagger2
 *          * 如果扫描不到，那么就无法访问swagger
 *          * 所以最保险的作坊将@EnableSwagger2放置在启动类，这样必然可以扫描到
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * 开启swagger功能，需要如下的基础配置，我们通过bean来注入
     * Docket 摘要。需要声明一个摘要让swagger能显示出来。
     * @return 返回摘要。通过new创建，
     * 需要的参数：
     *    1. 文档的类型 - 使用swagger2 - 故：DocumentationType.SWAGGER_2
     *    2. 文档通过一系列选择器组成 主要有两个 apis 和 path => .select()选择器，通过这个选择器可以创建我们需要的api和path
     *    select()设置apis() 和 paths().
     *    3. .apis() 指定生成哪些controller的接口，因为我们几乎所有的接口都是在controller的 @RequestMapping()上声明的
     *    4. .paths() 在controller查找出来的接口中进行筛选
     *    RequestHandlerSelectors.any() 表示选择所有controller（没有进行任何筛选）
     *    PathSelectors.any() 表示选择所有接口 （没有进行任何筛选）
     *    5. .build() 创建出Swagger的文档摘要
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
//                .apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("cn.zhoudbw.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(myApiInfo());
                //　通过apiInfo()传递设置好的apiInfo()获取的ApiInfo对象到Docket内
    }

    /**
     * 自定义文档的介绍
     * @return ApiInfo对象
     */
    private ApiInfo myApiInfo() {
        // 需要通过ApiInfoBuilder来创建ApiInfo
        // 这个builder可以声明文档的title(标题)、description(描述信息)、version(版本信息)
        System.out.println("apiInfo()");
        ApiInfo apiInfo =  new ApiInfoBuilder()
                .title("spring-boot-zhoudbw-06-swagger")
                .description("这是一个简答的swagger使用DEMO")
                .version("1.0")
                .build(); // 创建出来
        return apiInfo;
    }
}
