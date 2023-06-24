package com.model.dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.model.abstraction.HasGenericCompPK;
import com.model.datasource.UserDataSource;
import com.model.entity.Role;
import com.model.entity.User;
import com.model.entity.UsersRoles;
import com.utils.Resources;

public class UsersRolesDAO implements HasGenericCompPK<User, Role> {

	private DataSource dataSource;
	
	public UsersRolesDAO() {
		
		dataSource = UserDataSource.getInstance().getDataSource();
	
	}
	
	public ArrayList<UsersRoles> getAllRecords() {
		
		ArrayList<UsersRoles> usersRolesList = new ArrayList<UsersRoles>();
		
		String sql = "SELECT * FROM users_roles";
		
		Connection conn = null;
		Statement stm = null;
		ResultSet result = null;
		
		try{
			
			conn = dataSource.getConnection();
			stm = conn.createStatement();
			
			result = stm.executeQuery(sql);
			
			UserDAO userDao = new UserDAO();
			RoleDAO roleDao = new RoleDAO();
			
			while (result.next()) {
				UsersRoles usersRoles = new UsersRoles(
						userDao.getRecordByPK(result.getBigDecimal(1)),
						roleDao.getRecordByPK(result.getBigDecimal(2))
						);
				usersRolesList.add(usersRoles);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}finally {
			Resources.freeRes(stm, conn, result);
		}
		
		return usersRolesList;
		
	}
	
	
	@Override
	public UsersRoles getRecordByPK(User user, Role role) {
		
		String SELECT_USERS_ROLES_BY_PK = "SELECT * FROM users_roles "
				+ "WHERE user_id = ? AND role_id = ?";
		
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement ps = null;
		
		try{
			
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(SELECT_USERS_ROLES_BY_PK);
			
			ps.setBigDecimal(1, user.getId());
			ps.setBigDecimal(2, role.getId());
			
			rs = ps.executeQuery();
			
			UsersRoles usersRoles = null;
			
			UserDAO userDao = new UserDAO();
			RoleDAO roleDao = new RoleDAO();
			
			if(rs.next()) {
				
				usersRoles = new UsersRoles(
						userDao.getRecordByPK(rs.getBigDecimal(1)),
						roleDao.getRecordByPK(rs.getBigDecimal(2))
						);
			}
			
			return usersRoles;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			Resources.freeRes(ps, conn, rs);
		}
	
		return null;
	}
}
