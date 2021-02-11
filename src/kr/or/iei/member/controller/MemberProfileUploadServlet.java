package kr.or.iei.member.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.or.iei.member.model.service.MemberService;
import kr.or.iei.member.model.vo.MemberAll;
import kr.or.iei.member.model.vo.MemberImg;

/**
 * Servlet implementation class MemberProfileUploadServlet
 */
@WebServlet("/profileUpload.kh")
public class MemberProfileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberProfileUploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 회원 번호 가져오기
				HttpSession session = request.getSession();
				MemberAll mAll = (MemberAll)session.getAttribute("memberAll");
				
				int userNo = mAll.getM().getMemberNo(); 
				
				MemberImg mImgCheck = new MemberService().selectProfileImg(userNo);
				// 기존에 프로필 사진이 있는지 확인하기 위해
		
				if(mImgCheck != null) {
					String fileName= mImgCheck.getChangedFileName();
					int resultCheck = new MemberService().deleteProfileImg(userNo, fileName);
					
					if(resultCheck>0) {
						// 파일이 정상적으로 삭제되었다면	
						File file = new File(mImgCheck.getFilePath());
						file.delete(); //해당 파일을 연결하여 삭제
					}
				}
				
				
				// Web Content로부터 사진이 업로드 되는 경로
				String uploadPath = "/resources/images/member/myPage/";

				// 현재 프로젝트에 대한 정보를 가지고 있는 객체
				ServletContext context = request.getServletContext();

				// ServletContext를 이용하여 실제 경로를 가져옴
				String realUploadPath = context.getRealPath(uploadPath);

				// 파일 사이즈 10MB 제한
				int uploadFileSizeLimit = 10 * 1024 * 1024;

				// 인코딩 값
				String encType = "UTF-8";

				// MultipartRequest 객체 생성. 마지막은 정책 설정 객체 생성
				MultipartRequest multi = new MultipartRequest(request, realUploadPath, uploadFileSizeLimit, encType,
						new DefaultFileRenamePolicy());

				// 파일 이름 가져오기
				String originalFileName = multi.getFilesystemName("file");
				
				
				// 파일 확장자명 가져오기
				String extension = originalFileName.substring(originalFileName.lastIndexOf(".")+1);
				
				
				// 이미지 파일만 업로드 가능하게 설정
				if(extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png") || extension.equals("gif") || extension.equals("bmp")) 
				{
					
					// 업로드 시간 생성
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");// 포맷 생성
					long currentTime = Calendar.getInstance().getTimeInMillis(); // 시간값 가져오기
					Timestamp uploadTime = Timestamp.valueOf(formatter.format(currentTime));

					// 원본 파일의 이름을 바꾸는 작업 (바꾸는 파일의 이름은 시간값_ph)
					File file = new File(realUploadPath + "\\" + originalFileName);
					file.renameTo(new File(realUploadPath + "\\" + currentTime + "_ph"));
					String changedFileName = currentTime + "_ph";

					File reNameFile = new File(realUploadPath + "\\" + changedFileName);
					String filePath = reNameFile.getPath();

					// 해당 업로드 된 file의 사이즈
					int fileSize = (int) reNameFile.length();
					
					// 해당 정보 vo 객체에 저장
					MemberImg mImg = new MemberImg();
					mImg.setMemberNo(userNo);
					mImg.setOriginalFileName(originalFileName);
					mImg.setChangedFileName(changedFileName);
					mImg.setFilePath(filePath);
					mImg.setUploadTime(uploadTime);
					
					int result = new MemberService().insertProfileImg(mImg);
					request.setAttribute("result", result);
					RequestDispatcher view = request.getRequestDispatcher("/views/member/myPage/profileUpload.jsp");
					view.forward(request, response);
					
				}else {
					response.setCharacterEncoding("UTF-8");
					response.setContentType("text/html; charset=UTF-8");
					
					PrintWriter out = response.getWriter();
					
					out.println("<script>alert('10MB 이하의 이미지 형식(jpg, jpeg, png, gif, bmp) 형식의 파일만 업로드 가능합니다.')</script>");
					out.println("<script>location.replace('/views/member/myPage/profileUpload.jsp');</script>");
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
