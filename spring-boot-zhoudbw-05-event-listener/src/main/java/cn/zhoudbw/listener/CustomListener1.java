package cn.zhoudbw.listener;

import cn.zhoudbw.event.CustomEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author zhoudbw
 * 自定义监听器
 * 1. 实现ApplicationListener接口，传递 事件 的类型
 * 2. 实现onApplicationEvent()方法
 */
public class CustomListener1 implements ApplicationListener<CustomEvent> {
    @Override
    public void onApplicationEvent(CustomEvent event) {
        event.printMessage("CustomListener1监听到了");
    }
}
