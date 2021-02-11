<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="kr.or.iei.product.model.vo.*"%>
<%@ page import="kr.or.iei.product.model.vo.ProductWrite"%>
<%@ page import="kr.or.iei.img.model.vo.Img" %>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="/resources/css/store/product/relationProduct.css" rel="stylesheet" type="text/css" />
</head>
<body>
<%
	ArrayList<ProductWrite> pcList = (ArrayList<ProductWrite>)request.getAttribute("pcList");		
%>

	<div id="content">
		<center>
		<%if(pcList.isEmpty()) {%>
		<br><br>
		<span style="font-weight: 700">해당 게시글이 없습니다.</span>
		<%}else{ %> 
		<div id="contentArray">
		<div id="box">				
				<div id="div_mid" style="float:left ">
		<%for(ProductWrite pAll : pcList){ %>
			
					<div class="different_community">
						<div class="community-box">
							<div class="community-pic"><a href=""><img src="/resources/images/community/<%=pAll.getImg().getChangedName()%>" onerror="javascript:this.src='/resources/images/store/product/error.PNG'"/></a></div>
							<br><div class="community-write"><a href=""><%=pAll.getWrite().getWriteExplain()%></a></div>
						</div>
						
					</div>									
			
			 <% } %>
			 	</div>			
			</div>
			 </div>
			<% } %> 
		</center>
	</div>

</body>
</html>