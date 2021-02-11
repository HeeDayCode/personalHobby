<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="kr.or.iei.write.model.vo.CommentsPageData"%>
<%@ page import="kr.or.iei.write.model.vo.CommentsMember"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/resources/css/admin/post/comment.css" rel="stylesheet"
	type="text/css" />
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<!-- 아이콘 가져오는 CDN -->
</head>

<body>
	<%
		CommentsPageData cpd = (CommentsPageData) request.getAttribute("pageData");
		ArrayList<CommentsMember> list = cpd.getList();

		String pageNavi = cpd.getPageNavi();
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
							class="fas fa-comment-dots"></i> 댓글 관리</li>
					</ul>
					<br> <br> <br>
					<div id="article_right_top_sub">댓글 삭제</div>
				</div>

				<div id="article_right_middle">
					<!-- Servlet으로 DB에 넘겨주기 -->
					<form action="/SearchComments.kh" method="post">
						<div id="article_right_middle_line1">
							<ul id="article_right_middle_line2">
								<li id="article_right_middle_title">댓글 검색</li>
								<li id="article_right_middle_select"><select
									id="post_select" name="commentsSelectOpt" style="width:100px;">

										<option
											${(param.commentsSelectOpt == "comments_text")?"selected":""}
											value="comments_text" name="comments_text">댓글 내용</option>
											
											<option
											${(param.commentsSelectOpt == "write_no")?"selected":""}
											value="write_no" name="write_no">게시글 번호</option>
											
										<option
											${(param.commentsSelectOpt == "member_Id")?"selected":""}
											value="member_Id" name="member_Id">ID</option>
											
										<option
											${(param.commentsSelectOpt == "member_Nickname")?"selected":""}
											value="member_Nickname" name="member_Nickname">닉네임</option>
											
										<option
											${(param.commentsSelectOpt == "member_Name")?"selected":""}
											value="member_Name" name="member_Name">이름</option>
											
										<option
											${(param.commentsSelectOpt == "comments_date")?"selected":""}
											value="comments_date" name="comments_date">작성일</option>
											
								</select></li>
								<li id="article_right_middle_search"><input type="text"
									placeholder=" 키워드를 입력하세요." name="keyword"
									value="${param.keyword}" /> <input type="submit"
									style="height: 30px; width: 100px;" value="검색"
									id="input_keyword" /> &nbsp;날짜 형식 : 년/월/일 (예 : 20/08/23)</li>
							</ul>
						</div>
					</form>

				</div>
				<div id="article_right_main">
					<span><a href="/commentsAllList.kh?currentPage=1">전체 댓글
							목록</a></span><br> <span>검색 결과 <%=cpd.getTotal()%> 건
					</span>

					<!-- Servlet으로 DB에 넘겨주기 -->
					<div id="article_right_submain">

						<div id="article_right_main_table">
							<table id="post_table">
								<tr id="search_list">
									<th width=50px;>댓글 번호</th>
									<th width=80px;>게시글 번호</th>
									<th width=600px;>댓글 내용</th>
									<th width=100px;>ID</th>
									<th width=100px;>닉네임</th>
									<th width=100px;>이름</th>
									<th width=150px;>작성일</th>
									<th width=80px;>삭제</th>
								</tr>
								<%
									for (CommentsMember CM : list) {
								%>
								<tr id="post_list">
									<td><%=CM.getC().getCommenstNo()%></td>
									<td><%=CM.getC().getWriteNo()%></td>
									<td><%=CM.getC().getCommentsText()%></td>
									<td><%=CM.getM().getMemberId()%></td>
									<td><%=CM.getM().getMemberNickname()%></td>
									<td><%=CM.getM().getMemberName()%></td>
									<td><%=CM.getC().getCommentsDate()%></td>
									<td><form action="/commentsDelete.kh" method="post"
											id="delCommentsSubmit">
											<input type="hidden" name="CommentNo" value="<%=CM.getC().getCommenstNo() %>" /> <input
												class="deleteBtn" type="submit" value="삭제" id="CommentsDelBtn" />
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