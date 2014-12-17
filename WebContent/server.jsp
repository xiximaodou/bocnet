<%@ page language="java" pageEncoding="gbk"%>
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

		<title>server</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<script type="text/javascript">
	
</script>
	</head>

	<body>
		
		<form action="/xixi/boc_Server_Ser" method="post">
			�ؼ���
			<input type="text" name="keyword" value="${sessionScope.keyword}" />
			<input type="submit" value="�ύ" />
			<input type="hidden" name="status" value="query" />
		</form>
		<form action="downloadfile" method="post">
				<button type="submit" value="����">Download</button>
		</form>
		<table border="1">
			<tr>
				<td>NO</td>	<td>MODULE</td>	<td>TYPE</td> <td>IP</td>	<td>REMARK</td>	<td>UPDATE</td> 				
			</tr>
			<c:forEach items="${requestScope.servers}" var="server" varStatus="num">
				<tr>
					<td>
						${num.count}
					</td>
					<td>
						<c:out value="${server.module}" default="��" />
					</td>
					<td>
						<c:out value="${server.type}" default="��" />
					</td>
					<td>
						<c:out value="${server.ip}" default="��" />
					</td>
					<td>
						<c:out value="${server.remark}" default="��" />
					</td>
					<td>
						<c:url value="/private/updateServer.jsp" var="update">
							<c:param name="no" value="${server.no}" />
							<c:param name="module" value="${server.module}" />
							<c:param name="type" value="${server.type}" />
							<c:param name="ip" value="${server.ip}" />
							<c:param name="remark" value="${server.remark}" />
						</c:url>
						<a href="${update}">����</a>
					</td>
					<td>
						ɾ��
					</td>
				</tr>
			</c:forEach>
		</table>
		<a href="/xixi/boc_Server_Ser?status=splitPage&currentPage=1">��ҳ</a>
		<c:choose>
			<c:when test="${sessionScope.currentPage==1}">
				��һҳ
			</c:when>
			<c:otherwise>
				<a href="/xixi/boc_Server_Ser?status=splitPage&currentPage=${sessionScope.currentPage-1}">��һҳ</a>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${sessionScope.currentPage==sessionScope.countPage}">
				��һҳ 
			</c:when>
			<c:otherwise>
				<a href="/xixi/boc_Server_Ser?status=splitPage&currentPage=${sessionScope.currentPage+1}">��һҳ </a>
			</c:otherwise>
		</c:choose>
		<a href="/xixi/boc_Server_Ser?status=splitPage&currentPage=${sessionScope.countPage}">βҳ </a>
		<script type="text/javascript">
			function changePage(){
				var currentPage=document.getElementById("currentPage").value;
				window.open("/xixi/boc_Server_Ser?status=splitPage&currentPage=" + currentPage,"_self");
			}
		</script>
		<select id="currentPage" onchange="changePage()">
			<c:forEach begin="1" end="${sessionScope.countPage}" step="1" var="num">
				<c:choose>
					<c:when test="${num==sessionScope.currentPage}">
						<option value="${num}" selected="selected">${num}</option>
					</c:when>
					<c:otherwise>
						<option value="${num}">${num}</option>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</select>
	</body>
</html>
