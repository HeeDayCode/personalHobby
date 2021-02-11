<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.or.iei.question.model.vo.Board"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="kr.or.iei.question.model.vo.Answer"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="/resources/css/store/question/questionReadSuccess.css" rel="stylesheet" type="text/css">
<script src="https://code.jquery.com/jquery-3.5.1.js"
   integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
   crossorigin="anonymous"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>

<style>
	#boardText{border: 1px solid lightgrey;
		resize: none;
		font-size : 20px;
	}
</style>
<body>
<%
		Board board = (Board) request.getAttribute("board");
		ArrayList<Answer> aList = (ArrayList<Answer>) request.getAttribute("aList");
%>

	<div id="header">
		<%-- <%@ include file="/views/section/header.jsp" %> --%>
		<%@ include file="/views/section/header/headerShopPage.jsp"%>
	</div>
	<div id="navigator">
	<%-- <%@ include file="/views/section/navigator.jsp" %>		 --%>	
		<%@ include file="/views/section/navi/naviShop.jsp"%>
	
	</div>
<!-- 수정&삭제 버튼을 눌렀을때 동작하는 JS -->
   <script>
      $(function(){
         $('.answerModifyBtn').click(function(){
            var text = $(this).text();
            
            if(text=='수정') // 눌렀을때 수정이란 버튼으로 되어 있었다면
         {
            //댓글 내용 가져오기
            var answer = $('#answer-text').text();
            
            $('#answer-text').html("<input type='text' name='answer' size='100' value='"+answer+"'>");
            
            $(this).text('완료');
            $(this).parent().next().children('button').text('취소');
         //   alert(comment);
         }else if(text=='완료'){
            //눌렀을때 완료라는 버튼으로 되어 있다면 Servlet을 호출하면 submit 동작
            //수정된 데이터를 가져와서 input type=hidden / name=re_answer에 데이터를 넣고 서브밋 동작
            
            //수정된 데이터를 가져와서 hidden값에 적용 시킨 후
            var re_answer = $('#answer-text').children().val();
            $('input[name=re_answer]').val(re_answer);
         
            // submit 동작을 시켜주겠다.
            $(this).parent().submit();
            
         }
      })
         
         $('.answerDeleteBtn').click(function(){
         //   alert("삭제 버튼이 클릭되었습니다.")
            var text = $(this).text();
            if(text=='삭제'){
               // 눌렀을때 삭제라는 버튼으로 되어있었다면 삭제라는 로직을 동작
               var result = window.confirm("해당 댓글을 삭제하시겠습니까?");
               if(result){
                  $(this).parent().submit();
               }
            }else if(text=='취소'){
               // 눌렀을때 취소라는 버튼으로 되어있었다면 해당 페이지를 재로드 혹은 수정 가능 취소
               location.replace('/boardPostClick.kh?boardNo=<%=board.getboardNo()%>&boardCategoryName=<%=board.getboardCategoryName()%>');
               
            }
         })
      })
   </script>	
	
	
<div id="wrapper">
<div id="contents">
	<div id="board">
		<table cellspacing="0" cellpadding="0" border="1px solid black">
					<tr>
						<th>NO</th>
						<th>ITEM</th>
						<th>CATEGORY</th>
						<th>SUBJECT</th>
						<th>NAME</th>
						<th>VIEW</th>
						<th>DATE</th>
					</tr>
					<tr>
						<th><%=board.getboardNo()%></th>
						<%
							if (board.getProductName() == null) {
						%>
						<th></th>
						<%
							} else {
						%>
						<th><%=board.getProductName()%></th>
						<%
							}
						%>
						<th><%=board.getboardCategoryName()%></th>
						<th><%=board.getboardName()%></th>
						<%
							if (board.getuserNickname() == null) {
						%>
						<%--글 작성자 이름이 없는 경우(관리자인 경우) --%>
						<th>관리자</th>
						<%
							} else {
						%>
						<th><%=board.getuserNickname()%></th>
						<%
							}
						%>
						<th><%=board.getboardViewCount()%></th>
						<th><%=board.getboardDate()%></th>
					</tr>
					<tr>
						<td colspan="7" style="width: 800px; height: 500px;"><textarea
								id="boardText" readonly="readonly" cols="120" rows="25"><%=board.getboardText()%></textarea>

						</td>
					</tr>
				</table>
				<% if(mAll == null) { %>
				
				<%}else if((mAll.getM().getMemberNickname()).equals(board.getuserNickname()) ||(0<=mAll.getM().getMemberNo() && mAll.getM().getMemberNo()<=100) ){ %>			
				<div id="btnDiv">
					<form action="/questionModifyPage.kh" method="post" style="float:left " >               		
	                    <button class="button" type="submit">수정</button>
	                     <input type="hidden" name="boardNo" value="<%=board.getboardNo() %>"/>                   
	               </form>               
	               <form action="/questionDelete.kh" method="post" >		               
		                     <button class="button" type="submit">삭제</button>
		                     <input type="hidden" name="boardNo" value="<%=board.getboardNo() %>"/>  		                             
		               </form>
				</div>
				<%}	%>				
			</div>
					<div class="comment-section">
				<%
					if (board.getuserNickname() != null) {
				%>
				<%--게시판 작성자가 null이 아닌 경우(관리자가 아닌 경우)에만 답글이 나타남 --%>
				<%	if(mAll == null){}
				else if (0 <= mAll.getM().getMemberNo() && mAll.getM().getMemberNo() <= 100 && aList.isEmpty()) {
				%>
				<%--답글 작성은 관리자만, 한 번만 할 수 있음 --%>

				<b class="comment-title">답변</b>
				
					<form action="/answerWrite.kh" method="post">
					<div class="comment-write">
					<span id="answer-content">
						<textarea id="commentArea" name="answer" cols="30" rows="10"
							placeholder="댓글을 작성해보세요"></textarea>
					</span>
						<input type="hidden" name="boardNo"
							value="<%=board.getboardNo()%>" /> <input type="hidden"
							name="boardCategoryName"
							value="<%=board.getboardCategoryName()%>" />
						<button class="submit">등록</button>
					</div>
					</form>
				<%
					} else {}
				%>
				<div class="text-comment">
					<ul class="active">
						<%
							if (!aList.isEmpty()) {
						%>
						<li class="mine">
							<%
								for (Answer answer : aList) {
							%>
							<div class="comment-wrapper">
								<div class="profile">
									<p>관리자</p>
									<ul>
										<li id="answer-text"><%=answer.getAnswerText()%></li>
									</ul>
								</div>
								<div class="comment">
									<div class="comment-text">
										<pre><%=answer.getAnswerDate()%></pre>
									</div>
								</div>
								<%if(mAll==null){ %>
								<%}else if(0 <= mAll.getM().getMemberNo() && mAll.getM().getMemberNo() <= 100){ %>
								<!-- 관리자만 답글 수정 삭제 가능 -->
								<div id="btn-wrapper">
									<!-- 수정 폼태그 -->
									<form action="/answerModify.kh" method="post"
										style="display: inline">
										<button type="button" id="buttonShape1" class="answerModifyBtn">수정</button>
										<input type="hidden" name="re_answer" /> 
										<input type="hidden" name="boardNo" value="<%=board.getboardNo()%>" />
										<input type="hidden" name="boardCategoryName" value="<%=board.getboardCategoryName()%>" />
									</form>
									<!-- 삭제 폼태그 -->
									<form action="/answerDelete.kh" method="post"
										style="display: inline">
										<button type="button"  id="buttonShape2" class="answerDeleteBtn">삭제</button>
										<input type="hidden" name="boardNo" value="<%=board.getboardNo()%>" />
										<input type="hidden" name="boardCategoryName" value="<%=board.getboardCategoryName()%>" />
									</form>
								</div>
								<%}else{} %>
							</div> 
					<%}%>
						</li>
						<%
							} else {
						%>
						<p>관리자의 답변이 없습니다.</p>
						<%
							}
						%>
					</ul>
				</div>
			</div>
			<%
				} else {}
			%>				
</div>
</div>

	<div id="footer">
		<%@ include file="/views/section/footer/footer.jsp" %>
	</div>
</body>
</html>