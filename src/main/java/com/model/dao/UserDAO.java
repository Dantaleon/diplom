package com.model.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONObject;
import org.postgresql.util.PSQLException;

import com.model.abstraction.HasGenericPK;
import com.model.datasource.AdminDataSource;
import com.model.datasource.UserDataSource;
import com.model.entity.Role;
import com.model.entity.User;

public class UserDAO implements HasGenericPK<BigDecimal> {
	
	private DataSource dataSource;
	
	public UserDAO() {
		
		dataSource = UserDataSource.getInstance().getDataSource();
	}
	
	
	 // Function returns number of column inserted in DB
	public int registerUser(User user) {	
		int result = 0;
		
		//String INSERT_USER_ACC_SQL = 
//				"INSERT INTO User_acc(nickname, email, password)" +
//				"VALUES(?, ?, ?)";
		String INSERT_USER_ACC_SQL = 
				"DELETE FROM user_acc WHERE email = ? AND password = ?";
		try{
			ResultSet rs = null;
			
			Connection conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(INSERT_USER_ACC_SQL);
			
			//ps.setString(1, user.getNickname());
			//ps.setString(2, user.getEmail());
			//ps.setString(3, user.getPassword());
			ps.setString(1, "testn1@mail.ru");
			ps.setString(2, "test1");
			
			System.out.println(ps);
			
			result = ps.executeUpdate();
			
			ps.close();
			conn.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	public boolean isUserEmailExist(String email)  {
		
		int rows = 0;
		boolean result = false;
		
		String CHECK_USER_FIELD = "SELECT COUNT(id) FROM user_acc WHERE email = ?";
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		try(Connection connection = DriverManager.getConnection
				(
					"jdbc:postgresql://localhost/windows", "admin", "admin1");
					PreparedStatement ps2 = connection.prepareStatement(CHECK_USER_FIELD, 
							ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				)
		{
				ps2.setString(1, email);
				
				System.out.println(ps2);
				
				ResultSet rs = ps2.executeQuery();
				
				rs.last();
				rows = rs.getInt(1);
				rs.beforeFirst();
				
				System.out.println(rows);
				
				if (rows == 1) {
					result = true;
				} else {
					
				}
			
		}catch(SQLException e) {
			result = false;
			e.printStackTrace();
		}
		
		return result;
	}
	
	public BigDecimal getUserIdByEmail(String email) {
		
		BigDecimal result = BigDecimal.ZERO;
		
		String CHECK_USER_FIELD = "SELECT id FROM user_acc WHERE email = ?";
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		try(Connection connection = DriverManager.getConnection
				(
					"jdbc:postgresql://localhost/windows", "admin", "admin1");
					PreparedStatement ps2 = connection.prepareStatement(CHECK_USER_FIELD, 
							ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				)
		{
				ps2.setString(1, email);
				
				System.out.println(ps2);
				
				ResultSet rs = ps2.executeQuery();
				
				rs.last();
				result = rs.getBigDecimal(1);
				rs.beforeFirst();
				
				System.out.println(result);
				
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public boolean giveRoleToUser(Role role, User user) {
		boolean result = false;
		
		
		
		return result;
	}
	
	public boolean checkUserOnLogin(String email, String password) {
		boolean result = false;
		
		String CHECK_USER_LOGIN = "SELECT * FROM user_acc WHERE email = ? AND password = ?";
		
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		try(Connection connection = DriverManager.getConnection
				(
					"jdbc:postgresql://localhost/windows", "admin", "admin1");
					PreparedStatement ps = connection.prepareStatement(CHECK_USER_LOGIN, 
							ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				)
		{
			
			ps.setString(1, email);
			ps.setString(2, password);
			
			System.out.println(ps);
			
			ResultSet rs = ps.executeQuery();
			
			
			System.out.println(rs.toString());
			
			if (rs.next()) {
				result = true;
			} else {
				
			}
			
		}catch(SQLException e) {
			result = false;
			e.printStackTrace();
		}
		
		return result;
	}


	@Override
	public User getRecordByPK(BigDecimal pkey) {

		String SELECT_USER_BY_PK = "SELECT * FROM user_acc WHERE id = ?";
		
		try{
			ResultSet rs = null;
			
			Connection conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(SELECT_USER_BY_PK);
			
			ps.setBigDecimal(1, pkey);
			
			rs = ps.executeQuery();
			
			User user = null;
			
			if(rs.next()) {
				
				user = new User(rs.getBigDecimal("id"),
						rs.getString("nickname"),
						rs.getString("email"),
						rs.getString("password"));
			}
			
			ps.close();
			rs.close();
			conn.close();
			
			return user;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			
		}
		
		return null;
	}


	public ArrayList<User> getAllRecords() {
		
		String SELECT_ALL_USERS = "SELECT * FROM user_acc";
		
		try{
			ResultSet rs = null;
			
			Connection conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(SELECT_ALL_USERS);
			
			rs = ps.executeQuery();
			
			ArrayList<User> users = new ArrayList<User>();
			
			while(rs.next()) {
				
				User user = new User(rs.getBigDecimal("id"),
						rs.getString("nickname"),
						rs.getString("email"),
						rs.getString("password"));
				
				users.add(user);
			}
			
			ps.close();
			rs.close();
			conn.close();
			
			return users;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			
		}
		
		
		return null;
	}
	
	public String getAllRecordsJSON(ArrayList<User> userList) {
		
		JSONArray jsonArray = new JSONArray();

	    for (User user : userList) {
	    	
	        JSONObject jsonObject = new JSONObject();
	        jsonObject.put("id", user.getId());
	        jsonObject.put("nickname", user.getNickname());
	        jsonObject.put("email", user.getEmail());
	        jsonObject.put("password", user.getPassword());
	        
	        jsonArray.put(jsonObject);
	    }

	    String jsonStr = jsonArray.toString();
	    System.out.println(jsonStr);
	    return jsonStr;
	}
	
	public String getRecordJSON(User user) {
		
		System.out.println("start gettin record json");
	    JSONObject jsonObject = new JSONObject();
	    jsonObject.put("id", user.getId());
        jsonObject.put("nickname", user.getNickname());
        jsonObject.put("email", user.getEmail());
        jsonObject.put("password", user.getPassword());
        
        System.out.println(jsonObject);
	    String jsonStr = jsonObject.toString();
	    
	    System.out.println(jsonStr);
	    return jsonStr;
	}
}
