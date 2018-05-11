<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<!-- 遍历Map集合 -->
<c:forEach var="cqut" items="${fileNameMap}">
<c:url value="download" var="downurl">
<c:param name="filename" value="${cqut.key}"></c:param>
</c:url>
${cqut.value} <a href="${downurl}">下载</a>
<br/>
</c:forEach>
</body>
</html>