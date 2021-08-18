package cn.zhoudbw.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author zhoudbw
 * 自定义事件
 * 1. 继承ApplicationEvent这个抽象类
 * 2. 需要给定默认的构造方法
 */
public class CustomEvent extends ApplicationEvent {

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public CustomEvent(Object source) {
        super(source);
    }

    /**
     * 事件 ——　打印
     */
    public void printMessage(String msg) {
        System.out.println("自定义事件，打印信息：" + msg);
    }
}
