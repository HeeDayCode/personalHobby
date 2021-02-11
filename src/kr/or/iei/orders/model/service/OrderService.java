package kr.or.iei.orders.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import kr.or.iei.common.JDBCTemplate;
import kr.or.iei.orders.model.dao.OrderDAO;
import kr.or.iei.orders.model.vo.OrderAll;
import kr.or.iei.orders.model.vo.OrderPageData;
import kr.or.iei.orders.model.vo.Orders;
import kr.or.iei.product.model.vo.ProductAllSub;

public class OrderService {
	OrderDAO oDAO = new OrderDAO();

	public OrderPageData selectAllOrderPage(int currentPage) {
		Connection conn = JDBCTemplate.getConnection();
		int recordCountPerPage = 10;
		ArrayList<OrderAll> list = oDAO.selectAllOrderPage(conn, currentPage, recordCountPerPage);
		int naviCountPerPage = 5;
		String pageNavi = oDAO.getPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage);
		int total = oDAO.orderTotalCount(conn);
		OrderPageData opd = new OrderPageData();
		opd.setList(list);
		opd.setPageNavi(pageNavi);
		opd.setTotal(total);
		JDBCTemplate.close(conn);
		return opd;
	}

	public int OrderDelivery(int orderNo) {
		Connection conn = JDBCTemplate.getConnection();
		int result = oDAO.CommentsDelete(conn, orderNo);
		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);

		return result;
	}

	public OrderPageData searchOrderPage(int currentPage, String Opt, String keyword) {
		Connection conn = JDBCTemplate.getConnection();
		int recordcountPerPage = 10;
		int naviCountPerPage = 5; // pageNavi 값이 몇개씩 보여줄 것인지 String pageNavi
		ArrayList<OrderAll> list = null;
		String pageNavi = "";
		int total = 0;

		list = oDAO.searchOrder(conn, currentPage, recordcountPerPage, Opt, keyword);
		pageNavi = oDAO.getPageNavi(conn, currentPage, recordcountPerPage, naviCountPerPage, Opt, keyword);
		total = oDAO.orderSearchTotalCount(conn, Opt, keyword);

		OrderPageData opd = new OrderPageData();
		opd.setList(list);
		opd.setPageNavi(pageNavi);
		opd.setTotal(total);
		JDBCTemplate.close(conn);
		return opd;
	}

	public ProductAllSub selectOneProduct(int productNo) {
		Connection conn = JDBCTemplate.getConnection();
		ProductAllSub productAS = oDAO.selectOneProduct(conn, productNo);
		JDBCTemplate.close(conn);
		return productAS;
	}

	public int insertOrder(Orders o) {
		Connection conn = JDBCTemplate.getConnection();
		int result = oDAO.insertOrder(conn, o);

		if (result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);

		return result;
	}
}
