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

/**
 * Servlet implementation class BoardSearchList
 */
@WebServlet("/boardSearch.kh")
public class BoardSearchListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardSearchListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
		request.setCharacterEncoding("utf-8");
		
		//2. 웹상에서 보내온 데이터를 저장
		String keyword = request.getParameter("keyword");
		String category = request.getParameter("searchCategory");
		
		ArrayList<Board> qList = new ArrayList<Board>();
		// 사용자 작성글
		ArrayList<Board> nList = new ArrayList<Board>();
		// 공지
		
		if(category.equals("제목")) {
			qList = new QuestionService().questionSearchTitleList(keyword);
			nList = new NoticeService().noticeSearchTitleList(keyword);
		}else if(category.equals("내용")) {
			qList = new QuestionService().questionSearchContentsList(keyword);
			nList = new NoticeService().noticeSearchContentsList(keyword);
		}else if(category.equals("작성자")){
			qList = new QuestionService().questionSearchWriterList(keyword);
			nList = new NoticeService().selectNoticeList();
		}else {
			qList = new QuestionService().questionSearchProductList(keyword);
		}
		
		RequestDispatcher view = request.getRequestDispatcher("/views/store/question/list.jsp");
		request.setAttribute("qList", qList);
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
