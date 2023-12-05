<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="update" method="post">
		<label>ID: </label><br>
		<input type="text" name="userID" value="${user.userID}" readonly="readonly"/><br>

		<Label>Name</Label><br> 
		<input type="text" name="userName"value="${user.userName}" /><br>
		
		<Label>Password</Label><br> 
		<input type="text" name="password" value="${user.password}"/><br> 
			
		<Label>Email</Label><br> <input
			type="text" name="email" value="${user.email}"/><br> 
			
		<Label>Role</Label><br> 
		<input type="text" name="role" value="${user.role}"/><br> 
		
		<Label>Status</Label><br> 
		<input type="text" name="status" value="${user.status}"/><br> 
		
		<input type="submit" value="User update"/>
	</form>
</body>
</html>