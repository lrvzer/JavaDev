<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <script src="js/jquery-3.6.0.min.js"></script>
    <script>
        $(function() {
            $("#btn").click(
                function() {
                    // alert("hello, btn");
                    // Ajax请求
                    $.ajax({
                        url: "user/testAjax",
                        type: "post",
                        contentType: "application/json;charset=UTF-8",
                        data: '{"username": "张三", "password": "qwe123", "age": "20"}',
                        dataType: 'json',
                        success: function (data) {
                            // data: 服务端响应的json的数据，可以进行解析
                            alert(data);
                        }
                    });
                }
            );
        });
    </script>
</head>
<body>
    <a href="user/testString">String</a><br>
    <a href="user/testVoid">Void</a><br>
    <a href="user/testModelAndView">ModelAndView</a><br>
    <a href="user/testForward">Forward</a><br>
    <a href="user/testRedirect">Redirect</a><br>

    <button id="btn">JQuery</button>
</body>
</html>
