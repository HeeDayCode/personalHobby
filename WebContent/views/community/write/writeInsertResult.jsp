<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	    
		boolean result = (boolean) request.getAttribute("result");
	%>

	<script>
		
	<%if (result == true) {%>
		alert("게시글 등록이 완료되었습니다.");
		location.replace("/index.kh");
	<%} else {%>
		alert("게시글 등록에 실패하였습니다.");
		location.replace("/index.kh");
	<%}%>
		
	</script>
</body>
</html>