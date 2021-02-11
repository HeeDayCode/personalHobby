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
		alert("상품이 발송 되었습니다.");
	<% } else { %>
		alert("상품 발송에 실패했습니다.");
	<% } %>
		location.replace("/orderAll.kh");
	</script>
</body>
</html>