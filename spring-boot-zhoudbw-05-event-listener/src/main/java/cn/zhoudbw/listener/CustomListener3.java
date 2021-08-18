package cn.zhoudbw.listener;

import cn.zhoudbw.event.CustomEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author zhoudbw
 *
 * 自定义类不需要实现ApplicationListener接口，但是需要声明当前类是@Component组件，需要被加载
 * 在方法上标明@EventListener注解 声明这是一个监听组件，监听的事件是方法的参数。
 *
 * （主要通过EventListenerMethodProcessor扫描出所有带有此注解的方法,
 *  然后动态构造事件监听器，并将监听器托管到Spring应用上文中）
 */
@Component
public class CustomListener3 {

    @EventListener
    public void eventListener(CustomEvent event) {
        event.printMessage("CustomListener3监听到了");
    }
}
