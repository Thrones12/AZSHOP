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
	<form action="insert" method="POST">
		<Label>Name</Label><br> 
		<input type="text" name="product_name" /><br>
		
		<Label>Description</Label><br>
		<textarea name="description" rows="3" cols="30"></textarea><br> 
		
		<Label>Price</Label><br> <input type="text"
			name="price" /><br> 
			
		<Label>Image</Label><br> <input
			type="text" name="image" /><br> 
			
		<Label>Category ID</Label><br> <input type="text"
			name="category_id" /><br> 
			
		<Label>Seller ID</Label><br> <input
			type="text" name="seller_id" /><br> 
			
		<Label>Amount</Label><br>
		<input type="text" name="amount" /><br> 
		
		<Label>Stoke</Label><br>
		<input type="text" name="stoke" /><br> 
		
		<input type="submit"
			value="Product insert" />
	</form>
</body>
</html>