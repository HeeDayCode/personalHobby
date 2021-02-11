<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
#inputId{
	width:200px; 
	height:40px; 	
}
.btn{
	width:70px; 
	height:40px;
	border:1px solid lightgrey;
	background-color:lightgrey;
}
</style>

</head>
<body onload="idvalue();">

	<center>
	<h3>아이디 중복확인</h3>
			<form action="/idCheck.kh" method="post">
				<b>아이디 </b>
				<input type="text" name="inputId" id="inputId" />
				<input type="submit" value="중복 확인" id="checkIdBtn" class="btn"><br><br>
			</form>
				<input type="button" value="사용하기" onclick="use();" class="btn">	

		<% 	if (request.getAttribute("result") !=null) {
			int result = (int)request.getAttribute("result");
			if (result > 0) {
		%>
		<script>
			alert('이미 사용중인 아이디 입니다.');
		</script>
		<%
			} else {
		%>
		<script>
			alert('사용 가능한 ID입니다.');
		</script>
		<%
			}
			}
		%>

	</center>

<script>
function idvalue(){
	if('<%=request.getAttribute("userId")%>' == "null"){
		document.getElementById('inputId').value = opener.document.joinForm.userId.value;
	}else{
		document.getElementById('inputId').value ='<%=request.getAttribute("userId")%>' ;
	}
};
function use(){
	opener.document.joinForm.userId.value=document.getElementById('inputId').value;
	opener.document.joinForm.userId.readOnly=true;
	self.close();
};

</script>
</body>
</html>