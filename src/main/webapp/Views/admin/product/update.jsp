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
		<input type="text" name="product_id" value="${product.product_id}" readonly="readonly"/><br>
		
		<Label>Name</Label><br>
		<input type="text" name="product_name" value="${product.product_name }"/><br>
		
		<Label>Description</Label><br>
		<textarea name="description" rows="3" cols="30" >${product.description}</textarea><br> 
		
		<Label>Price</Label><br>
		<input type="text" name="price" value="${product.price}"/><br>
		
		<Label>Image</Label><br>
		<input type="text" name="image" value="${product.image}"/><br>
		
		<Label>Category ID</Label><br>
		<input type="text" name="category_id" value="${product.category_id}" readonly="readonly"/><br>
		
		<Label>Seller ID</Label><br>
		<input type="text" name="seller_id" value="${product.seller_id}"/><br>
		
		<Label>Amount</Label><br>
		<input type="text" name="amount" value="${product.amount}"/><br>
		
		<Label>Stoke</Label><br>
		<input type="text" name="stoke" value="${product.stoke}"/><br>
		
		<input type="submit" value="Product update"/>
	</form>
</body>
</html>