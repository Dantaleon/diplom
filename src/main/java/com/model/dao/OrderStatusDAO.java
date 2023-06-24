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
import com.model.entity.OrderStatus;
import com.utils.Resources;

public class OrderStatusDAO implements HasGenericPK<BigDecimal> {

	private DataSource dataSource;

	public OrderStatusDAO() {

		dataSource = UserDataSource.getInstance().getDataSource();

	}

	public ArrayList<OrderStatus> getAllRecords() {

		ArrayList<OrderStatus> orderStatusList = new ArrayList<OrderStatus>();

		String sql = "SELECT * FROM order_status";

		try {

			Connection conn = dataSource.getConnection();
			Statement stm = conn.createStatement();

			ResultSet result = stm.executeQuery(sql);

			while (result.next()) {
				OrderStatus orderStatus = new OrderStatus(result.getBigDecimal(1), result.getString(2));
				orderStatusList.add(orderStatus);
			}

			result.close();
			stm.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();

		}

		return orderStatusList;

	}

	@Override
	public OrderStatus getRecordByPK(BigDecimal pkey) {

		String SELECT_ORDER_STATUS_BY_PK = "SELECT * FROM order_status WHERE id = ?";

		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement ps = null;

		try {

			conn = dataSource.getConnection();
			ps = conn.prepareStatement(SELECT_ORDER_STATUS_BY_PK);

			ps.setBigDecimal(1, pkey);

			rs = ps.executeQuery();

			OrderStatus orderStatus = null;

			if (rs.next()) {

				orderStatus = new OrderStatus(rs.getBigDecimal(1), rs.getString(2));
			}

			return orderStatus;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Resources.freeRes(ps, conn, rs);
		}

		return null;
	}

}
