package kr.or.iei.product.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.product.model.service.ProductService;
import kr.or.iei.product.model.vo.ProductAll;

/**
 * Servlet implementation class ProductPrintServlet
 */
@WebServlet("/imgPrint.kh")
public class ProductPrintServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductPrintServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int categoryNo = Integer.parseInt(request.getParameter("category_no"));
		String value = request.getParameter("value");

		
		ArrayList<ProductAll> list = new ProductService().printIMG(categoryNo);
		ArrayList<ProductAll> allList = new ProductService().printIMG();
		ArrayList<ProductAll> alignList1 = new ProductService().productAlign(categoryNo);
		ArrayList<ProductAll> alignList2 = new ProductService().productAlignPrice(categoryNo);
		ArrayList<ProductAll> alignList3 = new ProductService().productAlignNew(categoryNo);
		ArrayList<ProductAll> alignList4 = new ProductService().productAlignPopular(categoryNo);
		
		RequestDispatcher view = request.getRequestDispatcher("/views/store/category/category.jsp");
		request.setAttribute("list", list);
		request.setAttribute("allList",allList);
		request.setAttribute("alignList1", alignList1);
		request.setAttribute("alignList2", alignList2);
		request.setAttribute("alignList3", alignList3);
		request.setAttribute("alignList4", alignList4);
		request.setAttribute("categoryNo", categoryNo);
		request.setAttribute("value", value);
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
