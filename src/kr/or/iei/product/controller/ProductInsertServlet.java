package kr.or.iei.product.controller;

import java.io.File;
import java.io.IOException;
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

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.or.iei.img.model.vo.Img;
import kr.or.iei.product.model.service.ProductService;
import kr.or.iei.product.model.vo.Product;
import kr.or.iei.product.model.vo.ProductOption;

/**
 * Servlet implementation class ProductinsertServlet
 */
@WebServlet("/productInsert.kh")
public class ProductInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* 이미지 업로드 */
    	
        //현재 해당 파일의 경로
        String uploadPath = "/resources/images/product";
        
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
        String originalName_sub = multi.getFilesystemName("file_sub");
        String originalName_main = multi.getFilesystemName("file_main");
        
        // 업로드 시간 포맷 및 현재 시간값 가져오기
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"); //포맷 만들기
        long currentTime = Calendar.getInstance().getTimeInMillis(); //시간값 가져오기
        Timestamp uploadTime = Timestamp.valueOf(formatter.format(currentTime));
        
        //원본 파일의 이름 바꾸기(시간값_kh)
        File file_sub = new File(realUploadPath+"\\"+originalName_sub); 
        File file_main = new File(realUploadPath+"\\"+originalName_main); 
        
        //renameTo 메소드로 파일 이름 변경
        file_sub.renameTo(new File(realUploadPath+"\\"+currentTime+"_sub"));
        String changedName_sub = currentTime+"_sub";//DB에 들어갈 값을 만들어주는 부분
        file_main.renameTo(new File(realUploadPath+"\\"+currentTime+"_main"));
        String changedName_main = currentTime+"_main";//DB에 들어갈 값을 만들어주는 부분
        
        // 파일 이름이 변경되면 새롭게 연결하는 파일 객체 필요
        File reNameFile_sub = new File(realUploadPath+"\\"+changedName_sub);
        String imgPath_sub = reNameFile_sub.getPath();
        File reNameFile_main = new File(realUploadPath+"\\"+changedName_main);
        String imgPath_main = reNameFile_main.getPath();
        
        
        // Service에 보내기 위해 패키징
        Img imgData_sub = new Img();
        imgData_sub.setOriginalName(originalName_sub);
        imgData_sub.setChangedName(changedName_sub);
        imgData_sub.setImgPath(imgPath_sub);
        imgData_sub.setUploadTime(uploadTime);
        
        Img imgData_main = new Img();
        imgData_main.setOriginalName(originalName_main);
        imgData_main.setChangedName(changedName_main);
        imgData_main.setImgPath(imgPath_main);
        imgData_main.setUploadTime(uploadTime);
        
        
        
        
        /* 텍스트 업로드 */
        
        // 인코딩 처리
        request.setCharacterEncoding("UTF-8");
        
        // 웹에서 보내온 값 자바코드 저장
        int categoryNo =Integer.parseInt(multi.getParameter("productCategory"));
        String[] options = multi.getParameterValues("options");
        String option=options[0];
        for(int i=1; i<options.length; i++) {
        	option=option+","+options[i];
        }
        String productName = multi.getParameter("productName");
        int productPrice = Integer.parseInt(multi.getParameter("insertPrice"));
        String productText = multi.getParameter("productSummary");
        
        /*
        System.out.println(categoryNo+"//"+option+"//"+productName+"//"+productPrice +"//"+productText);
        */
        
        Product productInfo = new Product();
        ProductOption productOption = new ProductOption();
        
        productInfo.setCategoryNo(categoryNo);
        productInfo.setProductName(productName);
        productInfo.setProductPrice(productPrice);
        productInfo.setProductText(productText);                         
        
        productOption.setOptions(option);
        
        
        
        // 비즈니스 로직 처리
        
        boolean result = new ProductService().insertProduct(productInfo, productOption, imgData_sub, imgData_main);
        
        
        // 결과 처리
        if(result==true)
        {
           RequestDispatcher view = request.getRequestDispatcher("/productAllList.kh");
           view.forward(request, response);
        }else {
        	reNameFile_sub.delete();
        	reNameFile_main.delete();
           RequestDispatcher view = request.getRequestDispatcher("/views/admin/product/productInsertFail.jsp");
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
