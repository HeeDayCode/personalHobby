<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="/resources/css/member/login/loginForm.css" rel="stylesheet" type="text/css"/>
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

<script src="https://code.jquery.com/jquery-3.5.1.js"
		integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
		crossorigin="anonymous"></script>
	
		</div>
		<div id="container">
			<div id="login_box">
				<form action="/memberLogin.kh" method="post">
					<input type="text" name="userId" placeholder="   아이디" /><br> 
					<input type="password" name="userPw" placeholder="   비밀번호" /><br>
					<input type="checkbox" /> 아이디 저장<br>
					<input type="submit" value="로그인" />
				</form>
				
				<div id="find_info">
					<a href="/views/member/login/findId.jsp">아이디 찾기</a> <a href="/views/member/login/findId.jsp">비밀번호 찾기</a> <a href="/views/member/join/joinForm.jsp">회원가입</a>
				</div>
				
				
				<div id="login_api">
					<fieldset>
						<legend>SNS 간편 로그인 </legend>
						<input id="naver" type="submit" value="네이버"></input><br> <input
							id="kakao" type="submit" value="카카오"></input>
					</fieldset>
				</div> 
			
			</div>
		</div>
		<div>
			<%@ include file="/views/section/footer/footer.jsp" %>
		</div>
	</div>
</body>
</html>