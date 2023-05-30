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
import com.model.entity.CommonWindow;
import com.model.entity.User;
import com.utils.Resources;

public class CommonWindowDAO implements HasGenericPK<BigDecimal> {
	
	private DataSource dataSource;
	
	public CommonWindowDAO() {
		
		dataSource = UserDataSource.getInstance().getDataSource();
	
	}
	
public ArrayList<CommonWindow> getAllRecords() {
		
		ArrayList<CommonWindow> roleList = new ArrayList<CommonWindow>();
		
		String sql = "SELECT * FROM common_window";
		
		try{
			
			Connection conn = dataSource.getConnection();
			Statement stm = conn.createStatement();
			
			ResultSet result = stm.executeQuery(sql);
			
			while (result.next()) {
				CommonWindow commonWindow = new CommonWindow(
						result.getBigDecimal(1), result.getString(2),
						result.getInt(3), result.getInt(4), result.getInt(5));
				roleList.add(commonWindow);
			}
			
			result.close();
			stm.close();
			conn.close();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		
		return roleList;
		
	}

@Override
public CommonWindow getRecordByPK(BigDecimal pkey) {
	
	String SELECT_COMMON_WINDOW_BY_PK = "SELECT * FROM common_window WHERE id = ?";
	
	ResultSet rs = null;
	Connection conn = null;
	PreparedStatement ps = null;
	
	try{
		
		conn = dataSource.getConnection();
		ps = conn.prepareStatement(SELECT_COMMON_WINDOW_BY_PK);
		
		ps.setBigDecimal(1, pkey);
		
		rs = ps.executeQuery();
		
		CommonWindow cwindow = null;
		
		if(rs.next()) {
			
			cwindow = new CommonWindow(rs.getBigDecimal(1),rs.getString(2), 
					rs.getInt(3), rs.getInt(4), rs.getInt(5));
		}
		
		return cwindow;
		
	}catch(SQLException e) {
		e.printStackTrace();
	}finally {
		Resources.freeRes(ps, conn, rs);
	}

	return null;
}
	
}
