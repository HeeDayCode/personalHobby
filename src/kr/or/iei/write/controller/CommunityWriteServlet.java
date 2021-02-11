package kr.or.iei.write.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.iei.img.model.vo.Img;
import kr.or.iei.member.model.vo.MemberAll;
import kr.or.iei.write.model.service.WriteService;

/**
 * Servlet implementation class CommunityWriteServlet
 */

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 50, maxRequestSize = 1024 * 1024 * 50 * 5)

@WebServlet("/communityWrite.kh")
public class CommunityWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CommunityWriteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Write DB에 필요한 값 생성
		request.setCharacterEncoding("utf-8");
		String content = request.getParameter("content");
		// System.out.println("작성한 글 내용 : " + content);

		int productNo = Integer.parseInt(request.getParameter("productNo"));
		// System.out.println("상품 번호 : " + productNo);

		HttpSession session = request.getSession();
		MemberAll mAll = (MemberAll) session.getAttribute("memberAll");
		int memberNo = mAll.getM().getMemberNo();

		// --------------------------------------------------------
		// img DB에 필요한 값 생성

		javax.servlet.http.Part filePart = request.getPart("file");
		String fileName = filePart.getSubmittedFileName();
		InputStream fis = filePart.getInputStream();

		// 파일이 저장되는 실제 경로
		String realPath = request.getServletContext().getRealPath("/resources/images/community/write");

		String filePath = realPath + File.separator + fileName;
		FileOutputStream fos = new FileOutputStream(filePath);

		// System.out.println(filePath);
		// System.out.println(fileName);

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		long currentTime = Calendar.getInstance().getTimeInMillis();
		Timestamp uploadTime = Timestamp.valueOf(formatter.format(currentTime));

		File file = new File(filePath + "/" + fileName);
		file.renameTo(new File(filePath + "/" + currentTime + "_main"));
		String changedFileName = currentTime + "_main";

		File reNameFile = new File(filePath + "/" + changedFileName);
		String filePath_re = reNameFile.getPath();

		// --------------------------------------------------------

		// Byte값으로 읽어들임

		byte[] buf = new byte[1024];
		int size = 0;
		while ((size = fis.read(buf)) != -1) {
			fos.write(buf, 0, size);
		}

		fos.close();
		fis.close();

		Img img = new Img();
		img.setOriginalName(fileName);
		img.setChangedName(changedFileName);
		img.setImgPath(filePath_re);
		img.setUploadTime(uploadTime);
		img.setOriginalName(fileName);

		boolean result = new WriteService().insertCommunityWrite(productNo, memberNo, img, content);

		RequestDispatcher view = request.getRequestDispatcher("/views/community/write/writeInsertResult.jsp");

		if (result == true) {
			request.setAttribute("result", result); // 성공시 true 값을 전달
		} else {
			// 비즈니스 로직 처리시 실패했다면 파일도 삭제를 해주어야 함
			reNameFile.delete(); // 해당 파일을 삭제
			request.setAttribute("result", result); // 실패시 false 값을 전달
		}

		view.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
