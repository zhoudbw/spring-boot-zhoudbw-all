package cn.zhoudbw.config;

import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author zhoudbw
 * 通过监听WebServerInitializedEvent获取
 */
@Component
public class ListenerWebServerName {
    @EventListener(WebServerInitializedEvent.class)
    public void onWebServerReady(WebServerInitializedEvent event) {
        System.out.println("listener -- 当前容器的实现类是: " +
                event.getWebServer().getClass().getName());
    }
}
