<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="kr.or.iei.product.model.vo.*" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>  <!-- 아이콘 가져오는 CDN -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="/resources/css/admin/product/list.css" rel = "stylesheet" type ="text/css"/>
<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin="anonymous"></script>
</head>
<body>

<%
	ProductPageData ppd = (ProductPageData)request.getAttribute("pageData");
	ArrayList<ProductAll> list = ppd.getList();
	String pageNavi = ppd.getPageNavi();
%>
   <div id="wrapper">
      <div id="header">
         <%@ include file="/views/admin/section/header.jsp"%>
      </div>
      <div id="container">
         <div id="snb">
            <%@ include file="/views/admin/section/navi.jsp"%>
         </div>
         <div id="contents_box">
            <div id="pageTitle">
               <div id="pageTitle">
                  	<i class="fas fa-clipboard-list"></i> 상품 목록
               </div>
            </div>
           <form action="/searchProduct.kh" method="post">
					<div id="basicInfo">
						<div class="basicTitle">상세검색</div>
						<table cellspacing="0" cellpadding="0" width=100%;>
							<tr>
								<th>검색분류</th>
								<td colspan="2"><select name="category" style="height: 32px;">
										<option>상품명</option>
										<option>상품번호</option>
										<option>상품코드</option>
								</select> <input type="text" name="productSearch"
									style="width: 500px; height: 32px;" /></td>
							</tr>

							<tr>
								<th>상품등록일</th>
								<td colspan="2"><input type="date" name="startDate" style="height: 32px;"
									id="starDate" min="2000-01-01" max="2100-12-31" /> ~ <input
									type="date" name="endDate" style="height: 32px;" id="lastDate" min="2000-01-01"
									max="2100-12-31" /></td>
							</tr>

						</table>
						<div>
							<button id="submit" class="searchBtn">상품검색</button>
							<button type="button" class="searchBtn" onclick="location.href =  '/productAllList.kh';">초기화</button>						</div>
					</div>
				</form>
            <script>
      $(function(){
         $('#checkboxAll').click(function(){
            if($(this).prop('checked')){
               $('.checkboxse').prop('checked', true);
            } else {
               $('.checkboxse').prop('checked', false);
            }
         });
      });
   
   </script>

            <div id="basicInfo">
               <div class="basicTitle">상품목록</div>
               
                  <table cellspacing="0" cellpadding="0" width=100%;>
                     <tr>                     
                        <th>상품명</th>
                        <th>판매가</th>
                        <th>상품 등록일</th>
                        <th>수정</th>
                        <th>삭제</th>
                     </tr>
                     <%for(ProductAll productAll : list){ %>
                     
                        <tr>
                           
                           <td>
                              <div class="productImg">
                              <img src="/resources/images/product/<%=productAll.getImg().getChangedName()%>.png"  width=100% height=100% 
                              onerror="javascript:this.src='/resources/images/store/product/error.PNG'"/> 
                              </div> <a href="#" class="productName"><%=productAll.getProduct().getProductName()%></a>
                           </td>
                           <td style="width: 300px; padding: 0px; text-align: center;">
                              <%=productAll.getProduct().getProductPrice()%> 원
                           </td>
      
                           <td style="width: 300px; padding: 0px; text-align: center;">
                              <%=productAll.getpDate().getProductEnrollDate()%></td>
                           <th>
                           <form action="/productModifyPage.kh" method="post">
                                 <input type="submit"  class="productListBtn" value="수정"/>
                                 <input type="hidden" name="productNo" value="<%=productAll.getProduct().getProductNo() %>"/>
                            </form>
                           </th>
                           <th>
                              <form action="/productDelete.kh" method="post">
                                 <input type="submit"  class="productListBtn" value="삭제" />
                                 <input type="hidden" name="productNo" value="<%=productAll.getProduct().getProductNo() %>"/>
                                 <input type="hidden" name="imgNo" value="<%=productAll.getProduct().getImgNo() %>"/>
                              </form>
                           </th>
                        </tr>
                    
                     <% } %>                                     
                  </table>
                  <table cellspacing="0" cellpadding="0" width=100%;>
                  		<tr>
							<td align="center"><%=pageNavi %></td>
						</tr> 
                  </table>
               
            </div>
         </div>
      </div>
      <div id="footer">
         <%@ include file="/views/section/footer/footer.jsp"%>
      </div>
   </div>
</body>
</html>