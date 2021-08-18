package cn.zhoudbw.bean;

/**
 * @author zhoudbw
 * 配合配置类来使用，充当数据的容器（数据接收）
 * 也许我们回想，直接返回FoodConfig 不就好了，分层的设计思想，让我们规范化
 */
public class Food {

    private String potato;
    private String eggplant;
    private String greenPepper;

    /**
     * 数据接收类的全参构造方法，方便赋值
     * @param potato 土豆
     * @param eggplant 茄子
     * @param greenPepper 青椒
     */
    public Food(String potato, String eggplant, String greenPepper) {
        this.potato = potato;
        this.eggplant = eggplant;
        this.greenPepper = greenPepper;
    }

    public String getPotato() {
        return potato;
    }

    public String getEggplant() {
        return eggplant;
    }

    public String getGreenPepper() {
        return greenPepper;
    }
}
