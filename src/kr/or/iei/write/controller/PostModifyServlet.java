package kr.or.iei.write.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.product.model.service.ProductService;
import kr.or.iei.product.model.vo.ProductAll;
import kr.or.iei.write.model.service.WriteService;
import kr.or.iei.write.model.vo.WriteAll;
import kr.or.iei.write.model.vo.WriteImage;

/**
 * Servlet implementation class ModifyPostServlet
 */
@WebServlet("/modifyPost.kh")
public class PostModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int writeNo = Integer.parseInt(request.getParameter("writeNo"));
		int productNo = Integer.parseInt(request.getParameter("productNo"));
		
		WriteAll wAll = new WriteService().selectOneDetailPrint(writeNo);
		ProductAll pAll = new ProductService().selectOneProduct(productNo);
		WriteImage wImg = new WriteService().selectImg(writeNo);
		
		
		RequestDispatcher view = request.getRequestDispatcher("/views/community/write/writeModify.jsp");
		
		request.setAttribute("wAll", wAll);
		request.setAttribute("pAll", pAll);
		request.setAttribute("wimg", wImg);
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
