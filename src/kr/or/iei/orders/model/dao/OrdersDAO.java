package kr.or.iei.orders.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.or.iei.common.JDBCTemplate;
import kr.or.iei.orders.model.vo.Orders;
import kr.or.iei.orders.model.vo.OrdersAll;
import kr.or.iei.product.model.vo.Product;

public class OrdersDAO {

	public ArrayList<OrdersAll> selectOrdersAllList(Connection conn, int memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<OrdersAll> ordersList = new ArrayList<OrdersAll>();
		
		String query = "select * from orders left join product using (product_NO) where member_NO=?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Orders orders = new Orders();
				Product product = new Product();
				OrdersAll oAll = new OrdersAll(); 
				
				orders.setOrdersDate(rset.getDate("orders_Date"));
				orders.setOrdersNo(rset.getInt("orders_No"));
				orders.setOrdersPay(rset.getString("orders_Pay").charAt(0));
				
				product.setProductName(rset.getString("product_Name"));
				product.setProductPrice(rset.getInt("product_Price"));
				
				oAll.setOrders(orders);
				oAll.setProduct(product);
				ordersList.add(oAll);
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return ordersList;
		
	}

	public ArrayList<OrdersAll> selectOneWeek(Connection conn, int memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<OrdersAll> ordersList = new ArrayList<OrdersAll>();
		
		String query = "SELECT * FROM (select * from orders left join product using (product_no)) WHERE orders_date >= TO_CHAR(SYSDATE-7,'YYYYMMDD') and member_No=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Orders orders = new Orders();
				Product product = new Product();
				OrdersAll oAll = new OrdersAll();
				
				orders.setOrdersDate(rset.getDate("orders_Date"));
				orders.setOrdersNo(rset.getInt("orders_No"));
				orders.setOrdersPay(rset.getString("orders_Pay").charAt(0));
				
				product.setProductName(rset.getString("product_Name"));
				product.setProductPrice(rset.getInt("product_Price"));
				
				oAll.setOrders(orders);
				oAll.setProduct(product);
				ordersList.add(oAll);
				
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return ordersList;
		
		
		
	}

	public ArrayList<OrdersAll> selectOneMonth(Connection conn, int memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		ArrayList<OrdersAll> ordersList = new ArrayList<OrdersAll>();
		
		String query = "SELECT * FROM (select * from orders left join product using (product_no)) WHERE orders_date >= TO_CHAR(SYSDATE-30,'YYYYMMDD') and member_No=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memberNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Orders orders = new Orders();
				Product product = new Product();
				OrdersAll oAll = new OrdersAll();
				
				orders.setOrdersDate(rset.getDate("orders_Date"));
				orders.setOrdersNo(rset.getInt("orders_No"));
				orders.setOrdersPay(rset.getString("orders_Pay").charAt(0));
				
				product.setProductName(rset.getString("product_Name"));
				product.setProductPrice(rset.getInt("product_Price"));
				
				oAll.setOrders(orders);
				oAll.setProduct(product);
				ordersList.add(oAll);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return ordersList;
	}

}
