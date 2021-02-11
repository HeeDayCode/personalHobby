package kr.or.iei.write.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.write.model.service.CommentsService;
import kr.or.iei.write.model.vo.CommentsPageData;

/**
 * Servlet implementation class CommentsSearchPageServlet
 */
@WebServlet("/SearchComments.kh")
public class CommentsSearchPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CommentsSearchPageServlet() {
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
		request.setCharacterEncoding("utf-8");

		String commentsSelectOpt_ = request.getParameter("commentsSelectOpt"); // select 옵션
		String keyword_ = request.getParameter("keyword"); // 검색 내용

		String commentsSelectOpt = "comments_text || member_Id || member_Nickname || member_Name || comments_date";
		if (commentsSelectOpt_ != null) {
			commentsSelectOpt = commentsSelectOpt_;
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

		CommentsPageData cpd = new CommentsService().searchCommentsPage(currentPage, commentsSelectOpt, keyword);
		RequestDispatcher view = request.getRequestDispatcher("/views/admin/post/comment.jsp");
		request.setAttribute("pageData", cpd);
		request.setAttribute("keyword", keyword);
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
