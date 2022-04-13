<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <a href="anno/testRequestParam?name=张三">RequestParam</a>
    <form action="anno/testRequestBody" method="post">
        username：<input type="text" name="username"><br>
             age：<input type="text" name="age"><br>
        <input type="submit" value="提交">
    </form>

    <a href="anno/testPathVariable/19">RequestParam</a><br>
    <a href="anno/testRequestHeader">RequestHeader</a><br>
    <a href="anno/testCookieValue">CookieValue</a><br>

    <form action="anno/testModelAttribute" method="post">
        username：<input type="text" name="uname"><br>
        age：<input type="text" name="age"><br>
        <input type="submit" value="提交">
    </form>

    <a href="anno/setSessionAttributes">setSessionAttributes</a><br>
    <a href="anno/getSessionAttributes">getSessionAttributes</a><br>
    <a href="anno/delSessionAttributes">delSessionAttributes</a><br>
</body>
</html>
