package cn.zhoudbw.util;

import org.jasypt.util.text.BasicTextEncryptor;

/**
 * @author zhoudbw
 * 加密的工具
 */
public class JasyptUtil {

    public static void main(String[] args) {
        BasicTextEncryptor encryptor = new BasicTextEncryptor();
        encryptor.setPassword("123456!@#");// 设置加密的盐
        String username = encryptor.encrypt("root");//加密用户名
        String password = encryptor.encrypt("123");// 加密密码
        System.out.println(username);
        System.out.println(password);
        /*
        第一次执行结果：
            P4DuC6Z3vuSN6dkAoSdrng==
            1wnPgueghxtcxya9Boa5KA==
        第二次执行结果：
            NhJkpWunKp5WCmeu+FiIbw==
            Bi4curAkr4/kXEQsStC1mA==
        * 便利之处：使用同样的盐，将相同的文字，转化为不同的密文。
         */
    }
}
