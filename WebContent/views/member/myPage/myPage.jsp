<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="kr.or.iei.member.model.vo.MemberImg"%>
<%@ page import="kr.or.iei.member.model.vo.MemberWritePageData"%>

<%@ page import="kr.or.iei.member.model.vo.MemberWrite"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="kr.or.iei.member.model.vo.MemberCommentPageData"%>
<%@ page import="kr.or.iei.member.model.vo.MemberComments"%>
<%@ page import="kr.or.iei.member.model.vo.MemberHeartPageData"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<link href="/resources/css/member/myPage/myPage.css" rel="stylesheet"
	type="text/css" />
<!-- 아이콘 가져오는 CDN -->
</head>
<body>
	<style>
#profileImg>a>img {
	border-radius: 100px;
	width: 100px;
	height: 100px;
}
</style>
	<%
		String fileName = (String) request.getAttribute("fileName");

		MemberWritePageData mwpd = (MemberWritePageData) request.getAttribute("page1Data");
		ArrayList<MemberWrite> list1 = mwpd.getList();
		MemberCommentPageData mcpd = (MemberCommentPageData) request.getAttribute("page2Data");
		ArrayList<MemberComments> list2 = mcpd.getList();
		MemberHeartPageData mhpd = (MemberHeartPageData) request.getAttribute("page3Data");
		ArrayList<MemberWrite> list3 = mhpd.getList();

		String pageNavi1 = mwpd.getPageNavi();
		String pageNavi2 = mcpd.getPageNavi();
		String pageNavi3 = mhpd.getPageNavi();
	%>
	<div id="warpper">
		<header>
			<%@ include file="/views/section/header/headerComuPage.jsp"%>
		</header>

		<%@ include file="/views/section/navi/naviComu.jsp"%>
		<script src="https://code.jquery.com/jquery-3.5.1.js"
			integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
			crossorigin="anonymous"></script>

		<div id="contents">
			<div id="contents-1">
				<ul id="contents-1-1">
					<a href="/myPage.kh">마이페이지</a>
					<li class="contents-1-2"><a href="/modifyPage.kh">회원 정보 수정</a></li>
					<li class="contents-1-2"><a href="/OrdersList.kh">MY 쇼핑</a></li>
					<li class="contents-1-2"><a href="/deliveryInfo.kh">배송 정보</a></li>
					<li class="contents-1-2"><a
						href="/views/member/myPage/withdraw.jsp">회원 탈퇴</a></li>
				</ul>

			</div>
			<div id="contents-2">
				<div id="contents-2-1">
					<ul id="profileImg">
						<a href="#"> <img
							src="/resources/images/member/myPage/<%=fileName%>" width=100%
							height=100% />
						</a>
						<li id="userName"><a href="#"><%=mAll.getM().getMemberName()%></a><span>(님)</span></li>
					</ul>
				</div>
				<div id="contents-2-2">
					<ul id="contents-middle">
						<li class="contents-middle" id="middle1">내가 쓴 글 ( <%=mwpd.getTotal()%>
							)
						</li>
						<li class="contents-middle" id="middle2"></li>
						<li class="contents-middle" id="middle3">내가 쓴 댓글 ( <%=mcpd.getTotal()%>
							)
						</li>
					</ul>
				</div>
				<div id="contents-2-3">
					<div id="memberWriteWrap">
						<div id="contents-2-3-1">
							<table>
								<%
									for (MemberWrite mWrite : list1) {
								%>
								<tr>
									<ul>
										<li><%=mWrite.getW().getWriteDate()%></li>
										<li><a
											href="/detailPrint.kh?write_no=<%=mWrite.getW().getWriteNo()%>&productNo=<%=mWrite.getW().getProductNo()%>">
												<img
												src="/resources/images/community/write/<%=mWrite.getI().getOriginalName()%>"
												height="200px" /> <%-- <%=wAll.getImg().getChangedName() %> --%>
										</a></li>
										<li><i class="fas fa-heart"> </i> <%=mWrite.getW().getWriteLike()%></li>
										<li>댓글 <%=mwpd.getTotal()%>
										</li>


									</ul>
								</tr>
								<%
									}
								%>
								</div>

								<div>

									<tr>
										<td align="center"><%=pageNavi1%></td>
									</tr>
							</table>
						</div>
					</div>
					<div id="comment-list">
						<table>
							<tr>
								<td colspan="2" style="text-align: right">댓글 내용을 누르면 해당 글로
									이동합니다.</td>
							</tr>
							<tr id="testtest">
								<th class="comment-list-left">작성일</th>
								<th class="comment-list-right">내용</th>
							</tr>
							<%
								for (MemberComments mc : list2) {
							%>
							<tr>
								<td class="comment-list-left"><%=mc.getC().getCommentsDate()%></td>
								<td class="comment-list-right"><a
									href="/detailPrint.kh?write_no=<%=mc.getC().getWriteNo()%>&productNo=<%=mc.getW().getProductNo()%>">
										<%=mc.getC().getCommentsText()%></a></td>
							</tr>
							<%
								}
							%>
							<tr>
								<td colspan="2" style="text-align: center;"><%=pageNavi2%></td>
							</tr>
						</table>
					</div>

				</div>

				<div id="HeartList-subject">
					<span>좋아요 누른 목록 ( <%=mhpd.getTotal()%> )
					</span>
				</div>
				<div id="HeartList">
					<div id="HeartList-content">
						<%
							for (MemberWrite mw1 : list3) {
						%>
						<ul>
							<li><%=mw1.getW().getWriteDate()%></li>
							<li><a
								href="/detailPrint.kh?write_no=<%=mw1.getW().getWriteNo()%>&productNo=<%=mw1.getW().getProductNo()%>"><img
									src="/resources/images/community/write/<%=mw1.getI().getOriginalName()%>"
									height="200px" /></a></li>
							<li><i class="fas fa-heart"> </i> <%=mw1.getW().getWriteLike()%></li>
							<li>댓글 <%=mw1.getCommentCount()%></li>
						</ul>

						<%
							}
						%>

					</div>

				</div>
				<p style="text-align: center;"><%=pageNavi3%></p>
			</div>
		</div>
		<footer>
			<%@ include file="/views/section/footer/footer.jsp"%>
		</footer>
	</div>
</body>
</html>