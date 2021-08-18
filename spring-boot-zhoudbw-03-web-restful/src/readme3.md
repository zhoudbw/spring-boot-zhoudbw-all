## RESTful接口详解

REST（REpresentational State Transfer 表现层状态转化）是WEB服务的一种架构风格，使用HTTP、URI、XML、JSON、HTML等广泛流行的标准和协议，轻量级、跨平台、跨语言的架构设计。它是一种设计风格，不是一种标准，是一种思想。

### 为什么会出现Restful

#### 什么是RSETful

RESTful Web Service是一种常见的REST的应用，是一种遵守了REST风格的Web服务;REST式的Web服务是一种ROA(The Resource Oriented Architecture 面向资源的架构).

#### 传统方式CURD操作

| URL                                            | 功能 | HTTP方法  |
| :--------------------------------------------- | :--: | :-------: |
| `http://127.0.0.1/item/queryItem.action?id=1`  | 查询 |    GET    |
| `http://127.0.0.1/item/saveItem.action`        | 新增 |   POST    |
| `http://127.0.0.1/item/updateItem.action`      | 更新 |   POST    |
| `http://127.0.0.1/item/deleteItem.action?id=1` | 删除 | GET或POST |

上述接口和我们上面的ＤＥＭＯ中的设计是一样的。我们会在接口本身增加一个描述功能关键字，如query、save、update、delete用来描述该接口对应的操作是什么，这是在接口本身进行定义的。如：`http://127.0.0.1/item/queryItem.action?id=1`，save表明该接口使用来查询item的，如果需要查询具体的，传递参数id，这里使用的GET方法。

#### RESTful的CURD操作

| URL                       | 功能 | HTTP方法 |
| :------------------------ | :--: | :------: |
| `http://127.0.0.1/item/1` | 查询 |   GET    |
| `http://127.0.0.1/item`   | 新增 |   POST   |
| `http://127.0.0.1/item`   | 更新 |   PUT    |
| `http://127.0.0.1/item/1` | 删除 |  DELETE  |

RESTful是将item当成资源本身来看待，接口代表的操作并不体现在接口上，而是体现在HTTP方法上。如：`http://127.0.0.1/item/1 GET`用GET代表是查询操作。因为GET本身的含义就是，不会有任何操作，是获取的行为，是读操作。获取那个item呢？没有id，而是直接传递1。大大的简化了我们的接口设计。

我们说，我们将item看做是资源看待，那么我们直观的理解一下。比如说，我们现在有一个集合List<Object>，我们的item代表的就是Object。`/item/1 GET`表示我们想要获取的是item.id=1的item。`/item POST`表示新增item进入集合。`/item PUT`根据新传递的item锁定旧的item，然后用传递的item替换。`item/1 DELETE`表示删除item.id=1的item。
`注意：item.id中的id代指的是能够唯一确定item的标识。`

将原先CRUD的行为放到接口中定义转化为放到HTTP方法中去定义，让方法承担更多的含义之后，接口本身就可以简化了。因为本质就是对item这个资源进行增删改查的。如果要定位到某一个具体的资源上，将能够定义资源的唯一标识（可以是id、name等等）传递进来。这样我们可以看到接口本身就成为了一堆名词的堆砌，动词都放到了HTTP方法里。。

传统的操作其实是没有问题的，但是又存在一定的问题 —— 每次请求的接口或者地址，都在做描述，例如查询的时候用了query，新增的时候用了save，其实完全没有这个必要。使用了get请求,就是查询。使用post请求，就是新增的请求。表达性很强，完全没有必要再去做描述。这就是为什么有了RESTful。

与传统方式比较，RESTful面向资源本身，具有自解释性。充分利用 HTTP 协议本身语义。取东西就要GET（GET就是安全的，不会修改服务资源），新增就要POST（POST就是不安全的），修改就要PUT(PUT就要幂等)， 删除就是DELETE(DELETE就要幂等)。

#### RESTful的设计要求

| HTTP方法 | 功能 | 幂等 | 安全 |
| :------: | :--: | :--: | :--: |
|   GET    | 查询 |  是  |  是  |
|   POST   | 新增 |  否  |  否  |
|   PUT    | 更新 |  是  |  否  |
|  DELETE  | 删除 |  是  |  否  |

幂等性：接口可重复调用，在调用方多次调用的情况下，接口最终得到的结果是一致的。
安全性：访问接口，不会使服务器端资源状态发生改变。

#### REST架构的主要原则

```undefined
1. 网络上的所有事物都被抽象为资源
2. 每个资源都有一个唯一的资源标识符（URI）
3. 同一个资源具有多种表现形式(xml,json等)
4. 对资源的各种操作不会改变资源标识符
5. 所有的操作都是无状态的
```

符合REST原则的架构方式即可称为RESTful。

### 表现层状态转化

```
"表现层状态转化"实际上是省略了主语，指的是"资源的表现层状态转化"。

任何事物，只要有被引用到的必要，它就是一个资源。资源可以是实体，也可以只是一个抽象概念 。资源通俗的说，就是我们要进行增删改查的东西。就比如说我们的"呆小甜公司员工"的DEMO，资源就是"员工"。
```

#### 表现层

**"表现层"**是指对资源展示出来的一种形式。比如说APP上的数据，可能以JSON形式返回，可能以XML的形式返回，可能以页面的形式返回，这都是"表现层"的表现形式。我们可以简单的立即为，"表现层"就是资源展示出来的一种形式。

其实，要让一个资源可以被识别，需要有个唯一标识，在Web中这个唯一标识就是URI(Uniform Resource Identifier)。URI既可以看成是资源的地址，也可以看成是资源的名称。我们把"资源"具体呈现出来的形式，叫做它的"表现层"（Representation）。 

#### 状态

资源的展现需要状态的转化。比如说，如果展现的数据要增加，那么就是该资源增加的转化；如果展现的数据要减少，那么就是该资源递减的转化，要更改就是某项的改变，这都是状态的转化。客户端只做单纯的展示。http本身是无状态的，所以服务端是要记录资源的这些变化的。

互联网通信协议HTTP协议，是一个无状态协议。这意味着，所有的状态都保存在服务器端。如果客户端想要操作服务器，必须通过某种手段，让服务器端发生"状态转化"（State Transfer），通俗说，客户端能够拿到服务端记录的状态转化，就可以准确的显示资源。

#### 表现层状态转化

我们以"呆小甜公司员工"的更新操作举例，原先"呆小甜"是"女"，突然有一天发现自己不是女的，是男的。现在想要将其性别更新为"男"。这个更新，实际上就进行了状态的转化。也就是资源的一些特性需要进行改变，这些改变都是基于该资源的表现层的（或者基于该资源的返回格式的），所以说**"表现层状态转化"**。

整个API的设计都是基于对"表现层状态转化"的理解上，进行设计的。状态转化的方式，就是使用了HTTP的方法。具体来说，就是HTTP协议里面，四个表示操作方式的动词：GET、POST、PUT、DELETE。它们分别对应四种基本操作。客户端通过HTTP方法可以获取资源的表述（包括数据和描述数据的元数据），如客户端通过Accept头请求一种特定格式的表述，服务端则通过Content-Type告诉客户端资源的表述形式。

### RESTful设计要点

**看Url就知道要什么；看http method就知道干什么；看http status code就知道结果如何**

```
客户端是否支持这些HTTP方法
    部分古老的客户端只支持GET和POST两种方法
    但部分框架支持通过隐藏参数_method=DELETE来传递真实的请求方法 

状态转移问题
    状态应该区分应用状态和资源状态，客户端负责维护应用状态，而服务端维护资源状态。
    客户端与服务端的交互必须是无状态的，并在每一次请求中包含处理该请求所需的一切信息。
    这种无状态通信原则，使得服务端和中介能够理解独立的请求和响应。

在URI里边带上版本号的问题
    如果我们把版本号理解成资源的不同表述形式的话，就应该只是用一个URL，并通过Accept头部来区分。


和旧方案比较
    统一接口，可缓存，支持分层系统（允许服务器和客户端之间的中间层（代理，网关等）代替服务器对客户端的请求进行回应）
```

## 呆小甜公司员工DEMO接口设计改进

### 接口设计

| 功能     | 传统API                                                      | RESTful API                      |
| -------- | ------------------------------------------------------------ | -------------------------------- |
| 查询     | /employee/list               参数无      <GET>               | /employee              <GET>     |
| 跳转添加 | /employee/toadd          参数无      <GET>                   | /employee/toadd  <GET>           |
| 添加     | /employee/add?employee={employee} 参数Employee对象       <POST> | /employee              <POST>    |
| 跳转编辑 | /employee/toupdate?nam=name 参数String字符串                     <GET> | /employee/toupdate/{name} <GET>  |
| 编辑     | /employee/update?employee={employee} 参数Employee对象 <POST> | /employee                  <PUT> |
| 删除     | /employee/delete?name={name} 参数String字符串                     <GET> | /employee/{name}   <DELETE>      |

查询、增加和修改，都是对传递的资源Employee自身的操作，所以不需要参数和额外的请求，只要能够用HTTP方法（分别为<GET>、<POST>、 <PUT>）表明其身份即可。

### 接口修改

首先，将`@RequestMapping()`放在类上面

```java
/**
 * @author zhoudbw
 * 业务控制类 返回视图
 * @RequestMapping("/xxx") 放在类上，代表类里面的方法都是以此开头的。
 */

@Controller
@RequestMapping("/employee") // 将RequestMapping提前到类上，表示该类下的所有请求方法都是以employee开头的
public class EmployeeController {...}
```

#### `/employee/list`

```java
* 将employ/list  <GET> 改为                         /employee <GET>
```

```java
¢ 修改EmployeeController中，HTTP方法名、请求名
  
/**
* 使用RESTful接口的方式来实现跳转到list页面
*
* @param model 传递数据给模板文件，用于填充其内需要的数据
* @return 放回列表展示页视图
* @RequestMapping(methode = "") , 指明该请求使用的HTTP请求
* 如果指定method = "RequestMethod.GET"，那么这一行的写法等价于@GetMapping
*/
@RequestMapping(method = RequestMethod.GET) // 指明该请求的HTTP方法，用于确定该请求的作用，不指定默认也是GET <=> @GetMapping
  public String employeeList(Model model) {
  List<Employee> employeeList = employeeService.employList();
  // 将结果返回给页面
  model.addAttribute("employeeList", employeeList);
  // 返回员工列表页
  return "employee-list";
}
```

#### `/employee/toadd`

```java
* 将employ/toadd <GET> 改为                         /employee/toadd <GET>
```

```java
¢ 修改EmployeeController中，HTTP方法名、请求名
/**
* 增加按钮的toadd请求，跳转到add.html
* GET方法，请求为toadd（/employee被提前了）
*/
@GetMapping("/toadd")
public String toAdd() {
  return "add";
}
```

#### `/employee/add`

```java
* 将employ/add?employee={employee} <POST> 改为      /employee <POST>
```

```java
¢ 1. 修改EmployeeController中，HTTP方法名、请求名
  /**
     * 处理add请求，跳转回 /employee/list请求
     * 接收到add.html发送的/employee POST请求，由于/employee已经提前了，可以映射到每个方法上, 所有不需要特别指明请求
     */
    @PostMapping
    public String add(Employee employee) {
        employeeService.add(employee);
        // 使用GET方法请求/employee，跳转到列表展示页
        return "redirect:/employee";
    }

¢ 2. 修改add.html，使其发送POST方式的/employee请求
  <!--使用POST发送请求，接口为/employee，表示新增一个Employee资源-->
  <form th:action="@{/employee}" method="post" class="form-horizontal">......</form>
```

#### `/employee/toupdate`

```java
* 将employ/toupdate?name={name} <GET> 改为          /employee/{name} <GET>
```

```java
¢ 1. 修改EmployeeController中，HTTP方法名、请求名
  /**
     * 原先传统的api是：/employee/toupdate?name=name，现在要改造成RESTful api的形式:/employee/toupdate/{name}
     * 那么参数怎么传递呢？
     * 通过在参数String name前添加注解@PathVariable("name")，来映射请求的参数和方法签名传递的参数
     * <p>
     * 使用path指定请求，使用method指定HTTP方法，发送/employee/toupdate请求，跳转到update.html
     *
     * @param model
     * @param name  用于从employeeList内寻找对应Employee对象的name
     * @return 返回update视图
     */
    @RequestMapping(path = "/toupdate/{name}", method = RequestMethod.GET)
    public String toUpdate(Model model, @PathVariable("name") String name) {
        // 通过employeeName找到一个具体的employee
        // 然后将这个employee传递给update视图，帮助进行默认值的初始化
        Employee employee = employeeService.get(name);
        model.addAttribute("employee", employee);
        // 视图update内的请求需要发送put请求。
        return "update";
    }

¢ 2. 修改employee-list.html中，update按钮的请求方式，使其发送GET方式的employee/toupdate请求
  <!--增加update按钮-->
  <td class="col-sm-2">
    <!-- 传统API -->
    <!-- <a th:href="@{/employee/toupdate(name=${employee.name})}" class="btn btn-info">update</a> -->
    <!-- 带有参数时，需要对API进行修改，请求和参数分开，并且请求名的前后 / 不可以少  -->
    <a th:href="@{/employee/toupdate/} + ${employee.name}" class="btn btn-info">update</a>
  </td>
```

#### `/employee/update`

```java
* 将employ/update?employee={employee} <POST> 改为  /employee/{name} <PUT>
```

```java
¢ 1. 修改EmployeeController中，HTTP方法名、请求名
  /**
     * 在RESTfulAPI中，修改方法对应PUT方法，所以设置PutMapping，
     * 又因为修改是对自身的修改，所以请求为/employee
     * 由于employee被提前了，所以PUTMapping不需要指定请求了
     *
     * @param employee 需要被修改的新资源信息
     * @return 跳转到GET方法的employee下。我们的GET方法下的employee方法是唯一的，所以定位必然是列表展示页
     */
    @PutMapping
    public String update(Employee employee) {
        // 修改employList内对应的employee对象
        employeeService.update(employee);
        return "redirect:employee";
    }

¢ 2. 修改update.html，使其发送PUT方式的/employee/update请求
  <!--form表单只支持get和post两种方式，当时现在我们我们想要发送PUT请求的/employee, 通过隐式的手段指定-->
  <form th:action="@{/employee}" th:object="${employee}" method="post" class="form-horizontal">
    <!--通过该input hidden，隐式的指明请求的HTTP方法-->
    <!--这个隐型参数的值put，会覆盖原有的方法请求名-->
    <input type="hidden" name="_method" value="put">
 ......
 </form>
```

#### `/employee/delete`

```java
* 将employ/delete?name={name} <GET> 改为           /employee/{name} <DELETE>
```

```java
¢ 1. 修改EmployeeController中，HTTP方法名、请求名
/**
     * 使用RESTfulAPI的方式修改传统API，
     * 即，将/employee/delete/name=name 修改为/employee/{name} <DELETE>
     *     关键在与， 如何将employee-list内，"delete" 对应的请求改为<DELETE>
     * @param name 所要删除的employee对象的名字
     * @return 跳转列表展示页
     */
    @DeleteMapping("/{name}")
    public String update(@PathVariable("name") String name) {
        // 删除employList内对应的name的employee对象
        employeeService.delete(name);
        return "redirect:/employee";
    }

¢ 2. 修改employee-list.html中的delete按钮，使其提交DELETE方式的/employee/{name}请求
  <!--增加delete按钮-->
  <td class="col-sm-2">
    <!-- 传统API -->
    <!-- <a th:href="@{/employee/delete(name=${employee.name})}" class="btn btn-info">delete</a> -->
    <!-- 为了将从将传统API改成RESTfulAPI，需要将a链接的GET请求，改成DELETE方法发送的请求 -->
    <!-- 借助button来实现，通过添加点击事件，来发送DELETE请求 -->
    <button th:attr="del_url=@{/employee/} + ${employee.name}" name="del_button">DELETE</button>
    <!--使用attr添加属性del_url-->
    <!--添加点击监听时间，来发送DELETE请求-->
    <!--// 我们需要引入JQuery相关的包。springboot给我们提供了一种静态资源的使用方式，webjars.-->
    <!--
      /**
      * 概念
      *    WebJars是将web前端资源（js，css等）打成jar包文件，然后借助Maven工具，
      *    以jar包形式对web前端资源进行统一依赖管理，
      *    保证这些Web资源版本唯一性。WebJars的jar包部署在Maven中央仓库上。
      *
      * 优势
      *    将静态资源版本化，更利于升级和维护。
      *    剥离静态资源，提高编译速度和打包效率。
      *    实现资源共享，有利于统一前端开发。
      *
      * 使用方式 引入依赖 - > 直接访问
      *     <link rel='stylesheet' href='webjars/bootstrap/3.3.6/css/bootstrap.min.css'>
      *                                  webjars/对应的项目名/对应的版本号/类型/文件名字
      *     <script type='text/javascript' src='webjars/bootstrap/3.3.6/js/bootstrap.min.js'></script >
      */
      -->
      <script type="text/javascript" th:src="@{/webjars/jquery/3.4.1/jquery.js}"></script><!--引入JQuery-->
      <script>
        //监听按钮点击，如果点击了，我们就将del_url的请求，以DELETE方法提交(借助表单)
        $(function () {
          // 找到del_button按钮，添加点击事件
          $("button[name='del_button']").click(function () {
            //整合<button>和<form>,想要实现的效果是，将我在<button>中设置的del_url属性，赋值给<form>的action属性
            //也就是<form action=del_url> 其中，del_url=@{/employee/} + ${employee.name}
            //这样提交这个表单，提交的就是以DELETE方式提交的del_url请求
            $("#del_form").prop("action", $(this).attr("del_url")).submit();
                            //找到del_form,设置其action为button的del_url属性,并提交
                            //this,这里指的就是<button>
            })
          })
      </script>
  </td>
```

