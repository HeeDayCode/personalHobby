package kr.or.iei.product.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.iei.member.model.vo.MemberAll;
import kr.or.iei.product.model.service.ProductService;
import kr.or.iei.product.model.vo.ProductAllSub;
import kr.or.iei.product.model.vo.ProductWrite;
import kr.or.iei.question.model.service.QuestionService;
import kr.or.iei.question.model.vo.Board;

/**
 * Servlet implementation class ProductAllSelectServlet
 */
@WebServlet("/productList.kh")
public class ProductSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductSelectServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int productNo = Integer.parseInt(request.getParameter("productNo"));
		ArrayList<Board> qList = new QuestionService().selectQuestionList1(productNo);

		HttpSession session = request.getSession();
		MemberAll mAll = (MemberAll) session.getAttribute("memberAll");
		
		ProductAllSub product = new ProductService().selectOneProductList(productNo);
		ArrayList<ProductWrite> pcList = new ProductService().selectpncList(productNo);
		

		if (mAll == null) {

			// 3.결과처리
			if (product != null) {
				RequestDispatcher view = request.getRequestDispatcher("/views/store/product/detail.jsp");
				request.setAttribute("product", product);
				request.setAttribute("pcList", pcList);
				request.setAttribute("qList", qList);
				view.forward(request, response);

			} else {
				// 정상적으로 게시물을 읽어 오지 못했다면
				RequestDispatcher view = request.getRequestDispatcher("/views/store/product/productOneReadFail.jsp");
				view.forward(request, response);
			}

		} else {
			
			if (product != null) {
				RequestDispatcher view = request.getRequestDispatcher("/views/store/product/detail.jsp");
				request.setAttribute("product", product);
				request.setAttribute("pcList", pcList);
				request.setAttribute("qList", qList);
				view.forward(request, response);

			} else {
				// 정상적으로 게시물을 읽어 오지 못했다면
				RequestDispatcher view = request.getRequestDispatcher("/views/store/product/productOneReadFail.jsp");
				view.forward(request, response);
			}
			
			
		}
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
