package kr.or.iei.question.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import kr.or.iei.common.JDBCTemplate;
import kr.or.iei.question.model.dao.QuestionDAO;
import kr.or.iei.question.model.vo.Answer;
import kr.or.iei.question.model.vo.Board;
import kr.or.iei.question.model.vo.BoardPageData;

public class QuestionService {
		QuestionDAO qDAO = new QuestionDAO();
	public int insertQuestion(int memberNo, String category, String title, String inputText) {
		Connection conn = JDBCTemplate.getConnection();
		int result = qDAO.insertQuestion(conn, memberNo, category, title, inputText);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}JDBCTemplate.close(conn);
		return result;
	}
	public ArrayList<Board> selectQuestionList() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Board> qList = qDAO.selectQuestionList(conn);
		JDBCTemplate.close(conn);
		return qList;
	}
	public int insertQuestion1(String productNo, int memberNo, String category, String title, String inputText) {
		Connection conn = JDBCTemplate.getConnection();
		String fromproductNo = productNo;
		int realProductNo = Integer.parseInt(fromproductNo);
				
		int result = qDAO.insertQuestion1(realProductNo, conn, memberNo, category, title, inputText);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}JDBCTemplate.close(conn);
		return result;
	}
	public ArrayList<Board> selectQuestionList1(int productNo) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Board> qList = qDAO.selectQuestionList1(conn,productNo);
		JDBCTemplate.close(conn);
		return qList;
	}
	public int delectQuestion(int boardNo) {
		Connection conn = JDBCTemplate.getConnection();
	      int result=qDAO.delectQuestion(conn, boardNo);	      
	      if(result>0) {
				JDBCTemplate.commit(conn);
			}else {
				JDBCTemplate.rollback(conn);
			}JDBCTemplate.close(conn);
			return result;	     
	}
	public int updateViewCount(int boardNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = qDAO.updateViewCount(conn, boardNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}JDBCTemplate.close(conn);
		
		return result;
		
	}
	public Board questionOneSelect(int boardNo) {
		Connection conn = JDBCTemplate.getConnection();
		Board board = qDAO.questionOneSelect(conn, boardNo);
		JDBCTemplate.close(conn);
		return board;
	}
	public Board selectQuestionListNo(int boardNo) {
		Connection conn = JDBCTemplate.getConnection();
		Board qBoard = qDAO.selectQuestionListNo(conn,boardNo);
		JDBCTemplate.close(conn);
		return qBoard;
	}
	public int updateQuestion(String subject, String content, int boardNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = qDAO.updateQuestion(conn,subject,content,boardNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}JDBCTemplate.close(conn);
		
		return result;
	}
	public ArrayList<Board> questionSearchTitleList(String keyword) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Board> qList = qDAO.questionSearchTitleList(conn, keyword);
		JDBCTemplate.close(conn);
		return qList;
	}
	public ArrayList<Board> questionSearchContentsList(String keyword) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Board> qList = qDAO.questionSearchContentsList(conn, keyword);
		JDBCTemplate.close(conn);
		return qList;
	}
	public ArrayList<Board> questionSearchWriterList(String keyword) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Board> qList = qDAO.questionSearchWriterList(conn, keyword);
		JDBCTemplate.close(conn);
		return qList;
	}
	public ArrayList<Board> questionSearchProductList(String keyword) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Board> qList = qDAO.questionSearchProductList(conn, keyword);
		JDBCTemplate.close(conn);
		return qList;
	}
	public ArrayList<Answer> answerSelect(int boardNo) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Answer> aList = qDAO.answerSelect(conn,boardNo);
		JDBCTemplate.close(conn);
		return aList;
		
	}
	public int insertAnswer(int boardNo, String answer) {
		Connection conn = JDBCTemplate.getConnection();
		int result = qDAO.insertAnswer(conn,boardNo,answer);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}JDBCTemplate.commit(conn);
		return result;
	}
	public int answerDelete(int boardNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = qDAO.answerDelete(conn,boardNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}JDBCTemplate.close(conn);
		
		return result;
		
	}
	public int answerUpdate(int boardNo, String re_answer) {
		Connection conn = JDBCTemplate.getConnection();
		int result = qDAO.answerUpdate(conn, boardNo, re_answer);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}JDBCTemplate.close(conn);
		
		return result;
		
	}
	public BoardPageData selectAllBoardPage(int currentPage) {
		Connection conn = JDBCTemplate.getConnection();
		int recordCountPerPage = 10; // 한 페이지당 몇 개의 게시물이 보이게 될 것인지
		ArrayList<Board>qList = qDAO.selectAllBoardPage(conn,currentPage,recordCountPerPage); //화면
		
		int naviCountPerPage = 5; //pageNavi 값이 몇 개씩 보여줄 것인지
		String pageNavi = qDAO.getPageNavi(conn,currentPage,recordCountPerPage,naviCountPerPage);//네비
		
		//리턴은 한번에 하나밖에 할 수 없기 때문에 2개의 데이터를 저장할 vo 객체가 필요
		BoardPageData bpd = new BoardPageData();
		bpd.setList(qList);
		bpd.setPageNavi(pageNavi);
		JDBCTemplate.close(conn);
		
		return bpd;
	}

}
