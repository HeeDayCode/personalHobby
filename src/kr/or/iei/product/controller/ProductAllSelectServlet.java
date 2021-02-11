package kr.or.iei.product.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.product.model.service.ProductService;
import kr.or.iei.product.model.vo.ProductPageData;

/**
 * Servlet implementation class ProductAllSelectServlet
 */
@WebServlet("/productAllList.kh")
public class ProductAllSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductAllSelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {				
		
		//게시판을 접근하면 그것이 바로 1page라고 생각을 하기
				int currentPage; //현재 페이지 값을 가지고 있는 변수
				if(request.getParameter("currentPage")==null)
				{					
					currentPage =1;					
				}else {					
					currentPage = Integer.parseInt(request.getParameter("currentPage"));					
				}
				
				ProductPageData ppd = new ProductService().selectAllProductPage(currentPage);
	
				RequestDispatcher view =request.getRequestDispatcher("/views/admin/product/list.jsp");
				request.setAttribute("pageData", ppd); //리스트의 내용을 앞의 파란색 이름으로 저장
				view.forward(request, response); //이거는 고정 이걸 가지고 이동하세요
		  
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
