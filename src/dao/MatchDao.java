package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;

import db.DBClose;
import db.DBConnection;
import dto.MatchDto;




public class MatchDao {
	
	//싱글톤
	private static MatchDao dao = new MatchDao();
	
	private MatchDao() {
		
		DBConnection.initConnection();
	}
	
	public static MatchDao getInstance() {
	
		return dao;
	}
	
	
	
	
	public boolean writeMatch(MatchDto dto) {
		String sql = " INSERT INTO TP_MATCH(TP_MA_SEQ, TP_MA_PLACE , TP_MA_ID, TP_MA_NAME, TP_MA_TITLE, TP_MA_CONTENT, TP_MA_FILENAME, TP_MA_NEWFILENAME, "
								+ "	TP_MA_READCOUNT, TP_MA_DOWNCOUNT, TP_MA_FIRDATE, TP_MA_LASDATE) "
				   + " VALUES(TP_MA_SEQ_MATCH.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, "
								+ "	0, 0, ?, ?)";
		
		Connection conn = null;
		PreparedStatement psmt = null;		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 S writeMustEat");
				
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getPlace());
			psmt.setString(2, dto.getId());
			psmt.setString(3, dto.getName());
			psmt.setString(4, dto.getTitle());
			psmt.setString(5, dto.getContent());
			psmt.setString(6, dto.getFilename());
			psmt.setString(7, dto.getNewFilename());
			psmt.setString(8, dto.getFirDate());
			psmt.setString(9, dto.getLasDate());
			
			System.out.println("2/3 S writeMatch");
			
			count = psmt.executeUpdate();	
			System.out.println("3/3 S writeMatchs");
			
		} catch (SQLException e) {
			System.out.println("fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, null);
		}

		return count>0?true:false;
	}
	
	
	
	@SuppressWarnings("resource")
	public boolean deleteMatch(int seq) {
		
		String sql1 = "DELETE FROM " + 
				" (SELECT a.COMMENT_MA_SEQ, b.TP_MA_SEQ" + 
				" FROM TP_COMMENT a, TP_MATCH b " + 
				" WHERE a.COMMENT_MA_SEQ = TP_MA_SEQ) c" + 
				" WHERE c.TP_MA_SEQ=? ";
		
		
		 String sql2 = " DELETE FROM TP_MATCH"
					+ " WHERE TP_MA_SEQ=?";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
		
			
			conn = DBConnection.getConnection();
			System.out.println("1/3 deleteMatch");
			
			conn.setAutoCommit(false);
			
			psmt = conn.prepareStatement(sql1);
			psmt.setInt(1, seq);
			System.out.println("2/3 deleteMatch");
			
			count = psmt.executeUpdate();
			System.out.println("3/3 deleteMatch");
			

			// psmt 초기화
			psmt.clearParameters();
			
			psmt = conn.prepareStatement(sql2);
			psmt.setInt(1, seq);
			
			count = psmt.executeUpdate();
			
			conn.commit();
			System.out.println("finally deleteMatch");
			
		}catch (SQLException e) {
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
	
	
	public MatchDto getMatch(int seq) {
		
		String sql = " SELECT TP_MA_SEQ, "
				+ " TP_MA_PLACE, "
				+ " TP_MA_ID, "
				+ " TP_MA_NAME, "
				+ " TP_MA_TITLE, "
				+ " TP_MA_CONTENT, "
				+ " TP_MA_FILENAME, "
				+ " TP_MA_NEWFILENAME, "
				+ " TP_MA_READCOUNT, "
				+ " TP_MA_DOWNCOUNT, "
				+ " TP_MA_FIRDATE, "
				+ " TP_MA_LASDATE"
				+ " FROM TP_MATCH"
				+ " WHERE TP_MA_SEQ=?";
		
		System.out.println(sql);
		
		Connection conn =null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		MatchDto dto = null;
		
		
		
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/5  getMatch");
			psmt = conn.prepareStatement(sql);
			System.out.println("2/5  getMatch");
			
			psmt.setInt(1, seq);
			
			rs = psmt.executeQuery();
			System.out.println(rs);
			System.out.println("3/5  getMatch");
			
			
			
			if(rs.next()) {
			
				
			 dto = new MatchDto(
							rs.getInt(1),
							rs.getString(2),
							rs.getString(3),						
							rs.getString(4),
							
							rs.getString(5),
							rs.getString(6),
							
							rs.getString(7),
							rs.getString(8),
							rs.getInt(9),	
							rs.getInt(10),
							rs.getString(11),
							rs.getString(12)
							);
			
			}
			System.out.println("4/5  getMustEat");

			
		} catch (SQLException e) {
			System.out.println("fail  getMustEat");
			e.printStackTrace();
		}finally {
			DBClose.close(conn, psmt, rs);
		}
		
		return dto;
	}
	
	public void readcount(int seq) {
		String sql = " UPDATE TP_MATCH "
				+ " SET TP_MA_READCOUNT=TP_MA_READCOUNT+1"
				+ " WHERE TP_MA_SEQ=? ";
				
				
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 readcount");
			psmt = conn.prepareStatement(sql);
			System.out.println("2/3 readcount");
			
			psmt.setInt(1, seq);
			System.out.println("3/3 readcount");
			psmt.executeUpdate();
			
			System.out.println("readcount finish");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(conn, psmt, null);
		}
		
		
		
		
	}
	public void downcount(int seq) {
		String sql = " UPDATE TP_MUSTEAT "
				+ " SET TP_ME_DOWNCOUNT=TP_ME_DOWNCOUNT+1"
				+ " WHERE TP_ME_SEQ=? ";
				
				
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 downcount");
			psmt = conn.prepareStatement(sql);
			System.out.println("2/3 downcount");
			
			psmt.setInt(1, seq);
			System.out.println("3/3 downcount");
			psmt.executeUpdate();
			
			System.out.println("downcount finish");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(conn, psmt, null);
		}
		
	}
	
	
	
	
public boolean updateMatch(int seq, MatchDto dto) {
		System.out.println("확인:"+dto.getNewFilename());
		String sql = " UPDATE TP_MATCH "
				+ " SET TP_MA_PLACE=?,"
				+ " TP_MA_TITLE=?, "
				+ " TP_MA_CONTENT=?, "
				+ " TP_MA_FILENAME=?, "
				+ " TP_MA_NEWFILENAME=?, "
				+ " TP_MA_FIRDATE=?, "
				+ " TP_MA_LASDATE=?"
				+ " WHERE TP_MA_SEQ=? ";
		
		Connection conn=null;
		PreparedStatement psmt=null;
		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 S updateMatch");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getPlace());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getFilename());
			psmt.setString(5, dto.getNewFilename());
			psmt.setString(6, dto.getFirDate());
			psmt.setString(7, dto.getLasDate());
			
			psmt.setInt(8, seq);
			System.out.println("2/3 S updateMatch ");

			count = psmt.executeUpdate();			
			System.out.println("3/3 S updateMatch ");
			
		} catch (SQLException e) {
			System.out.println("fail updateMatch ");
			e.printStackTrace();
		} finally {
		}
				
		return count>0?true:false;
	}

public int getAllMatch(String choice, String searchWord) {
	String sql = " SELECT COUNT(*) FROM TP_MATCH ";

	String sqlWord = "";
	if(choice.equals("title")) {
		sqlWord = " WHERE TP_MA_TITLE LIKE '%" + searchWord.trim() + "%' ";
	}else if(choice.equals("writer") && !searchWord.equals("")) {
		sqlWord = " WHERE TP_MA_ID='" + searchWord.trim() + "'";
	}else if(choice.equals("content")) {
		sqlWord = " WHERE TP_MA_CONTENT LIKE '%" + searchWord.trim() + "%' ";
	}
	sql += sqlWord;

	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	int len = 0;
    
	try {
		conn = DBConnection.getConnection();
		psmt = conn.prepareStatement(sql);
		rs = psmt.executeQuery();
		if(rs.next()) {
			len = rs.getInt(1);
		}            
	} catch (Exception e) {
		System.out.println("getAllMatch fail");
		e.printStackTrace();
	} finally {
		DBClose.close(conn, psmt, rs);;            
	}
	return len;        
}



	public List<MatchDto> getMatchPagingList(String choice, String search, int page) {
	System.out.println("getMustPagingList DAO");
	String sql = " SELECT RNUM, TP_MA_SEQ,"
			+ " TP_MA_PLACE,"
			+ " TP_MA_ID,"
			+ " TP_MA_NAME,"
			+ " TP_MA_TITLE,"
			+ " TP_MA_CONTENT,"
			+ " TP_MA_FILENAME,"
			+ " TP_MA_NEWFILENAME,"
			+ " TP_MA_READCOUNT,"
			+ " TP_MA_DOWNCOUNT,"
			+ " TP_MA_FIRDATE,"
			+ " TP_MA_LASDATE"
			+ " FROM ";
	
	

		
		 sql += "(SELECT ROW_NUMBER()OVER(ORDER BY TP_MA_SEQ DESC)AS RNUM, " +
		  "	TP_MA_SEQ, TP_MA_PLACE, TP_MA_ID, TP_MA_NAME, TP_MA_TITLE, "
		  + " TP_MA_CONTENT,"
		  + " TP_MA_FILENAME, TP_MA_NEWFILENAME,"
		  + " TP_MA_READCOUNT, TP_MA_DOWNCOUNT,"
		  + " TP_MA_FIRDATE, TP_MA_LASDATE " 
		  + " FROM TP_MATCH ";
		 
		
	String sWord = "";
	if(choice.equals("title")) {
	sWord = " WHERE TP_MA_TITLE LIKE '%" + search + "%' ";
	}else if(choice.equals("content")) {
	sWord = " WHERE TP_MA_CONTENT LIKE '%" + search + "%' ";
	}else if(choice.equals("writer")) {
	sWord = " WHERE TP_MA_ID='" + search + "'";
	} 
	sql = sql + sWord;
	System.out.println(sWord);
	sql = sql + " ORDER BY RNUM ASC) ";
	sql = sql + " WHERE RNUM >= ? AND RNUM <= ? ";
	System.out.println(sql);

	int start, end;
	start = 1 + 9 * page;
	end = 9 + 9 * page;		
		/*
		 * start = 1 + 10 * page; 
		 * end = 10 + 10 * page;
		 */
	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;

	List<MatchDto> list = new ArrayList<MatchDto>();

		try {
	conn = DBConnection.getConnection();
	System.out.println("1/4 getMatchPagingList success");
		
	psmt = conn.prepareStatement(sql);
	psmt.setInt(1, start);
	psmt.setInt(2, end);
	System.out.println("2/4 getMatchPagingList success");
	
	rs = psmt.executeQuery();			
	System.out.println("3/4 getMatchPagingList success");
	
	while(rs.next()) {
		int i = 1;
		
		MatchDto dto = new MatchDto(
				 	rs.getInt(i++),
					rs.getInt(i++),
					rs.getString(i++),
					rs.getString(i++),
					rs.getString(i++),
					
					
					rs.getString(i++),
					rs.getString(i++),
					rs.getString(i++),
					rs.getString(i++),
					rs.getInt(i++),
					
					rs.getInt(i++),
					rs.getString(i++),
					rs.getString(i++)				);
		list.add(dto);
			}			
	System.out.println("4/4 getMatchPagingList success");
	
		} catch (SQLException e) {	
	System.out.println("getMatchPagingList fail");
	e.printStackTrace();
	} finally {			
	DBClose.close(conn, psmt, rs);			
	}
		
		return list;
	}
	
	
	

	
	




}

