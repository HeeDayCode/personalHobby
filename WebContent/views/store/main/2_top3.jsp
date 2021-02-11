<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="kr.or.iei.product.model.vo.ProductWrite"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="/resources/css/store/main/2_top3.css" rel="stylesheet"
	type="text/css" />

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
</head>
<body>
	<%
		ArrayList<ProductWrite> topList = (ArrayList<ProductWrite>) request.getAttribute("topList");
		ProductWrite pWrite = null;
	%>
	<div id="content">
		<div class="title">TOP 3</div>
		<center>

			<div id="carouselExampleFade" class="carousel slide carousel-fade"
				data-bs-ride="carousel" data-bs-interval="false">

				<div class="carousel-inner whole">

					<!-- num1 -->
					<div class="carousel-item active">

						<%
							pWrite = topList.get(0);
						%>
						<div class="top_img">
							<a href="/productList.kh?productNo=<%=pWrite.getProduct().getProductNo()%>"> <img
								src="/resources/images/product/<%=pWrite.getImg().getChangedName() %>.png" class="t_img" />

								<%--				<img src="/resources/product/<%=pWrite.getImg().getChangedName()%> "  class="t_img" /> --%>

							</a>

							<div class="c_content">
								<p class="hidden_text"><%=pWrite.getProduct().getProductName()%></p>
							</div>
						</div>

						<div class="top_text">

							<%
								for (int j = 0; j < 4; j++) {
							%>
							<a href="/detailPrint.kh?write_no=<%=pWrite.getWrite().getWriteNo()%>&productNo=<%=pWrite.getWrite().getProductNo()%>">
								<div>
									<div class="top_rank">
										<a href="/detailPrint.kh?write_no=<%=pWrite.getWrite().getWriteNo()%>&productNo=<%=pWrite.getWrite().getProductNo()%>"><span><%=j + 1%></span></a>
									</div>
									<div class="top_comment">
										<div class="top_review">
											<p>
												<a href="/detailPrint.kh?write_no=<%=pWrite.getWrite().getWriteNo()%>&productNo=<%=pWrite.getWrite().getProductNo()%>"> <%
 	pWrite = topList.get(0 + j);
 %> <%=pWrite.getWrite().getWriteExplain()%></a>
											</p>
										</div>
										<!-- 해시태그 for문 -->
										<a href="/detailPrint.kh?write_no=<%=pWrite.getWrite().getWriteNo()%>&productNo=<%=pWrite.getWrite().getProductNo()%>">
										<div class="top_tag">
											<p>
												<%
													for (int k = 0; k < pWrite.getWriteTagList().size(); k++) {
												%>#<%=pWrite.getWriteTagList().get(k).getTag()%>
												<%
													}
												%>
											</p>
										</div>
										</a>
										<!-- 해시태그 for문 -->

									</div>
									<div class="top_heart">
										<i class="fas fa-heart heart-icon"></i><span><%=pWrite.getWrite().getWriteLike()%></span>
									</div>
								</div>
							</a>
							<%
								}
							%>



						</div>
					</div>
					<!-- num2 -->
					<div class="carousel-item">


						<%
							pWrite = topList.get(4);
						%>
						<div class="top_img">
							<a href="/productList.kh?productNo=<%=pWrite.getProduct().getProductNo()%>"> <img
								src="/resources/images/product/<%=pWrite.getImg().getChangedName() %>.png" class="t_img" />

								<%--				<img src="/resources/product/<%=pWrite.getImg().getChangedName()%> "  class="t_img" /> --%>

							</a>

							<div class="c_content">
								<p class="hidden_text"><%=pWrite.getProduct().getProductName()%></p>
							</div>
						</div>

						<div class="top_text">

							<%
								for (int j = 0; j < 4; j++) {
							%>
							<a href="/detailPrint.kh?write_no=<%=pWrite.getWrite().getWriteNo()%>&productNo=<%=pWrite.getWrite().getProductNo()%>">
								<div>
									<div class="top_rank">
										<a href="/detailPrint.kh?write_no=<%=pWrite.getWrite().getWriteNo()%>&productNo=<%=pWrite.getWrite().getProductNo()%>"><span><%=j + 1%></span></a>
									</div>
									<div class="top_comment">
										<div class="top_review">
											<p>
												<a href="/detailPrint.kh?write_no=<%=pWrite.getWrite().getWriteNo()%>&productNo=<%=pWrite.getWrite().getProductNo()%>"> <%
 	pWrite = topList.get(4 + j);
 %> <%=pWrite.getWrite().getWriteExplain()%></a>
											</p>
										</div>
										<!-- 해시태그 for문 -->
										<a href="/detailPrint.kh?write_no=<%=pWrite.getWrite().getWriteNo()%>&productNo=<%=pWrite.getWrite().getProductNo()%>">
										<div class="top_tag">
											<p>
												<%
													for (int k = 0; k < pWrite.getWriteTagList().size(); k++) {
												%>#<%=pWrite.getWriteTagList().get(k).getTag()%>
												<%
													}
												%>
											</p>
										</div>
										</a>
										<!-- 해시태그 for문 -->

									</div>
									<div class="top_heart">
										<i class="fas fa-heart heart-icon"></i><span><%=pWrite.getWrite().getWriteLike()%></span>
									</div>
								</div> <%
 	}
 %>
							</a>
						</div>


					</div>
					<!-- num3 -->
					<div class="carousel-item">


						<%
							pWrite = topList.get(8);
						%>
						<div class="top_img">
							<a href="/productList.kh?productNo=<%=pWrite.getProduct().getProductNo()%>"> <img
								src="/resources/images/product/<%=pWrite.getImg().getChangedName() %>.png" class="t_img" />

								<%--				<img src="/resources/product/<%=pWrite.getImg().getChangedName()%> "  class="t_img" /> --%>

							</a>

							<div class="c_content">
								<p class="hidden_text"><%=pWrite.getProduct().getProductName()%></p>
							</div>
						</div>

						<div class="top_text">

							<%
								for (int j = 0; j < 4; j++) {
							%>
							<a href="/detailPrint.kh?write_no=<%=pWrite.getWrite().getWriteNo()%>&productNo=<%=pWrite.getWrite().getProductNo()%>">
								<div>
									<div class="top_rank">
										<a href="/detailPrint.kh?write_no=<%=pWrite.getWrite().getWriteNo()%>&productNo=<%=pWrite.getWrite().getProductNo()%>"><span><%=j + 1%></span></a>
									</div>
									<div class="top_comment">
										<div class="top_review">
											<p>
												<a href="/detailPrint.kh?write_no=<%=pWrite.getWrite().getWriteNo()%>&productNo=<%=pWrite.getWrite().getProductNo()%>"> <%
 	pWrite = topList.get(8 + j);
 %> <%=pWrite.getWrite().getWriteExplain()%></a>
											</p>
										</div>
										<!-- 해시태그 for문 -->
										<a href="/detailPrint.kh?write_no=<%=pWrite.getWrite().getWriteNo()%>&productNo=<%=pWrite.getWrite().getProductNo()%>">
										<div class="top_tag">
											<p>
												<%
													for (int k = 0; k < pWrite.getWriteTagList().size(); k++) {
												%>#<%=pWrite.getWriteTagList().get(k).getTag()%>
												<%
													}
												%>
											</p>
										</div>
										</a>
										<!-- 해시태그 for문 -->

									</div>
									<div class="top_heart">
										<i class="fas fa-heart heart-icon"></i><span><%=pWrite.getWrite().getWriteLike()%></span>
									</div>
								</div> <%
 	}
 %>
							</a>
						</div>


					</div>

				</div>

				<a class="carousel-control-prev" href="#carouselExampleFade"
					role="button" data-bs-slide="prev"> <span
					class="carousel-control-prev-icon" aria-hidden="true"><img
						src="/resources/images/store/arrow_left.jpg" class="arrow"
						id="arrow_left" /></span> <span class="visually-hidden">Previous</span>
				</a> <a class="carousel-control-next" href="#carouselExampleFade"
					role="button" data-bs-slide="next"> <span
					class="carousel-control-next-icon" aria-hidden="true"><img
						src="/resources/images/store/arrow_right.png" class="arrow"
						id="arrow_right" /></span> <span class="visually-hidden">Next</span>
				</a>

			</div>
		</center>
	</div>


	<script src="https://code.jquery.com/jquery-3.5.1.js"
		integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
		crossorigin="anonymous"></script>
	<script>
		$(function() {
			$('.t_img').hover(function() {
				$(this).css('opacity', '0.3');
				$('.hidden_text').css('display', 'block');
			}, function() {
				$(this).css('opacity', '1');
			});
		});
	</script>

</body>
</html>