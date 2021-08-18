package cn.zhoudbw.listener;

import cn.zhoudbw.event.CustomEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author zhoudbw
 * 实现ApplicationListener接口，泛型指定自定义事件的类型
 * 使用@Component声明这是一个需要加载的监听器
 */
@Component
public class CustomListener2 implements ApplicationListener<CustomEvent> {
    @Override
    public void onApplicationEvent(CustomEvent event) {
        event.printMessage("CustomListener2监听到了");
    }
}
