<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ page import="kr.or.iei.write.model.vo.WriteAll" %>
<%@ page import="kr.or.iei.write.model.vo.CommentsAll" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="/resources/css/community/detail/detail.css" rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic&display=swap" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.js"
   integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc="
   crossorigin="anonymous"></script>
</head>
<body>
   <% 
   WriteAll wAll  = (WriteAll)request.getAttribute("wAll");
   ArrayList<CommentsAll> coList = (ArrayList<CommentsAll>)request.getAttribute("coList");
   ArrayList<WriteAll> pList = (ArrayList<WriteAll>)request.getAttribute("pList");
   %>

<div id="header">
         <%@ include file="/views/section/header/headerComuPage.jsp" %>
      </div>
      <%@ include file="/views/section/navi/naviComu.jsp"%>
   <div id="wrapper">

      <div class="contents">
         <!-- <div id="contents-left"></div> -->
         <div id="contents-main">
            <div class="main-content">
               <div class="side-title">
                  <div class="communityDetail-indent">
                     <p>
                        <span>커뮤니티</span>
                     </p>
                     <ul>
                        <li><span><%=wAll.getWrite().getWriteDate() %></span></li>
                        <li><span>좋아요</span> <span><%=wAll.getWrite().getWriteLike() %></span></li>
                        <li><span>조회수</span> <span id="totalComment"><%=wAll.getWrite().getWriteViewCount() %></span></li>
                     </ul>
                  </div>
               </div>

               <div id="sub-section-contentMain" class="sub-section">
                  <p><%=wAll.getWrite().getWriteExplain() %></p> 
                  <%if(mAll!=null&&mAll.getM().getMemberNo()== wAll.getWrite().getMemberNo()){%>
                        <form action="/modifyPost.kh" method="post" id="modifyForm">
                        <input type="hidden"  name="writeNo" value="<%=wAll.getWrite().getWriteNo()%>"/>
                        <input type="hidden"  name="productNo" value="<%=wAll.getWrite().getProductNo() %>"/>
                        <input type="button" id="modifyBtn" value="수정"/>
                        </form>
                        <form action="/deletePost.kh" method="post" id="deleteForm">
                        <input type="hidden" name="writeNo" value="<%=wAll.getWrite().getWriteNo() %>"/>
                        <input type="button" id="deleteBtn" value="삭제"/>
                        </form>
                  <%} %>
               </div>
<script>
$(function(){
   $('#modifyBtn').click(function(){
      $(this).parent().submit();
   })
   $('#deleteBtn').click(function(){
      var result = confirm('정말삭제하시겠습니까?');
      
      if(result==true){
         $(this).parent().submit();
      }else{
         location.replace('/detailPrint.kh?write_no=<%=wAll.getWrite().getWriteNo()%>&productNo=<%=wAll.getWrite().getProductNo() %>');
      }
   })
})
</script>               
               
         
               <!-- <div class="hashtag-section">
         <button>
            <span>#가죽공예</span>
         </button>
         <button>
            <span>#지갑</span>
         </button>
      </div> -->
               <div class="sub-section full-size">
                  <div class="slide-wrapper">
                     <div class="slide-section">
                        <b class="slide-title">#태그된 상품</b>
                        <div class="responsive">
                           
                           <img width="20%" src="/resources/images/community/write/<%=wAll.getImg().getOriginalName() %>"  />
                        </div>
                     </div>
                  </div>
               </div>
               <div class="sub-section full-size">
                  <div class="slide-wrapper">
                     <div class="slide-section">
                        <b class="slide-title">#관련 커뮤니티 둘러보기</b>
                        <div class="responsive">
                        <%for(WriteAll writeAll : pList){ %>
                           <%-- <%=writeAll.getImg().getChangedName() %> --%>
                           <img width="20%" src="/resources/images/community/write/<%=writeAll.getImg().getOriginalName() %>" />
                        <%} %>
                        </div>
                     </div>
                  </div>
               </div>
               <div class="comment-section">
                  <%if(mAll!=null){ %>
                  <b class="comment-title"><span><%=coList.size() %></span>개의 댓글</b>
                  <div class="comment-write">
                     <form action="/postCommentWrite.kh" method="post">
                     <textarea id="commentArea" name="comments" cols="30"
                        rows="10" placeholder="댓글을 작성해보세요"></textarea>
                     <input type="hidden" name="writeNo" value="<%=wAll.getWrite().getWriteNo()%>"/>
                     <input type="hidden" name="productNo" value="<%=wAll.getWrite().getProductNo() %>"/>
                     <button class="submit">등록</button>
                     
                     
                     </form>
                  </div>
                  <%}else{ %>
                  <b class="comment-title"><span><%=coList.size() %></span>개의 댓글</b>
                  <div class="comment-write">
                     <textarea id="commentArea" name="reply_contents" cols="30"
                        rows="10"  placeholder="로그인후에 댓글을 작성해보세요" readOnly></textarea>
                  </div>
                  
                  <%} %>
                  <div class="text-comment">
                     <ul class="active">
                     <%if(!coList.isEmpty()){ %>
                        <li class="mine">
                           <%for(CommentsAll coAll : coList) {%>
                           <div class="comment-wrapper">
                              <div class="profile">
                                 <p><%=coAll.getMemberNickName() %></p>
                                 <ul>
                                    <li><%=coAll.getCommentsDate() %></li>
                                 </ul>
                              </div>
                              <div class="comment">
                              <div class="comment-text reply_val_2076">
                                    <pre id="content_<%=coAll.getCommenstNo()%>"><%=coAll.getCommentsText() %></pre>
                              </div>
                              <%if(mAll!=null && mAll.getM().getMemberNo()==coAll.getMemberNo()) {%>
                              <div id="commentBtn">
      
                              <!-- 수정 폼태그 -->
                              <form action="/commentModify.kh" method="post"
                                 style="display: inline">
                                 <button type="button" class="commentModifyBtn">수정</button>
                                 <input type="hidden" name="commentNo" value="<%=coAll.getCommenstNo() %>" /> 
                                 <input type="hidden" name="co_content" />
                                 <input type="hidden" name="writeNo" value="<%=coAll.getWriteNo() %>" />
                                 <input type="hidden" name="productNo" value="<%=wAll.getWrite().getProductNo() %>"/>
                              </form>
                              <!-- 삭제 폼태그 -->
                              <form action="/commentDelete.kh" method="post"
                                 style="display: inline">
                                 <button type="button" class="commentDeleteBtn">삭제</button>
                                 <input type="hidden" name="commentNo" value="<%=coAll.getCommenstNo() %>" />
                                 <input type="hidden" name="writeNo" value="<%=coAll.getWriteNo() %>" />
                                 <input type="hidden" name="productNo" value="<%=wAll.getWrite().getProductNo() %>"/>
                              </form>
                              </div>
                              <%} %>
                                 
                              </div>
                           
                           </div>
                           <%} %>      
                        </li>
                        <%}else{ %>
                        <p>댓글이없습니다.</p>
                        <%} %>
                     </ul>
                  </div>
               </div>
            </div>
         </div>
         <!-- <div id="contents-right"></div> -->
      </div>
   <script>
      $(function(){
         $('.commentModifyBtn').click(function(){      
            var text =$(this).text();
               if(text=='수정'){   

               //댓글 번호 가져오기
               var commentNo = $(this).next().attr('value');   
               //댓글 내용 가져오기
               var comment = $('#content_'+commentNo).text();
               
               $('#content_'+commentNo).html("<input type='text' name='comment' value='"+comment+"' />");
               $(this).text('완료');
               $(this).parent().next().children('button').text('취소');
         
               
            }else if(text=='완료'){
               
               //수정된 데이터를 가져와서 hidden 값에 적용 시킨후 
               var commentNo = $(this).next().attr('value');
               var co_content=$('#content_'+commentNo).children().val();
               $('input[name=co_content]').val(co_content);
               
               //submit동작을 시켜주겠다.
               $(this).parent().submit();
            
            }   
            });
            $('.commentDeleteBtn').click(function(){
            var text =$(this).text();            
            if(text=='삭제'){
               //눌렀을때 삭제라는 버튼으로 되어있었다면 삭제라는 로직을 동작
               var result = window.confirm("해당 댓글을 삭제하시겠습니까?");
               if(result==true){
                  $(this).parent().submit();
               }
            }else if(text=='취소'){
               //눌렀을때 취소라는 버튼으로 되어있었다면! 해당 페이지를 재로드 혹은 수정기능 취소 (여기서는 페이지 재로드)
               location.replace('/detailPrint.kh?write_no=<%=wAll.getWrite().getWriteNo()%>&productNo=<%=wAll.getWrite().getProductNo() %>');
            }
         });
      });      
      
      </script>   
      <script type="text/javascript">
         $(function() {
            $(window).scroll(function() {
               if ($(this).scrollTop() != 0) {
                  $('#backtotop').fadeIn();
               } else {
                  $('#backtotop').fadeOut();
               }
            });

            $('#backtotop').click(function() {
               $('body,html').animate({
                  scrollTop : 0
               }, 800);
            });
            $("#heartClick").click(function(){
               $('#heartForm').submit();
               $('#heart').css('color','red');
               
            });
         });
      </script>


      <!-- 여기서부터 푸터 -->
      
      <%if(mAll!=null) {%>
      <div id="heartClick">
         <form action="/myHeart.kh" method="post" id="heartForm">
         <i class="fas fa-heart" id="heart" style="font-size:50px;"></i>
         <input type="hidden" name="write_no" value="<%=wAll.getWrite().getWriteNo()%>"/>
         <input type="hidden" name="productNo" value="<%=wAll.getWrite().getProductNo() %>"/>   
         </form>      
      </div>
      <%} %>
      <div id="backtotop">
         <img src="/resources/images/community/detail/up-arrow.png">
      </div>

   </div>
   <div id="footer">
         <%@ include file="/views/section/footer/footer.jsp"%>
      </div>
</body>
</html>