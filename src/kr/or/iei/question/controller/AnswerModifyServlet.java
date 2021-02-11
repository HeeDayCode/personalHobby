package kr.or.iei.question.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.question.model.service.QuestionService;

/**
 * Servlet implementation class AnswerModifyServlet
 */
@WebServlet("/answerModify.kh")
public class AnswerModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnswerModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String re_answer = request.getParameter("re_answer");
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String boardCategoryName = request.getParameter("boardCategoryName");

		
		int result = new QuestionService().answerUpdate(boardNo, re_answer);
		
		RequestDispatcher view = request.getRequestDispatcher("/views/store/question/answerUpdate.jsp");
				
		request.setAttribute("boardNo", boardNo);
		request.setAttribute("boardCategoryName", boardCategoryName);
		
		if(result>0) {// 댓글이 정상적으로 수정되었다면 true 값
			request.setAttribute("result", true);
		}else { //댓글이 정상적으로 수정되지 못하였다면 false값
			request.setAttribute("result", false);
		}
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
