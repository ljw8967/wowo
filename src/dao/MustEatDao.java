package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;

import db.DBClose;
import db.DBConnection;

import dto.MustEatDto;


public class MustEatDao {
	
	//싱글톤
	private static MustEatDao dao = new MustEatDao();
	
	private MustEatDao() {
		
		DBConnection.initConnection();
	}
	
	public static MustEatDao getInstance() {
	
		return dao;
	}
	
	/*
	 * public List<MustEatDto> getMustEatList() { String sql = " SELECT TP_ME_SEQ,"
	 * + " TP_ME_PLACE," + " TP_ME_ID," + " TP_ME_NAME," + " TP_ME_TITLE," +
	 * " TP_ME_CONTENT," + " TP_ME_FILENAME," + " TP_ME_NEWFILENAME," +
	 * " TP_ME_READCOUNT," + " TP_ME_DOWNCOUNT," + "  " + " FROM TP_MUSTEAT" +
	 * " ORDER BY TP_ME_SEQ DESC";
	 * 
	 * 
	 * System.out.println(sql);
	 * 
	 * System.out.println("0/4 getMustEatList success");
	 * 
	 * Connection conn = null; PreparedStatement psmt = null; ResultSet rs = null;
	 * 
	 * List<MustEatDto> list = new ArrayList<MustEatDto>();
	 * 
	 * 
	 * 
	 * System.out.println("1/4 getMustEatList success"); try { conn =
	 * DBConnection.getConnection();
	 * System.out.println("2/4 getMustEatList success");
	 * 
	 * 
	 * psmt = conn.prepareStatement(sql);
	 * System.out.println("3/4 getMustEatList success");
	 * 
	 * rs = psmt.executeQuery(); System.out.println("4/4 getMustEatList success");
	 * 
	 * 
	 * 
	 * while(rs.next()) { int i = 1; MustEatDto dto = new MustEatDto(
	 * 
	 * rs.getInt(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
	 * rs.getString(i++), rs.getString(i++), rs.getString(i++), rs.getString(i++),
	 * rs.getInt(i++), rs.getInt(i++), rs.getString(i++));
	 * 
	 * list.add(dto); } System.out.println("5/4 getMustEatList success");
	 * 
	 * } catch (SQLException e) { System.out.println("getMustEatList  fail");
	 * e.printStackTrace(); }finally {
	 * 
	 * DBClose.close(conn, psmt, rs); }
	 * 
	 * 
	 * return list; }
	 */
	
	
	public boolean writeMustEat(MustEatDto must) {
		String sql = " INSERT INTO TP_MUSTEAT(TP_ME_SEQ, TP_ME_PLACE , TP_ME_ID, TP_ME_NAME, TP_ME_TITLE, TP_ME_CONTENT, TP_ME_FILENAME, TP_ME_NEWFILENAME, "
								+ "	TP_ME_READCOUNT, TP_ME_DOWNCOUNT, TP_ME_REGDATE, TP_ME_LIKE) "
				   + " VALUES(TP_ME_SEQ_MUSTEAT.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, "
								+ "	0, 0, SYSDATE, 0)";
		
		Connection conn = null;
		PreparedStatement psmt = null;		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 S writeMustEat");
				
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, must.getPlace());
			psmt.setString(2, must.getId());
			psmt.setString(3, must.getName());
			psmt.setString(4, must.getTitle());
			psmt.setString(5, must.getContent());
			psmt.setString(6, must.getFilename());
			psmt.setString(7, must.getNewFilename());
			System.out.println("2/3 S writeMustEat");
			
			count = psmt.executeUpdate();	
			System.out.println("3/3 S writeMustEat");
			
		} catch (SQLException e) {
			System.out.println("fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, null);
		}

		return count>0?true:false;
	}
	
	
	
	@SuppressWarnings("resource")
	public boolean deleteMustEat(int seq) {
		
		String sql1 = "DELETE FROM " + 
				" (SELECT a.COMMENT_ME_SEQ, b.TP_ME_SEQ" + 
				" FROM TP_COMMENT a, TP_MUSTEAT b " + 
				" WHERE a.COMMENT_ME_SEQ = TP_ME_SEQ) c" + 
				" WHERE c.TP_ME_SEQ=? ";
		
		
		 String sql2 = " DELETE FROM TP_MUSTEAT"
					+ " WHERE TP_ME_SEQ=?";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 deleteMustEat");
			conn.setAutoCommit(false);
			
			psmt = conn.prepareStatement(sql1);
			psmt.setInt(1, seq);
			System.out.println("2/3 deleteMustEat");
			
			count = psmt.executeUpdate();
			System.out.println("3/3 deleteMustEat");
			

			// psmt 초기화
			psmt.clearParameters();
			
			psmt = conn.prepareStatement(sql2);
			psmt.setInt(1, seq);
			
			count = psmt.executeUpdate();
			
			conn.commit();
			System.out.println("3/3 deleteMustEat");
			
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
	
	
	public MustEatDto getMustEat(int seq) {
		
		String sql = " SELECT TP_ME_SEQ, "
				+ " TP_ME_PLACE, "
				+ " TP_ME_ID, "
				+ " TP_ME_NAME, "
				+ " TP_ME_TITLE, "
				+ " TP_ME_CONTENT, "
				+ " TP_ME_FILENAME, "
				+ " TP_ME_NEWFILENAME, "
				+ " TP_ME_READCOUNT, "
				+ " TP_ME_DOWNCOUNT, "
				+ " TP_ME_REGDATE, "
				+ " TP_ME_LIKE"
				+ " FROM TP_MUSTEAT"
				+ " WHERE TP_ME_SEQ=?";
		
		System.out.println(sql);
		
		Connection conn =null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		MustEatDto dto = null;
		
		
		
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/5  getMustEat");
			psmt = conn.prepareStatement(sql);
			System.out.println("2/5  getMustEat");
			
			psmt.setInt(1, seq);
			
			rs = psmt.executeQuery();
			System.out.println(rs);
			System.out.println("3/5  getMustEat");
			
			
			
			if(rs.next()) {
			
				
			 dto = new MustEatDto(
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
							rs.getInt(12)
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
		String sql = " UPDATE TP_MUSTEAT "
				+ " SET TP_ME_READCOUNT=TP_ME_READCOUNT+1"
				+ " WHERE TP_ME_SEQ=? ";
				
				
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
	//좋아요 증가
	public void updatelike(int seq) {
		String sql = "UPDATE TP_MUSTEAT "
				+ " SET TP_ME_LIKE=TP_ME_LIKE+1"
				+ " WHERE TP_ME_SEQ=?";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 updatelike");
			psmt = conn.prepareStatement(sql);
			System.out.println("2/3 updatelike");
			
			psmt.setInt(1, seq);
			System.out.println("3/3 updatelike");
			psmt.executeUpdate();
			
			System.out.println("updatelike finish");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(conn, psmt, null);
		}
	}
	
	//좋아요 개수 찾기
	public int selectlike(int seq){
		String sql = "SELECT TP_ME_LIKE"
				+ " FROM TP_MUSTEAT "
				+ " WHERE TP_ME_SEQ=?";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		int like = 0;
		
		
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				like = rs.getInt("TP_ME_LIKE");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(conn, psmt, rs);
		}
		return like;
		
		
		
		}

	
	
public boolean updateMustEat(int seq, MustEatDto dto) {
		
		String sql = " UPDATE TP_MUSTEAT "
				+ " SET TP_ME_PLACE=?, TP_ME_TITLE=?, TP_ME_CONTENT=?, TP_ME_FILENAME=?, TP_ME_NEWFILENAME=?, TP_ME_REGDATE=SYSDATE "
				+ " WHERE TP_ME_SEQ=? ";
		
		Connection conn=null;
		PreparedStatement psmt=null;
		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 S MustEat updateMustEat");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getPlace());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getFilename());
			psmt.setString(5, dto.getNewFilename());
			psmt.setInt(6, seq);
			System.out.println("2/3 S MustEat updateMustEat");

			count = psmt.executeUpdate();			
			System.out.println("3/3 S MustEat updateMustEat");
			
		} catch (SQLException e) {
			System.out.println("fail MustEat updateMustEat");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, null);
		}
				
		return count>0?true:false;
	}

public int getAllmust(String choice, String searchWord) {
	String sql = " SELECT COUNT(*) FROM TP_MUSTEAT ";

	String sqlWord = "";
	if(choice.equals("title")) {
		sqlWord = " WHERE TP_ME_TITLE LIKE '%" + searchWord.trim() + "%' ";
	}else if(choice.equals("writer") && !searchWord.equals("")) {
		sqlWord = " WHERE TP_ME_ID='" + searchWord.trim() + "'";
	}else if(choice.equals("content")) {
		sqlWord = " WHERE TP_ME_CONTENT LIKE '%" + searchWord.trim() + "%' ";
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
		System.out.println("getAllmust fail");
		e.printStackTrace();
	} finally {
		DBClose.close(conn, psmt, rs);;            
	}
	return len;        
}



	public List<MustEatDto> getMustPagingList(String choice, String search, int page) {
	System.out.println("getMustPagingList DAO");
	String sql = " SELECT RNUM, TP_ME_SEQ,"
			+ " TP_ME_PLACE,"
			+ " TP_ME_ID,"
			+ " TP_ME_NAME,"
			+ " TP_ME_TITLE,"
			+ " TP_ME_CONTENT,"
			+ " TP_ME_FILENAME,"
			+ " TP_ME_NEWFILENAME,"
			+ " TP_ME_READCOUNT,"
			+ " TP_ME_DOWNCOUNT,"
			+ " TP_ME_REGDATE,"
			+ " TP_ME_LIKE"
			+ " FROM ";
	
	

		
		 sql += "(SELECT ROW_NUMBER()OVER(ORDER BY TP_ME_SEQ DESC)AS RNUM, " +
		  "	TP_ME_SEQ, TP_ME_PLACE, TP_ME_ID, TP_ME_NAME, TP_ME_TITLE, "
		  + " TP_ME_CONTENT,"
		  + " TP_ME_FILENAME, TP_ME_NEWFILENAME,"
		  + " TP_ME_READCOUNT, TP_ME_DOWNCOUNT,"
		  + " TP_ME_REGDATE, TP_ME_LIKE " 
		  + " FROM TP_MUSTEAT ";
		 
		
	String sWord = "";
	if(choice.equals("title")) {
	sWord = " WHERE TP_ME_TITLE LIKE '%" + search + "%' ";
	}else if(choice.equals("content")) {
	sWord = " WHERE TP_ME_CONTENT LIKE '%" + search + "%' ";
	}else if(choice.equals("writer")) {
	sWord = " WHERE TP_ME_ID='" + search + "'";
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

	List<MustEatDto> list = new ArrayList<MustEatDto>();

		try {
	conn = DBConnection.getConnection();
	System.out.println("1/4 getMustPagingList success");
		
	psmt = conn.prepareStatement(sql);
	psmt.setInt(1, start);
	psmt.setInt(2, end);
	System.out.println("2/4 getMustPagingList success");
	
	rs = psmt.executeQuery();			
	System.out.println("3/4 getMustPagingList success");
	
	while(rs.next()) {
		int i = 1;
		
		 MustEatDto dto = new MustEatDto(
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
					rs.getInt(i++)				);
		list.add(dto);
			}			
	System.out.println("4/4 getMustPagingList success");
	
		} catch (SQLException e) {	
	System.out.println("getMustPagingList fail");
	e.printStackTrace();
	} finally {			
	DBClose.close(conn, psmt, rs);			
	}
		
		return list;
	}
	
	
	public List<MustEatDto> getranking(){
		System.out.println("1/4 getranking success");
		
		System.out.println("getranking DAO");
		String sql = "SELECT "
				+ " RNUM, "
				+ " TP_ME_SEQ, "
				+ " TP_ME_PLACE, "
				+ " TP_ME_ID, "
				+ " TP_ME_NAME, "
				+ " TP_ME_TITLE, "
				+ " TP_ME_CONTENT, "
				 + " TP_ME_FILENAME, "
				 + " TP_ME_NEWFILENAME, "
				 + " TP_ME_READCOUNT, "
				 + " TP_ME_DOWNCOUNT, "
				 + " TP_ME_REGDATE, "
				 + " TP_ME_LIKE"
				 +" FROM (SELECT ROW_NUMBER()OVER(ORDER BY TP_ME_READCOUNT DESC)AS RNUM, TP_ME_SEQ, TP_ME_PLACE,"
				 + " TP_ME_ID, TP_ME_NAME, TP_ME_TITLE, TP_ME_CONTENT,"
				 + " TP_ME_FILENAME, TP_ME_NEWFILENAME, TP_ME_READCOUNT, TP_ME_DOWNCOUNT, TP_ME_REGDATE, TP_ME_LIKE"
				 + " FROM TP_MUSTEAT)"
				 + " WHERE RNUM <=3 AND TP_ME_READCOUNT != 0";
			
		/*
		 sql += "(SELECT ROW_NUMBER()OVER(ORDER BY TP_ME_SEQ DESC)AS ROWNUM, " +
				  "	TP_ME_SEQ, TP_ME_PLACE, TP_ME_ID, TP_ME_NAME, TP_ME_TITLE, "
				  + " TP_ME_CONTENT,"
				  + " TP_ME_FILENAME, TP_ME_NEWFILENAME,"
				  + " TP_ME_READCOUNT, TP_ME_DOWNCOUNT,"
				  + " TP_ME_REGDATE " 
				  + " FROM TP_MUSTEAT "
				  + " ORDER BY TP_ME_READCOUNT DESC)"
				  + " WHERE ROWNUM<=3";
		 
		*/
		
		
	    System.out.println(sql);
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		List<MustEatDto> list = new ArrayList<MustEatDto>();
		System.out.println("2/4 getranking success");
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				int i = 1;
				 MustEatDto dto = new MustEatDto(
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
							rs.getInt(i++)
							);
				list.add(dto);
				
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(conn, psmt, rs);
		}
		
		return list;
		
		
		
	}
	




}

