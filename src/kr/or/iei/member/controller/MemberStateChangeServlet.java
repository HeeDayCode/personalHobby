package kr.or.iei.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.member.model.service.MemberService;

/**
 * Servlet implementation class MemberStateChangeServlet
 */
@WebServlet("/memberStateChanged.kh")
public class MemberStateChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberStateChangeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int memberNo = Integer.parseInt(request.getParameter("memberNo"));
		char memberWithdrawYN = (request.getParameter("memberWithdrawYN")).charAt(0);

		if (memberWithdrawYN == 'N') {
			memberWithdrawYN = 'Y';
		} else {
			memberWithdrawYN = 'N';
		}

		int result = new MemberService().memberStateUpdate(memberNo, memberWithdrawYN);

		if (result > 0) {
			response.sendRedirect("/memberAllListPage.kh");
		} else {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();
			out.println("<script>alert('회원 상태변경 실패 (지속적인 문제 발생시 개발자에게 문의해주세요)');</script>");
			out.println("<script>location.replace('/memberAllListPage.kh')</script>");
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
