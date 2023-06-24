package com.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.sql.DataSource;

import com.model.abstraction.HasGenericPK;
import com.model.datasource.UserDataSource;
import com.model.entity.User;
import com.model.entity.UserDetails;
import com.utils.Resources;

public class UserDetailsDAO implements HasGenericPK<User> {

	private DataSource dataSource;
	
	public UserDetailsDAO() {
		
		dataSource = UserDataSource.getInstance().getDataSource();
	
	}
	
	public ArrayList<UserDetails> getAllRecords() {
		
		ArrayList<UserDetails> userDetailsList = new ArrayList<UserDetails>();
		
		String sql = "SELECT * FROM user_details";
		
		Connection conn = null;
		Statement stm = null;
		
		ResultSet result = null;
		
		try{
			
			conn = dataSource.getConnection();
			stm = conn.createStatement();
			
			result = stm.executeQuery(sql);
			
			UserDAO userDao = new UserDAO();
			
			while (result.next()) {
				UserDetails userDetails = new UserDetails(
						userDao.getRecordByPK(result.getBigDecimal(1)), 
						result.getString(2), result.getString(3), result.getString(4));
				userDetailsList.add(userDetails);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}finally {
			Resources.freeRes(stm, conn, result);
		}
		
		return userDetailsList;
		
	}
	
	
	@Override
	public UserDetails getRecordByPK(User userPK) {
		
		String SELECT_USER_DETAILS_BY_PK = "SELECT * FROM user_details WHERE id = ?";
		
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement ps = null;
		
		try{
			
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(SELECT_USER_DETAILS_BY_PK);
			
			ps.setBigDecimal(1, userPK.getId());
			
			rs = ps.executeQuery();
			
			UserDetails userDetails = null;
			
			UserDAO userDao = new UserDAO();
			
			if(rs.next()) {
				
				userDetails = new UserDetails(
						userDao.getRecordByPK(rs.getBigDecimal(1)), 
						rs.getString(2),
						rs.getString(3), rs.getString(4));
			}
			
			return userDetails;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			Resources.freeRes(ps, conn, rs);
		}
	
		return null;
	}
}
