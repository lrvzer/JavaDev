<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>登录</h1>
<div style="text-align:center">
    <form action="${pageContext.request.contextPath}/login" method="post">
        用户名：<input type="text" name="username"> <br>
        密码：<input type="password" name="password"> <br>
        爱好：
        <input type="checkbox" name="hobby" value="girl">女孩
        <input type="checkbox" name="hobby" value="code">代码
        <input type="checkbox" name="hobby" value="sing">唱歌
        <input type="checkbox" name="hobby" value="firm">电影
        <br>
        <input type="submit" value="提交">
    </form>

    <br>
    <br>

    <form action="${pageContext.request.contextPath}/upload" method="post" enctype="multipart/form-data">
        <input type="file" name="upload"> <br>
        <input type="submit" value="上传">
    </form>
</div>
</body>
</html>
