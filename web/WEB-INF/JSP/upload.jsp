<%--
  Created by IntelliJ IDEA.
  User: tzz
  Date: 2018/4/25
  Time: 13:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>upload</title>
</head>
<body>
<form action="/uploadServlet" enctype="multipart/form-data" method="post">
    上传文件1：<input type="file" name="file1"><br>
    上传文件1：<input type="file" name="file2"><br>

    <input type="reset" value="重置"><br>
    <input type="submit" value="上传">

</form>
</body>
</html>
