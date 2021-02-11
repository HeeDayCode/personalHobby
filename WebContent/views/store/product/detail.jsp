<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.or.iei.product.model.vo.*" %>
<%@ page import="kr.or.iei.img.model.vo.Img" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="kr.or.iei.question.model.vo.Board" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="/resources/css/store/product/detail.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
ProductAllSub product =(ProductAllSub)request.getAttribute("product");			
	
	ArrayList<Board> qList= new ArrayList<Board>();
	if(request.getAttribute("qList")!=null){
		qList = (ArrayList<Board>)request.getAttribute("qList");
		
	}	
%>

	<div id="header">
		<%-- <%@ include file="/views/section/header.jsp" %> --%>
		<%@ include file="/views/section/header/headerShopPage.jsp"%>
	</div>
	<div id="navigator">
		<%-- <%@ include file="/views/section/navigator.jsp" %>		 --%>	
		<%@ include file="/views/section/navi/naviShop.jsp"%>
		
	</div>
	<div id="wrapper">
<% 
    int categoryNo = product.getCategory().getCategoryNo();
	String categoryFullName= "";
    switch(product.getCategory().getCategoryNo()) {
    case 1: categoryFullName= "실과바늘";break;
    case 2: categoryFullName= "미술키트";break;
    case 3:categoryFullName= "라이프/소품키트";break;
    case 4:categoryFullName= "베이킹/데코키트";break;
    case 5:categoryFullName= "가죽공예";break;
    case 6:categoryFullName= "비즈공예";break;
    case 7:categoryFullName= "퍼즐/조립/브릭";break;
    default: break;
}
%>
	
	
	<div id="menu-info">
		<p><a href="/views/store/category/category_all.jsp">카테고리</a></p><p> > </p>
		<p><a href="/views/store/category/category_<%=product.getCategory().getCategoryNo()%>.jsp"><%=categoryFullName%></a></p>
	</div>
	
<br>
	<div id="contents">
		<div id="sale-product">
			<div id="sale-pic">
					<img src="/resources/images/product/<%=product.getImg().getChangedName()%>.png"  width=100% height=100% 
                     onerror="javascript:this.src='/resources/images/store/product/none.png'"/> 
			</div>
			<div id="sale-info">
				<div id="sale-title"><%=product.getProduct().getProductName()%></div>
				<div id="sale-price"><%=product.getProduct().getProductPrice()%>원</div>
				<div id="sale-delivery">
					<div id="delivery-title">배송</div>
					<div id="delivery-price">택배<br>2,500원<br>추가배송비 3,000원(지역별)</div>
				</div>
				<% 
	 	 			String [] pOptionsValues = product.getpOption().getOptions().split(",");
	 			%>

				<div id="sale-option">
				<select align="right" id="optSelect">
                  <%for (int i = 0; i < pOptionsValues.length; i++) {%>
                  <option><%=pOptionsValues[i]%></option>
                  <%}%>
               </select>
				</div>
							
				<div id="sale-count">
					<div id="count-title">
						수량 
					</div>
					<input class="countSet" id="count1" type="text" value="0" size="3"/><input class="countSet1" id="countPlus1"  type="button" value=" + "/><input class="countSet1" id="countMinus1" type="button" value=" - "/> 
				
				</div>
				<script>
				$(function() {				
					/* 개수 조정 및 총액 계산 스크립트 */
					$('#countPlus1').click(function() {
						var count = $('#count1').val();						
							count++;
							$('#count1').val(count);
							totalPrice=count*(<%=product.getProduct().getProductPrice()%>);
							$('#totalPrice').text(totalPrice);
										
						});
					$('#countMinus1').click(function() {
						var count = $('#count1').val();						
						if(count==0){
							$('#count1').val(0);
							totalPrice=0;
						}else{
							$('#count1').val(count-1);
							totalPrice=(count-1)*(<%=product.getProduct().getProductPrice()%>);
							$('#totalPrice').text(totalPrice);
						}						
					});
					
					/* 결제 관련 부분 */
					
					// 셀렉트 옵션 중 선택된 값 가져오기
		               $("#payBtn").click(function(){
		                    var check=$("#optSelect option:selected").html();
		                    $("input[name=selectedOpt]").val(check);
		                    $("input[name=totalPrice]").val(totalPrice);
		                    $("#goToPay").submit();
		               });
					
					
					
				});				
				
			</script>			
				<div id="total">
					<div id="total-title">
						총 상품금액
					</div>
					<div id="total-price">
						<span id="totalPrice">0</span>원
					</div>
				</div>
				<div id="pay">
				<form action="/selectOneProduct.kh" method="post" id="goToPay">
                  <button type="button" id="basketBtn" style="height: 70px;">장바구니</button><button id="payBtn" style="height: 70px;">결제하기</button>
                  <input type="hidden" name="selectedOpt" value=""/>
                  <input type="hidden" name="totalPrice" value=""/>
                  <input type="hidden" name="productNo" value="<%=product.getProduct().getProductNo() %>"/>
               </form>
				</div>										
			</div>
		</div>
		<div id="sale-menu">
			<button><a href="#target">상세설명</a></button>
			<button><a href="#target2">상품후기</a></button>
			<button><a href="#target3">상품문의</a></button>
			<button><a href="#target4">교환/반품/배송</a></button>
		</div>
		<a name="target"> </a>
		<div id="sale-detail">
		
		
		
			<img src="/resources/images/product/<%=product.getImg_sub().getChangedName()%>.png"  width=100% height=100% 
                onerror="javascript:this.src='/resources/images/store/product/error.PNG'"/>  
                
		</div>
	</div>

	<a name="target3"> </a>
	<div id="qna">
		<div id="qna-title">상품 문의 </div>
		<div id="qna-contents">
			<!-- <div id="qna-search">
				<form action="#" method="get">
						<input type="text" name="keyword" placeholder="제목, 내용, 상품명, 상품설명, 작성자를 검색하세요."/>
						<input type="submit" value="검색" name="searchBtn"/>
				</form>
			</div> -->
			<div id="qna-board">
				<table>
					<tr class="qna-board">
						<th>번호</th>
						<th>상품명</th>
						<th>문의 게시글 제목</th>
						<th>작성자</th>
						<th>조회수</th>
						<th>등록일</th>
					</tr>
			<%if(qList.isEmpty()) {%>
            <tr>
            	<td colspan="6"><span style="font-weight: 700; text-align: center;">해당 문의글이 없습니다.</span></td>
            </tr>
            <%}else { %>
			<%
               for(Board qBoard : qList){
            %>           
            <tr> 
               <td align="center"><%=qBoard.getboardNo() %></td>             
               <td><%=qBoard.getProductName() %></td>                            
               <td><a style="color:black;" href="/boardPostClick.kh?boardNo=<%=qBoard.getboardNo()%>&boardCategoryName=<%=qBoard.getboardCategoryName()%>"><%=qBoard.getboardName()%></a></td>
               <td><%=qBoard.getuserNickname() %></td>
               <td><%=qBoard.getboardViewCount() %></td>
               <td><%=qBoard.getboardDate() %></td>
            </tr>
           <%} %>
           <%} %>
				</table>
			</div>
			<div id="qna-write">
				<form>
					<input type="button" class="button" value="전체문의" onclick="window.location='/question.kh'"/>
					
					<% if(mAll==null) { %>
									
					<%}else{ %>
					<input type="button" class="button" value="글쓰기" 
					onclick="window.location='/views/store/product/question/qnaWrite.jsp?productNo=<%=product.getProduct().getProductNo()%>'" name="writeBtn"/>
						<!-- <button onclick="window.location='/views/store/question/qnaWrite.jsp'">글쓰기</button> -->
					<%} %>
				
				
				
				</form>					
			</div>
		</div>
	</div>
	<a name="target4"> </a>
	<div id="shop-guide">
		<div id="guide-title">교환/반품/배송</div>
		<div id="guide-contents">
			<center>
				<table>
				<tr>
					<td style="border:1px solid gray" width="100px">배송정보 </td>
					<td>- 배송은 결제일로부터 3일 이내 발송합니다. (단, 주말 및 공휴일은 배송일에서 제외됩니다.)<br>
					- 주문 제작의 상품의 경우 상품에 따라 배송 기간이 상이할 수 있습니다.<br>
					- 각 공급사마다 추가 배송비가 부과될 수 있으며, 배송비 부과 기준에 따라 별도의 배송비가 책정될 수도 있습니다. (일부 도서·산간 지역의 경우 추가 배송비가 발생할 수 있습니다.)<br>
					- 직접 수령은 불가능하며 온라인 주문/배송만 가능합니다.<br>
					- 공급사의 배송 실수나 착오로 인한 반품 배송 비용은 공급사에서 부담합니다.<br>
					- 상품의 재고 상황에 따라 배송일이 다소 지연되거나 품절 취소 될 수도 있으니, 이 점 양해하여 주시기 바랍니다.</td>
				</tr>
				<tr>
					<td>교환/반품 </td>
					<td style="border:1px solid gray">- 교환/반품/취소/환불 신청은 배송완료 후 7일 이내 가능합니다. (단, 일부 제품의 경우 신청이 제한될 수 있습니다.)<br>
					- 환불 승인 후 영업일 기준 3~7일 이내 환불 및 취소 여부 확인 가능합니다.<br>
					- 상품 불량인 경우는 배송비를 포함한 전액이 환불됩니다.<br>
					- 상품가치가 훼손된 경우 반품/환불이 불가합니다. (제품포장 개봉 및 주문제작상품/밀봉포장상품 및 스티커 부착 상품/식품 등)<br>
					- 출고 이후 반품/환불 요청 시 상품 회수 후 처리됩니다.<br>
					- 상품 페이지에 교환/환불/AS에 대한 브랜드 개별 기준이 있는 경우에는 해당 내용이 우선 적용 됩니다.</td>
				</tr>
			</table>
			</center>
		</div>
	</div>
	<a name="target2"> </a> 
	 <div id="shop-community">
		<div id="community-title">같은 상품 커뮤니티 구경하기</div>
		<div id="community-contents">
		<%@ include file="/views/store/product/relationProduct.jsp" %> 
			
		</div>
	</div> 
	<div id="backtotop">
			<img src="/resources/images/community/detail/up-arrow.png">
	</div>
	<script type="text/javascript">
			$(function() {
				$(window).scroll(function() {
					if ($(this).scrollTop() != 0) {
						$('#backtotop').fadeIn();
					} else {
						$('#backtotop').fadeOut();
					}
				});

				$('#backtotop').click(function() {
					$('body,html').animate({scrollTop : 0}, 100);
				});				
			});
		</script>
	</div>
	<div id="footer">
		<%@ include file="/views/section/footer/footer.jsp" %>
	</div>

</body>
</html>