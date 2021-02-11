package kr.or.iei.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.iei.member.model.service.MemberService;
import kr.or.iei.member.model.vo.Member;
import kr.or.iei.member.model.vo.MemberAll;

/**
 * Servlet implementation class MemberLoginServlet
 */
@WebServlet("/memberLogin.kh")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//이전 페이지에서 보내준 값을 Servlet에서 받을 수 있도록 해야함
				String userId =request.getParameter("userId");	
				
				String pw = request.getParameter("userPw");
				StringBuffer sb = new StringBuffer();
				try {
					MessageDigest md=MessageDigest.getInstance("MD5");//SHA-1 또는 MD5
					md.update(pw.getBytes());
					byte [] digest = md.digest();
			
					for(byte b:digest) {
						sb.append(Integer.toHexString(b&0xff));
					}
					
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				pw=sb.toString();
				String userPw=pw;
				MemberAll mAll =new MemberService().loginMember(userId, userPw);
				
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html; charset=UTF-8");
				
				
				PrintWriter out = response.getWriter();
				
				//성공시 m객체 안에 Member 객체가 있고 실패면 null임
				if(mAll !=null) {
					HttpSession session = request.getSession();
					session.setAttribute("memberAll", mAll);
					
					out.println("<script>alert('로그인 성공');</script>");
				}else {
					out.println("<script>alert('로그인 실패 재확인바랍니다.');</script>");
				}
				//성공실패와 상관없이 이동함 
				out.println("<script>location.replace('/index.kh');</script>");
				/* response.sendRedirect("/index.jsp"); */
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
