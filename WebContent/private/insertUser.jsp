<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>insertUser</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<form action="/xixi/boc_User_Ser" method="post">
		�û���:<input type="text" name="name" value="${requestScope.name}" /><br />
		����:<input type="password" name="password" value="${requestScope.password}" /><br />
		
		<input type="submit" value="�ύ" /> 
		<input type="hidden" name="status" value="insertUser" />
		<br/>
		${requestScope.error}
		${requestScope.info}
	</form>
</body>
</html>
