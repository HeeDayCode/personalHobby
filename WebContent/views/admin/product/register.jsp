<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<!-- 아이콘 가져오는 CDN -->
<title>Insert title here</title>

<link href="/resources/css/admin/product/register.css" rel="stylesheet"
	type="text/css" />

</head>
<body>
	<div id="wrapper">
		<div id="header">
			<%@ include file="/views/admin/section/header.jsp"%>
		</div>
		<div id="container">
			<div id="snb">
				<%@ include file="/views/admin/section/navi.jsp"%>
			</div>

			<!-- jQuery 라이브러리 -->
			<script src="https://code.jquery.com/jquery-3.5.1.js"
				integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
				crossorigin="anonymous"></script>

			<script>
				$(function() {
					/* 150자 넘으면 입력 못하게 */
					$('#inputText').keydown(function() {
						var inputTextLength = $(this).val().length;
						var inputTextPossible = 150 - inputTextLength;
						if (inputTextLength > 150) {
							$(this).val($(this).val().substring(0, 150));
							$('#textLength').html('0');

						} else {
							$('#textLength').html(inputTextPossible);
						}
					});

					/* 250자 넘으면 입력 못하게 */
					$('#inputText2').keydown(function() {
						var inputTextLength = $(this).val().length;
						var inputTextPossible = 250 - inputTextLength;
						if (inputTextLength > 250) {
							$(this).val($(this).val().substring(0, 250));
							$('#textLength2').html('0');

						} else {
							$('#textLength2').html(inputTextPossible);
						}
					});

					/* 옵션 추가 삭제 버튼 */
					$('#plus')
							.click(
									function() {
										var $optionAdd = $('.optionTextAdd')
												.last();
										$optionAdd
												.after('&nbsp; <input type="text" name="options" class="optionTextAdd" style="height: 32px;"/> &nbsp;');
									});
					$('#minus').click(function() {
						var $optionAdd = $('.optionTextAdd').last();
						if ($optionAdd.attr('id') != 'default') {
							$optionAdd.remove();
						}
					});
				});
			</script>



			<div id="contents_box">
				<div id="contents">
					<div id="pageTitle">
						<i class="fas fa-clipboard-check"></i> 상품 등록
					</div>
					<div id="basicInfo">
						<div id="basicTitle">기본정보</div>
						<form action="/productInsert.kh" method="post"
							enctype="multipart/form-data">
							<table cellspacing="0" cellpadding="0" width=100%;>
								<tr>
									<th>카테고리 <span style="color: blue; font-size: 14px;">(필수)</span></th>
									<td><select name="productCategory" style="height: 32px;">
											<option value=1>실과바늘</option>
											<option value=2>미술키트</option>
											<option value=3>라이프/소품키트</option>
											<option value=4>베이킹/데코키트</option>
											<option value=5>가죽공예</option>
											<option value=6>비즈공예</option>
											<option value=7>퍼즐/조립/브릭</option>
									</select></td>
								</tr>

								<tr>
									<th>옵션 <span style="color: blue; font-size: 14px;">(필수)</span></th>
									<td><input type="text" id="default" name="options"
										class="optionTextAdd" style="height: 32px;" required />&nbsp;
										<input type="button" value="+" id="plus"
										style="width: 40px; height: 32px;" /> <input type="button"
										value="-" id="minus" style="width: 40px; height: 32px;" /></td>
								</tr>

								<tr>
									<th>상품명 <span style="color: blue; font-size: 14px;">(필수)</span></th>
									<td><input type="text" name="productName" id="inputText"
										required style="width: 500px; height: 32px;"
										placeholder="예시) 뜨개질 키트" /> [<span id=textLength>150</span>/150]
									</td>
								</tr>

								<tr>
									<th>판매가 <span style="color: blue; font-size: 14px;">(필수)</span></th>
									<td><input type="text" name="insertPrice" id="insertPrice"
										style="height: 32px; direction: rtl;" required /> 원</td>
								</tr>
								<tr>
									<th>상품 요약 설명</th>
									<td><input type="text" name="productSummary"
										id="inputText2" style="width: 800px; height: 64px;" /> [<span
										id=textLength2>250</span>/250]</td>
								</tr>



								<tr>
									<th>상품 상세 설명</th>
									<td>
										<div class="img_wrap">
											<label>
												<div id="mainImg">
													<i class="fas fa-plus"></i> <input type="file"
														name="file_sub" id="image_sub" accept="image/*"
														style="display: none;" required />
												</div>
											</label> - 제품 상세 설명
										</div>
									</td>
								</tr>
								<tr>
									<th>대표 이미지</th>
									<td><span style="margin: 60px;">대표 이미지</span>
										<div class="img_wrap">
											<label>
												<div id="mainImg">
													<i class="fas fa-plus"></i> <input type="file"
														name="file_main" id="image_main" accept="image/*"
														style="display: none;" required />
												</div>
											</label> - 권장사이즈 : 500px * 500px
										</div></td>
								</tr>
							</table>
							<button type="submit" class="button" id="submitBtn">상품등록</button>
							<button type="button" class="button"
								onclick="location.href =  '/productAllList.kh';">취소</button>
						</form>
					</div>
				</div>
			</div>
			<script>

				var fileCheck1 = document.getElementById("image_sub").value;
				var fileCheck2 = document.getElementById("image_main").value;
				if (!fileCheck1 || !fileCheck2) {
					alert("파일을 첨부해 주세요");
					return false;
				}
			</script>

		</div>
		<div id="footer">
			<%@ include file="/views/section/footer/footer.jsp"%>
		</div>
</body>
</html>