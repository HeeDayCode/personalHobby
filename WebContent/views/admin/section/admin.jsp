<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/css/admin/section/admin.css">
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<meta http-equiv="X-UA-Compatible" content="ie=edge">
</head>
<body>
	<%
		LoadData lData = (LoadData) request.getAttribute("lData");
	%>
	<div id="wrapper">
		<div id="header">
			<%@ include file="/views/admin/section/header.jsp"%>
		</div>
		<div id="container">
			<div id="snb">
				<%@ include file="/views/admin/section/navi.jsp"%>
			</div>
			<div id="contents_box">
				<div id="pageTitle">
					&nbsp;<i class="fas fa-chart-line"></i>&nbsp;&nbsp;Today
					통계&nbsp;&nbsp;<i class="fas fa-pen-fancy"></i>
				</div>
				
				<a href="/adminToday.kh" id="reNew">오늘 통계 새로고침</a>

				<div id="snb-dayInfo">

					<span>오늘 회원가입 수 : </span><span><%=lData.getMemberNum()%></span><br>
					<br> <span>오늘 매출 금액 : </span><span><%=lData.getSalesNum()%>
						원</span>
				</div>

			</div>
		</div>
		<div id="footer">
			<%@ include file="/views/section/footer/footer.jsp"%>
		</div>
	</div>
</body>
</html>