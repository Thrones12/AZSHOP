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
			<c:if test="${not empty(message)}">
				<p style="text-align: center;">${message}</p>
			</c:if>
			<div id="content">
				<!--Product-->
				<c:forEach var="product" items="${listProduct }">
					<div class="productAjax col-sm-4">
						<div class="product-image-wrapper">
							<div class="single-products">
								<div class="productinfo text-center">
									<a
										href="<c:url value='/product-detail?product_id=${product.product_id}'></c:url>">
										<img style="width: 270px; height: 270px"
										src="templates/images/product/${product.image}" alt="" />
									</a>
									<h2>$${product.price }</h2>
									<h4>${product.product_name }</h4>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>

	<script>
		$(window).scroll(
				function() {
					if ($(window).scrollTop() + $(window).height() >= $(
							document).height()) {
						loadAjax();
					}
				});

		function loadAjax() {
			var amount = $(".productAjax").length;
			$.ajax({
				url : "/AZSHOP/loadAjax",
				type : "get",
				data : {
					exists : amount,
					category_id: ${category_id},
					supplier_id: ${supplier_id},
					start_range: ${start_range},
					end_range  : ${end_range}
				},
				success : function(data) {
					$("#content").append(data);
				},
				error : function(xhr) {
					// Xử lý lỗi nếu cần
				}
			});
		}
	</script>
</body>
</html>