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

<title>insertServer</title>

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
	<form action="/xixi/boc_Server_Ser" method="post">
		MODULE:<input type="text" name="module" value="${requestScope.module}" /><br />
		TYPE:<input type="text" name="type" value="${requestScope.type}" /><br />
		IP:<input type="text" name="ip" value="${requestScope.ip}" /><br />
		REMARK:<input type="text" name="remark" value="${requestScope.remark}" /><br />		
		<input type="submit" value="Ìá½»" /> 
		<input type="hidden" name="status" value="insert" />
		<br/>
		${requestScope.error}
		${requestScope.info}
	</form>
</body>
</html>
