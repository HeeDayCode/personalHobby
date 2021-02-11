package kr.or.iei.notice.controlloer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.notice.model.service.NoticeService;

/**
 * Servlet implementation class QuestionManagerInsert
 */
@WebServlet("/noticeInsert.kh")
public class NoticeInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 인코딩
		request.setCharacterEncoding("utf-8");
		
		// 페이지에서 전송받은 값 저장
		String category = request.getParameter("category");
		String title = request.getParameter("title");
		String inputText = request.getParameter("inputText");
		
		System.out.println(category);
		System.out.println(title);
		System.out.println(inputText);
		int result = new NoticeService().insertNotice(category, title, inputText);
		
		if(result>0) {
			RequestDispatcher view = request.getRequestDispatcher("/question.kh");
			view.forward(request, response);
		}else {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			
			PrintWriter out = response.getWriter();
			
			out.println("<script>alert('글 작성에 실패하였습니다. 지속적인 문제 발생시 관리자에게 문의해주세요.')</script>");
			out.println("<script>location.replace('/question.kh');</script>");
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
