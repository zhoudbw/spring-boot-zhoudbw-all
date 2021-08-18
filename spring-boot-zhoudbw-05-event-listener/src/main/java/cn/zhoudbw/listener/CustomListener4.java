package cn.zhoudbw.listener;

import cn.zhoudbw.event.CustomEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author zhoudbw
 * 需要实现ApplicationListener接口，实现其中的方法
 *
 * 但是监听器加载方式不同，这里使用配置的方式进行加载：
 *      在application.properties中配置context.listener.classes（DelegatingApplicationListener，
 *      该类的作用是从application.properties中读取配置context.listener.classes，并将事件广播给这些配置的监听器）
 */
public class CustomListener4  implements ApplicationListener<CustomEvent> {
    @Override
    public void onApplicationEvent(CustomEvent event) {
        event.printMessage("CustomListener4监听到了");
    }
}
