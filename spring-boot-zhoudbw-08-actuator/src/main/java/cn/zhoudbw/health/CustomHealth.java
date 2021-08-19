package cn.zhoudbw.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * @author zhoudw
 * 自定义健康指标，最为/health结果汇总的一部分
 * 实现 HealthIndicator接口
 *  实现 health()方法
 * 自定义的健康指标需要被Spring识别到，使用@Component，声明是Spring组件
 */
@Component
public class CustomHealth implements HealthIndicator {
    /**
     * 通过health来自定义健康指标
     * @return 返回该指标的健康状态
     */
    @Override
    public Health health() {
        // errorCode==1 表示不健康， ==0 表示健康
        int errorCode = 1;
        // errorCode的值，可以对我们需要的指标进行check，然后赋值获取。
        if (errorCode != 0) {
            // 通过withDetail()方法设置细节信息，并返回不健康状态
            return Health.down().withDetail("ErrorCode", errorCode).build();
            /**
             * 设置能够看到health的具体细节
             * management.endpoint.health.show-details=always
             */
        }

        // 默认返回健康状态
        return Health.up().build();
    }
}
