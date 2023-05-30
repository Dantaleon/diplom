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
import com.model.entity.Cart;
import com.model.entity.CommonWindow;
import com.utils.Resources;

public class CartDAO implements HasGenericPK<BigDecimal>{
	
	private DataSource dataSource;
	
	public CartDAO() {
		
		dataSource = UserDataSource.getInstance().getDataSource();
	
	}
	
	public ArrayList<Cart> getAllRecords() {
		
		ArrayList<Cart> cartList = new ArrayList<Cart>();
		
		String sql = "SELECT * FROM cart";
		
		try{
			
			Connection conn = dataSource.getConnection();
			Statement stm = conn.createStatement();
			
			ResultSet result = stm.executeQuery(sql);

			UserDAO userDao = new UserDAO();
			
			while (result.next()) {
				
				Cart cart = new Cart(result.getBigDecimal(1), 
						userDao.getRecordByPK(result.getBigDecimal(2)),
						result.getDouble(3)
						);
				cartList.add(cart);
			}
			
			result.close();
			stm.close();
			conn.close();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		
		return cartList;
	
	}

	public Cart getRecordByPK(BigDecimal pkey) {
		
		String SELECT_CART_BY_PK = "SELECT * FROM cart WHERE id = ?";
		
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement ps = null;
		
		try{
			
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(SELECT_CART_BY_PK);
			
			ps.setBigDecimal(1, pkey);
			
			rs = ps.executeQuery();
			
			Cart cart = null;
			UserDAO userDao = new UserDAO();
			
			if(rs.next()) {
				
				cart = new Cart(rs.getBigDecimal(1), 
						userDao.getRecordByPK(rs.getBigDecimal(2)),
						rs.getDouble(3)
				);
			}
			
			return cart;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			Resources.freeRes(ps, conn, rs);
		}

		return null;
	}
}
