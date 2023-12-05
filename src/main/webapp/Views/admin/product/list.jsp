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
	<h5><a href="<c:url value='/admin/product/insert'></c:url>">Insert</a></h5>

	<table border="1">
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Descrip</th>
			<th>Price</th>
			<th>Cate</th>
			<th>Seller</th>
			<th>Image</th>
			<th>Amount</th>
			<th>Stoke</th>
			<th>Action</th>
		</tr>
		<c:forEach var="product" items="${products}" varStatus="STT">
			<tr>
				<td>${STT.index+1}</td>
				<td>${product.product_name}</td>
				<td>${product.description}</td>
				<td>${product.price}</td>
				<td>${product.category_id}</td>
				<td>${product.seller_id}</td>
				<td>${product.image}</td>
				<td>${product.amount}</td>
				<td>${product.stoke}</td>
				<td><a
					href="<c:url value="/admin/product/update?product_id=${product.product_id}"></c:url>">
						Update </a> <label> || </label> <a
					href="<c:url value="/admin/product/delete?product_id=${product.product_id}"></c:url>">
						Delete </a>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>