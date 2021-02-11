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

import kr.or.iei.member.model.service.MemberService;

/**
 * Servlet implementation class MemberNewPwServlet
 */
@WebServlet("/newPW.kh")
public class MemberNewPwServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberNewPwServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String userIdEmail = request.getParameter("userIdEmail");

		String pw = request.getParameter("newPw_Email");
		if (pw != null) {

			StringBuffer sb = new StringBuffer();
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");// SHA-1 또는 MD5
				md.update(pw.getBytes());
				byte[] digest = md.digest();

				for (byte b : digest) {
					sb.append(Integer.toHexString(b & 0xff));
				}

			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pw = sb.toString();
		}
		String newPwEmail = pw;

		String pw2 = request.getParameter("newPw_Phone");

		if (pw2 != null) {

			StringBuffer sb = new StringBuffer();
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");// SHA-1 또는 MD5
				md.update(pw2.getBytes());
				byte[] digest = md.digest();

				for (byte b : digest) {
					sb.append(Integer.toHexString(b & 0xff));
				}

			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pw2 = sb.toString();
		}
		String newPwPhone = pw2;

		String userIdPhone = request.getParameter("userIdPhone");

		int result = 0;

		if (userIdEmail != null) {
			result = new MemberService().updateNewPw(newPwEmail, userIdEmail);
		} else {
			result = new MemberService().updateNewPw(newPwPhone, userIdPhone);
		}

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");

		PrintWriter out = response.getWriter();

		if (result > 0) {
			out.println("<script>alert('비밀번호가 변경되었습니다. 변경된 비밀번호로 로그인해주세요');</script>");
			out.println("<script>location.replace('/views/member/login/loginForm.jsp');</script>");
		} else {
			out.println("<script>alert('비밀번호가 변경이 실패하였습니다. 지속적인 문제 발생 시 관리자에게 문의해주세요');</script>");
			out.println("<script>location.replace('/views/member/login/findId.jsp');</script>");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
