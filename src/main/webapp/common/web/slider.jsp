<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<section id="slider">
	<!--slider-->
	<div class="container">
		<div class="row">
			<div class="col-sm-12">
				<div id="slider-carousel" class="carousel slide"
					data-ride="carousel">
					<ol class="carousel-indicators">
						<li data-target="#slider-carousel" data-slide-to="0"
							class="active"></li>
						<li data-target="#slider-carousel" data-slide-to="1"></li>
						<li data-target="#slider-carousel" data-slide-to="2"></li>
					</ol>

					<div class="carousel-inner">
						<div class="item active">
							<div class="col-sm-6">
								<h2>${bestSelling_active.product_name }</h2>
								<p>${bestSelling_active.description }</p>
								<a
									href="<c:url value='/product-detail?product_id=${bestSelling_active.product_id}'></c:url>"><button
										type="button" class="btn btn-default get">Get it now</button></a>
							</div>
							<div class="col-sm-6">
								<img src="templates/images/product/${bestSelling_active.image }"
									style="width =: auto; height: 300px"
									class="girl img-responsive" alt="" /> <img />
							</div>
						</div>
						<div class="item">
							<div class="col-sm-6">
								<h2>${bestSelling1.product_name }</h2>
								<p>${bestSelling1.description }</p>
								<a
									href="<c:url value='/product-detail?product_id=${bestSelling1.product_id}'></c:url>"><button
										type="button" class="btn btn-default get">Get it now</button></a>
							</div>
							<div class="col-sm-6">
								<img src="templates/images/product/${bestSelling1.image }"
									style="width =: auto; height: 300px"
									class="girl img-responsive" alt="" /> <img />
							</div>
						</div>
						<div class="item">
							<div class="col-sm-6">
								<h2>${bestSelling2.product_name }</h2>
								<p>${bestSelling2.description }</p>
								<a
									href="<c:url value='/product-detail?product_id=${bestSelling2.product_id}'></c:url>"><button
										type="button" class="btn btn-default get">Get it now</button></a>
							</div>
							<div class="col-sm-6">
								<img src="templates/images/product/${bestSelling2.image }"
									style="width =: auto; height: 300px"
									class="girl img-responsive" alt="" /> <img />
							</div>
						</div>
					</div>

					<a href="#slider-carousel" class="left control-carousel hidden-xs"
						data-slide="prev"> <i class="fa fa-angle-left"></i>
					</a> <a href="#slider-carousel"
						class="right control-carousel hidden-xs" data-slide="next"> <i
						class="fa fa-angle-right"></i>
					</a>
				</div>

			</div>
		</div>
	</div>
</section>
<!--/slider-->