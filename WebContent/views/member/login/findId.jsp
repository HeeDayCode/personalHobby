<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="/resources/css/member/login/findId.css" rel="stylesheet" type="text/css"/>
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

<script src="https://code.jquery.com/jquery-3.5.1.js"
      integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
      crossorigin="anonymous"></script>
   

      </div>
      <div id="container">
         <div id="find_box">
         
            <ul>
               <li id="click_id">아이디찾기</li>
               <li id="click_pw">비밀번호 찾기</li>
            </ul>
            
            <div id="find_Id">
               <form action="/findId.kh" method="post">
               <fieldset>
                  <legend><h2>등록된 정보로 아이디 찾기</h2></legend>
                  <p>회원가입시 등록한 정보로 찾을수 있습니다.</p>
                  <div id=check_box>
                  <input type="radio" name="find_gb" value="email" onclick="showDiv(this);" checked/>이메일 
                  <input type="radio" name="find_gb" value="phone" onclick="showDiv(this);" />휴대폰
                  </div>
                  <div  id="email_box">
                  <table class="find_input" border="1px">
                     <tr>
                     <th>이름</th>
                     <td><input type="text" name="userNameEmail" /></td>
                     </tr>
                     <tr>
                     <th>이메일</th>
                     <td><input type="text" name="email" /></td>
                     </tr>
                  </table>
                  </div>
                  <div  id="phone_box">
                  <table class="find_input" border="1px">
                     <tr>
                     <th>이름</th>
                     <td><input type="text" name="userNamePhone" /></td>
                     </tr>
                     <tr>
                     <th>휴대폰</th>
                     <td><input type="text" name="phone" /></td>
                     </tr>
                  </table>
                  </div>
                  <button type="submit">확인</button>
               </fieldset>
               </form>
            </div>
            
            
            
            <div id="find_Pw">
               <form action="/findPw.kh" method="post">
               <fieldset>
                  <legend><h2>등록된 정보로 임시 비밀번호 찾기</h2></legend>
                  <p>회원가입시 등록한 정보로 찾을수 있습니다.</p>
                  <div id=check_box>
                  <input type="radio" name="find_gb" value="email" onclick="showDiv(this);" checked/>이메일 
                  <input type="radio" name="find_gb" value="phone" onclick="showDiv(this);" />휴대폰
                  </div>
                  <div  id="email_box_pw">
                  <table class="find_input" border="1px">
                     <tr>
                     <th>이름</th>
                     <td><input type="text" name="userNameEmail"/></td>
                     </tr>
                     <tr>
                     <th>아이디</th>
                     <td><input type="text" name="userIdEmail" /></td>
                     </tr>
                     <tr>
                     <th>이메일</th>
                     <td><input type="text" name="email" /></td>
                     </tr>
                  </table>
                  </div>
                  <div  id="phone_box_pw">
                  <table class="find_input" border="1px">
                     <tr>
                     <th>이름</th>
                     <td><input type="text" name="userNamePhone" /></td>
                     </tr>
                     <tr>
                     <th>아이디</th>
                     <td><input type="text" name="userIdPhone" /></td>
                     </tr>
                     <tr>
                     <th>휴대폰</th>
                     <td><input type="text" name="phone" /></td>
                     </tr>
                  </table>
                  </div>
                  <button type="submit">확인</button>
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
      $(function(){
         $('#click_id').click(function(){
            $('#find_Id').css('display','block');
            $('#find_Pw').css('display','none');
         });
         $('#click_pw').click(function(){
            $('#find_Pw').css('display','block');
            $('#find_Id').css('display','none');
         });
      });
     </script>
</body>
</html>