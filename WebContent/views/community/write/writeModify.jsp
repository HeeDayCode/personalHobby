<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import ="kr.or.iei.write.model.vo.WriteAll" %>
<%@ page import ="kr.or.iei.product.model.vo.ProductAll" %>
<%@ page import = "kr.or.iei.write.model.vo.WriteImage" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="/resources/css/community/write/write.css" rel="stylesheet"
	type="text/css">
<link
	href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.js"
	integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
	crossorigin="anonymous"></script>
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-lite.css"
	rel="stylesheet">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.12/summernote-lite.js"></script>
	
<style>
.contents{
	height:1100px;
}
#plusBtn {
	float: right;
	margin-left: 10px;
}

#selectProduct {
	width: 100px;
	height: 35px;
	padding: 10px;
	border-radius: 3px;
	border-width: 1px;
	border-style: solid;
	border-color: -internal-light-dark(rgb(118, 118, 118),
		rgb(133, 133, 133));
	border-image: initial;
	font-size: 1rem;
}
#selectName {
	width: 200px;
	height: 35px;
	padding: 10px;
	border-radius: 3px;
	border-width: 1px;
	border-style: solid;
	border-color: -internal-light-dark(rgb(118, 118, 118),
		rgb(133, 133, 133));
	border-image: initial;
	font-size: 1rem;
}

#searchBox {
	width: 345px;
}

#summernote {
	width: 100%;
	height: 400px;
}

#inputText {
	width: 300px;
	height: 35px;
	padding: 10px;
	border-radius: 3px;
	border-width: 1px;
	border-style: solid;
	border-color: -internal-light-dark(rgb(118, 118, 118),
		rgb(133, 133, 133));
	border-image: initial;
	font-size: 1rem;
}

#plusBtn {
	color: #B0B0B0;
	cursor: pointer;
	margin-right: 1190px;
	margin-left: 0px;
}

.hashtag-section {
	height: 130px;
}

#file-upload-button {
	cursor: pointer;
}
#productnum>input:nth-child(1){
	width:50px; height:30px;
}
#productnum>input:nth-child(2){
	width:50px; height:30px;
}
#productnum>input:nth-child(3){
	width:100px; height:30px;
}

input[name=file]{
border:0;
}
</style>
		
</head>
<body>
<% 	WriteAll wAll = (WriteAll)request.getAttribute("wAll");
	ProductAll pAll = (ProductAll)request.getAttribute("pAll");
	WriteImage wimg = (WriteImage)request.getAttribute("wimg");
%>
	<%--css 파일에 넣으면 적용이 안돼서 그냥 여기서 css값 줬어요 --%>
	
	<div id="header">
		<%@ include file="/views/section/header/headerComuPage.jsp"%>
	</div>
	<%@ include file="/views/section/navi/naviComu.jsp"%>
	<div class="contents">

		<div id="contents-main">
			<div class="main-content">
				<div class="side-title">
					<p>
						<span>글쓰기 수정</span>
					</p>
					<h1>커뮤니티</h1>
				</div>
						<div>
							<input type="text" name="keyword" placeholder="상품명을 검색하세요."
								name="searchBox" id="searchBox" /> <input type="submit"
								class="submit" value="수정" id="searchBtn" />

						</div>
						<br>
						<div id="productnum">
							기존 상품번호 : <input type="text"  name="selectProduct" value="<%=pAll.getProduct().getProductNo() %>" readOnly/>
							수정 상품번호 : <input type="text"  id="selectProduct" value="<%=pAll.getProduct().getProductNo() %>" readOnly/>
							수정한 상품명 : <input type="text" name="selectName" id="selectName" value="<%=pAll.getProduct().getProductName() %>" readOnly/>
						</div>
						<br>
				<form action="/CommunityModify.kh" id="articleForm" method="post" enctype="multipart/form-data">
					<div id="fileimg">
					 
						<input type="file" name="file" id="file" />  
						<input type="hidden" id="searchProduct" name="productNo" />
						<input type="hidden" name="writeNo" value="<%=wAll.getWrite().getWriteNo()%>" /> 
						<input type="hidden" name="imgNo" value="<%=wimg.getImgNo()%>" />
					</div>
					<br style="clear: both">
					<div class="form-group">
						<textarea id="summernote" name="content" style="font-size: 20px" maxlength="140" rows="10"><%=wAll.getWrite().getWriteExplain()%></textarea>
					</div>
					<div class="hashtag-section">
					<div>
						<input type="text" id="inputText" placeholder="태그를 입력하세요"  />
						</div>
						<button id="plusBtn" type="button">
							<span>태그추가</span>
						</button>
					</div>
					<div class="button-group">
						<button class="submit" id="tagChoice">수정하기</button>
						<button type="submit" class="submit" name="submit">취소</button>
					</div>
				</form>
			</div>


			<!-- 상품 번호 넘기는 코드  -->
			<script>
				$(function() {
					$('#articleForm').submit(function() {
						var selectProduct = $('#selectProduct').val();
						$('#searchProduct').val(selectProduct);
					});
				});
			</script> 



			<script>
				$(function() {
					$('#searchBtn').click(
							function() {
								var keyword = $('#searchBox').val();
								window.open("/productSearchList.kh?keyword="
										+ keyword, "_black",
										"width=1300, height=900");
							});
				});
			</script>




			<script>
				$(function() {
					var $tagSection = $(".hashtag-section").children("div");
					var $addTag = $(".hashtag-section").children().last();
					var cnt = 0;
					$addTag.click(function() {
						if (cnt > 9) {
							$addTag.css("display", "none");
							$('#inputText').css("display", "none");
						} else {
							var $inputText = $('#inputText').val();
							$tagSection.html($tagSection.html()
									+ "<button><span>#" + $inputText
									+ "</span></button>");
							cnt++;
						}
					});
				});
			</script>

		</div>
	</div>
	<div id="footer">
		<%@ include file="/views/section/footer/footer.jsp"%>
	</div>
</body>
</html>