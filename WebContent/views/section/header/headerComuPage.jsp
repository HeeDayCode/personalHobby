<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="kr.or.iei.member.model.vo.MemberAll"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="/resources/css/section/header/headerComuPage.css" rel="stylesheet" type="text/css" />
</head>
<body>
<%
		MemberAll mAll = (MemberAll) session.getAttribute("memberAll");
	%>
	<header>
		<div id="community">
			<a href="/storeMain.kh">스토어</a>
		</div>
		<div id="logo">
			<a href="/index.kh"><img src="/resources/images/logo.png"
				height="100px" /></a>
		</div>
		<div id="login">
	<%if (mAll != null){ %>
            <% if(0<=mAll.getM().getMemberNo() && mAll.getM().getMemberNo()<=100) { %>
               <a href="/adminToday.kh">관리자페이지</a>
            <% } else { %>
               <a href="/myPage.kh">마이페이지</a>
            <% } %>
            <a href="/memberLogout.kh">로그아웃</a><br>
      <%} else{%>
         <a href="/views/member/login/loginForm.jsp">로그인</a>
         <%} %>
		</div>
	</header>
</body>
</html>