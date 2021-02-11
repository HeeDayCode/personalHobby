package kr.or.iei.product.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.or.iei.common.JDBCTemplate;
import kr.or.iei.img.model.vo.Img;
import kr.or.iei.product.model.vo.Category;
import kr.or.iei.product.model.vo.Product;
import kr.or.iei.product.model.vo.ProductAll;
import kr.or.iei.product.model.vo.ProductAllSub;
import kr.or.iei.product.model.vo.ProductDate;
import kr.or.iei.product.model.vo.ProductDetail;
import kr.or.iei.product.model.vo.ProductOption;
import kr.or.iei.product.model.vo.ProductWrite;
import kr.or.iei.write.model.vo.Write;
import kr.or.iei.write.model.vo.WriteTag;

public class ProductDAO {

	public boolean insertProduct(Connection conn, Product productInfo, ProductOption productOption, Img imgData_sub,
			Img imgData_main) {
		PreparedStatement pstmt = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		PreparedStatement pstmt4 = null;
		PreparedStatement pstmt5 = null;

		int result0 = 0;
		int result1 = 0;
		int result2 = 0;
		int result3 = 0;
		int result4 = 0;
		int result5 = 0;

		boolean result = false;

		String query = "INSERT INTO IMG VALUES(IMG_NO_SEQ.NEXTVAL,?,?,?,sysdate,'N')";
		String query1 = "INSERT INTO PRODUCT VALUES(PRODUCT_NO_SEQ.NEXTVAL,?,?,?,?,IMG_NO_SEQ.CURRVAL)";
		String query2 = "INSERT INTO PRODUCT_DATE VALUES(PRODUCT_NO_SEQ.CURRVAL,SYSDATE,'','N')";
		String query3 = "INSERT INTO IMG VALUES(IMG_NO_SEQ.NEXTVAL,?,?,?,sysdate,'N')";
		String query4 = "INSERT INTO PRODUCT_DETAIL VALUES(PRODUCT_NO_SEQ.CURRVAL,'이미지로 대체',IMG_NO_SEQ.CURRVAL)";
		String query5 = "INSERT INTO PRODUCT_OPTION VALUES(PRODUCT_NO_SEQ.CURRVAL,?)";

		try {

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, imgData_main.getOriginalName());
			pstmt.setString(2, imgData_main.getChangedName());
			pstmt.setString(3, imgData_main.getImgPath());
			result0 = pstmt.executeUpdate();

			pstmt1 = conn.prepareStatement(query1);
			pstmt1.setInt(1, productInfo.getCategoryNo());
			pstmt1.setString(2, productInfo.getProductName());
			pstmt1.setInt(3, productInfo.getProductPrice());
			pstmt1.setString(4, productInfo.getProductText());
			result1 = pstmt1.executeUpdate();

			pstmt2 = conn.prepareStatement(query2);
			result2 = pstmt2.executeUpdate();

			pstmt3 = conn.prepareStatement(query3);
			pstmt3.setString(1, imgData_sub.getOriginalName());
			pstmt3.setString(2, imgData_sub.getChangedName());
			pstmt3.setString(3, imgData_sub.getImgPath());
			result3 = pstmt3.executeUpdate();

			pstmt4 = conn.prepareStatement(query4);
			result4 = pstmt4.executeUpdate();

			pstmt5 = conn.prepareStatement(query5);
			pstmt5.setString(1, productOption.getOptions());
			result5 = pstmt5.executeUpdate();

			if (result0 > 0 && result1 > 0 && result2 > 0 && result3 > 0 && result4 > 0 && result5 > 0) {
				result = true;

			} else {
				result = false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(pstmt2);
			JDBCTemplate.close(pstmt3);
			JDBCTemplate.close(pstmt4);
			JDBCTemplate.close(pstmt5);

		}
		return result;
	}

	public boolean deleteProduct(Connection conn, int productNo, int imgNo) {
		PreparedStatement pstmt0 = null;
		PreparedStatement pstmt1 = null;

		int result0 = 0;
		int result1 = 0;
		boolean result = false;

		String query0 = "UPDATE IMG SET IMAGE_DELETE_YN='Y' WHERE IMG_NO=? OR IMG_NO=?";
		String query1 = "UPDATE PRODUCT_DATE SET PRODUCT_YN='Y', PRODUCT_DELETE_DATE=SYSDATE WHERE PRODUCT_NO=?";

		try {
			pstmt0 = conn.prepareStatement(query0);
			pstmt0.setInt(1, imgNo);
			pstmt0.setInt(2, imgNo + 1);
			result0 = pstmt0.executeUpdate();

			pstmt1 = conn.prepareStatement(query1);
			pstmt1.setInt(1, productNo);
			result1 = pstmt1.executeUpdate();

			if (result0 > 0 && result1 > 0) {
				result = true;
			} else {
				result = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt1);
			JDBCTemplate.close(pstmt0);
		}
		return result;
	}

	public ArrayList<ProductAll> selectAllProductPageList(Connection conn, int currentPage, int recordCountPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ProductAll> list = new ArrayList<ProductAll>();

		/*
		 * select * FROM (SELECT ROW_NUMBER() OVER (ORDER BY BOARDNO desc) AS ROW_NUM,
		 * BOARD.* FROM BOARD) WHERE ROW_NUM BETWEEN 1 AND 5;
		 */
		/*
		 * start =현재 페이지 * 보여줄 페이지 개수 - 4 end = 현재 페이지 * 보여줄 페이지 개수
		 */
		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;

		String query = "select * from (SELECT ROW_NUMBER() OVER (order by PRODUCT.PRODUCT_NO desc) AS ROW_NUM,"
				+ "IMG.IMG_NO, PRODUCT.PRODUCT_NO, PRODUCT.CATEGORY_NO, PRODUCT.PRODUCT_NAME, PRODUCT.PRODUCT_PRICE,"
				+ "PRODUCT.PRODUCT_TEXT, PRODUCT_DATE.PRODUCT_ENROLL_DATE, PRODUCT_DATE.PRODUCT_DELETE_DATE,"
				+ "PRODUCT_DATE.PRODUCT_YN, PRODUCT_OPTION.OPTIONS, IMG.ORIGINAL_NAME, IMG.CHANGED_NAME, "
				+ "IMG.IMG_PATH, IMG.UPLOAD_TIME, IMG.IMAGE_DELETE_YN " + "FROM PRODUCT "
				+ "LEFT JOIN PRODUCT_DATE on (PRODUCT.PRODUCT_NO = PRODUCT_DATE.PRODUCT_NO) "
				+ "LEFT JOIN PRODUCT_OPTION  on (PRODUCT.PRODUCT_NO = PRODUCT_OPTION.PRODUCT_NO) "
				+ "LEFT JOIN IMG on (PRODUCT.IMG_NO = IMG.IMG_NO) " + "WHERE PRODUCT_YN='N') "
				+ "WHERE ROW_NUM BETWEEN ? AND ? ";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				/*
				 * Category category = new Category();
				 * category.setCategoryNo(rset.getInt("Category_No"));
				 * category.setCategoryName(rset.getString("Category_Name"));
				 */

				Product product = new Product();
				product.setProductNo(rset.getInt("Product_No"));
				product.setCategoryNo(rset.getInt("Category_No"));
				product.setProductName(rset.getString("Product_Name"));
				product.setProductPrice(rset.getInt("Product_Price"));
				product.setProductText(rset.getString("Product_Text"));
				product.setImgNo(rset.getInt("Img_No"));

				ProductDate pDate = new ProductDate();
				/* pDate.setProductNo(rset.getInt("Product_No")); */
				pDate.setProductEnrollDate(rset.getDate("Product_Enroll_Date"));
				pDate.setProductDeleteDate(rset.getDate("Product_Delete_Date"));

				/*
				 * ProductDetail pDetail = new ProductDetail();
				 * pDetail.setProductNo(rset.getInt("Product_No"));
				 * pDetail.setProductDescription(rset.getString("Product_Description")); 커럼명바꾸기
				 * pDetail.setImgNo(rset.getInt("Img_No"));
				 */

				ProductOption pOption = new ProductOption();
				/* pOption.setProductNo(rset.getInt("Product_No")); */
				pOption.setOptions(rset.getString("Options"));

				Img img = new Img();
				img.setImgNo(rset.getInt("Img_No"));
				img.setOriginalName(rset.getString("Original_Name"));
				img.setChangedName(rset.getString("Changed_Name"));
				img.setImgPath(rset.getString("Img_Path"));
				img.setUploadTime(rset.getTimestamp("Upload_Time"));

				ProductAll productAll = new ProductAll();
				/* productAll.setCategory(category); */
				productAll.setProduct(product);
				productAll.setpDate(pDate);
				/* productAll.setpDetail(pDetail); */
				productAll.setpOption(pOption);
				productAll.setImg(img);

				list.add(productAll);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;

	}

	public String getPageNavi(Connection conn, int currentPage, int recordCountPerPage, int naviCountPerPage) {
		// 현재 변수를 확인
		// currentPage 현재 페이지를 가지고 있는 변수
		// recordCountPerPage 현 페이지당 보여질 게시물의 수
		// naviCountPerPage pageNavi가 몇개씩 보여질 것인지에 대한 개수

		int postTotalCount = postTotalCount(conn); // 전체 게시물의 개수를 구하기 위한 메소드

		// 생성될 페이지의 개수를 구하기
		// 108/5 몫이 페이지가 되는 데 이때 나머지가 있으면 +1 없으면 그대로
		int pageTotalCount;
		if (postTotalCount % recordCountPerPage > 0) {
			pageTotalCount = postTotalCount / recordCountPerPage + 1;
		} else {
			pageTotalCount = postTotalCount / recordCountPerPage;
		}

		// 현재 페이지를 중심으로 네비 페이지 계산하기
		// pageNavi를 계산하기
		// startNavi 현재페이지-1 / 보여질 navi개수 * 보여질navi 개수 +1
		int startNavi = ((currentPage - 1) / naviCountPerPage) * naviCountPerPage + 1;

		// endNavi 개수 시작 navi번호 + 보여질navi개수 -1
		int endNavi = startNavi + naviCountPerPage - 1;
		if (endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}

		// 이제 네비의 모양을 구현해야함
		StringBuilder sb = new StringBuilder();

		if (startNavi != 1) {
			sb.append("<a href='/productAllList.kh?currentPage=" + (startNavi - 1) + "'><</a> ");
		}
		for (int i = startNavi; i <= endNavi; i++) {
			if (i == currentPage) {
				sb.append("<a href='/productAllList.kh?currentPage=" + i + "'><b>" + i + "</b></a> ");
			} else {
				sb.append("<a href='/productAllList.kh?currentPage=" + i + "'>" + i + "</a> ");
			}
		}
		if (endNavi != pageTotalCount) {
			sb.append("<a href='/productAllList.kh?currentPage=" + (endNavi + 1) + "'>></a> ");
		}
		/* System.out.println(sb); */
		return sb.toString();

	}

	private int postTotalCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int postTotalCount = 0;

		String query = "SELECT count(*) as totalCount FROM PRODUCT " + "LEFT JOIN PRODUCT_DATE USING (PRODUCT_NO) "
				+ "LEFT JOIN PRODUCT_OPTION USING (PRODUCT_NO)" + "LEFT JOIN IMG USING (IMG_NO)"
				+ "WHERE PRODUCT_YN='N' and IMAGE_DELETE_YN='N'" + "order by PRODUCT_NO desc";

		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			rset.next();
			postTotalCount = rset.getInt("totalCount");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return postTotalCount;
	}

	public ProductAll selectOneProduct(Connection conn, int productNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ProductAll productAll = null;

		String query = "SELECT * FROM PRODUCT LEFT JOIN PRODUCT_DATE USING (PRODUCT_NO) LEFT JOIN PRODUCT_DETAIL USING (PRODUCT_NO) LEFT JOIN PRODUCT_OPTION USING (PRODUCT_NO) WHERE PRODUCT_YN='N' AND PRODUCT_NO=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, productNo);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				Product product = new Product();
				product.setProductNo(rset.getInt("Product_No"));
				product.setCategoryNo(rset.getInt("Category_No"));
				product.setProductName(rset.getString("Product_Name"));
				product.setProductPrice(rset.getInt("Product_Price"));
				product.setProductText(rset.getString("Product_Text"));
				product.setImgNo(rset.getInt("Img_No"));

				ProductDate pDate = new ProductDate();
				pDate.setProductEnrollDate(rset.getDate("Product_Enroll_Date"));
				pDate.setProductDeleteDate(rset.getDate("Product_Delete_Date"));

				ProductOption pOption = new ProductOption();
				pOption.setOptions(rset.getString("Options"));

				/*
				 * Img img = new Img(); img.setImgNo(rset.getInt("Img_No"));
				 * img.setOriginalName(rset.getString("Original_Name"));
				 * img.setChangedName(rset.getString("Changed_Name"));
				 * img.setImgPath(rset.getString("Img_Path"));
				 * img.setUploadTime(rset.getTimestamp("Upload_Time"));
				 */

				productAll = new ProductAll();
				productAll.setProduct(product);
				productAll.setpDate(pDate);
				productAll.setpOption(pOption);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return productAll;
	}

	public boolean updateProduct(Connection conn, Product productInfo, ProductOption productOption, Img imgData_sub,
			Img imgData_main) {
		PreparedStatement pstmt0 = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;

		int result0 = 0;
		int result1 = 0;
		int result2 = 0;
		int result3 = 0;

		boolean result = false;

		String query0 = "UPDATE IMG SET ORIGINAL_NAME=?, CHANGED_NAME=?, IMG_PATH=?, UPLOAD_TIME=SYSDATE WHERE IMG_NO=?";
		String query1 = "UPDATE IMG SET ORIGINAL_NAME=?, CHANGED_NAME=?, IMG_PATH=?, UPLOAD_TIME=SYSDATE WHERE IMG_NO=?";
		String query2 = "UPDATE PRODUCT SET CATEGORY_NO=?, PRODUCT_NAME=?, PRODUCT_PRICE=?, PRODUCT_TEXT=? WHERE PRODUCT_NO=?";
		String query3 = "UPDATE PRODUCT_OPTION SET OPTIONS=? WHERE PRODUCT_NO=?";

		try {

			pstmt0 = conn.prepareStatement(query0);
			pstmt0.setString(1, imgData_main.getOriginalName());
			pstmt0.setString(2, imgData_main.getChangedName());
			pstmt0.setString(3, imgData_main.getImgPath());
			pstmt0.setInt(4, productInfo.getImgNo());
			result0 = pstmt0.executeUpdate();

			pstmt1 = conn.prepareStatement(query1);
			pstmt1.setString(1, imgData_sub.getOriginalName());
			pstmt1.setString(2, imgData_sub.getChangedName());
			pstmt1.setString(3, imgData_sub.getImgPath());
			pstmt1.setInt(4, productInfo.getImgNo() + 1);
			result1 = pstmt1.executeUpdate();

			pstmt2 = conn.prepareStatement(query2);
			pstmt2.setInt(1, productInfo.getCategoryNo());
			pstmt2.setString(2, productInfo.getProductName());
			pstmt2.setInt(3, productInfo.getProductPrice());
			pstmt2.setString(4, productInfo.getProductText());
			pstmt2.setInt(5, productInfo.getProductNo());
			result2 = pstmt2.executeUpdate();

			pstmt3 = conn.prepareStatement(query3);
			pstmt3.setString(1, productOption.getOptions());
			pstmt3.setInt(2, productInfo.getProductNo());
			result3 = pstmt3.executeUpdate();

			if (result0 > 0 && result1 > 0 && result2 > 0 && result3 > 0) {
				result = true;

			} else {
				result = false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt0);
			JDBCTemplate.close(pstmt1);
			JDBCTemplate.close(pstmt2);
			JDBCTemplate.close(pstmt3);

		}
		return result;
	}

	public ArrayList<ProductAll> searchProductName(Connection conn, String keyword, String startDate, String endDate) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ProductAll> list = new ArrayList<ProductAll>();

		String query = "SELECT * FROM PRODUCT LEFT JOIN PRODUCT_DATE USING (PRODUCT_NO)"
				+ "LEFT JOIN PRODUCT_OPTION USING (PRODUCT_NO) " + "LEFT JOIN IMG USING (IMG_NO) "
				+ "WHERE PRODUCT_YN='N' and IMAGE_DELETE_YN='N' and PRODUCT_NAME LIKE ? "
				+ "AND PRODUCT_ENROLL_DATE >= to_date(?) AND PRODUCT_ENROLL_DATE < to_date(?)+1"
				+ "order by PRODUCT_NO desc";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setString(2, startDate);
			pstmt.setString(3, endDate);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Product product = new Product();
				product.setProductNo(rset.getInt("Product_No"));
				product.setCategoryNo(rset.getInt("Category_No"));
				product.setProductName(rset.getString("Product_Name"));
				product.setProductPrice(rset.getInt("Product_Price"));
				product.setProductText(rset.getString("Product_Text"));
				product.setImgNo(rset.getInt("Img_No"));

				ProductDate pDate = new ProductDate();
				/* pDate.setProductNo(rset.getInt("Product_No")); */
				pDate.setProductEnrollDate(rset.getDate("Product_Enroll_Date"));
				pDate.setProductDeleteDate(rset.getDate("Product_Delete_Date"));

				/*
				 * ProductDetail pDetail = new ProductDetail();
				 * pDetail.setProductNo(rset.getInt("Product_No"));
				 * pDetail.setProductDescription(rset.getString("Product_Description")); 커럼명바꾸기
				 * pDetail.setImgNo(rset.getInt("Img_No"));
				 */

				ProductOption pOption = new ProductOption();
				/* pOption.setProductNo(rset.getInt("Product_No")); */
				pOption.setOptions(rset.getString("Options"));

				Img img = new Img();
				img.setImgNo(rset.getInt("Img_No"));
				img.setOriginalName(rset.getString("Original_Name"));
				img.setChangedName(rset.getString("Changed_Name"));
				img.setImgPath(rset.getString("Img_Path"));
				img.setUploadTime(rset.getTimestamp("Upload_Time"));

				ProductAll productAll = new ProductAll();
				/* productAll.setCategory(category); */
				productAll.setProduct(product);
				productAll.setpDate(pDate);
				/* productAll.setpDetail(pDetail); */
				productAll.setpOption(pOption);
				productAll.setImg(img);

				list.add(productAll);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return list;
	}

	public ArrayList<ProductAll> searchProductNo(Connection conn, int keywordNo, String startDate, String endDate) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ProductAll> list = new ArrayList<ProductAll>();

		String query = "SELECT * FROM PRODUCT LEFT JOIN PRODUCT_DATE USING (PRODUCT_NO)"
				+ "LEFT JOIN PRODUCT_OPTION USING (PRODUCT_NO)" + "LEFT JOIN IMG USING (IMG_NO) "
				+ "WHERE PRODUCT_YN='N' and IMAGE_DELETE_YN='N' and PRODUCT_NO = ? "
				+ "AND PRODUCT_ENROLL_DATE >= to_date(?) AND PRODUCT_ENROLL_DATE < to_date(?)+1"
				+ "order by PRODUCT_NO desc";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, keywordNo);
			pstmt.setString(2, startDate);
			pstmt.setString(3, endDate);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Product product = new Product();
				product.setProductNo(rset.getInt("Product_No"));
				product.setCategoryNo(rset.getInt("Category_No"));
				product.setProductName(rset.getString("Product_Name"));
				product.setProductPrice(rset.getInt("Product_Price"));
				product.setProductText(rset.getString("Product_Text"));
				product.setImgNo(rset.getInt("Img_No"));

				ProductDate pDate = new ProductDate();
				/* pDate.setProductNo(rset.getInt("Product_No")); */
				pDate.setProductEnrollDate(rset.getDate("Product_Enroll_Date"));
				pDate.setProductDeleteDate(rset.getDate("Product_Delete_Date"));

				/*
				 * ProductDetail pDetail = new ProductDetail();
				 * pDetail.setProductNo(rset.getInt("Product_No"));
				 * pDetail.setProductDescription(rset.getString("Product_Description")); 커럼명바꾸기
				 * pDetail.setImgNo(rset.getInt("Img_No"));
				 */

				ProductOption pOption = new ProductOption();
				/* pOption.setProductNo(rset.getInt("Product_No")); */
				pOption.setOptions(rset.getString("Options"));

				Img img = new Img();
				img.setImgNo(rset.getInt("Img_No"));
				img.setOriginalName(rset.getString("Original_Name"));
				img.setChangedName(rset.getString("Changed_Name"));
				img.setImgPath(rset.getString("Img_Path"));
				img.setUploadTime(rset.getTimestamp("Upload_Time"));

				ProductAll productAll = new ProductAll();
				/* productAll.setCategory(category); */
				productAll.setProduct(product);
				productAll.setpDate(pDate);
				/* productAll.setpDetail(pDetail); */
				productAll.setpOption(pOption);
				productAll.setImg(img);

				list.add(productAll);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public ArrayList<ProductAll> searchProductCategoryNo(Connection conn, int keywordNo, String startDate,
			String endDate) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ProductAll> list = new ArrayList<ProductAll>();

		String query = "SELECT * FROM PRODUCT LEFT JOIN PRODUCT_DATE USING (PRODUCT_NO)"
				+ "LEFT JOIN PRODUCT_OPTION USING (PRODUCT_NO)" + "LEFT JOIN IMG USING (IMG_NO) "
				+ "WHERE PRODUCT_YN='N' and IMAGE_DELETE_YN='N' and CATEGORY_NO=? "
				+ "AND PRODUCT_ENROLL_DATE >= to_date(?) AND PRODUCT_ENROLL_DATE < to_date(?)+1"
				+ "order by PRODUCT_NO desc";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, keywordNo);
			pstmt.setString(2, startDate);
			pstmt.setString(3, endDate);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Product product = new Product();
				product.setProductNo(rset.getInt("Product_No"));
				product.setCategoryNo(rset.getInt("Category_No"));
				product.setProductName(rset.getString("Product_Name"));
				product.setProductPrice(rset.getInt("Product_Price"));
				product.setProductText(rset.getString("Product_Text"));
				product.setImgNo(rset.getInt("Img_No"));

				ProductDate pDate = new ProductDate();
				/* pDate.setProductNo(rset.getInt("Product_No")); */
				pDate.setProductEnrollDate(rset.getDate("Product_Enroll_Date"));
				pDate.setProductDeleteDate(rset.getDate("Product_Delete_Date"));

				/*
				 * ProductDetail pDetail = new ProductDetail();
				 * pDetail.setProductNo(rset.getInt("Product_No"));
				 * pDetail.setProductDescription(rset.getString("Product_Description")); 커럼명바꾸기
				 * pDetail.setImgNo(rset.getInt("Img_No"));
				 */

				ProductOption pOption = new ProductOption();
				/* pOption.setProductNo(rset.getInt("Product_No")); */
				pOption.setOptions(rset.getString("Options"));

				Img img = new Img();
				img.setImgNo(rset.getInt("Img_No"));
				img.setOriginalName(rset.getString("Original_Name"));
				img.setChangedName(rset.getString("Changed_Name"));
				img.setImgPath(rset.getString("Img_Path"));
				img.setUploadTime(rset.getTimestamp("Upload_Time"));

				ProductAll productAll = new ProductAll();
				/* productAll.setCategory(category); */
				productAll.setProduct(product);
				productAll.setpDate(pDate);
				/* productAll.setpDetail(pDetail); */
				productAll.setpOption(pOption);
				productAll.setImg(img);

				list.add(productAll);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public ArrayList<ProductAll> storeMainNew(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		ArrayList<ProductAll> list = new ArrayList<ProductAll>();

		String query = "SELECT * FROM "
				+ "(SELECT * FROM PRODUCT LEFT JOIN PRODUCT_DATE USING (PRODUCT_NO) LEFT JOIN IMG USING (IMG_NO) "
				+ "WHERE PRODUCT_YN='N' ORDER BY PRODUCT_ENROLL_DATE DESC) WHERE ROWNUM<=8";

		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				Product p = new Product();
				Img img = new Img();

				p.setProductNo(rset.getInt("PRODUCT_NO"));
				p.setCategoryNo(rset.getInt("CATEGORY_NO"));
				p.setProductName(rset.getString("PRODUCT_NAME"));
				p.setProductPrice(rset.getInt("PRODUCT_PRICE"));
				p.setProductText(rset.getString("PRODUCT_TEXT"));
				p.setImgNo(rset.getInt("IMG_NO"));

				img.setImgNo(rset.getInt("IMG_NO"));
				img.setOriginalName(rset.getString("ORIGINAL_NAME"));
				img.setChangedName(rset.getString("CHANGED_NAME"));
				img.setImgPath(rset.getString("IMG_PATH"));
				img.setUploadTime(rset.getTimestamp("UPLOAD_TIME"));
				img.setImgDeleteYN(rset.getString("IMAGE_DELETE_YN").charAt(0));

				ProductAll pAll = new ProductAll();
				pAll.setProduct(p);
				pAll.setImg(img);
				list.add(pAll);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public ArrayList<ProductAll> storeMainHot(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		ArrayList<ProductAll> list = new ArrayList<ProductAll>();

		String query = "SELECT * FROM (SELECT * FROM PRODUCT INNER JOIN (SELECT * FROM (SELECT PRODUCT_NO "
				+ "FROM PRODUCT LEFT JOIN PRODUCT_DATE USING (PRODUCT_NO) LEFT JOIN ORDERS USING (PRODUCT_NO) "
				+ "WHERE PRODUCT_YN='N' GROUP BY PRODUCT_NO "
				+ "ORDER BY SUM(ORDERS_NUM) DESC)) USING (PRODUCT_NO) WHERE ROWNUM<=8) INNER JOIN IMG USING (IMG_NO)";

		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Product p = new Product();
				Img img = new Img();

				p.setProductNo(rset.getInt("PRODUCT_NO"));
				p.setCategoryNo(rset.getInt("CATEGORY_NO"));
				p.setProductName(rset.getString("PRODUCT_NAME"));
				p.setProductPrice(rset.getInt("PRODUCT_PRICE"));
				p.setProductText(rset.getString("PRODUCT_TEXT"));
				p.setImgNo(rset.getInt("IMG_NO"));

				img.setImgNo(rset.getInt("IMG_NO"));
				img.setOriginalName(rset.getString("ORIGINAL_NAME"));
				img.setChangedName(rset.getString("CHANGED_NAME"));
				img.setImgPath(rset.getString("IMG_PATH"));
				img.setUploadTime(rset.getTimestamp("UPLOAD_TIME"));
				img.setImgDeleteYN(rset.getString("IMAGE_DELETE_YN").charAt(0));

				ProductAll pAll = new ProductAll();
				pAll.setProduct(p);
				pAll.setImg(img);
				list.add(pAll);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public ArrayList<ProductAll> storeMainBest(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		ArrayList<ProductAll> list = new ArrayList<ProductAll>();

		String query = "SELECT * FROM PRODUCT LEFT JOIN IMG USING(IMG_NO) LEFT JOIN PRODUCT_DATE USING (PRODUCT_NO) "
				+ "WHERE PRODUCT_YN='N' ORDER BY PRODUCT_PRICE DESC";

		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Product p = new Product();
				Img img = new Img();

				p.setProductNo(rset.getInt("PRODUCT_NO"));
				p.setCategoryNo(rset.getInt("CATEGORY_NO"));
				p.setProductName(rset.getString("PRODUCT_NAME"));
				p.setProductPrice(rset.getInt("PRODUCT_PRICE"));
				p.setProductText(rset.getString("PRODUCT_TEXT"));
				p.setImgNo(rset.getInt("IMG_NO"));

				img.setImgNo(rset.getInt("IMG_NO"));
				img.setOriginalName(rset.getString("ORIGINAL_NAME"));
				img.setChangedName(rset.getString("CHANGED_NAME"));
				img.setImgPath(rset.getString("IMG_PATH"));
				img.setUploadTime(rset.getTimestamp("UPLOAD_TIME"));
				img.setImgDeleteYN(rset.getString("IMAGE_DELETE_YN").charAt(0));

				ProductAll pAll = new ProductAll();
				pAll.setProduct(p);
				pAll.setImg(img);
				list.add(pAll);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public ArrayList<ProductWrite> storeMainTop(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PreparedStatement pstmt1 = null;
		ResultSet rset1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rset2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rset3 = null;
		PreparedStatement pstmt4 = null;
		ResultSet rset4 = null;
		PreparedStatement pstmt5 = null;
		ResultSet rset5 = null;

		ArrayList<ProductWrite> list = new ArrayList<ProductWrite>();

		String query = "SELECT PRODUCT_NO FROM (SELECT PRODUCT_NO,COUNT(PRODUCT_NO) FROM WRITE "
				+ "GROUP BY PRODUCT_NO HAVING COUNT(PRODUCT_NO)>=4 " + "ORDER BY COUNT(PRODUCT_NO) DESC)";

		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			Product p = null;

			while (rset.next()) {
				p = new Product();
				p.setProductNo(rset.getInt("PRODUCT_NO"));

				// 결과 1,6,8
				try {
					String query1 = "SELECT * FROM (SELECT PRODUCT_NO,WRITE_NO FROM PRODUCT LEFT JOIN WRITE USING(PRODUCT_NO)"
							+ "WHERE PRODUCT_NO=? ORDER BY WRITE_LIKE DESC) WHERE ROWNUM<=4";

					pstmt1 = conn.prepareStatement(query1);
					pstmt1.setInt(1, p.getProductNo());
					rset1 = pstmt1.executeQuery();

					Write w = null;
					while (rset1.next()) {
						w = new Write();
						w.setWriteNo(rset1.getInt("WRITE_NO"));

						ProductWrite pWrite = null;

						String query2 = "SELECT * FROM PRODUCT LEFT JOIN PRODUCT_DATE USING (PRODUCT_NO) WHERE PRODUCT_YN='N' AND PRODUCT_NO=?";
						pstmt2 = conn.prepareStatement(query2);
						pstmt2.setInt(1, p.getProductNo());
						rset2 = pstmt2.executeQuery();

						while (rset2.next()) {
							Product p2 = new Product();
							pWrite = new ProductWrite();

							p2.setProductNo(rset2.getInt("PRODUCT_NO"));
							p2.setCategoryNo(rset2.getInt("CATEGORY_NO"));
							p2.setProductName(rset2.getString("PRODUCT_NAME"));
							p2.setProductPrice(rset2.getInt("PRODUCT_PRICE"));
							p2.setProductText(rset2.getString("PRODUCT_TEXT"));
							p2.setImgNo(rset2.getInt("IMG_NO"));

							pWrite.setProduct(p2);
						}

						String query3 = "SELECT * FROM WRITE WHERE WRITE_DELETE_YN='N' AND WRITE_NO=?";
						pstmt3 = conn.prepareStatement(query3);
						pstmt3.setInt(1, w.getWriteNo());
						rset3 = pstmt3.executeQuery();

						while (rset3.next()) {

							Write w2 = new Write();

							w2.setWriteNo(rset3.getInt("WRITE_NO"));
							w2.setMemberNo(rset3.getInt("MEMBER_NO"));
							w2.setProductNo(rset3.getInt("PRODUCT_NO"));
							w2.setWriteLike(rset3.getInt("WRITE_LIKE"));
							w2.setWriteDate(rset3.getDate("WRITE_DATE"));
							w2.setWriteExplain(rset3.getString("WRITE_EXPLAIN"));
							w2.setWriteViewCount(rset3.getInt("WRITE_VIEW_COUNT"));
							w2.setWriteDeleteDate(rset3.getDate("WRITE_DELETE_DATE"));
							w2.setWriteDeleteYN(rset3.getString("WRITE_DELETE_YN").charAt(0));

							pWrite.setWrite(w2);
						}

						String query4 = "SELECT * FROM WRITE_TAG WHERE WRITE_NO=?";
						pstmt4 = conn.prepareStatement(query4);
						pstmt4.setInt(1, w.getWriteNo());
						rset4 = pstmt4.executeQuery();

						ArrayList<WriteTag> writeTagList = new ArrayList<WriteTag>();
						while (rset4.next()) {
							WriteTag wTag = new WriteTag();

							wTag.setWriteNo(rset4.getInt("WRITE_NO"));
							wTag.setTag(rset4.getString("TAG"));

							writeTagList.add(wTag);
						}
						pWrite.setWriteTagList(writeTagList);

						String query5 = "SELECT * FROM IMG WHERE IMAGE_DELETE_YN='N' AND IMG_NO=(SELECT IMG_NO FROM PRODUCT WHERE PRODUCT_NO=?)";
						pstmt5 = conn.prepareStatement(query5);
						pstmt5.setInt(1, p.getProductNo());
						rset5 = pstmt5.executeQuery();
						while (rset5.next()) {
							Img img = new Img();

							img.setImgNo(rset5.getInt("IMG_NO"));
							img.setOriginalName(rset5.getString("ORIGINAL_NAME"));
							img.setChangedName(rset5.getString("CHANGED_NAME"));
							img.setImgPath(rset5.getString("IMG_PATH"));
							img.setUploadTime(rset5.getTimestamp("UPLOAD_TIME"));
							img.setImgDeleteYN(rset5.getString("IMAGE_DELETE_YN").charAt(0));

							pWrite.setImg(img);
						}

						list.add(pWrite);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					JDBCTemplate.close(rset5);
					JDBCTemplate.close(pstmt5);
					JDBCTemplate.close(rset4);
					JDBCTemplate.close(pstmt4);
					JDBCTemplate.close(rset3);
					JDBCTemplate.close(pstmt3);
					JDBCTemplate.close(rset2);
					JDBCTemplate.close(pstmt2);
					JDBCTemplate.close(rset1);
					JDBCTemplate.close(pstmt1);
				}

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public ArrayList<ProductAll> printIMG(Connection conn, int categoryNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<ProductAll> list = new ArrayList<ProductAll>();

		String query = "SELECT * FROM IMG LEFT JOIN PRODUCT USING (IMG_NO) LEFT JOIN PRODUCT_DATE USING (PRODUCT_NO) WHERE PRODUCT_YN='N' AND CATEGORY_NO=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, categoryNo);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Product p = new Product();
				Img img = new Img();
				ProductAll pAll = new ProductAll();

				p.setProductNo(rset.getInt("product_no"));
				p.setCategoryNo(rset.getInt("category_no"));
				p.setProductName(rset.getString("product_name"));
				p.setProductPrice(rset.getInt("product_price"));
				p.setImgNo(rset.getInt("img_no"));
				img.setChangedName(rset.getString("changed_name"));
				img.setImgPath(rset.getString("img_path"));
				pAll.setProduct(p);
				pAll.setImg(img);

				list.add(pAll);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;

	}

	public ArrayList<ProductAll> printIMG(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM PRODUCT_DATE LEFT JOIN PRODUCT USING (PRODUCT_NO) LEFT JOIN IMG USING (IMG_NO) WHERE PRODUCT_YN='N'";

		ArrayList<ProductAll> list = new ArrayList<ProductAll>();
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Product p = new Product();
				Img img = new Img();
				ProductAll pAll = new ProductAll();

				p.setProductNo(rset.getInt("product_no"));
				p.setCategoryNo(rset.getInt("category_no"));
				p.setProductName(rset.getString("product_name"));
				p.setProductPrice(rset.getInt("product_price"));
				p.setImgNo(rset.getInt("img_no"));
				img.setChangedName(rset.getString("changed_name"));
				img.setImgPath(rset.getString("img_path"));
				pAll.setProduct(p);
				pAll.setImg(img);

				list.add(pAll);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;

	}

	public ArrayList<ProductAll> productAlign(Connection conn, int categoryNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from product_date left join product using (product_no) left join img using (img_no) where PRODUCT_YN='N' and category_no =? order by PRODUCT_PRICE desc";

		ArrayList<ProductAll> list = new ArrayList<ProductAll>();
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, categoryNo);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Product p = new Product();
				Img img = new Img();
				ProductAll pAll = new ProductAll();

				p.setProductNo(rset.getInt("product_no"));
				p.setCategoryNo(rset.getInt("category_no"));
				p.setProductName(rset.getString("product_name"));
				p.setProductPrice(rset.getInt("product_price"));
				p.setImgNo(rset.getInt("img_no"));
				img.setChangedName(rset.getString("changed_name"));
				img.setImgPath(rset.getString("img_path"));
				pAll.setProduct(p);
				pAll.setImg(img);

				list.add(pAll);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;

	}

	public ArrayList<ProductAll> productAlignPrice(Connection conn, int categoryNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from product_date left join product using (product_no) left join img using (img_no) where PRODUCT_YN='N' and category_no =? order by PRODUCT_PRICE";

		ArrayList<ProductAll> list = new ArrayList<ProductAll>();
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, categoryNo);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Product p = new Product();
				Img img = new Img();
				ProductAll pAll = new ProductAll();

				p.setProductNo(rset.getInt("product_no"));
				p.setCategoryNo(rset.getInt("category_no"));
				p.setProductName(rset.getString("product_name"));
				p.setProductPrice(rset.getInt("product_price"));
				p.setImgNo(rset.getInt("img_no"));
				img.setChangedName(rset.getString("changed_name"));
				img.setImgPath(rset.getString("img_path"));
				pAll.setProduct(p);
				pAll.setImg(img);

				list.add(pAll);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public ArrayList<ProductAll> productAlignNew(Connection conn, int categoryNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from product_date left join product using (product_no) left join img using (img_no) where category_no =? and PRODUCT_YN='N' order by product_enroll_date desc";

		ArrayList<ProductAll> list = new ArrayList<ProductAll>();
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, categoryNo);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Product p = new Product();
				Img img = new Img();
				ProductAll pAll = new ProductAll();

				p.setProductNo(rset.getInt("product_no"));
				p.setCategoryNo(rset.getInt("category_no"));
				p.setProductName(rset.getString("product_name"));
				p.setProductPrice(rset.getInt("product_price"));
				p.setImgNo(rset.getInt("img_no"));
				img.setChangedName(rset.getString("changed_name"));
				img.setImgPath(rset.getString("img_path"));
				pAll.setProduct(p);
				pAll.setImg(img);

				list.add(pAll);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public ArrayList<ProductAll> productAlignPopular(Connection conn, int categoryNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "SELECT * FROM PRODUCT INNER JOIN (SELECT * FROM (SELECT PRODUCT_NO "
				+ "FROM PRODUCT LEFT JOIN PRODUCT_DATE USING (PRODUCT_NO) LEFT JOIN ORDERS USING (PRODUCT_NO) "
				+ "WHERE PRODUCT_YN='N' and category_no =? GROUP BY PRODUCT_NO "
				+ "ORDER BY SUM(ORDERS_NUM) DESC)) USING (PRODUCT_NO) INNER JOIN IMG USING (IMG_NO)";

		ArrayList<ProductAll> list = new ArrayList<ProductAll>();
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, categoryNo);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				Product p = new Product();
				Img img = new Img();
				ProductAll pAll = new ProductAll();

				p.setProductNo(rset.getInt("product_no"));
				p.setCategoryNo(rset.getInt("category_no"));
				p.setProductName(rset.getString("product_name"));
				p.setProductPrice(rset.getInt("product_price"));
				p.setImgNo(rset.getInt("img_no"));
				img.setChangedName(rset.getString("changed_name"));
				img.setImgPath(rset.getString("img_path"));
				pAll.setProduct(p);
				pAll.setImg(img);

				list.add(pAll);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

public ProductAllSub selectOneProductStoreMain(Connection conn, int productNo) {
		PreparedStatement pstmt = null;		
		ResultSet rset = null;		
		ProductAllSub p = null;
		
		String query = "SELECT * FROM PRODUCT "
				+ "LEFT JOIN CATEGORY USING (category_NO) LEFT JOIN PRODUCT_DATE USING (PRODUCT_NO) "
				+ "LEFT JOIN PRODUCT_OPTION USING (PRODUCT_NO) "
				+ "LEFT JOIN IMG USING (IMG_NO) "				
				+ "WHERE PRODUCT_YN='N' and IMAGE_DELETE_YN='N' and product_no=? ";
					
				
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, productNo); 
			rset = pstmt.executeQuery();
													
			
			if (rset.next() == true) {
								
			  Category category = new Category();
			  category.setCategoryNo(rset.getInt("Category_No"));
			  category.setCategoryName(rset.getString("Category_Name"));
				
			  Product product = new Product();
			  product.setProductNo(rset.getInt("Product_No"));
			  product.setCategoryNo(rset.getInt("Category_No"));
			  product.setProductName(rset.getString("Product_Name"));
			  product.setProductPrice(rset.getInt("Product_Price"));
			  product.setProductText(rset.getString("Product_Text"));
			  product.setImgNo(rset.getInt("Img_No"));
			  
			  ProductDate pDate = new ProductDate();
			  pDate.setProductNo(rset.getInt("Product_No"));
			  pDate.setProductEnrollDate(rset.getDate("Product_Enroll_Date"));
			  pDate.setProductDeleteDate(rset.getDate("Product_Delete_Date"));			  		
			  
			  ProductOption pOption = new ProductOption();
			  pOption.setProductNo(rset.getInt("Product_No"));
			  pOption.setOptions(rset.getString("Options"));			  
			  
			  Img img = new Img(); 
			  img.setImgNo(rset.getInt("Img_No"));
			  img.setOriginalName(rset.getString("Original_Name"));
			  img.setChangedName(rset.getString("Changed_Name"));
			  img.setImgPath(rset.getString("Img_Path"));
			  img.setUploadTime(rset.getTimestamp("Upload_Time"));			  
			  
			  p = new ProductAllSub(); 
			  p.setCategory(category);
			  p.setProduct(product); 
			  p.setpDate(pDate);				
			  p.setpOption(pOption);
			  p.setImg(img);
			  		  	
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);			
			JDBCTemplate.close(pstmt);
			
		}
		return p;
	}

	public ProductAllSub selectOneProductStoreSub(Connection conn, int productNo) {
		PreparedStatement pstmt = null;		
		ResultSet rset = null;		
		ProductAllSub p = null;
		
		String query = "select * from PRODUCT_DETAIL "
				+ "LEFT JOIN PRODUCT_DATE USING (PRODUCT_NO) "
				+ "LEFT JOIN IMG USING (IMG_NO) "
				+ "WHERE PRODUCT_YN='N' and IMAGE_DELETE_YN='N'and product_no=?";
							
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, productNo); 
			rset = pstmt.executeQuery();
													
			
			if (rset.next() == true) {								
				  ProductDetail pDetail = new ProductDetail();
				  pDetail.setProductNo(rset.getInt("Product_No"));
				  pDetail.setProductDescription(rset.getString("Product_Description")); 
				  pDetail.setImgNo(rset.getInt("Img_No"));
				  								  
				  Img img_sub = new Img(); 
				  img_sub.setImgNo(rset.getInt("Img_No"));
				  img_sub.setOriginalName(rset.getString("Original_Name"));
				  img_sub.setChangedName(rset.getString("Changed_Name"));
				  img_sub.setImgPath(rset.getString("Img_Path"));
				  img_sub.setUploadTime(rset.getTimestamp("Upload_Time"));
				  			 			  
				  p = new ProductAllSub(); 
				  p.setpDetail(pDetail); 			 
				  p.setImg_sub(img_sub);
			  		  	
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);			
			JDBCTemplate.close(pstmt);
			
		}
		return p;
	}

	
	public ArrayList<ProductWrite> selectpncList2(Connection conn, int productNo) {
			  PreparedStatement pstmt = null;   
		      PreparedStatement pstmt1 = null;   
		      ResultSet rset = null;   
		      ResultSet rset1 = null;   
		      
		    
		      ArrayList<ProductWrite> list = new ArrayList<ProductWrite>();
		      ProductWrite pAll= new ProductWrite();  
		      String query = "select product_no,write_no,write_Explain from write "
		            + "left join "
		            + "(product  LEFT JOIN PRODUCT_DATE USING (PRODUCT_NO)) "
		            + "uSING(PRODUCT_NO) "
		            + "where WRITE_NO in (SELECT WRITE_NO FROM PRODUCT "
		            + "LEFT JOIN WRITE USING(PRODUCT_NO)) "
		            + "and product_no = ? and write_delete_YN ='N' and product_YN='N'";
		                        
		         try {
		            pstmt = conn.prepareStatement(query);
		            pstmt.setInt(1, productNo); 
		            rset = pstmt.executeQuery();
		            
	            
		            while(rset.next()) {   
		               Write write = new Write();
	               	                 
		               write.setProductNo(rset.getInt("product_no"));
		               write.setWriteNo(rset.getInt("write_no"));
		               write.setWriteExplain(rset.getString("write_Explain"));
		               
		               pAll.setWrite(write);
		               
		               String query1 = "select Img_No,IMG_PATH,Changed_Name from write "
			                     + "left join WRITE_IMAGE using (write_no) "
			                     + "left join img using (img_no) "
			                     + "where WRITE_NO in (SELECT WRITE_NO FROM PRODUCT "
			                     + "LEFT JOIN WRITE USING(PRODUCT_NO)) "
			                     + "and product_no = ? and write_delete_YN ='N' and image_delete_yn='N'";
		               		                           
		               pstmt1 = conn.prepareStatement(query1);
		               pstmt1.setInt(1, productNo);
		               rset1 = pstmt1.executeQuery();
		               	               		               
		               while(rset1.next()) {   
		            	  Img img = new Img();
		            	  img.setImgNo(rset1.getInt("Img_No"));
		                  img.setImgPath(rset1.getString("IMG_PATH"));
		                  img.setChangedName(rset1.getString("Changed_Name"));
		      
		                  pAll.setImg(img);		               
		               } 
                
		               list.add(pAll);               
		            }                        
		            
		         } catch (SQLException e) {
		            // TODO Auto-generated catch block
		            e.printStackTrace();
		         }         
		   
		      return list;

	}
	
}
