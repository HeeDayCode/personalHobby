<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		boolean result=(boolean)request.getAttribute("result");
	%>
	
	<script>
	<% if(result) { %>
		alert("수정이 완료되었습니다.");
	<% } else { %>
		alert("수정에 실패하였습니다.\n개발자에게 문의해주세요.");
	<% } %>
		location.replace("/productAllList.kh");
	</script>
</body>
</html>