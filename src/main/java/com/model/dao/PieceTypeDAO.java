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
import com.model.entity.PieceType;
import com.utils.Resources;

public class PieceTypeDAO implements HasGenericPK<BigDecimal> {
	
private DataSource dataSource;

	public PieceTypeDAO() {
		
		dataSource = UserDataSource.getInstance().getDataSource();
	
	}
	
	public ArrayList<PieceType> getAllRecords() {
		
		ArrayList<PieceType> pieceTypeList = new ArrayList<PieceType>();
		
		String sql = "SELECT * FROM piece_type";
		
		try{
			
			Connection conn = dataSource.getConnection();
			Statement stm = conn.createStatement();
			
			ResultSet result = stm.executeQuery(sql);
			
			while (result.next()) {
				PieceType pieceType = new PieceType(result.getBigDecimal(1), 
						result.getString(2));
				pieceTypeList.add(pieceType);
			}
			
			result.close();
			stm.close();
			conn.close();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		
		return pieceTypeList;
		
	}
	
	@Override
	public PieceType getRecordByPK(BigDecimal pkey) {
		
		String SELECT_PIECE_TYPE_BY_PK = "SELECT * FROM piece_type WHERE id = ?";
		
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement ps = null;
		
		try{
			
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(SELECT_PIECE_TYPE_BY_PK);
			
			ps.setBigDecimal(1, pkey);
			
			rs = ps.executeQuery();
			
			PieceType pieceType = null;
			
			if(rs.next()) {
				
				pieceType = new PieceType(rs.getBigDecimal(1),rs.getString(2));
			}
			
			return pieceType;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			Resources.freeRes(ps, conn, rs);
		}
	
		return null;
	}
}
