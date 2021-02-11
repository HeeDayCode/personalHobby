package kr.or.iei.question.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.iei.member.model.vo.MemberAll;
import kr.or.iei.question.model.service.QuestionService;

/**
 * Servlet implementation class QuestionCommentInsertServlet
 */
@WebServlet("/answerWrite.kh")
public class AnswerInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnswerInsertServlet() {
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
		String answer = request.getParameter("answer");
		
		HttpSession session = request.getSession();
		MemberAll mAll = (MemberAll)session.getAttribute("memberAll");
		
		int result = new QuestionService().insertAnswer(boardNo, answer);
		
		RequestDispatcher view = request.getRequestDispatcher("/views/store/question/answerWriter.jsp");
		
		if(result>0) {
			// 답글 입력에 성공했을시 성공값(true)
			request.setAttribute("result", true);
		}else {
			// 답글 입력에 실패했을시 실패값(false)
			request.setAttribute("result", false);
		}
		request.setAttribute("boardNo", boardNo);
		request.setAttribute("boardCategoryName", boardCategoryName);
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
