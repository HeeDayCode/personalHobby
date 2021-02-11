<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="kr.or.iei.product.model.vo.ProductAll" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="/resources/css/store/main/4_hot.css" rel="stylesheet" type="text/css" />
</head>
<body>
<%
	ArrayList<ProductAll> hotList = (ArrayList<ProductAll>) request.getAttribute("hotList");
	
	
	%>
	<div id="content">
		<div class="title">HOT</div>
		<center>
			<div id="hotBox">
<%
					for(ProductAll pAll : hotList){%>
				<div class="h_box">
					<div class="hotImg_box">
						<a href="/productList.kh?productNo=<%=pAll.getProduct().getProductNo()%>">
						
						<img src="/resources/images/product/<%=pAll.getImg().getChangedName() %>.png"
							class="h_img" onerror="javascript:this.src='/resources/images/store/product/none.png'"/>
<%--  <img src="/resources/product/<%=pAll.getImg().getChangedName()%>" class="h_img" />		--%>					
							
							</a>
					</div>
					<div class="hotText">
						<span><%=pAll.getProduct().getProductName()%></span><br>
						<span><%=pAll.getProduct().getProductPrice() %> 원</span>
					</div>
				</div>
				
<%} %>
			</div>
		</center>

	</div>
</body>
</html>