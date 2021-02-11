package kr.or.iei.orders.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.orders.model.service.OrderService;
import kr.or.iei.product.model.vo.ProductAllSub;

/**
 * Servlet implementation class selectOneProductServlet
 */
@WebServlet("/selectOneProduct.kh")
public class SelectOneProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectOneProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// 주문할 상품 정보 조회를 위한 상품 번호 값
		int productNo=Integer.parseInt(request.getParameter("productNo"));
		// 디비에 보내진 않지만 다음 결제 페이지에 필요한 총가격 값과 옵션 선택
		int totalPrice=Integer.parseInt(request.getParameter("totalPrice"));
		String selectedOpt=request.getParameter("selectedOpt");
		/*
		System.out.println(productNo+"/"+totalPrice+"/"+selectedOpt);
		*/
		
		// 비즈니스 로직
		ProductAllSub productAS=new OrderService().selectOneProduct(productNo);
		
		
		// 결과 처리
		if(productAS!=null) {
			RequestDispatcher view=request.getRequestDispatcher("/views/store/pay/pay.jsp");
			request.setAttribute("productInfo", productAS);
			request.setAttribute("totalPrice", totalPrice);
			request.setAttribute("selectedOpt", selectedOpt);
			view.forward(request, response);
		}
		else {
			RequestDispatcher view=request.getRequestDispatcher("/views/store/pay/productSelectOneFail.jsp");
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
