<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>param</h1>

<%--
<form action="param/saveAccount" method="post">
    username：<input type="text" name="username"/> <br>
    password：<input type="password" name="password"/> <br>
    money：<input type="text" name="money"/> <br>
    用户姓名：<input type="text" name="user.uname"> <br>
    用户年龄：<input type="number" name="user.age"><br>
    <input type="submit" value="提交">
</form>
--%>

<%--
<form action="param/saveAccount" method="post">
    username：<input type="text"     name="username"/> <br>
    password：<input type="password" name="password"/> <br>
    money：<input type="text"        name="money"/> <br>
    用户姓名：<input type="text"      name="list[0].uname"> <br>
    用户年龄：<input type="number"    name="list[0].age"><br>

    用户姓名：<input type="text"   name="map['one'].uname"> <br>
    用户年龄：<input type="number" name="map['one'].age"><br>
    <input type="submit" value="提交">
</form>
--%>

<%--自定义类型转换器--%>
<%--
<form action="param/saveUser" method="post">
    用户姓名：<input type="text" name="uname"> <br>
    用户年龄：<input type="text" name="age"><br>
    出生日期：<input type="text" name="date"><br>
    <input type="submit" value="提交">
</form>
--%>

<a href="param/testServlet">测试ServletAPI原生</a>

</body>
</html>
