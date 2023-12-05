<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${message!=null}">
		<span>${message}</span>
	</c:if>
	<c:if test="${erroe!=null}">
		<span>${error}</span>
	</c:if>

	<h5><a href="<c:url value='/admin/user/insert'></c:url>">Insert</a></h5>
	
	<table border="1">
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Password</th>
			<th>Email</th>
			<th>Role</th>
			<th>Status</th>
			<th>Action</th>
		</tr>
		<c:forEach var="i" items="${listuser}" >
			<tr>
				<td>${i.userID}</td>
				<td>${i.userName}</td>
				<td>${i.password}</td>
				<td>${i.email}</td>
				<td>${i.role}</td>
				<td>${i.status}</td>
				<td><a
					href="<c:url value="/admin/user/update?id=${i.userID}"></c:url>">
						Update </a> <label> || </label> <a
					href="<c:url value="/admin/user/delete?id=${i.userID}"></c:url>">
						Delete </a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>