<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="kr.or.iei.member.model.vo.MemberImg"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="/resources/css/member/myPage/deliveryEmpty.css">
</head>
<body>

	<div id="wrapper">
	
		<header>
			<%@ include file="/views/section/header/headerComuPage.jsp"%>
		</header>
		
		<nav>
			<%@ include file="/views/section/navi/naviComu.jsp"%>
		</nav>

		<div id="middleWrapper">
		
			<aside>
				<div id="asideTitle">
					<p>
						<a href="/myPage.kh">마이페이지</a>
					</p>
				</div>
				<div id="asideMenu">
					<ul>
						<li><a href="/modifyPage.kh">회원 정보 수정</a></li>
						<li><a href="/OrdersList.kh">MY 쇼핑</a></li>
						<li><a href="/deliveryInfo.kh">배송 정보</a></li>
						<li><a href="/views/member/myPage/withdraw.jsp">회원 탈퇴</a></li>
					</ul>
				</div>
			</aside>
			
			<section>
				<div id="sectionWrap">
					<div id="titlePanel">
						<h1>배송 정보 조회</h1>
					</div>
					<hr>
					<div id="orderPanel">
						<p>주문하신 정보가 없습니다.</p>
					</div>
				</div>
			</section>
		</div>
		
		<footer>
			<%@ include file="/views/section/footer/footer.jsp"%>
		</footer>
		
	</div>
</body>
</html>