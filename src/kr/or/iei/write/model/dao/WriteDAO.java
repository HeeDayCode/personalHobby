package kr.or.iei.write.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.or.iei.common.JDBCTemplate;
import kr.or.iei.img.model.vo.Img;
import kr.or.iei.member.model.vo.Member;
import kr.or.iei.product.model.vo.Category;
import kr.or.iei.product.model.vo.Product;
import kr.or.iei.product.model.vo.ProductAll;
import kr.or.iei.write.model.vo.CommentsAll;
import kr.or.iei.write.model.vo.Write;
import kr.or.iei.write.model.vo.WriteAll;
import kr.or.iei.write.model.vo.WriteImage;
import kr.or.iei.write.model.vo.WriteListAll;
import kr.or.iei.write.model.vo.WriteTag;

public class WriteDAO {

	public ArrayList<WriteAll> indexRandom(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PreparedStatement pstmt1 = null;
		ResultSet rset1 = null;
		Write write = null;
		String query = "select * from (SELECT * FROM (SELECT * FROM WRITE ORDER BY dbms_random.value) "
				+ "WHERE ROWNUM<=27) left join write_image using(write_no) where write_delete_yn='N'";
		ArrayList<WriteAll> list = new ArrayList<WriteAll>();
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				write = new Write();
				write.setWriteNo(rset.getInt("Write_No"));

				String query1 = "select * from write_image left join img using (img_no) "
						+ "left join write using(write_no) where write_no=? and img_no is not null and write_explain is not null";
				pstmt1 = conn.prepareStatement(query1);
				pstmt1.setInt(1, write.getWriteNo());
				rset1 = pstmt1.executeQuery();

				Write w = null;
				Img img = null;
				WriteAll wAll = null;

				while (rset1.next()) {
					w = new Write();
					img = new Img();
					wAll = new WriteAll();

					w.setWriteNo(rset1.getInt("WRITE_NO"));
					w.setMemberNo(rset1.getInt("MEMBER_NO"));
					w.setProductNo(rset1.getInt("PRODUCT_NO"));
					w.setWriteLike(rset1.getInt("WRITE_LIKE"));
					w.setWriteDate(rset1.getDate("WRITE_DATE"));
					w.setWriteExplain(rset1.getString("WRITE_EXPLAIN"));
					w.setWriteViewCount(rset1.getInt("WRITE_VIEW_COUNT"));
					w.setWriteDeleteDate(rset1.getDate("WRITE_DELETE_DATE"));
					w.setWriteDeleteYN(rset1.getString("WRITE_DELETE_YN").charAt(0));

					wAll.setWrite(w);

					img.setImgNo(rset1.getInt("IMG_NO"));
					img.setOriginalName(rset1.getString("ORIGINAL_NAME"));
					img.setChangedName(rset1.getString("CHANGED_NAME"));
					img.setImgPath(rset1.getString("IMG_PATH"));
					img.setUploadTime(rset1.getTimestamp("UPLOAD_TIME"));
					img.setImgDeleteYN(rset1.getString("IMAGE_DELETE_YN").charAt(0));

					wAll.setImg(img);
					list.add(wAll);
				}

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

	public ArrayList<WriteAll> indexBest(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PreparedStatement pstmt1 = null;
		ResultSet rset1 = null;
		Write write = null;
		String query = "select write_no from (select * from write left join write_image using(write_no) "
				+ "where write_delete_yn='N' order by write_like desc) where rownum<=27";
		ArrayList<WriteAll> list = new ArrayList<WriteAll>();
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				write = new Write();
				write.setWriteNo(rset.getInt("Write_No"));

				String query1 = "select * from write_image left join img using (img_no) "
						+ "left join write using(write_no) where write_no=? and img_no is not null and write_explain is not null";
				pstmt1 = conn.prepareStatement(query1);
				pstmt1.setInt(1, write.getWriteNo());
				rset1 = pstmt1.executeQuery();

				Write w = null;
				Img img = null;
				WriteAll wAll = null;

				while (rset1.next()) {
					w = new Write();
					img = new Img();
					wAll = new WriteAll();

					w.setWriteNo(rset1.getInt("WRITE_NO"));
					w.setMemberNo(rset1.getInt("MEMBER_NO"));
					w.setProductNo(rset1.getInt("PRODUCT_NO"));
					w.setWriteLike(rset1.getInt("WRITE_LIKE"));
					w.setWriteDate(rset1.getDate("WRITE_DATE"));
					w.setWriteExplain(rset1.getString("WRITE_EXPLAIN"));
					w.setWriteViewCount(rset1.getInt("WRITE_VIEW_COUNT"));
					w.setWriteDeleteDate(rset1.getDate("WRITE_DELETE_DATE"));
					w.setWriteDeleteYN(rset1.getString("WRITE_DELETE_YN").charAt(0));

					wAll.setWrite(w);

					img.setImgNo(rset1.getInt("IMG_NO"));
					img.setOriginalName(rset1.getString("ORIGINAL_NAME"));
					img.setChangedName(rset1.getString("CHANGED_NAME"));
					img.setImgPath(rset1.getString("IMG_PATH"));
					img.setUploadTime(rset1.getTimestamp("UPLOAD_TIME"));
					img.setImgDeleteYN(rset1.getString("IMAGE_DELETE_YN").charAt(0));

					wAll.setImg(img);
					list.add(wAll);
				}

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

	public ArrayList<WriteAll> indexNew(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PreparedStatement pstmt1 = null;
		ResultSet rset1 = null;
		Write write = null;
		String query = "select write_no from (select * from write left join write_image using(write_no) "
				+ "where write_delete_yn='N' order by write_date asc) where rownum<=27";
		//String query="select write_no from (select * from write left join write_image using(write_no) " + 
		//		"where write_delete_yn='N' order by write_date desc) where write_no between 21 and 47";
		ArrayList<WriteAll> list = new ArrayList<WriteAll>();
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				write = new Write();
				write.setWriteNo(rset.getInt("Write_No"));

				String query1 = "select * from write_image left join img using (img_no) "
						+ "left join write using(write_no) where write_no=? and img_no is not null and write_explain is not null";
				pstmt1 = conn.prepareStatement(query1);
				pstmt1.setInt(1, write.getWriteNo());
				rset1 = pstmt1.executeQuery();

				Write w = null;
				Img img = null;
				WriteAll wAll = null;

				while (rset1.next()) {
					w = new Write();
					img = new Img();
					wAll = new WriteAll();

					w.setWriteNo(rset1.getInt("WRITE_NO"));
					w.setMemberNo(rset1.getInt("MEMBER_NO"));
					w.setProductNo(rset1.getInt("PRODUCT_NO"));
					w.setWriteLike(rset1.getInt("WRITE_LIKE"));
					w.setWriteDate(rset1.getDate("WRITE_DATE"));
					w.setWriteExplain(rset1.getString("WRITE_EXPLAIN"));
					w.setWriteViewCount(rset1.getInt("WRITE_VIEW_COUNT"));
					w.setWriteDeleteDate(rset1.getDate("WRITE_DELETE_DATE"));
					w.setWriteDeleteYN(rset1.getString("WRITE_DELETE_YN").charAt(0));

					wAll.setWrite(w);

					img.setImgNo(rset1.getInt("IMG_NO"));
					img.setOriginalName(rset1.getString("ORIGINAL_NAME"));
					img.setChangedName(rset1.getString("CHANGED_NAME"));
					img.setImgPath(rset1.getString("IMG_PATH"));
					img.setUploadTime(rset1.getTimestamp("UPLOAD_TIME"));
					img.setImgDeleteYN(rset1.getString("IMAGE_DELETE_YN").charAt(0));

					wAll.setImg(img);
					list.add(wAll);
				}

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

	public ArrayList<WriteListAll> selectAllWritePage(Connection conn, int currentPage, int recordCountPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PreparedStatement pstmt1 = null;
		ResultSet rset1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rset2 = null;

		ArrayList<WriteListAll> list = null;
		WriteListAll writeListAll = null;
		int start = recordCountPerPage * (currentPage - 1) + 1;
		int end = recordCountPerPage * currentPage;
		String query = "select write_no from (select row_number() over(order by write_no desc) as row_num,write.* "
				+ "from write where write_delete_yn='N') where row_num between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			list = new ArrayList<WriteListAll>();
			while (rset.next()) {
				Write w = new Write();
				w.setWriteNo(rset.getInt("Write_No"));
				String query1 = "select * from write left join member using(member_no) left join product using(product_no) where write_no=?";
				pstmt1 = conn.prepareStatement(query1);
				pstmt1.setInt(1, w.getWriteNo());
				rset1 = pstmt1.executeQuery();
				writeListAll = new WriteListAll();

				while (rset1.next()) {
					Product p = new Product();
					Write w2 = new Write();
					Member m = new Member();
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

					w2.setWriteNo(rset1.getInt("WRITE_NO"));
					w2.setMemberNo(rset1.getInt("MEMBER_NO"));
					w2.setProductNo(rset1.getInt("PRODUCT_NO"));
					w2.setWriteLike(rset1.getInt("WRITE_LIKE"));
					w2.setWriteDate(rset1.getDate("WRITE_DATE"));
					w2.setWriteExplain(rset1.getString("WRITE_EXPLAIN"));
					w2.setWriteViewCount(rset1.getInt("WRITE_VIEW_COUNT"));
					w2.setWriteDeleteDate(rset1.getDate("WRITE_DELETE_DATE"));
					w2.setWriteDeleteYN(rset1.getString("WRITE_DELETE_YN").charAt(0));

					writeListAll.setProduct(p);
					writeListAll.setMember(m);
					writeListAll.setWrite(w2);

				}
				String query2 = "select * from write_tag left join write using (write_no) where write_no=? order by tag";
				pstmt2 = conn.prepareStatement(query2);
				pstmt2.setInt(1, w.getWriteNo());
				rset2 = pstmt2.executeQuery();

				ArrayList<WriteTag> writeTagList = new ArrayList<WriteTag>();
				while (rset2.next()) {
					WriteTag wTag = new WriteTag();

					wTag.setWriteNo(rset2.getInt("WRITE_NO"));
					wTag.setTag(rset2.getString("TAG"));

					writeTagList.add(wTag);

				}
				writeListAll.setWriteTag(writeTagList);
				list.add(writeListAll);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset2);
			JDBCTemplate.close(pstmt2);
			JDBCTemplate.close(rset1);
			JDBCTemplate.close(pstmt1);
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return list;
	}

	public String getPageNavi(Connection conn, int currentPage, int recordCountPerPage, int naviCountPerPage) {
		// 현재 페이지 값, 한페이지당 보여줄 글 수, 페이지 인덱스 번호
		int postTotalCount = postTotalCount(conn); // 전체 게시물 개수
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
			sb.append("<a href='/writeAllList.kh?currentPage=" + (startNavi - 1) + "'><</a> ");
		}
		for (int i = startNavi; i <= endNavi; i++) {
			if (i == currentPage) { // 현재페이지에 <b> 추가
				sb.append("<a href='/writeAllList.kh?currentPage=" + i + "'><b>" + i + "</b></a> ");

			} else {
				sb.append("<a href='/writeAllList.kh?currentPage=" + i + "'>" + i + "</a> ");
			}
		}
		if (endNavi != pageTotalCount) {// 오른쪽 화살표
			sb.append("<a href='/writeAllList.kh?currentPage=" + (endNavi + 1) + "'>></a> ");
		}
		// System.out.println(sb); //a태그 합친 코드
		return sb.toString();
	}

	public int postTotalCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int postTotalCount = 0;
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM write WHERE write_delete_YN='N'";
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

	public ArrayList<WriteListAll> searchOneWritePageTag(Connection conn, int currentPage, int recordcountPerPage,
			String tagOpt, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PreparedStatement pstmt1 = null;
		ResultSet rset1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rset2 = null;

		ArrayList<WriteListAll> list = null;
		WriteListAll writeListAll = null;
		int start = recordcountPerPage * (currentPage - 1) + 1;
		int end = recordcountPerPage * currentPage;
		String query = "WITH EX AS ( SELECT distinct WRITE_NO " + "from write left join write_tag using (write_no) "
				+ "WHERE tag like ?) " + "SELECT NUM,WRITE_NO "
				+ "FROM (SELECT EX.*, ROW_NUMBER() OVER(ORDER BY EX.WRITE_NO DESC) AS NUM FROM EX) "
				+ "where NUM between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			list = new ArrayList<WriteListAll>();

			while (rset.next()) {
				Write w = new Write();
				w.setWriteNo(rset.getInt("Write_No"));

				try {
					String query1 = "select * from write left join member using(member_no) left join product using(product_no) where write_no=?";
					pstmt1 = conn.prepareStatement(query1);
					pstmt1.setInt(1, w.getWriteNo());
					rset1 = pstmt1.executeQuery();
					writeListAll = new WriteListAll();

					while (rset1.next()) {
						Product p = new Product();
						Write w2 = new Write();
						Member m = new Member();
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

						w2.setWriteNo(rset1.getInt("WRITE_NO"));
						w2.setMemberNo(rset1.getInt("MEMBER_NO"));
						w2.setProductNo(rset1.getInt("PRODUCT_NO"));
						w2.setWriteLike(rset1.getInt("WRITE_LIKE"));
						w2.setWriteDate(rset1.getDate("WRITE_DATE"));
						w2.setWriteExplain(rset1.getString("WRITE_EXPLAIN"));
						w2.setWriteViewCount(rset1.getInt("WRITE_VIEW_COUNT"));
						w2.setWriteDeleteDate(rset1.getDate("WRITE_DELETE_DATE"));
						w2.setWriteDeleteYN(rset1.getString("WRITE_DELETE_YN").charAt(0));

						writeListAll.setProduct(p);
						writeListAll.setMember(m);
						writeListAll.setWrite(w2);

					}
					String query2 = "select * from write_tag left join write using (write_no) where write_no=? order by tag";
					pstmt2 = conn.prepareStatement(query2);
					pstmt2.setInt(1, w.getWriteNo());
					rset2 = pstmt2.executeQuery();

					ArrayList<WriteTag> writeTagList = new ArrayList<WriteTag>();
					while (rset2.next()) {
						WriteTag wTag = new WriteTag();

						wTag.setWriteNo(rset2.getInt("WRITE_NO"));
						wTag.setTag(rset2.getString("TAG"));

						writeTagList.add(wTag);

					}
					writeListAll.setWriteTag(writeTagList);
					list.add(writeListAll);

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					JDBCTemplate.close(rset2);
					JDBCTemplate.close(pstmt2);
					JDBCTemplate.close(rset1);
					JDBCTemplate.close(pstmt1);
				}
			}

		} catch (

		SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return list;
	}

	public String getPageNaviTag(Connection conn, int currentPage, int recordcountPerPage, int naviCountPerPage,
			String tagOpt, String keyword) {
		// 현재 페이지 값, 한페이지당 보여줄 글 수, 페이지 인덱스 번호
		int postTotalCount = postTotalCountTag(conn, keyword); // 전체 게시물 개수
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
			sb.append("<a href='/SearchListPage.kh?postSelectOpt=" + tagOpt + "&keyword=" + keyword + "&currentPage="
					+ (startNavi - 1) + "'><</a> ");
		}
		for (int i = startNavi; i <= endNavi; i++) {
			if (i == currentPage) { // 현재페이지에 <b> 추가
				sb.append("<a href='/SearchListPage.kh?postSelectOpt=" + tagOpt + "&keyword=" + keyword
						+ "&currentPage=" + i + "'><b>" + i + "</b></a> ");

			} else {
				sb.append("<a href='/SearchListPage.kh?postSelectOpt=" + tagOpt + "&keyword=" + keyword
						+ "&currentPage=" + i + "'>" + i + "</a> ");
			}
		}
		if (endNavi != pageTotalCount) {// 오른쪽 화살표
			sb.append("<a href='/SearchListPage.kh?postSelectOpt=" + tagOpt + "&keyword=" + keyword + "&currentPage="
					+ (endNavi + 1) + "'>></a> ");
		}
		// System.out.println(sb); //a태그 합친 코드
		return sb.toString();

	}

	public int postTotalCountTag(Connection conn, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int postTotalCount = 0;
		String query = "select count(*) as TOTALCOUNT from( " + "WITH EX AS ( SELECT distinct WRITE_NO "
				+ "from write left join write_tag using (write_no) " + "WHERE tag like ?) " + "SELECT NUM,WRITE_NO "
				+ "FROM (SELECT EX.*, ROW_NUMBER() OVER(ORDER BY EX.WRITE_NO DESC) AS NUM FROM EX) )";
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

	public ArrayList<WriteListAll> searchOneWritePageExplain(Connection conn, int currentPage, int recordcountPerPage,
			String explainOpt, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PreparedStatement pstmt1 = null;
		ResultSet rset1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rset2 = null;

		ArrayList<WriteListAll> list = null;
		WriteListAll writeListAll = null;
		int start = recordcountPerPage * (currentPage - 1) + 1;
		int end = recordcountPerPage * currentPage;
		String query = "select write_no from (select row_number() over(order by write_no desc) as row_num,write.* "
				+ "from write where write_delete_yn='N' and write_explain like ?) " + "where row_num between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			list = new ArrayList<WriteListAll>();

			while (rset.next()) {
				Write w = new Write();
				w.setWriteNo(rset.getInt("Write_No"));

				try {
					String query1 = "select * from write left join member using(member_no) left join product using(product_no) where write_no=?";
					pstmt1 = conn.prepareStatement(query1);
					pstmt1.setInt(1, w.getWriteNo());
					rset1 = pstmt1.executeQuery();
					writeListAll = new WriteListAll();

					while (rset1.next()) {
						Product p = new Product();
						Write w2 = new Write();
						Member m = new Member();
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

						w2.setWriteNo(rset1.getInt("WRITE_NO"));
						w2.setMemberNo(rset1.getInt("MEMBER_NO"));
						w2.setProductNo(rset1.getInt("PRODUCT_NO"));
						w2.setWriteLike(rset1.getInt("WRITE_LIKE"));
						w2.setWriteDate(rset1.getDate("WRITE_DATE"));
						w2.setWriteExplain(rset1.getString("WRITE_EXPLAIN"));
						w2.setWriteViewCount(rset1.getInt("WRITE_VIEW_COUNT"));
						w2.setWriteDeleteDate(rset1.getDate("WRITE_DELETE_DATE"));
						w2.setWriteDeleteYN(rset1.getString("WRITE_DELETE_YN").charAt(0));

						writeListAll.setProduct(p);
						writeListAll.setMember(m);
						writeListAll.setWrite(w2);

					}
					String query2 = "select * from write_tag left join write using (write_no) where write_no=? order by tag";
					pstmt2 = conn.prepareStatement(query2);
					pstmt2.setInt(1, w.getWriteNo());
					rset2 = pstmt2.executeQuery();

					ArrayList<WriteTag> writeTagList = new ArrayList<WriteTag>();
					while (rset2.next()) {
						WriteTag wTag = new WriteTag();

						wTag.setWriteNo(rset2.getInt("WRITE_NO"));
						wTag.setTag(rset2.getString("TAG"));

						writeTagList.add(wTag);

					}
					writeListAll.setWriteTag(writeTagList);
					list.add(writeListAll);

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					JDBCTemplate.close(rset2);
					JDBCTemplate.close(pstmt2);
					JDBCTemplate.close(rset1);
					JDBCTemplate.close(pstmt1);
				}
			}

		} catch (

		SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return list;
	}

	public String getPageNaviExplain(Connection conn, int currentPage, int recordcountPerPage, int naviCountPerPage,
			String explainOpt, String keyword) {
		// 현재 페이지 값, 한페이지당 보여줄 글 수, 페이지 인덱스 번호
		int postTotalCount = postTotalCountExplain(conn, keyword); // 전체 게시물 개수
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
			sb.append("<a href='/SearchListPage.kh?postSelectOpt=" + explainOpt + "&keyword=" + keyword
					+ "&currentPage=" + (startNavi - 1) + "'><</a> ");
		}
		for (int i = startNavi; i <= endNavi; i++) {
			if (i == currentPage) { // 현재페이지에 <b> 추가
				sb.append("<a href='/SearchListPage.kh?postSelectOpt=" + explainOpt + "&keyword=" + keyword
						+ "&currentPage=" + i + "'><b>" + i + "</b></a> ");

			} else {
				sb.append("<a href='/SearchListPage.kh?postSelectOpt=" + explainOpt + "&keyword=" + keyword
						+ "&currentPage=" + i + "'>" + i + "</a> ");
			}
		}
		if (endNavi != pageTotalCount) {// 오른쪽 화살표
			sb.append("<a href='/SearchListPage.kh?postSelectOpt=" + explainOpt + "&keyword=" + keyword
					+ "&currentPage=" + (endNavi + 1) + "'>></a> ");
		}
		// System.out.println(sb); //a태그 합친 코드
		return sb.toString();
	}

	public int postTotalCountExplain(Connection conn, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int postTotalCount = 0;
		String query = "select count(*) as TOTALCOUNT from(select write_no from (select row_number() over(order by write_no desc) as row_num,write.* "
				+ "from write where write_delete_yn='N' and write_explain like ?))";
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

	public ArrayList<WriteListAll> searchOneWritePageM(Connection conn, int currentPage, int recordcountPerPage,
			String postSelectOpt, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PreparedStatement pstmt1 = null;
		ResultSet rset1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rset2 = null;

		ArrayList<WriteListAll> list = null;
		WriteListAll writeListAll = null;
		int start = recordcountPerPage * (currentPage - 1) + 1;
		int end = recordcountPerPage * currentPage;
		String query = "WITH EX AS ( SELECT MEMBER_NO,WRITE_NO,PRODUCT_NO,WRITE_LIKE,WRITE_DATE,WRITE_EXPLAIN "
				+ "from write left join member using (member_no) " + "WHERE " + postSelectOpt + " like ?) "
				+ "SELECT NUM,MEMBER_NO,WRITE_NO,PRODUCT_NO,WRITE_LIKE,WRITE_DATE,WRITE_EXPLAIN "
				+ "FROM (SELECT EX.*, ROW_NUMBER() OVER(ORDER BY EX.WRITE_NO DESC) AS NUM FROM EX ) "
				+ "WHERE NUM BETWEEN ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			list = new ArrayList<WriteListAll>();

			while (rset.next()) {
				Write w = new Write();
				w.setWriteNo(rset.getInt("Write_No"));

				try {
					String query1 = "select * from write left join member using(member_no) left join product using(product_no) where write_no=?";
					pstmt1 = conn.prepareStatement(query1);
					pstmt1.setInt(1, w.getWriteNo());
					rset1 = pstmt1.executeQuery();
					writeListAll = new WriteListAll();

					while (rset1.next()) {
						Product p = new Product();
						Write w2 = new Write();
						Member m = new Member();
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

						w2.setWriteNo(rset1.getInt("WRITE_NO"));
						w2.setMemberNo(rset1.getInt("MEMBER_NO"));
						w2.setProductNo(rset1.getInt("PRODUCT_NO"));
						w2.setWriteLike(rset1.getInt("WRITE_LIKE"));
						w2.setWriteDate(rset1.getDate("WRITE_DATE"));
						w2.setWriteExplain(rset1.getString("WRITE_EXPLAIN"));
						w2.setWriteViewCount(rset1.getInt("WRITE_VIEW_COUNT"));
						w2.setWriteDeleteDate(rset1.getDate("WRITE_DELETE_DATE"));
						w2.setWriteDeleteYN(rset1.getString("WRITE_DELETE_YN").charAt(0));

						writeListAll.setProduct(p);
						writeListAll.setMember(m);
						writeListAll.setWrite(w2);

					}
					String query2 = "select * from write_tag left join write using (write_no) where write_no=? order by tag";
					pstmt2 = conn.prepareStatement(query2);
					pstmt2.setInt(1, w.getWriteNo());
					rset2 = pstmt2.executeQuery();

					ArrayList<WriteTag> writeTagList = new ArrayList<WriteTag>();
					while (rset2.next()) {
						WriteTag wTag = new WriteTag();

						wTag.setWriteNo(rset2.getInt("WRITE_NO"));
						wTag.setTag(rset2.getString("TAG"));

						writeTagList.add(wTag);

					}
					writeListAll.setWriteTag(writeTagList);
					list.add(writeListAll);

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					JDBCTemplate.close(rset2);
					JDBCTemplate.close(pstmt2);
					JDBCTemplate.close(rset1);
					JDBCTemplate.close(pstmt1);
				}
			}

		} catch (

		SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return list;
	}

	public String getPageNaviM(Connection conn, int currentPage, int recordcountPerPage, int naviCountPerPage,
			String postSelectOpt, String keyword) {
		// 현재 페이지 값, 한페이지당 보여줄 글 수, 페이지 인덱스 번호
		int postTotalCount = postTotalCountM(conn, keyword, postSelectOpt); // 전체 게시물 개수
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
			sb.append("<a href='/SearchListPage.kh?postSelectOpt=" + postSelectOpt + "&keyword=" + keyword
					+ "&currentPage=" + (startNavi - 1) + "'><</a> ");
		}
		for (int i = startNavi; i <= endNavi; i++) {
			if (i == currentPage) { // 현재페이지에 <b> 추가
				sb.append("<a href='/SearchListPage.kh?postSelectOpt=" + postSelectOpt + "&keyword=" + keyword
						+ "&currentPage=" + i + "'><b>" + i + "</b></a> ");

			} else {
				sb.append("<a href='/SearchListPage.kh?postSelectOpt=" + postSelectOpt + "&keyword=" + keyword
						+ "&currentPage=" + i + "'>" + i + "</a> ");
			}
		}
		if (endNavi != pageTotalCount) {// 오른쪽 화살표
			sb.append("<a href='/SearchListPage.kh?postSelectOpt=" + postSelectOpt + "&keyword=" + keyword
					+ "&currentPage=" + (endNavi + 1) + "'>></a> ");
		}
		// System.out.println(sb); //a태그 합친 코드
		return sb.toString();
	}

	public int postTotalCountM(Connection conn, String keyword, String postSelectOpt) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int postTotalCount = 0;
		String query = "WITH EX AS ( SELECT MEMBER_NO,WRITE_NO,PRODUCT_NO,WRITE_LIKE,WRITE_DATE,WRITE_EXPLAIN "
				+ "from write left join member using (member_no)  " + "WHERE " + postSelectOpt + " like ?) "
				+ "SELECT count(*) as TOTALCOUNT "
				+ "FROM (SELECT EX.*, ROW_NUMBER() OVER(ORDER BY EX.WRITE_NO DESC) AS NUM FROM EX )";
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

	public ArrayList<WriteListAll> searchOneWritePageDate(Connection conn, int currentPage, int recordcountPerPage,
			String dateOpt, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PreparedStatement pstmt1 = null;
		ResultSet rset1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rset2 = null;

		ArrayList<WriteListAll> list = null;
		WriteListAll writeListAll = null;
		int start = recordcountPerPage * (currentPage - 1) + 1;
		int end = recordcountPerPage * currentPage;
		String query = "select write_no from (select row_number() over(order by write_no desc) as row_num,write.* "
				+ "from write where write_delete_yn='N' and write_date like ?) " + "where row_num between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			list = new ArrayList<WriteListAll>();

			while (rset.next()) {
				Write w = new Write();
				w.setWriteNo(rset.getInt("Write_No"));

				try {
					String query1 = "select * from write left join member using(member_no) left join product using(product_no) where write_no=?";
					pstmt1 = conn.prepareStatement(query1);
					pstmt1.setInt(1, w.getWriteNo());
					rset1 = pstmt1.executeQuery();
					writeListAll = new WriteListAll();

					while (rset1.next()) {
						Product p = new Product();
						Write w2 = new Write();
						Member m = new Member();
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

						w2.setWriteNo(rset1.getInt("WRITE_NO"));
						w2.setMemberNo(rset1.getInt("MEMBER_NO"));
						w2.setProductNo(rset1.getInt("PRODUCT_NO"));
						w2.setWriteLike(rset1.getInt("WRITE_LIKE"));
						w2.setWriteDate(rset1.getDate("WRITE_DATE"));
						w2.setWriteExplain(rset1.getString("WRITE_EXPLAIN"));
						w2.setWriteViewCount(rset1.getInt("WRITE_VIEW_COUNT"));
						w2.setWriteDeleteDate(rset1.getDate("WRITE_DELETE_DATE"));
						w2.setWriteDeleteYN(rset1.getString("WRITE_DELETE_YN").charAt(0));

						writeListAll.setProduct(p);
						writeListAll.setMember(m);
						writeListAll.setWrite(w2);

					}
					String query2 = "select * from write_tag left join write using (write_no) where write_no=? order by tag";
					pstmt2 = conn.prepareStatement(query2);
					pstmt2.setInt(1, w.getWriteNo());
					rset2 = pstmt2.executeQuery();

					ArrayList<WriteTag> writeTagList = new ArrayList<WriteTag>();
					while (rset2.next()) {
						WriteTag wTag = new WriteTag();

						wTag.setWriteNo(rset2.getInt("WRITE_NO"));
						wTag.setTag(rset2.getString("TAG"));

						writeTagList.add(wTag);

					}
					writeListAll.setWriteTag(writeTagList);
					list.add(writeListAll);

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					JDBCTemplate.close(rset2);
					JDBCTemplate.close(pstmt2);
					JDBCTemplate.close(rset1);
					JDBCTemplate.close(pstmt1);
				}
			}

		} catch (

		SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return list;
	}

	public String getPageNaviDate(Connection conn, int currentPage, int recordcountPerPage, int naviCountPerPage,
			String dateOpt, String keyword) {
		// 현재 페이지 값, 한페이지당 보여줄 글 수, 페이지 인덱스 번호
		int postTotalCount = postTotalCountDate(conn, keyword); // 전체 게시물 개수
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
			sb.append("<a href='/SearchListPage.kh?postSelectOpt=" + dateOpt + "&keyword=" + keyword + "&currentPage="
					+ (startNavi - 1) + "'><</a> ");
		}
		for (int i = startNavi; i <= endNavi; i++) {
			if (i == currentPage) { // 현재페이지에 <b> 추가
				sb.append("<a href='/SearchListPage.kh?postSelectOpt=" + dateOpt + "&keyword=" + keyword
						+ "&currentPage=" + i + "'><b>" + i + "</b></a> ");

			} else {
				sb.append("<a href='/SearchListPage.kh?postSelectOpt=" + dateOpt + "&keyword=" + keyword
						+ "&currentPage=" + i + "'>" + i + "</a> ");
			}
		}
		if (endNavi != pageTotalCount) {// 오른쪽 화살표
			sb.append("<a href='/SearchListPage.kh?postSelectOpt=" + dateOpt + "&keyword=" + keyword + "&currentPage="
					+ (endNavi + 1) + "'>></a> ");
		}
		// System.out.println(sb); //a태그 합친 코드
		return sb.toString();
	}

	public int postTotalCountDate(Connection conn, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int postTotalCount = 0;
		String query = "SELECT count(*) as TOTALCOUNT from(select write_no from (select row_number() over(order by write_no desc) as row_num,write.* "
				+ "from write where write_delete_yn='N' and write_date like ?) )";
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

	public int writeDelete(Connection conn, int writeNo) {
		PreparedStatement pstmt = null;
		String query = "UPDATE write SET write_delete_date=sysdate, write_delete_yn='Y' where write_no=?";

		int result = 0;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, writeNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public boolean insertCommunityWrt(Connection conn, int productNo, int memberNo, String content, Img img) {
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		int result1 = 0;
		int result2 = 0;
		int result3 = 0;
		boolean result = false;
		
		String query1 = "insert into write values(WRITE_NO_SEQ.nextval, ?, ?, 0, sysdate, ?, 0, '', 'N')";
		String query2 = "INSERT INTO img VALUES(IMG_NO_SEQ.NEXTVAL, ?, ?, ?, ?, 'N')";
		String query3 = " insert into write_image values (WRITE_NO_SEQ.currval,IMG_NO_SEQ.currval)";

		try {
			pstmt1 = conn.prepareStatement(query1);

			pstmt1.setInt(1, memberNo);
			pstmt1.setInt(2, productNo);
			pstmt1.setString(3, content);

			result1 = pstmt1.executeUpdate();
			//
			pstmt2 = conn.prepareStatement(query2);

			pstmt2.setString(1, img.getOriginalName());
			pstmt2.setString(2, img.getChangedName());
			pstmt2.setString(3, img.getImgPath());
			pstmt2.setTimestamp(4, img.getUploadTime());

			result2 = pstmt2.executeUpdate();

			pstmt3 = conn.prepareStatement(query3);
			result3 = pstmt3.executeUpdate();

			if (result1 > 0 && result2 > 0 && result3 > 0) {
				result = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt1);
		}
		return result;

	}

	public ArrayList<ProductAll> selectProductInfo(Connection conn, String keyword, int currentPage,
			int recordCountPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		ArrayList<ProductAll> list = new ArrayList<ProductAll>();

		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;

		String query = "select * from ( SELECT ROW_NUMBER() OVER(order by product_no) AS Row_Num,product.* FROM product where PRODUCT_NAME like ?) "
				+ "LEFT JOIN PRODUCT_DATE USING (PRODUCT_NO) LEFT JOIN IMG USING (IMG_NO) LEFT JOIN Category using (category_no) "
				+ "where PRODUCT_YN='N' and IMAGE_DELETE_YN='N' and Row_Num between ? and ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				Product product = new Product();

				product.setProductNo(rset.getInt("product_no"));
				product.setCategoryNo(rset.getInt("category_no"));
				product.setProductName(rset.getString("product_name"));
				product.setProductPrice(rset.getInt("product_price"));
				product.setProductText(rset.getString("product_text"));
				product.setImgNo(rset.getInt("img_no"));

				Category category = new Category();
				category.setCategoryNo(rset.getInt("category_no"));
				category.setCategoryName(rset.getString("category_name"));

				Img img = new Img();
				img.setImgNo(rset.getInt("img_no"));
				img.setOriginalName(rset.getString("original_name"));
				img.setChangedName(rset.getString("changed_name"));
				img.setImgPath(rset.getString("img_path"));
				img.setUploadTime(rset.getTimestamp("upload_time"));

				ProductAll productAll = new ProductAll();
				productAll.setProduct(product);
				productAll.setImg(img);
				productAll.setCategory(category);

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

	public String getPageNavi(Connection conn, String keyword, int currentPage, int recordCountPerPage,
			int naviCountPerPage) {
		int productTotalCount = productSearchTotalCount(conn, keyword);

		int pageTotalCount;

		if (productTotalCount % recordCountPerPage > 0) {
			pageTotalCount = productTotalCount / recordCountPerPage + 1;
		} else {
			pageTotalCount = productTotalCount / recordCountPerPage + 0;
		}

		int startNavi = (currentPage - 1) / (naviCountPerPage) * naviCountPerPage + 1;
		int endNavi = startNavi + naviCountPerPage - 1;

		if (endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}

		StringBuilder sb = new StringBuilder();

		if (startNavi != 1) {
			sb.append("<a href='/productSearchList.kh?keyword=" + keyword + "&currentPage=" + (startNavi - 1)
					+ "'><</a> ");
		}

		for (int i = startNavi; i <= endNavi; i++) {
			if (i == currentPage) {
				sb.append("<a href='/productSearchList.kh?keyword=" + keyword + "&currentPage=" + i + "'><b>" + i
						+ "</b></a> ");
			} else {
				sb.append(
						"<a href='/productSearchList.kh?keyword=" + keyword + "&currentPage=" + i + "'>" + i + "</a> ");
			}
		}

		if (endNavi != pageTotalCount) {
			sb.append("<a href='/productSearchList.kh?keyword=" + keyword + "&currentPage=" + (startNavi - 1)
					+ "'>></a> ");
		}

		return sb.toString();

	}

	private int productSearchTotalCount(Connection conn, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		int productTotalCount = 0;

		String query = "SELECT count(*) as totalCount from PRODUCT LEFT JOIN PRODUCT_DATE USING (PRODUCT_NO) "
				+ "LEFT JOIN PRODUCT_OPTION USING (PRODUCT_NO) LEFT JOIN IMG USING (IMG_NO) WHERE PRODUCT_YN='N' "
				+ "and IMAGE_DELETE_YN='N' and PRODUCT_NAME LIKE ? order by PRODUCT_NO desc";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + keyword + "%");

			rset = pstmt.executeQuery();
			rset.next();
			productTotalCount = rset.getInt("totalCount");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return productTotalCount;
	}

	public int myHeartInsert(Connection conn, int userNo, int writeNo) {
		PreparedStatement pstmt = null;

		int result = 0;

		String query = "INSERT INTO MY_HEART VALUES(?,?)";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, userNo);
			pstmt.setInt(2, writeNo);
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);

		}
		return result;

	}

	public WriteAll selectOneDetailPrint(Connection conn, int writeNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		WriteAll wAll = null;
		Write write = null;
		Img img = null;

		String query = "SELECT * FROM WRITE LEFT JOIN WRITE_IMAGE USING(WRITE_NO) "
				+ "LEFT JOIN IMG USING(IMG_NO) WHERE WRITE_DELETE_YN='N' " + "AND WRITE_NO=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, writeNo);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				write = new Write();
				img = new Img();
				wAll = new WriteAll();

				write.setMemberNo(rset.getInt("member_no"));
				write.setWriteNo(rset.getInt("write_no"));
				write.setProductNo(rset.getInt("product_no"));
				write.setWriteDate(rset.getDate("write_date"));
				write.setWriteLike(rset.getInt("write_like"));
				write.setWriteExplain(rset.getString("write_explain"));
				write.setWriteViewCount(rset.getInt("write_view_count"));

				img.setImgNo(rset.getInt("img_no"));
				img.setOriginalName(rset.getString("original_name"));
				img.setChangedName(rset.getString("changed_name"));
				img.setImgPath(rset.getString("img_path"));
				img.setImgDeleteYN(rset.getString("image_delete_yn").charAt(0));

				wAll.setWrite(write);
				wAll.setImg(img);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);

		}
		return wAll;

	}

	public ArrayList<CommentsAll> selectCommentwrite(Connection conn, int writeNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		// 여러개의 댓글을 저장할 수 있는 list
		ArrayList<CommentsAll> list = new ArrayList<CommentsAll>();

		String query = "select * from comments left join member using(member_no) where write_no = ? and comments_delete_yn='N'";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, writeNo);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				CommentsAll coAll = new CommentsAll();

				coAll.setCommenstNo(rset.getInt("comment_no"));
				coAll.setWriteNo(rset.getInt("write_no"));
				coAll.setMemberNo(rset.getInt("member_no"));
				coAll.setCommentsDate(rset.getDate("comments_date"));
				coAll.setCommentsText(rset.getString("comments_text"));
				coAll.setMemberNickName(rset.getString("member_nickName"));
				coAll.setMemberId(rset.getString("member_id"));

				list.add(coAll);
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

	public ArrayList<WriteAll> selectRelationProduct(Connection conn, int productNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Write write = null;
		Img img = null;
		WriteAll wAll = null;
		ArrayList<WriteAll> list = new ArrayList<WriteAll>();

		String query = "SELECT * FROM WRITE LEFT JOIN WRITE_IMAGE USING(WRITE_NO) "
				+ "LEFT JOIN IMG USING(IMG_NO) WHERE PRODUCT_NO=? and write_delete_yn='N'";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, productNo);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				write = new Write();
				img = new Img();
				wAll = new WriteAll();

				write.setMemberNo(rset.getInt("member_no"));
				write.setProductNo(rset.getInt("product_no"));
				write.setWriteNo(rset.getInt("write_no"));

				img.setOriginalName(rset.getString("original_name"));
				img.setChangedName(rset.getString("changed_name"));
				img.setImgPath(rset.getString("img_path"));

				wAll.setImg(img);
				wAll.setWrite(write);

				list.add(wAll);
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

	public int deletePost(Connection conn, int writeNo) {
		PreparedStatement pstmt = null;
		int result = 0;

		String query = "update write set write_delete_yn='Y' where WRITE_NO=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, writeNo);
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);

		}
		return result;

	}

	public int insertComment(Connection conn, String comments, int writeNo, int userNo) {
		PreparedStatement pstmt = null;

		int result = 0;

		String query = "INSERT INTO COMMENTS VALUES(COMMENT_NO_SEQ.NEXTVAL,?,?,?,SYSDATE,'','N')";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, writeNo);
			pstmt.setInt(2, userNo);
			pstmt.setString(3, comments);
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);

		}
		return result;

	}

	public int modifyComment(Connection conn, int commentNo, String content, int userNo, int writeNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update comments set comments_text=? where write_no=? " + "and member_no=? and COMMENT_NO=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, content);
			pstmt.setInt(2, writeNo);
			pstmt.setInt(3, userNo);
			pstmt.setInt(4, commentNo);
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int deleteComment(Connection conn, int commentNo, int userNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update comments set comments_delete_yn='Y' where COMMENT_NO=? and member_no=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, commentNo);
			pstmt.setInt(2, userNo);
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	 public boolean updateCommunity(Connection conn, WriteAll wAll) {
         PreparedStatement pstmt1 = null;
         PreparedStatement pstmt2 = null;
         int result1 = 0;
         int result2 = 0;
         boolean result = false;
         
         String query = "update write set product_no=? ,write_explain=? where write_no=?";
         String query2 = "update img set CHANGED_NAME=? ,IMG_PATH=?, ORIGINAL_NAME=? ,UPLOAD_TIME=? where img_no=?";
         try {
            pstmt1 = conn.prepareStatement(query);
            pstmt1.setInt(1, wAll.getWrite().getProductNo());
            pstmt1.setString(2, wAll.getWrite().getWriteExplain());
            pstmt1.setInt(3, wAll.getWrite().getWriteNo());
            result1 = pstmt1.executeUpdate();
            
            pstmt2 = conn.prepareStatement(query2);
            pstmt2.setString(1, wAll.getImg().getChangedName());
            pstmt2.setString(2, wAll.getImg().getImgPath());
            pstmt2.setString(3, wAll.getImg().getOriginalName());
            pstmt2.setTimestamp(4, wAll.getImg().getUploadTime());
            pstmt2.setInt(5, wAll.getImg().getImgNo());
            result2 = pstmt2.executeUpdate();

            if (result1 > 0 && result2 > 0) {
                result = true;
             }
            
         } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         } finally {
            JDBCTemplate.close(pstmt1);
            JDBCTemplate.close(pstmt2);
         }
         return result;
      }

public WriteImage selectImg(Connection conn, int writeNo) {
     PreparedStatement pstmt = null;
      ResultSet rset = null;
      WriteImage wImg  = null;

      String query = "select * from write_image where write_no=?";

      try {
         pstmt = conn.prepareStatement(query);
         pstmt.setInt(1, writeNo);
         rset = pstmt.executeQuery();

         if (rset.next()) {
            wImg = new WriteImage();
            wImg.setImgNo(rset.getInt("img_no"));
            
         }

      } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally {

         JDBCTemplate.close(rset);
         JDBCTemplate.close(pstmt);
      }
      return wImg;
   
}
}
