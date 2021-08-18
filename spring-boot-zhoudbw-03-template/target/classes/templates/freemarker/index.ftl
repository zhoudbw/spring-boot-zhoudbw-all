<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8"/>
    <title></title>
</head>
<body>

<#--从下述代码我们可以看出，freemarker可以通过${key}取值
可以通过${string ${key}} 大括号套大括号的形式拼接值
可以通过${string + key + string} 加号的形式拼接字符串
-->
Hi FreeMarker. 现在时间是 ${now} !<br/>
${"Hi FreeMarker. 现在时间是 ${now} !"}<br/>
${"Hi FreeMarker. 现在时间是 " + now+ " !"}<br/>

<#--我们知道，这个模板文件需要数据，那么怎么将数据传递过来的呢？
根据mvc的思想，我们知道数据来自模型层，
其实，这里的数据就是通过Model传递过来的。-->
</body>
</html>