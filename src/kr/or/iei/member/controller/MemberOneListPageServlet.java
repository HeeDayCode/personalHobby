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
 * Servlet implementation class MemberOneListPageServlet
 */
@WebServlet("/OneListPage.kh")
public class MemberOneListPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberOneListPageServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String searchOpt_ = request.getParameter("searchOpt"); // select 옵션
		String keyword_ = request.getParameter("keyword"); // 검색 내용
		
		String searchOpt = "member_id || member_name";
		if(searchOpt_ !=null) {
			searchOpt = searchOpt_;
		}
		
		String keyword ="";
		if(keyword_ != null) {
			keyword = keyword_;
		}
		
		// 현재 페이지 처리 
		int currentPage;
        if(request.getParameter("currentPage")==null)
        {
        	currentPage=1;
        }else {
        	currentPage = Integer.parseInt(request.getParameter("currentPage"));
        }
        
		
        MemberPageData mpd = new MemberService().memberOneListPage(currentPage, searchOpt, keyword);
		
        
        RequestDispatcher view = request.getRequestDispatcher("/views/admin/member/manage.jsp");
        request.setAttribute("pageData", mpd);
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
