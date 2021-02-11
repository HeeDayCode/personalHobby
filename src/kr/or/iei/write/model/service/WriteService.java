package kr.or.iei.write.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import kr.or.iei.common.JDBCTemplate;
import kr.or.iei.img.model.vo.Img;
import kr.or.iei.product.model.vo.ProductAll;
import kr.or.iei.product.model.vo.ProductPageData;
import kr.or.iei.write.model.dao.WriteDAO;
import kr.or.iei.write.model.vo.CommentsAll;
import kr.or.iei.write.model.vo.WriteAll;
import kr.or.iei.write.model.vo.WriteImage;
import kr.or.iei.write.model.vo.WriteListAll;
import kr.or.iei.write.model.vo.WritePageData;

public class WriteService {
	WriteDAO writeDAO = new WriteDAO();
	WriteDAO wDAO = new WriteDAO();

	public ArrayList<WriteAll> indexRandom() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<WriteAll> list = writeDAO.indexRandom(conn);
		JDBCTemplate.close(conn);
		return list;
	}

	public ArrayList<WriteAll> indexBest() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<WriteAll> list = writeDAO.indexBest(conn);
		JDBCTemplate.close(conn);
		return list;
	}

	public ArrayList<WriteAll> indexNew() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<WriteAll> list = writeDAO.indexNew(conn);
		JDBCTemplate.close(conn);
		return list;
	}

	public WritePageData selectAllWritePage(int currentPage) {
		Connection conn = JDBCTemplate.getConnection();
		int recordCountPerPage = 10;
		ArrayList<WriteListAll> list = writeDAO.selectAllWritePage(conn, currentPage, recordCountPerPage);
		int naviCountPerPage = 5;
		String pageNavi = writeDAO.getPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage);
		int total = writeDAO.postTotalCount(conn);
		WritePageData wpd = new WritePageData();
		wpd.setList(list);
		wpd.setPageNavi(pageNavi);
		wpd.setTotalPage(total);
		JDBCTemplate.close(conn);
		return wpd;
	}

	public WritePageData searchOneWritePage(int currentPage, String postSelectOpt, String keyword) {
		Connection conn = JDBCTemplate.getConnection();
		int recordcountPerPage = 10;
		int naviCountPerPage = 5; // pageNavi 값이 몇개씩 보여줄 것인지 String pageNavi
		ArrayList<WriteListAll> list = null;
		String tagOpt = "";
		String explainOpt = "";
		String dateOpt = "";
		String pageNavi = "";
		int total = 0;
		if (postSelectOpt.equals("tag")) {
			tagOpt = postSelectOpt;
			list = writeDAO.searchOneWritePageTag(conn, currentPage, recordcountPerPage, tagOpt, keyword);
			pageNavi = writeDAO.getPageNaviTag(conn, currentPage, recordcountPerPage, naviCountPerPage, tagOpt,
					keyword);
			total = writeDAO.postTotalCountTag(conn, keyword);
		} else if (postSelectOpt.equals("write_explain")) {
			explainOpt = postSelectOpt;
			list = writeDAO.searchOneWritePageExplain(conn, currentPage, recordcountPerPage, explainOpt, keyword);
			pageNavi = writeDAO.getPageNaviExplain(conn, currentPage, recordcountPerPage, naviCountPerPage, explainOpt,
					keyword);
			total = writeDAO.postTotalCountExplain(conn, keyword);
		} else if (postSelectOpt.equals("member_Nickname") || postSelectOpt.equals("member_Id")) {
			list = writeDAO.searchOneWritePageM(conn, currentPage, recordcountPerPage, postSelectOpt, keyword);
			pageNavi = writeDAO.getPageNaviM(conn, currentPage, recordcountPerPage, naviCountPerPage, postSelectOpt,
					keyword);
			total = writeDAO.postTotalCountM(conn, keyword, postSelectOpt);
		} else if (postSelectOpt.equals("write_date")) {
			dateOpt = postSelectOpt;
			list = writeDAO.searchOneWritePageDate(conn, currentPage, recordcountPerPage, dateOpt, keyword);
			pageNavi = writeDAO.getPageNaviDate(conn, currentPage, recordcountPerPage, naviCountPerPage, dateOpt,
					keyword);
			total = writeDAO.postTotalCountDate(conn, keyword);
		}

		WritePageData wpd = new WritePageData();
		wpd.setList(list);
		wpd.setPageNavi(pageNavi);
		wpd.setTotalPage(total);
		JDBCTemplate.close(conn);
		return wpd;

	}

	public int writeDelete(int writeNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = writeDAO.writeDelete(conn, writeNo);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);

		return result;
	}

	public boolean insertCommunityWrite(int productNo, int memberNo, Img img, String content) {

		Connection conn = JDBCTemplate.getConnection();
		boolean result = wDAO.insertCommunityWrt(conn, productNo, memberNo, content, img);

		if (result == true) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}

		return result;
	}

	public ProductPageData selectProductInfo(String keyword, int currentPage) {
		Connection conn = JDBCTemplate.getConnection();
		int recordCountPerPage = 10;

		ArrayList<ProductAll> list = wDAO.selectProductInfo(conn, keyword, currentPage, recordCountPerPage);

		int naviCountPerPage = 5;
		String pageNavi = wDAO.getPageNavi(conn, keyword, currentPage, recordCountPerPage, naviCountPerPage);

		ProductPageData ppd = new ProductPageData();
		ppd.setList(list);
		ppd.setPageNavi(pageNavi);

		JDBCTemplate.close(conn);
		return ppd;

	}

	public int myHeartInsert(int userNo, int writeNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = writeDAO.myHeartInsert(conn, userNo, writeNo);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public WriteAll selectOneDetailPrint(int writeNo) {
		Connection conn = JDBCTemplate.getConnection();
		WriteAll wAll = writeDAO.selectOneDetailPrint(conn, writeNo);
		JDBCTemplate.close(conn);

		return wAll;
	}

	public ArrayList<CommentsAll> selectCommentwrite(int writeNo) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<CommentsAll> list = writeDAO.selectCommentwrite(conn, writeNo);
		JDBCTemplate.close(conn);
		return list;
	}

	public ArrayList<WriteAll> selectRelationProduct(int productNo) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<WriteAll> list = writeDAO.selectRelationProduct(conn, productNo);
		JDBCTemplate.close(conn);

		return list;

	}

	public int deletePost(int writeNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = writeDAO.deletePost(conn, writeNo);

		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int insertComment(String comments, int writeNo, int userNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = writeDAO.insertComment(conn, comments, writeNo, userNo);

		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int modifyComment(int commentNo, String content, int userNo, int writeNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = writeDAO.modifyComment(conn, commentNo, content, userNo, writeNo);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;

	}

	public int deleteComment(int commentNo, int userNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = writeDAO.deleteComment(conn, commentNo, userNo);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	
	public boolean updateCommunity(WriteAll wAll) {
		   Connection conn = JDBCTemplate.getConnection();
		   boolean result = writeDAO.updateCommunity(conn,wAll);
		     
		   if (result ==true) {
		            JDBCTemplate.commit(conn);
		         } else {
		            JDBCTemplate.rollback(conn);
		         }

		         return result;
		   
		}
		public WriteImage selectImg(int writeNo) {
		   Connection conn =JDBCTemplate.getConnection();
		   WriteImage wImg = writeDAO.selectImg(conn,writeNo);
		   return wImg;
		   
		}
}
