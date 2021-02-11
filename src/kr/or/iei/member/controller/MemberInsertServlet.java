package kr.or.iei.member.controller;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.member.model.service.MemberService;
import kr.or.iei.member.model.vo.Member;
import kr.or.iei.member.model.vo.MemberAddress;

/**
 * Servlet implementation class MemberInsertServlet
 */
@WebServlet("/joinForm.kh")
public class MemberInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//1.인코딩
		request.setCharacterEncoding("utf-8");
		
		//2.이전페이지 전송값 저장
		Member m = new Member();
		m.setMemberId(request.getParameter("userId"));
		
		
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
		
		
		m.setMemberPw(pw);
		m.setMemberName(request.getParameter("userName"));
		m.setMemberNickname(request.getParameter("userNickname"));
		
		String email1=request.getParameter("email");
		String email2=request.getParameter("email2");
		m.setMemberEmail(email1+"@"+email2);
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String phone3 = request.getParameter("phone3");
		String phone= phone1+phone2+phone3;
		
		m.setMemberPhone(phone);
	
		MemberAddress ma = new MemberAddress();
		ma.setAddress(request.getParameter("addr"));
		ma.setPostNum(Integer.parseInt(request.getParameter("addrNumber")));
		
		boolean result = new MemberService().insertMember(m, ma);
	
		//3.비즈니스 로직 처리
		if(result==true)
		{
			RequestDispatcher view =request.getRequestDispatcher("/views/member/join/joinSuccess.jsp");
			view.forward(request, response);
		}else
		{
			RequestDispatcher view =request.getRequestDispatcher("/views/member/join/joinFail.jsp");
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
