<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<header id="header">
	<!--header-->
	<div class="header_top">
		<!--header_top-->
		<div class="container">
			<div class="row">
				<div class="col-sm-6">
					<div class="contactinfo">
						<ul class="nav nav-pills">
							<li><a href="#"><i class="fa fa-phone"></i> +84 981 141
									044</a></li>
							<li><a href="#"><i class="fa fa-envelope"></i>
									hungphong.congviec@gmail.com</a></li>
						</ul>
					</div>
				</div>
				<div class="col-sm-6">
					<div class="social-icons pull-right">
						<ul class="nav navbar-nav">
							<li><a target="_blank"
								href="https://www.facebook.com/profile.php?id=100074351727209"><i
									class="fa fa-facebook"></i></a></li>
							<li><a target="_blank"
								href="https://twitter.com/ashenone2003"><i
									class="fa fa-twitter"></i></a></li>
							<li><a target="_blank"
								href="https://www.instagram.com/_phamphong/"><i
									class="fa fa-linkedin"></i></a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--/header_top-->

	<div class="header-middle">
		<!--header-middle-->
		<div class="container">
			<div class="row">
				<div class="col-sm-4">
					<div class="logo pull-left">
						<a href="home"><img src="templates/images/home/logo.png"
							alt="" /></a>
					</div>
				</div>
				<div class="col-sm-8">
					<div class="shop-menu pull-right">
						<ul class="nav navbar-nav">
							<li><a href="user/account"><i class="fa fa-user"></i>
									Tài khoản</a></li>
							<li><a href="user/wishlist"><i class="fa fa-star"></i>
									Yêu thích</a></li>
							<li><a href="user/history"><i class="fa fa-crosshairs"></i>
									Lịch sử</a></li>
							<li><a href="user/cart"><i class="fa fa-shopping-cart"></i>
									Giỏ hàng</a></li>
							<li><a href="web/login"><i class="fa fa-lock"></i> Đăng
									nhập</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--/header-middle-->

	<div class="header-bottom">
		<!--header-bottom-->
		<div class="container">
			<div class="row">
				<div class="col-sm-9">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse"
							data-target=".navbar-collapse">
							<span class="sr-only">Toggle navigation</span> <span
								class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
					</div>
				</div>
				<div class="col-sm-3">
					<div class="search_box pull-right">
						<form action="${pageContext.request.contextPath}/search"
							method="post">
							<input name="searchInput"
								style="width: 255px; background-position: 230px;" type="text"
								placeholder="Search" />
							<button type="submit" style="display: none;"></button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--/header-bottom-->
</header>
<!--/header-->