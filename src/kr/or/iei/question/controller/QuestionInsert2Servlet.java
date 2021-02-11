package kr.or.iei.question.controller;

import java.io.IOException;
import java.io.PrintWriter;

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
 * Servlet implementation class QuestionUserInsert
 */
@WebServlet("/questionInsert1.kh")
public class QuestionInsert2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionInsert2Servlet() {
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
		String productNo = request.getParameter("productNo");
		/* System.out.println(productNo); */
		
		HttpSession session = request.getSession();
		MemberAll mAll = (MemberAll) session.getAttribute("memberAll");
		int memberNo = mAll.getM().getMemberNo();
	
		int result = new QuestionService().insertQuestion1(productNo,memberNo, category,title,inputText);
		
		if(result>0) {
			/* request.setAttribute("paramName", productNo); */
			RequestDispatcher view = request.getRequestDispatcher("/productList.kh?paramName="+productNo);
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
