package kr.or.iei.product.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.product.model.service.ProductService;
import kr.or.iei.product.model.vo.ProductAll;

/**
 * Servlet implementation class ProductModifyPageServlet
 */
@WebServlet("/productModifyPage.kh")
public class ProductModifyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductModifyPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 값 받아오기
		int productNo=Integer.parseInt(request.getParameter("productNo"));
		
		
		// 비즈니스 로직
		ProductAll productAll=new ProductService().selectOneProduct(productNo);
		
		// 결과 처리
		RequestDispatcher view=request.getRequestDispatcher("/views/admin/product/modify.jsp");
		request.setAttribute("product", productAll);
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
