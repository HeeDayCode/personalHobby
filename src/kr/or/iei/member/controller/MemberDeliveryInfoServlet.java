package kr.or.iei.member.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.iei.member.model.service.MemberService;
import kr.or.iei.member.model.vo.MemberAll;
import kr.or.iei.orders.model.vo.OrdersWithImg;

/**
 * Servlet implementation class MemberDeliveryInfoServlet
 */
@WebServlet("/deliveryInfo.kh")
public class MemberDeliveryInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDeliveryInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 주문 정보 조회를 위한 유저 넘버 꺼내기
		HttpSession session=request.getSession();
	    MemberAll mAll=(MemberAll)session.getAttribute("memberAll");
	    int userNo=mAll.getM().getMemberNo();
	    
	    // 비즈니스 로직
	    ArrayList<OrdersWithImg> list=new MemberService().selectDeliveryInfo(userNo);
	    
	    // 결과 처리
	    if(!(list.isEmpty())) {
	    	RequestDispatcher view=request.getRequestDispatcher("/views/member/myPage/deliveryCheck.jsp");
	    	request.setAttribute("list", list);
	    	view.forward(request, response);
	    }
	    else {
	    	RequestDispatcher view=request.getRequestDispatcher("/views/member/myPage/deliveryEmpty.jsp");
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
