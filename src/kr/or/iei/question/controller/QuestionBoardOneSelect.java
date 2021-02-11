package kr.or.iei.question.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.iei.member.model.vo.MemberAll;
import kr.or.iei.notice.model.service.NoticeService;
import kr.or.iei.question.model.service.QuestionService;
import kr.or.iei.question.model.vo.Answer;
import kr.or.iei.question.model.vo.Board;

/**
 * Servlet implementation class BoardOneSelect
 */
@WebServlet("/boardPostClick.kh")
public class QuestionBoardOneSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionBoardOneSelect() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String boardCategoryName = request.getParameter("boardCategoryName");
		HttpSession session = request.getSession();
		MemberAll mAll = (MemberAll) session.getAttribute("memberAll");
		Board board = new Board();
		// 게시물 정보
		int result = 0;
		// 조회수 증가 확인
		ArrayList<Answer> aList = new ArrayList<Answer>();
		// 댓글 정보
		
		if(boardCategoryName.equals("공지사항") || boardCategoryName.equals("자주묻는 질문")) {
			//공지(notice) 관련 게시글인 경우
			result = new NoticeService().updateViewCount(boardNo);
		}else{
			//질문(question) 관련 게시글인 경우
			result = new QuestionService().updateViewCount(boardNo);
		}
		if(result > 0) {
			//조회수 업데이트 정상 작동시
			if(boardCategoryName.equals("공지사항") || boardCategoryName.equals("자주묻는 질문")) {
				board = new NoticeService().noticeOneSelect(boardNo);
			}else{
				board = new QuestionService().questionOneSelect(boardNo);
				aList = new QuestionService().answerSelect(boardNo);
			}
			
			if(board != null) {
				// 게시판 정보 제대로 가져왔을시
				RequestDispatcher view = request.getRequestDispatcher("/views/store/question/questionReadSuccess.jsp");
				request.setAttribute("board", board);
				request.setAttribute("aList", aList);
				view.forward(request, response);
			}else {
				// 게시판 정보 가져오지 못했을시
				RequestDispatcher view = request.getRequestDispatcher("/views/store/question/postReadFail.jsp");
				view.forward(request, response);
			}
			
		}else {
			//조회수 업데이트 미작동시
			RequestDispatcher view = request.getRequestDispatcher("/views/store/question/postReadFail.jsp");
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
