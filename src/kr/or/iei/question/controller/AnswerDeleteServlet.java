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
 * Servlet implementation class AnswerDelete
 */
@WebServlet("/answerDelete.kh")
public class AnswerDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnswerDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String boardCategoryName = request.getParameter("boardCategoryName");
		
		int result = new QuestionService().answerDelete(boardNo);
		
		if(result>0) {
			response.sendRedirect("/boardPostClick.kh?boardNo=" + boardNo + "&boardCategoryName=" + boardCategoryName);
			
		}else {
			RequestDispatcher view = request.getRequestDispatcher("/views/store/question/answerDeleteFail.jsp");
			request.setAttribute("boardNo", boardNo);
			request.setAttribute("boardCategoryName", boardCategoryName);
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
