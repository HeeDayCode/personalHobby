<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="kr.or.iei.orders.model.vo.OrdersAll"%>
<%@ page import="kr.or.iei.orders.model.vo.Orders"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="kr.or.iei.product.model.vo.ProductAll"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<!-- 아이콘 가져오는 CDN -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>

<link href="/resources/css/member/myPage/order.css" rel="stylesheet"
	type="text/css" />

</head>
<body>


	<style>
#orderTable {
	overflow: auto;
	height: 100px;
}

#container {
	height: 1000px;
}

#shoppingListTest {
	font-size: 30px;
}

#containerTitle {
	height: 180px;
	padding-left: 600px;
}

#searchOpt {
	width: 110px;
	height: 35px;
}

#dateSelect {
	padding: 0px;
}

#selectForm {
	padding-bottom: 20px;
}

#test {
	width: 50px;
	height: 35px;
}
</style>


	<%
		ArrayList<OrdersAll> ordersList = (ArrayList<OrdersAll>) request.getAttribute("ordersList");
		String searchOpt = (String) request.getAttribute("searchOpt");
		ProductAll pAll = new ProductAll();
	%>



	<div id="wrapper">
		<div id="header">
			<%@ include file="/views/section/header/headerComuPage.jsp"%>
		</div>
		<div id="navigator">
			<%@ include file="/views/section/navi/naviComu.jsp"%>

			<script src="https://code.jquery.com/jquery-3.5.1.js"
				integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
				crossorigin="anonymous"></script>

		</div>

		<div id="contents">
			<div id="snb">
				<div id="snb-reserved"></div>
				<div id="snb-adminId">
					<b>마이페이지</b>
				</div>
				<div id="snb-list">
					<a href="/modifyPage.kh">회원정보 수정</a><br> <a
						href="/OrdersList.kh">MY 쇼핑</a><br> 
						<a href="/deliveryInfo.kh">배송 정보</a><br>
					<a href="/views/member/myPage/withdraw.jsp">회원 탈퇴</a><br>
				</div>
			</div>

			<div id="container">
				<div id="containerTitle">
					<span><%=mAll.getM().getMemberName()%><span
						id="shoppingListTest">(님)의 MY 쇼핑</span></span>
				</div>
				<div id="basicInfo">
					<div id="dateSelect">

						<form action="/OrdersList.kh" method="post" id="selectForm">
							<select select id="searchOpt" name="searchOpt">
								<option value="all" name="orders_date"
									${(param.searchOpt == "all")?"selected":""}>전체</option>
								<option value="one_week" name="orders_date"
									${(param.searchOpt == "one_week")?"selected":""}>1주</option>
								<option value="one_month" name="orders_date"
									${(param.searchOpt == "one_month")?"selected":""}>1개월</option>
							</select> <input type="submit" value="확인" id="test" />
					</div>
					</form>

					<table cellspacing="0" cellpadding="0" width=100%; id="orderTable">
						<tr>
							<th class="colName" width="10%">날 짜</th>
							<th class="colName" width="20%">주문 번호</th>
							<th class="colName" width="40%">상 품</th>
							<th class="colName" width="15%">주문 금액</th>
							<th class="colName" width="15%">상 태</th>
						</tr>


						<%
							if (ordersList != null) {
						%>



						<%
							for (OrdersAll ordersAll : ordersList) {
						%>


						<tr>
							<td><%=ordersAll.getOrders().getOrdersDate()%></td>

							<td><%=ordersAll.getOrders().getOrdersNo()%></a></td>
							<td style="text-align: left;">
								<div id="productImg"
									style="width: 50px; height: 50px; border: 1px solid black; text-align: center; margin-right: 10px; padding-top: 15px; float: left;">
									<i class="fas fa-camera"></i>
								</div>
								<div style="float: left;">
									<a
										href="/productList.kh?productNo=<%=ordersAll.getProduct().getProductNo()%>"
										class="productName"><%=ordersAll.getProduct().getProductName()%></a>
								</div>
							</td>
							<td><%=ordersAll.getProduct().getProductPrice()%></td>
							<td class="orderPay"><%=ordersAll.getOrders().getOrdersPay()%></td>
						</tr>

						<%
							if (ordersAll.getOrders().getOrdersPay() == 'Y') {
						%>
						<script>
							$('.orderPay').html("결제 완료");
						</script>
						<%
							} else if (ordersAll.getOrders().getOrdersPay() == 'N') {
						%>
						<script>
							$('.orderPay').html("미결제");
						</script>
						<%
							}
						%>


						<%
							}
							}
						%>





					</table>
				</div>
			</div>




		</div>
		<div>
			<%@ include file="/views/section/footer/footer.jsp"%>
		</div>
	</div>
</body>
</html>