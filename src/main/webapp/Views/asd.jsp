<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ include file="/common/taglib.jsp"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style>
.zoomable {
	cursor: pointer;
	transition: transform 0.3s;
}

.zoomed {
	transform: scale(2); /* hoặc giá trị phóng to mong muốn */
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background: rgba(0, 0, 0, 0.8);
	display: flex;
	justify-content: center;
	align-items: center;
}

.zoomed img {
	width: 100%;
	height: 100%;
	object-fit: contain;
}
</style>
</head>
<body>
	<img class="zoomable"
		src="<c:url value='/templates/images/home/logo.png'></c:url>"
		alt="Zoomable Image">

	<script>
		document.addEventListener('DOMContentLoaded', function() {
			const zoomableImage = document.querySelector('.zoomable');

			zoomableImage.addEventListener('click', function() {
				zoomableImage.classList.toggle('zoomed');
			});
		});
	</script>
</body>
</html>
