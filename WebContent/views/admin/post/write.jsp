<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="kr.or.iei.write.model.vo.WriteAll"%>
<%@ page import="kr.or.iei.write.model.vo.WriteListAll"%>
<%@ page import="kr.or.iei.write.model.vo.WritePageData"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/resources/css/admin/post/write.css" rel="stylesheet"
	type="text/css" />
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<!-- 아이콘 가져오는 CDN -->
</head>

<body>

	<%
		WritePageData wpd = (WritePageData) request.getAttribute("pageData");
		ArrayList<WriteListAll> list = wpd.getList();

		WriteListAll wAll = null;

		String pageNavi = wpd.getPageNavi();
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
							class="fas fa-user-edit"></i>게시글 관리</li>
					</ul>
					<br> <br> <br>
					<div id="article_right_top_sub">게시글 삭제</div>
				</div>

				<div id="article_right_middle">
					<!-- Servlet으로 DB에 넘겨주기 -->
					<form action="/SearchListPage.kh" method="post">
						<div id="article_right_middle_line1">
							<ul id="article_right_middle_line2">
								<li id="article_right_middle_title">게시글 검색</li>
								<li id="article_right_middle_select"><select
									id="post_select" name="postSelectOpt"style="width:80px;">
										<option ${(param.postSelectOpt == "tag")?"selected":""}
											value="tag" name="tag" >태그</option>
										<option
											${(param.postSelectOpt == "write_explain")?"selected":""}
											value="write_explain" name="write_explain">내용</option>
										<option ${(param.postSelectOpt == "member_Id")?"selected":""}
											value="member_Id" name="member_Id">ID</option>
										<option
											${(param.postSelectOpt == "member_Nickname")?"selected":""}
											value="member_Nickname" name="member_Nickname">닉네임</option>
										<option ${(param.postSelectOpt == "write_date")?"selected":""}
											value="write_date" name="write_date">작성일</option>
								</select></li>
								<li id="article_right_middle_search"><input type="text"
									placeholder=" 키워드를 입력하세요." name="keyword"
									value="${param.keyword}" /> <input type="submit"
									style="height: 30px; width: 100px;" value="검색"
									id="input_keyword" /> &nbsp;날짜 형식 : 년/월/일 (예 : 20/02/17)</li>
							</ul>
						</div>
					</form>

				</div>
				<div id="article_right_main">
					<span><a href="/writeAllList.kh?currentPage=1">전체 게시글 목록</a></span><br>

					<span>검색 결과 <%=wpd.getTotalPage()%> 건
					</span>

					<!-- Servlet으로 DB에 넘겨주기 -->
					<div id="article_right_submain">

						<div id="article_right_main_table">
							<table id="post_table">
								<tr id="search_list">
									<th width=60px;>번호</th>
									<th width=600px;>내용</th>
									<th width=400px;>태그</th>
									<th width=60px;><i class="fas fa-heart heart-icon"></i></th>
									<th width=250px;>관련 상품</th>
									<th width=170px;>작성자 ID / 닉네임</th>
									<th width=130px;>작성일</th>
									<th width=80px;>삭제</th>
								</tr>



								<%
									for (int i = 0; i < list.size(); i++) {
								%>
								<%
									wAll = list.get(i);
								%>


								<tr id="post_list">
									<td><%=wAll.getWrite().getWriteNo()%></td>
									<td><%=wAll.getWrite().getWriteExplain()%></td>
									<td>
										<%
											for (int k = 0; k < wAll.getWriteTag().size(); k++) {
										%>#<%=wAll.getWriteTag().get(k).getTag()%> <%
 	}
 %>
									</td>
									<td><%=wAll.getWrite().getWriteLike()%></td>
									<td><%=wAll.getProduct().getProductName()%></td>
									<td><%=wAll.getMember().getMemberId()%> / <%=wAll.getMember().getMemberNickname()%></td>
									<td><%=wAll.getWrite().getWriteDate()%></td>
									<td><form action="/WriteDelete.kh" method="post" id="delWriteSubmit">
											<input type="hidden" name="WriteNo"
												value="<%=wAll.getWrite().getWriteNo()%>" /> <input
												class="deleteBtn" type="submit" value="삭제" id="writeDelBtn"
												/>
										</form></td>
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