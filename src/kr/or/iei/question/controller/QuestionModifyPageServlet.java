package kr.or.iei.question.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.question.model.service.QuestionService;
import kr.or.iei.question.model.vo.Board;

/**
 * Servlet implementation class QuestionModifyPageServlet
 */
@WebServlet("/questionModifyPage.kh")
public class QuestionModifyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionModifyPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int boardNo=Integer.parseInt(request.getParameter("boardNo"));

		 Board qBoard = new QuestionService().selectQuestionListNo(boardNo);
		  // 사용자 작성 문의
		 		
		/*
		 * ArrayList<Board> nList = new NoticeService().selectNoticeListNo(boardNo); //
		 * 공지
		 */		
		// 결과 처리
		RequestDispatcher view=request.getRequestDispatcher("/views/store/question/questionModify.jsp");
		request.setAttribute("qBoard", qBoard);
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
