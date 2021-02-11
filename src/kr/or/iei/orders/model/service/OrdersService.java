package kr.or.iei.orders.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import kr.or.iei.common.JDBCTemplate;
import kr.or.iei.orders.model.dao.OrdersDAO;
import kr.or.iei.orders.model.vo.OrdersAll;

public class OrdersService {
	OrdersDAO oDAO = new OrdersDAO();
	public ArrayList<OrdersAll> selectOrdersAllList(int memberNo) {
		
	Connection conn = JDBCTemplate.getConnection();
	ArrayList<OrdersAll> ordersList = oDAO.selectOrdersAllList(conn, memberNo);
	JDBCTemplate.close(conn);
	return ordersList;
	}

	public ArrayList<OrdersAll> selectOneWeek(int memberNo) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<OrdersAll> orderList = oDAO.selectOneWeek(conn, memberNo);
		JDBCTemplate.close(conn);
		return orderList;
		
	}

	public ArrayList<OrdersAll> selectOneMonth(int memberNo) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<OrdersAll> orderList = oDAO.selectOneMonth(conn, memberNo);
		JDBCTemplate.close(conn);
		return orderList;
		
		
	}

	public ArrayList<OrdersAll> selectThreeMonth(int memberNo) {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<OrdersAll> orderList = oDAO.selectOneMonth(conn, memberNo);
		JDBCTemplate.close(conn);
		return orderList;
	}

	

}
