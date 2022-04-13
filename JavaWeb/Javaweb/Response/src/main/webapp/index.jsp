<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Index</title>
</head>
<body>
<h1>Hello World</h1>

<%--
${pageContext.request.contextPath} -- 代表当前项目
--%>
<form action="${pageContext.request.contextPath}/login" method="get">
    Username：<input type="text" name="username"/>
    Password：<input type="password" name="password">
    <input type="submit">
</form>
</body>
</html>