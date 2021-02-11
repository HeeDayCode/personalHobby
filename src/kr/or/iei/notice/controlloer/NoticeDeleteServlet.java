package kr.or.iei.notice.controlloer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.iei.notice.model.service.NoticeService;

/**
 * Servlet implementation class NoticeDeleteServlet
 */
@WebServlet("/noticeDelete.kh")
public class NoticeDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo =Integer.parseInt(request.getParameter("boardNo"));
		
		  int result = new NoticeService().deleteNoticeBoard(boardNo);
	      
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=UTF-8");
			
	      
	      PrintWriter out = response.getWriter();
	      if(result>0) {
				out.println("<script>alert('삭제처리 완료'); </script>");				
				out.println("<script>location.replace('/question.kh'); </script>");
			}else {
				out.println("<script>alert('삭제오류발생'); </script>");
				out.println("<script>location.replace('/question.kh'); </script>");
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
