package kr.or.iei.write.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.write.model.service.WriteService;

/**
 * Servlet implementation class WriteDeleteServlet
 */
@WebServlet("/WriteDelete.kh")
public class WriteDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WriteDeleteServlet() {
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

		int WriteNo = Integer.parseInt(request.getParameter("WriteNo"));

		int result = new WriteService().writeDelete(WriteNo);

		RequestDispatcher view = request.getRequestDispatcher("/views/admin/post/writeDelete.jsp");
		if (result > 0) {
			request.setAttribute("result", true);
		} else {
			request.setAttribute("result", false);
		}
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
