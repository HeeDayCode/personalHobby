<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="/resources/css/store/question/qnaWrite.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script> 
<title>Insert title here</title>
</head>
<body>
<script>
	$(function(){
		/* 1000자 입력 제한 */
		$('#inputText').keydown(function(){
			var inputTextLength = $(this).val().length;
			var inputTextPossible = 1000 - inputTextLength;
			
			if (inputTextLength > 1000) {
				$(this).val($(this).val().substring(0, 1000));
				$('#textLength').html('0');
				}else{
				$('#textLength').html(inputTextPossible);
			}	
		});
	});
</script>
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
		<div id="qnaWrite">
			<form action="/questionInsert.kh" method="post">
				<table cellspacing="0" cellpadding="0" width=100% border="1px solid black" margin="auto";>
					<tr>
						<th>카테고리</th>
						<td>
							<select name="category" style="width: 700px; height: 35px;">
								<option value="상품 문의">상품 문의</option>
								<option value="배송 문의">배송 문의</option>
								<option value="기타 문의">기타 문의</option>
							</select>
						</td>
					</tr>
					<tr>	
						<th>제목</th>
						<td><input type="text" maxlength="30"  style="width: 700px;height: 35px;" name="title" placeholder="제목을 입력해주세요." 
						required style="width: 500px; height: 32px;"/></td>		
					</tr>
					<tr>
						<th>게시글 작성</th>
						<td>
						<textarea name="inputText" id="inputText" cols="70" rows="5" placeholder="내용을 입력해주세요." required style="width: 100%; height: 500px; resize: none;"></textarea>
						[<span id=textLength>1000</span>/1000]</td>
					</tr>
				</table>
				<div id="btnDiv">
					<button class="button" type="submit">글쓰기</button>
					<button class="button" type="reset">취소</button>
				</div>
			</form>
			</div>
			
		</div>
	<div id="footer">
		<%@ include file="/views/section/footer/footer.jsp" %>
	</div>
</div>
</html>