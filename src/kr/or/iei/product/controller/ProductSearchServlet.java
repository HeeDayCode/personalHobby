package kr.or.iei.product.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.product.model.service.ProductService;
import kr.or.iei.product.model.vo.ProductAll;

/**
 * Servlet implementation class ProductSearchServlet
 */
@WebServlet("/searchProduct.kh")
public class ProductSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
		request.setCharacterEncoding("utf-8");
		
		//2. 웹상에서 보내온 데이터를 저장
		String keyword = request.getParameter("productSearch");
		String category = request.getParameter("category");
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		
		if(category.equals("상품명")) {
			ArrayList<ProductAll> list = new ProductService().searchProductName(keyword,startDate,endDate);
		
			request.setAttribute("productSelectList", list); 
			  
		}else if(category.equals("상품번호")) {
			// 상품번호이므로 int형으로 변환
			int keywordNo = Integer.parseInt(keyword);
			ArrayList<ProductAll> list = new ProductService().searchProductNo(keywordNo,startDate,endDate);
			
			request.setAttribute("productSelectList", list); 
		}else {
			int keywordNo = Integer.parseInt(keyword);
			ArrayList<ProductAll> list = new ProductService().searchProductCategoryNo(keywordNo,startDate,endDate);
			
			request.setAttribute("productSelectList", list); 
		}
		RequestDispatcher view = request.getRequestDispatcher("/views/admin/product/list2.jsp");
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
