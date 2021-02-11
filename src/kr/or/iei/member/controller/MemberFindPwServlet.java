package kr.or.iei.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.member.model.service.MemberService;
import kr.or.iei.member.model.vo.Member;

/**
 * Servlet implementation class MemberFindPwServlet
 */
@WebServlet("/findPw.kh")
public class MemberFindPwServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberFindPwServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      // TODO Auto-generated method stub
      //response.getWriter().append("Served at: ").append(request.getContextPath());
      request.setCharacterEncoding("UTF-8");
      
      String userNameEmail = request.getParameter("userNameEmail");
      String userIdEmail = request.getParameter("userIdEmail");
      String email = request.getParameter("email");
      String userNamePhone = request.getParameter("userNamePhone");
      String userIdPhone = request.getParameter("userIdPhone");
      String phone = request.getParameter("phone");
		
      Member m = null;
      
      if(userNameEmail!="") // 이메일로 ID 찾기의 이름이 비어있지 않을 경우 
      {
    	  m=new MemberService().findPwEmail(userNameEmail, userIdEmail, email);
			
      }else if(userNamePhone!="") //휴대폰으로 ID 찾기의 이름이 비어있지 않을 경우
      {
    	  m=new MemberService().findPwPhone(userNamePhone, userIdPhone, phone);
      }
      
      response.setCharacterEncoding("UTF-8");
      response.setContentType("text/html; charset=UTF-8");
      
      PrintWriter out = response.getWriter();
      if(m!=null) {//일치할때
    	 Random rd = new Random();
    	 int authKey = 1000+rd.nextInt(9000);
         
    	 out.println("<script>alert('메일로 인증번호가 발송되었습니다.');</script>");
         RequestDispatcher view = request.getRequestDispatcher("/views/member/login/findPw.jsp");
         
         request.setAttribute("userNameEmail", userNameEmail);
         request.setAttribute("userIdEmail", userIdEmail);
         request.setAttribute("email", email);
         request.setAttribute("userNamePhone", userNamePhone);
         request.setAttribute("userIdPhone", userIdPhone);
         request.setAttribute("phone", phone);
         request.setAttribute("authKey",Integer.toString(authKey));
         if(userNameEmail!="") {
        	 request.setAttribute("return_email", "true");
         }else {
        	 request.setAttribute("return_email", "false");
         }
         view.forward(request, response);
         
         
         
      }else {//일치하지않을때
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