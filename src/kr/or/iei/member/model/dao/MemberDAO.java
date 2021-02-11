package kr.or.iei.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.or.iei.common.JDBCTemplate;
import kr.or.iei.img.model.vo.Img;
import kr.or.iei.member.model.vo.Member;
import kr.or.iei.member.model.vo.MemberAddress;
import kr.or.iei.member.model.vo.MemberAll;
import kr.or.iei.member.model.vo.MemberComments;
import kr.or.iei.member.model.vo.MemberDate;
import kr.or.iei.member.model.vo.MemberImg;
import kr.or.iei.member.model.vo.MemberStatistics;
import kr.or.iei.member.model.vo.MemberWrite;
import kr.or.iei.orders.model.vo.Orders;
import kr.or.iei.orders.model.vo.OrdersWithImg;
import kr.or.iei.product.model.vo.Product;
import kr.or.iei.write.model.vo.Comments;
import kr.or.iei.write.model.vo.Write;

public class MemberDAO {

	public boolean insertMember(Connection conn, Member m, MemberAddress ma) {
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;

		int result1 = 0;
		int result2 = 0;
		int result3 = 0;
		boolean result = false;

		String query1 = "INSERT INTO MEMBER VALUES(MEMBER_NO_SEQ.NEXTVAL,?,?,?,?,?,?,'')";
		String query2 = "INSERT INTO MEMBER_DATE VALUES(MEMBER_NO_SEQ.CURRVAL,SYSDATE,'','N')";
		String query3 = "INSERT INTO MEMBER_ADDRESS VALUES(MEMBER_NO_SEQ.CURRVAL,?,?)";

		try {
			pstmt1 = conn.prepareStatement(query1);
			pstmt1.setString(1, m.getMemberId());
			pstmt1.setString(2, m.getMemberPw());
			pstmt1.setString(3, m.getMemberName());
			pstmt1.setString(4, m.getMemberNickname());
			pstmt1.setString(5, m.getMemberEmail());
			pstmt1.setString(6, m.getMemberPhone());
			result1 = pstmt1.executeUpdate();

			pstmt2 = conn.prepareStatement(query2);
			result2 = pstmt2.executeUpdate();

			pstmt3 = conn.prepareStatement(query3);
			pstmt3.setInt(1, ma.getPostNum());
			pstmt3.setString(2, ma.getAddress());
			result3 = pstmt3.executeUpdate();

			if (result1 > 0 && result2 > 0 && result3 > 0) {
				result = true;

			} else {
				result = false;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt1);
			JDBCTemplate.close(pstmt2);
			JDBCTemplate.close(pstmt3);
		}
		return result;

	}

	public String findId(Connection conn, String phone, String userName) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String userId = null;
		String query = "SELECT * FROM MEMBER LEFT JOIN MEMBER_DATE USING (MEMBER_NO) WHERE MEMBER_PHONE=? AND MEMBER_NAME=? AND MEMBER_WITHDRAW_YN='N'";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, phone);
			pstmt.setString(2, userName);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				userId = rset.getString("member_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return userId;

	}

	public MemberAll loginMember(Connection conn, String userId, String userPw) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		Member m = null;
		MemberAddress ma = null;
		MemberImg mImg = null;
		MemberAll mAll = null;
		String query = "SELECT * FROM MEMBER LEFT JOIN MEMBER_DATE USING (MEMBER_NO) LEFT JOIN MEMBER_ADDRESS USING (MEMBER_NO) "
				+ "LEFT JOIN MEMBER_IMG USING (MEMBER_NO) "
				+ "WHERE MEMBER_ID=? AND MEMBER_PW=? AND MEMBER_WITHDRAW_YN='N'";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPw);
			rset = pstmt.executeQuery();

			if (rset.next() == true) {
				m = new Member();
				ma = new MemberAddress();
				mImg = new MemberImg();
				m.setMemberNo(rset.getInt("member_No"));
				m.setMemberId(rset.getString("member_Id"));
				m.setMemberPw(rset.getString("member_Pw"));
				m.setMemberName(rset.getString("member_Name"));
				m.setMemberNickname(rset.getString("member_Nickname"));
				m.setMemberEmail(rset.getString("member_Email"));
				m.setMemberPhone(rset.getString("member_Phone"));
				ma.setAddress(rset.getString("address"));
				ma.setPostNum(rset.getInt("post_num"));
				mImg.setChangedFileName(rset.getString("changed_name"));
				mAll = new MemberAll();
				mAll.setM(m);
				mAll.setMa(ma);
				mAll.setmImg(mImg);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return mAll;
	}

	public int idCheck(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "SELECT COUNT(*) FROM MEMBER WHERE MEMBER_ID=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				result = rset.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return result;

	}

	public int deleteMember(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		String query = "update member_date set MEMBER_WITHDRAW_YN='Y', MEMBER_WITHDRAW_DATE=SYSDATE where member_no=(select member_no from member where member_id=?)";
		/*
		 * String query =
		 * "UPDATE MEMBER LEFT JOIN MEMBER_DATE set MEMBER_WITHDRAW_YN='Y' where MEMBER_ID=?"
		 * ;
		 */
		int result = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			result = pstmt.executeUpdate(); // insert delete update는 앞에 꺼로 해야함

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}

		return result;
	}

	public int nickCheck(Connection conn, String userNickname) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		String query = "SELECT COUNT(*) FROM MEMBER WHERE MEMBER_NICKNAME=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userNickname);

			rset = pstmt.executeQuery();

			if (rset.next()) {
				result = rset.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return result;
	}

	public int updateMember(Connection conn, MemberAll mAll) {
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		String query1 = "update member " + "set member_pw=?, member_nickname=?, " + "member_email=?, member_phone=?"
				+ " where member_id=? ";

		String query2 = "update member_address set address=?, post_Num=? where member_no=(select member_no from member where member_id=?)";
		int result1 = 0;
		int result2 = 0;
		int result = 0;
		try {
			pstmt1 = conn.prepareStatement(query1);
			pstmt1.setString(1, mAll.getM().getMemberPw());
			pstmt1.setString(2, mAll.getM().getMemberName());
			pstmt1.setString(3, mAll.getM().getMemberEmail());
			pstmt1.setString(4, mAll.getM().getMemberPhone());
			pstmt1.setString(5, mAll.getM().getMemberId());

			result1 = pstmt1.executeUpdate();

			pstmt2 = conn.prepareStatement(query2);
			pstmt2.setString(1, mAll.getMa().getAddress());
			pstmt2.setInt(2, mAll.getMa().getPostNum());
			pstmt2.setString(3, mAll.getM().getMemberId());
			result2 = pstmt2.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt2);
			JDBCTemplate.close(pstmt1);
		}
		if (result1 > 0 && result2 > 0) {
			result = 1;
		}
		return result;

	}

	public String findIdPhone(Connection conn, String userNamePhone, String phone) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String memberId = null;
		String query = "SELECT * FROM MEMBER LEFT JOIN MEMBER_DATE USING (MEMBER_NO) WHERE MEMBER_NAME=? AND MEMBER_PHONE=? AND MEMBER_WITHDRAW_YN='N'";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userNamePhone);
			pstmt.setString(2, phone);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				memberId = rset.getString("member_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return memberId;

	}

	public String findIdEmail(Connection conn, String userNameEmail, String email) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		String memberId = null;
		String query = "SELECT * FROM MEMBER LEFT JOIN MEMBER_DATE USING (MEMBER_NO) WHERE MEMBER_NAME=? AND MEMBER_EMAIL=? AND MEMBER_WITHDRAW_YN='N'";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userNameEmail);
			pstmt.setString(2, email);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				memberId = rset.getString("member_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return memberId;
	}

	public Member findPwEmail(Connection conn, String userNameEmail, String userIdEmail, String email) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		Member m = null;
		String query = "SELECT * FROM MEMBER LEFT JOIN MEMBER_DATE USING (MEMBER_NO) "
				+ "WHERE MEMBER_NAME=? AND MEMBER_ID=? AND MEMBER_EMAIL=? AND MEMBER_WITHDRAW_YN='N'";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userNameEmail);
			pstmt.setString(2, userIdEmail);
			pstmt.setString(3, email);
			rset = pstmt.executeQuery();
			if (rset.next() == true) {
				m = new Member();
				m.setMemberNo(rset.getInt("member_No"));
				m.setMemberId(rset.getString("member_Id"));
				m.setMemberPw(rset.getString("member_Pw"));
				m.setMemberName(rset.getString("member_Name"));
				m.setMemberNickname(rset.getString("member_Nickname"));
				m.setMemberEmail(rset.getString("member_Email"));
				m.setMemberPhone(rset.getString("member_Phone"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return m;

	}

	public Member findPwPhone(Connection conn, String userNamePhone, String userIdPhone, String phone) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		Member m = null;
		String query = "SELECT * FROM MEMBER LEFT JOIN MEMBER_DATE USING (MEMBER_NO) "
				+ "WHERE MEMBER_NAME=? AND MEMBER_ID=? AND MEMBER_PHONE=? AND MEMBER_WITHDRAW_YN='N'";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userNamePhone);
			pstmt.setString(2, userIdPhone);
			pstmt.setString(3, phone);
			rset = pstmt.executeQuery();
			if (rset.next() == true) {
				m = new Member();
				m.setMemberNo(rset.getInt("member_No"));
				m.setMemberId(rset.getString("member_Id"));
				m.setMemberPw(rset.getString("member_Pw"));
				m.setMemberName(rset.getString("member_Name"));
				m.setMemberNickname(rset.getString("member_Nickname"));
				m.setMemberEmail(rset.getString("member_Email"));
				m.setMemberPhone(rset.getString("member_Phone"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return m;
	}

	public int updateNewPw(Connection conn, String newPw, String userId) {
		PreparedStatement pstmt = null;
		String query = "UPDATE MEMBER SET MEMBER_PW=? WHERE MEMBER_ID=?";

		int result = 0;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, newPw);
			pstmt.setString(2, userId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;

	}

	public int insertProfileImg(Connection conn, MemberImg mImg) {
		PreparedStatement pstmt = null;
		String query = "INSERT INTO MEMBER_IMG VALUES(?,?,?,?,?,'N')";
		int result = 0;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, mImg.getMemberNo());
			pstmt.setString(2, mImg.getOriginalFileName());
			pstmt.setString(3, mImg.getChangedFileName());
			pstmt.setString(4, mImg.getFilePath());
			pstmt.setTimestamp(5, mImg.getUploadTime());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int deleteProfileImg(Connection conn, int userNo, String fileName) {
		PreparedStatement pstmt = null;
		String query = "UPDATE MEMBER_IMG SET IMAGE_DELETE_YN='Y' WHERE MEMBER_NO=? AND CHANGED_NAME=? AND IMAGE_DELETE_YN='N'";
		int result = 0;
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, userNo);
			pstmt.setString(2, fileName);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public MemberImg selectProfileImg(Connection conn, int userNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		MemberImg mImg = null;

		String query = "SELECT * FROM MEMBER_IMG WHERE MEMBER_NO = ? AND IMAGE_DELETE_YN='N'";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, userNo);
			rset = pstmt.executeQuery();

			if (rset.next()) {
				mImg = new MemberImg();
				mImg.setMemberNo(rset.getInt("member_no"));
				mImg.setOriginalFileName(rset.getString("original_name"));
				mImg.setChangedFileName(rset.getString("changed_name"));
				mImg.setFilePath(rset.getString("img_path"));
				mImg.setUploadTime(rset.getTimestamp("upload_time"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return mImg;
	}

	public ArrayList<MemberAll> selectAllMemberPage(Connection conn, int currentPage, int recordCountPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		ArrayList<MemberAll> mAllList = new ArrayList<MemberAll>();
		MemberAll mAll = null;
		Member m = null;
		MemberDate md = null;

		int start = currentPage * recordCountPerPage - (recordCountPerPage - 1);
		int end = currentPage * recordCountPerPage;

		/*
		 * String query =
		 * "SELECT * FROM (SELECT * FROM MEMBER LEFT JOIN MEMBER_DATE USING (MEMBER_NO) ORDER BY MEMBER_NO) WHERE ROWNUM between ? and ?"
		 * ;
		 */
		String query = "SELECT * FROM (SELECT ROW_NUMBER() OVER(order by member_no) AS Row_Num,member.* FROM member) "
				+ "MEMBER LEFT JOIN MEMBER_DATE USING (MEMBER_NO) WHERE Row_Num between ? and ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				mAll = new MemberAll();
				m = new Member();
				md = new MemberDate();

				m.setMemberNo(rset.getInt("member_no"));
				m.setMemberId(rset.getString("member_id"));
				m.setMemberPw(rset.getString("member_pw"));
				m.setMemberName(rset.getString("member_name"));
				m.setMemberNickname(rset.getString("member_nickname"));
				m.setMemberEmail(rset.getString("member_email"));
				m.setMemberPhone(rset.getString("member_phone"));

				md.setMemberJoinDate(rset.getDate("member_join_date"));
				md.setMemberWithdrawDate(rset.getDate("member_withdraw_date"));
				md.setMemberWithdrawYN((rset.getString("member_withdraw_YN")).charAt(0));

				mAll.setM(m);
				mAll.setMd(md);
				mAllList.add(mAll);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);

		}

		return mAllList;

	}

	public String getPageNavi(Connection conn, int currentPage, int recordCountPerPage, int naviCountPerPage) {

		int memberTotalCount = memberTotalCount(conn);

		int pageTotalCount;

		if (memberTotalCount % recordCountPerPage > 0) {
			pageTotalCount = memberTotalCount / recordCountPerPage + 1;
		} else {
			pageTotalCount = memberTotalCount / recordCountPerPage + 0;
		}

		int startNavi = (currentPage - 1) / (naviCountPerPage) * naviCountPerPage + 1;
		int endNavi = startNavi + naviCountPerPage - 1;

		if (endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}

		StringBuilder sb = new StringBuilder();

		if (startNavi != 1) {
			sb.append("<a href='/memberAllListPage.kh?currentPage=" + (startNavi - 1) + "'>< < </a> ");
		}

		for (int i = startNavi; i <= endNavi; i++) {
			if (i == currentPage) {
				sb.append("<a href='/memberAllListPage.kh?currentPage=" + i + "'><b>" + i + "</b></a> ");

			} else {
				sb.append("<a href='/memberAllListPage.kh?currentPage=" + i + "'>" + i + "</a> ");
			}
		}

		if (endNavi != pageTotalCount) {
			sb.append("<a href='/memberAllListPage.kh?currentPage=" + (endNavi + 1) + "'> > </a> ");
		}

		return sb.toString();

	}

	public int memberTotalCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int memberTotalCount = 0;

		String query = "SELECT COUNT(*) as totalCount FROM member";

		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			rset.next();

			memberTotalCount = rset.getInt("totalCount");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}

		return memberTotalCount;
	}

	public int memberStateUpdate(Connection conn, int memberNo, char memberWithdrawYN) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = null;

		if (memberWithdrawYN == 'Y') {
			query = "update member_date set member_withdraw_YN=?, MEMBER_WITHDRAW_DATE=sysdate where member_no=?";
		} else {
			query = "update member_date set member_withdraw_YN=?, MEMBER_WITHDRAW_DATE='' where member_no=?";
		}

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, Character.toString(memberWithdrawYN));
			pstmt.setInt(2, memberNo);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<MemberAll> memberOneListPage(Connection conn, int currentPage, int recordcountPerPage,
			String searchOpt, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<MemberAll> mAllList = new ArrayList<MemberAll>();

		int start = currentPage * recordcountPerPage - (recordcountPerPage - 1);
		int end = currentPage * recordcountPerPage;
		String query = "select * from "
				+ "( SELECT ROW_NUMBER() OVER(order by member_no) AS Row_Num,member.* FROM member where " + searchOpt
				+ " like ? ) " + "LEFT JOIN MEMBER_DATE USING (MEMBER_NO) WHERE Row_Num between ? and ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);

			rset = pstmt.executeQuery();

			while (rset.next()) {
				MemberAll mAll = new MemberAll();
				Member m = new Member();
				MemberDate md = new MemberDate();

				m.setMemberNo(rset.getInt("member_No"));
				m.setMemberId(rset.getString("member_Id"));
				m.setMemberPw(rset.getString("member_Pw"));
				m.setMemberName(rset.getString("member_Name"));
				m.setMemberNickname(rset.getString("member_Nickname"));
				m.setMemberEmail(rset.getString("member_Email"));
				m.setMemberPhone(rset.getString("member_Phone"));

				md.setMemberJoinDate(rset.getDate("member_join_date"));
				md.setMemberWithdrawDate(rset.getDate("member_withdraw_date"));
				md.setMemberWithdrawYN((rset.getString("member_withdraw_YN")).charAt(0));

				mAll.setM(m);
				mAll.setMd(md);
				mAllList.add(mAll);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);

		}

		return mAllList;
	}

	public String getPageNavi(Connection conn, int currentPage, int recordcountPerPage, int naviCountPerPage,
			String searchOpt, String keyword) {

		// 키워드를 바탕으로 검색된 pageNavifmf 만드는 메소드
		int memberTotalCount = MemberSearchTotalCount(conn, searchOpt, keyword);
		int pageTotalCount;

		if (memberTotalCount % recordcountPerPage > 0) {
			pageTotalCount = memberTotalCount / recordcountPerPage + 1;
			// pageTotalCount = 108 / 5 + 1 -> 22page
		} else {
			pageTotalCount = memberTotalCount / recordcountPerPage + 0;
			// pageTotalCount = 105 / 5 + 0 -> 21page
		}
		int startNavi = ((currentPage - 1) / naviCountPerPage) * naviCountPerPage + 1;
		int endNavi = startNavi + naviCountPerPage - 1;

		if (endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}

		// pageNavi 구현
		StringBuilder sb = new StringBuilder();

		// 만약 첫 번째 PageNavi가 아니라면 '<' 모양을 추가해라
		if (startNavi != 1) {
			sb.append("<a href='/OneListPage.kh?searchOpt=" + searchOpt + "&keyword=" + keyword + "&currentPage="
					+ (startNavi - 1) + "'> < </a> ");
		}

		for (int i = startNavi; i <= endNavi; i++) {
			if (i == currentPage) {
				sb.append("<a href='/OneListPage.kh?searchOpt=" + searchOpt + "&keyword=" + keyword + "&currentPage="
						+ i + "'><b>" + i + "</b></a> ");
			} else {
				sb.append("<a href='/OneListPage.kh?searchOpt=" + searchOpt + "&keyword=" + keyword + "&currentPage="
						+ i + "'>" + i + "</a> ");
			}
		}
		// 마지막 pageNavi가 아니라면 '>' 모양을 추가해라

		if (endNavi != pageTotalCount) {
			sb.append("<a href='/OneListPage.kh?searchOpt=" + searchOpt + "&keyword=" + keyword + "&currentPage="
					+ (endNavi + 1) + "'> > </a> ");
		}

		// System.out.println(sb);

		return sb.toString();

	}

	private int MemberSearchTotalCount(Connection conn, String searchOpt, String keyword) {

		PreparedStatement pstmt = null;
		ResultSet rset = null;

		int memberSearchTotalCount = 0;
		String query = "select count(*) as totalCount from member where " + searchOpt + " like ?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + keyword + "%");
			rset = pstmt.executeQuery();
			rset.next();
			memberSearchTotalCount = rset.getInt("totalCount");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return memberSearchTotalCount;
	}
	
	public ArrayList<OrdersWithImg> selectDeliveryInfo(Connection conn, int userNo) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		ArrayList<OrdersWithImg> list=new ArrayList<OrdersWithImg>();
		
		String query="SELECT * FROM ORDERS LEFT JOIN PRODUCT USING (PRODUCT_NO) LEFT JOIN IMG USING (IMG_NO) WHERE MEMBER_NO=? ORDER BY ORDERS_NO DESC";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, userNo);
			rset=pstmt.executeQuery();
			
			while(rset.next()) {
				Orders o=new Orders();
				
				o.setOrdersNo(rset.getInt("ORDERS_NO"));
				o.setMemberNo(rset.getInt("MEMBER_NO"));
				o.setProductNo(rset.getInt("PRODUCT_NO"));
				o.setOrdersDate(rset.getDate("ORDERS_DATE"));
				o.setOrdersNum(rset.getInt("ORDERS_NUM"));
				o.setDeliveryYN(rset.getString("DELIVERY_YN").charAt(0));
				o.setDemand(rset.getString("DEMAND"));
				o.setReceiverName(rset.getString("RECEIVER_NAME"));
				o.setReceiverPhone(rset.getString("RECEIVER_PHONE"));
				o.setReceiverAddress(rset.getString("RECEIVER_ADDRESS"));
				o.setReceiveYN(rset.getString("RECEIVE_YN").charAt(0));
				
				Product p=new Product();
				
				p.setProductNo(rset.getInt("PRODUCT_NO"));
				p.setCategoryNo(rset.getInt("CATEGORY_NO"));
				p.setProductName(rset.getString("PRODUCT_NAME"));
				p.setProductPrice(rset.getInt("PRODUCT_PRICE"));
				p.setProductText(rset.getString("PRODUCT_TEXT"));
				p.setImgNo(rset.getInt("IMG_NO"));
				
				Img img=new Img();
				
				img.setImgNo(rset.getInt("IMG_NO"));
				img.setOriginalName(rset.getString("ORIGINAL_NAME"));
				img.setChangedName(rset.getString("CHANGED_NAME"));
				img.setImgPath(rset.getString("IMG_PATH"));
				img.setUploadTime(rset.getTimestamp("UPLOAD_TIME"));
				
				OrdersWithImg oAll=new OrdersWithImg();
				
				oAll.setOrders(o);
				oAll.setProduct(p);
				oAll.setImg(img);
				
				list.add(oAll);
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

	public int updateDeliveryStatus(Connection conn, int orderNo) {
		PreparedStatement pstmt=null;
		int result=0;
		
		String query="UPDATE ORDERS SET RECEIVE_YN='Y' WHERE ORDERS_NO=?";
		
		try {
			pstmt=conn.prepareStatement(query);
			pstmt.setInt(1, orderNo);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<MemberStatistics> memberStatistics(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<MemberStatistics> list = new ArrayList<MemberStatistics>();
		MemberStatistics ms = null;
		String query = "SELECT extract(month from member_join_date) as month,count(*) as count "
				+ "FROM member left join member_date using(member_no) " + "where member_withdraw_yn='N' "
				+ "group by member_join_date order by 1";

		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				ms = new MemberStatistics();
				ms.setMonth(rset.getString("month"));
				ms.setmCount(rset.getInt("count"));

				list.add(ms);
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

	public ArrayList<MemberStatistics> memberStatisticsSales(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<MemberStatistics> list = new ArrayList<MemberStatistics>();
		MemberStatistics ms = null;
		String query = "select month, sum(price) as price from( select month, price from "
				+ "(select extract(month from orders_date)as month,sum(orders_num*product_price)as price "
				+ "from orders left join product using(product_no) left join product_date using(product_no) "
				+ "where orders_pay='Y' group by orders_date)) group by month order by 1";

		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();

			while (rset.next()) {
				ms = new MemberStatistics();
				ms.setMonth(rset.getString("month"));
				ms.setpCount(rset.getInt("price"));

				list.add(ms);
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

	public ArrayList<MemberWrite> loadWritePage(Connection conn, int memberNo, int page1, int recordCountPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PreparedStatement pstmt1 = null;
		ResultSet rset1 = null;

		ArrayList<MemberWrite> list = null;
		MemberWrite mWrite = null;
		int start = recordCountPerPage * (page1 - 1) + 1;
		int end = recordCountPerPage * page1;
		String query = "SELECT write_no FROM (SELECT ROW_NUMBER() OVER(order by write_no DESC) AS Row_Num,write.* FROM write "
				+ "WHERE WRITE_DELETE_YN='N' and member_no=?) LEFT JOIN write_image USING (write_no) LEFT JOIN img USING (img_no) "
				+ "WHERE Row_Num between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			list = new ArrayList<MemberWrite>();
			while (rset.next()) {
				Write w0 = new Write();
				w0.setWriteNo(rset.getInt("write_No"));
				try {
					String query1 = "select * from (select count(write_no) as TOTAL,write_no from write "
							+ "left join comments using (write_no) group by write_no) "
							+ "left join write_image using (write_no) left join img using(img_no) left join write using (write_no) where write_no = ?";
					pstmt1 = conn.prepareStatement(query1);
					pstmt1.setInt(1, w0.getWriteNo());
					rset1 = pstmt1.executeQuery();

					mWrite = new MemberWrite();
					while (rset1.next()) {
						Write w = new Write();
						Img i = new Img();

						w.setMemberNo(rset1.getInt("member_no"));
						w.setProductNo(rset1.getInt("product_no"));
						w.setWriteLike(rset1.getInt("write_like"));
						w.setWriteDate(rset1.getDate("write_Date"));
						w.setWriteExplain(rset1.getString("write_explain"));
						w.setWriteViewCount(rset1.getInt("WRITE_VIEW_COUNT"));
						w.setWriteDeleteDate(rset1.getDate("WRITE_DELETE_DATE"));
						w.setWriteDeleteYN(rset1.getString("WRITE_DELETE_YN").charAt(0));

						i.setImgNo(rset1.getInt("img_no"));
						i.setOriginalName(rset1.getString("original_name"));
						i.setChangedName(rset1.getString("changed_name"));
						i.setImgPath(rset1.getString("img_path"));
						i.setUploadTime(rset1.getTimestamp("upload_time"));

						mWrite.setW(w);
						mWrite.setI(i);
						mWrite.setCommentCount(rset1.getInt("TOTAL"));

						list.add(mWrite);
					}
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

	public String loadWritePageNavi(Connection conn, int page1, int recordCountPerPage, int naviCountPerPage,
			int memberNo) {
		// 현재 페이지 값, 한페이지당 보여줄 글 수, 페이지 인덱스 번호
		int postTotalCount = loadWriteTotalCount(conn, memberNo); // 전체 게시물 개수
		// 게시물개수/한페이지당 글 수 -> 나머지 있으면 몫+1, 나머지 없으면 몫
		int pageTotalCount; // 전체 페이지 인덱스 수
		if (postTotalCount % recordCountPerPage > 0) {
			pageTotalCount = postTotalCount / recordCountPerPage + 1;
		} else {
			pageTotalCount = postTotalCount / recordCountPerPage;
		}
		// pageNavi 는 현재 페이지 인덱스
		// startNavi=((현재페이지-1)/보여질 navi 개수)*보여질navi개수+1;
		int startNavi = ((page1 - 1) / naviCountPerPage) * naviCountPerPage + 1;
		int endNavi = startNavi + naviCountPerPage - 1;
		if (endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}
		StringBuilder sb = new StringBuilder(); // 태그를 String 형태로 넘겨주기 위해
		if (startNavi != 1) {// 왼쪽 화살표
			sb.append("<a href='/myPage.kh?page1=" + (startNavi - 1) + "'><</a> ");
		}
		for (int i = startNavi; i <= endNavi; i++) {
			if (i == page1) { // 현재페이지에 <b> 추가
				sb.append("<a href='/myPage.kh?page1=" + i + "'><b>" + i + "</b></a> ");

			} else {
				sb.append("<a href='/myPage.kh?page1=" + i + "'>" + i + "</a> ");
			}
		}
		if (endNavi != pageTotalCount) {// 오른쪽 화살표
			sb.append("<a href='/myPage.kh?page1=" + (endNavi + 1) + "'>></a> ");
		}
		// System.out.println(sb); //a태그 합친 코드
		return sb.toString();
	}

	public int loadWriteTotalCount(Connection conn, int memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int postTotalCount = 0;
		String query = "select count(*) as TOTALCOUNT from(select * from (select count(write_no) as TOTAL,write_no from write "
				+ "left join comments using (write_no) group by write_no) "
				+ "left join write_image using (write_no) left join img using(img_no) left join write using (write_no) "
				+ "where member_no = ?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
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

	public ArrayList<MemberComments> loadCommentPage(Connection conn, int memberNo, int page2, int recordCountPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		ArrayList<MemberComments> list = null;
		MemberComments mc = null;
		int start = recordCountPerPage * (page2 - 1) + 1;
		int end = recordCountPerPage * page2;
		String query = "WITH EX AS ( SELECT * from comments left join member using (member_no) where comments_DELETE_YN='N' and member_no = ?) "
				+ "SELECT NUM,comment_no "
				+ "FROM (SELECT EX.*, ROW_NUMBER() OVER(ORDER BY EX.comment_no DESC) AS NUM FROM EX) where NUM between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			list = new ArrayList<MemberComments>();

			while (rset.next()) {

				Comments c0 = new Comments();
				c0.setCommenstNo(rset.getInt("Comment_No"));
				PreparedStatement pstmt1 = null;
				ResultSet rset1 = null;
				try {

					String query1 = "select * from comments left join write using (write_no) where comment_no=?";
					pstmt1 = conn.prepareStatement(query1);
					pstmt1.setInt(1, c0.getCommenstNo());
					rset1 = pstmt1.executeQuery();

					mc = new MemberComments();
					while (rset1.next()) {
						Write w = new Write();
						Comments c = new Comments();

						w.setMemberNo(rset1.getInt("member_no"));
						w.setProductNo(rset1.getInt("product_no"));
						w.setWriteLike(rset1.getInt("write_like"));
						w.setWriteDate(rset1.getDate("write_Date"));
						w.setWriteExplain(rset1.getString("write_explain"));
						w.setWriteViewCount(rset1.getInt("WRITE_VIEW_COUNT"));
						w.setWriteDeleteDate(rset1.getDate("WRITE_DELETE_DATE"));
						w.setWriteDeleteYN(rset1.getString("WRITE_DELETE_YN").charAt(0));

						c.setCommenstNo(rset1.getInt("comment_no"));
						c.setWriteNo(rset1.getInt("write_no"));
						c.setCommentsText(rset1.getString("comments_text"));
						c.setCommentsDate(rset1.getDate("comments_date"));
						c.setCommentsDeleteDate(rset1.getDate("comments_delete_date"));
						c.setCommentsDeleteYN(rset1.getString("comments_delete_yn").charAt(0));

						mc.setC(c);
						mc.setW(w);

						list.add(mc);
					}
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

	public String loadCommentPageNavi(Connection conn, int page2, int recordCountPerPage, int naviCountPerPage,
			int memberNo) {
		// 현재 페이지 값, 한페이지당 보여줄 글 수, 페이지 인덱스 번호
		int postTotalCount = loadCommentsTotalCount(conn, memberNo); // 전체 게시물 개수
		// 게시물개수/한페이지당 글 수 -> 나머지 있으면 몫+1, 나머지 없으면 몫
		int pageTotalCount; // 전체 페이지 인덱스 수
		if (postTotalCount % recordCountPerPage > 0) {
			pageTotalCount = postTotalCount / recordCountPerPage + 1;
		} else {
			pageTotalCount = postTotalCount / recordCountPerPage;
		}
		// pageNavi 는 현재 페이지 인덱스
		// startNavi=((현재페이지-1)/보여질 navi 개수)*보여질navi개수+1;
		int startNavi = ((page2 - 1) / naviCountPerPage) * naviCountPerPage + 1;
		int endNavi = startNavi + naviCountPerPage - 1;
		if (endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}
		StringBuilder sb = new StringBuilder(); // 태그를 String 형태로 넘겨주기 위해
		if (startNavi != 1) {// 왼쪽 화살표
			sb.append("<a href='/myPage.kh?page2=" + (startNavi - 1) + "'><</a> ");
		}
		for (int i = startNavi; i <= endNavi; i++) {
			if (i == page2) { // 현재페이지에 <b> 추가
				sb.append("<a href='/myPage.kh?page2=" + i + "'><b>" + i + "</b></a> ");

			} else {
				sb.append("<a href='/myPage.kh?page2=" + i + "'>" + i + "</a> ");
			}
		}
		if (endNavi != pageTotalCount) {// 오른쪽 화살표
			sb.append("<a href='/myPage.kh?page2=" + (endNavi + 1) + "'>></a> ");
		}
		// System.out.println(sb); //a태그 합친 코드
		return sb.toString();
	}

	public int loadCommentsTotalCount(Connection conn, int memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int postTotalCount = 0;
		String query = "select count(*) as TOTALCOUNT from( select * from "
				+ "(SELECT ROW_NUMBER() OVER(order by comment_no DESC) AS Row_Num,comments.* FROM comments "
				+ "WHERE comments_DELETE_YN='N' and member_no=?))";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
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

	public ArrayList<MemberWrite> loadHeartPage(Connection conn, int memberNo, int page3, int recordCountPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PreparedStatement pstmt1 = null;
		ResultSet rset1 = null;

		ArrayList<MemberWrite> list = null;
		MemberWrite mWrite = null;
		int start = recordCountPerPage * (page3 - 1) + 1;
		int end = recordCountPerPage * page3;
		String query = "SELECT write_no FROM (SELECT ROW_NUMBER() OVER(order by member_no) AS Row_Num,my_heart.* FROM my_heart "
				+ "WHERE member_no=?) WHERE Row_Num between ? and ? ";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();
			list = new ArrayList<MemberWrite>();
			while (rset.next()) {
				Write w0 = new Write();
				w0.setWriteNo(rset.getInt("write_No"));
				try {
					String query1 = "select * from ((select count(write_no) as TOTAL,write_no from write " + 
							"left join comments using (write_no) group by write_no) " + 
							"left join write_image using (write_no) " + 
							"left join img using(img_no) left join write using (write_no) )where write_no =?";
					pstmt1 = conn.prepareStatement(query1);
					pstmt1.setInt(1, w0.getWriteNo());
					rset1 = pstmt1.executeQuery();

					mWrite = new MemberWrite();
					while (rset1.next()) {
						Write w = new Write();
						Img i = new Img();

						w.setMemberNo(rset1.getInt("member_no"));
						w.setProductNo(rset1.getInt("product_no"));
						w.setWriteLike(rset1.getInt("write_like"));
						w.setWriteDate(rset1.getDate("write_Date"));
						w.setWriteExplain(rset1.getString("write_explain"));
						w.setWriteViewCount(rset1.getInt("WRITE_VIEW_COUNT"));
						w.setWriteDeleteDate(rset1.getDate("WRITE_DELETE_DATE"));
						w.setWriteDeleteYN(rset1.getString("WRITE_DELETE_YN").charAt(0));

						i.setImgNo(rset1.getInt("img_no"));
						i.setOriginalName(rset1.getString("original_name"));
						i.setChangedName(rset1.getString("changed_name"));
						i.setImgPath(rset1.getString("img_path"));
						i.setUploadTime(rset1.getTimestamp("upload_time"));

						mWrite.setW(w);
						mWrite.setI(i);
						mWrite.setCommentCount(rset1.getInt("TOTAL"));

						list.add(mWrite);
					}
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

	public String loadHeartPageNavi(Connection conn, int page3, int recordCountPerPage, int naviCountPerPage,
			int memberNo) {
		// 현재 페이지 값, 한페이지당 보여줄 글 수, 페이지 인덱스 번호
				int postTotalCount = loadHeartTotalCount(conn, memberNo); // 전체 게시물 개수
				// 게시물개수/한페이지당 글 수 -> 나머지 있으면 몫+1, 나머지 없으면 몫
				int pageTotalCount; // 전체 페이지 인덱스 수
				if (postTotalCount % recordCountPerPage > 0) {
					pageTotalCount = postTotalCount / recordCountPerPage + 1;
				} else {
					pageTotalCount = postTotalCount / recordCountPerPage;
				}
				// pageNavi 는 현재 페이지 인덱스
				// startNavi=((현재페이지-1)/보여질 navi 개수)*보여질navi개수+1;
				int startNavi = ((page3 - 1) / naviCountPerPage) * naviCountPerPage + 1;
				int endNavi = startNavi + naviCountPerPage - 1;
				if (endNavi > pageTotalCount) {
					endNavi = pageTotalCount;
				}
				StringBuilder sb = new StringBuilder(); // 태그를 String 형태로 넘겨주기 위해
				if (startNavi != 1) {// 왼쪽 화살표
					sb.append("<a href='/myPage.kh?page3=" + (startNavi - 1) + "'><</a> ");
				}
				for (int i = startNavi; i <= endNavi; i++) {
					if (i == page3) { // 현재페이지에 <b> 추가
						sb.append("<a href='/myPage.kh?page3=" + i + "'><b>" + i + "</b></a> ");

					} else {
						sb.append("<a href='/myPage.kh?page3=" + i + "'>" + i + "</a> ");
					}
				}
				if (endNavi != pageTotalCount) {// 오른쪽 화살표
					sb.append("<a href='/myPage.kh?page3=" + (endNavi + 1) + "'>></a> ");
				}
				// System.out.println(sb); //a태그 합친 코드
				return sb.toString();
	}

	public int loadHeartTotalCount(Connection conn, int memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int postTotalCount = 0;
		String query = "select count(*) as TOTALCOUNT from(select write_no from my_heart where member_no=?)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
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

}
