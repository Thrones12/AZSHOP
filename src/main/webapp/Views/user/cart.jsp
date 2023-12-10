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
	<section id="cart_items">
		<div class="features_items">
			<h2 class="title text-center">Giỏ hàng</h2>
		</div>
		<div class="container">
			<div class="table-responsive cart_info">
				<table class="table table-condensed">
					<thead>
						<tr class="cart_menu">
							<td class="image">Hình ảnh</td>
							<td class="description">Tên sản phẩm</td>
							<td class="price">Đơn giá</td>
							<td class="quantity">Số lượng</td>
							<td class="total">Thành tiền</td>
							<td></td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="cart" items="${carts}">
							<tr>
								<td class="cart_product"><a href=""><img width="80px"
										height="auto"
										src="${pageContext.request.contextPath}/templates/images/product/${cart.product.image}"
										alt="error"></a></td>
								<td class="cart_description">
									<h4>
										<a href="">${cart.product.product_name }</a>
									</h4>
									<p>Mã sản phẩm: ${cart.product.product_id}</p>
								</td>
								<td class="cart_price">
									<p>$${cart.product.price }</p>
								</td>
								<td class="cart_quantity">
									<div class="cart_quantity_button">
										<a class="cart_quantity_up"
											href="${pageContext.request.contextPath}/user/update-cart?cart_id=${cart.cart_id}&quantity=1">
											+ </a> <input class="cart_quantity_input" type="text"
											name="quantity" value="${cart.quantity }" autocomplete="off"
											size="2"> <a class="cart_quantity_down"
											href="${pageContext.request.contextPath}/user/update-cart?cart_id=${cart.cart_id}&quantity=-1">
											- </a>
									</div>
								</td>
								<td class="cart_total">
									<p class="cart_total_price">$${cart.total_price }</p>
								</td>
								<td class="cart_delete"><a class="cart_quantity_delete"
									href="${pageContext.request.contextPath}/user/delete-cart?cart_id=${cart.cart_id}"><i
										class="fa fa-times"></i></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</section>
	<!--/#cart_items-->
	<section id="do_action" style="justify-content: flex-end;">
		<div class="container">
			<div class="row">
				<div class="col-sm-6">
					<div class="total_area" style="width: 500px; font-size: 20px">
						<p>
							Giá trị đơn hàng: <span>$${total }</span>
						</p>
						<a style="font-size: 20px" class="btn btn-default check_out"
							href="${pageContext.request.contextPath}/user/checkout">Đặt hàng</a>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!--/#do_action-->

</body>
</html>