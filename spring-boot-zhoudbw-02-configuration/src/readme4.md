## 配置敏感字段加密

在实际的应用场景中：

假如我们现在有这样一个数据库表，该数据库表存储了多个用户的信息，有用户名、密码。

| 用户名 | 密码 |
| :----: | :--: |
|        |      |

现在有一个黑客去攻击这个数据库，然后看到了这张表中的信息了，知道了用户名和密码就可以做坏事了，显然这样直接存储密码很不安全。通常密码都是加密之后存储的。

但是加密还有一个问题，用户A的密码123、用户B的密码123，好巧不巧这个黑客的密码也是123，然后他发现了一堆和他的密码加密后一样的密码。这样这些密码一样的用户的信息也就被获取了。因为密码设置过于简单了，加密之后，密码是一样的，所以这些用户的密码就被盗走了。怎么避免这种情况呢？ —— 加盐加密算法。

加盐，我们能想到什么？菜淡了，需要加盐。算法里的加盐，代表的是加密方式是变化的。也就是，即使用户Ａ和用户B的密码是一样的， 但是通过不同的盐来加密，加密后相同密码的加密结果是不一样的。 

Jasypt是一个Java库（就是加盐加密的库），可以使开发者不需太多操作，就可以给Java项目添加基本加密功能，而且不需要知道加密原理。 Jasypt也即Java Simplified Encryption。默认使用的是PBEWITHMD5ANDDES算法。 

### 使用方式

`1. 引入依赖`

```xml
<!--java加盐加密的库的依赖-->
<dependency>
  <groupId>com.github.ulisesbocchio</groupId>
  <artifactId>jasypt-spring-boot-starter</artifactId>
  <version>2.0.0</version>
</dependency>
```

`2 通过工具类：JasyptUtil对明文进行加密`

```java
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
```

`3. 选取一种加密后结果，在application中配置盐和密文`

```yaml
# 想要使用，必须在配置文件中设置盐，加密的盐和解密的盐一致，才能解密
jasypt:
  encryptor:
    password: 123456!@#
# 设置需要解密的用户名密文和密码密文
# 使用ENC()进行解密
info:
  username: ENC(NhJkpWunKp5WCmeu+FiIbw==)
  password: ENC(Bi4curAkr4/kXEQsStC1mA==)
```

`4. 添加/jasypt请求，方便访问拿到解密后的明文`

```java
/**
* 获取解密后的username 和 password
*/
@Value("${info.username}")  private String username;
@Value("${info.password}")  private String password;

@RequestMapping("/jasypt")
public String jasypt() {
  StringBuffer stringBuffer = new StringBuffer();
  stringBuffer.append(username);
  stringBuffer.append("\t");
  stringBuffer.append(password);
  return stringBuffer.toString();
}
```

`5. 启动springboot项目，访问验证。`