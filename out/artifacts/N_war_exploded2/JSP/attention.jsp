<%@page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
</head>
<body>
<%
    String infor = "";
    infor = (String) request.getAttribute("infor");
    System.out.println(infor);
    if (infor != null) {%>
<div style="color:red"><%=infor%>
</div>
<% }
%>
<form action="attentionservlet" method="get">公告名称 &nbsp;
    <input type="text" name="attentionName"><br><br>内容 &nbsp;
    <input type="text" name="attentionContain"><br><br>作业范例&nbsp;
    <input type="text" name="attentionExample"><br><br>开始时间 &nbsp;
    <input type="text" name="startTime"><br><br>结束时间 &nbsp;
    <input type="text" name="endTime"><br><br>注意事项 &nbsp;
    <input type="text" name="needAttention"><br><br>
    <input type="submit" value="提交">
</form>

<%
    ArrayList<String> list = new ArrayList<>();
    String attName = "";
    String contain = "";
    String example = "";
    String startT = "";
    String endT = "";
    String nedAtt = "";
    attName = request.getParameter("attentionName");
    contain = request.getParameter("attentionContain");
    example = request.getParameter("attentionExample");
    startT = request.getParameter("startTime");
    endT = request.getParameter("endTime");
    nedAtt = request.getParameter("needAttention");
    System.out.println("amospvrg" + attName);
    System.out.println(list);
    if (attName != null && contain != null) {
        list.add(attName);
        list.add(contain);
        list.add(example);
        list.add(startT);
        list.add(endT);
        list.add(nedAtt);
        System.out.println("attentionlist is " + list);
        session.setAttribute("listFor", list);
    }


%>


</body>
</html>