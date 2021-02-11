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
 * Servlet implementation class InsertPostServlet
 */
@WebServlet("/postCommentWrite.kh")
public class InsertCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String comments = request.getParameter("comments");	//작성댓글
		int writeNo = Integer.parseInt(request.getParameter("writeNo"));		//게시글번호
		int productNo = Integer.parseInt(request.getParameter("productNo"));	//페이지 돌아갈때 필요
		
		HttpSession session = request.getSession();
		MemberAll mAll = (MemberAll)session.getAttribute("memberAll");//세션에서 로그인중인 유저정보 가져오기
		
		int userNo = mAll.getM().getMemberNo(); 
		
		int result = new WriteService().insertComment(comments,writeNo,userNo);
		
		RequestDispatcher view = request.getRequestDispatcher("/views/community/detail/commentWrite.jsp");
		if(result>0)
		{
			//데이터 입력에 성공
			//성공값(true)+boardNo를 보내주도록함
			request.setAttribute("writeNo", writeNo);
			request.setAttribute("productNo", productNo);
			request.setAttribute("result", true);
		}else {
			//데이터 입력에 실패
			//실패값(false)+boardNo를 보내주도록함
			request.setAttribute("writeNo", writeNo);
			request.setAttribute("productNo", productNo);
			request.setAttribute("result", true);
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
