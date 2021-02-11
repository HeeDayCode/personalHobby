package kr.or.iei.orders.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.iei.member.model.vo.MemberAll;
import kr.or.iei.orders.model.service.OrderService;
import kr.or.iei.orders.model.vo.Orders;

/**
 * Servlet implementation class insertOrderServlet
 */
@WebServlet(name = "InsertOrderServlet", urlPatterns = { "/insertOrder.kh" })
public class InsertOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 인코딩부터
		request.setCharacterEncoding("UTF-8");
		
		// order에 저장할 멤버넘버 세션에서 가져오기
		HttpSession session=request.getSession();
		MemberAll mAll=(MemberAll)session.getAttribute("memberAll");
		int memberNo=mAll.getM().getMemberNo();
		
		// 상품정보
		int productNo=Integer.parseInt(request.getParameter("productNo"));
		int ordersNum=Integer.parseInt(request.getParameter("ordersNum"));
		
		// 배송정보
		String receiverName=request.getParameter("name");
		String receiverPhone=request.getParameter("phone");
		String receiverAddress=request.getParameter("address");
		String demand=request.getParameter("demand");
		/*
		System.out.println(receiverName+"\n"+receiverPhone+"\n"+receiverAddress+"\n"+demand);
		*/
		Orders o=new Orders();
		o.setMemberNo(memberNo);
		o.setProductNo(productNo);
		o.setOrdersNum(ordersNum);
		o.setReceiverName(receiverName);
		o.setReceiverPhone(receiverPhone);
		o.setReceiverAddress(receiverAddress);
		o.setDemand(demand);
		
		int result=new OrderService().insertOrder(o);
		
		RequestDispatcher view=request.getRequestDispatcher("/productList.kh?productNo="+productNo);
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
