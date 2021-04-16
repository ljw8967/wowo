package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;
import dto.TpListDto;

public class TpListDao {
	
	private static TpListDao dao = new TpListDao();
	
	private TpListDao() {
		DBConnection.initConnection();
	}
	
	public static TpListDao getInstance() {
		return dao;
	}
	
	public List<TpListDto> getTpList() {
		
		String sql = " SELECT TP_L_SEQ, TP_L_PLACE, TP_L_ID, TP_L_NAME, TP_L_TITLE,"
					+ "TP_L_CONTENT, TP_L_WDATE, TP_L_DEL, TP_L_READCOUNT, TP_L_CONCEPT"
					+ " FROM TP_LIST "
					+ " ORDER BY TP_L_SEQ DESC";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<TpListDto> list = new ArrayList<TpListDto>();
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/4 getTpList success");
			psmt = conn.prepareStatement(sql);
			System.out.println("2/4 getTpList success");
			rs = psmt.executeQuery();
			System.out.println("3/4 getTpList success");
			while (rs.next()) {
				TpListDto dto = new TpListDto(rs.getInt(1),
											rs.getString(2),
											rs.getString(3),
											rs.getString(4),
											rs.getString(5),
											rs.getString(6),
											rs.getString(7),
											rs.getInt(8),
											rs.getInt(9),
											rs.getString(10));
			list.add(dto);
			System.out.println("4/4 getTpList success");
			}
		} catch (SQLException e) {
			System.out.println("getTpList fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		return list;

	}
	
	public boolean writeTplist(TpListDto wdto) {
	      
	      String sql = " INSERT INTO TP_LIST "
	               + " (TP_L_SEQ, TP_L_PLACE, TP_L_ID, TP_L_NAME, TP_L_TITLE, "
	               + " TP_L_CONTENT, TP_L_WDATE, TP_L_DEL, TP_L_READCOUNT, TP_L_CONCEPT, TP_L_FILENAME, TP_L_NEWFILENAME) "
	               + " VALUES( TP_LIST_SEQ.NEXTVAL, ?, ?, ?, ?, "
	               + " ?, SYSDATE, 0, 0, ?, ?, ?)";
	      
	      Connection conn = null;
	      PreparedStatement psmt = null;
	      
	      int count = 0;
	      System.out.println("writeID : " + wdto.getId());
	      System.out.println("writename : " + wdto.getName());
	   
	      try {
	         conn = DBConnection.getConnection();
	         System.out.println("1/3 writeTplist success");
	         psmt = conn.prepareStatement(sql);
	         psmt.setString(1, wdto.getPlace());
	         psmt.setString(2, wdto.getId());
	         psmt.setString(3, wdto.getName());
	         psmt.setString(4, wdto.getTitle());
	         psmt.setString(5, wdto.getContent());
	         psmt.setString(6, wdto.getConcept());
	         psmt.setString(7, wdto.getFilename());
	         psmt.setString(8, wdto.getNewFilename());
	         System.out.println("2/3 writeTplist success");
	         count = psmt.executeUpdate();
	         System.out.println("3/3 writeTplist success");
	      } catch (SQLException e) {
	    	  System.out.println("writeTplist fail");
	         e.printStackTrace();
	      }finally {
	         
	         DBClose.close(conn, psmt, null);
	      }
	      return count > 0?true:false;
	   }
	
	public TpListDto getTplistdetail(int seq) {
		
		String sql = " SELECT "
				+ " TP_L_SEQ, TP_L_PLACE, TP_L_ID, TP_L_NAME, TP_L_TITLE,"
				+ " TP_L_CONTENT, TP_L_WDATE, TP_L_DEL, TP_L_READCOUNT, TP_L_CONCEPT, TP_L_FILENAME, TP_L_NEWFILENAME"
				+ " FROM TP_LIST "
				+ " WHERE TP_L_SEQ=? ";
		 
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		TpListDto dto = null;

       try {
       	conn = DBConnection.getConnection();
       	System.out.println("1/5 getTplistdetail success");
       	
			psmt = conn.prepareStatement(sql);
			System.out.println("2/5 getTplistdetail success");
			psmt.setInt(1, seq);
			System.out.println("3/5 getTplistdetail success");
			
			rs = psmt.executeQuery();
			System.out.println("4/5 getTplistdetail success");
			
			while(rs.next()) {
				dto = new TpListDto(rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getInt(8),
						rs.getInt(9),
						rs.getString(10),
						rs.getString(11),
						rs.getString(12));
		            readCount(dto.getSeq());
		            
		         }
			System.out.println("5/5 getTplistdetail success");
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("getbbs fail");
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		
		return dto;
	}

	public void readCount(int seq) {
		String sql = " UPDATE TP_LIST "
					+ " SET TP_L_READCOUNT = TP_L_READCOUNT + 1 "
					+ " WHERE TP_L_SEQ = ? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 readCount success");
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			System.out.println("2/3 readCount success");
			
			psmt.executeUpdate();
			System.out.println("3/3 readCount success");
		} catch (SQLException e) {
			System.out.println("readCount fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, null);
		}
		
	}
	
	public boolean updateTplist(int seq, String place,String title, String content, String concept, String filename, String newfilename) {
		String sql = " UPDATE TP_LIST SET"
					+ " TP_L_PLACE=?, TP_L_TITLE=?, TP_L_CONTENT=?, TP_L_CONCEPT=?, TP_L_FILENAME=?, TP_L_NEWFILENAME=?"
					+ " WHERE TP_L_SEQ=? ";
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		conn = DBConnection.getConnection();
		System.out.println("1/3 updateTplist success");
		System.out.println("seq=" + seq);
		System.out.println("place=" + place);
		System.out.println("title=" + title);
		System.out.println("content= " + content);
		System.out.println("concept= " + concept);
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, place);
			psmt.setString(2, title);
			psmt.setString(3, content);
			psmt.setString(4, concept);
			psmt.setString(5, filename);
			psmt.setString(6, newfilename);
			psmt.setInt(7, seq);
			System.out.println("2/3 updateTplist success");
			count = psmt.executeUpdate();
			System.out.println("3/3 updateTplist success");
		} catch (SQLException e) {
			System.out.println("updateTplist fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, null);
		}
		
		return count>0?true:false;
	}
	
	public boolean deleteTplist(int seq) {
		String sql = " UPDATE TP_LIST "
					+ " SET TP_L_DEL = 1 "
					+ " WHERE TP_L_SEQ = ?";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		
		int count = 0;
		   
	      try {
	         conn = DBConnection.getConnection();
	         System.out.println("1/3 deleteTplist Success");
	         
	         psmt = conn.prepareStatement(sql);
	         psmt.setInt(1, seq);
	         System.out.println("2/3 deleteTplist Success");
	         
	         count = psmt.executeUpdate();
	         System.out.println("3/3 deleteTplist Success");
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	         System.out.println("deleteTplist fail");
	      }finally {
	         
	         DBClose.close(conn, psmt, null);
	      }
	      return count > 0?true:false;
	}
	
	public List<TpListDto> getTplistSearchList(String choice, String search) {
		
		String sql = " SELECT TP_L_SEQ, TP_L_PLACE, TP_L_ID, TP_L_NAME, TP_L_TITLE,"
				+ " TP_L_CONTENT, TP_L_WDATE, TP_L_DEL, TP_L_READCOUNT, TP_L_CONCEPT"
				+ " FROM TP_LIST "
				+ " ORDER BY TP_L_SEQ DESC";
			String sWord = "";
			if(choice.equals("title")) {
				sWord = " WHERE TITLE LIKE '%" + search + "%' ";
			} else if(choice.equals("content")) {
				sWord = " WHERE CONTENT LIKE '%" + search + "%' ";
			} else if(choice.equals("writer")) {
				sWord = " WHERE ID = '" + search + "' ";
			}
			sql = sql + sWord;
			/* sql = sql + " ORDER BY TP_L_REF DESC, STEP ASC "; */
			
			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			
			List<TpListDto> list = new ArrayList<TpListDto>();
			
			try {
				conn = DBConnection.getConnection();
				System.out.println("1/4 getTplistSearchList success");
				psmt = conn.prepareStatement(sql);
				System.out.println("2/4 getTplistSearchList success");
				rs = psmt.executeQuery();
				System.out.println("3/4 getTplistSearchList success");
				while (rs.next()) {
					TpListDto dto = new TpListDto(rs.getInt(1),
												rs.getString(2),
												rs.getString(3),
												rs.getString(4),
												rs.getString(5),
												rs.getString(6),
												rs.getString(7),
												rs.getInt(8),
												rs.getInt(9),
												rs.getString(10));
					list.add(dto);
				}
				System.out.println("4/4 getTplistSearchList success");
			
			} catch(SQLException e) {
				System.out.println("getTplistSearchList fail");
				e.printStackTrace();
			} finally {
				DBClose.close(conn, psmt, rs);
			}
			
			return list;
			
		}
	
	public List<TpListDto> getTpPagingList(String choice, String search, int page) {
		
	String sql = " SELECT RNUM, TP_L_SEQ, TP_L_PLACE, TP_L_ID, TP_L_NAME, TP_L_TITLE, "
			+ " TP_L_CONTENT, TP_L_WDATE, TP_L_DEL, TP_L_READCOUNT, TP_L_CONCEPT, TP_L_FILENAME, TP_L_NEWFILENAME "
			+ " FROM ";
				
	sql += " (SELECT ROW_NUMBER()OVER(ORDER BY TP_L_SEQ DESC) AS RNUM, " 
			+ "	TP_L_SEQ, TP_L_PLACE, TP_L_ID, TP_L_NAME, TP_L_TITLE, "
			+ "	TP_L_CONTENT, TP_L_WDATE, TP_L_DEL, TP_L_READCOUNT, TP_L_CONCEPT, TP_L_FILENAME, TP_L_NEWFILENAME " 
			+ " FROM TP_LIST ";
	

		String sWord = "WHERE TP_L_DEL=0";
		
		  if(choice.equals("title")) { 
			  sWord = " WHERE TP_L_TITLE LIKE '%" + search + "%' AND TP_L_DEL=0"; 
		  } else if(choice.equals("content")) { 
			  sWord = " WHERE TP_L_CONTENT LIKE '%" + search + "%' AND TP_L_DEL=0"; 
		  } else if(choice.equals("writer")) { 
			  sWord = " WHERE TP_L_ID = '" + search + "' AND TP_L_DEL=0"; 
		  }
		
		 
		sql = sql + sWord;
		sql = sql + " ORDER BY RNUM ASC ) ";
		sql = sql + " WHERE RNUM >= ? AND RNUM <= ? ";
		
		int start, end;
		start = 1 + 9 * page;
		end = 9 + 9 * page;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<TpListDto> list = new ArrayList<TpListDto>();
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/4 getTpPagingList success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, start);
			psmt.setInt(2, end);
			System.out.println("2/4 getTpPagingList success");
			
			rs = psmt.executeQuery();
			System.out.println("3/4 getTpPagingList success");
			
			while (rs.next()) {
				TpListDto dto = new TpListDto(rs.getInt(1),
											rs.getInt(2),
											rs.getString(3),
											rs.getString(4),
											rs.getString(5),
											rs.getString(6),
											rs.getString(7),
											rs.getString(8),
											rs.getInt(9),
											rs.getInt(10),
											rs.getString(11),
											rs.getString(12),
											rs.getString(13));
				list.add(dto);
			}
			System.out.println("4/4 getTpPagingList success");
		
		} catch(SQLException e) {
			System.out.println("getTpPagingList fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		
		return list;
		
	}
	
	public List<TpListDto> getTpCPagingList(String choice, String search, int page, String concept) {
		
		String sql = " SELECT RNUM, TP_L_SEQ, TP_L_PLACE, TP_L_ID, TP_L_NAME, TP_L_TITLE, "
				+ " TP_L_CONTENT, TP_L_WDATE, TP_L_DEL, TP_L_READCOUNT, TP_L_CONCEPT, TP_L_FILENAME, TP_L_NEWFILENAME "
				+ " FROM ";
					
		sql += " (SELECT ROW_NUMBER()OVER(ORDER BY TP_L_SEQ DESC) AS RNUM, " 
				+ "	TP_L_SEQ, TP_L_PLACE, TP_L_ID, TP_L_NAME, TP_L_TITLE, "
				+ "	TP_L_CONTENT, TP_L_WDATE, TP_L_DEL, TP_L_READCOUNT, TP_L_CONCEPT, TP_L_FILENAME, TP_L_NEWFILENAME " 
				+ " FROM TP_LIST ";
		

			String sWord = "WHERE TP_L_DEL=0";
			
			  if(choice.equals("title")) { 
				  sWord = " WHERE TP_L_TITLE LIKE '%" + search + "%' AND TP_L_DEL=0"; 
			  } else if(choice.equals("content")) { 
				  sWord = " WHERE TP_L_CONTENT LIKE '%" + search + "%' AND TP_L_DEL=0"; 
			  } else if(choice.equals("writer")) { 
				  sWord = " WHERE TP_L_ID = '" + search + "' AND TP_L_DEL=0"; 
			  }
			
			 
			sql = sql + sWord;
			sql = sql + " ORDER BY RNUM ASC ) ";
			sql = sql + " WHERE RNUM >= ? AND RNUM <= ? AND TP_L_CONCEPT = ?";
			
			int start, end;
			start = 1 + 9 * page;
			end = 9 + 9 * page;
			
			Connection conn = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			
			List<TpListDto> list = new ArrayList<TpListDto>();
			
			try {
				conn = DBConnection.getConnection();
				System.out.println("1/4 getTpPagingList success");
				
				psmt = conn.prepareStatement(sql);
				psmt.setInt(1, start);
				psmt.setInt(2, end);
				psmt.setString(3, concept);
				
				System.out.println("2/4 getTpPagingList success");
				
				rs = psmt.executeQuery();
				System.out.println("3/4 getTpPagingList success");
				
				while (rs.next()) {
					TpListDto dto = new TpListDto(rs.getInt(1),
												rs.getInt(2),
												rs.getString(3),
												rs.getString(4),
												rs.getString(5),
												rs.getString(6),
												rs.getString(7),
												rs.getString(8),
												rs.getInt(9),
												rs.getInt(10),
												rs.getString(11),
												rs.getString(12),
												rs.getString(13));
					list.add(dto);
				}
				System.out.println("4/4 getTpPagingList success");
			
			} catch(SQLException e) {
				System.out.println("getTpPagingList fail");
				e.printStackTrace();
			} finally {
				DBClose.close(conn, psmt, rs);
			}
			
			return list;
			
		}
	
	public int getAllTplist(String choice, String search) {
		String sql = " SELECT COUNT(*) FROM TP_LIST ";
		
		String sWord = "WHERE TP_L_DEL=0";
		
			  if(choice.equals("title")) { 
				  sWord = " WHERE TP_L_TITLE LIKE '%" + search + "%' AND TP_L_DEL=0"; 
			  } else if(choice.equals("content")) { 
				  sWord = " WHERE TP_L_CONTENT LIKE '%" + search + "%' AND TP_L_DEL=0"; 
			  } else if(choice.equals("writer")) { 
				  sWord = " WHERE TP_L_ID = '" + search + "' AND TP_L_DEL=0"; 
			  }
			
		sql = sql + sWord;
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		int len = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 getAllTplist success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("2/3 getAllTplist success");
			
			rs = psmt.executeQuery();
			if(rs.next()) {
				len = rs.getInt(1);
			}
			System.out.println("3/3 getAllTplist success");
		} catch (SQLException e) {
			System.out.println("getAllTplist fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		
		return len;
	}
	
	public List<TpListDto> tprank() {
		
		System.out.println("TpListDao, tprank start");
		
		String sql = " SELECT RNUM, TP_L_SEQ, TP_L_PLACE, TP_L_ID, TP_L_NAME, TP_L_TITLE, "
				+ " TP_L_CONTENT, TP_L_WDATE, TP_L_DEL, TP_L_READCOUNT, TP_L_CONCEPT, TP_L_FILENAME, TP_L_NEWFILENAME "
				+ " FROM ";
					
		sql += " (SELECT ROW_NUMBER()OVER(ORDER BY TP_L_READCOUNT DESC) AS RNUM, " 
				+ "	TP_L_SEQ, TP_L_PLACE, TP_L_ID, TP_L_NAME, TP_L_TITLE, "
				+ "	TP_L_CONTENT, TP_L_WDATE, TP_L_DEL, TP_L_READCOUNT, TP_L_CONCEPT, TP_L_FILENAME, TP_L_NEWFILENAME " 
				+ " FROM TP_LIST)"
				+ " WHERE RNUM <=3  AND TP_L_READCOUNT !=0 AND TP_L_DEL = 0";
		
		System.out.println("tprank sql : " + sql);
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<TpListDto> list = new ArrayList<TpListDto>();
		
		System.out.println("1/3 tprank success");
		
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			System.out.println("2/3 tprank success");
			
			while(rs.next()) {
				int i = 1;
			TpListDto dto = new TpListDto(
										rs.getInt(i++), //RNUM
										rs.getInt(i++), //SEQ
										rs.getString(i++), //PLACE
										rs.getString(i++), //ID
										rs.getString(i++), //NAME
										rs.getString(i++), //TITLE
										rs.getString(i++), //CONTENT
										rs.getString(i++), //WDATE
										rs.getInt(i++), //DEL
										rs.getInt(i++), //READCOUNT
										rs.getString(i++),//CONCEPT
										rs.getString(i++),//FILENAME
										rs.getString(i++) //NEWFILENAME
										);
			list.add(dto);
			
			}
			System.out.println("3/3 tprank success");
		} catch (SQLException e) {
			System.out.println("tprank fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		return list;
		
		
		
		
		
		
	}
	
	
	
	
	
	
	
	

	
	
	
	
	
}
