<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
</head>
<body>
<style>
	input[type=button]{
		margin-top:20px;
		margin-left:100px;
		cursor:pointer;
		width:70px;
		height:30px;
		background-color:#A978FE;
		border:0px;
		border-radius:5px;
		color:white;
		text-align:center;
		
	}
	input{
		cursor:pointer;
	}
</style>
<form action="/profileUpload.kh" method="post" enctype="multipart/form-data" accept=".jpg,.jpeg,.png,.gif,.bmp">
	<input type="file" name="file"/> 
	<input type="submit" value="업로드"/> 
</form><br>
<form action="/profileDelete.kh" method="post" class="deleteForm">
	<input type="submit" value="사진 삭제"/>
</form>
					
<%if(request.getAttribute("result") != null){ 
	int result = (int)request.getAttribute("result");
	if(result>0){ %>
	<script>
		alert('사진이 수정되었습니다');
		opener.parent.location.reload();
		window.close();
	</script>
<%} else{%>
	<script>
		alert('수정에 실패하였습니다. 지속적인 문제 발생시 관리자에게 문의해주세요');
	</script>
<%	} 
}%>

</body>
</html>