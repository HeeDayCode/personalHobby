package kr.or.iei.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.iei.member.model.service.MemberService;
import kr.or.iei.member.model.vo.MemberAll;
import kr.or.iei.member.model.vo.MemberCommentPageData;
import kr.or.iei.member.model.vo.MemberHeartPageData;
import kr.or.iei.member.model.vo.MemberImg;
import kr.or.iei.member.model.vo.MemberWritePageData;

/**
 * Servlet implementation class MemberMypageServlet
 */
@WebServlet("/myPage.kh")
public class MemberMypageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberMypageServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		MemberAll mAll = (MemberAll) session.getAttribute("memberAll");

		int MemberNo = mAll.getM().getMemberNo();

		MemberImg mImg = new MemberService().selectProfileImg(MemberNo);

		int page1;

		if (request.getParameter("page1") == null) {
			page1 = 1;
		} else {
			page1 = Integer.parseInt(request.getParameter("page1"));
		}

		MemberWritePageData mwpd = new MemberService().loadWrite(MemberNo, page1);

		int page2;

		if (request.getParameter("page2") == null) {
			page2 = 1;
		} else {
			page2 = Integer.parseInt(request.getParameter("page2"));
		}

		MemberCommentPageData mcpd = new MemberService().loadComment(MemberNo, page2);

		int page3;

		if (request.getParameter("page3") == null) {
			page3 = 1;
		} else {
			page3 = Integer.parseInt(request.getParameter("page3"));
		}

		MemberHeartPageData mhpd= new MemberService().loadHeart(MemberNo, page3);

		RequestDispatcher view = request.getRequestDispatcher("/views/member/myPage/myPage.jsp");
		request.setAttribute("page1Data", mwpd);
		request.setAttribute("page2Data", mcpd);
		request.setAttribute("page3Data", mhpd);
		if (mImg != null) {
			request.setAttribute("fileName", mImg.getChangedFileName());
		} else {
			request.setAttribute("fileName", "profile.png");
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
