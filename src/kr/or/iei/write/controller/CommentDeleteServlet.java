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
 * Servlet implementation class CommentDeleteServlet
 */
@WebServlet("/commentDelete.kh")
public class CommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int commentNo = Integer.parseInt(request.getParameter("commentNo"));
		int writeNo = Integer.parseInt(request.getParameter("writeNo"));

		int productNo = Integer.parseInt(request.getParameter("productNo")); //돌아가기 위한 값
		
		//session에서 해당 삭제 요청자의 ID값도 가져 와야 함(userNo)
		HttpSession session = request.getSession();
		MemberAll mAll = (MemberAll)session.getAttribute("memberAll");
		int userNo = mAll.getM().getMemberNo();
		
		//비즈니스 로직처리
		int result = new WriteService().deleteComment(commentNo,userNo);
		
		if(result>0)
		{
			response.sendRedirect("/detailPrint.kh?write_no="+writeNo+"&productNo="+productNo);
		}else
		{
			RequestDispatcher view = request.getRequestDispatcher("/views/comunity/detail/commentDelete.jsp");
			request.setAttribute("writeNo", writeNo);
			request.setAttribute("productNo", productNo);
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
