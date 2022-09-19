<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<c:set var="score" value="85"/>
<c:choose>
    <c:when test="${score >= 90}">优秀</c:when>
    <c:when test="${score >= 80}">优良</c:when>
    <c:when test="${score >= 70}">一般</c:when>
    <c:when test="${score >= 60}">及格</c:when>
    <c:when test="${score < 60}">不及格</c:when>
</c:choose>

</body>
</html>
