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
%>
	<script>
		alert("댓글 삭제 실패!");
		location.replace('/boardPostClick.kh?boardNo=<%=boardNo%>&boardCategoryName=<%=boardCategoryName%>');
	</script>
</body>
</html>