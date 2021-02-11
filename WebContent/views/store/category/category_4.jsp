<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body><!-- 베이킹 데코 키트 --><%-- 
<%@ include file="/views/store/category/category.jsp"%> --%>
	<script src="https://code.jquery.com/jquery-3.5.1.js"
		integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
		crossorigin="anonymous"></script>
	<script>
		$(function() {
			$('#category_img img').attr('src',
					"/resources/images/store/category/c_4.jpg");
			$('#category_title').text('베이킹/데코 키트');
			$('#category_sub').text('맛과 즐거움을 동시에, 내가 만든 간식');
			$('#navi_category span:eq(4)').css('font-weight', 'bolder');
			$('#navi_category span:eq(4)').css('color', 'rgb(128,127,244)');
			$('#navi_category span:eq(4)').css('background-color', 'rgba(169,120,254,0.2)');
		});
	</script>
</body>
</html>