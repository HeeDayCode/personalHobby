<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="/resources/css/member/myPage/withdraw.css">
<script type="text/javascript" src="/resources/js/profileModify.js"></script>
<script src="https://kit.fontawesome.com/822d8824d6.js"
	crossorigin="anonymous"></script>
<script>
	$(function() {
		$(".birthBtn").click(function() {
			$(this).children("ul").toggle(300);
		});
		$("#phoneBtn").click(function() {
			$("#smsNo").attr("disabled", false);
		});
	});
</script>
</head>
<body>
	<div id="wrapper">
		<header> <%@ include file="/views/section/header/headerComuPage.jsp"%>
		</header>
		<nav> <%@ include file="/views/section/navi/naviComu.jsp"%>
		<script src="https://code.jquery.com/jquery-3.5.1.js"
		integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
		crossorigin="anonymous"></script>
	
		</nav>
		<div id="middleWrapper">
			<aside>
			<div id="asideTitle">
				<p><a href="/views/member/myPage/myPage.jsp">마이페이지</a></p>
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
			<section> <!-- <div id="profileHeader">프로필헤더</div> -->
			<div id="modifyContainer">
				<div id="modifyTitle">
					<h1>회원탈퇴</h1>
				</div>
				<div id="modifyPanel">
					<form action="/memberWithdraw.kh" method="post">
						<div>
							<div class="inputLable">비밀번호 입력</div>
							<div class="inputWrap">
								<input type="password" name="userPw" value="">
							</div>
						</div>
						<div id="modifyBtnPanel">
                            <input type="submit" value="회원탈퇴">
                        </div>
					</form>
				</div>
			</div>
			</section>
		</div>
		<footer> <%@ include file="/views/section/footer/footer.jsp"%>
		</footer>
	</div>
</body>
</html>