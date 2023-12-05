<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ include file="/common/taglib.jsp" %>
<div class="col-sm-3">
	<div class="left-sidebar">
		<h2>Danh mục</h2>
		<div class="panel-group category-products" id="accordian">
			<!--category-productsr-->
			<c:forEach var="category" items="${listCategory }">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a href="<c:url value='/home?category_id=${category.category_id}'></c:url>">
							${category.category_name}</a>
						</h4>
					</div>
				</div>
			</c:forEach>

		</div>
		<!--/category-products-->

	</div>
</div>