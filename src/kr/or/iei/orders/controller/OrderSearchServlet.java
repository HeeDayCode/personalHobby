package kr.or.iei.orders.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.orders.model.service.OrderService;
import kr.or.iei.orders.model.vo.OrderPageData;

/**
 * Servlet implementation class OrderSearchServlet
 */
@WebServlet("/orderSearch.kh")
public class OrderSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		request.setCharacterEncoding("utf-8");

		String Opt_ = request.getParameter("Opt"); // select 옵션
		String keyword_ = request.getParameter("keyword"); // 검색 내용

		String Opt = "orders_date || product_name || member_id || member_Nickname";
		if (Opt_ != null) {
			Opt = Opt_;
		}
		String keyword = "";
		if (keyword_ != null) {
			keyword = keyword_;
		}

		// 현재 페이지 처리
		int currentPage;
		if (request.getParameter("currentPage") == null) {
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}

		OrderPageData opd = new OrderService().searchOrderPage(currentPage, Opt, keyword);
		RequestDispatcher view = request.getRequestDispatcher("/views/admin/order/order.jsp");
		request.setAttribute("pageData", opd);
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
