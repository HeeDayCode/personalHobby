package kr.or.iei.orders.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.or.iei.common.JDBCTemplate;
import kr.or.iei.img.model.vo.Img;
import kr.or.iei.member.model.vo.Member;
import kr.or.iei.orders.model.vo.OrderAll;
import kr.or.iei.orders.model.vo.Orders;
import kr.or.iei.product.model.vo.Product;
import kr.or.iei.product.model.vo.ProductAllSub;

public class OrderDAO {

	public ArrayList<OrderAll> selectAllOrderPage(Connection conn, int currentPage, int recordCountPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PreparedStatement pstmt1 = null;
		ResultSet rset1 = null;

		ArrayList<OrderAll> list = null;
		OrderAll oAll = null;
		int start = recordCountPerPage * (currentPage - 1) + 1;
		int end = recordCountPerPage * currentPage;
		String query = "select orders_no from "
				+ "(select row_number() over(order by orders_no desc) as row_num,orders.* "
				+ "from orders) where row_num between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			list = new ArrayList<OrderAll>();
			while (rset.next()) {
				Orders o0 = new Orders();
				o0.setOrdersNo(rset.getInt("Orders_No"));
				String query1 = "select * from orders left join member using (member_no) "
						+ "left join product using (product_no) where orders_no=?";
				pstmt1 = conn.prepareStatement(query1);
				pstmt1.setInt(1, o0.getOrdersNo());
				rset1 = pstmt1.executeQuery();

				oAll = new OrderAll();
				while (rset1.next()) {
					Product p = new Product();
					Member m = new Member();
					Orders o = new Orders();

					p.setProductNo(rset1.getInt("Product_No"));
					p.setCategoryNo(rset1.getInt("Category_No"));
					p.setProductName(rset1.getString("Product_Name"));
					p.setProductPrice(rset1.getInt("Product_Price"));
					p.setProductText(rset1.getString("Product_Text"));
					p.setImgNo(rset1.getInt("Img_No"));

					m.setMemberNo(rset1.getInt("member_No"));
					m.setMemberId(rset1.getString("member_Id"));
					m.setMemberPw(rset1.getString("member_Pw"));
					m.setMemberName(rset1.getString("member_Name"));
					m.setMemberNickname(rset1.getString("member_Nickname"));
					m.setMemberEmail(rset1.getString("member_Email"));
					m.setMemberPhone(rset1.getString("member_Phone"));

					o.setOrdersNo(rset1.getInt("orders_No"));
					o.setMemberNo(rset1.getInt("member_No"));
					o.setProductNo(rset1.getInt("product_No"));
					o.setOrdersDate(rset1.getDate("orders_date"));
					o.setOrdersNum(rset1.getInt("orders_num"));
					o.setOrdersPay(rset1.getString("orders_pay").charAt(0));
					o.setDeliveryYN(rset1.getString("delivery_yn").charAt(0));
					o.setDemand(rset1.getString("demand"));
					o.setReceiverName(rset1.getString("receiver_name"));
					o.setReceiverPhone(rset1.getString("receiver_phone"));
					o.setReceiverAddress(rset1.getString("receiver_address"));
					o.setReceiveYN(rset1.getString("receive_yn").charAt(0));
					oAll.setM(m);
					oAll.setP(p);
					oAll.setO(o);

				}
				list.add(oAll);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset1);
			JDBCTemplate.close(pstmt1);
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return list;
	}

	public String getPageNavi(Connection conn, int currentPage, int recordCountPerPage, int naviCountPerPage) {
		// 현재 페이지 값, 한페이지당 보여줄 글 수, 페이지 인덱스 번호
		int postTotalCount = orderTotalCount(conn); // 전체 게시물 개수
		// 게시물개수/한페이지당 글 수 -> 나머지 있으면 몫+1, 나머지 없으면 몫
		int pageTotalCount; // 전체 페이지 인덱스 수
		if (postTotalCount % recordCountPerPage > 0) {
			pageTotalCount = postTotalCount / recordCountPerPage + 1;
		} else {
			pageTotalCount = postTotalCount / recordCountPerPage;
		}
		// pageNavi 는 현재 페이지 인덱스
		// startNavi=((현재페이지-1)/보여질 navi 개수)*보여질navi개수+1;
		int startNavi = ((currentPage - 1) / naviCountPerPage) * naviCountPerPage + 1;
		int endNavi = startNavi + naviCountPerPage - 1;
		if (endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}
		StringBuilder sb = new StringBuilder(); // 태그를 String 형태로 넘겨주기 위해
		if (startNavi != 1) {// 왼쪽 화살표
			sb.append("<a href='/orderAll.kh?currentPage=" + (startNavi - 1) + "'><</a> ");
		}
		for (int i = startNavi; i <= endNavi; i++) {
			if (i == currentPage) { // 현재페이지에 <b> 추가
				sb.append("<a href='/orderAll.kh?currentPage=" + i + "'><b>" + i + "</b></a> ");

			} else {
				sb.append("<a href='/orderAll.kh?currentPage=" + i + "'>" + i + "</a> ");
			}
		}
		if (endNavi != pageTotalCount) {// 오른쪽 화살표
			sb.append("<a href='/orderAll.kh?currentPage=" + (endNavi + 1) + "'>></a> ");
		}
		// System.out.println(sb); //a태그 합친 코드
		return sb.toString();
	}

	public int orderTotalCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int postTotalCount = 0;
		String query = "select count(*) as TOTALCOUNT from(select orders_no from "
				+ "(select row_number() over(order by orders_no desc) as row_num,orders.* " + "from orders))";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			rset.next(); // 컬럼이 없으면 0
			postTotalCount = rset.getInt("TOTALCOUNT"); // AS 뒤의 별칭 값
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return postTotalCount;
	}

	public int CommentsDelete(Connection conn, int orderNo) {
		PreparedStatement pstmt = null;
		String query = "UPDATE orders SET delivery_yn='Y' where orders_no=?";

		int result = 0;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, orderNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<OrderAll> searchOrder(Connection conn, int currentPage, int recordcountPerPage, String opt,
			String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PreparedStatement pstmt1 = null;
		ResultSet rset1 = null;

		ArrayList<OrderAll> list = null;
		OrderAll oAll = null;
		int start = recordcountPerPage * (currentPage - 1) + 1;
		int end = recordcountPerPage * currentPage;
		String query = "WITH EX AS ( SELECT orders_no from orders left join member using (member_no) "
				+ "left join product using (product_no) " + "WHERE " + opt + " like ?) SELECT NUM,orders_no "
				+ "FROM (SELECT EX.*, ROW_NUMBER() OVER(ORDER BY EX.orders_no DESC) AS NUM FROM EX) where num between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			list = new ArrayList<OrderAll>();
			while (rset.next()) {
				Orders o0 = new Orders();
				o0.setOrdersNo(rset.getInt("Orders_No"));
				try {
					String query1 = "select * from orders left join member using (member_no) "
							+ "left join product using (product_no) where orders_no=?";
					pstmt1 = conn.prepareStatement(query1);
					pstmt1.setInt(1, o0.getOrdersNo());
					rset1 = pstmt1.executeQuery();

					oAll = new OrderAll();
					while (rset1.next()) {
						Product p = new Product();
						Member m = new Member();
						Orders o = new Orders();

						p.setProductNo(rset1.getInt("Product_No"));
						p.setCategoryNo(rset1.getInt("Category_No"));
						p.setProductName(rset1.getString("Product_Name"));
						p.setProductPrice(rset1.getInt("Product_Price"));
						p.setProductText(rset1.getString("Product_Text"));
						p.setImgNo(rset1.getInt("Img_No"));

						m.setMemberNo(rset1.getInt("member_No"));
						m.setMemberId(rset1.getString("member_Id"));
						m.setMemberPw(rset1.getString("member_Pw"));
						m.setMemberName(rset1.getString("member_Name"));
						m.setMemberNickname(rset1.getString("member_Nickname"));
						m.setMemberEmail(rset1.getString("member_Email"));
						m.setMemberPhone(rset1.getString("member_Phone"));

						o.setOrdersNo(rset1.getInt("orders_No"));
						o.setMemberNo(rset1.getInt("member_No"));
						o.setProductNo(rset1.getInt("product_No"));
						o.setOrdersDate(rset1.getDate("orders_date"));
						o.setOrdersNum(rset1.getInt("orders_num"));
						o.setOrdersPay(rset1.getString("orders_pay").charAt(0));
						o.setDeliveryYN(rset1.getString("delivery_yn").charAt(0));
						o.setDemand(rset1.getString("demand"));
						o.setReceiverName(rset1.getString("receiver_name"));
						o.setReceiverPhone(rset1.getString("receiver_phone"));
						o.setReceiverAddress(rset1.getString("receiver_address"));
						o.setReceiveYN(rset1.getString("receive_yn").charAt(0));
						oAll.setM(m);
						oAll.setP(p);
						oAll.setO(o);

					}
					list.add(oAll);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
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

	public String getPageNavi(Connection conn, int currentPage, int recordcountPerPage, int naviCountPerPage,
			String opt, String keyword) {
		// 현재 페이지 값, 한페이지당 보여줄 글 수, 페이지 인덱스 번호
		int postTotalCount = orderSearchTotalCount(conn, opt, keyword); // 전체 게시물 개수
		// 게시물개수/한페이지당 글 수 -> 나머지 있으면 몫+1, 나머지 없으면 몫
		int pageTotalCount; // 전체 페이지 인덱스 수
		if (postTotalCount % recordcountPerPage > 0) {
			pageTotalCount = postTotalCount / recordcountPerPage + 1;
		} else {
			pageTotalCount = postTotalCount / recordcountPerPage;
		}
		// pageNavi 는 현재 페이지 인덱스
		// startNavi=((현재페이지-1)/보여질 navi 개수)*보여질navi개수+1;
		int startNavi = ((currentPage - 1) / naviCountPerPage) * naviCountPerPage + 1;
		int endNavi = startNavi + naviCountPerPage - 1;
		if (endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}
		StringBuilder sb = new StringBuilder(); // 태그를 String 형태로 넘겨주기 위해
		if (startNavi != 1) {// 왼쪽 화살표
			sb.append("<a href='/orderSearch.kh?" + opt + "=" + keyword + "&currentPage=" + (startNavi - 1) + "'><</a> ");
		}
		for (int i = startNavi; i <= endNavi; i++) {
			if (i == currentPage) { // 현재페이지에 <b> 추가
				sb.append("<a href='/orderSearch.kh?" + opt + "=" + keyword + "&currentPage=" + i + "'><b>" + i
						+ "</b></a> ");

			} else {
				sb.append("<a href='/orderSearch.kh?" + opt + "=" + keyword + "&currentPage=" + i + "'>" + i + "</a> ");
			}
		}
		if (endNavi != pageTotalCount) {// 오른쪽 화살표
			sb.append("<a href='/orderSearch.kh?" + opt + "=" + keyword + "&currentPage=" + (endNavi + 1) + "'>></a> ");
		}
		// System.out.println(sb); //a태그 합친 코드
		return sb.toString();
	}

	public int orderSearchTotalCount(Connection conn, String opt, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int postTotalCount = 0;
		String query = "select count(*) as TOTALCOUNT from(WITH EX AS ( SELECT orders_no from orders left join member using (member_no) "
				+ "left join product using (product_no) " + "WHERE " + opt + " like ?) SELECT NUM,orders_no "
				+ "FROM (SELECT EX.*, ROW_NUMBER() OVER(ORDER BY EX.orders_no DESC) AS NUM FROM EX))";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + keyword + "%");
			rset = pstmt.executeQuery();
			rset.next(); // 컬럼이 없으면 0
			postTotalCount = rset.getInt("TOTALCOUNT"); // AS 뒤의 별칭 값
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return postTotalCount;
	}
public ProductAllSub selectOneProduct(Connection conn, int productNo) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		
		ProductAllSub productAS=null;
		
		String query="SELECT * FROM PRODUCT LEFT JOIN PRODUCT_DATE USING (PRODUCT_NO) LEFT JOIN IMG USING (IMG_NO) WHERE PRODUCT_YN='N' AND PRODUCT_NO=?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, productNo);
			rset=pstmt.executeQuery();
			
			if(rset.next()) {
				Product product=new Product();
				
				product.setProductNo(rset.getInt("PRODUCT_NO"));
				product.setCategoryNo(rset.getInt("CATEGORY_NO"));
				product.setProductName(rset.getString("PRODUCT_NAME"));
				product.setProductPrice(rset.getInt("PRODUCT_PRICE"));
				product.setProductText(rset.getString("PRODUCT_TEXT"));
				product.setImgNo(rset.getInt("IMG_NO"));
				
				Img img=new Img();
				
				img.setImgNo(rset.getInt("IMG_NO"));
				img.setOriginalName(rset.getString("ORIGINAL_NAME"));
				img.setChangedName(rset.getString("CHANGED_NAME"));
				img.setImgPath(rset.getString("IMG_PATH"));
				img.setUploadTime(rset.getTimestamp("UPLOAD_TIME"));
				
				productAS=new ProductAllSub();
				productAS.setProduct(product);
				productAS.setImg(img);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return productAS;
	}

	public int insertOrder(Connection conn, Orders o) {
		PreparedStatement pstmt=null;
		int result=0;
		
		String query="INSERT INTO ORDERS VALUES(ORDERS_NO_SEQ.NEXTVAL, ?, ?, SYSDATE, ?, 'Y', 'N', ?, ?, ?, ?,'N')";
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, o.getMemberNo());
			pstmt.setInt(2, o.getProductNo());
			pstmt.setInt(3, o.getOrdersNum());
			pstmt.setString(4, o.getDemand());
			pstmt.setString(5, o.getReceiverName());
			pstmt.setString(6, o.getReceiverPhone());
			pstmt.setString(7, o.getReceiverAddress());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
}
