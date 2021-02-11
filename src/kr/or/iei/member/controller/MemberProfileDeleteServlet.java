package kr.or.iei.member.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.iei.member.model.service.MemberService;
import kr.or.iei.member.model.vo.MemberAll;
import kr.or.iei.member.model.vo.MemberImg;

/**
 * Servlet implementation class MemberProfileDeleteServlet
 */
@WebServlet("/profileDelete.kh")
public class MemberProfileDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberProfileDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청한 사용자의 memberNo 가져옴
		HttpSession session = request.getSession();
		MemberAll mAll = (MemberAll)session.getAttribute("memberAll");
		int userNo = mAll.getM().getMemberNo();
		
		MemberImg mImg = new MemberService().selectProfileImg(userNo);
		String fileName= mImg.getChangedFileName();
		int result = new MemberService().deleteProfileImg(userNo, fileName);
		
		// 결과 처리
		RequestDispatcher view = request.getRequestDispatcher("/views/member/myPage/profileUpload.jsp");
		if(result>0) {
			// 파일이 정상적으로 삭제되었다면	
			File file = new File(mImg.getFilePath());
			file.delete(); //해당 파일을 연결하여 삭제
		}
		request.setAttribute("result", result);
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
