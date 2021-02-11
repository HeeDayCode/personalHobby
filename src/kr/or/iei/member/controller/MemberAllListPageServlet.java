package kr.or.iei.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.member.model.service.MemberService;
import kr.or.iei.member.model.vo.MemberPageData;

/**
 * Servlet implementation class MemberAllListPageServlet
 */
@WebServlet("/memberAllListPage.kh")
public class MemberAllListPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberAllListPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원 조회 및 삭제 페이지 이동시 전체 회원 리스트 자동 출력 - 해당 페이지가 1page
		int currentPage;
		
		if(request.getParameter("currentPage")==null) {
			currentPage = 1;
		} else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		// 비즈니스 로직
		MemberPageData mpd = new MemberService().selectAllMemberPage(currentPage);		
		
		RequestDispatcher view = request.getRequestDispatcher("/views/admin/member/manage.jsp");
		request.setAttribute("pageData", mpd);
		
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







