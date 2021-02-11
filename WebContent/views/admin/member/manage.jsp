<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="kr.or.iei.member.model.vo.MemberPageData" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="kr.or.iei.member.model.vo.MemberAll" %>
<%@ page import="kr.or.iei.member.model.vo.MemberDate" %>
<%@ page import="kr.or.iei.member.model.vo.Member" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<title>Insert title here</title>
<link href="/resources/css/admin/member/manage.css" rel="stylesheet" type="text/css" />
<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
</head>
<body>

<%
	MemberPageData mpd = (MemberPageData)request.getAttribute("pageData");
	ArrayList<MemberAll> mAllList = mpd.getmAllList();
	String pageNavi = mpd.getPageNavi();
	String keyword = (String)request.getAttribute("keyword");
	
%>	
	
	<div id="wrapper">
		<div id="header">
				<%@ include file="/views/admin/section/header.jsp"%>
		</div>
		<div id="container">
			<div id="snb">
				<%@ include file="/views/admin/section/navi.jsp"%>
			</div>
			<section>
			<div id="pageTitle">
					&nbsp; <i class="fas fa-user-check"></i>회원 조회 및 삭제 <i class="fas fa-user-minus" ></i>
			</div>
			<div id="sectionWrap">
				<table cellpadding="0" cellspacing="0">
						<tr>
						<th>검색</th>
						<form action="/OneListPage.kh" method="post">
							<td><select id="searchOpt" name="searchOpt" style="height:32px;">
									<option ${(param.searchOpt == "member_Id")?"selected":""}
										value="member_Id" name="member_Id">ID</option>
									<option ${(param.searchOpt == "member_Name")?"selected":""}
										value="member_Name" name="member_Name">이름</option>
							</select> 
							<input type="text" name="keyword" value="${param.keyword}" style="height:32px;width: 150px;"/> 
							<input type="submit" value="검색" style="width: 80px; height:32px;"/></td>
						</form>

					</tr>
					</table>
					<br>
					<div>
						<div>
							<table cellpadding="0" cellspacing="0" style="text-align:center;">
								<tr>
									<th>이름</th>
									<th>아이디</th>
									<th>닉네임</th>
									<th>휴대폰</th>
									<th>이메일</th>
									<th>가입일</th>
									<th>탈퇴일</th>
									<th>탈퇴여부</th>
									<th>탈퇴상태</th>
								</tr>
							

							 <% for(MemberAll mAll : mAllList) { %>	
							 	
								<% if(mAll.getMd().getMemberWithdrawYN()=='Y'){ %>
								<tr style="background-color:#FF6464;">
								<!-- <tr style="text-decoration:line-through;"> -->
								<% } %>
								
									<td><%= mAll.getM().getMemberName()%></td>
									<td><%= mAll.getM().getMemberId()%></td>
									<td><%=mAll.getM().getMemberNickname() %>
									<td><%= mAll.getM().getMemberPhone()%></td>
									<td><%= mAll.getM().getMemberEmail()%></td>	
									<td><%= mAll.getMd().getMemberJoinDate()%></td>
									<td>
										<% if(mAll.getMd().getMemberWithdrawDate()==null) { %>
											-
										<% } else {%>
											<%= mAll.getMd().getMemberWithdrawDate() %>
										<% } %>
										
									</td>
									<%-- <td><%= mAll.getMd().getMemberWithdrawYN() %></td> --%>
									<td>
										<% if(mAll.getMd().getMemberWithdrawYN()=='N') { %>
											사용중
											
										<% } else { %>
											탈퇴
										<% } %>
										
									</td>
									
									<td>
										<form action="/memberStateChanged.kh" method="get" id="deleteForm">
											<input type="hidden" name="memberNo" value="<%=mAll.getM().getMemberNo() %>"/>
											<input type="hidden" name="memberWithdrawYN" value="<%=mAll.getMd().getMemberWithdrawYN() %>"/>
											
										<%if(mAll.getM().getMemberNo() >= 100){ %>
											<input class="deleteBtn" type="submit" value="<%=mAll.getMd().getMemberWithdrawYN() %>" style="width:100%; height:100%;"/>
										<% } %>
										</form>
									</td>				
								</tr>
							
								
							<% } %>
								<tr>
									<td colspan="9" align="center"><%=pageNavi %></td>
								</tr>
						
							</table>
						</div>
					</div>
				</div>
			</section>
		</div>
			
		<div id="footer">
			<%@ include file="/views/section/footer/footer.jsp"%>
		</div>
	</div>
</body>
</html>