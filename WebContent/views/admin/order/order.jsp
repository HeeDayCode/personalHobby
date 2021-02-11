<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="kr.or.iei.orders.model.vo.OrderPageData"%>
<%@ page import="kr.or.iei.orders.model.vo.OrderAll"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/resources/css/admin/order/order.css" rel="stylesheet"
	type="text/css" />
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<!-- 아이콘 가져오는 CDN -->
</head>

<body>


	<%
		OrderPageData opd = (OrderPageData) request.getAttribute("pageData");
		ArrayList<OrderAll> list = opd.getList();

		String pageNavi = opd.getPageNavi();
		String keyword = (String) request.getAttribute("keyword");
	%>

	<div id="wrapper">
		<div id="header">
			<%@ include file="/views/admin/section/header.jsp"%>
		</div>
		<div id="container">
			<div id="snb">
				<%@ include file="/views/admin/section/navi.jsp"%>
			</div>
			<div id="article_right">
				<div id="article_right_top">
					<ul id="article_right_top_title">
						<li id="article_right_top_title_1" style="font-size: 30px"><i
							class="fas fa-truck"></i> 주문 관리</li>
					</ul>
					<br> <br> <br>
					<div id="article_right_top_sub">배송 처리</div>
				</div>

				<div id="article_right_middle">
				
					<form action="/orderSearch.kh" method="post">
						<div id="article_right_middle_line1">
							<ul id="article_right_middle_line2">
								<li id="article_right_middle_title">주문 내역 검색</li>
								<li id="article_right_middle_select"><select
									id="post_select" name="Opt" style="width: 100px;">

										<option ${(param.Opt == "orders_date")?"selected":""}
											value="orders_date" name="orders_date">주문일</option>

										<option ${(param.Opt == "product_name")?"selected":""}
											value="product_name" name="product_name">상품명</option>

										<option ${(param.Opt == "member_id")?"selected":""}
											value="member_id" name="member_id">ID</option>

										<option ${(param.Opt == "member_Nickname")?"selected":""}
											value="member_Nickname" name="member_Nickname">닉네임</option>

								</select></li>
								<li id="article_right_middle_search"><input type="text"
									placeholder=" 키워드를 입력하세요." name="keyword"
									value="${param.keyword}" /> <input type="submit"
									style="height: 30px; width: 100px;" value="검색"
									id="input_keyword" /> &nbsp;날짜 형식 : 년/월/일 (예 : 20/12/05)</li>
							</ul>
						</div>
					</form>

				</div>
				<div id="article_right_main">
					<span><a href="/orderAll.kh?currentPage=1">전체 주문 목록</a></span><br>
					<span>검색 결과 <%=opd.getTotal()%> 건
					</span>

					<div id="article_right_submain">

						<div id="article_right_main_table">
							<table id="post_table">
								<tr id="search_list">
									<th width=90px; rowspan="2">주문 번호</th>
									<th width=140px;>주문 정보</th>
									<th width=150px;>주문일</th>
									<th width=160px;>회원 ID / 닉네임</th>
									<th width=400px;>상품명</th>
									<th width=100px;>가격</th>
									<th width=70px;>개수</th>
									<th width=150px;>총 금액 (배송비 포함)</th>
									<th width=110px;>결제 여부</th>
									<th width=110px;>상품 발송</th>
								</tr>
								<tr id="search_list">
									<th>배송지 정보</th>
									<th>수령인 이름</th>
									<th>수령인 연락처</th>
									<th colspan="3">주소</th>
									<th colspan="2">배송시 요청사항</th>
									<th>도착 여부</th>
								</tr>

								<%
									for (OrderAll oAll : list) {
								%>
								<tr id="post_list">
									<td rowspan="2"><%=oAll.getO().getOrdersNo()%></td>
									<td>주문 정보</td>
									<td><%=oAll.getO().getOrdersDate()%></td>
									<td><%=oAll.getM().getMemberId()%> / <%=oAll.getM().getMemberNickname()%></td>
									<td><%=oAll.getP().getProductName()%></td>
									<td><%=oAll.getP().getProductPrice()%></td>
									<td><%=oAll.getO().getOrdersNum()%></td>
									<td><%=oAll.getP().getProductPrice() * oAll.getO().getOrdersNum() + 2500%>
										원</td>
									<%
										if (oAll.getO().getOrdersPay() == 'Y') {
									%>
									<td style="background-color: #6CC0FF;">결제 완료 <%
										} else if (oAll.getO().getOrdersPay() == 'N') {
									%>
									<td style="background-color: #FF6464;">미결제 <%
										}
									%>
									</td>
									<td>
										<%
											if (oAll.getO().getOrdersPay() == 'Y' && oAll.getO().getDeliveryYN() == 'N') {
										%>
										<form action="/deliverOrders.kh" method="post"
											id="delCommentsSubmit">
											<input type="hidden" name="orderNo"
												value="<%=oAll.getO().getOrdersNo()%>" /> <input
												class="deleteBtn" type="submit" value="상품 발송"
												id="orderDelBtn" />
										</form> <%
 	} else if (oAll.getO().getOrdersPay() == 'Y' && oAll.getO().getDeliveryYN() == 'Y') {
 %> 발송 완료 <%
 	}
 %>
									</td>
								</tr>
								<tr id="post_list">
									<td>배송지 정보</td>
									<td><%=oAll.getO().getReceiverName()%></td>
									<td><%=oAll.getO().getReceiverPhone()%></td>
									<td colspan="3"><%=oAll.getO().getReceiverAddress()%></td>
									<td colspan="2"><%=oAll.getO().getDemand()%></td>
									<td style="color: #0100FF;">
										<%
											if (oAll.getO().getOrdersPay() == 'Y' && oAll.getO().getDeliveryYN() == 'Y'
														&& oAll.getO().getReceiveYN() == 'N') {
										%> 배송 중 <%
											}
										%> <%
 	if (oAll.getO().getOrdersPay() == 'Y' && oAll.getO().getDeliveryYN() == 'Y'
 				&& oAll.getO().getReceiveYN() == 'Y') {
 %> 배송 완료 <%
 	}
 %>

									</td>
								</tr>

								<%
									}
								%>

								<tr>
									<td colspan="8" align="center"><%=pageNavi%></td>
								</tr>

							</table>

						</div>

					</div>


				</div>
			</div>
		</div>
		<div id="footer">
			<%@ include file="/views/section/footer/footer.jsp"%>
		</div>

	</div>


</body>
</html>