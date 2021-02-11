<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
#inputNickname{
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
<body onload="nicknamevalue();">

	<center>
	<h3>닉네임 중복확인</h3>
			<form action="/nicknameCheck.kh" method="post">
				<b>닉네임 </b>
				<input type="text" name="inputNickname" id="inputNickname" />
				<input type="submit" value="중복 확인" id="checkIdBtn" class="btn"><br><br>
			</form>
				<input type="button" value="사용하기" onclick="use();" class="btn">	

<%
	request.setCharacterEncoding("UTF-8");
%>

	<% 	if (request.getAttribute("result") !=null) {
			int result = (int)request.getAttribute("result");
			if (result > 0) {
		%>
		<script>
			alert('이미 사용중인 닉네임 입니다.');
		</script>
		<%
			} else {
		%>
		<script>
			alert('사용 가능한 닉네임 입니다.');
		</script>
		<%
			}
			}
		%>

	</center>

<script>
function nicknamevalue(){
	if('<%=request.getAttribute("userNickname")%>' == "null"){
		document.getElementById('inputNickname').value = opener.document.joinForm.userNickname.value;
	}else{
		document.getElementById('inputNickname').value ='<%=request.getAttribute("userNickname")%>' ;
	}
	
};
function use(){
	opener.document.joinForm.userNickname.value=document.getElementById('inputNickname').value;
	opener.document.joinForm.userNickname.readOnly=true;
	self.close();
};
</script>
</body>
</html>