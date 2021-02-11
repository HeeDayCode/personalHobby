<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="/resources/css/member/join/joinForm.css" rel="stylesheet" type="text/css"/>
<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
</head>
<body>
	<div id="wrapper">
	
		<div id="header">
			<%@ include file="/views/section/header/headerComuPage.jsp"%>
		</div>
		
		<div id="navigator">
				<%-- <%@ include file="/views/sections/navigator.jsp" %> --%>
			<%@ include file="/views/section/navi/naviShop.jsp"%>

<script src="https://code.jquery.com/jquery-3.5.1.js"
		integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
		crossorigin="anonymous"></script>
	

		</div>
		
		<div id="container">
		
			<h1>회원정보 입력</h1>
			<form action="/joinForm.kh" method="post" id="member_form" name="joinForm" onsubmit="return maincheck()">
				<fieldset id="join_box">
					<legend>필수사항 </legend>
					<div>
						<div>아이디</div>
						<div>
							<input type="text" name="userId" id="userId"
								placeholder="   공백 없는 영문/숫자 포함 6-20자" />
							<input type="button" onclick="checkId();" class="button" id="idCheck" value="중복확인"/>
							<input type="hidden" name="pilsucheck" value='n'/>
						</div>
					</div>
					<div>
						<div>비밀번호</div>
						<div>
							<input type="password" name="userPw" size="30"
								placeholder="   공백 없는 영문/숫자 포함 6-20자" />
						</div>
					</div>
					<div>
						<div>비밀번호 확인</div>
						<div>
							<input type="password" name="userPw_re" placeholder="   비밀번호를 재입력하세요" />
						</div>
					</div>
					<div>
						<div>이름</div>
						<div>
							<input type="text" name="userName" placeholder="   이름을 입력하세요" />
						</div>
					</div>
					<div>
						<div>닉네임</div>
						<div>
							<input type=text " name="userNickname" placeholder="   한글만 2~6자" />
							<input type="button" onclick="checkNickname();" class="button" id="nicknameCheck" value="중복확인"/>
							<input type="hidden" name="pilsucheck" value='n'/>
						</div>
					</div>
					<div class="info_box">
						<div>이메일</div>
						<div>
							<input class="email" type="text" name="email"/> @
							<input class="email" type="text" name="email2"/>
							
							<select name="find_email" class="email">
								<option value="" id="free">직접입력</option>
								<option value="naver.com">naver.com</option>
								<option value="nate.com">nate.com</option>
								<option value="dreamwiz.com">dreamwiz.com</option>
								<option value="yahoo.co.kr">yahoo.co.kr</option>
								<option value="empal.com">empal.com</option>
								<option value="unitel.co.kr">unitel.co.kr</option>
								<option value="gmail.com">gmail.com</option>
								<option value="korea.com">korea.com</option>
								<option value="chol.com">chol.com</option>
								<option value="paran.com">paran.com</option>
								<option value="freechal.com">freechal.com</option>
								<option value="hanmail.net">hanmail.net</option>
								<option value="hotmail.com">hotmail.com</option>
								
							</select>
							<div>
								<label>
								<input type="checkbox" name="mailing" /> 
								<span>정보메일을 수신하겠습니다.</span>
								</label>
								<p class="info_box_font">이메일 수신에 동의하시면 여러가지 할인혜택과 각종 이벤트 정보를 받아보실 수 있습니다.<br />회원가입관련, 주문배송관련 등의 메일은 수신동의와 상관없이 모든 회원에게 발송됩니다.<br /></p>
							</div>
						</div>
					</div>
					<div class="info_box">
						<div>휴대전화번호</div>
						<div>
							<input class="phone" type="tel" name="phone1" maxlength="3" onkeypress="onlyNumber();" /> -
							<input class="phone" type="tel" name="phone2" maxlength="4" onkeypress="onlyNumber();" /> -
							<input class="phone" type="tel" name="phone3" maxlength="4" onkeypress="onlyNumber();" />
							<button type="button" id="phone_send_check" class="button" onclick="sendSMSphone();">SMS인증</button>
							<div>
								<label style="padding-left:5px"> 
								<input type="checkbox" name="sms"/>
								<span> SMS를수신하겠습니다.</span>
								</label>
							<p class="info_box_font">SMS 수신에 동의하시면 여러가지 할인혜택과 각종 이벤트 정보를 받아보실 수 있습니다.<br>회원가입관련,주문배송관련 등의 SMS는 수신동의와 상관없이 구매 회원에게 발송됩니다.<br /></p>
							</div>
						</div>
					</div>
					<div style="display:none;">
						<div>인증번호</div>
						<div>
							<input class="phone" name="user_phone_check" type="tel"  maxlength="4" placeholder="  인증번호를 입력해주세요." style="width:40%;"> 
							<input type="hidden" name="phone_check" id="phone_check" value="">
							<button type="button" id="phone_get_check" class="button" onclick="check_phone_sms()">인증하기</button>
						</div>
					</div>
					<div class="info_box">
					<div>주소</div>
						<div>
							<input class="addr" type="text" name="addr" placeholder="   주소를 입력해주세요"  />
							<input class="addr" type="text" name="addrNumber" placeholder="   우편번호 입력해주세요"  />
						</div>
					</div>
					<div>
						<input class="button" type="submit" value="입력완료" id="complete" />
					</div>
				</fieldset>
			</form>
		</div>
		
		<div id="footer">
			<%@ include file="/views/section/footer/footer.jsp"%>
		</div>
		
	</div>

	<script>
		$(function() {
			
			$('select[name=find_email]').change(function(){
				$('input[name=email2]').css('background-color', 'rgb(246,246,246)');
				$('input[name=email2]').prop('readonly','true');
				var email=$(this).val();
				$('input[name=email2]').val(email);
			});
			
			$('#idCheck').click(function(){
				var $userId = $('input[name=userId]');
				if(!/^[a-zA-Z0-9]{6,20}$/.test($userId.val())){
					alert("아이디는 영문/숫자포함 6~20자로 입력해주세요!");
					return false;
				}else{
					window.open("idCheckForm.jsp","checkForm","width=450, height=200");
					$(this).next().val('y');
				}
				
			});
			
			$('#nicknameCheck').click(function(){
				var $userNickname = $('input[name=userNickname]');
				if(!/^[가-힣0-9]{2,6}$/.test($userNickname.val())){
					alert('닉네임은 한글/숫자포함 2~6자 로 입력해주세요!');
					return false;
				}else{
					window.open("nicknameCheckForm.jsp","checkForm","width=450, height=200");
					$(this).next().val('y');
				}
			});
			
				
			$('#member_form').submit(function() {
				var $userId = $('input[name=userId]');
				var $userPw = $('input[name=userPw]');
				var $userPw_re = $('input[name=userPw_re]');
				var $userName = $('input[name=userName]');
				var $userNickname = $('input[name=userNickname]');
				var $addr_number = $('input[name=addr_number]');
				
				if(!/^[a-zA-Z0-9]{6,20}$/.test($userId.val())){
					alert("아이디는 영문/숫자포함 6~20자로 입력해주세요!");
					return false;
				}else if(!/^[a-zA-Z0-9]{6,20}$/.test($userPw.val())){
					alert("비밀번호는 영문/숫자포함 6~20자로 입력해주세요!");
					return false;
				}else if($userPw.val()!=$userPw_re.val()){
					alert("비밀번호와 비밀번호 확인값이 다릅니다!");
					return false;
				}else if(!/^[가-힣]{2,5}$/.test($userName.val())){
					alert("이름은 2글자이상 한글만 입력가능합니다!");
					return false;
				}else if(!/^[가-힣0-9]{2,6}$/.test($userNickname.val())){
					alert('닉네임은 한글/숫자포함 2~6자 로 입력해주세요!');
					return false;
				}else if(!/^[0-9]{2,5}$/.test($addrNumber.val())){
					alert('우편번호는 숫자만 입력해주세요');
					return false;
				}
				return true;
			
			});

			$('input').focusin(function(){
				$(this).css('outline','none');
				$(this).css('border','2px solid rgb(169, 120, 254)');
				
			});
			$('input').focusout(function(){
				$(this).css('outline','none');
				$(this).css('border','2px solid lightgrey');
			});
			
			
		});
		
		
	</script>
	<script>
	
	//submit 전에 확인
	function maincheck() {
	   if($('input[name=pilsucheck]').val()=='n'){
	      alert('중복확인을 해주세요');
	      return false;
	      }
	      
	}
	</script>

</body>
</html>