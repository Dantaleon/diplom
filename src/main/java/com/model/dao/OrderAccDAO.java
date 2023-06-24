package com.model.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.model.abstraction.HasGenericPK;
import com.model.datasource.UserDataSource;
import com.model.entity.CartsItems;
import com.model.entity.OrderAcc;
import com.utils.Resources;

public class OrderAccDAO implements HasGenericPK<BigDecimal> {

	private DataSource dataSource;

	public OrderAccDAO() {

		dataSource = UserDataSource.getInstance().getDataSource();

	}

	public ArrayList<OrderAcc> getAllRecords() {

		ArrayList<OrderAcc> orderAccList = new ArrayList<OrderAcc>();

		String sql = "SELECT * FROM order_acc";

		Connection conn = null;
		Statement stm = null;
		ResultSet result = null;

		try {

			conn = dataSource.getConnection();
			stm = conn.createStatement();

			result = stm.executeQuery(sql);

			PaymentVarDAO paymentVarDao = new PaymentVarDAO();
			UserDAO userDao = new UserDAO();
			OrderStatusDAO orderStatusDao = new OrderStatusDAO();

			while (result.next()) {
				OrderAcc orderAcc = new OrderAcc(result.getBigDecimal(1), result.getDouble(2),
						Resources.timestampToString(result.getTimestamp(3)),
						Resources.timestampToString(result.getTimestamp(4)),
						paymentVarDao.getRecordByPK(result.getBigDecimal(5)),
						userDao.getRecordByPK(result.getBigDecimal(6)),
						orderStatusDao.getRecordByPK(result.getBigDecimal(7)));

				orderAccList.add(orderAcc);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}finally {
			Resources.freeRes(stm, conn, result);
		}
		
		return orderAccList;
		
	}
	
	
	@Override
	public OrderAcc getRecordByPK(BigDecimal pkey) {
		
		String SELECT_ORDER_ACC_BY_PK = "SELECT * FROM order_acc WHERE id = ?";
		
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement ps = null;
		
		try{
			
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(SELECT_ORDER_ACC_BY_PK);
			
			ps.setBigDecimal(1, pkey);
			
			rs = ps.executeQuery();
			
			OrderAcc orderAcc = null;
			
			PaymentVarDAO paymentVarDao = new PaymentVarDAO();
			UserDAO userDao = new UserDAO();
			OrderStatusDAO orderStatusDao = new OrderStatusDAO();
			
			if(rs.next()) {
				
				orderAcc = new OrderAcc(rs.getBigDecimal(1),rs.getDouble(2),
						Resources.timestampToString(rs.getTimestamp(3)),
						Resources.timestampToString(rs.getTimestamp(4)),
						paymentVarDao.getRecordByPK(rs.getBigDecimal(5)),
						userDao.getRecordByPK(rs.getBigDecimal(6)),
						orderStatusDao.getRecordByPK(rs.getBigDecimal(7))
						
				
				);
			}
			
			return orderAcc;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			Resources.freeRes(ps, conn, rs);
		}
	
		return null;
	}
	
	
	public ArrayList<OrderAcc> getAllRecordsByUserId(BigDecimal uid) {
		
		ArrayList<OrderAcc> orderAccList = new ArrayList<OrderAcc>();
		
		String sql = "select * from order_acc where id_user = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet result = null;
		
		try{
			
			conn = dataSource.getConnection();
			pstm = conn.prepareStatement(sql);
			
			pstm.setBigDecimal(1, uid);
			
			result = pstm.executeQuery();
			
			PaymentVarDAO paymentVarDao = new PaymentVarDAO();
			UserDAO userDao = new UserDAO();
			OrderStatusDAO orderStatusDao = new OrderStatusDAO();
			
			while (result.next()) {
				OrderAcc orderAcc = new OrderAcc(result.getBigDecimal(1), 
						result.getDouble(2),
						Resources.timestampToString(result.getTimestamp(3)),
						Resources.timestampToString(result.getTimestamp(4)),
						paymentVarDao.getRecordByPK(result.getBigDecimal(5)),
						userDao.getRecordByPK(result.getBigDecimal(6)),
						orderStatusDao.getRecordByPK(result.getBigDecimal(7))
				);
				
				orderAccList.add(orderAcc);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}finally {
			Resources.freeRes(pstm, conn, result);
		}
		
		return orderAccList;
		
	}

	public boolean transferCartToOrder(BigDecimal uid, OrderAcc orderAcc) {
		
		boolean isOk = false;
		
		String INSERT_ORDER_ACC = "insert into order_acc(sum_cost, date_oform, date_deliver, "
				+ "id_payment_var, id_user, id_status) "
				+ "values(?, ?, ?, "
				+ "?, ?, ?) returning id";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try {
			
			conn = dataSource.getConnection();
			pstm = conn.prepareStatement(INSERT_ORDER_ACC);
			
			System.out.println(orderAcc.getSum_cost());
			System.out.println(Resources.stringToTimestamp(orderAcc.getDateOform()));
			System.out.println(Resources.stringToTimestamp(orderAcc.getDateDeliver()));
			System.out.println(orderAcc.getPaymentVar().getId());
			System.out.println(uid);
			System.out.println(orderAcc.getOrderStatus().getId());
			
			pstm.setDouble(1, orderAcc.getSum_cost());
			pstm.setTimestamp(2, Resources.stringToTimestamp(orderAcc.getDateOform()));
			pstm.setTimestamp(3, Resources.stringToTimestamp(orderAcc.getDateDeliver()));
			pstm.setBigDecimal(4, orderAcc.getPaymentVar().getId());
			pstm.setBigDecimal(5, uid);
			pstm.setBigDecimal(6, orderAcc.getOrderStatus().getId());
			
			rs = pstm.executeQuery();
			
			BigDecimal oid = null;
			
			if (rs.next()) {
				oid = rs.getBigDecimal(1);
			}
			
			if (oid == null) {
				System.out.println("oid is null oacdao");
			}
			
			pstm.close();
			
			// id_order, user_id, user_id;
			String TRANSFER_TO_ORDER = "INSERT INTO orders_items(amount, id_order, id_window) "
					+ "SELECT carts_items.amount, ?, carts_items.id_window "
					+ "FROM carts_items "
					+ "INNER JOIN cart ON cart.id = carts_items.id_cart "
					+ "WHERE cart.id_user = ?; "
					+ "DELETE FROM carts_items "
					+ "USING cart "
					+ "WHERE cart.id = carts_items.id_cart "
					+ "AND cart.id_user = ?;";
			
			pstm = conn.prepareStatement(TRANSFER_TO_ORDER);
			
			pstm.setBigDecimal(1, oid);
			pstm.setBigDecimal(2, uid);
			pstm.setBigDecimal(3, uid);
			
			pstm.executeUpdate();
			
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}finally {
			Resources.freeRes(pstm, conn);
		}

		return isOk;
	}
}
