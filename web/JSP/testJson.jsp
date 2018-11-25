<%--
  Created by IntelliJ IDEA.
  com.zt.sr.pojo.User: tzz
  Date: 2018/5/1
  Time: 19:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>testJson</title>
    <script type="text/javascript" src="/JS/jquery-1.4.1.min.js"></script>
    <script type="text/javascript">
        var J;

        function test() {
            alert("aa");
            $.ajax({
                type: "POST", //请求方式
                url: "/com.zt.sr.controller.myHomeworkServlet",//请求路径
                cache: false,
                data: "",//传参
                dataType: 'json',//返回值类型
                success: function (json) {
                    alert(json);
                    alert(json[0].beginTime + " " + json[0].endTime);//弹出返回过来的List对象
                    alert(ChangeDateFormat(json[0].beginTime));
                    J = json;
                }
            });
        }

        function ChangeDateFormat(d) {
            //将时间戳转为int类型，构造Date类型
            var date = new Date(parseInt(d.time, 10));

            //月份得+1，且只有个位数时在前面+0
            var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;

            //日期为个位数时在前面+0
            var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();

            //getFullYear得到4位数的年份 ，返回一串字符串
            return date.getFullYear() + "-" + month + "-" + currentDate;
        }

        function out() {
            document.writeln("aaa");

            document.writeln(J[1].hno);
            document.writeln(J[1].hcontent);
        }
    </script>
</head>
<body>
    <input type="button" name="b" value="测试" onclick="test();">
    <input type="button" name="a" value="测试" onclick="out();">
</body>
</html>
