<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="kr.or.iei.member.model.vo.MemberStatistics" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<meta http-equiv="X-UA-Compatible" content="ie=edge">
<!-- 아이콘 가져오는 CDN -->
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>
<!-- 차트 링크 -->

<title>Insert title here</title>
 <link rel="stylesheet" href="/resources/css/admin/statistics/member.css">
</head>
<body>
<% ArrayList<MemberStatistics> mslist = (ArrayList<MemberStatistics>)request.getAttribute("mslist"); %>	


	<div id="wrapper">
		<div id="header">
			<%@ include file="/views/admin/section/header.jsp" %>
		</div>
		<div id="container">
			<div id="snb">
				<%@ include file="/views/admin/section/navi.jsp" %>
			</div>
			<div id="contents_box">
				<div id="pageTitle">
						&nbsp;<i class="fas fa-id-badge" ></i> 회원 통계
				</div>
				<div id="contents">
				<div>
					<h3 align="center">달별 회원수 통계</h3>
				</div>
					<%for (MemberStatistics ms : mslist) {%> <!-- 데이턴 넣어줄 자료 -->
					<input type="hidden" value="<%=ms.getMonth()%>" id="month"/>
					<input type="hidden" value="<%=ms.getmCount()%>" id="memberCount_<%=ms.getMonth()%>"/> <!-- 아이디값부여 -->
					<%} %>
				<canvas id="myChart"></canvas></div>
						
			</div>
		</div>	
	<div id="footer">
	<%@ include file="/views/section/footer/footer.jsp"%>
	</div>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<!-- 부트스트랩 -->


<!-- 차트그리기 -->
<script>
$(function(){
	
var ctx = document.getElementById('myChart').getContext('2d');
var chart = new Chart(ctx, { // 챠트 종류를 선택 
	type: 'bar', // 챠트를 그릴 데이타
	data: { labels: ['1월', '2월', '3월', '4월', '5월', '6월', '7월','8월','9월','10월','11월','12월'],
	datasets: [{ label: '회원수', backgroundColor: 'rgba(169, 120, 254, 0.5)',
	borderColor: 'red', data: 
		  [ $('#memberCount_1').val(),$('#memberCount_2').val(), $('#memberCount_3').val(),
			$('#memberCount_4').val(),$('#memberCount_5').val(), $('#memberCount_6').val(),
			$('#memberCount_7').val(),$('#memberCount_8').val(), $('#memberCount_9').val(),
			$('#memberCount_10').val(),$('#memberCount_11').val(), $('#memberCount_12').val()]
	}] }, // 옵션
	options: {
		legend:{
		display:true
		},
		title:{
		display:false,
		text :'통계'
		}
	}
	});
})
</script>


</body>
</html>























