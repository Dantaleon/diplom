package com.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.model.datasource.UserDataSource;
import com.model.entity.CartsItems;

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
}
