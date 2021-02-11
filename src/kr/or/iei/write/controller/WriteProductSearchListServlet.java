package kr.or.iei.write.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.product.model.vo.ProductPageData;
import kr.or.iei.write.model.service.WriteService;

/**
 * Servlet implementation class WriteProductSearchListServlet
 */
@WebServlet("/productSearchList.kh")
public class WriteProductSearchListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteProductSearchListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 인코딩
		request.setCharacterEncoding("utf-8");
		
		// 파라미터값 가져오기
		String keyword = request.getParameter("keyword");

		// 현재 페이지 처리
		int currentPage;

		if(request.getParameter("currentPage")==null) {
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		
		ProductPageData ppd = new WriteService().selectProductInfo(keyword, currentPage);
		
		RequestDispatcher view = request.getRequestDispatcher("/views/community/write/productSearchForm.jsp");
		request.setAttribute("pageData", ppd);
		request.setAttribute("keyword", keyword);
		
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


















