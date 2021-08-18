package cn.zhoudbw.groovy

/**
 * 使用@Grab声明对于Thymeleaf的引用
 * 使用@Grab声明对于JQuery的引用 丰富HTML页面
 *  groupId moduleName version
 *
 * * 都不需要import包
 */
@Grab("org.springframework.boot:spring-boot-starter-thymeleaf:2.1.8.RELEASE")
@Grab("org.webjars:jquery:3.4.1")
@Controller
class WelcomeCLI2 {
    @RequestMapping("/")
    String home(Model model) {
        model.addAttribute("name", "Welcome CLI")
        return "welcome2"
    }
}
