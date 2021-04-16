package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;
import dto.QnaDto;

public class QnaDao {
	
	private static QnaDao dao = new QnaDao();
	
	private QnaDao() {
		DBConnection.initConnection();
	}
	
	public static QnaDao getInstance() {
		return dao;
	}
	
	

	
public List<QnaDto> getQnaPagingList(String choice, String search, int page) {
		System.out.println("paging");
		String sql = " SELECT TP_Q_SEQ, TP_Q_ID, TP_Q_REF, TP_Q_STEP, TP_Q_DEPTH, "
					+ " TP_Q_TITLE, TP_Q_CONTENT, TP_Q_WDATE, TP_Q_DEL, TP_Q_READCOUNT "
					+ " FROM ";
		
		sql += "(SELECT ROW_NUMBER()OVER(ORDER BY TP_Q_REF DESC, TP_Q_STEP ASC) AS RNUM, " + 
				"	TP_Q_SEQ, TP_Q_ID, TP_Q_REF, TP_Q_STEP, TP_Q_DEPTH, TP_Q_TITLE, TP_Q_CONTENT, TP_Q_WDATE, TP_Q_DEL, TP_Q_READCOUNT " + 
				"	FROM TP_QNA ";
				
		String sWord = "";
		if(choice.equals("title")) {
			sWord = " WHERE TP_Q_TITLE LIKE '%" + search + "%' ";
		}else if(choice.equals("content")) {
			sWord = " WHERE TP_Q_CONTENT LIKE '%" + search + "%' ";
		}else if(choice.equals("writer")) {
			sWord = " WHERE TP_Q_ID='" + search + "'";
		} 
		sql = sql + sWord;
		
		sql = sql + " ORDER BY TP_Q_REF DESC, TP_Q_STEP ASC) ";
		
		sql = sql + " WHERE RNUM >= ? AND RNUM <= ? ";
		System.out.println(sql);
		int start, end;
		start = 1 + 10 * page;
		end = 10 + 10 * page;		
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<QnaDto> list = new ArrayList<QnaDto>();
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/4 getQnaPagingList success");
				
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, start);
			psmt.setInt(2, end);
			System.out.println("2/4 getQnaPagingList success");
			
			rs = psmt.executeQuery();			
			System.out.println("3/4 getQnaPagingList success");
			
			while(rs.next()) {
				int i = 1;
				QnaDto dto = new QnaDto(rs.getInt(i++), 
										rs.getString(i++), 
										rs.getInt(i++), 
										rs.getInt(i++), 
										rs.getInt(i++), 
										rs.getString(i++), 
										rs.getString(i++), 
										rs.getString(i++), 
										rs.getInt(i++), 
										rs.getInt(i++));
				list.add(dto);
			}			
			System.out.println("4/4 getQnaPagingList success");
			
		} catch (SQLException e) {	
			System.out.println("getQnaPagingList fail");
			e.printStackTrace();
		} finally {			
			DBClose.close(conn, psmt, rs);			
		}
		
		return list;
	}

	public int getAllQna(String choice, String search) {
		String sql = " SELECT COUNT(*) FROM TP_QNA ";
		
		String sWord = "";
		if(choice.equals("title")) {
			sWord = " WHERE TP_Q_TITLE LIKE '%" + search + "%' ";
		}else if(choice.equals("content")) {
			sWord = " WHERE TP_Q_CONTENT LIKE '%" + search + "%' ";
		}else if(choice.equals("writer")) {
			sWord = " WHERE TP_Q_ID='" + search + "'";
		} 
		sql = sql + sWord;
	
		
		System.out.println(sql);
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		int len = 0;
				
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 getAllQna success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("2/3 getAllQna success");
			
			rs = psmt.executeQuery();
			if(rs.next()) {
				len = rs.getInt(1);
			}
			System.out.println("3/3 getAllQna success");
			
		} catch (SQLException e) {
			System.out.println("getAllQna fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);			
		}
		
		return len;
	}


	public boolean writeQna(QnaDto dto) {
		
		String sql = " INSERT INTO TP_QNA"
				+ " (TP_Q_SEQ, TP_Q_ID, TP_Q_REF, TP_Q_STEP, TP_Q_DEPTH, "
				+ " TP_Q_TITLE, TP_Q_CONTENT, TP_Q_WDATE, TP_Q_DEL, TP_Q_READCOUNT) "
				+ " VALUES( SEQ_TP_QNA.NEXTVAL, ?,"
				+ " (SELECT NVL(MAX(TP_Q_REF), 0)+1 FROM TP_QNA), 0, 0,"
				+ " ?, ?, SYSDATE,"
				+ " 0, 0) ";
		
		System.out.println(sql);
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
		
			
			psmt = conn.prepareStatement(sql);
			System.out.println("1/3 writeQna success");
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			System.out.println("2/3 writeQna success");
			
			count = psmt.executeUpdate();
			System.out.println("3/3 writeQna success");
		} catch (SQLException e) {
			System.out.println("writeQna Fail");
			e.printStackTrace();
		}finally {
			DBClose.close(conn, psmt, null);
		}
		return count>0?true:false;
		
	}
	
	public QnaDto getQna(int seq) {
		String sql = " SELECT TP_Q_SEQ, TP_Q_ID, TP_Q_REF, TP_Q_STEP, TP_Q_DEPTH, "
				+ " TP_Q_TITLE, TP_Q_CONTENT, TP_Q_WDATE,"
				+ " TP_Q_DEL, TP_Q_READCOUNT "
				+ " FROM TP_QNA "
				+ " WHERE TP_Q_SEQ=? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		QnaDto dto = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/4 getQna success");
		
			psmt = conn.prepareStatement(sql);
			System.out.println("2/4 getQna success");
			
			psmt.setInt(1, seq);
			
			rs = psmt.executeQuery();
			System.out.println("3/4 getQna success");
			
			if(rs.next()) {
				int i = 1;
				dto = new QnaDto(rs.getInt(i++), 
								rs.getString(i++), 
								rs.getInt(i++), 
								rs.getInt(i++), 
								rs.getInt(i++), 
								rs.getString(i++), 
								rs.getString(i++),
								rs.getString(i++), 
								rs.getInt(i++), 
								rs.getInt(i++));
			}
			System.out.println("4/4 getQna success");
			
		} catch (Exception e) {
			System.out.println("getQna fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);			
		}
		return dto;
	}
	
	public void readcount(int seq) {
		String sql = " UPDATE TP_QNA "
				+ " SET TP_Q_READCOUNT=TP_Q_READCOUNT+1 "
				+ " WHERE TP_Q_SEQ=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 readcount success");
				
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			System.out.println("2/3 readcount success");
			
			psmt.executeUpdate();
			System.out.println("3/3 readcount success");
			
		} catch (SQLException e) {
			System.out.println("readcount fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, null);
		}				
	}
	public boolean updateQna(int seq, String title, String content) {
		String sql = " UPDATE TP_QNA SET "
				+ " TP_Q_TITLE=?, TP_Q_CONTENT=? "
				+ " WHERE TP_Q_SEQ=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 updateQna success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, title);
			psmt.setString(2, content);
			psmt.setInt(3, seq);
			
			System.out.println("2/3 updateQna success");
			
			count = psmt.executeUpdate();
			System.out.println("3/3 updateQna success");
			
		} catch (Exception e) {	
			System.out.println("updateQna fail");
			e.printStackTrace();
		} finally{
			DBClose.close(conn, psmt, null);			
		}		
		
		return count>0?true:false;
	}
		
	public boolean deleteQna(int seq) {
			
			String sql = " UPDATE TP_QNA "
						+ " SET TP_Q_DEL=1 "
						+ " WHERE TP_Q_SEQ=? ";
			
			Connection conn = null;
			PreparedStatement psmt = null;
			int count = 0;
			
			try {
				conn = DBConnection.getConnection();
				System.out.println("1/3 deleteQna success");
				
				psmt = conn.prepareStatement(sql);
				psmt.setInt(1, seq);
				System.out.println("1/3 deleteQna success");
				
				count = psmt.executeUpdate();
				System.out.println("1/3 deleteQna success");
				
			} catch (Exception e) {		
				System.out.println("fail deleteQna");
				e.printStackTrace();
			} finally {
				DBClose.close(conn, psmt, null);			
			}
			
			return count>0?true:false;
		}
		//	   부모글 번호    새로운 답글
		public boolean answer(int seq, QnaDto qna) {
		// update
		String sql1 = " UPDATE TP_QNA "
		+ "	SET TP_Q_STEP=TP_Q_STEP+1 "
		+ " WHERE TP_Q_REF=(SELECT TP_Q_REF FROM TP_QNA WHERE TP_Q_SEQ=? ) "
		+ "		AND TP_Q_STEP>(SELECT TP_Q_STEP FROM TP_QNA WHERE TP_Q_SEQ=? )";		
		
		// insert
		String sql2 = " INSERT INTO TP_QNA "
				+ " (TP_Q_SEQ, TP_Q_ID, "
				+ " TP_Q_REF, TP_Q_STEP, TP_Q_DEPTH, "
				+ " TP_Q_TITLE, TP_Q_CONTENT, TP_Q_WDATE, TP_Q_DEL, TP_Q_READCOUNT) "
				+ " VALUES(SEQ_TP_QNA.NEXTVAL, ?, "
				+ "        (SELECT TP_Q_REF FROM TP_QNA WHERE TP_Q_SEQ=?), "	// REF
				+ "			(SELECT TP_Q_STEP FROM TP_QNA WHERE TP_Q_SEQ=?) + 1, " // STEP
				+ "         (SELECT TP_Q_DEPTH FROM TP_QNA WHERE TP_Q_SEQ=?) + 1, " // DEPTH
				+ "         ?, ?, SYSDATE, 0, 0) ";     
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
		conn = DBConnection.getConnection();		
		conn.setAutoCommit(false);
		System.out.println("1/6 success answer");
		
		// update
		psmt = conn.prepareStatement(sql1);
		psmt.setInt(1, seq);
		psmt.setInt(2, seq);
		System.out.println("2/6 success answer");
		
		count = psmt.executeUpdate();
		System.out.println("3/6 success answer");
		
		// psmt 초기화
		psmt.clearParameters();
		
		// insert
		psmt = conn.prepareStatement(sql2);
		psmt.setString(1, qna.getId());
		psmt.setInt(2, seq);
		psmt.setInt(3, seq);
		psmt.setInt(4, seq);
		psmt.setString(5, qna.getTitle());
		psmt.setString(6, qna.getContent());
		System.out.println("4/6 success answer");
		
		count = psmt.executeUpdate();
		System.out.println("5/6 success answer");
		
		conn.commit();
		System.out.println("6/6 success answer");
		
		} catch (SQLException e) {
		System.out.println("answer fail");			
		try {
		conn.rollback();
		} catch (SQLException e1) {				
		e1.printStackTrace();
		}			
		e.printStackTrace();
		} finally {
		
		try {
		conn.setAutoCommit(true);
		} catch (SQLException e) {				
		e.printStackTrace();
		}			
		DBClose.close(conn, psmt, null);			
		}
		
		return count>0?true:false;		
		}
	
}
