<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%--JSP表达式
    作用：用来将程序输出，输出到客户端
    <%=变量或者表达式 %>
--%>
<%=new Date() %>

<hr>

<%-- 脚本片段--%>
<%
    int sum = 0;
    for (int i = 0; i < 100; i++) {
        sum += i;
    }
    out.println("<h1>Sum=" + sum + "</h1>");
%>

<%for (int i = 0; i < 5; i++) {%>
    <h1>hello world</h1>
<%}%>

<%!
    static {
        System.out.println("Loading Servlet");
    }

    public int globalValue = 0;

    public void test() {
        System.out.println("进入方法test");
    }
%>
</body>
</html>
