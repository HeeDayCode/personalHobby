<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ page import="kr.or.iei.member.model.vo.MemberImg" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.5.1.js"
   integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
   crossorigin="anonymous"></script>
<link rel="stylesheet" href="/resources/css/member/myPage/modify.css">
<script type="text/javascript" src="/resources/js/profileModify.js"></script>
<script src="https://kit.fontawesome.com/822d8824d6.js"
   crossorigin="anonymous"></script>
<script>
   $(function() {
      $("#phoneBtn").click(function() {
         $("#smsNo").attr("disabled", false);
      });
   });
</script>
</head>
<body>

   <div id="wrapper">
      <header> <%@ include
         file="/views/section/header/headerComuPage.jsp"%>
      </header>
      <nav> <%@ include file="/views/section/navi/naviComu.jsp"%>
      <script src="https://code.jquery.com/jquery-3.5.1.js"
         integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
         crossorigin="anonymous"></script> </nav>
<%
   String fileName = (String)request.getAttribute("fileName");
%>

      <div id="middleWrapper">
         <aside>
         <div id="asideTitle">
            <p>
               <a href="/myPage.kh">마이페이지</a>
            </p>
         </div>
         <div id="asideMenu">
            <ul>
						<li><a href="/modifyPage.kh">회원 정보 수정</a></li>
						<li><a href="/OrdersList.kh">MY 쇼핑</a></li>
						<li><a href="/deliveryInfo.kh">배송 정보</a></li>
						<li><a href="/views/member/myPage/withdraw.jsp">회원 탈퇴</a></li>
					</ul>
         </div>
         </aside>
         <section> <!-- <div id="profileHeader">프로필헤더</div> -->
         <div id="modifyContainer">
            <div id="modifyTitle">
               <h2>정보수정</h2>
               <div id="withdrawWrap">
                  <a href="#"> <span>회원탈퇴하기</span> <img
                     src="/resources/images/member/myPage/ico_arrow_more.png"
                     alt="오른쪽 애로우 아이콘" />
                  </a>
               </div>
            </div>
            <div id="modifyPanel">
               <form action="/Modify.kh" method="post"
                  onsubmit="return maincheck()" name="modify">
                  <div>
                     <div class="inputLable">아이디</div>
                     <div class="inputWrap">
                        <input type="text" name="memberId"
                           value="<%=mAll.getM().getMemberId()%>" readonly="readonly"/>
                     </div>
                  </div>
                  <div>
                     <div class="inputLable">비밀번호</div>
                     <div class="inputWrap">
                        <input type="password" id="password1" name="memberPw"
                           maxlength="20" placeholder="비밀번호를 입력해주세요"> <span>공백
                           없는 영문, 숫자포함 6~20자</span>
                     </div>
                  </div>
                  <div>
                     <div class="inputLable">비밀번호 확인</div>
                     <div class="inputWrap">
                        <input type="password" id="password2" name="memberPwCheck"
                           maxlength="20" placeholder="비밀번호를 다시 한번 입력해 주세요."> <span>비밀번호를
                           다시 한 번 입력해주세요.</span>
                     </div>
                  </div>

                  <script>
                     $(function() {
                        $('#submitBtn')
                              .click(
                                    function() {
                                       var pw1 = $("#password1")
                                             .val();
                                       var pw2 = $("#password2")
                                             .val();

                                       if (pw1 != pw2) {
                                          alert("비밀번호가 일치하지 않습니다.");
                                          return false;
                                       }
                                       var $memberPw = $('input[name=memberPw]');
                                       var $memberName = $('input[name=memberName]');
                                       var $memberNickname = $('input[name=memberNickname]');

                                       if (!/^[a-zA-Z0-9]{6,20}$/
                                             .test($memberPw
                                                   .val())) {
                                          alert("비밀번호는 영문/숫자포함 6~20자로 입력해주세요!");
                                          return false;
                                       } else if (!/^[가-힣]{2,5}$/
                                             .test($memberName
                                                   .val())) {
                                          alert("이름은 2글자이상 한글만 입력가능합니다!");
                                          return false;
                                       }
                                       return true;

                                    });

                     });
                  </script>


                  <div>
                     <div class="inputLable">이름</div>
                     <div class="inputWrap">
                        <input type="text" name="memberName"
                           value="<%=mAll.getM().getMemberName()%>" readonly="readonly"/>
                     </div>
                  </div>
                  <div>
                     <div class="inputLable">닉네임</div>
                     <div class="inputWrap">
                        <input type="text" name="memberNickname" maxlength="6"
                           value="<%=mAll.getM().getMemberNickname()%>"/>
                     </div>
                  </div>
                  <div>
                     <div class="inputLable">이메일</div>
                     <div class="inputWrap">
                        <input type="email" name="memberEmail"
                           value="<%=mAll.getM().getMemberEmail()%>">
                     </div>
                  </div>
                  <div>
                     <div class="inputLable">휴대폰번호</div>
                     <div class="inputWrap">
                        <input type="text" name="phone1" maxlength="3"
                           value="<%=mAll.getM().getMemberPhone().substring(0, 3)%>"
                           id="phoneInput"> <input type="text" name="phone2"
                           maxlength="4"
                           value="<%=mAll.getM().getMemberPhone().substring(3, 7)%>"
                           id="phoneInput"> <input type="text" name="phone3"
                           maxlength="4"
                           value="<%=mAll.getM().getMemberPhone().substring(7, 11)%>"
                           id="phoneInput">
                     </div>
                  </div>
                  <div>
                     <div class="inputLable">주소</div>
                     <div class="inputWrap">
                        <input type="text" id="addr" name="address"
                           value="<%=mAll.getMa().getAddress()%>" />
                     </div>
                     <div class="inputLable">우편번호</div>
                     <div class="inputWrap">
                        <input type="text" id="addr" name="postNum"
                           style="width: 100px;" value="<%=mAll.getMa().getPostNum()%>" />
                     </div>
                  </div>
                  <div>
                     <div class="inputLable">프로필사진</div>
                     <div class="inputWrap">   
                     <div id="profileImgWindow">
                     <img src="/resources/images/member/myPage/<%=fileName%>" width=100% height=100% />
                     </div>
                        <input type="button" id="imgBtn" value="파일업로드"/>
                     </div>
                  </div>
                  <div id="modifyBtnPanel">
                     <input type="submit" value="정보수정" id="submitBtn">
                  </div>
               </form>
            </div>
         </div>
         </section>
      </div>
      <footer> <%@ include file="/views/section/footer/footer.jsp"%>
      </footer>
      <script>
         $(function(){
            $('#imgBtn').click(function(){
               window.open("/views/member/myPage/profileUpload.jsp","uploadForm","width=450, height=200");
            })
         });
      </script>
   </div>
</body>
</html>