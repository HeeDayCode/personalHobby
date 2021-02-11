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
function getQuerystring(paramName){
	var _tempUrl = window.location.search.substring(1); //url에서 처음부터 '?'까지 삭제 
	var _tempArray = _tempUrl.split('&'); // '&'을 기준으로 분리하기 
	for(var i = 0; _tempArray.length; i++) { 
		var _keyValuePair = _tempArray[i].split('='); // '=' 을 기준으로 분리하기 
	if(_keyValuePair[0] == paramName){ // _keyValuePair[0] : 파라미터 명 // _keyValuePair[1] : 파라미터 값 
		return _keyValuePair[1]; 
		} 	
	} 
	var productNo = getQuerystring(productNo);	
	return productNo;
}
</script>

<%-- <%
	int productNo = request.getParameter("productNo"); 	
%>  --%>
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


	<div id="header">
		<%-- <%@ include file="/views/section/header.jsp" %> --%>
		<%@ include file="/views/section/header/headerShopPage.jsp"%>
	</div>
	<div id="navigator">
	<%-- <%@ include file="/views/section/navigator.jsp" %>		 --%>	
		<%@ include file="/views/section/navi/naviShop.jsp"%>
	
	</div>
<div id="wrapper">
<div id="contents">
		<div id="title">Q & A
		</div>
		<script>
		console.log(getQuerystring('productNo'));		
		</script>
		<div id="qnaWrite">
			<form action="/questionInsert1.kh" method="post">
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
						<td><input type="text" maxlength="30" name="title" placeholder="제목을 입력해주세요." 
						required style="width: 500px; height: 32px;"/></td>	
					</tr>
					<tr>
						<th>게시글 작성</th>
						<td>
						<textarea name="inputText" id="inputText" cols="70" rows="5" placeholder="내용을 입력해주세요." required style="width: 100%; height: 500px; resize: none;"></textarea>
						[<span id=textLength>1000</span>/1000]</td>
					</tr>
				</table>			
				<input type="hidden"  id="productNo"  name="productNo"  value=""/>
				<script>
				document.getElementById('productNo').value = getQuerystring('productNo');				
				</script>
				<div id="btnDiv">
					<button class="button" type="submit">글쓰기</button>
					<button class="button" type="reset">취소</button>
				</div>
			</form>
			</div>
			</div>
		</div>
	<div id="footer">
		<%@ include file="/views/section/footer/footer.jsp" %>
	</div>

</html>