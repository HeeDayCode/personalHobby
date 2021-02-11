<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="/resources/css/member/login/findSuccess.css" rel="stylesheet" type="text/css"/>
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
			<div id="find_box">
				<ul>
					<li id="click_id"><a href="/views/member/login/findId.jsp">아이디찾기</a></li>
					<li id="click_pw"><a href="/views/member/login/findId.jsp">비밀번호 찾기</a></li>
				</ul>	
				<% String memberId=(String)request.getAttribute("memberId"); %>
				<div id="find_id_text">
				<center>
					당신의 아이디는 <%=memberId %>입니다.
					<input type="hidden" name="userPw" id="userPw" placeholder="변경할 비밀번호를 입력해주세요"/>
					<input type="hidden" name="userPw_re" id="userPw_re" placeholder="변경할 비밀번호를 다시 입력해주세요"/>
					<button style="display:none;" id="checkBtn">확인</button>
				</center>		
				</div>
				<div id="login_box">
					<a href="/views/member/login/loginForm.jsp">로그인</a>
					<a href="/views/member/login/findId.jsp">비밀번호찾기</a>
				</div>
			</div>
		</div>
		<div id="footer">
			<%@ include file="/views/section/footer/footer.jsp" %>
		</div>
	</div>
	
</body>
</html>