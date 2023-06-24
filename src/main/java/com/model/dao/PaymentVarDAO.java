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
import com.model.entity.PaymentVar;
import com.utils.Resources;

public class PaymentVarDAO implements HasGenericPK<BigDecimal> {

	private DataSource dataSource;

	public PaymentVarDAO() {

		dataSource = UserDataSource.getInstance().getDataSource();

	}

	public ArrayList<PaymentVar> getAllRecords() {

		ArrayList<PaymentVar> paymentVarList = new ArrayList<PaymentVar>();

		String sql = "SELECT * FROM payment_var";

		ResultSet rs = null;
		Connection conn = null;
		Statement stm = null;

		try {

			conn = dataSource.getConnection();
			stm = conn.createStatement();

			ResultSet result = stm.executeQuery(sql);

			while (result.next()) {
				PaymentVar paymentVar = new PaymentVar(result.getBigDecimal(1), result.getString(2));
				paymentVarList.add(paymentVar);
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			Resources.freeRes(stm, conn, rs);
		}

		return paymentVarList;

	}
	
	@Override
	public PaymentVar getRecordByPK(BigDecimal pkey) {
		
		String SELECT_PAYMENT_VAR_BY_PK = "SELECT * FROM payment_var WHERE id = ?";
		
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement ps = null;
		
		try{
			
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(SELECT_PAYMENT_VAR_BY_PK);
			
			ps.setBigDecimal(1, pkey);
			
			rs = ps.executeQuery();
			
			PaymentVar paymentVar = null;
			
			if(rs.next()) {
				
				paymentVar = new PaymentVar(rs.getBigDecimal(1),rs.getString(2));
			}
			
			return paymentVar;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			Resources.freeRes(ps, conn, rs);
		}
	
		return null;
	}	
}
