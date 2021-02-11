<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="kr.or.iei.product.model.vo.ProductAllSub" %>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
<link href="/resources/css/store/pay/pay.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>   <!-- 결제 api cdn -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

   <%
      ProductAllSub productAS=(ProductAllSub)request.getAttribute("productInfo");
      int totalPrice=Integer.parseInt(request.getAttribute("totalPrice").toString());
      String selectedOpt=request.getAttribute("selectedOpt").toString();
      int ordersNum=totalPrice/productAS.getProduct().getProductPrice();
   %>
<div id="wrapper">
   <div id="header">
      <%@ include file="/views/section/header/headerShopPage.jsp" %>
   </div>
   <div id="navigator">
      <%@ include file="/views/section/navi/naviShop.jsp" %>
   </div>
   <div id="contents">
      <div id="title">주문/결제</div>
      <div id="delivery">
         <div id="delivery-title">배송 정보</div>
         <div id="delivery-info">
            <form action="/insertOrder.kh" method="post" id="insertOrder">
            <input type="hidden" name="productNo" value="<%=productAS.getProduct().getProductNo() %>"/>
            <input type="hidden" name="ordersNum" value="<%=ordersNum %>"/>
               <table>
                  <tr>
                     <th>주문자</th>
                     <td><input type="text" name="orderName" value="<%=mAll.getM().getMemberName() %>"/></td>
                  </tr>
                  <!-- 
                  <tr>
                     <th>배송지선택</th>
                     <td><input type="button" name="basicDelivery" value="기본배송지"/><input type="button" name="newDelivery" value="신규배송지"/></td>
                  </tr>
                   -->
                  <tr>
                     <th>수령인</th>
                     <td><input type="text" name="name" placeholder="이름"/></td>
                  </tr>
                  <tr>
                     <th>연락처</th>
                     <td><input type="text" name="phone" placeholder="'-' 없이 숫자만 입력해주세요."/></td>
                  </tr>
                  <tr>
                     <th>주소</th>
                     <td>
                        <input type="text" id="sample6_postcode" placeholder="우편번호">
                        <input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
                        <input type="text" id="sample6_address" placeholder="주소"><br>
                        <input type="text" id="sample6_detailAddress" placeholder="상세주소">
                        <input type="text" id="sample6_extraAddress" placeholder="참고항목">
                        <input type="hidden" id="address" name="address" value=""/>
                     </td>
                  </tr>
                  <tr>
                     <th>배송시 요청사항</th>
                     <td><textarea cols="100" name="demand" placeholder="배송시 요청사항"></textarea></td>
                  </tr>
               </table>
            </form>
         </div>
      </div>
      <div id="product">
         <div id="product-title">주문상품 정보</div>
         <div id="product-info">
            <table>
               <tr>
                  <th colspan="2">상품정보/수량</th>
                  <th>상품금액</th>
                  <th>배송비</th>
               </tr>
               <tr>
                  <td rowspan="2" id="product-img"><img src="/resources/images/product/<%=productAS.getImg().getChangedName() %>.png"/></td>
                  <td><%=productAS.getProduct().getProductName() %></td>
                  <td rowspan="2"><%=totalPrice %> 원 </td>
                  <td rowspan="2">2,500원</td>
               </tr>
               <tr>
                  <td><%=selectedOpt %></td>
               </tr>
            </table>
         </div>
      </div>
      <div id="terms">
         약관동의<br><br>
         <input type="checkbox" id="checkAll" value="전체동의"/> 전체동의<br>
         <input type="checkbox" class="normal essentialCheck" value="개인정보 제3자 제공(필수)"/> 개인정보 제3자 제공(필수) <button id="termsBtn">보기</button>
         <div id="terms-view">
            <table>
               <tr>
                  <th>제공받는자</th>
                  <td>Personal Hobby</td>
               </tr>
               <tr>
                  <th>목적</th>
                  <td>상품 및 경품(서비스) 배송(전송), 반품, 환불, 고객상담 등 정보통신서비스제공계약 및 전자상거래(통신판매) 계약의 이행을 위해 필요한 업무의 처리</td>
               </tr>
               <tr>                     
                  <th>항목</th>
                  <td>성명, 전화번호, 휴대폰번호, 배송지 주소, 이메일 주소</td>
               </tr>
               <tr>
                  <th>보유기간</th>
                  <td>서비스 이용기간 동안 보관</td>
               </tr>
            </table>
         </div>
      </div>
      <div id="pay">
         <button type="button" id="payBtn">결제하기</button>
         <button type="button" id="resetBtn">취소</button>
      </div>
   </div>
   <div id="footer">
      <%@ include file="/views/section/footer/footer.jsp" %>
   </div>
</div>

   <script>
      $(function(){
         // 뒤로가기 버튼
         $("#resetBtn").click(function(){
            history.back(-1);
         });
         
         // 체크박스 전체 선택
         $("#terms").on("click", "#checkAll", function () {
             $(this).parents("#terms").find("input").prop("checked", $(this).is(":checked"));
         });

         // 체크박스 개별 선택
         $("#terms").on("click", ".normal", function() {
             var is_checked = true;

             $("#terms .normal").each(function(){
                 is_checked = is_checked && $(this).is(":checked");
             });

             $("#checkAll").prop("checked", is_checked);
         });
         
         // 약관 창 토글 버튼
         $('#termsBtn').click(function(){
            var btn = $(this);
            var view = document.getElementById("terms-view");
            if(btn.text() == '보기'){
               view.style.display='block';
               btn.text('닫기');
            }else if(btn.text() == '닫기'){
               view.style.display='none';
               btn.text('보기');
            }
         });
      });
   </script>
   
   <!-- 우편번호 찾기 api -->
   <script>
       function sample6_execDaumPostcode() {
          
          var themeObj = {
                   bgColor: "#020202" //바탕 배경색
                   //searchBgColor: "", //검색창 배경색
                   //contentBgColor: "", //본문 배경색(검색결과,결과없음,첫화면,검색서제스트)
                   //pageBgColor: "", //페이지 배경색
                   //textColor: "", //기본 글자색
                   //queryTextColor: "", //검색창 글자색
                   //postcodeTextColor: "", //우편번호 글자색
                   //emphTextColor: "", //강조 글자색
                   //outlineColor: "", //테두리
                };
          //테마 관련 오브젝트 생성
          
         
          
           new daum.Postcode({
              theme: themeObj,
               oncomplete: function(data) {
                   // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
   
                   // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                   // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                   var addr = ''; // 주소 변수
                   var extraAddr = ''; // 참고항목 변수
   
                   //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                   if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                       addr = data.sido + " " + data.sigungu;
                   } else { // 사용자가 지번 주소를 선택했을 경우(J)
                       addr = data.sigungu;
                   }
   
                   // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                   if(data.userSelectedType === 'R'){
                       // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                       // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                       if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                           extraAddr += data.bname;
                       }
                       // 건물명이 있고, 공동주택일 경우 추가한다.
                       if(data.buildingName !== '' && data.apartment === 'Y'){
                           extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                       }
                       // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                       if(extraAddr !== ''){
                           extraAddr = ' (' + extraAddr + ')';
                       }
                       // 조합된 참고항목을 해당 필드에 넣는다.
                       document.getElementById("sample6_extraAddress").value = extraAddr;
                   
                   } else {
                       document.getElementById("sample6_extraAddress").value = '';
                   }
   
                   // 우편번호와 주소 정보를 해당 필드에 넣는다.
                   document.getElementById('sample6_postcode').value = data.zonecode;
                   document.getElementById("sample6_address").value = addr;
                   // 커서를 상세주소 필드로 이동한다.
                   document.getElementById("sample6_detailAddress").focus();
               }
           }).open();
       }
   </script>
   
   <script>
      $("#payBtn").click(function () {
         // 필수 동의 항목 체크됐는지 확인하기 위한 변수
         var essentialCheck=$(".essentialCheck").prop("checked");
         var postcode=null;
         // 주소 조합하기 위한 변수
         if($("#sample6_address").val()!=null){
            var address=$("#sample6_address").val()+" "+$("#sample6_detailAddress").val()+$("#sample6_extraAddress").val();
            $("#address").val(address);
            postcode=$("#sample6_postcode").val();
         }
         if(essentialCheck==true){
            var IMP = window.IMP; // 생략가능
            IMP.init('imp12279850');
            // 'iamport' 대신 부여받은 "가맹점 식별코드"를 사용
            // i'mport 관리자 페이지 -> 내정보 -> 가맹점식별코드
            IMP.request_pay({
               pg: 'inicis', // version 1.1.0부터 지원.
               /*
               'kakao':카카오페이,
               html5_inicis':이니시스(웹표준결제)
               'nice':나이스페이
               'jtnet':제이티넷
               'uplus':LG유플러스
               'danal':다날
               'payco':페이코
               'syrup':시럽페이
               'paypal':페이팔
               */
               pay_method: 'card',
               /*
               'samsung':삼성페이,
               'card':신용카드,
               'trans':실시간계좌이체,
               'vbank':가상계좌,
               'phone':휴대폰소액결제
               */
               merchant_uid: 'merchant_' + new Date().getTime(),
               name: '<%=productAS.getProduct().getProductName() %>',
               //결제창에서 보여질 이름
               amount: <%=totalPrice %>+2500,
               //가격
               buyer_email: '<%=mAll.getM().getMemberEmail() %>',
               buyer_name: '<%=mAll.getM().getMemberName() %>',
               buyer_tel: '<%=mAll.getM().getMemberPhone() %>',
               buyer_addr: address,
               buyer_postcode: postcode,
               /* 
               m_redirect_url: 'https://www.yourdomain.com/payments/complete'
                */
               /*
               모바일 결제시,
               결제가 끝나고 랜딩되는 URL을 지정
               (카카오페이, 페이코, 다날의 경우는 필요없음. PC와 마찬가지로 callback함수로 결과가 떨어짐)
               */
            }, function (rsp) {
               console.log(rsp);
               if (rsp.success) {
                  var msg = '결제가 완료되었습니다.\n';
                  /* 
                  msg += '고유ID : ' + rsp.imp_uid;
                  msg += '상점 거래ID : ' + rsp.merchant_uid;
                   */
                  msg += '결제 금액 : ' + rsp.paid_amount;
                  /* 
                  msg += '카드 승인번호 : ' + rsp.apply_num;
                   */
                  $("#insertOrder").submit();            // 결제 창 로직 성공하면 디비에 오더 데이터 넣기
               } else {
                  var msg = '결제에 실패하였습니다.';
                  msg += '에러내용 : ' + rsp.error_msg;
               }
               alert(msg);
            });
         }
         else{
            alert("필수 동의 항목에 동의하지 않으셨습니다.");
         }
      });
   </script>
   
</body>
</html>