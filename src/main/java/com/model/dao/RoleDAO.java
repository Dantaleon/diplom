package com.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.model.datasource.UserDataSource;
import com.model.entity.Role;

public class RoleDAO {
	
private DataSource dataSource;
	
	public RoleDAO() {
		
		dataSource = UserDataSource.getInstance().getDataSource();
	
	}
	
	public ArrayList<Role> getAllRecords() {
		
		ArrayList<Role> roleList = new ArrayList<Role>();
		
		String sql = "SELECT * FROM role";
		
		try{
			
			Connection conn = dataSource.getConnection();
			Statement stm = conn.createStatement();
			
			ResultSet result = stm.executeQuery(sql);
			
			while (result.next()) {
				Role role = new Role(result.getBigDecimal(1), result.getString(2));
				roleList.add(role);
			}
			
			result.close();
			stm.close();
			conn.close();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		
		return roleList;
		
	}
}
