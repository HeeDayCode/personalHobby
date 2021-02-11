package kr.or.iei.notice.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.or.iei.common.JDBCTemplate;
import kr.or.iei.question.model.vo.Board;

public class NoticeDAO {

	public int insertNotice(Connection conn, String category, String title, String inputText) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO NOTICE VALUES (NOTICE_NO_SEQ.NEXTVAL, " + 
				"(SELECT NOTICE_CATEGORY_NO FROM NOTICE_CATEGORY WHERE NOTICE_CATEGORY_NAME=?)," + 
				"?,0,SYSDATE,'','N',?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, category);
			pstmt.setString(2, inputText);
			pstmt.setString(3, title);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
		
	}

	public ArrayList<Board> selectNoticeList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Board> nList = new ArrayList<Board>();
		
		String query = "select notice_no, notice_category_name, notice_name," + 
				"notice_text, notice_view_count, notice_date, NOTICE_DELETE_YN from notice " + 
				"left join notice_category using (notice_category_no) where NOTICE_DELETE_YN='N' order by notice_no desc";
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Board board = new Board();
				board.setboardNo(rset.getInt("notice_no"));
				board.setboardCategoryName(rset.getString("notice_category_name"));
				board.setboardName(rset.getString("notice_name"));
				board.setboardText(rset.getString("notice_text"));
				board.setboardViewCount(rset.getInt("notice_view_count"));
				board.setboardDate(rset.getDate("notice_date"));
				nList.add(board);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return nList;
	}

	public Board noticeOneSelect(Connection conn, int boardNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Board board = null;
		String query = "select notice_no, notice_category_name, notice_name," + 
				"notice_text, notice_view_count, notice_date from notice " + 
				"left join notice_category using (notice_category_no) "
				+"where notice_no = ?";
		String query_view = ""; 
		// 조회수 증가 쿼리
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				board = new Board();
				board.setboardNo(rset.getInt("notice_no"));
				board.setboardCategoryName(rset.getString("notice_category_name"));
				board.setboardName(rset.getString("notice_name"));
				board.setboardText(rset.getString("notice_text"));
				board.setboardViewCount(rset.getInt("notice_view_count")+1);
				board.setboardDate(rset.getDate("notice_date"));	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return board;
	}

	public int updateViewCount(Connection conn, int boardNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query= "UPDATE NOTICE SET NOTICE_VIEW_COUNT = NOTICE_VIEW_COUNT+1 WHERE NOTICE_NO=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int deleteNoticeBoard(Connection conn, int boardNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query= "UPDATE NOTICE SET NOTICE_DELETE_YN='Y', NOTICE_DELETE_DATE=sysdate WHERE NOTICE_NO=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public Board selectNoticeListNo(Connection conn, int boardNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Board board = new Board();
		
		
		String query = "select notice_no, notice_category_name, notice_name, "
				+ "notice_text, notice_view_count, notice_date, NOTICE_DELETE_YN from notice "
				+ "left join notice_category using (notice_category_no) where NOTICE_NO=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {				
				board.setboardNo(rset.getInt("notice_no"));
				board.setboardCategoryName(rset.getString("notice_category_name"));
				board.setboardName(rset.getString("notice_name"));
				board.setboardText(rset.getString("notice_text"));
				board.setboardViewCount(rset.getInt("notice_view_count"));
				board.setboardDate(rset.getDate("notice_date"));				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return board;
	}

	public int updateNotice(Connection conn, String subject, String content, int boardNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE NOTICE SET NOTICE_NAME=?, NOTICE_TEXT=?, NOTICE_DATE=sysdate  WHERE NOTICE_NO=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, subject);
			pstmt.setString(2, content);
			pstmt.setInt(3, boardNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
		
	}

	public ArrayList<Board> noticeSearchTitleList(Connection conn, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Board> nList = new ArrayList<Board>();
		
		String query = "SELECT NOTICE_NO, NOTICE_CATEGORY_NAME, NOTICE_NAME, NOTICE_TEXT, " + 
				"NOTICE_VIEW_COUNT, NOTICE_DATE " + 
				"FROM NOTICE LEFT JOIN NOTICE_CATEGORY USING (NOTICE_CATEGORY_NO) " + 
				"WHERE NOTICE_NAME LIKE ? AND NOTICE_DELETE_YN='N'";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+keyword+"%");
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Board board = new Board();
				board.setboardNo(rset.getInt("notice_no"));
				board.setboardCategoryName(rset.getString("notice_category_name"));
				board.setboardName(rset.getString("notice_name"));
				board.setboardText(rset.getString("notice_text"));
				board.setboardViewCount(rset.getInt("notice_view_count"));
				board.setboardDate(rset.getDate("notice_date"));
				nList.add(board);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return nList;
	}

	public ArrayList<Board> noticeSearchContentsList(Connection conn, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Board> nList = new ArrayList<Board>();
		
		String query = "SELECT NOTICE_NO, NOTICE_CATEGORY_NAME, NOTICE_NAME, NOTICE_TEXT, " + 
				"NOTICE_VIEW_COUNT, NOTICE_DATE " + 
				"FROM NOTICE LEFT JOIN NOTICE_CATEGORY USING (NOTICE_CATEGORY_NO) " + 
				"WHERE NOTICE_TEXT LIKE ? AND NOTICE_DELETE_YN='N'";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "%"+keyword+"%");
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Board board = new Board();
				board.setboardNo(rset.getInt("notice_no"));
				board.setboardCategoryName(rset.getString("notice_category_name"));
				board.setboardName(rset.getString("notice_name"));
				board.setboardText(rset.getString("notice_text"));
				board.setboardViewCount(rset.getInt("notice_view_count"));
				board.setboardDate(rset.getDate("notice_date"));
				nList.add(board);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return nList;
	}

}
