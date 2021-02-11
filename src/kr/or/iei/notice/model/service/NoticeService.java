package kr.or.iei.notice.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import kr.or.iei.common.JDBCTemplate;
import kr.or.iei.notice.model.dao.NoticeDAO;
import kr.or.iei.question.model.vo.Board;

public class NoticeService {
		NoticeDAO nDAO = new NoticeDAO();
		
	public int insertNotice(String category, String title, String inputText) {
		Connection conn = JDBCTemplate.getConnection();
		int result = nDAO.insertNotice(conn, category,title,inputText);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}JDBCTemplate.close(conn);
		
		return result;
	}

	public ArrayList<Board> selectNoticeList() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Board> nList = nDAO.selectNoticeList(conn);
		JDBCTemplate.close(conn);
		return nList;
	}

	public Board noticeOneSelect(int boardNo) {
		Connection conn = JDBCTemplate.getConnection();
		Board board = nDAO.noticeOneSelect(conn, boardNo);
		JDBCTemplate.close(conn);
		return board;
		
	}

	public int updateViewCount(int boardNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = nDAO.updateViewCount(conn, boardNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}JDBCTemplate.close(conn);
		
		return result;
	}

	public int deleteNoticeBoard(int boardNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = nDAO.deleteNoticeBoard(conn,boardNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
	}

	public Board selectNoticeListNo(int boardNo) {
		Connection conn = JDBCTemplate.getConnection();
		Board nBoard = nDAO.selectNoticeListNo(conn,boardNo);
		JDBCTemplate.close(conn);
		return nBoard;
	}

	public int updateNotice(String subject, String content, int boardNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = nDAO.updateNotice(conn, subject,content,boardNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}JDBCTemplate.close(conn);
		
		return result;
	}

	public ArrayList<Board> noticeSearchTitleList(String keyword) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Board> nList = nDAO.noticeSearchTitleList(conn, keyword);
		JDBCTemplate.close(conn);
		return nList;
	}

	public ArrayList<Board> noticeSearchContentsList(String keyword) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Board> nList = nDAO.noticeSearchContentsList(conn, keyword);
		JDBCTemplate.close(conn);
		return nList;
	}

}
