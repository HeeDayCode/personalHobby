<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="kr.or.iei.product.model.vo.ProductAll"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="/resources/css/store/main/5_best.css" rel="stylesheet"
	type="text/css" />
</head>
<body>
	<%
		ArrayList<ProductAll> bestList = (ArrayList<ProductAll>) request.getAttribute("bestList");
	%>
	<div id="content2">
		<div>
			<p id="bestTitle">BEST</p>
		</div>
		<center>


			<div id="carouselExampleControls2" class="carousel slide"
				data-bs-ride="carousel" data-bs-interval="false">

				<div class="carousel-inner box2">

					<div class="carousel-item active ">


						<div class="div_mid">
							
							
							<%
								for (int i = 0; i < 5; i++) {
							%>
							<%
								ProductAll pAll = bestList.get(i);
							%>
							<div class="md_box">
								<div class="img_box">
									<a href="/productList.kh?productNo=<%=pAll.getProduct().getProductNo()%>"> <img
										src="/resources/images/product/<%=pAll.getImg().getChangedName() %>.png" class="md_img"
										onerror="javascript:this.src='/resources/images/store/product/none.png'"/>

										<%--			<img src="/resources/product/<%=pAll.getImg().getChangedName()%>" class="md_img" />				--%>

									</a>
								</div>
								<div class="text_box">

									<span><%=pAll.getProduct().getProductName()%></span>
								</div>
							</div>

							<%
								}
							%>

						</div>

					</div>
					<div class="carousel-item">
					<div class="div_mid">
							
							
							<%
								for (int i = 0; i < 5; i++) {
							%>
							<%
								ProductAll pAll = bestList.get(i+5);
							%>
							<div class="md_box">
								<div class="img_box">
									<a href="/productList.kh?productNo=<%=pAll.getProduct().getProductNo()%>"> <img
										src="/resources/images/product/<%=pAll.getImg().getChangedName() %>.png" class="md_img" 
										onerror="javascript:this.src='/resources/images/store/product/none.png'"/>

										<%--			<img src="/resources/product/<%=pAll.getImg().getChangedName()%>" class="md_img" />				--%>

									</a>
								</div>
								<div class="text_box">

									<span><%=pAll.getProduct().getProductName()%></span>
								</div>
							</div>

							<%
								}
							%>

						</div></div>
					<div class="carousel-item"><div class="div_mid">
							
							
							<%
								for (int i = 0; i < 5; i++) {
							%>
							<%
								ProductAll pAll = bestList.get(i+10);
							%>
							<div class="md_box">
								<div class="img_box">
									<a href="/productList.kh?productNo=<%=pAll.getProduct().getProductNo()%>"> <img
										src="/resources/images/product/<%=pAll.getImg().getChangedName() %>.png" class="md_img" 
										onerror="javascript:this.src='/resources/images/store/product/none.png'"/>

										<%--			<img src="/resources/product/<%=pAll.getImg().getChangedName()%>" class="md_img" />				--%>

									</a>
								</div>
								<div class="text_box">

									<span><%=pAll.getProduct().getProductName()%></span>
								</div>
							</div>

							<%
								}
							%>

						</div></div>
						<div class="carousel-item"><div class="div_mid">
							
							
							<%
								for (int i = 0; i < 5; i++) {
							%>
							<%
								ProductAll pAll = bestList.get(i+15);
							%>
							<div class="md_box">
								<div class="img_box">
									<a href="/productList.kh?productNo=<%=pAll.getProduct().getProductNo()%>"> <img
										src="/resources/images/product/<%=pAll.getImg().getChangedName() %>.png" class="md_img" 
										onerror="javascript:this.src='/resources/images/store/product/none.png'"/>

										<%--			<img src="/resources/product/<%=pAll.getImg().getChangedName()%>" class="md_img" />				--%>

									</a>
								</div>
								<div class="text_box">

									<span><%=pAll.getProduct().getProductName()%></span>
								</div>
							</div>

							<%
								}
							%>

						</div></div>
						<div class="carousel-item"><div class="div_mid">
							
							
							<%
								for (int i = 0; i < 5; i++) {
							%>
							<%
								ProductAll pAll = bestList.get(i+20);
							%>
							<div class="md_box">
								<div class="img_box">
									<a href="/productList.kh?productNo=<%=pAll.getProduct().getProductNo()%>"> <img
										src="/resources/images/product/<%=pAll.getImg().getChangedName() %>.png" class="md_img" 
										onerror="javascript:this.src='/resources/images/store/product/none.png'"/>

										<%--			<img src="/resources/product/<%=pAll.getImg().getChangedName()%>" class="md_img" />				--%>

									</a>
								</div>
								<div class="text_box">

									<span><%=pAll.getProduct().getProductName()%></span>
								</div>
							</div>

							<%
								}
							%>

						</div></div>
				</div>

				<a class="carousel-control-prev" href="#carouselExampleControls2"
					role="button" data-bs-slide="prev"> <span
					class="carousel-control-prev-icon" aria-hidden="true"><img
						src="/resources/images/store/arrow_left.jpg" class="arrow"
						id="arrow_left" /></span> <span class="visually-hidden">Previous</span>
				</a> <a class="carousel-control-next" href="#carouselExampleControls2"
					role="button" data-bs-slide="next"> <span
					class="carousel-control-next-icon" aria-hidden="true"><img
						src="/resources/images/store/arrow_right.png" class="arrow"
						id="arrow_right" /></span> <span class="visually-hidden">Next</span>
				</a>
			</div>

		</center>
	</div>


</body>
</html>