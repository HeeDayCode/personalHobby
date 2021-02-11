<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="/resources/css/member/join/joinSuccess.css" rel="stylesheet" type="text/css"/>
<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<%@ include file="/views/section/header/headerComuPage.jsp" %>
		</div>
		<div id="navigator">
				<%-- <%@ include file="/views/sections/navigator.jsp" %> --%>
			<%@ include file="/views/section/navi/naviShop.jsp"%>

		</div>
		<div id="container">
			<h1>회원가입 완료!</h1>
			<p>회원 가입을 진심으로 축하드립니다.</p>
			<div>
			<a href="/views/member/login/loginForm.jsp">로그인</a>
			<a href="/storeMain.kh">쇼핑하러가기</a>
			</div>
		</div>
		<div>
			<%@ include file="/views/section/footer/footer.jsp" %>
		</div>
	</div>
	
	<script>
		
		
	</script>
</body>
</html>