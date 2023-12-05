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
	<form action="update" method="POST">
		<label>ID: </label><br>
		<input type="text" name="category_id" value="${category.category_id}" readonly="readonly"/><br>
		
		<Label>Name</Label><br>
		<input type="text" name="category_name" value="${category.category_name}"/><br>
		
		<input type="submit" value="Category update"/>
	</form>
</body>
</html>