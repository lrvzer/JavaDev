<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    ArrayList<String> people = new ArrayList<>();
    people.add(0, "111");
    people.add(1, "张三");
    people.add(2, "李四");
    people.add(3, "王五");
    people.add(4, "天气");
    request.setAttribute("list", people);
%>
<%--
    var         每次遍历出来的对象
    items       要遍历的对象
    begin       开始索引位置
    end         结束索引位置
    step        步长
--%>
<c:forEach var="people" items="${list}">
    <c:out value="${people}"/> <br>
</c:forEach>

<hr/>

<c:forEach begin="1" end="3" step="2" items="${list}" var="people">
    <c:out value="${people}"/>
</c:forEach>

</body>
</html>
