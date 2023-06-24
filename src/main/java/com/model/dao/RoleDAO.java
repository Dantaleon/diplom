package com.model.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import com.model.abstraction.HasGenericPK;
import com.model.datasource.UserDataSource;
import com.model.entity.Role;
import com.model.entity.UserDetails;
import com.utils.Resources;

public class RoleDAO implements HasGenericPK<BigDecimal> {

	private DataSource dataSource;

	public RoleDAO() {

		dataSource = UserDataSource.getInstance().getDataSource();

	}

	public ArrayList<Role> getAllRecords() {

		ArrayList<Role> roleList = new ArrayList<Role>();

		String sql = "SELECT * FROM role";

		try {

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

		} catch (SQLException e) {
			e.printStackTrace();

		}

		return roleList;

	}

	@Override
	public Role getRecordByPK(BigDecimal pkey) {

		String SELECT_ROLE_BY_PK = "SELECT * from role WHERE id = ?";

		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement ps = null;

		try {

			conn = dataSource.getConnection();
			ps = conn.prepareStatement(SELECT_ROLE_BY_PK);

			ps.setBigDecimal(1, pkey);

			rs = ps.executeQuery();

			Role role = null;

			if (rs.next()) {

				role = new Role(rs.getBigDecimal(1), rs.getString(2));
			}

			return role;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Resources.freeRes(ps, conn, rs);
		}

		return null;
	}
}
