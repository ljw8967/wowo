package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;
import dto.PdsDto;

public class PdsDao {
	private static PdsDao dao = new PdsDao();
	
	private PdsDao() {
		DBConnection.initConnection();
	}
	
	public static PdsDao getInstance() {
		return dao;
	}

	public List<PdsDto> getPdsList() {
		
		String sql = " SELECT TP_B_SEQ,"
				+ " TP_B_ID,"
				+ " TP_B_NAME,"
				+ " TP_B_REF,"
				+ " TP_B_STEP,"
				+ " TP_B_DEPTH,"
				+ " TP_B_TITLE,"
				+ " TP_B_CONTENT, "
				+ " TP_B_WDATE, "
				+ " TP_B_DEL, "
				+ " TP_B_READCOUNT, "
				+ " TP_B_FILENAME, "
				+ " TP_B_NEWFILENAME "
				+ " FROM TP_BBS "
				+ " ORDER BY TP_B_SEQ TP_B_REF DESC, TP_B_STEP ASC ";	// + " ORDER BY REF DESC, STEP ASC ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<PdsDto> list = new ArrayList<PdsDto>();
		
		conn = DBConnection.getConnection();
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/4 S getPdsList");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("2/4 S getPdsList");
			
			rs = psmt.executeQuery();
			System.out.println("3/4 S getPdsList");
			
			while(rs.next()) {
				int i = 1;
				PdsDto dto = new PdsDto(rs.getInt(i++), 
										rs.getString(i++), 
										rs.getString(i++), 
										rs.getInt(i++), 
										rs.getInt(i++), 
										rs.getInt(i++),
										rs.getString(i++), 
										rs.getString(i++), 
										rs.getString(i++),
										rs.getInt(i++),
										rs.getInt(i++),
										rs.getString(i++),
										rs.getString(i++));
				list.add(dto);
			}
			System.out.println("4/4 S getPdsList");
			
		} catch (SQLException e) {	
			System.out.println("Fail getPdsList");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);			
		}
		
		return list;
	}
	
	

	
	public boolean writePds(PdsDto pds) {
		
		System.out.println(pds.toString());
		String sql = " INSERT INTO TP_BBS"
				+ "(TP_B_SEQ, "
				+ " TP_B_ID, "
				+ " TP_B_NAME,"
				+ " TP_B_REF, "
				+ " TP_B_STEP, "
				+ " TP_B_DEPTH, "
				+ " TP_B_TITLE, "
				+ " TP_B_CONTENT, "
				+ "	TP_B_WDATE, "
				+ " TP_B_DEL, "
				+ " TP_B_READCOUNT, "
				+ " TP_B_FILENAME, "
				+ " TP_B_NEWFILENAME ) "
				+ " VALUES(SEQ_TP_BBS.NEXTVAL, ?, ?, (SELECT NVL(MAX(TP_B_REF), 0)+1 FROM TP_BBS), 0, 0, ?, ?, "
								+ "	SYSDATE , 0 , 0 , ?, ? ) " ;
		
		Connection conn = null;
		PreparedStatement psmt = null;		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 S writePds");
				
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, pds.getId());
			psmt.setString(2, pds.getName());
			psmt.setString(3, pds.getTitle());
			psmt.setString(4, pds.getContent());
			psmt.setString(5, pds.getFilename());
			psmt.setString(6, pds.getNewfilename());
	//		psmt.setInt(1, pds.getSeq());
	//		psmt.setString(2, pds.getId());
	//		psmt.setString(3, pds.getName());
	//		psmt.setInt(4, pds.getRef());
	//		psmt.setInt(5, pds.getStep());
	//		psmt.setInt(6, pds.getDepth());
	//		psmt.setString(7, pds.getTitle());
	//		psmt.setString(8, pds.getContent());
	//		psmt.setInt(9, pds.getDel());
	//		psmt.setString(10, pds.getWdate());
	//		psmt.setString(11, pds.getFilename());
	//		psmt.setString(12, pds.getNewfilename());
			
			System.out.println("2/3 S writePds");
			
			count = psmt.executeUpdate();	
			
			System.out.println("3/3 S writePds");
			
		} catch (SQLException e) {
			System.out.println("fail writePds");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, null);
		}

		return count>0?true:false;
	}
	
	public PdsDto getPds(int seq) {
		
		String sql = " SELECT TP_B_SEQ, TP_B_ID, TP_B_NAME , TP_B_REF, TP_B_STEP, TP_B_DEPTH , "
				+ "  TP_B_TITLE, TP_B_CONTENT, TP_B_WDATE, TP_B_DEL , TP_B_READCOUNT , TP_B_FILENAME , TP_B_NEWFILENAME  "
				+ " FROM TP_BBS "
				+ " WHERE TP_B_SEQ=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		//PdsDto pds = null;
		PdsDto dto = null;
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/4 getPds Success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			System.out.println("2/4 getPds Success");
			
			rs = psmt.executeQuery();
			System.out.println("3/4 getPds Success");
			
			if(rs.next()) {
				int i = 1;				
			 dto = new PdsDto(
					 	rs.getInt(i++), 
						rs.getString(i++), 
						rs.getString(i++), 
						rs.getInt(i++), 
						rs.getInt(i++), 
						rs.getInt(i++),
						rs.getString(i++), 
						rs.getString(i++), 
						rs.getString(i++),
						rs.getInt(i++),
						rs.getInt(i++),
						rs.getString(i++),
						rs.getString(i++));			
			}
			System.out.println("4/4 getPds Success");
			
		} catch (Exception e) {		
			System.out.println("getPds Fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);			
		}
				
		return dto;
	}
	
	
	
	public boolean pdsReadCount(int seq) {
		int count=0;
		String sql=" UPDATE TP_BBS SET " +
				"  TP_B_READCOUNT=TP_B_READCOUNT+1 " +
				" WHERE  TP_B_SEQ=? ";
		
		Connection conn=null;
		PreparedStatement psmt=null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 S  pdsReadCount");
			
			psmt=conn.prepareStatement(sql);
			psmt.setInt(1, seq );
			System.out.println("2/3 S  pdsReadCount");
			
			count=psmt.executeUpdate();
			System.out.println("3/3 S  pdsReadCount");
			
		} catch (Exception e) {
			System.out.println("F pdsReadCount");
		}finally{
			DBClose.close(conn, psmt, null);
		}
		return count>0?true:false;
	}
	
	public boolean updatePdss(int seq, PdsDto pds) {
		
		System.out.println(pds);
		
		String sql = " UPDATE TP_BBS "
				+ " SET TP_B_TITLE=?, TP_B_CONTENT=?, TP_B_WDATE=SYSDATE, TP_B_FILENAME=?, TP_B_NEWFILENAME=? "
				+ " WHERE TP_B_SEQ=? " ;
		System.out.println("updatepds ㅎ확인1");
		Connection conn=null;
		PreparedStatement psmt=null;
		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 S updatePds");
			
			psmt = conn.prepareStatement(sql);
			
	//		System.out.println(pds.getFilename());
			
			psmt.setString(1, pds.getTitle());
			psmt.setString(2, pds.getContent());
			
			psmt.setString(3, pds.getFilename());
			psmt.setString(4, pds.getNewfilename());			
			psmt.setInt(5, seq); 
			 
			System.out.println("2/3 S updatePds");

			count = psmt.executeUpdate();			
			System.out.println("3/3 S updatePds");
			
		} catch (SQLException e) {
			System.out.println("fail updatePds");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, null);
		}
				
		return count>0?true:false;
	}
	

	public int getsearchPbs(String choice, String search) {
		String sql = " SELECT COUNT(*) FROM TP_BBS ";
			
			String sWord = "";
			if(choice.equals("title")) {
				sWord = " WHERE TP_B_TITLE LIKE '%" + search + "%' ";
			}else if(choice.equals("content")) {
				sWord = " WHERE TP_B_CONTENT LIKE '%" + search + "%' ";
			}else if(choice.equals("writer")) {
				sWord = " WHERE TP_B_ID='" + search + "'";
			} 
			sql = sql + sWord;
			
			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			
			int len = 0;
					
			try {
				conn = DBConnection.getConnection();
				System.out.println("1/3 getsearchPbs success");
				
				psmt = conn.prepareStatement(sql);
				System.out.println("2/3 getsearchPbs success");
				
				rs = psmt.executeQuery();
				if(rs.next()) {
					len = rs.getInt(1);
				}
				System.out.println("3/3 getsearchPbs success");
				
			} catch (SQLException e) {
				System.out.println("getsearchPbsPbs fail");
				e.printStackTrace();
			} finally {
				DBClose.close(conn, psmt, rs);			
			}
			
			return len;
		}

	
	public List<PdsDto> getPdspagingList(String choice, String search, int page) {
		
		String sql = " SELECT TP_B_SEQ,"
				+ " TP_B_ID, "
				+ " TP_B_NAME, "
				+ " TP_B_REF, "
				+ " TP_B_STEP, "
				+ " TP_B_DEPTH, "
				+ " TP_B_TITLE, "
				+ " TP_B_CONTENT, "
				+ " TP_B_WDATE, "
				+ " TP_B_DEL, "
				+ " TP_B_READCOUNT, "
				+ " TP_B_FILENAME, "
				+ " TP_B_NEWFILENAME "
				+ " FROM " ;
		
		sql += "(SELECT ROW_NUMBER()OVER(ORDER BY TP_B_REF DESC, TP_B_STEP ASC) AS RNUM, " 
		+ " TP_B_SEQ, "
		+ " TP_B_ID,"
		+ " TP_B_NAME,"
		+ " TP_B_REF,"
		+ " TP_B_STEP, "
		+ " TP_B_DEPTH, "
		+ " TP_B_TITLE, "
		+ " TP_B_CONTENT, "
		+ " TP_B_WDATE, "
		+ " TP_B_DEL, "
		+ " TP_B_READCOUNT, "
		+ " TP_B_FILENAME, "
		+ " TP_B_NEWFILENAME " 
		+ " FROM TP_BBS ";
		
		
		String sWord = "";
		if(choice.equals("title")) {
			sWord = " WHERE TP_B_TITLE LIKE '%" + search + "%' ";
		}else if(choice.equals("content")) {
			sWord = " WHERE TP_B_CONTENT LIKE '%" + search + "%' ";
		}else if(choice.equals("writer")) {
			sWord = " WHERE TP_B_ID= '" + search + "' ";
		} 
		sql = sql + sWord;
		
		sql = sql + " ORDER BY RNUM ASC ) ";
		
		sql = sql + "  WHERE RNUM >= ? AND RNUM <= ? ";
	//	sql = sql + " WHERE TP_B_DEL=0 AND RNUM >= ? AND RNUM <= ? ";
		
		System.out.println(sql);
		int start, end;
		start = 1 + 10 * page;
		end = 10 + 10 * page;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<PdsDto> list = new ArrayList<PdsDto>();
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/4 getBbsPagingList success");
				
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, start);
			psmt.setInt(2, end);
			System.out.println("2/4 getBbsPagingList success");
			
			rs = psmt.executeQuery();			
			System.out.println("3/4 getBbsPagingList success");
			
			while(rs.next()) {
				int i = 1;
				PdsDto dto = new PdsDto( 
							rs.getInt(i++), 
							rs.getString(i++), 
							rs.getString(i++), 
							rs.getInt(i++), 
							rs.getInt(i++), 
							rs.getInt(i++),
							rs.getString(i++), 
							rs.getString(i++), 
							rs.getString(i++),
							rs.getInt(i++),
							rs.getInt(i++),
							rs.getString(i++),
							rs.getString(i++));		
				list.add(dto);
			}			
			System.out.println("4/4 getBbsPagingList success");
			
		} catch (SQLException e) {	
			System.out.println("getBbsPagingList fail");
			e.printStackTrace();
		} finally {			
			DBClose.close(conn, psmt, rs);			
		}
		System.out.println(list.toString());
		
		return list;
	}

	

public boolean deletePds(int seq) {
		
		String sql = " UPDATE TP_BBS "
					+ " SET TP_B_DEL=1 "
					+ " WHERE TP_B_SEQ=? ";
				
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 S deletePds");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			System.out.println("2/3 S deletePds");
			
			count = psmt.executeUpdate();
			System.out.println("3/3 S deletePds");
			
		} catch (Exception e) {		
			System.out.println("fail deletePds");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, null);			
		}
		
		return count>0?true:false;
	}

	
/*
	public List<PdsDto> getPdspagingList(String choice, String search, int page) {
		
		String sql = " SELECT TP_B_SEQ,"
				+ " TP_B_ID, "
				+ " TP_B_NAME, "
				+ " TP_B_REF, "
				+ " TP_B_STEP, "
				+ " TP_B_DEPTH, "
				+ " TP_B_TITLE, "
				+ " TP_B_CONTENT, "
				+ " TP_B_WDATE, "
				+ " TP_B_DEL, "
				+ " TP_B_READCOUNT, "
				+ " TP_B_FILENAME, "
				+ " TP_B_NEWFILENAME "
				+ " FROM " ;
		
		sql += "(SELECT ROW_NUMBER()OVER(ORDER BY TP_B_REF DESC, TP_B_STEP ASC) AS RNUM, " 
		+ " TP_B_SEQ, "
		+ " TP_B_ID,"
		+ " TP_B_NAME,"
		+ " TP_B_REF,"
		+ " TP_B_STEP, "
		+ " TP_B_DEPTH, "
		+ " TP_B_TITLE, "
		+ " TP_B_CONTENT, "
		+ " TP_B_WDATE, "
		+ " TP_B_DEL, "
		+ " TP_B_READCOUNT, "
		+ " TP_B_FILENAME, "
		+ " TP_B_NEWFILENAME " 
		+ " FROM TP_BBS ";
		
		
		String sWord = "";
		if(choice.equals("title")) {
			sWord = " WHERE TP_B_TITLE LIKE '%" + search + "%' ";
		}else if(choice.equals("content")) {
			sWord = " WHERE TP_B_CONTENT LIKE '%" + search + "%' ";
		}else if(choice.equals("writer")) {
			sWord = " WHERE TP_B_ID= '" + search + "' ";
		} 
		sql = sql + sWord;
		
		sql = sql + " ORDER BY RNUM ASC ) ";
		
		sql = sql + "  WHERE RNUM >= ? AND RNUM <= ? ";
	//	sql = sql + " WHERE TP_B_DEL=0 AND RNUM >= ? AND RNUM <= ? ";
		
		int start, end;
		start = 1 + 10 * page;
		end = 10 + 10 * page;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<PdsDto> list = new ArrayList<PdsDto>();
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/4 getBbsPagingList success");
				
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, start);
			psmt.setInt(2, end);
			System.out.println("2/4 getBbsPagingList success");
			
			rs = psmt.executeQuery();			
			System.out.println("3/4 getBbsPagingList success");
			
			while(rs.next()) {
				PdsDto dto = new PdsDto( 
							rs.getInt(1), 
							rs.getString(2), 
							rs.getString(3), 
							rs.getInt(4), 
							rs.getInt(5), 
							rs.getInt(6),
							rs.getString(7), 
							rs.getString(8), 
							rs.getString(9),
							rs.getInt(10),
							rs.getInt(11),
							rs.getString(12),
							rs.getString(13));		
				list.add(dto);
			}			
			System.out.println("4/4 getBbsPagingList success");
			
		} catch (SQLException e) {	
			System.out.println("getBbsPagingList fail");
			e.printStackTrace();
		} finally {			
			DBClose.close(conn, psmt, rs);			
		}
		System.out.println(list.toString());
		return list;
	}
	*/
	
	
	/*
	public boolean deletePds(int seq) {
		
		String sql = "DELETE FROM TP_BBS "
				+ " WHERE TP_B_SEQ=?";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 삭제해 다오");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			System.out.println("2/3 삭제해 다오");
			
			count = psmt.executeUpdate();
			System.out.println("3/3 삭제헤ㅐ 다오");
			
		} catch (Exception e) {		
			System.out.println("fail 삭제해 다오");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, null);			
		}
		
		
		
		return count>0?true:false;
	}
	
	*/
	
	
public int getAllPbs(String choice, String search) {
	
	String sql = " SELECT COUNT(*) FROM TP_BBS WHERE TP_B_DEL=0 ";

	String sqlWord = "";
	if(choice.equals("title")) {
		sqlWord = " AND TP_B_TITLE LIKE '%" + search.trim() + "%' ";
	}else if(choice.equals("writer") && !search.equals("")) {
		sqlWord = " AND TP_B_ID='" + search.trim() + "'";
	}else if(choice.equals("content")) {
		sqlWord = " AND TP_B_CONTENT LIKE '%" + search.trim() + "%' ";
	}
	sql += sqlWord;

	Connection conn = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	int len = 0;
    
	try {
		conn = DBConnection.getConnection();
		System.out.println("1/3 S getAllPbs");
		psmt = conn.prepareStatement(sql);
		rs = psmt.executeQuery();
		System.out.println("2/3 S getAllPbs");
		if(rs.next()) {
			len = rs.getInt(1);
		}            
		System.out.println("3/3 S getAllPbs");
	} catch (Exception e) {
		System.out.println("getAllPds fail");
		e.printStackTrace();
	} finally {
		DBClose.close(conn, psmt, rs);;            
	}
	return len;        
}


	/* @SuppressWarnings("resource") */
	public boolean answer(int seq, PdsDto pdsDto) {
				System.out.println(pdsDto.toString());
				// update
				String sql1 = " UPDATE TP_BBS "
						+ "	SET TP_B_STEP=TP_B_STEP+1 "
						+ " WHERE TP_B_REF=(SELECT TP_B_REF FROM TP_BBS WHERE TP_B_SEQ=? ) "
						+ "		AND TP_B_STEP>(SELECT TP_B_STEP FROM TP_BBS WHERE TP_B_SEQ=? )";		
				
				// insert
				String sql2 = " INSERT INTO TP_BBS "
								+ " (TP_B_SEQ, TP_B_ID, TP_B_NAME, "
								+ " TP_B_REF, TP_B_STEP, TP_B_DEPTH, "
								+ " TP_B_TITLE, TP_B_CONTENT, TP_B_WDATE, TP_B_DEL, TP_B_READCOUNT , TP_B_FILENAME , TP_B_NEWFILENAME) "
								+ " VALUES(TP_BBS_SEQ.NEXTVAL, ?, ?, "
								+ "        (SELECT TP_B_REF FROM TP_BBS WHERE TP_B_SEQ=?), "	// REF
								+ "			(SELECT TP_B_STEP FROM TP_BBS WHERE TP_B_SEQ=?) + 1, " // STEP
								+ "         (SELECT TP_B_DEPTH FROM TP_BBS WHERE TP_B_SEQ=?) + 1, " // DEPTH
								+ "         ?, ?, SYSDATE, 0, 0, 0, 0) ";    
		
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
					psmt.setString(1, pdsDto.getId());
					psmt.setString(2, pdsDto.getName());
					psmt.setInt(3, seq);
					psmt.setInt(4, seq);
					psmt.setInt(5, seq);
					psmt.setString(6, pdsDto.getTitle());
					psmt.setString(7, pdsDto.getContent());
					//psmt.setString(8, pdsDto.getFilename());
			//		psmt.setString(9, pdsDto.getNewfilename());
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










