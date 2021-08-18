package cn.zhoudbw.groovy

/**
 * 使用@Grab声明对于Thymeleaf的引用
 *  groupId moduleName version
 */
@Grab("org.springframework.boot:spring-boot-starter-thymeleaf:2.1.8.RELEASE")
@Controller
class WelcomeCLI {
    @RequestMapping("/")
    String home(Model model) {
        model.addAttribute("name", "Welcome CLI")
        return "welcome"
    }
}
