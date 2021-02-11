package kr.or.iei.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.iei.member.model.service.MemberService;
import kr.or.iei.member.model.vo.MemberAll;
import kr.or.iei.member.model.vo.MemberImg;

/**
 * Servlet implementation class MemberModifyPageServlet
 */
@WebServlet("/modifyPage.kh")
public class MemberModifyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberModifyPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
	    MemberAll mAll = (MemberAll)session.getAttribute("memberAll");
	    
	    int userNo = mAll.getM().getMemberNo();
	   
	    MemberImg mImg = new MemberService().selectProfileImg(userNo);
	    RequestDispatcher view = request.getRequestDispatcher("/views/member/myPage/modify.jsp");
	    if(mImg != null) {
	    	request.setAttribute("fileName", mImg.getChangedFileName());
	    }else {
	    	request.setAttribute("fileName","profile.png");
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
