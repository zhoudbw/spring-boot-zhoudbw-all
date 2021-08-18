package cn.zhoudbw.controller;

import cn.zhoudbw.event.CustomEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhoudbw
 * 通过controller触发监听器
 * @RestController 直接返回JSON
 */
@RestController
public class EventController {

    /**
     * 注入发布者，用来发布事件
     */
    @Autowired
    private ApplicationEventPublisher publisher;

    @RequestMapping("/event")
    public String event() {
        // 通过发布者发布事件
        publisher.publishEvent(new CustomEvent(this));
        return "success";
    }
}
