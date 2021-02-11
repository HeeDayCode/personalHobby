package kr.or.iei.member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.member.model.service.MemberService;

/**
 * Servlet implementation class findIdServlet
 */
@WebServlet("/findId.kh")
public class MemberFindIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberFindIdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String userNameEmail = request.getParameter("userNameEmail");
		String email = request.getParameter("email");
		String userNamePhone = request.getParameter("userNamePhone");
		String phone = request.getParameter("phone");
		
		String memberId=null;
		
		if(userNameEmail!="") // 이메일로 ID 찾기의 이름이 비어있지 않을 경우 
		{
			memberId = new MemberService().findIdEmail(userNameEmail, email);
			
		}else if(userNamePhone!="") //휴대폰으로 ID 찾기의 이름이 비어있지 않을 경우
		{
			memberId = new MemberService().findIdPhone(userNamePhone, phone);
		}
		
		if(memberId!=null) {
			RequestDispatcher view = request.getRequestDispatcher("/views/member/login/findSuccess.jsp");
			request.setAttribute("memberId", memberId);
			view.forward(request, response);
		}else {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			
			PrintWriter out = response.getWriter();
			
			out.println("<script>alert('입력한 정보와 일치하는 회원이 없습니다.')</script>");
			out.println("<script>location.replace('/views/member/login/findId.jsp');</script>");
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
