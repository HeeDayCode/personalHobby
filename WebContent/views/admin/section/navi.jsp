<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="kr.or.iei.member.model.vo.LoadData"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/css/admin/section/navi.css">
</head>
<body>

	

	<div id="snb-box">
		<div id="snb-reserved"></div>
		<div id="snb-adminId">
			<p>
				<a href="/adminToday.kh" style="color:black;">관리자</a><span>(님)</span>
			</p>
			
		</div>
		<div id="snb-list">
			<ul>

			
			
				<li><a href="/memberSales.kh">통계</a>
					<ul class="list">
					<li><a href="/adminToday.kh">Today</a></li>
						<li><a href="/memberSales.kh">매출</a></li>
						<li><a href="/memberStatistics.kh">회원</a></li>
					</ul></li>
				<li><a href="/views/admin/product/register.jsp">상품 관리</a>
					<ul class="list">
						<li><a href="/views/admin/product/register.jsp">상품 등록</a></li>
						<li><a href="/productAllList.kh">상품 목록</a></li>
					</ul></li>
					
					<li><a href="/orderAll.kh?currentPage=1">주문 배송 관리</a>
					<ul class="list">
						<li><a href="/orderAll.kh?currentPage=1">주문 내역 보기</a></li>
					</ul></li>
					
				<li><a href="/memberAllListPage.kh?currentPage=1">회원 관리</a>
					<ul class="list">
						<li><a href="/memberAllListPage.kh?currentPage=1">회원
								조회 & 삭제</a></li>
					</ul></li>
				<li><a href="/writeAllList.kh?currentPage=1">게시글 관리</a>
					<ul class="list">
						<li><a href="/writeAllList.kh?currentPage=1">게시글 삭제</a></li>
						<li><a href="/commentsAllList.kh?currentPage=1">댓글 삭제</a></li>
					</ul></li>
			</ul>
		</div>
	</div>
</body>
</html>