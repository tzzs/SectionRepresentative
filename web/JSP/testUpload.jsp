<%--
  Created by IntelliJ IDEA.
  com.zt.sr.pojo.User: tzz
  Date: 2018/5/1
  Time: 19:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>testUpload</title>
</head>
<body>
    <form action="/uploadServlet" enctype="multipart/form-data" method="post">
        <input type="text" name="hno">
        <input type="file" name="filename"><br>
        <input type="file" name="filename">
        <input type="submit" value="upload">
    </form>
</body>
</html>
