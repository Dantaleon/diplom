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

import com.logic.enums.RoleEnums;
import com.model.abstraction.HasGenericPK;
import com.model.datasource.AdminDataSource;
import com.model.datasource.UserDataSource;
import com.model.entity.Role;
import com.model.entity.User;
import com.utils.Resources;

public class UserDAO implements HasGenericPK<BigDecimal> {

	private DataSource dataSource;

	public UserDAO() {

		dataSource = UserDataSource.getInstance().getDataSource();
	}

	// Function returns number of column inserted in DB
	public int registerUser(User user) {
		int result = 0;

		String INSERT_USER_ACC_SQL = "INSERT INTO User_acc(nickname, email, password)" + "VALUES(?, ?, ?)";

		try {

			Connection conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(INSERT_USER_ACC_SQL);

			ps.setString(1, user.getNickname());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());

			System.out.println(ps);

			result = ps.executeUpdate();

			ps.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();

		}

		return result;
	}

	public boolean isUserEmailExist(String email) {

		int rows = 0;
		boolean result = false;

		String CHECK_USER_FIELD = "SELECT COUNT(id) FROM user_acc WHERE email = ?";

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost/windows", "admin",
				"admin1");
				PreparedStatement ps2 = connection.prepareStatement(CHECK_USER_FIELD, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);) {
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

		} catch (SQLException e) {
			result = false;
			e.printStackTrace();
		}

		return result;
	}

	public BigDecimal getUserIdByEmail(String email) {

		BigDecimal result = BigDecimal.ZERO;

		String CHECK_USER_FIELD = "SELECT id FROM user_acc WHERE email = ?";

		try {
			Connection conn = dataSource.getConnection();
			PreparedStatement pstm = conn.prepareStatement(CHECK_USER_FIELD);

			pstm.setString(1, email);

			System.out.println(pstm);

			ResultSet rs = pstm.executeQuery();

			if (rs.next()) {
				result = rs.getBigDecimal(1);
			}

			System.out.println(result);

		} catch (SQLException e) {
			e.printStackTrace();
		}

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

		try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost/windows", "admin",
				"admin1");
				PreparedStatement ps = connection.prepareStatement(CHECK_USER_LOGIN, ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);) {

			ps.setString(1, email);
			ps.setString(2, password);

			System.out.println(ps);

			ResultSet rs = ps.executeQuery();

			System.out.println(rs.toString());

			if (rs.next()) {
				result = true;
			} else {

			}

		} catch (SQLException e) {
			result = false;
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public User getRecordByPK(BigDecimal pkey) {

		String SELECT_USER_BY_PK = "SELECT * FROM user_acc WHERE id = ?";

		try {
			ResultSet rs = null;

			Connection conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(SELECT_USER_BY_PK);

			ps.setBigDecimal(1, pkey);

			rs = ps.executeQuery();

			User user = null;

			if (rs.next()) {

				user = new User(rs.getBigDecimal("id"), rs.getString("nickname"), rs.getString("email"),
						rs.getString("password"));
			}

			ps.close();
			rs.close();
			conn.close();

			return user;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

		}

		return null;
	}

	public ArrayList<User> getAllRecords() {

		String SELECT_ALL_USERS = "SELECT * FROM user_acc";

		try {
			ResultSet rs = null;

			Connection conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(SELECT_ALL_USERS);

			rs = ps.executeQuery();

			ArrayList<User> users = new ArrayList<User>();

			while (rs.next()) {

				User user = new User(rs.getBigDecimal("id"), rs.getString("nickname"), rs.getString("email"),
						rs.getString("password"));

				users.add(user);
			}

			ps.close();
			rs.close();
			conn.close();

			return users;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

		}

		return null;
	}

	public boolean userHasRole(BigDecimal id, String roleName) {

		String SELECT_ROLE_BY_USER_PK = "SELECT role.name FROM role "
				+ "RIGHT JOIN users_roles ON role.id = users_roles.role_id "
				+ "RIGHT JOIN user_acc ON users_roles.user_id = user_acc.id "
				+ "WHERE role.name = ? AND user_acc.id = ?";

		try {
			ResultSet rs = null;

			Connection conn = dataSource.getConnection();

			PreparedStatement ps = conn.prepareStatement(SELECT_ROLE_BY_USER_PK);
			ps.setString(1, roleName);
			ps.setBigDecimal(2, id);

			rs = ps.executeQuery();

			if (rs.next()) {

				return true;
			}

			ps.close();
			rs.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	public String getTopRole(BigDecimal id) {

		boolean isAdmin = userHasRole(id, RoleEnums.administrator.name());
		boolean isUser = userHasRole(id, RoleEnums.user.name());
		boolean isGuest = userHasRole(id, RoleEnums.guest.name());

		String role = "";
		if (isAdmin)
			return RoleEnums.administrator.name();
		if (isUser)
			return RoleEnums.user.name();
		if (isGuest)
			return RoleEnums.guest.name();

		return RoleEnums.guest.name();
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

	public void deleteUserById(BigDecimal uid) {
		
		String DELETE_USER_BY_PK = "delete from user_acc where id = ?";
			
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			
			conn = dataSource.getConnection();
			
			pstm = conn.prepareStatement(DELETE_USER_BY_PK);
			pstm.setBigDecimal(1, uid);
			
			pstm.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			Resources.freeRes(pstm, conn);
		}
		
	}
}
