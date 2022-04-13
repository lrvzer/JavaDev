<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%-- 内置对象 --%>
<%
    pageContext.setAttribute("name1", "1"); /* 保存的数据只在一个页面有效 */
    request.setAttribute("name2", "2"); /* 保存的数据只在一个请求中有效，请求转发会携带这个数据 */
    session.setAttribute("name3", "3"); /* 保存的数据只在一个会话中有效，从打开浏览器到关闭浏览器 */
    application.setAttribute("name4", "4"); /* 保存的数据只在服务器中有效，从打开服务器到关闭服务器 */
%>

<%
    /* 从pageContext取出，通过寻找的方式来 */
    /* 从底层到高层（作用域） */
    String name1 = (String)pageContext.findAttribute("name1");
    String name2 = (String)pageContext.findAttribute("name2");
    String name3 = (String)pageContext.findAttribute("name3");
    String name4 = (String)pageContext.findAttribute("name4");
    String name5 = (String)pageContext.findAttribute("name5"); /* 不存在 */
%>

<%-- 使用EL表达式输出 ${} --%>
<h1>${name1}</h1>
<h1>${name2}</h1>
<h1>${name3}</h1>
<h1>${name4}</h1>
<h1>${name5}</h1>
<h1><%=name5%></h1>
</body>
</html>
