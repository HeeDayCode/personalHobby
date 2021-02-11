<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="kr.or.iei.product.model.vo.Product" %>
<%@ page import="kr.or.iei.product.model.vo.ProductAll" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="/resources/css/store/category/2_product.css" rel="stylesheet"
	type="text/css" />

</head>
<body>

<% 
	ArrayList<ProductAll> list = (ArrayList<ProductAll>)request.getAttribute("list"); 	//페이지별 리스트
	ArrayList<ProductAll> allList = (ArrayList<ProductAll>)request.getAttribute("allList");  //전체출력리스트
	ArrayList<ProductAll> alignList1 = (ArrayList<ProductAll>)request.getAttribute("alignList1"); //가격높은순정렬
	ArrayList<ProductAll> alignList2 = (ArrayList<ProductAll>)request.getAttribute("alignList2");	//가격낮은순정렬
	ArrayList<ProductAll> alignList3 = (ArrayList<ProductAll>)request.getAttribute("alignList3");		//신규등록순정렬
	ArrayList<ProductAll> alignList4 = (ArrayList<ProductAll>)request.getAttribute("alignList4");		//인기순정렬
	
	
	
	int categoryNo = (int)request.getAttribute("categoryNo");	//카테고리 페이지넘버
	String value = (String)request.getAttribute("value");		//정렬할때 필요한값
%>
<div id="wrapper">
	<div id="header"> 
		<%@ include file="/views/section/header/headerShopPage.jsp"%>
	</div>
	<div id="navigator">
		<%@ include file="/views/section/navi/naviShop.jsp"%>
	</div>
	<div id="container">
	<%@ include file="/views/store/category/1_img.jsp"%>
	<%@ include file="/views/section/navi/subNavi.jsp"%>

	<div id="productBox">
	<% switch(categoryNo){ 
	  case 0: %> <!-- 전체출력일때 -->
		<%@ include file="/views/store/category/category_all.jsp"%>	 <!-- 카테고리페이지 이미지 -->
			<% for(ProductAll p : allList){ %>
			<div class="p_box">
				<div class="productImg_box">
					<a href="/productList.kh?productNo=<%=p.getProduct().getProductNo()%>">
					<img src="/resources/images/product/<%=p.getImg().getChangedName() %>.png"
						class="p_img" onerror="javascript:this.src='/resources/images/store/product/none.png'"/></a>
				</div>
				<div class="product_textBox">
					<span><%=p.getProduct().getProductName() %></span><br><span><%=p.getProduct().getProductPrice() %>원 </span>
				</div>
	
			</div>
		<%} %>	
	<% break;	
		  case 1: %>
		  <%@ include file="/views/store/category/category_1.jsp"%>
		<ul id="p-align">	
			<li><a href="/imgPrint.kh?category_no=<%=categoryNo%>&value=1">가격 높은순</a></li>
			<li><a href="/imgPrint.kh?category_no=<%=categoryNo%>&value=2">가격 낮은순</a></li>
			<li><a href="/imgPrint.kh?category_no=<%=categoryNo%>&value=3">신규 등록 순</a></li>
			<li><a href="/imgPrint.kh?category_no=<%=categoryNo%>&value=4">인기 순</a></li>
		</ul>
		<%-- 	<% for(ProductAll p : list){ %>
			<div class="p_box">
				<div class="productImg_box">
					<a href="/views/store/product/detail.jsp"><img src="/resources/images/community/index/7.jpg"
						class="p_img" /></a>
				</div>
				<div class="product_textBox">
					<span><%=p.getProduct().getProductName() %></span><br><span><%=p.getProduct().getProductPrice() %>원 </span>
				</div>
	
			</div>
		<%} %> 	 //정렬 하지 않았을때 기본 페이지 --%>
		<%if(value.equals("1")){ %>
			<% for(ProductAll p : alignList1){ %>
			<div class="p_box">
				<div class="productImg_box">
					<a href="/productList.kh?productNo=<%=p.getProduct().getProductNo()%>"><img src="/resources/images/product/<%=p.getImg().getChangedName() %>.png"
						class="p_img" onerror="javascript:this.src='/resources/images/store/product/none.png'"/></a>
				</div>
				<div class="product_textBox">
					<span><%=p.getProduct().getProductName() %></span><br><span><%=p.getProduct().getProductPrice() %>원 </span>
				</div>
	
			</div>
			<%}%> 			
		<%}else if(value.equals("2")){ %>
				<% for(ProductAll p : alignList2){ %>
			<div class="p_box">
				<div class="productImg_box">
					<a href="/productList.kh?productNo=<%=p.getProduct().getProductNo()%>"><img src="/resources/images/product/<%=p.getImg().getChangedName() %>.png"
						class="p_img" onerror="javascript:this.src='/resources/images/store/product/none.png'"/></a>
				</div>
				<div class="product_textBox">
					<span><%=p.getProduct().getProductName() %></span><br><span><%=p.getProduct().getProductPrice() %>원 </span>
				</div>
	
			</div>
			<%}%> 	
		<%}else if(value.equals("3")){ %>
				<% for(ProductAll p : alignList3){ %>
			<div class="p_box">
				<div class="productImg_box">
					<a href="/productList.kh?productNo=<%=p.getProduct().getProductNo()%>"><img src="/resources/images/product/<%=p.getImg().getChangedName() %>.png"
						class="p_img" onerror="javascript:this.src='/resources/images/store/product/none.png'"/></a>
				</div>
				<div class="product_textBox">
					<span><%=p.getProduct().getProductName() %></span><br><span><%=p.getProduct().getProductPrice() %>원 </span>
				</div>
	
			</div>
			<%}%> 	
		<%}else if(value.equals("4")){ %>
				<% for(ProductAll p : alignList4){ %>
			<div class="p_box">
				<div class="productImg_box">
					<a href="/productList.kh?productNo=<%=p.getProduct().getProductNo()%>"><img src="/resources/images/product/<%=p.getImg().getChangedName() %>.png"
						class="p_img" onerror="javascript:this.src='/resources/images/store/product/none.png'"/></a>
				</div>
				<div class="product_textBox">
					<span><%=p.getProduct().getProductName() %></span><br><span><%=p.getProduct().getProductPrice() %>원 </span>
				</div>
	
			</div>
			<%}%> 	
		<%} %>
	<% 	break;
	  case 2: %>
		<%@ include file="/views/store/category/category_2.jsp"%>
				<ul id="p-align">	
			<li><a href="/imgPrint.kh?category_no=<%=categoryNo%>&value=1">가격 높은순</a></li>
			<li><a href="/imgPrint.kh?category_no=<%=categoryNo%>&value=2">가격 낮은순</a></li>
			<li><a href="/imgPrint.kh?category_no=<%=categoryNo%>&value=3">신규 등록 순</a></li>
			<li><a href="/imgPrint.kh?category_no=<%=categoryNo%>&value=4">인기 순</a></li>
		</ul>
			<%if(value.equals("1")){ %>
			<% for(ProductAll p : alignList1){ %>
			<div class="p_box">
				<div class="productImg_box">
					<a href="/productList.kh?productNo=<%=p.getProduct().getProductNo()%>"><img src="/resources/images/product/<%=p.getImg().getChangedName() %>.png"
						class="p_img" onerror="javascript:this.src='/resources/images/store/product/none.png'"/></a>
				</div>
				<div class="product_textBox">
					<span><%=p.getProduct().getProductName() %></span><br><span><%=p.getProduct().getProductPrice() %>원 </span>
				</div>
	
			</div>
		<%}%> 			
				<%}else if(value.equals("2")){ %>
				<% for(ProductAll p : alignList2){ %>
			<div class="p_box">
				<div class="productImg_box">
					<a href="/productList.kh?productNo=<%=p.getProduct().getProductNo()%>"><img src="/resources/images/product/<%=p.getImg().getChangedName() %>.png"
						class="p_img" onerror="javascript:this.src='/resources/images/store/product/none.png'"/></a>
				</div>
				<div class="product_textBox">
					<span><%=p.getProduct().getProductName() %></span><br><span><%=p.getProduct().getProductPrice() %>원 </span>
				</div>
	
			</div>
			<%}%> 	
		<%}else if(value.equals("3")){ %>
				<% for(ProductAll p : alignList3){ %>
			<div class="p_box">
				<div class="productImg_box">
					<a href="/productList.kh?productNo=<%=p.getProduct().getProductNo()%>"><img src="/resources/images/product/<%=p.getImg().getChangedName() %>.png"
						class="p_img" onerror="javascript:this.src='/resources/images/store/product/none.png'"/></a>
				</div>
				<div class="product_textBox">
					<span><%=p.getProduct().getProductName() %></span><br><span><%=p.getProduct().getProductPrice() %>원 </span>
				</div>
	
			</div>
			<%}%> 	
		<%}else if(value.equals("4")){ %>
				<% for(ProductAll p : alignList4){ %>
			<div class="p_box">
				<div class="productImg_box">
					<a href="/productList.kh?productNo=<%=p.getProduct().getProductNo()%>"><img src="/resources/images/product/<%=p.getImg().getChangedName() %>.png"
						class="p_img" onerror="javascript:this.src='/resources/images/store/product/none.png'" /></a>
				</div>
				<div class="product_textBox">
					<span><%=p.getProduct().getProductName() %></span><br><span><%=p.getProduct().getProductPrice() %>원 </span>
				</div>
	
			</div>
			<%}%> 	
		<%} %>
		<% 	break;
	  case 3: %>
		<%@ include file="/views/store/category/category_3.jsp"%>
				<ul id="p-align">	
			<li><a href="/imgPrint.kh?category_no=<%=categoryNo%>&value=1">가격 높은순</a></li>
			<li><a href="/imgPrint.kh?category_no=<%=categoryNo%>&value=2">가격 낮은순</a></li>
			<li><a href="/imgPrint.kh?category_no=<%=categoryNo%>&value=3">신규 등록 순</a></li>
			<li><a href="/imgPrint.kh?category_no=<%=categoryNo%>&value=4">인기 순</a></li>
		</ul>
			<%if(value.equals("1")){ %>
			<% for(ProductAll p : alignList1){ %>
			<div class="p_box">
				<div class="productImg_box">
					<a href="/productList.kh?productNo=<%=p.getProduct().getProductNo()%>"><img src="/resources/images/product/<%=p.getImg().getChangedName() %>.png"
						class="p_img" onerror="javascript:this.src='/resources/images/store/product/none.png'"/></a>
				</div>
				<div class="product_textBox">
					<span><%=p.getProduct().getProductName() %></span><br><span><%=p.getProduct().getProductPrice() %>원 </span>
				</div>
	
			</div>
		<%}%> 			
				<%}else if(value.equals("2")){ %>
				<% for(ProductAll p : alignList2){ %>
			<div class="p_box">
				<div class="productImg_box">
					<a href="/productList.kh?productNo=<%=p.getProduct().getProductNo()%>"><img src="/resources/images/product/<%=p.getImg().getChangedName() %>.png"
						class="p_img" onerror="javascript:this.src='/resources/images/store/product/none.png'"/></a>
				</div>
				<div class="product_textBox">
					<span><%=p.getProduct().getProductName() %></span><br><span><%=p.getProduct().getProductPrice() %>원 </span>
				</div>
	
			</div>
			<%}%> 	
		<%}else if(value.equals("3")){ %>
				<% for(ProductAll p : alignList3){ %>
			<div class="p_box">
				<div class="productImg_box">
					<a href="/productList.kh?productNo=<%=p.getProduct().getProductNo()%>"><img src="/resources/images/product/<%=p.getImg().getChangedName() %>.png"
						class="p_img" onerror="javascript:this.src='/resources/images/store/product/none.png'"/></a>
				</div>
				<div class="product_textBox">
					<span><%=p.getProduct().getProductName() %></span><br><span><%=p.getProduct().getProductPrice() %>원 </span>
				</div>
	
			</div>
			<%}%> 	
		<%}else if(value.equals("4")){ %>
				<% for(ProductAll p : alignList4){ %>
			<div class="p_box">
				<div class="productImg_box">
					<a href="/productList.kh?productNo=<%=p.getProduct().getProductNo()%>"><img src="/resources/images/product/<%=p.getImg().getChangedName() %>.png"
						class="p_img" onerror="javascript:this.src='/resources/images/store/product/none.png'"/></a>
				</div>
				<div class="product_textBox">
					<span><%=p.getProduct().getProductName() %></span><br><span><%=p.getProduct().getProductPrice() %>원 </span>
				</div>
	
			</div>
			<%}%> 	
		<%} %>
	<% 	break;
	  case 4: %>
		<%@ include file="/views/store/category/category_4.jsp"%>
				<ul id="p-align">	
			<li><a href="/imgPrint.kh?category_no=<%=categoryNo%>&value=1">가격 높은순</a></li>
			<li><a href="/imgPrint.kh?category_no=<%=categoryNo%>&value=2">가격 낮은순</a></li>
			<li><a href="/imgPrint.kh?category_no=<%=categoryNo%>&value=3">신규 등록 순</a></li>
			<li><a href="/imgPrint.kh?category_no=<%=categoryNo%>&value=4">인기 순</a></li>
		</ul>
			<%if(value.equals("1")){ %>
			<% for(ProductAll p : alignList1){ %>
			<div class="p_box">
				<div class="productImg_box">
					<a href="/productList.kh?productNo=<%=p.getProduct().getProductNo()%>"><img src="/resources/images/product/<%=p.getImg().getChangedName() %>.png"
						class="p_img" onerror="javascript:this.src='/resources/images/store/product/none.png'"/></a>
				</div>
				<div class="product_textBox">
					<span><%=p.getProduct().getProductName() %></span><br><span><%=p.getProduct().getProductPrice() %>원 </span>
				</div>
	
			</div>
		<%}%> 			
				<%}else if(value.equals("2")){ %>
				<% for(ProductAll p : alignList2){ %>
			<div class="p_box">
				<div class="productImg_box">
					<a href="/productList.kh?productNo=<%=p.getProduct().getProductNo()%>"><img src="/resources/images/product/<%=p.getImg().getChangedName() %>.png"
						class="p_img" onerror="javascript:this.src='/resources/images/store/product/none.png'"/></a>
				</div>
				<div class="product_textBox">
					<span><%=p.getProduct().getProductName() %></span><br><span><%=p.getProduct().getProductPrice() %>원 </span>
				</div>
	
			</div>
			<%}%> 	
		<%}else if(value.equals("3")){ %>
				<% for(ProductAll p : alignList3){ %>
			<div class="p_box">
				<div class="productImg_box">
					<a href="/productList.kh?productNo=<%=p.getProduct().getProductNo()%>"><img src="/resources/images/product/<%=p.getImg().getChangedName() %>.png"
						class="p_img" onerror="javascript:this.src='/resources/images/store/product/none.png'"/></a>
				</div>
				<div class="product_textBox">
					<span><%=p.getProduct().getProductName() %></span><br><span><%=p.getProduct().getProductPrice() %>원 </span>
				</div>
	
			</div>
			<%}%> 	
		<%}else if(value.equals("4")){ %>
				<% for(ProductAll p : alignList4){ %>
			<div class="p_box">
				<div class="productImg_box">
					<a href="/productList.kh?productNo=<%=p.getProduct().getProductNo()%>"><img src="/resources/images/product/<%=p.getImg().getChangedName() %>.png"
						class="p_img" onerror="javascript:this.src='/resources/images/store/product/none.png'"/></a>
				</div>
				<div class="product_textBox">
					<span><%=p.getProduct().getProductName() %></span><br><span><%=p.getProduct().getProductPrice() %>원 </span>
				</div>
	
			</div>
			<%}%> 	
		<%} %>
	<% 	break;
	  case 5: %>
		<%@ include file="/views/store/category/category_5.jsp"%>
				<ul id="p-align">	
			<li><a href="/imgPrint.kh?category_no=<%=categoryNo%>&value=1">가격 높은순</a></li>
			<li><a href="/imgPrint.kh?category_no=<%=categoryNo%>&value=2">가격 낮은순</a></li>
			<li><a href="/imgPrint.kh?category_no=<%=categoryNo%>&value=3">신규 등록 순</a></li>
			<li><a href="/imgPrint.kh?category_no=<%=categoryNo%>&value=4">인기 순</a></li>
		</ul>
			<%if(value.equals("1")){ %>
			<% for(ProductAll p : alignList1){ %>
			<div class="p_box">
				<div class="productImg_box">
					<a href="/productList.kh?productNo=<%=p.getProduct().getProductNo()%>"><img src="/resources/images/product/<%=p.getImg().getChangedName() %>.png"
						class="p_img" onerror="javascript:this.src='/resources/images/store/product/none.png'"/></a>
				</div>
				<div class="product_textBox">
					<span><%=p.getProduct().getProductName() %></span><br><span><%=p.getProduct().getProductPrice() %>원 </span>
				</div>
	
			</div>
		<%}%> 			
				<%}else if(value.equals("2")){ %>
				<% for(ProductAll p : alignList2){ %>
			<div class="p_box">
				<div class="productImg_box">
					<a href="/productList.kh?productNo=<%=p.getProduct().getProductNo()%>"><img src="/resources/images/product/<%=p.getImg().getChangedName() %>.png"
						class="p_img" onerror="javascript:this.src='/resources/images/store/product/none.png'" /></a>
				</div>
				<div class="product_textBox">
					<span><%=p.getProduct().getProductName() %></span><br><span><%=p.getProduct().getProductPrice() %>원 </span>
				</div>
	
			</div>
			<%}%> 	
		<%}else if(value.equals("3")){ %>
				<% for(ProductAll p : alignList3){ %>
			<div class="p_box">
				<div class="productImg_box">
					<a href="/productList.kh?productNo=<%=p.getProduct().getProductNo()%>"><img src="/resources/images/product/<%=p.getImg().getChangedName() %>.png"
						class="p_img" onerror="javascript:this.src='/resources/images/store/product/none.png'"/></a>
				</div>
				<div class="product_textBox">
					<span><%=p.getProduct().getProductName() %></span><br><span><%=p.getProduct().getProductPrice() %>원 </span>
				</div>
	
			</div>
			<%}%> 	
		<%}else if(value.equals("4")){ %>
				<% for(ProductAll p : alignList4){ %>
			<div class="p_box">
				<div class="productImg_box">
					<a href="/productList.kh?productNo=<%=p.getProduct().getProductNo()%>"><img src="/resources/images/product/<%=p.getImg().getChangedName() %>.png"
						class="p_img" onerror="javascript:this.src='/resources/images/store/product/none.png'"/></a>
				</div>
				<div class="product_textBox">
					<span><%=p.getProduct().getProductName() %></span><br><span><%=p.getProduct().getProductPrice() %>원 </span>
				</div>
	
			</div>
			<%}%> 	
		<%} %>
	<% 	break;
	  case 6: %>
		<%@ include file="/views/store/category/category_6.jsp"%>
				<ul id="p-align">	
			<li><a href="/imgPrint.kh?category_no=<%=categoryNo%>&value=1">가격 높은순</a></li>
			<li><a href="/imgPrint.kh?category_no=<%=categoryNo%>&value=2">가격 낮은순</a></li>
			<li><a href="/imgPrint.kh?category_no=<%=categoryNo%>&value=3">신규 등록 순</a></li>
			<li><a href="/imgPrint.kh?category_no=<%=categoryNo%>&value=4">인기 순</a></li>
		</ul>
			<%if(value.equals("1")){ %>
			<% for(ProductAll p : alignList1){ %>
			<div class="p_box">
				<div class="productImg_box">
					<a href="/productList.kh?productNo=<%=p.getProduct().getProductNo()%>"><img src="/resources/images/product/<%=p.getImg().getChangedName() %>.png"
						class="p_img" onerror="javascript:this.src='/resources/images/store/product/none.png'"/></a>
				</div>
				<div class="product_textBox">
					<span><%=p.getProduct().getProductName() %></span><br><span><%=p.getProduct().getProductPrice() %>원 </span>
				</div>
	
			</div>
		<%}%> 			
				<%}else if(value.equals("2")){ %>
				<% for(ProductAll p : alignList2){ %>
			<div class="p_box">
				<div class="productImg_box">
					<a href="/productList.kh?productNo=<%=p.getProduct().getProductNo()%>"><img src="/resources/images/product/<%=p.getImg().getChangedName() %>.png"
						class="p_img" onerror="javascript:this.src='/resources/images/store/product/none.png'"/></a>
				</div>
				<div class="product_textBox">
					<span><%=p.getProduct().getProductName() %></span><br><span><%=p.getProduct().getProductPrice() %>원 </span>
				</div>
	
			</div>
			<%}%> 	
		<%}else if(value.equals("3")){ %>
				<% for(ProductAll p : alignList3){ %>
			<div class="p_box">
				<div class="productImg_box">
					<a href="/productList.kh?productNo=<%=p.getProduct().getProductNo()%>"><img src="/resources/images/product/<%=p.getImg().getChangedName() %>.png"
						class="p_img" onerror="javascript:this.src='/resources/images/store/product/none.png'"/></a>
				</div>
				<div class="product_textBox">
					<span><%=p.getProduct().getProductName() %></span><br><span><%=p.getProduct().getProductPrice() %>원 </span>
				</div>
	
			</div>
			<%}%> 	
		<%}else if(value.equals("4")){ %>
				<% for(ProductAll p : alignList4){ %>
			<div class="p_box">
				<div class="productImg_box">
					<a href="/productList.kh?productNo=<%=p.getProduct().getProductNo()%>"><img src="/resources/images/product/<%=p.getImg().getChangedName() %>.png"
						class="p_img" onerror="javascript:this.src='/resources/images/store/product/none.png'"/></a>
				</div>
				<div class="product_textBox">
					<span><%=p.getProduct().getProductName() %></span><br><span><%=p.getProduct().getProductPrice() %>원 </span>
				</div>
	
			</div>
			<%}%> 	
		<%} %>
	<% 	break;
	  case 7: %>
		<%@ include file="/views/store/category/category_7.jsp"%>
				<ul id="p-align">	
			<li><a href="/imgPrint.kh?category_no=<%=categoryNo%>&value=1">가격 높은순</a></li>
			<li><a href="/imgPrint.kh?category_no=<%=categoryNo%>&value=2">가격 낮은순</a></li>
			<li><a href="/imgPrint.kh?category_no=<%=categoryNo%>&value=3">신규 등록 순</a></li>
			<li><a href="/imgPrint.kh?category_no=<%=categoryNo%>&value=4">인기 순</a></li>
		</ul>
			<%if(value.equals("1")){ %>
			<% for(ProductAll p : alignList1){ %>
			<div class="p_box">
				<div class="productImg_box">
					<a href="/productList.kh?productNo=<%=p.getProduct().getProductNo()%>"><img src="/resources/images/product/<%=p.getImg().getChangedName() %>.png"
						class="p_img" onerror="javascript:this.src='/resources/images/store/product/none.png'"/></a>
				</div>
				<div class="product_textBox">
					<span><%=p.getProduct().getProductName() %></span><br><span><%=p.getProduct().getProductPrice() %>원 </span>
				</div>
	
			</div>
		<%}%> 			
		<%}else if(value.equals("2")){ %>
				<% for(ProductAll p : alignList2){ %>
			<div class="p_box">
				<div class="productImg_box">
					<a href="/productList.kh?productNo=<%=p.getProduct().getProductNo()%>"><img src="/resources/images/product/<%=p.getImg().getChangedName() %>.png"
						class="p_img" onerror="javascript:this.src='/resources/images/store/product/none.png'"/></a>
				</div>
				<div class="product_textBox">
					<span><%=p.getProduct().getProductName() %></span><br><span><%=p.getProduct().getProductPrice() %>원 </span>
				</div>
	
			</div>
			<%}%> 	
		<%}else if(value.equals("3")){ %>
				<% for(ProductAll p : alignList3){ %>
			<div class="p_box">
				<div class="productImg_box">
					<a href="/productList.kh?productNo=<%=p.getProduct().getProductNo()%>"><img src="/resources/images/product/<%=p.getImg().getChangedName() %>.png"
						class="p_img" onerror="javascript:this.src='/resources/images/store/product/none.png'"/></a>
				</div>
				<div class="product_textBox">
					<span><%=p.getProduct().getProductName() %></span><br><span><%=p.getProduct().getProductPrice() %>원 </span>
				</div>
	
			</div>
			<%}%> 	
		<%}else if(value.equals("4")){ %>
				<% for(ProductAll p : alignList4){ %>
			<div class="p_box">
				<div class="productImg_box">
					<a href="/productList.kh?productNo=<%=p.getProduct().getProductNo()%>"><img src="/resources/images/product/<%=p.getImg().getChangedName() %>.png"
						class="p_img" onerror="javascript:this.src='/resources/images/store/product/none.png'"/></a>
				</div>
				<div class="product_textBox">
					<span><%=p.getProduct().getProductName() %></span><br><span><%=p.getProduct().getProductPrice() %>원 </span>
				</div>
	
			</div>
			<%}%> 	
		<%} %>
	<%} %>
		</div>

		<div id="index_btn">
			<center>
			<div class="btn-group" role="group" aria-label="Basic example">
				<button type="button" class="btn btn-outline-dark"><</button>
				<button type="button" class="btn btn-outline-dark">1</button>
				<button type="button" class="btn btn-outline-dark">2</button>
				<button type="button" class="btn btn-outline-dark">3</button>
				<button type="button" class="btn btn-outline-dark">4</button>
				<button type="button" class="btn btn-outline-dark">5</button>
				<button type="button" class="btn btn-outline-dark">></button>
			</div>
			</center>
		</div>	
	</div>
	<div> 
		<%@ include file="/views/section/footer/footer.jsp"%>
	</div>
</div>
	  

<%-- <%@ include file="/views/store/category/category_3.jsp"%>		  
<% 	break; 
	  case 4: %>
<%@ include file="/views/store/category/category_4.jsp"%>	  
<%	break;
	  case 5: %>
<%@ include file="/views/store/category/category_5.jsp"%>	
<% 	break;
	case 6: %>
<%@ include file="/views/store/category/category_6.jsp"%>		
<% 	break;
	case 7: %>
<%@ include file="/views/store/category/category_7.jsp"%>
<% break; 
	default: %>
	
 --%>
	
</body>
</html>