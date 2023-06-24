package com.model.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.model.datasource.UserDataSource;
import com.model.entity.Cart;
import com.model.entity.CartsItems;
import com.utils.Resources;

public class CartsItemsDAO {
	
private DataSource dataSource;
	
	public CartsItemsDAO() {
		
		dataSource = UserDataSource.getInstance().getDataSource();
	
	}
	
	public ArrayList<CartsItems> getAllRecords() {
		
		ArrayList<CartsItems> cartsItemsList = new ArrayList<CartsItems>();
		
		String sql = "SELECT * FROM carts_items";
		
		try{
			
			Connection conn = dataSource.getConnection();
			Statement stm = conn.createStatement();
			
			ResultSet result = stm.executeQuery(sql);

			CartDAO cartDao = new CartDAO();
			CommonWindowDAO cwindowDao = new CommonWindowDAO();
			
			while (result.next()) {
				
				CartsItems cartItem = new CartsItems(result.getBigDecimal(1),
						result.getInt(2),
						cartDao.getRecordByPK(result.getBigDecimal(3)),
						cwindowDao.getRecordByPK(result.getBigDecimal(4))
						);
				cartsItemsList.add(cartItem);
			}
			
			result.close();
			stm.close();
			conn.close();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		
		return cartsItemsList;
	
	}
	
	public ArrayList<CartsItems> getCartItemsByUserId(BigDecimal uid) {
		
		ArrayList<CartsItems> cartsItemsList = new ArrayList<CartsItems>();
		
		String sql = "select * from carts_items  "
				+ "join cart on cart.id = carts_items.id_cart "
				+ "join user_acc on user_acc.id = cart.id_user "
				+ "where user_acc.id = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet result = null;
		
		try{
			
			conn = dataSource.getConnection();
			pstm = conn.prepareStatement(sql);
			
			pstm.setBigDecimal(1, uid);
			result = pstm.executeQuery();

			CartDAO cartDao = new CartDAO();
			CommonWindowDAO cwindowDao = new CommonWindowDAO();
			
			while (result.next()) {
				
				CartsItems cartItem = new CartsItems(result.getBigDecimal(1),
						result.getInt(2),
						cartDao.getRecordByPK(result.getBigDecimal(3)),
						cwindowDao.getRecordByPK(result.getBigDecimal(4))
						);
				cartsItemsList.add(cartItem);
			}	
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}finally {
			Resources.freeRes(pstm, conn, result);
		}
		
		return cartsItemsList;
	}

	public int addWindowToCart(BigDecimal newWindowId, BigDecimal cartId) {
		
		int rowIns = 0;
		
		String sql = "insert into carts_items(amount, id_cart, id_window)"
				+ " values(1, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet result = null;
		
		try{
			
			conn = dataSource.getConnection();
			pstm = conn.prepareStatement(sql);
			
			pstm.setBigDecimal(1, cartId);
			pstm.setBigDecimal(2, newWindowId);
			
			rowIns += pstm.executeUpdate();	
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}finally {
			Resources.freeRes(pstm, conn, result);
		}
		
		return rowIns;
		
	}

	public int delWindowFromCart(BigDecimal windowId, BigDecimal cartId) {
		
		int rowDel = 0;
		
		String sql = "delete from carts_items where id_cart = ? and id_window = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet result = null;
		
		try{
			
			conn = dataSource.getConnection();
			pstm = conn.prepareStatement(sql);
			
			pstm.setBigDecimal(1, cartId);
			pstm.setBigDecimal(2, windowId);
			
			rowDel += pstm.executeUpdate();	
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}finally {
			Resources.freeRes(pstm, conn, result);
		}
		
		return rowDel;
	}
	
}
