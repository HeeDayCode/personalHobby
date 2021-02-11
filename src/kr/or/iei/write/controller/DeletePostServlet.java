package kr.or.iei.write.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.write.model.service.WriteService;

/**
 * Servlet implementation class DeletePostServlet
 */
@WebServlet("/deletePost.kh")
public class DeletePostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletePostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int writeNo = Integer.parseInt(request.getParameter("writeNo")); //지울게시물의 번호가져오기
		int result = new WriteService().deletePost(writeNo);
		
		 RequestDispatcher view=request.getRequestDispatcher("/views/community/detail/deletePost.jsp");
	      if(result>0) {
	         request.setAttribute("result", true);
	      }
	      else {
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
