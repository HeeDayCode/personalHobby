package kr.or.iei.question.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.or.iei.common.JDBCTemplate;
import kr.or.iei.question.model.vo.Answer;
import kr.or.iei.question.model.vo.Board;

public class QuestionDAO {

	public int insertQuestion(Connection conn, int memberNo, String category, String title, String inputText) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO QUESTION VALUES (QUESTION_NO_SEQ.NEXTVAL, ?, '', " + 
				"(SELECT QUESTION_CATEGORY_NO FROM QUESTION_CATEGORY WHERE QUESTION_CATEGORY_NAME=?)," + 
				"?,0,SYSDATE,'','N',?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			pstmt.setString(2, category);
			pstmt.setString(3, inputText);
			pstmt.setString(4, title);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Board> selectQuestionList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Board> qList = new ArrayList<Board>();
		
		String query = "select question_no, product_no, product_name, question_category_name, question_name, " + 
				"question_text, member_nickname, question_view_count, question_date, QUESTION_DELETE_YN  " + 
				"from question left join question_category using (question_category_no) " + 
				"left join member using (member_no) left join product using (product_no) where QUESTION_DELETE_YN ='N' ORDER by question_no desc";

		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
				
	         while(rset.next()) {
	             Board qBoard = new Board();
	             qBoard.setboardNo(rset.getInt("question_no"));
	             qBoard.setProductNo(rset.getInt("product_no"));
	             qBoard.setProductName(rset.getString("product_name"));
	             qBoard.setboardCategoryName(rset.getString("question_category_name"));
	             qBoard.setboardName(rset.getString("question_name"));
	             qBoard.setboardText(rset.getString("question_text"));
	             qBoard.setuserNickname(rset.getString("member_nickname"));
	             qBoard.setboardViewCount(rset.getInt("question_view_count"));
	             qBoard.setboardDate(rset.getDate("question_date"));   
	             qList.add(qBoard);
	          }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return qList;
		
	}

	public int insertQuestion1(int realProductNo, Connection conn, int memberNo, String category, String title,
			String inputText) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO QUESTION VALUES (QUESTION_NO_SEQ.NEXTVAL, ?, ?, " + 
				"(SELECT QUESTION_CATEGORY_NO FROM QUESTION_CATEGORY WHERE QUESTION_CATEGORY_NAME=?)," + 
				"?,0,SYSDATE,'','N',?)";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			pstmt.setInt(2, realProductNo);
			pstmt.setString(3, category);
			pstmt.setString(4, inputText);
			pstmt.setString(5, title);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}	
		return result;
	}

	public ArrayList<Board> selectQuestionList1(Connection conn, int productNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Board> qList = new ArrayList<Board>();
		
		String query = "select question_no, product_no, product_name, question_category_name, question_name, "
				+ "question_text, member_nickname, question_view_count, question_date  "
				+ "from question left join question_category using (question_category_no) "
				+ "left join member using (member_no) left join product using (product_no) "
				+ "where product_no=?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, productNo);
			rset = pstmt.executeQuery();
				
	         while(rset.next()) {
	             Board qBoard = new Board();
	             qBoard.setboardNo(rset.getInt("question_no"));
	             qBoard.setProductNo(rset.getInt("product_no"));
	             qBoard.setProductName(rset.getString("product_name"));
	             qBoard.setboardCategoryName(rset.getString("question_category_name"));
	             qBoard.setboardName(rset.getString("question_name"));
	             qBoard.setboardText(rset.getString("question_text"));
	             qBoard.setuserNickname(rset.getString("member_nickname"));
	             qBoard.setboardViewCount(rset.getInt("question_view_count"));
	             qBoard.setboardDate(rset.getDate("question_date"));   
	             qList.add(qBoard);
	          }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return qList;
		
	}

	public int delectQuestion(Connection conn, int boardNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		
		String query= "UPDATE QUESTION SET QUESTION_DELETE_YN='Y', QUESTION_DELETE_DATE=sysdate WHERE QUESTION_NO=?";	
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

	public int updateViewCount(Connection conn, int boardNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE QUESTION SET QUESTION_VIEW_COUNT=QUESTION_VIEW_COUNT+1 WHERE QUESTION_NO=?";
		
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

	
	public Board questionOneSelect(Connection conn, int boardNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Board board = null;
		
		String query = "select question_no, product_no, product_name, question_category_name, question_name, " + 
				"question_text, member_nickname, question_view_count, question_date " + 
				"from question left join question_category using (question_category_no) " + 
				"left join member using (member_no) left join product using (product_no) "+
				"where question_no = ?";
		String query_view = "";
		// 조회수 증가 쿼리
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				board = new Board();
				board.setboardNo(rset.getInt("question_no"));
				board.setProductNo(rset.getInt("product_no"));
				board.setProductName(rset.getString("product_name"));
				board.setboardName(rset.getString("product_name"));
				board.setboardCategoryName(rset.getString("question_category_name"));
				board.setboardName(rset.getString("question_name"));
				board.setboardText(rset.getString("question_text"));
				board.setuserNickname(rset.getString("member_nickname"));
				board.setboardViewCount(rset.getInt("question_view_count")+1);
				board.setboardDate(rset.getDate("question_date"));	
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

	public Board selectQuestionListNo(Connection conn, int boardNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Board qBoard = new Board();
		
		String query = "select question_no, product_no, product_name, question_category_name, question_name, "
				+ "question_text, member_nickname, question_view_count, question_date, QUESTION_DELETE_YN  "
				+ "from question left join question_category using (question_category_no) "
				+ "left join member using (member_no) left join product using (product_no) "
				+ "where question_no =?";

		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			rset = pstmt.executeQuery();
				
	         while(rset.next()) {	             
	             qBoard.setboardNo(rset.getInt("question_no"));
	             qBoard.setProductNo(rset.getInt("product_no"));
	             qBoard.setProductName(rset.getString("product_name"));
	             qBoard.setboardCategoryName(rset.getString("question_category_name"));
	             qBoard.setboardName(rset.getString("question_name"));
	             qBoard.setboardText(rset.getString("question_text"));
	             qBoard.setuserNickname(rset.getString("member_nickname"));
	             qBoard.setboardViewCount(rset.getInt("question_view_count"));
	             qBoard.setboardDate(rset.getDate("question_date"));   	             
	          }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return qBoard;
		
	}

	public int updateQuestion(Connection conn, String subject, String content, int boardNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE QUESTION SET QUESTION_NAME=?, QUESTION_TEXT=?, QUESTION_DATE=sysdate  WHERE QUESTION_NO=?";
		
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

	public ArrayList<Board> questionSearchTitleList(Connection conn, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Board> qList = new ArrayList<Board>();
		
		String query = "SELECT QUESTION_NO, PRODUCT_NO, PRODUCT_NAME, PRODUCT_NO, QUESTION_CATEGORY_NAME, QUESTION_NAME, " + 
				"QUESTION_TEXT, MEMBER_NICKNAME, QUESTION_VIEW_COUNT, QUESTION_DATE " + 
				"FROM QUESTION LEFT JOIN QUESTION_CATEGORY USING (QUESTION_CATEGORY_NO) " + 
				"LEFT JOIN MEMBER USING (MEMBER_NO) LEFT JOIN PRODUCT USING (PRODUCT_NO)" +
				" WHERE QUESTION_NAME LIKE ? AND QUESTION_DELETE_YN='N'";
		
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, "%"+keyword+"%");
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					Board board = new Board();
					board.setboardNo(rset.getInt("question_no"));
					board.setProductNo(rset.getInt("product_no"));
					board.setProductName(rset.getString("product_name"));
					board.setboardCategoryName(rset.getString("question_category_name"));
					board.setboardName(rset.getString("question_name"));
					board.setboardText(rset.getString("question_text"));
					board.setuserNickname(rset.getString("member_nickname"));
					board.setboardViewCount(rset.getInt("question_view_count"));
					board.setboardDate(rset.getDate("question_date"));	
					qList.add(board);
					}
				} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}finally {
					JDBCTemplate.close(rset);
					JDBCTemplate.close(pstmt);
				}
					return qList;
	}

	public ArrayList<Board> questionSearchContentsList(Connection conn, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Board> qList = new ArrayList<Board>();
		
		String query = "SELECT QUESTION_NO, PRODUCT_NO, PRODUCT_NAME, PRODUCT_NO, QUESTION_CATEGORY_NAME, QUESTION_NAME, " + 
				"QUESTION_TEXT, MEMBER_NICKNAME, QUESTION_VIEW_COUNT, QUESTION_DATE " + 
				"FROM QUESTION LEFT JOIN QUESTION_CATEGORY USING (QUESTION_CATEGORY_NO) " +  
				"LEFT JOIN MEMBER USING (MEMBER_NO) LEFT JOIN PRODUCT USING (PRODUCT_NO)" +
				" WHERE QUESTION_TEXT LIKE ? AND QUESTION_DELETE_YN='N'";
		
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, "%"+keyword+"%");
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					Board board = new Board();
					board.setboardNo(rset.getInt("question_no"));
					board.setProductNo(rset.getInt("product_no"));
					board.setProductName(rset.getString("product_name"));
					board.setboardCategoryName(rset.getString("question_category_name"));
					board.setboardName(rset.getString("question_name"));
					board.setboardText(rset.getString("question_text"));
					board.setuserNickname(rset.getString("member_nickname"));
					board.setboardViewCount(rset.getInt("question_view_count"));
					board.setboardDate(rset.getDate("question_date"));	
					qList.add(board);
					}
				} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}finally {
					JDBCTemplate.close(rset);
					JDBCTemplate.close(pstmt);
				}
					return qList;
	}

	public ArrayList<Board> questionSearchWriterList(Connection conn, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Board> qList = new ArrayList<Board>();
		
		String query = "SELECT QUESTION_NO, PRODUCT_NO, PRODUCT_NAME, PRODUCT_NO, QUESTION_CATEGORY_NAME, QUESTION_NAME, " + 
				"QUESTION_TEXT, MEMBER_NICKNAME, QUESTION_VIEW_COUNT, QUESTION_DATE " + 
				"FROM QUESTION LEFT JOIN QUESTION_CATEGORY USING (QUESTION_CATEGORY_NO) " +  
				"LEFT JOIN MEMBER USING (MEMBER_NO) LEFT JOIN PRODUCT USING (PRODUCT_NO)" +
				" WHERE MEMBER_NICKNAME LIKE ? AND QUESTION_DELETE_YN='N'";
		
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, "%"+keyword+"%");
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					Board board = new Board();
					board.setboardNo(rset.getInt("question_no"));
					board.setProductNo(rset.getInt("product_no"));
					board.setProductName(rset.getString("product_name"));
					board.setboardCategoryName(rset.getString("question_category_name"));
					board.setboardName(rset.getString("question_name"));
					board.setboardText(rset.getString("question_text"));
					board.setuserNickname(rset.getString("member_nickname"));
					board.setboardViewCount(rset.getInt("question_view_count"));
					board.setboardDate(rset.getDate("question_date"));	
					qList.add(board);
					}
				} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}finally {
					JDBCTemplate.close(rset);
					JDBCTemplate.close(pstmt);
				}
					return qList;
	}

	public ArrayList<Board> questionSearchProductList(Connection conn, String keyword) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Board> qList = new ArrayList<Board>();
		
		String query = "SELECT QUESTION_NO, PRODUCT_NO, PRODUCT_NAME, PRODUCT_NO, QUESTION_CATEGORY_NAME, QUESTION_NAME, " + 
				"QUESTION_TEXT, MEMBER_NICKNAME, QUESTION_VIEW_COUNT, QUESTION_DATE " + 
				"FROM QUESTION LEFT JOIN QUESTION_CATEGORY USING (QUESTION_CATEGORY_NO) " +  
				"LEFT JOIN MEMBER USING (MEMBER_NO) LEFT JOIN PRODUCT USING (PRODUCT_NO)" +
				" WHERE PRODUCT_NAME LIKE ? AND QUESTION_DELETE_YN='N'";
		
			try {
				pstmt = conn.prepareStatement(query);
				pstmt.setString(1, "%"+keyword+"%");
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					Board board = new Board();
					board.setboardNo(rset.getInt("question_no"));
					board.setProductNo(rset.getInt("product_no"));
					board.setProductName(rset.getString("product_name"));
					board.setboardCategoryName(rset.getString("question_category_name"));
					board.setboardName(rset.getString("question_name"));
					board.setboardText(rset.getString("question_text"));
					board.setuserNickname(rset.getString("member_nickname"));
					board.setboardViewCount(rset.getInt("question_view_count"));
					board.setboardDate(rset.getDate("question_date"));	
					qList.add(board);
					}
				} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}finally {
					JDBCTemplate.close(rset);
					JDBCTemplate.close(pstmt);
				}
					return qList;
	}

	public ArrayList<Answer> answerSelect(Connection conn, int boardNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<Answer> aList = new ArrayList<Answer>();
		
		String query="SELECT * FROM ANSWER WHERE QUESTION_NO=? AND ANSWER_DELETE_YN='N'";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Answer answer = new Answer();
				answer.setQuestionNo(rset.getInt("question_no"));
				answer.setAnswerText(rset.getString("answer_text"));
				answer.setAnswerDate(rset.getDate("answer_date"));
				aList.add(answer);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return aList;
	}

	public int answerDelete(Connection conn, int boardNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE ANSWER SET ANSWER_DELETE_YN='Y', ANSWER_DELETE_DATE=SYSDATE WHERE QUESTION_NO = ?";
		
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

	public int answerUpdate(Connection conn, int boardNo, String re_answer) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "UPDATE ANSWER SET ANSWER_TEXT=? WHERE QUESTION_NO=? AND ANSWER_DELETE_YN='N'";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, re_answer);
			pstmt.setInt(2, boardNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
		
		
	}
	public int insertAnswer(Connection conn, int boardNo, String answer) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "INSERT INTO ANSWER VALUES (?,?,SYSDATE,'','N')";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, boardNo);
			pstmt.setString(2, answer);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Board> selectAllBoardPage(Connection conn, int currentPage, int recordCountPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<Board> qList = new ArrayList<Board>();
		
		int start = currentPage * recordCountPerPage - (recordCountPerPage-1);
		int end = currentPage * recordCountPerPage;
		
		String query = "select * from (SELECT ROW_NUMBER() OVER (order by question.question_no desc) AS ROW_NUM, " + 
				"question.question_no, question.product_no, product.product_name, question_category.question_category_name, question.question_name, " + 
				"question.question_text, member.member_nickname, question.question_view_count, question.question_date " + 
				"from question " + 
				"left join question_category on (question.question_category_no =question_category.question_category_no) " + 
				"left join member on (question.member_no = member.member_no) " + 
				"left join product on (question.product_no = product.product_no) " + 
				"where question_delete_yn='N') " + 
				"WHERE ROW_NUM BETWEEN ? AND ? ";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Board board = new Board();
				board.setboardNo(rset.getInt("question_no"));
				board.setProductNo(rset.getInt("product_no"));
				board.setProductName(rset.getString("product_name"));
				board.setboardName(rset.getString("question_name"));
				board.setboardCategoryName(rset.getString("question_category_name"));
				board.setboardName(rset.getString("question_name"));
				board.setboardText(rset.getString("question_text"));
				board.setuserNickname(rset.getString("member_nickname"));
				board.setboardViewCount(rset.getInt("question_view_count"));
				board.setboardDate(rset.getDate("question_date"));	
				
				qList.add(board);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return qList;
	}

	public String getPageNavi(Connection conn, int currentPage, int recordCountPerPage, int naviCountPerPage) {
		int postTotalCount = postTotalCount(conn); // 전체 게시물의 개수를 구하기 위한 메소드
		
		int pageTotalCount; //전체 페이지를 저장하는 변수
		if(postTotalCount % recordCountPerPage > 0) {
			pageTotalCount = postTotalCount / recordCountPerPage + 1;
		}else {
			pageTotalCount = postTotalCount / recordCountPerPage + 0;
		}
		// startNavi(Navi시작하는페이지) = ((현재페이지-1)/보여질 navi 개수) * 보여질 navi 개수 + 1
		int startNavi = ((currentPage-1) / naviCountPerPage) * naviCountPerPage + 1; 
		
		int endNavi = startNavi + naviCountPerPage-1;
		
		if(endNavi > pageTotalCount) {
			endNavi = pageTotalCount;
		}
		
		// pageNavi의 모양을 구성
		StringBuilder sb = new StringBuilder();
		
		// 만약 첫번째 pageNavi가 아니라면 '<'모양을 추가
		if(startNavi!=1) {
			sb.append("<a href='/question.kh?currentPage="+(startNavi-1)+"'><</a> ");
		}
		
		for(int i=startNavi; i<=endNavi; i++) {
			if(i==currentPage) {
				sb.append("<a href='/question.kh?currentPage="+i+"'><b>"+i+"</b></a> ");
			}else {
				sb.append("<a href='/question.kh?currentPage="+i+"'>"+i+"</a> ");
			}
		}
		
		// 만약 마지막 pageNavi가 아니라면 '>'모양을 추가
		if(endNavi != pageTotalCount) {
			sb.append("<a href='/question.kh?currentPage="+(endNavi+1)+"'>></a> ");
		}
		
		return sb.toString();
	}

	private int postTotalCount(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int postTotalCount = 0;
		
		String query = "SELECT COUNT(*) AS TOTALCOUNT FROM QUESTION WHERE QUESTION_DELETE_YN='N'";
		
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			rset.next();
			postTotalCount = rset.getInt("totalCount");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return postTotalCount;
	}

	
}
