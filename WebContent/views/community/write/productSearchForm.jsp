<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.or.iei.product.model.vo.ProductPageData" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="kr.or.iei.product.model.vo.ProductAll" %>
<%@ page import="kr.or.iei.product.model.vo.Product" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
<title>Insert title here</title>
<style>
	#inputProductName{
		width: 200px;
		height: 30px;
	}
	.functionBtn{
		width : 50px;
		height: 34px;
		background-color: #A978FE;
		color : white;
		border-radius: 8px;
		border-color : #A978FE;
	}
	
	#searchListBox{
		border : 1px solid black;
		width:1000px; height:620px;
		overflow:auto;
		text-align:center;
	}
	tr{
		width : 100%;
		height : 50px;
	}
		
</style>

</head>
<body>

<%
	ProductPageData ppd = (ProductPageData)request.getAttribute("pageData");
	String keyword = (String)request.getAttribute("keyword");
 	ArrayList<ProductAll> list = ppd.getList();
 	String pageNavi = ppd.getPageNavi();
%>


<center>
<h3>상품 검색</h3>
<form action="/productSearchList.kh" method="get">
		<input type="text" name="keyword" id="inputProductName" placeholder="상품명을 입력하세요."/>
		<input type="submit" class="functionBtn" value="검색" id="productSearchBtn" />
		<br><br>
 </form> 
 		
		
	<div id="searchListBox" style="border-color:white;">
		<table border="1px" cellpadding="0" cellspacing="0" width="100%">
			<tr style="background-color:#A978FE; color:white;">
				<th style="width:70px;">선택</th>
				<th style="width:70px;">상품번호</th>
				<th>상품이미지</th>
				<th>카테고리</th>
				<th>상품명</th>
				<th>상품설명</th>
				<th>상품가격</th>
				
			</tr>

			<% for(ProductAll productAll : list) {%>
			
			<tr>
				<td><input type="button" value="선택" name="selectBtn" class="selectBtn" onclick="WinClose();"/></td>

				<td id="productNo"><%=productAll.getProduct().getProductNo()%></td>
				<td id="imgChangedName">
					<div>
						<img width="70px"; src="/resources/images/community/write/<%=productAll.getImg().getOriginalName() %>"
                  onerror="javascript:this.src='/resources/images/store/product/none.png'"
                  />
					</div>
				</td>
				<td> <%=productAll.getCategory().getCategoryName() %></td>
				<td id="productName"><%=productAll.getProduct().getProductName()%></td>
				<td><%=productAll.getProduct().getProductText() %></td>
				<td><%=productAll.getProduct().getProductPrice() %> 원</td>
			</tr>
		<% } %> 
			
			<tr>
				<td colspan="7" ><%= pageNavi %></td>
			</tr>
		</table>
	</div>
	<br>
	
		<input type="submit" class="functionBtn" id="cancelBtn" onclick="WinClose();" value="취소"/>
	
</center>
	
	<script>
		$('.selectBtn').click(function(){
			// test는 그냥 연습용 임시 변수고
			// 이 부분 찾아가는건 아시겟져
			// 얘는 선택자인데 그냥 적으면 이 페이지의 선택자고 이걸 같이 적어주면 자기 오프너에 잇는 선택자 이 창을 열어준 부모창이
			// 쟤니깐요 부모창의 선택자 거기에 value값을 이걸 넣은거고
			// 히든으로 또 딴거도 넘기시려면 마찬가지로 변수만들고
			// 변수에다가 넘길 값, 그러니까 히든 안에 val()을 가져와서 넘겨주고 아래코드처럼 부모창 선택자 지정해서 val(넣을값) 하시면 될거같습니다
			var productName= $(this).parents().children('#productName').html();
			var productNo = $(this).parents().children('#productNo').html();
			var imgChangedName = $(this).parents().children('#imgChangedName').html();
			
			
			$("#selectProduct", parent.opener.document).val(productNo);
			$("#selectName", parent.opener.document).val(productName);
			
			// 확인 코드
			//alert(productNo);
			//alert(imgChangedName);
			
			
		});
	</script>
	
	<script>
			/*  창닫기 기능 */ 
			function WinClose()
			 {
			   window.open('','_self').close();     
			 }
	</script>

	
</body>
</html>























