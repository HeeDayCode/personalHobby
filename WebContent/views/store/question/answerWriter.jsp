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
	int boardNo = (int)request.getAttribute("boardNo");
	String boardCategoryName = (String)request.getAttribute("boardCategoryName");
	boolean result = (boolean)request.getAttribute("result");
%>
<script>
	<% if(result == false){%>
	<!-- 작성을 실패했을시 알림창 띄워줌-->
	alert('댓글 작성에 실패하였습니다.');
	<%}%>
	location.replace('/boardPostClick.kh?boardNo=<%=boardNo%>&boardCategoryName=<%=boardCategoryName%>');
</script>
</body>
</html>