package kr.or.iei.product.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.product.model.service.ProductService;

/**
 * Servlet implementation class ProductDeleteServlet
 */
@WebServlet("/productDelete.kh")
public class ProductDeleteServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // 상품 넘버 / 이미지 넘버 받아오기
      int productNo=Integer.parseInt(request.getParameter("productNo"));
      int imgNo=Integer.parseInt(request.getParameter("imgNo"));
      
		/* System.out.println("상품번호 : "+productNo+" // 이미지 번호 : "+imgNo); */
      
      
      // 비즈니스 로직 처리
      boolean result=new ProductService().deleteProduct(productNo, imgNo);
      
      RequestDispatcher view=request.getRequestDispatcher("/views/admin/product/productDelete.jsp");
      if(result==true) {
         request.setAttribute("result", true);
      }
      else {
         request.setAttribute("result", false);
      }
      view.forward(request, response);
      
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      doGet(request, response);
   }

}