package com.model.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

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
	public String getRecordByPK(BigDecimal pkey) {
		
		String result = "";
		String CHECK_USER_FIELD = "SELECT email FROM user_acc WHERE id = ?";
		
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
				ps2.setBigDecimal(1, pkey);
				
				System.out.println(ps2);
				
				ResultSet rs = ps2.executeQuery();
				
				rs.last();
				result = rs.getString(1);
				rs.beforeFirst();
				
				System.out.println("Почта " + result + " ID " + pkey);
				
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
