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
import com.model.entity.WindowsParts;
import com.utils.Resources;
import com.utils.TransferPiece;

public class WindowsPartsDAO implements HasGenericPK<BigDecimal> {

	private DataSource dataSource;

	public WindowsPartsDAO() {

		dataSource = UserDataSource.getInstance().getDataSource();

	}

	public ArrayList<WindowsParts> getAllRecords() {

		ArrayList<WindowsParts> windowsPartsList = new ArrayList<WindowsParts>();

		String sql = "SELECT * FROM windows_parts";

		Connection conn = null;
		Statement stm = null;

		ResultSet result = null;

		try {

			conn = dataSource.getConnection();
			stm = conn.createStatement();

			result = stm.executeQuery(sql);

			CommonWindowDAO commonWindowDao = new CommonWindowDAO();
			PieceDAO pieceDao = new PieceDAO();

			while (result.next()) {
				WindowsParts windowsParts = new WindowsParts(result.getBigDecimal(1),
						commonWindowDao.getRecordByPK(result.getBigDecimal(2)),
						pieceDao.getRecordByPK(result.getBigDecimal(3)));
				windowsPartsList.add(windowsParts);
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			Resources.freeRes(stm, conn, result);
		}

		return windowsPartsList;

	}

	@Override
	public WindowsParts getRecordByPK(BigDecimal pkey) {

		String SELECT_WINDOWS_PARTS_BY_PK = "SELECT * FROM windows_parts WHERE id = ?";

		ResultSet rs = null;
		Connection conn = null;
		PreparedStatement ps = null;

		try {

			conn = dataSource.getConnection();
			ps = conn.prepareStatement(SELECT_WINDOWS_PARTS_BY_PK);

			ps.setBigDecimal(1, pkey);

			rs = ps.executeQuery();

			WindowsParts windowsParts = null;

			CommonWindowDAO commonWindowDao = new CommonWindowDAO();
			PieceDAO pieceDao = new PieceDAO();

			if (rs.next()) {

				windowsParts = new WindowsParts(rs.getBigDecimal(1), commonWindowDao.getRecordByPK(rs.getBigDecimal(2)),
						pieceDao.getRecordByPK(rs.getBigDecimal(3)));
			}

			return windowsParts;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Resources.freeRes(ps, conn, rs);
		}

		return null;
	}

	public ArrayList<WindowsParts> getAllRecordsDistinctWindowId() {

		String sql = "select distinct on (window_id) id, window_id, piece_id from windows_parts";

		ArrayList<WindowsParts> windowsPartsList = new ArrayList<WindowsParts>();

		Connection conn = null;
		Statement stm = null;

		ResultSet result = null;

		try {

			conn = dataSource.getConnection();
			stm = conn.createStatement();

			result = stm.executeQuery(sql);

			CommonWindowDAO commonWindowDao = new CommonWindowDAO();
			PieceDAO pieceDao = new PieceDAO();

			while (result.next()) {
				WindowsParts windowsParts = new WindowsParts(result.getBigDecimal(1),
						commonWindowDao.getRecordByPK(result.getBigDecimal(2)),
						pieceDao.getRecordByPK(result.getBigDecimal(3)));
				windowsPartsList.add(windowsParts);
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			Resources.freeRes(stm, conn, result);
		}

		return windowsPartsList;

	}

	public int fillWindowWithParts(BigDecimal newWindowId, TransferPiece[] transferPieceArr) {

		String sql = "insert into windows_parts(window_id, piece_id)" + " values(?, ?)";

		Connection conn = null;
		PreparedStatement pstm = null;
		int insertedRows = 0;

		try {

			System.out.println(transferPieceArr.length);
			conn = dataSource.getConnection();
			pstm = conn.prepareStatement(sql);

			for (int i = 0; i < transferPieceArr.length; ++i) {

				pstm.setBigDecimal(1, newWindowId);
				pstm.setBigDecimal(2, transferPieceArr[i].getId());

				insertedRows += pstm.executeUpdate();

			}
			return insertedRows;
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			Resources.freeRes(pstm, conn);
		}

		return insertedRows;
	}

	public ArrayList<WindowsParts> getRecordsByWindowId(BigDecimal wid) {

		ArrayList<WindowsParts> windowsPartsList = new ArrayList<WindowsParts>();

		String sql = "select * from windows_parts where windows_parts.window_id = ?";

		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;

		try {

			conn = dataSource.getConnection();
			pstm = conn.prepareStatement(sql);

			pstm.setBigDecimal(1, wid);

			rs = pstm.executeQuery();

			CommonWindowDAO commonWindowDao = new CommonWindowDAO();
			PieceDAO pieceDao = new PieceDAO();

			while (rs.next()) {
				WindowsParts windowsParts = new WindowsParts(rs.getBigDecimal(1),
						commonWindowDao.getRecordByPK(rs.getBigDecimal(2)),
						pieceDao.getRecordByPK(rs.getBigDecimal(3)));
				windowsPartsList.add(windowsParts);
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			Resources.freeRes(pstm, conn, rs);
		}

		return windowsPartsList;
	}
}
