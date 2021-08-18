package cn.zhoudbw.groovy

/**
 * Groovy中不要求，类的名字和文件的名字一致，当然一样就更好了
 */
@RestController
class HelloCLI {
    /**
     * 在浏览器中显示Hello World!
     * 不需要return关键字
     */
    @RequestMapping("/")
    String home() {
        "Hello World!"
    }
}
