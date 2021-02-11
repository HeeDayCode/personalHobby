package kr.or.iei.orders.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.iei.member.model.vo.MemberAll;
import kr.or.iei.orders.model.service.OrdersService;
import kr.or.iei.orders.model.vo.OrdersAll;

/**
 * Servlet implementation class OrdersListServlet
 */
@WebServlet("/OrdersList.kh")
public class OrdersListServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrdersListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      HttpSession session = request.getSession();
      MemberAll mAll = (MemberAll)session.getAttribute("memberAll");
      int memberNo = mAll.getM().getMemberNo();
      
      //System.out.println("유저 번호 : " + memberNo);
      
      String searchOpt = request.getParameter("searchOpt");
      
      //System.out.println("선택: " + searchOpt);
      if(searchOpt==null)
      {
         searchOpt = "all";
      }
         
      switch(searchOpt) {
         case "all" : 
         ArrayList<OrdersAll> ordersList =  new OrdersService().selectOrdersAllList(memberNo);
         
         RequestDispatcher view = request.getRequestDispatcher("/views/member/myPage/order.jsp");
         request.setAttribute("ordersList", ordersList);
         request.setAttribute("searchOpt", searchOpt);
         view.forward(request, response);
         break; 
         
         case "one_week" : 
         ArrayList<OrdersAll> ordersList2 = new OrdersService().selectOneWeek(memberNo);
         RequestDispatcher view2 = request.getRequestDispatcher("/views/member/myPage/order.jsp");
         request.setAttribute("ordersList", ordersList2);
         request.setAttribute("searchOpt", searchOpt);
         view2.forward(request, response);
         break; 
         
         
         case "one_month" : 
         ArrayList<OrdersAll> ordersList3 = new OrdersService().selectOneMonth(memberNo);
         RequestDispatcher view3 = request.getRequestDispatcher("/views/member/myPage/order.jsp");
         request.setAttribute("ordersList", ordersList3);
         request.setAttribute("searchOpt", searchOpt);
         view3.forward(request, response);
         break; 
         
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