<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FileUpload</title>
</head>
<body>
    <form action="./user/fileUploadServlet" method="post" enctype="multipart/form-data">
        <input type="file" name="upload"> <br>
        <input type="submit" value="上传">
    </form>

    <form action="./user/fileUploadSpringMVC" method="post" enctype="multipart/form-data">
        <input type="file" name="upload"> <br>
        <input type="submit" value="上传">
    </form>

    <form action="./user/fileUploadServer" method="post" enctype="multipart/form-data">
        <input type="file" name="upload"> <br>
        <input type="submit" value="上传">
    </form>
</body>
</html>
