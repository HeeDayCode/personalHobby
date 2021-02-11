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
<title>Insert title here</title>
 <link rel="stylesheet" href="/resources/css/admin/statistics/member.css">
 <style>
 
 </style>
</head>
<body>
<% ArrayList<MemberStatistics> salesList = (ArrayList<MemberStatistics>)request.getAttribute("salesList"); %>	
	
	
	
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
						&nbsp;<i class="fas fa-file-invoice-dollar"></i> 매출 통계
				</div>
				<div id="contents">
				<div>
					<h3 align="center" style="font-size:28px;">달별 매출 통계</h3>
				</div>
				 <%for(MemberStatistics ms : salesList){ %>
					<input type="hidden" value="<%=ms.getMonth()%>" id="month"/>
					<input type="hidden" value="<%=ms.getpCount()%>" id="sales_<%=ms.getMonth()%>"/>
				<%} %> 
				<canvas id="myChart"></canvas>
				</div>
			</div>
		</div>	
	<div id="footer">
	<%@ include file="/views/section/footer/footer.jsp"%>
	</div>
</div>

<!-- 부트스트랩 -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>


<!-- 차트그리기 -->
<script> 
$(function(){
	
	var ctx = document.getElementById('myChart').getContext('2d');
	var chart = new Chart(ctx, { // 챠트 종류를 선택 
		type: 'bar', // 챠트를 그릴 데이타
		data: { labels: ['1월', '2월', '3월', '4월', '5월', '6월', '7월','8월','9월','10월','11월','12월'],
		datasets: [{ label: '매출금액', backgroundColor: 'rgba(169, 120, 254, 0.5)',
		borderColor: 'red', data: 
			  [   $('#sales_1').val(),$('#sales_2').val(), $('#sales_3').val(),		//통계별 아이디값을 제이쿼리로 가져와서 데이터로 넣어주기
				  $('#sales_4').val(),$('#sales_5').val(), $('#sales_6').val(),
				  $('#sales_7').val(),$('#sales_8').val(), $('#sales_9').val(),
				  $('#sales_10').val(),$('#sales_11').val(), $('#sales_12').val()]
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























