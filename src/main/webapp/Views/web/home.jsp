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
	<div class="col-sm-9 padding-right">
		<div class="features_items">
			<h2 class="title text-center">Sản phẩm</h2>
			<!--Product-->
			<c:forEach var="product" items="${listProduct }">
				<div class="col-sm-4">
					<div class="product-image-wrapper">
						<div class="single-products">
							<div class="productinfo text-center">
								<a
									href="<c:url value='/product-detail?product_id=${product.product_id}'></c:url>">
									<img style="width: 270px; height: 270px"
									src="templates/images/product/${product.image}" alt="" />
								</a>
								<h2>$${product.price }</h2>
								<p>${product.image }</p>
								<a href="#" class="btn btn-default add-to-cart"><i
									class="fa fa-shopping-cart"></i>Add to cart</a>
							</div>
						</div>
						<div class="choose">
							<ul class="nav nav-pills nav-justified">
								<li><a href="#"><i class="fa fa-plus-square"></i>Add to
										wishlist</a></li>
								<li><a href="#"><i class="fa fa-plus-square"></i>Add to
										compare</a></li>
							</ul>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</body>
</html>