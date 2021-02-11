<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="/resources/css/member/login/findPw.css" rel="stylesheet" type="text/css"/>
<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
</head>
<body>
   <div id="wrapper">
      <div id="header">
         <%@ include file="/views/section/header/headerComuPage.jsp" %>
      </div>
      <div id="navigator">
            <%-- <%@ include file="/views/sections/navigator.jsp" %> --%>
         <%@ include file="/views/section/navi/naviShop.jsp"%> 

      </div>
      <div id="container">
         <div id="find_box">
         
            <ul>
               <li id="click_id"><a href="/views/member/login/findId.jsp">아이디찾기</a></li>
               <li id="click_pw"><a href="/views/member/login/findId.jsp">비밀번호 찾기</a></li>
            </ul>
            <%   
            
            String return_email = (String)request.getAttribute("return_email");
            String userNameEmail = (String)request.getAttribute("userNameEmail");
            String userIdEmail = (String)request.getAttribute("userIdEmail");
            String email = (String)request.getAttribute("email");
            String userNamePhone = (String)request.getAttribute("userNamePhone");
            String userIdPhone = (String)request.getAttribute("userIdPhone");
            String phone = (String)request.getAttribute("phone");
            String authKey = (String)request.getAttribute("authKey");
            
            System.out.println(authKey);
            
             %>

            <div id="find_Pw">
               <form action="/newPW.kh" method="post" id="find_Pw_form">
               <fieldset>
                  <legend><h2>등록된 정보로 임시 비밀번호 찾기</h2></legend>
                  <div id=check_box>
                  </div>
                  
                  <%if(return_email.equals("true")){ %>
                  <div  id="email_box_pw">
                  <table class="find_input" border="1px">
                     <tr>
                     <th>이름</th>
                     <td><input type="text" name="userNameEmail" value="<%=userNameEmail%>" readonly/></td>
                     </tr>
                     <tr>
                     <th>아이디</th>
                     <td><input type="text" name="userIdEmail" value="<%=userIdEmail%>" readonly/></td>
                     </tr>
                     <tr>
                     <th>이메일</th>
                     <td><input type="text" name="email" value="<%=email%>" readonly/></td>
                     </tr>
                     
                     <tr>
                     <th>인증번호</th>
                     <td><input type="text" name="authEmail" /></td>
                     </tr>
                     <tr>
                     <th>새로운 비밀번호 입력</th>
                     <td><input type="password" name="newPw_Email" /></td>
                     </tr>
                     <tr>
                     <th>새로운 비밀번호 재입력</th>
                     <td><input type="password" name="newPw_re_Email" /></td>
                     </tr>
                     
                  </table>
                  </div>
                  
                  <%} else{ %>
                 	<script>
                 		$(function(){
                 			$('#phone_box_pw').css('display','block');
                 		});
                 	</script>
                 	
                  <div  id="phone_box_pw">
                  <table class="find_input" border="1px">
                     <tr>
                     <th>이름</th>
                     <td><input type="text" name="userNamePhone" value="<%=userNamePhone%>" readonly/></td>
                     </tr>
                     <tr>
                     <th>아이디</th>
                     <td><input type="text" name="userIdPhone" value="<%=userIdPhone%>" readonly/></td>
                     </tr>
                     <tr>
                     <th>휴대폰</th>
                     <td><input type="text" name="phone" value="<%=phone%>" readonly/></td>
                     </tr>
                     
                     <tr>
                     <th>인증번호</th>
                     <td><input type="text" name="authPhone" /></td>
                     </tr>
                     <tr>
                     <th>새로운 비밀번호 입력</th>
                     <td><input type="password" name="newPw_Phone" /></td>
                     </tr>
                     <tr>
                     <th>새로운 비밀번호 재입력</th>
                     <td><input type="password" name="newPw_re_Phone" /></td>
                     </tr>
                     
                  </table>
                  </div>
                  <%} %>
                  <button type="submit" id="testBtn">확인</button>
               </fieldset>
               </form>
            </div>
            
            
            
         </div>
      </div>
      <div>
         <%@ include file="/views/section/footer/footer.jsp" %>
      </div>
   </div>
   
      <script type="text/javascript">

      function showDiv(element){
         if(element.checked == true && element.value == "email"){
            document.getElementById("email_box").style.display = "block";   
            document.getElementById("phone_box").style.display = "none";
         }
         if(element.checked == true && element.value == "phone"){
            document.getElementById("email_box").style.display = "none";   
            document.getElementById("phone_box").style.display = "block";
         }
         if(element.checked == true && element.value == "email"){
            document.getElementById("email_box_pw").style.display = "block";   
            document.getElementById("phone_box_pw").style.display = "none";
         }
         if(element.checked == true && element.value == "phone"){
            document.getElementById("email_box_pw").style.display = "none";   
            document.getElementById("phone_box_pw").style.display = "block";
         }         
      }
      
     </script>
     <script>
     $(function() {
			$('#find_Pw_form').submit(function() {
				var authKey = <%=authKey%>; 
				var return_email = <%=return_email%>;
				
				if(return_email){
					var $newPw_Email = $('input[name=newPw_Email]');
					var $newPw_re_Email = $('input[name=newPw_re_Email]');
					var $authEmail = $('input[name=authEmail]');
					
					if($authEmail.val() != authKey){
						alert("올바르지 않은 인증키입니다");
						return false;
					}else if(!/^[a-zA-Z0-9]{6,20}$/.test($newPw_Email.val())){
						alert("비밀번호는 영문/숫자포함 6~20자로 입력해주세요!");
						return false;
					}else if($newPw_Email.val() != $newPw_re_Email.val()){
						alert("비밀번호와 비밀번호 확인값이 다릅니다!");
						return false;
					}
				}else{
					var $newPw_Phone = $('input[name=newPw_Phone]');
					var $newPw_re_Phone = $('input[name=newPw_re_Phone]');
					var $authPhone = $('input[name=authPhone]');
					
					if($authPhone.val() != authKey){
						alert("올바르지 않은 인증키입니다");
						return false;
					}else if(!/^[a-zA-Z0-9]{6,20}$/.test($newPw_Phone.val())){
						alert("비밀번호는 영문/숫자포함 6~20자로 입력해주세요!");
						return false;
					}else if($newPw_Phone.val() != $newPw_re_Phone.val()){
						alert("비밀번호와 비밀번호 확인값이 다릅니다!");
						return false;
					}
				}
				return true;
			});
     });
     
     </script>
    
</body>
</html>