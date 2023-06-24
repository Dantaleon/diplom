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
import com.model.entity.MaterialType;
import com.utils.Resources;

public class MaterialTypeDAO implements HasGenericPK<BigDecimal> {

	private DataSource dataSource;

	public MaterialTypeDAO() {

		dataSource = UserDataSource.getInstance().getDataSource();

	}

	public ArrayList<MaterialType> getAllRecords() {

		ArrayList<MaterialType> materialTypeList = new ArrayList<MaterialType>();

		String sql = "SELECT * FROM material_type";

		try {

			Connection conn = dataSource.getConnection();
			Statement stm = conn.createStatement();

			ResultSet result = stm.executeQuery(sql);

			while (result.next()) {
				MaterialType materialType = new MaterialType(result.getBigDecimal(1), result.getString(2),
						result.getDouble(3));
				materialTypeList.add(materialType);
			}

			result.close();
			stm.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();

		}

		return materialTypeList;

	}

	@Override
	public MaterialType getRecordByPK(BigDecimal pkey) {

		String SELECT_MATERIAL_TYPE_BY_PK = "SELECT * FROM material_type WHERE id = ?";

		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement ps = null;

		try {

			conn = dataSource.getConnection();
			ps = conn.prepareStatement(SELECT_MATERIAL_TYPE_BY_PK);

			ps.setBigDecimal(1, pkey);

			rs = ps.executeQuery();

			MaterialType materialType = null;

			if (rs.next()) {

				materialType = new MaterialType(rs.getBigDecimal(1), rs.getString(2), rs.getDouble(3));
			}

			return materialType;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Resources.freeRes(ps, conn, rs);
		}

		return null;
	}

}
