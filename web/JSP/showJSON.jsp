<%--
  Created by IntelliJ IDEA.
  User: tzz
  Date: 2018/4/30
  Time: 23:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>showJson</title>
    <script type="text/javascript" src="/JS/jquery-1.4.1.min.js"></script>
    <script type="text/javascript">
        function test() {
            $.ajax({
                type: "POST", //请求方式
                url: "/JsonServlet",//请求路径
                cache: false,
                data: "name=zah",
                //传参
                dataType: 'text',//返回值类型
                success: function (json) {
                    alert(json);
                    // alert(json[1].username + " " + json[1].password);//弹出返回过来的List对象
                }
            });
            document.write("aaa")
        }
    </script>
</head>
<body>
<input type="button" name="b" value="测试" onclick="test();">
</body>
</html>
