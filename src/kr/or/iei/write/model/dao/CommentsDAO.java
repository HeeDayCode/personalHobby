package kr.or.iei.write.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.or.iei.common.JDBCTemplate;
import kr.or.iei.member.model.vo.Member;
import kr.or.iei.write.model.vo.Comments;
import kr.or.iei.write.model.vo.CommentsMember;

public class CommentsDAO {

	public ArrayList<CommentsMember> selectAllCommentsPage(Connection conn, int currentPage, int recordCountPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PreparedStatement pstmt1 = null;
		ResultSet rset1 = null;
		ArrayList<CommentsMember> list = null;

		CommentsMember CM = null;
		int start = recordCountPerPage * (currentPage - 1) + 1;
		int end = recordCountPerPage * currentPage;
		String query = "WITH EX AS ( SELECT comment_no,member_no " + 
				"from comments left join member using (member_no) where comments_delete_yn='N' ) SELECT NUM,comment_no,member_no " + 
				"FROM (SELECT EX.*, ROW_NUMBER() OVER(ORDER BY EX.comment_no DESC) AS NUM FROM EX) " + 
				"where NUM between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();

			list = new ArrayList<CommentsMember>();

			while (rset.next()) {
				Comments c0 = new Comments();
				c0.setCommenstNo(rset.getInt("Comment_no"));
				try {
					String query1 = "select * from comments left join member using(member_no) where comment_no=?";
					pstmt1 = conn.prepareStatement(query1);
					pstmt1.setInt(1, c0.getCommenstNo());
					rset1 = pstmt1.executeQuery();
					CM = new CommentsMember();

					while (rset1.next()) {
						Comments c = new Comments();
						Member m = new Member();

						c.setCommenstNo(rset1.getInt("comment_no"));
						c.setWriteNo(rset1.getInt("write_no"));
						c.setCommentsText(rset1.getString("comments_text"));
						c.setCommentsDate(rset1.getDate("comments_date"));
						c.setCommentsDeleteDate(rset1.getDate("comments_delete_date"));
						c.setCommentsDeleteYN(rset1.getString("comments_delete_yn").charAt(0));

						m.setMemberNo(rset1.getInt("member_No"));
						m.setMemberId(rset1.getString("member_Id"));
						m.setMemberPw(rset1.getString("member_Pw"));
						m.setMemberName(rset1.getString("member_Name"));
						m.setMemberNickname(rset1.getString("member_Nickname"));
						m.setMemberEmail(rset1.getString("member_Email"));
						m.setMemberPhone(rset1.getString("member_Phone"));

						CM.setC(c);
						CM.setM(m);
						list.add(CM);
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

	public String getPageNavi(Connection conn, int currentPage, int recordCountPerPage, int naviCountPerPage) {
		// 현재 페이지 값, 한페이지당 보여줄 글 수, 페이지 인덱스 번호
		int postTotalCount = commentsTotalCount(conn); // 전체 게시물 개수
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
			sb.append("<a href='/commentsAllList.kh?currentPage=" + (startNavi - 1) + "'><</a> ");
		}
		for (int i = startNavi; i <= endNavi; i++) {
			if (i == currentPage) { // 현재페이지에 <b> 추가
				sb.append("<a href='/commentsAllList.kh?currentPage=" + i + "'><b>" + i + "</b></a> ");

			} else {
				sb.append("<a href='/commentsAllList.kh?currentPage=" + i + "'>" + i + "</a> ");
			}
		}
		if (endNavi != pageTotalCount) {// 오른쪽 화살표
			sb.append("<a href='/commentsAllList.kh?currentPage=" + (endNavi + 1) + "'>></a> ");
		}
		// System.out.println(sb); //a태그 합친 코드
		return sb.toString();
	}

	public int commentsTotalCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int postTotalCount = 0;
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM comments WHERE comments_delete_yn='N'";
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

	public ArrayList<CommentsMember> searchCommentsC(Connection conn, int currentPage, int recordcountPerPage,
			String commentsOpt, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PreparedStatement pstmt1 = null;
		ResultSet rset1 = null;
		ArrayList<CommentsMember> list = null;

		CommentsMember CM = null;
		int start = recordcountPerPage * (currentPage - 1) + 1;
		int end = recordcountPerPage * currentPage;
		String query = "WITH EX AS ( SELECT comment_no,member_no "
				+ "from comments left join member using (member_no) where " + commentsOpt
				+ " like ? ) SELECT NUM,comment_no,member_no "
				+ "FROM (SELECT EX.*, ROW_NUMBER() OVER(ORDER BY EX.comment_no DESC) AS NUM FROM EX) "
				+ "where NUM between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();

			list = new ArrayList<CommentsMember>();

			while (rset.next()) {
				Comments c0 = new Comments();
				c0.setCommenstNo(rset.getInt("Comment_no"));
				try {
					String query1 = "select * from comments left join member using(member_no) where comment_no=?";
					pstmt1 = conn.prepareStatement(query1);
					pstmt1.setInt(1, c0.getCommenstNo());
					rset1 = pstmt1.executeQuery();
					CM = new CommentsMember();

					while (rset1.next()) {
						Comments c = new Comments();
						Member m = new Member();

						c.setCommenstNo(rset1.getInt("comment_no"));
						c.setWriteNo(rset1.getInt("write_no"));
						c.setCommentsText(rset1.getString("comments_text"));
						c.setCommentsDate(rset1.getDate("comments_date"));
						c.setCommentsDeleteDate(rset1.getDate("comments_delete_date"));
						c.setCommentsDeleteYN(rset1.getString("comments_delete_yn").charAt(0));

						m.setMemberNo(rset1.getInt("member_No"));
						m.setMemberId(rset1.getString("member_Id"));
						m.setMemberPw(rset1.getString("member_Pw"));
						m.setMemberName(rset1.getString("member_Name"));
						m.setMemberNickname(rset1.getString("member_Nickname"));
						m.setMemberEmail(rset1.getString("member_Email"));
						m.setMemberPhone(rset1.getString("member_Phone"));

						CM.setC(c);
						CM.setM(m);
						list.add(CM);
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

	public String getPageNaviComments(Connection conn, int currentPage, int recordcountPerPage, int naviCountPerPage,
			String commentsOpt, String keyword) {
		// 현재 페이지 값, 한페이지당 보여줄 글 수, 페이지 인덱스 번호
		int postTotalCount = commentsTotalCountComments(conn, keyword, commentsOpt); // 전체 게시물 개수
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
			sb.append("<a href='/SearchComments.kh?commentsSelectOpt=" + commentsOpt + "&keyword=" + keyword
					+ "&currentPage=" + (startNavi - 1) + "'><</a> ");
		}
		for (int i = startNavi; i <= endNavi; i++) {
			if (i == currentPage) { // 현재페이지에 <b> 추가
				sb.append("<a href='/SearchComments.kh?commentsSelectOpt=" + commentsOpt + "&keyword=" + keyword
						+ "&currentPage=" + i + "'><b>" + i + "</b></a> ");

			} else {
				sb.append("<a href='/SearchComments.kh?commentsSelectOpt=" + commentsOpt + "&keyword=" + keyword
						+ "&currentPage=" + i + "'>" + i + "</a> ");
			}
		}
		if (endNavi != pageTotalCount) {// 오른쪽 화살표
			sb.append("<a href='/SearchComments.kh?commentsSelectOpt=" + commentsOpt + "&keyword=" + keyword
					+ "&currentPage=" + (endNavi + 1) + "'>></a> ");
		}
		// System.out.println(sb); //a태그 합친 코드
		return sb.toString();
	}

	public int commentsTotalCountComments(Connection conn, String keyword, String commentsOpt) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int postTotalCount = 0;
		String query = "SELECT count(*) as TOTALCOUNT from "
				+ "(select comment_no from (select row_number() over(order by comment_no desc) as row_num,comments.* "
				+ "from comments where comments_delete_yn='N' and " + commentsOpt + " like ?) )";
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

	public ArrayList<CommentsMember> searchCommentsM(Connection conn, int currentPage, int recordcountPerPage,
			String memberOpt, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		PreparedStatement pstmt1 = null;
		ResultSet rset1 = null;
		ArrayList<CommentsMember> list = null;

		CommentsMember CM = null;
		int start = recordcountPerPage * (currentPage - 1) + 1;
		int end = recordcountPerPage * currentPage;
		String query = "WITH EX AS ( SELECT comment_no,member_no "
				+ "from comments left join member using (member_no) where " + memberOpt + " like ?) "
				+ "SELECT NUM,comment_no,member_no "
				+ "FROM (SELECT EX.*, ROW_NUMBER() OVER(ORDER BY EX.comment_no DESC) AS NUM FROM EX) "
				+ "where NUM between ? and ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rset = pstmt.executeQuery();

			list = new ArrayList<CommentsMember>();

			while (rset.next()) {
				Comments c0 = new Comments();
				c0.setCommenstNo(rset.getInt("Comment_no"));
				try {
					String query1 = "select * from comments left join member using(member_no) where comment_no=?";
					pstmt1 = conn.prepareStatement(query1);
					pstmt1.setInt(1, c0.getCommenstNo());
					rset1 = pstmt1.executeQuery();
					CM = new CommentsMember();

					while (rset1.next()) {
						Comments c = new Comments();
						Member m = new Member();

						c.setCommenstNo(rset1.getInt("comment_no"));
						c.setWriteNo(rset1.getInt("write_no"));
						c.setCommentsText(rset1.getString("comments_text"));
						c.setCommentsDate(rset1.getDate("comments_date"));
						c.setCommentsDeleteDate(rset1.getDate("comments_delete_date"));
						c.setCommentsDeleteYN(rset1.getString("comments_delete_yn").charAt(0));

						m.setMemberNo(rset1.getInt("member_No"));
						m.setMemberId(rset1.getString("member_Id"));
						m.setMemberPw(rset1.getString("member_Pw"));
						m.setMemberName(rset1.getString("member_Name"));
						m.setMemberNickname(rset1.getString("member_Nickname"));
						m.setMemberEmail(rset1.getString("member_Email"));
						m.setMemberPhone(rset1.getString("member_Phone"));

						CM.setC(c);
						CM.setM(m);
						list.add(CM);
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

	public String getPageNaviMember(Connection conn, int currentPage, int recordcountPerPage, int naviCountPerPage,
			String memberOpt, String keyword) {
		// 현재 페이지 값, 한페이지당 보여줄 글 수, 페이지 인덱스 번호
		int postTotalCount = commentsTotalCountMember(conn, keyword, memberOpt); // 전체 게시물 개수
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
			sb.append("<a href='/SearchComments.kh?commentsSelectOpt=" + memberOpt + "&keyword=" + keyword
					+ "&currentPage=" + (startNavi - 1) + "'><</a> ");
		}
		for (int i = startNavi; i <= endNavi; i++) {
			if (i == currentPage) { // 현재페이지에 <b> 추가
				sb.append("<a href='/SearchComments.kh?commentsSelectOpt=" + memberOpt + "&keyword=" + keyword
						+ "&currentPage=" + i + "'><b>" + i + "</b></a> ");

			} else {
				sb.append("<a href='/SearchComments.kh?commentsSelectOpt=" + memberOpt + "&keyword=" + keyword
						+ "&currentPage=" + i + "'>" + i + "</a> ");
			}
		}
		if (endNavi != pageTotalCount) {// 오른쪽 화살표
			sb.append("<a href='/SearchComments.kh?commentsSelectOpt=" + memberOpt + "&keyword=" + keyword
					+ "&currentPage=" + (endNavi + 1) + "'>></a> ");
		}
		// System.out.println(sb); //a태그 합친 코드
		return sb.toString();
	}

	public int commentsTotalCountMember(Connection conn, String keyword, String memberOpt) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int postTotalCount = 0;
		String query = "SELECT count(*) as TOTALCOUNT from (" + "WITH EX AS ( SELECT comment_no,member_no "
				+ "from comments left join member using (member_no) where " + memberOpt + " like ?) "
				+ "SELECT NUM,comment_no,member_no "
				+ "FROM (SELECT EX.*, ROW_NUMBER() OVER(ORDER BY EX.comment_no DESC) AS NUM FROM EX) )";
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

	public int CommentsDelete(Connection conn, int commentNo) {
		PreparedStatement pstmt = null;
		String query = "UPDATE comments SET comments_delete_date=sysdate, comments_delete_yn='Y' where comment_no=?";

		int result = 0;

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, commentNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

}
