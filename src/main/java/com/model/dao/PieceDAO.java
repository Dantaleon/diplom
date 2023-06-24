package com.model.dao;

import java.io.InputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;

import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONObject;

import com.model.abstraction.HasGenericPK;
import com.model.datasource.UserDataSource;
import com.model.entity.Piece;
import com.model.entity.User;
import com.utils.Resources;

public class PieceDAO implements HasGenericPK<BigDecimal> {

	private DataSource dataSource;
	
	public PieceDAO() {
		
		dataSource = UserDataSource.getInstance().getDataSource();
	
	}
	
	public ArrayList<Piece> getAllRecords() {
		
		ArrayList<Piece> pieceList = new ArrayList<Piece>();
		
		String sql = "SELECT * FROM piece";
		
		try{
			
			Connection conn = dataSource.getConnection();
			Statement stm = conn.createStatement();
			
			ResultSet result = stm.executeQuery(sql);
			
			while (result.next()) {
				
				byte[] imgBytes = result.getBytes("image");
				
				String base64Img = "no-image";
				if (imgBytes != null) {
				
					base64Img = Base64.getEncoder().encodeToString(imgBytes);
				}
				
				MaterialTypeDAO mtypeDao = new MaterialTypeDAO();
				PieceTypeDAO ptypeDao = new PieceTypeDAO();
				
				Piece piece = new Piece(result.getBigDecimal(1), 
						result.getString(2), base64Img, result.getInt(4),
						result.getInt(5), result.getInt(6), result.getInt(7),
						result.getInt(8), result.getInt(9), result.getDouble(10),
						result.getInt(11), result.getInt(12), result.getInt(13),
						mtypeDao.getRecordByPK(result.getBigDecimal(14)),
						ptypeDao.getRecordByPK(result.getBigDecimal(15)) );
				
				pieceList.add(piece);
			}
			
			result.close();
			stm.close();
			conn.close();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		
		return pieceList;
		
	}

	public int newRecord(Piece piece) {
		
		int result = 0;
		
		String INSERT_PIECE_SQL = 
				"INSERT INTO piece(name, image, "
				+ "picx, picy, picw, pich, defaultw, defaulth, "
				+ "cost, "
				+ "realw, realh, realth, "
				+ "id_material, id_type) " +
				"VALUES(?, ?, "
				+ "?, ?, ?, ?, ?, ?, "
				+ "?, "
				+ "?, ?, ?, "
				+ "?, ?)";
		
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(INSERT_PIECE_SQL);
			
			byte[] imageBytes = Base64.getDecoder().decode(piece.getBase64Img());
			
			ps.setString(1, piece.getName());
			ps.setBytes(2, imageBytes);
			
			ps.setInt(3, piece.getPicx());
			ps.setInt(4, piece.getPicy());
			ps.setInt(5, piece.getPicw());
			ps.setInt(6, piece.getPich());
			ps.setInt(7, piece.getDefaultw());
			ps.setInt(8, piece.getDefaulth());
			
			ps.setDouble(9, piece.getCost());
			
			ps.setInt(10, piece.getRealw());
			ps.setInt(11, piece.getRealh());
			ps.setInt(12, piece.getRealth());
			
			ps.setBigDecimal(13, piece.getMaterialType().getId());
			ps.setBigDecimal(14, piece.getPieceType().getId());
			
			result = ps.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		} finally {
			Resources.freeRes(ps, conn, null);
		}
		
		return result;
		
	}
	
	
	@Override
	public Piece getRecordByPK(BigDecimal pkey) {
		
		String SELECT_PIECE_BY_PK = "SELECT * FROM piece WHERE id = ?";
		
		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement ps = null;
		
		try{
			
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(SELECT_PIECE_BY_PK);
			
			ps.setBigDecimal(1, pkey);
			
			rs = ps.executeQuery();
			
			Piece piece = null;
			
			MaterialTypeDAO mtypeDao = new MaterialTypeDAO();
			PieceTypeDAO ptypeDao = new PieceTypeDAO();
			
			if(rs.next()) {
				
				byte[] imgBytes = rs.getBytes("image");
				
				String base64Img = "no-image";
				if (imgBytes != null) {
				
					base64Img = Base64.getEncoder().encodeToString(imgBytes);
				}
				
				//base64Img = Base64.getEncoder().encodeToString(rs.getBytes(3));
				
				piece = new Piece(rs.getBigDecimal(1),rs.getString(2), base64Img,
						rs.getInt(4), rs.getInt(5),
						rs.getInt(6), rs.getInt(7),
						rs.getInt(8), rs.getInt(9),
						rs.getDouble(10),
						rs.getInt(11), rs.getInt(12), rs.getInt(13),
						mtypeDao.getRecordByPK(rs.getBigDecimal(14)),
						ptypeDao.getRecordByPK(rs.getBigDecimal(15))
						);
			}
			
			return piece;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			Resources.freeRes(ps, conn, rs);
		}
	
		return null;
	}
	
	public ArrayList<Piece> getRecordsByType(String type) {
		
		ArrayList<Piece> pieceList = new ArrayList<Piece>();
		
		String sql = "select * from piece "
				+ "join piece_type on piece.id_type = piece_type.id "
				+ "where piece_type.name = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet result = null;
		
		try{
			
			conn = dataSource.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, type);
			
			result = pstm.executeQuery();
			
			while (result.next()) {
				
				byte[] imgBytes = result.getBytes("image");
				
				String base64Img = "no-image";
				if (imgBytes != null) {
				
					base64Img = Base64.getEncoder().encodeToString(imgBytes);
				}
				
				MaterialTypeDAO mtypeDao = new MaterialTypeDAO();
				PieceTypeDAO ptypeDao = new PieceTypeDAO();
				
				Piece piece = new Piece(result.getBigDecimal(1), 
						result.getString(2), base64Img, result.getInt(4),
						result.getInt(5), result.getInt(6), result.getInt(7),
						result.getInt(8), result.getInt(9), result.getDouble(10),
						result.getInt(11), result.getInt(12), result.getInt(13),
						mtypeDao.getRecordByPK(result.getBigDecimal(14)),
						ptypeDao.getRecordByPK(result.getBigDecimal(15)) );
				
				pieceList.add(piece);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		}finally {
			Resources.freeRes(pstm, conn, result);
		}
		
		return pieceList;
		
	}

	public String pieceListToJson(ArrayList<Piece> pieceList) {
		
		JSONArray jsonArray = new JSONArray();

	    for (Piece piece : pieceList) {
	    	
	        JSONObject jsonObject = new JSONObject();
	        jsonObject.put("id", piece.getId());
	        jsonObject.put("name", piece.getName());
	        jsonObject.put("image", piece.getBase64Img());
	        jsonObject.put("picx", piece.getPicx());
	        jsonObject.put("picy", piece.getPicy());
	        jsonObject.put("picw", piece.getPicw());
	        jsonObject.put("pich", piece.getPich());
	        jsonObject.put("defaultw", piece.getDefaultw());
	        jsonObject.put("defaulth", piece.getDefaulth());
	        jsonObject.put("cost", piece.getCost());
	        jsonObject.put("material_name", piece.getMaterialType().getName());
	        jsonObject.put("type_name", piece.getPieceType().getName());
	        
	        jsonArray.put(jsonObject);
	    }

	    String jsonStr = jsonArray.toString();
	    return jsonStr;
	}

	public int saveXYWHByJsonStr(String requestBody) {
		
		String sql = "update piece set picx = ?, picy = ?, picw = ?, pich = ? where id = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		int rowUpd = -1;
		
		try {
			conn = dataSource.getConnection();
			pstm = conn.prepareStatement(sql);
			
			JSONObject jsonObj = new JSONObject(requestBody);
			
			pstm.setInt(1, jsonObj.getInt("picx"));
			pstm.setInt(2, jsonObj.getInt("picy"));
			pstm.setInt(3, jsonObj.getInt("picw"));
			pstm.setInt(4, jsonObj.getInt("pich"));
			pstm.setBigDecimal(5, jsonObj.getBigDecimal("id"));
			
			rowUpd = pstm.executeUpdate();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			Resources.freeRes(pstm, conn, null);
		}
		
		return rowUpd;
	}

	public int deleteRecord(BigDecimal deleteId) {
		
		String sql = "delete from piece where id = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		int rowDel = -1;
		
		try {
			conn = dataSource.getConnection();
			pstm = conn.prepareStatement(sql);

			pstm.setBigDecimal(1, deleteId);
			
			rowDel = pstm.executeUpdate();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			Resources.freeRes(pstm, conn, null);
		}
		
		return rowDel;
	}

	public int editRecord(Piece piece) {
		
		int result = 0;
		
		String UPDATE_PIECE_SQL = 
				"update piece SET  "
				+ "name = ?, "
				+ "image = ?, "
				+ "picx = ?, "
				+ "picy = ?, "
				+ "picw = ?, "
				+ "pich = ?, "
				+ "defaultw = ?, "
				+ "defaulth = ?, "
				+ "cost = ?, "
				+ "realw = ?, "
				+ "realh = ?, "
				+ "realth = ?, "
				+ "id_material = ?, "
				+ "id_type = ? "
				+ "where id = ?";
		
		Connection conn = null;
		PreparedStatement ps = null;
		try{
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(UPDATE_PIECE_SQL);
			
			byte[] imageBytes = Base64.getDecoder().decode(piece.getBase64Img());
			
			ps.setString(1, piece.getName());
			ps.setBytes(2, imageBytes);
			ps.setInt(3, piece.getPicx());
			ps.setInt(4, piece.getPicy());
			ps.setInt(5, piece.getPicw());
			ps.setInt(6, piece.getPich());
			ps.setInt(7, piece.getDefaultw());
			ps.setInt(8, piece.getDefaulth());
			ps.setDouble(9, piece.getCost());
			ps.setInt(10, piece.getRealw());
			ps.setInt(11, piece.getRealh());
			ps.setInt(12, piece.getRealth());
			ps.setBigDecimal(13, piece.getMaterialType().getId());
			ps.setBigDecimal(14, piece.getPieceType().getId());
			ps.setBigDecimal(15, piece.getId());
			
			result = ps.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
			
		} finally {
			Resources.freeRes(ps, conn, null);
		}
		
		return result;
	}	
}
