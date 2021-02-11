<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="kr.or.iei.question.model.vo.Board" %>
<%@ page import="kr.or.iei.question.model.vo.BoardPageData" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="/resources/css/store/question/list.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	BoardPageData bpd = (BoardPageData)request.getAttribute("pageData");
	ArrayList<Board> qPageList = null;
	String pageNavi = null;
	if(bpd!=null){
		qPageList = bpd.getList();
		pageNavi = bpd.getPageNavi();
	}
	// 사용자 문의(페이징)
	ArrayList<Board> qList = (ArrayList<Board>)request.getAttribute("qList");
	// 사용자 문의(검색)
	ArrayList<Board> nList = (ArrayList<Board>)request.getAttribute("nList");
	// 공지
%>
<div id="wrapper">
	<div id="header">
		<%-- <%@ include file="/views/section/header.jsp" %> --%>
		<%@ include file="/views/section/header/headerShopPage.jsp"%>
	</div>
	<div id="navigator">
	<%-- <%@ include file="/views/section/navigator.jsp" %>		 --%>	
		<%@ include file="/views/section/navi/naviShop.jsp"%>
	
	</div>
	<div id="contents">
		<div id="title">Q & A
		</div>	
			<div id="qna-bottom">
			<div id="search" style="float:right">
				<form id="heightNo" action="/boardSearch.kh" method="post">
					<table cellspacing="0" cellpadding="0" border="0px solid white" style="width:100%; height:30px; float:right">
						<tr>
						<td><select name="searchCategory" style="height:30px;">
							<option>제목</option>
							<option>내용</option>
							<option>작성자</option>
							<option>상품명</option>
						</select></td> 
							<td><input type="text" name="keyword" style="height:30px;"/></td>	
							<td><input type="submit"  class="button" value="검색" style="height:30px;width:70px;"/></td>
				</form>
							<td><button  id="resetButton"  class="button" type="button" style="height:30px; line-height: 10px;" 
								   onclick="location.href = '/question.kh';"
							>취소</button></td>
							<td>
							<% if(mAll == null) { %>
							
							<%}else if(0<=mAll.getM().getMemberNo() && mAll.getM().getMemberNo()<=100){ %>
								<button type="button" style="height:30px;padding: 10px; line-height: 10px;"  class="button" onclick="window.location='/views/store/question/noticeWrite.jsp'">글쓰기</button>
							<%}else{ %>
								<button type="button"  style="height:30px;padding: 10px; line-height: 10px;"  class="button" onclick="window.location='/views/store/question/qnaWrite.jsp'">글쓰기</button>
							<%} %>							
							</td>
						</tr>						
					</table>	
			</div>
			</div>	
		<div id="board">
			<table>
				<tr>
					<th>NO</th>
					<th>ITEM</th>
					<th>CATEGORY</th>
					<th>SUBJECT</th>
					<th>NAME</th>
					<th>VIEW</th>
					<th>DATE</th>					
				</tr>								
<!--  공지 -->
				<%
					for(Board nBoard : nList){
				%>
				<tr>
					<th><%=nBoard.getboardNo()%></th>
					<td></td>
					<td><%=nBoard.getboardCategoryName()%></td>									
					<td><a href="/boardPostClick1.kh?boardNo=<%=nBoard.getboardNo()%>&boardCategoryName=<%=nBoard.getboardCategoryName()%>"><%=nBoard.getboardName()%></a></td>									
					<td>관리자</td>
					<td><%=nBoard.getboardViewCount()%></td>
					<td><%=nBoard.getboardDate()%></td>				
				</tr>
				<%
					}
				%>
				
<!-- 일반 -->
<%if(qList != null){ %>
			<%--qList가 null이 아닐시(검색해서 받아온 값이 있을시) --%>
				<%
					for(Board qBoard : qList){
				%>
				<tr>
					<td align="center"><%=qBoard.getboardNo() %></td>
					<%if(qBoard.getProductName()==null){ %>
					<td></td>
					<%}else{ %>
					<td><%=qBoard.getProductName() %></td>
					<%} %>
					<td><%=qBoard.getboardCategoryName() %></td>
					<td><a href="/boardPostClick.kh?boardNo=<%=qBoard.getboardNo()%>&boardCategoryName=<%=qBoard.getboardCategoryName()%>"><%=qBoard.getboardName() %></a></td>
					<td><%=qBoard.getuserNickname() %></td>
					<td><%=qBoard.getboardViewCount() %></td>
					<td><%=qBoard.getboardDate() %></td>
				</tr>
				<%} %>
			<%}else{ %>
				<%
					for(Board qBoard : qPageList){
				%>
				<tr>
					<td align="center"><%=qBoard.getboardNo() %></td>
					<%if(qBoard.getProductName()==null){ %>
					<td></td>
					<%}else{ %>
					<td><%=qBoard.getProductName() %></td>
					<%} %>
					<td><%=qBoard.getboardCategoryName() %></td>
					<td><a href="/boardPostClick.kh?boardNo=<%=qBoard.getboardNo()%>&boardCategoryName=<%=qBoard.getboardCategoryName()%>"><%=qBoard.getboardName() %></a></td>
					<td><%=qBoard.getuserNickname() %></td>
					<td><%=qBoard.getboardViewCount() %></td>
					<td><%=qBoard.getboardDate() %></td>
				</tr>
				<%} %>
			<%} %>			
			</table>
		</div>
			<%if(bpd!=null){ %>
	      <div id="pageNavi">
	            <%=pageNavi %>
	      </div>
	      <%} %>
	</div>
	<div id="footer">
		<%@ include file="/views/section/footer/footer.jsp" %>
	</div>
</div>
</body>
</html>