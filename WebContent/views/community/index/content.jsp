<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="kr.or.iei.write.model.vo.WriteAll"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="/resources/css/community/index/content.css" rel="stylesheet"
	type="text/css" />
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
</head>
<body>
	<%
		ArrayList<WriteAll> list = null;
		String align = (String) request.getAttribute("align");
		WriteAll wAll = null;

		if (align == null) {
			list = (ArrayList<WriteAll>) request.getAttribute("Randomlist");
		}

		else if (align.equals("New")) {
			list = (ArrayList<WriteAll>) request.getAttribute("Newlist");

		} else if (align.equals("Best")) {
			list = (ArrayList<WriteAll>) request.getAttribute("Bestlist");

		}
	%>

	<center>
		<div id="contents">
			<%
				for (int j = 0; j < list.size() / 3; j++) {
			%>
			<div class="box">
				<%
					for (int i = 0; i < 3; i++) {
							wAll = list.get(i + j * 3);
				%>
				<div class="indexImgBox">
					<%-- /detailPrint.kh?write_no=<%=wAll.getWrite().getWriteNo() %> --%>
					<a
						href="/detailPrint.kh?write_no=<%=wAll.getWrite().getWriteNo()%>&productNo=<%=wAll.getWrite().getProductNo()%>">
						<img src="/resources/images/community/write/<%=wAll.getImg().getOriginalName() %>"
						class="index_img" onerror="javascript:this.src='/resources/images/store/product/none.png'"/>
					</a>
					<%--<img src="/resources/images/community/"<%=wAll.getImg().getChangedName()" class="index_img" /> --%>
					<div class="i_content">
						<p class="index_text"><%=wAll.getWrite().getWriteExplain()%></p>
						<p class="index_text2">
							<i class="fas fa-heart heart-icon"></i><%=wAll.getWrite().getWriteLike()%></p>
					</div>
				</div>

				<%
					}
				%>
			</div>
			<%
				}
			%>

		</div>
	</center>

	<script src="https://code.jquery.com/jquery-3.5.1.js"
		integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
		crossorigin="anonymous"></script>
	<script>
		$(function() {
			$('.index_img').hover(
					function() {
						$(this).css('opacity', '0.2');
						$(this).parent().next().css('background-color',
								'rgba(0, 0, 0, 0.8)');
						$('.index_text').css('display', 'block');
						$('.index_text2').css('display', 'block');
					},
					function() {
						$(this).css('opacity', '1');
						$(this).parent().next()
								.css('background-color', 'white');
					});
		});
	</script>
</body>
</html>