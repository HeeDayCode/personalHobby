package kr.or.iei.notice.controlloer;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.notice.model.service.NoticeService;
import kr.or.iei.question.model.vo.Board;

/**
 * Servlet implementation class ProductModifyPageServlet
 */
@WebServlet("/noticeModifyPage.kh")
public class NoticeModifyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeModifyPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 값 받아오기
		int boardNo=Integer.parseInt(request.getParameter("boardNo"));
		
		/*
		 * ArrayList<Board> qList = new QuestionService().selectQuestionListNo(boardNo);
		 * // 사용자 작성 문의
		 */		
		Board nBoard = new NoticeService().selectNoticeListNo(boardNo);
		// 공지
		
		// 결과 처리
		RequestDispatcher view=request.getRequestDispatcher("/views/store/question/noticeModify.jsp");
		request.setAttribute("nBoard", nBoard);
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
