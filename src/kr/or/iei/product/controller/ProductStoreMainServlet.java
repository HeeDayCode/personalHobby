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
import kr.or.iei.product.model.vo.ProductWrite;

/**
 * Servlet implementation class storeMainServlet
 */
@WebServlet("/storeMain.kh")
public class ProductStoreMainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductStoreMainServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		ArrayList<ProductWrite> topList = new ProductService().storeMainTop();
		ArrayList<ProductAll> newList = new ProductService().storeMainNew();
		ArrayList<ProductAll> hotList = new ProductService().storeMainHot();
		ArrayList<ProductAll> bestList = new ProductService().storeMainBest();

		RequestDispatcher view = request.getRequestDispatcher("/views/store/main/store.jsp");
		request.setAttribute("topList", topList);
		request.setAttribute("newList", newList);
		request.setAttribute("hotList", hotList);
		request.setAttribute("bestList", bestList);
		view.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
