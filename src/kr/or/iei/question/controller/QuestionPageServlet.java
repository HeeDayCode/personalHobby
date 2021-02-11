package kr.or.iei.question.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.notice.model.service.NoticeService;
import kr.or.iei.question.model.service.QuestionService;
import kr.or.iei.question.model.vo.Board;
import kr.or.iei.question.model.vo.BoardPageData;

/**
 * Servlet implementation class QuestionPageServlet
 */
@WebServlet("/question.kh")
public class QuestionPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Board> nList = new NoticeService().selectNoticeList();
		// 공지
		
		int currentPage; // 현재 페이지 값을 가지고 있는 변수
		
		if(request.getParameter("currentPage")==null) {
			currentPage = 1;
		}else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		BoardPageData bpd = new QuestionService().selectAllBoardPage(currentPage);
		// 사용자 작성 문의(페이징 처리 함)
		
		RequestDispatcher view = request.getRequestDispatcher("/views/store/question/list.jsp");
		request.setAttribute("pageData", bpd);
		request.setAttribute("nList", nList);
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
