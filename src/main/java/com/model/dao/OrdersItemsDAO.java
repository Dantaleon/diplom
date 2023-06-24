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
import com.model.entity.CartsItems;
import com.model.entity.OrdersItems;
import com.utils.Resources;

public class OrdersItemsDAO implements HasGenericPK<BigDecimal> {

	private DataSource dataSource;

	public OrdersItemsDAO() {

		dataSource = UserDataSource.getInstance().getDataSource();

	}

	public ArrayList<OrdersItems> getAllRecords() {

		ArrayList<OrdersItems> ordersItemsList = new ArrayList<OrdersItems>();

		String sql = "SELECT * FROM orders_items";

		ResultSet rs = null;
		Connection conn = null;
		Statement stm = null;

		try {

			conn = dataSource.getConnection();
			stm = conn.createStatement();

			rs = stm.executeQuery(sql);

			OrderAccDAO orderAccDao = new OrderAccDAO();
			CommonWindowDAO commonWindowDao = new CommonWindowDAO();

			while (rs.next()) {
				OrdersItems ordersItems = new OrdersItems(rs.getBigDecimal(1), rs.getInt(2),
						orderAccDao.getRecordByPK(rs.getBigDecimal(3)),
						commonWindowDao.getRecordByPK(rs.getBigDecimal(4)));
				ordersItemsList.add(ordersItems);
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			Resources.freeRes(stm, conn, rs);
		}

		return ordersItemsList;

	}

	@Override
	public OrdersItems getRecordByPK(BigDecimal pkey) {

		String SELECT_ORDERS_ITEMS_BY_PK = "SELECT * FROM orders_items WHERE id = ?";

		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement ps = null;

		try {

			conn = dataSource.getConnection();
			ps = conn.prepareStatement(SELECT_ORDERS_ITEMS_BY_PK);

			ps.setBigDecimal(1, pkey);

			rs = ps.executeQuery();

			OrdersItems ordersItems = null;
			OrderAccDAO orderAccDao = new OrderAccDAO();
			CommonWindowDAO commonWindowDao = new CommonWindowDAO();

			if (rs.next()) {

				ordersItems = new OrdersItems(rs.getBigDecimal(1), rs.getInt(2),
						orderAccDao.getRecordByPK(rs.getBigDecimal(3)),
						commonWindowDao.getRecordByPK(rs.getBigDecimal(4)));
			}

			return ordersItems;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Resources.freeRes(ps, conn, rs);
		}

		return null;
	}

	public ArrayList<OrdersItems> getOrdersItemsByUserIdAndOrderId(BigDecimal uid, BigDecimal oid) {

		ArrayList<OrdersItems> ordersItemsList = new ArrayList<OrdersItems>();

		String sql = "select * from orders_items  " + "join order_acc on order_acc.id = orders_items.id_order "
				+ "join user_acc on user_acc.id = order_acc.id_user " + "where user_acc.id = ? and order_acc.id = ?";

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet result = null;

		try {

			conn = dataSource.getConnection();
			pstm = conn.prepareStatement(sql);

			pstm.setBigDecimal(1, uid);
			pstm.setBigDecimal(2, oid);
			result = pstm.executeQuery();

			OrderAccDAO orderDao = new OrderAccDAO();
			CommonWindowDAO cwindowDao = new CommonWindowDAO();

			while (result.next()) {

				OrdersItems orderItem = new OrdersItems(result.getBigDecimal(1), result.getInt(2),
						orderDao.getRecordByPK(result.getBigDecimal(3)),
						cwindowDao.getRecordByPK(result.getBigDecimal(4)));
				ordersItemsList.add(orderItem);
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			Resources.freeRes(pstm, conn, result);
		}

		return ordersItemsList;
	}

}
