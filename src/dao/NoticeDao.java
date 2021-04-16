package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;
import dto.CommentDto;
import dto.MatchDto;
import dto.MustEatDto;
import dto.NoticeDto;

public class NoticeDao {
	public static NoticeDao dao = new NoticeDao();
	

	private NoticeDao() {
		DBConnection.initConnection();
	}
	
	public static NoticeDao getInstance() {
		return dao;
	}
	
	public boolean writeNotice(NoticeDto dto) {
		
		String sql = "INSERT INTO TP_NOTICE("
				+ " NOTICE_SEQ, NOTICE_ID, NOTICE_DATE, "
				+ " NOTICE_TITLE, NOTICE_CONTENT,"
				+ " NOTICE_READC)"
				+ " VALUES(TP_NOTICE_SEQ.NEXTVAL, ?, SYSDATE, "
				+ " ?, ?, 0)";
		
		 Connection conn = null;
		  PreparedStatement psmt = null;		
		  int count = 0;
		  
		  System.out.println("1/3 writeNotice");
				
		try {
			conn = DBConnection.getConnection();
			System.out.println("2/3 writeNotice");
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, dto.getNoId());;
			psmt.setString(2, dto.getNoTitle());
			psmt.setString(3, dto.getNoContent());
			
			count = psmt.executeUpdate();	
			System.out.println("3/3 writeNotice");
			
			
		} catch (SQLException e) {
			System.out.println("fail writeNotice");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBClose.close(conn, psmt, null);
		}
		  return count>0?true:false;
		
		
		
		
	}
	
	public List<NoticeDto> getnoticeData() {
		  String sql = "SELECT "
		  		+ " NOTICE_SEQ, "
		  		+ " NOTICE_ID,"
		  		+ " NOTICE_DATE,"
		  		+ " NOTICE_TITLE,"
		  		+ " NOTICE_CONTENT,"
		  		+ " NOTICE_READC"
		  		+ " FROM TP_NOTICE"
		  		+ " ORDER BY NOTICE_SEQ DESC";
		  		
		  
		  	Connection conn =null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			
			List<NoticeDto> list = new ArrayList<NoticeDto>();
			
			
			NoticeDto dto = null;
			
			System.out.println("1/5  getData");
			try {
				conn = DBConnection.getConnection();
				psmt = conn.prepareStatement(sql);
				
				rs = psmt.executeQuery();
				
				System.out.println("2/5  getData");
				
				while(rs.next()) {
					dto = new NoticeDto(
							rs.getInt(1), 
							rs.getString(2), 
							rs.getString(3),
							rs.getString(4),
							rs.getString(5), 
							rs.getInt(6) );

					
					list.add(dto);
				}
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally {
				DBClose.close(conn, psmt, rs);
			}
		  return list;
	  }
	
	public NoticeDto getNotice() {
	
		String sql = "SELECT "
		  		+ " NOTICE_SEQ, "
		  		+ " NOTICE_ID,"
		  		+ " NOTICE_DATE,"
		  		+ " NOTICE_TITLE,"
		  		+ " NOTICE_CONTENT,"
		  		+ " NOTICE_READC"
		  		+ " FROM TP_NOTICE";
		  		
		  
		  	Connection conn =null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			
			
			
			
			NoticeDto dto = null;
			
			System.out.println("1/5  getData");
			try {
				conn = DBConnection.getConnection();
				psmt = conn.prepareStatement(sql);
				
				rs = psmt.executeQuery();
				
				System.out.println("2/5  getData");
				
				while(rs.next()) {
					dto = new NoticeDto(
							rs.getInt(1), 
							rs.getString(2), 
							rs.getString(3),
							rs.getString(4),
							rs.getString(5), 
							rs.getInt(6) );

					
				}
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally {
				DBClose.close(conn, psmt, rs);
			}
		  return dto;
		
		
	}
	
	
	public NoticeDto getNoticedetail(int seq) {
		
		String sql = "SELECT "
		  		+ " NOTICE_SEQ, "
		  		+ " NOTICE_ID,"
		  		+ " NOTICE_DATE,"
		  		+ " NOTICE_TITLE,"
		  		+ " NOTICE_CONTENT,"
		  		+ " NOTICE_READC"
		  		+ " FROM TP_NOTICE"
		  		+ " WHERE NOTICE_SEQ=?";
		  		
		  
		  	Connection conn =null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			
			
			
			
			NoticeDto dto = null;
			
			System.out.println("1/5  getData");
			try {
				conn = DBConnection.getConnection();
				psmt = conn.prepareStatement(sql);
				psmt.setInt(1, seq);
				
				
				rs = psmt.executeQuery();
				
				System.out.println("2/5  getData");
				
				while(rs.next()) {
					dto = new NoticeDto(
							rs.getInt(1), 
							rs.getString(2), 
							rs.getString(3),
							rs.getString(4),
							rs.getString(5), 
							rs.getInt(6) );

					
				}
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally {
				DBClose.close(conn, psmt, rs);
			}
		  return dto;
		
		
	}
	
	
	public void readcount(int seq) {
		String sql = " UPDATE TP_NOTICE "
				+ " SET NOTICE_READC=NOTICE_READC+1"
				+ " WHERE NOTICE_SEQ=? ";
				
				
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
	
	public boolean updateNotice(int seq, NoticeDto dto) {

		String sql = " UPDATE TP_NOTICE "
				+ " SET NOTICE_ID=?,"
				+ " NOTICE_TITLE=?, "
				+ " NOTICE_CONTENT=?, "
				+ " NOTICE_DATE=SYSDATE"
				+ " WHERE NOTICE_SEQ=? ";
		
		Connection conn=null;
		PreparedStatement psmt=null;
		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 S updateNotice");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getNoId());
			psmt.setString(2, dto.getNoTitle());
			psmt.setString(3, dto.getNoContent());
			psmt.setInt(4, seq);
			
			
			System.out.println("2/3 S updateNotice ");

			count = psmt.executeUpdate();			
			System.out.println("3/3 S updateNotice ");
			
		} catch (SQLException e) {
			System.out.println("fail updateNotice ");
			e.printStackTrace();
		} finally {
		}
				
		return count>0?true:false;
	}
	public boolean deleteNotice(int seq) {

		String sql = " DELETE FROM TP_NOTICE "
				+ " WHERE NOTICE_SEQ=? ";
		
		Connection conn=null;
		PreparedStatement psmt=null;
		
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 S deleteNotice");
			
			psmt = conn.prepareStatement(sql);
	
			psmt.setInt(1, seq);
			
			
			System.out.println("2/3 S deleteNotice ");

			count = psmt.executeUpdate();			
			System.out.println("3/3 S deleteNotice ");
			
		} catch (SQLException e) {
			System.out.println("fail deleteNotice ");
			e.printStackTrace();
		} finally {
		}
				
		return count>0?true:false;
	}
	
}



