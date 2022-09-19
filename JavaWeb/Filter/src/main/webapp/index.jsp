<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>当前人数：<span><%=this.getServletConfig().getServletContext().getAttribute("OnlineCount") %></span></h2>
</body>
</html>
