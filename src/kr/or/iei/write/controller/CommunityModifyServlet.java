package kr.or.iei.write.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.or.iei.img.model.vo.Img;
import kr.or.iei.member.model.vo.MemberAll;
import kr.or.iei.product.model.vo.Product;
import kr.or.iei.product.model.vo.ProductOption;
import kr.or.iei.write.model.service.WriteService;
import kr.or.iei.write.model.vo.Write;
import kr.or.iei.write.model.vo.WriteAll;

/**
 * Servlet implementation class CommunityModifyServlet
 */
@MultipartConfig(
		fileSizeThreshold=1024*1024,
		maxFileSize=1024*1024*50,
		maxRequestSize=1024*1024*50*5	
		)

@WebServlet("/CommunityModify.kh")
public class CommunityModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommunityModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Write DB에 필요한 값 생성
	
	
		
 /* 이미지 업로드 */
    	
        //현재 해당 파일의 경로
        String uploadPath = "/resources/images/community/write";
        
        //현재 프로젝트의 정보를 가지고 있음
        ServletContext context = request.getServletContext();
        
        //ServletContext 이용 실제 경로 가져오기(WebCotent 경로출력)
        String realUploadPath = context.getRealPath(uploadPath);
        
        //byte 단위 1byte*1024 1kbyte 10byte
        int uploadFileSizeLimit = 10*1024*1024;
        
        //인코딩 
        String encType ="UTF-8";
        
        //MultipartRequest 객체 생성 하면서 정책 설정하기 라이브러리 활용
        MultipartRequest multi = new MultipartRequest(request, realUploadPath, uploadFileSizeLimit, encType, new DefaultFileRenamePolicy());            
        //new DefaultFileRenamePolicy()이것이 없으면 동일 인물이 동일한 이름으로 넣으면 덮어쓰게 된다
        
              
        
        //DB에 저장할 정보 추출
        //파일이름 가져오기
        String originalName_main = multi.getFilesystemName("file_main");
        
        // 업로드 시간 포맷 및 현재 시간값 가져오기
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"); //포맷 만들기
        long currentTime = Calendar.getInstance().getTimeInMillis(); //시간값 가져오기
        Timestamp uploadTime = Timestamp.valueOf(formatter.format(currentTime));
        
        //원본 파일의 이름 바꾸기(시간값_kh)
        File file_main = new File(realUploadPath+"\\"+originalName_main); 
        
        //renameTo 메소드로 파일 이름 변경
        file_main.renameTo(new File(realUploadPath+"\\"+currentTime+"_main"));
        String changedName_main = currentTime+"_main";//DB에 들어갈 값을 만들어주는 부분
        
        // 파일 이름이 변경되면 새롭게 연결하는 파일 객체 필요
        File reNameFile_main = new File(realUploadPath+"\\"+changedName_main);
        String imgPath_main = reNameFile_main.getPath();
        
        
        // Service에 보내기 위해 패키징

        
        Img img = new Img();
		img.setOriginalName(originalName_main);
		img.setChangedName(changedName_main);
		img.setImgPath(imgPath_main);
		img.setUploadTime(uploadTime);

        
        /* 텍스트 업로드 */
        
        // 인코딩 처리
        request.setCharacterEncoding("UTF-8");
        
        // 웹에서 보내온 값 자바코드 저장
        String content = multi.getParameter("content");
		int writeNo = Integer.parseInt(multi.getParameter("writeNo"));
		int productNo = Integer.parseInt(multi.getParameter("productNo"));
		int imgNo = Integer.parseInt(multi.getParameter("imgNo"));

		img.setImgNo(imgNo);
		
		HttpSession session = request.getSession();
		MemberAll mAll = (MemberAll)session.getAttribute("memberAll");
		int memberNo = mAll.getM().getMemberNo();
		
		Write w =new Write();
		w.setMemberNo(memberNo);
		w.setProductNo(productNo);
		w.setWriteExplain(content);
		w.setWriteNo(writeNo);
		
		WriteAll wAll = new WriteAll();
		wAll.setWrite(w);
		wAll.setImg(img);
		
		boolean result = new WriteService().updateCommunity(wAll);
		
		RequestDispatcher view = request.getRequestDispatcher("/views/community/write/writeUpdate.jsp");
		

			if(result==true) {
				request.setAttribute("result", true);  //성공시 true 값을 전달
			}else {
				//비즈니스 로직 처리시 실패했다면 파일도 삭제를 해주어야 함
				reNameFile_main.delete(); //해당 파일을 삭제
				request.setAttribute("result", false); //실패시 false 값을 전달
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
