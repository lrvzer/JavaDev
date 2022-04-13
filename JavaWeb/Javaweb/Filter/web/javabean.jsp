<%--
  Created by IntelliJ IDEA.
  User: lrwei
  Date: 2021/11/12
  Time: 10:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<jsp:useBean id="people" class="per.study.pojo.People" scope="page">
    <jsp:setProperty name="people" property="id" value="1"/>
    <jsp:setProperty name="people" property="name" value="张三"/>
    <jsp:setProperty name="people" property="age" value="18"/>
    <jsp:setProperty name="people" property="address" value="张家港"/>
</jsp:useBean>

</body>
</html>
