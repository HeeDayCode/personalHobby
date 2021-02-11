<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="kr.or.iei.member.model.vo.MemberImg"%>
<%@ page import="kr.or.iei.orders.model.vo.Orders" %>
<%@ page import="kr.or.iei.img.model.vo.Img" %>
<%@ page import="kr.or.iei.orders.model.vo.OrdersWithImg" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="/resources/css/member/myPage/deliveryCheck.css">
</head>
<body>
	<%
		ArrayList<OrdersWithImg> list=(ArrayList<OrdersWithImg>)request.getAttribute("list");
	%>

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
					<div>
						<table>
							<tr>
								<th>주문번호</th>
								<th>주문일자</th>
								<th>상품사진</th>
								<th>상품명</th>
								<th>개수</th>
								<th>결제금액</th>
								<th>배송상태</th>
								<th>구매확정</th>
							</tr>
						<% for(OrdersWithImg oAll : list) { %>
							<tr>
								<td id="orderNoPan">
									<p><%=oAll.getOrders().getOrdersNo() %></p>
								</td>
								<td>
									<p><%=oAll.getOrders().getOrdersDate() %></p>
								</td>
								<td id="imagePan">
									<img src="/resources/images/product/<%=oAll.getImg().getChangedName() %>.png"/>
								</td>
								<td id="titlePan">
									<h3><%=oAll.getProduct().getProductName() %></h3>
								</td>
								<td id="countPan">
									<p><%=oAll.getOrders().getOrdersNum() %> EA</p>
								</td>
								<td id="pricePan">
									<p><%=oAll.getProduct().getProductPrice()*oAll.getOrders().getOrdersNum()+2500 %> 원</p>
								</td>
								<td id="statusPan">
								<% if(oAll.getOrders().getDeliveryYN()=='Y' && oAll.getOrders().getReceiveYN()=='Y'){ %>
										<p style="color: #b084fe;">배송완료</p>
								<% } else if(oAll.getOrders().getDeliveryYN()=='Y' && oAll.getOrders().getReceiveYN()=='N') { %>
										<p style="color: skyblue;">배송중</p>
								<% } else { %>
										<p style="color: pink;">상품준비중</p>
								<% } %>
								</td>
								<td id="deliveryBtnPan">
								<% if(oAll.getOrders().getDeliveryYN()=='Y' && oAll.getOrders().getReceiveYN()=='Y'){ %>
										<p>구매 확정</p>
								<% } else { %>
										<input type="button" value="수령확인" class="deliveryBtn"/>
								<% } %>
								</td>
							</tr>
						<% } %>
						</table>
					</div>
				</div>
			</section>
		</div>
		
		<footer>
			<%@ include file="/views/section/footer/footer.jsp"%>
		</footer>
		
	</div>
	
	<script>
		$(function(){
			$(".deliveryBtn").click(function(){
				// 주문번호 빼오는 변수
				var orderNo=$(this).parents("tr").children().children().html();
				// 배송완료로 바꾸는 변수
				var statusChange=$(this).parents("tr").children("#statusPan");
				// 배송완료되면 구매확정 글 띄우는 변수
				var thisBtn=$(this).parents("td");
				
				var result=confirm("구매 확정 하시겠습니까?\n(구매 확정 이후에는 교환 및 환불이 불가능합니다.)");
				
				if(result){
					$.ajax({
						url: "/deliveryComplete.kh",
						type: "post",
						data: {orderNo: orderNo},
						success: function(result){
							statusChange.html("<p style='color: #b084fe;'>배송완료</p>");
							thisBtn.html("<p>구매 확정</p>");
						}
					});
				}
				
			});
		});
	</script>
	
</body>
</html>