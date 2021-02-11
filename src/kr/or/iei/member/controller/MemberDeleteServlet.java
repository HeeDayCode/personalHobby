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
import kr.or.iei.member.model.vo.MemberAll;

/**
 * Servlet implementation class MemberDeleteServlet
 */
@WebServlet("/memberWithdraw.kh")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.입력값 가져오기
				
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

				String userPw = pw;
				
				//session DB로 비밀번호가 맞는 지 확인
				HttpSession session = request.getSession();
				MemberAll mAll = (MemberAll)session.getAttribute("memberAll");
				
				if(userPw.equals(mAll.getM().getMemberPw())) {
					//입력과 session이 같다면 
					String userId = mAll.getM().getMemberId();
					int result = new MemberService().deleteMember(userId);
					
					response.setCharacterEncoding("utf-8");
					response.setContentType("text/html; charset=UTF-8");
					
					PrintWriter out = response.getWriter();
					
					if(result>0) {
						out.println("<script>alert('탈퇴처리 완료'); </script>");
						session.invalidate();
						out.println("<script>location.replace('/index.kh'); </script>");
					}else {
						out.println("<script>alert('탈퇴오류발생'); </script>");
						out.println("<script>location.replace('/index.kh'); </script>");
					}
					
					
				}else {
					//다르다면
					response.setCharacterEncoding("utf-8");
					response.setContentType("text/html; charset=UTF-8");
					
					PrintWriter out = response.getWriter();
					
					out.println("<script>alert('비밀번호 일치안함'); </script>");
					out.println("<script>location.replace('/index.kh'); </script>");
					
					
					
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
