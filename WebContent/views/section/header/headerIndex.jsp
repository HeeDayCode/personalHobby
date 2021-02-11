<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="kr.or.iei.member.model.vo.MemberAll"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="/resources/css/section/header/headerIndex.css"
	rel="stylesheet" type="text/css" />
	<link href="/resources/css/section/navi/naviIndex.css" rel="stylesheet"
	type="text/css" />
	<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
</head>
<body>
	<%
		MemberAll mAll = (MemberAll) session.getAttribute("memberAll");
	%>
	<header>
	<div id="shop">
		<a href="/storeMain.kh">스토어</a>
	</div>
	<div id="logo">
		<a href="/index.kh"><img src="/resources/images/logo.png" /></a>
	</div>
	<div id="login">
		<%
			if (mAll != null) {
		%>
		<%
			if (0 <= mAll.getM().getMemberNo() && mAll.getM().getMemberNo() <= 100) {
		%>
		<a href="/adminToday.kh">관리자페이지</a>
		<%
			} else {
		%>
		<a href="/myPage.kh">마이페이지</a>
		<%
			}
		%>
		<a href="/memberLogout.kh">로그아웃</a><br>
		<%
			} else {
		%>
		<a href="/views/member/login/loginForm.jsp">로그인</a>
		<%
			}
		%>
	</div>
	</header>
	
	
	<div id="navigator">
		<div id="gnb">
			<ul>
				<%
					if (mAll != null) {
				%>
				<li><a href="/views/community/write/write.jsp">ADD</a></li>
				<%
					} else {
				%>
				<li><a href="/views/member/login/loginForm.jsp">ADD</a></li>
				<%
					}
				%>
				<li><a href="/indexBest.kh?align=Best" id="Best">BEST</a></li>
				<li><a href="/indexNew.kh?align=New" id="New">NEW</a></li>
			</ul>
			<form action="/index.kh" method="get" id="search_box">
				<input type="text" />
				<button>
					<i class="fas fa-search"
						style="height: 100%; border: 0; width: 20px; color: lightgrey"></i>
				</button>
			</form>
		</div>
	</div>
	
</body>
</html>