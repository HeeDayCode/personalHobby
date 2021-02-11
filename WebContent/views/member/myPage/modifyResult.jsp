<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="kr.or.iei.member.model.vo.MemberAll" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	    MemberAll mAll = (MemberAll)request.getAttribute("memberAll");
		boolean result = (boolean) request.getAttribute("result");
	%>

	<script>
		
	<%if (result == true) {%>
		alert("회원 정보 수정이 완료되었습니다.");
		location.replace("/myPage.kh");
	<%} else {%>
		alert("회원 정보 수정이 실패하였습니다. 지속적인 문제 발생 시 관리자에게 문의하세요.");
		location.replace("/myPage.kh");
	<%}%>
		
	</script>
</body>
</html>