package kr.or.iei.write.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.write.model.service.WriteService;
import kr.or.iei.write.model.vo.CommentsAll;
import kr.or.iei.write.model.vo.WriteAll;

/**
 * Servlet implementation class DetailPrintServlet
 */
@WebServlet("/detailPrint.kh")
public class DetailPrintServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailPrintServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 파라미터 값 가져오기
		
		int writeNo = Integer.parseInt(request.getParameter("write_no"));	//게시글가져올때 필요함			
		int productNo = Integer.parseInt(request.getParameter("productNo"));//관련상품불러올때필요함
		
		WriteAll wAll = new WriteService().selectOneDetailPrint(writeNo);		
		
		ArrayList<WriteAll> pList = new WriteService().selectRelationProduct(productNo);		
		ArrayList<CommentsAll> coList = new WriteService().selectCommentwrite(writeNo);
		
		if(wAll != null) {
			//정상적으로 게시물을 읽어 왔다면 !! (해당 게시물을 가지고 jsp페이지로 이동~~)
			RequestDispatcher view = request.getRequestDispatcher("/views/community/detail/detail.jsp");
			request.setAttribute("wAll", wAll);
			request.setAttribute("coList", coList);
			request.setAttribute("pList", pList);
			view.forward(request, response);
			
		}else {
			//정상적으로 게시물을 읽어오지 못한다면!!(읽어오지 못했다라는 메시지를 출력하는 페이지로 보내라!)
			RequestDispatcher view = request.getRequestDispatcher("/views/community/detail/detailFail.jsp");
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
