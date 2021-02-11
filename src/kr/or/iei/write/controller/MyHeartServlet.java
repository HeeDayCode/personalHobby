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
 * Servlet implementation class MyHeartServlet
 */
@WebServlet("/myHeart.kh")
public class MyHeartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyHeartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		MemberAll mAll = (MemberAll)session.getAttribute("memberAll");
		
		int writeNo = Integer.parseInt(request.getParameter("write_no"));
		int productNo = Integer.parseInt(request.getParameter("productNo"));
		int userNo = mAll.getM().getMemberNo();
		
	   int result = new WriteService().myHeartInsert(userNo,writeNo);
		
	   RequestDispatcher view =request.getRequestDispatcher("/views/community/detail/myHeartSuccess.jsp");
	   if(result>0){//찜하기성공
		   	request.setAttribute("writeNo", writeNo);
		   	request.setAttribute("productNo", productNo);
			request.setAttribute("result", true);
	   }else
	   {//찜하기 실패
		   request.setAttribute("writeNo", writeNo);
		   request.setAttribute("productNo", productNo);
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
