<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"
	integrity="sha384-q2kxQ16AaE6UbzuKqyBE9/u/KzioAlnx2maXQHiDX9d4/zp8Ok3f+M7DPm+Ib6IU"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.min.js"
	integrity="sha384-pQQkAEnwaBkjpqZ8RU1fF1AKtTcHJwFl3pblpTlHXybJjHpMYo79HY3hIi4NKxyj"
	crossorigin="anonymous"></script>
</head>
<body>
	<header> <%@ include
		file="/views/section/header/headerShop.jsp"%>
	</header>

	<%@ include file="/views/section/navi/naviShop.jsp"%>

	<%@ include file="/views/store/main/1_ad.jsp"%>
	<%@ include file="/views/store/main/2_top3.jsp"%>
	<%@ include file="/views/store/main/3_new.jsp"%>
	<%@ include file="/views/store/main/4_hot.jsp"%>
	<%@ include file="/views/store/main/5_best.jsp"%>

	<footer> <%@ include file="/views/section/footer/footer.jsp"%>
	</footer>

	<script src="https://code.jquery.com/jquery-3.5.1.js"
		integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
		crossorigin="anonymous"></script>
	<script>
		$(function() {

			$('#category_tap').hover(function() {
				$('#t-wrapper').css('z-index', '99');
				$('#t-wrapper').show();
				$('#carouselExampleIndicators').css('z-index', '-1');
				$('div[id*=t-]').show();
			});
			$('div[id*=t-]').hover(function() {
				$('#t-wrapper').children().show();
			});
			$('#t-wrapper').hover(function() {
				$(this).children().show();
			}, function() {
				$('#t-wrapper').hide();
				$('#t-wrapper').css('z-index', '-1');
				$('#carouselExampleIndicators').css('z-index', '99');
			});
		});
	</script>
</body>
</html>