package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;

//import java.util.List;

import db.DBConnection;
//import dto.CommentDto;
import dto.CommentDto;
import dto.MustEatDto;

public class CommentDao {
	public static CommentDao dao = new CommentDao();
	
	private CommentDao() {
		DBConnection.initConnection();
	}
	
	
	public static CommentDao getInstance() {
		return dao;
	}
	
	/*
	 * public List<CommentDto> getCommList(){
	 * 
	 * 
	 * return List<CommentDto>; }
	 */
	  
	  public boolean writeComment(CommentDto dto) {
		  
		  System.out.println(dto.toString());
		  
		  String sql = "INSERT INTO TP_COMMENT(COMMENT_SEQ,  COMMENT_ME_SEQ, COMMENT_TP_SEQ,"
		  		+ " COMMENT_MA_SEQ, COMMENT_ID, COMMENT_DATE, COMMENT_CONTENT)"
		  		+ " VALUES(TP_COMMENT_SEQ.NEXTVAL, ?, NULL, NULL, "
		  		+ "	?, SYSDATE, ?)";
		  
		  Connection conn = null;
		  PreparedStatement psmt = null;		
		  int count = 0;
		  
		  System.out.println("1/3 writeComment");
				
		try {
			conn = DBConnection.getConnection();
			System.out.println("2/3 writeComment");
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, dto.getCommListSeq());;
			psmt.setString(2, dto.getCommId());
			psmt.setString(3, dto.getCommContent());
			
			count = psmt.executeUpdate();	
			System.out.println("3/3 writeComment");
			
			
		} catch (SQLException e) {
			System.out.println("fail writeComment");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(conn, psmt, null);
		}
		  return count>0?true:false;
		  
	  }
	  
 public boolean writetpComment(CommentDto dto) {
		  
		  System.out.println(dto.toString());
		  
		  String sql = "INSERT INTO TP_COMMENT(COMMENT_SEQ,  COMMENT_ME_SEQ, COMMENT_TP_SEQ,"
			  		+ " COMMENT_MA_SEQ, COMMENT_ID, COMMENT_DATE, COMMENT_CONTENT)"
			  		+ " VALUES(TP_COMMENT_SEQ.NEXTVAL, NULL, ?, NULL, "
			  		+ "	?, SYSDATE, ?)";
		  
		  Connection conn = null;
		  PreparedStatement psmt = null;		
		  int count = 0;
		  
		  System.out.println("1/3 writeTPComment");
				
		try {
			conn = DBConnection.getConnection();
			System.out.println("2/3 writeTPComment");
			psmt = conn.prepareStatement(sql);
			
			psmt.setInt(1, dto.getCommTPSeq());
			psmt.setString(2, dto.getCommId());
			psmt.setString(3, dto.getCommContent());
			
			count = psmt.executeUpdate();	
			System.out.println("3/3 writeTPComment");
			
			
		} catch (SQLException e) {
			System.out.println("fail writeComment");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(conn, psmt, null);
		}
		  return count>0?true:false;
		  
	  }
	  
public boolean writeMatchComment(CommentDto dto) {
		  
		  System.out.println(dto.toString());
		  
		  String sql = "INSERT INTO TP_COMMENT("
		  		+ " COMMENT_SEQ,  "
		  		+ " COMMENT_ME_SEQ, "
		  		+ " COMMENT_TP_SEQ,"
		  		+ " COMMENT_MA_SEQ, "
		  		+ " COMMENT_ID, "
		  		+ " COMMENT_DATE, "
		  		+ " COMMENT_CONTENT)"
		  		+ " VALUES("
		  		+ " TP_COMMENT_SEQ.NEXTVAL,"
		  		+ " NULL,"
		  		+ " NULL,"
		  		+ " ?, "
		  		+ "	?, "
		  		+ " SYSDATE, "
		  		+ " ?)";
		  
		  Connection conn = null;
		  PreparedStatement psmt = null;		
		  int count = 0;
		  
		  System.out.println("1/4 writeMatchComment");
				
		try {
			conn = DBConnection.getConnection();
			System.out.println("2/4 writeMatchComment");
			psmt = conn.prepareStatement(sql);
			System.out.println("3/4 writeMatchComment");
			
		
			psmt.setInt(1, dto.getCommMatchseq());;
			psmt.setString(2, dto.getCommId());
			psmt.setString(3, dto.getCommContent());
			
			count = psmt.executeUpdate();	
			System.out.println("4/4 writeMatchComment");
			
			
		} catch (SQLException e) {
			System.out.println("fail writeMatchComment");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(conn, psmt, null);
		}
		  return count>0?true:false;
		  
	  }
	  
	  public List<CommentDto> getCommentData(int seq) {
		  String sql = "SELECT COMMENT_SEQ, "
		  		+ " COMMENT_ME_SEQ, "
		  		+ " COMMENT_TP_SEQ,"
		  		+ " COMMENT_MA_SEQ, "
		  		+ " COMMENT_ID,"
		  		+ " COMMENT_DATE,"
		  		+ " COMMENT_CONTENT"
		  		+ " FROM TP_COMMENT"
		  		+ " WHERE COMMENT_ME_SEQ=?"
		  		+ " ORDER BY COMMENT_SEQ ASC";
		  		
		  
		  	Connection conn =null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			
			ArrayList<CommentDto> list = new ArrayList<CommentDto>();
			
			
			CommentDto dto = null;
			
			System.out.println("1/5  getData");
			try {
				conn = DBConnection.getConnection();
				psmt = conn.prepareStatement(sql);
				
				psmt.setInt(1, seq);
				
				rs = psmt.executeQuery();
				
				System.out.println("2/5  getData");
				
				while(rs.next()) {
					dto = new CommentDto(
							rs.getInt(1), 
							rs.getInt(2), 
							rs.getInt(3),
							rs.getInt(4),
							rs.getString(5), 
							rs.getString(6), 
							rs.getString(7));
					
					list.add(dto);
				}
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally {
				DBClose.close(conn, psmt, rs);
			}
		  return list;
	  }
	  
	  public List<CommentDto> getCommenttpData(int seq) {
		  String sql = "SELECT COMMENT_SEQ, "
			  		+ " COMMENT_ME_SEQ, "
			  		+ " COMMENT_TP_SEQ,"
			  		+ " COMMENT_MA_SEQ,"
			  		+ " COMMENT_ID,"
			  		+ " COMMENT_DATE,"
			  		+ " COMMENT_CONTENT"
			  		+ " FROM TP_COMMENT"
			  		+ " WHERE COMMENT_TP_SEQ=?"
			  		+ " ORDER BY COMMENT_SEQ ASC";
		  		
		  
		  	Connection conn =null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			
			List<CommentDto> list = new ArrayList<CommentDto>();
			
			
			CommentDto dto = null;
			System.out.println("코멘트다오로 들어온 seq 값: " + seq);
			System.out.println("1/3  getcmttpData");
			try {
				conn = DBConnection.getConnection();
				psmt = conn.prepareStatement(sql);
				
				psmt.setInt(1, seq);
				
				rs = psmt.executeQuery();
				
				System.out.println("2/3  getcmttpData");
				
				while(rs.next()) {
					dto = new CommentDto(
							rs.getInt(1), 
							rs.getInt(2), 
							rs.getInt(3),
							rs.getInt(4),
							rs.getString(5), 
							rs.getString(6), 
							rs.getString(7));
					
					list.add(dto);
				}
				System.out.println(list);
				System.out.println("3/3  getcmttpData");
				
			} catch (SQLException e) {
				System.out.println("getcmttpData 대실퍀ㅋㅋ");
				e.printStackTrace();
			}finally {
				DBClose.close(conn, psmt, rs);
			}
		  return list;
	  }
	  
	  public List<CommentDto> getMatchCommentData(int seq) {
		  String sql = "SELECT COMMENT_SEQ, "
		  		+ " COMMENT_ME_SEQ, "
		  		+ " COMMENT_TP_SEQ,"
		  		+ " COMMENT_MA_SEQ, "
		  		+ " COMMENT_ID,"
		  		+ " COMMENT_DATE,"
		  		+ " COMMENT_CONTENT"
		  		+ " FROM TP_COMMENT"
		  		+ " WHERE COMMENT_MA_SEQ=?"
		  		+ " ORDER BY COMMENT_SEQ ASC";
		  		
		  
		  	Connection conn =null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			
			ArrayList<CommentDto> list = new ArrayList<CommentDto>();
			
			
			CommentDto dto = null;
			
			System.out.println("1/5  getData");
			try {
				conn = DBConnection.getConnection();
				psmt = conn.prepareStatement(sql);
				
				psmt.setInt(1, seq);
				
				rs = psmt.executeQuery();
				
				System.out.println("2/5  getData");
				
				while(rs.next()) {
					dto = new CommentDto(
							rs.getInt(1), 
							rs.getInt(2), 
							rs.getInt(3),
							rs.getInt(4),
							rs.getString(5), 
							rs.getString(6), 
							rs.getString(7));
					
					list.add(dto);
				}
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally {
				DBClose.close(conn, psmt, rs);
			}
		  return list;
	  }
	  
	  
	  public boolean deletecomment(int commNum) {
		  String sql = "DELETE FROM TP_COMMENT"
		  		+ " WHERE COMMENT_SEQ=? ";
		  
		    Connection conn = null;
			PreparedStatement psmt = null;
			int count = 0;
		  
			
		  
			try {
				conn = DBConnection.getConnection();
				System.out.println("1/5 deletecomment");
				
				psmt = conn.prepareStatement(sql);
				System.out.println("2/5 deletecomment");
				psmt.setInt(1,commNum);
				
				count = psmt.executeUpdate();
				
				
			} catch (SQLException e) {
				System.out.println("fail deletecomment");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				DBClose.close(conn, psmt, null);
			}
			return count>0?true:false;
	  }
	 
	
	
	
}
