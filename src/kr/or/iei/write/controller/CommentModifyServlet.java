package kr.or.iei.write.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.iei.member.model.vo.MemberAll;
import kr.or.iei.write.model.service.WriteService;

/**
 * Servlet implementation class CommentModifyServlet
 */
@WebServlet("/commentModify.kh")
public class CommentModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 인코딩
		request.setCharacterEncoding("utf-8");
		
		//2. 이전 페이지에서 보내온값 저장
		int commentNo = Integer.parseInt(request.getParameter("commentNo"));
		String content = request.getParameter("co_content");
		
		int writeNo = Integer.parseInt(request.getParameter("writeNo"));
		int productNo = Integer.parseInt(request.getParameter("productNo"));	//페이지로 돌아가기위한 값
		
		
		//3.세션에서 요청자의 ID를 가져오도록 해야 함
		HttpSession session = request.getSession();
		MemberAll mAll = (MemberAll)session.getAttribute("memberAll");//세션에서 로그인중인 유저정보 가져오기
		int userNo = mAll.getM().getMemberNo(); 
		
		//4.비즈니스 로직처리
		int result = new WriteService().modifyComment(commentNo,content,userNo,writeNo);
	
		//5.결과처리
		RequestDispatcher view = request.getRequestDispatcher("/views/community/detail/CommentUpdate.jsp");
		
		request.setAttribute("writeNo", writeNo);
		request.setAttribute("productNo", productNo);
		if(result>0) {
			//댓글이 정상적으로 수정되었다면 true값
			request.setAttribute("result", true);
		}else {
			//댓글이 정상적으로 수정되지 못하였다면 false 값
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
