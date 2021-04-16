package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBClose;
import db.DBConnection;
import dto.MemberDto;

public class MemberDao {

	private static MemberDao dao = new MemberDao();

	private MemberDao() {
		DBConnection.initConnection();
	}

	public static MemberDao getInstance() {
		return dao;
	}

	public MemberDto getUserData(String id) {
		String sql = " SELECT TP_M_SEQ," + " TP_M_ID, " + " TP_M_PWD, " + " TP_M_NAME, " + " TP_M_EMAIL, "
				+ " TP_M_AUTH, " + " TP_M_JOINDATE " + " FROM TP_MEMBER " + " WHERE TP_M_ID=? ";

		System.out.println(sql);
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		MemberDto dto = null;

		try {
			conn = DBConnection.getConnection();
			System.out.println("1/5 getUserData");
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);

			System.out.println("2/5 getUserData");

			rs = psmt.executeQuery();

			System.out.println("3/5 getUserData");

			if (rs.next()) {

				dto = new MemberDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getInt(6), rs.getString(7));

				System.out.println(dto.toString());
			}
			// MemberDto(int seq, String id, String pwd, String name, String email, String
			// joinDate)

			// MemberDto dto1 = new MemberDto(seq, id, pwd, name, email, joinDate)
			System.out.println("4/5 getUserData");

		} catch (SQLException e) {
			System.out.println("fail getUserData");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		return dto;
	}

	public boolean insert(MemberDto dto) {

		String sql = " INSERT INTO TP_MEMBER( TP_M_SEQ,"
											+ " TP_M_ID, "
											+ "TP_M_PWD, "
											+ "TP_M_NAME, "
											+ "TP_M_EMAIL,"
											+ " TP_M_DEL, "
											+ "TP_M_AUTH,"
											+ " TP_M_JOINDATE,"
											+ " TP_M_USERPIC,"
											+ " TP_M_NEWUSERPIC) "
				+ " VALUES( SEQ_TP_MEMBER.NEXTVAL, ?, ?, ?, ?, 0, 0, SYSDATE, ?, NULL) ";

		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;

		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 insert success");

			psmt = conn.prepareStatement(sql);
			System.out.println("2/3 insert success");

			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPwd());
			psmt.setString(3, dto.getEmail());
			psmt.setString(4, dto.getName());
			psmt.setString(5, dto.getUserPic());

			count = psmt.executeUpdate();
			System.out.println("3/3 insert success");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Insert fail");
		} finally {
			DBClose.close(conn, psmt, null);
		}

		return count > 0 ? true : false;
	}

	public MemberDto login(MemberDto dto) {

		String sql = " SELECT TP_M_ID, TP_M_PWD, TP_M_NAME, TP_M_EMAIL, " + " TP_M_DEL, TP_M_AUTH " + " FROM TP_MEMBER "
				+ " WHERE TP_M_ID=? AND TP_M_PWD=? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		MemberDto mem = null;

		try {

			conn = DBConnection.getConnection();
			System.out.println("1/3 login success");

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getPwd());
			System.out.println("2/3 login success");

			rs = psmt.executeQuery();

			if (rs.next()) {

				String id = rs.getString(1);
				String pwd = rs.getString(2);
				String name = rs.getString(3);
				String email = rs.getString(4);
				int del = rs.getInt(5);
				int auth = rs.getInt(6);

				mem = new MemberDto(id, pwd, name, email, del, auth);

			}
			System.out.println("3/3 login success");

		} catch (SQLException e) {
			System.out.println("login Fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		return mem;

	}

	public MemberDto getData(String id) {

		MemberDto mom = null;

		String sql = " SELECT TP_M_ID, TP_M_NAME, TP_M_EMAIL, " + " TP_M_DEL, TP_M_AUTH " + " FROM TP_MEMBER"
				+ " WHERE TP_M_ID=? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {

			conn = DBConnection.getConnection();

			psmt = conn.prepareStatement(sql);

			psmt.setString(1, id);

			rs = psmt.executeQuery();

			if (rs.next()) {

				mom = new MemberDto();

				mom.setId(rs.getString("id"));

				mom.setPwd(rs.getString("pwd"));

				mom.setName(rs.getString("name"));

				mom.setEmail(rs.getString("email"));
			}

		} catch (Exception e) {

			System.out.println("getData err : " + e);

		} finally {

			DBClose.close(conn, psmt, rs);

		}

		return mom;

	}

	// 회원 탈퇴와 비밀번호 확인 0405

	public boolean deleteConfirm(String id, String pwd) {

		Connection conn = null;
		PreparedStatement psmt = null;

		int count = 0;

		String sql = "DELETE FROM TP_MEMBER " + " WHERE TP_M_ID=? AND TP_M_PWD =?";

		try {

			conn = DBConnection.getConnection();

			psmt = conn.prepareStatement(sql);

			psmt.setString(1, id);
			psmt.setString(2, pwd);

			count = psmt.executeUpdate();

		} catch (Exception e) {

			System.out.println("deleteConfirm err : " + e);

		} finally {

			DBClose.close(conn, psmt, null);

		}

		return count > 0 ? true : false;

	}

	// 회원 탈퇴 - 탈퇴하기 (안쓰는거)

	@SuppressWarnings("resource")
	public int deleteMember(String id, String pw) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String dbpw = ""; // DB상의 비밀번호를 담아둘 변수
		int x = -1;

		try {
			// 비밀번호 조회
			StringBuffer query1 = new StringBuffer();
			query1.append("SELECT TP_M_PWD FROM TP_MEMBER WHERE ID=?");

			// 회원 삭제
			StringBuffer query2 = new StringBuffer();
			query2.append("DELETE FROM TP_MEMBER WHERE ID=?");

			conn = DBConnection.getConnection();

			// 자동 커밋을 false로 한다.--+

			conn.setAutoCommit(false);

			// 1. 아이디에 해당하는 비밀번호를 조회한다.
			pstmt = conn.prepareStatement(query1.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dbpw = rs.getString("password");
				if (dbpw.equals(pw)) // 입력된 비밀번호와 DB비번 비교
				{
					// 같을경우 회원삭제 진행
					pstmt = conn.prepareStatement(query2.toString());
					pstmt.setString(1, id);
					pstmt.executeUpdate();
					conn.commit();
					x = 1; // 삭제 성공
				} else {
					x = 0; // 비밀번호 비교결과 - 다름
				}
			}

			return x;

		} catch (Exception sqle) {
			try {
				conn.rollback(); // 오류시 롤백
			} catch (SQLException e) {
				e.printStackTrace();
			}
			throw new RuntimeException(sqle.getMessage());
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	} // end deleteMember

	/**
	 * 로그인시 아이디, 비밀번호 체크 메서드
	 * 
	 * @param id 로그인할 아이디
	 * @param pw 비밀번호
	 * @return x : loginCheck() 수행 후 결과값
	 */
	public int loginCheck(String id, String pw) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String dbPW = ""; // db에서 꺼낸 비밀번호를 담을 변수
		int x = -1;

		try {
			// 쿼리 - 먼저 입력된 아이디로 DB에서 비밀번호를 조회한다.
			StringBuffer query = new StringBuffer();
			query.append("SELECT PASSWORD FROM JSP_MEMBER WHERE ID=?");

			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) // 입려된 아이디에 해당하는 비번 있을경우
			{
				dbPW = rs.getString("password"); // 비번을 변수에 넣는다.

				if (dbPW.equals(pw))
					x = 1; // 넘겨받은 비번과 꺼내온 비번 비교. 같으면 인증성공
				else
					x = 0; // DB의 비밀번호와 입력받은 비밀번호 다름, 인증실패

			} else {
				x = -1; // 해당 아이디가 없을 경우
			}

			return x;

		} catch (Exception sqle) {
			throw new RuntimeException(sqle.getMessage());
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
					pstmt = null;
				}
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	} // end loginCheck()

	public boolean memUpdate(String id, String pwd, String name, String email) {

		String sql = " UPDATE TP_MEMBER SET" + " TP_M_PWD=?, TP_M_NAME=?, TP_M_EMAIL=?" + " WHERE TP_M_ID=? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;

		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 S memUpdate");

			psmt = conn.prepareStatement(sql);
			psmt.setString(1, pwd);
			psmt.setString(2, name);
			psmt.setString(3, email);
			psmt.setString(4, id);
			System.out.println("2/3 S memUpdate");

			count = psmt.executeUpdate();
			System.out.println("3/3 S memUpdate");

		} catch (SQLException e) {
			System.out.println("memUpdate Fail");

			e.printStackTrace();
		}
		return count > 0 ? true : false;
	}

	public String pwdCheck(String pwd) {

		String sql = " SELECT TP_M_PWD " + " FROM TP_MEMBER " + " WHERE TP_M_PWD=? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		String pwdCheck = "";

		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 S pwdCheck");

			psmt = conn.prepareStatement(sql);

			psmt.setString(1, pwd.trim());
			System.out.println("2/3 S pwdCheck");

			rs = psmt.executeQuery();

			if (rs.next()) {
				pwdCheck = rs.getString(1); // 비번 찾음
			}
			System.out.println("3/3 S pwdCheck");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		return pwdCheck;

	}

	public boolean idCheck(String id) {
		String sql = " SELECT TP_M_ID "
				+ " FROM TP_MEMBER "
				+ " WHERE TP_M_ID=? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		boolean idcheck = false;		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 idcheck success");	
		
			psmt = conn.prepareStatement(sql);
			System.out.println("2/3 idcheck success");	
			psmt.setString(1, id.trim());
			
			
			rs = psmt.executeQuery();
			System.out.println("3/3 idcheck success");	
			
			if(rs.next()) {
				idcheck = true;				
			}
		} catch (SQLException e) {		
			System.out.println("idcheck fail");	
			e.printStackTrace();
		}finally {
			DBClose.close(conn, psmt, rs);
		}
		return idcheck;
	}

	public List<MemberDto> getAllMemPaging (int page){
		System.out.println("getAllMemPaging");
		String sql = "  SELECT RNUM, "
				+ "A.TP_M_SEQ, "
				+ "A.TP_M_ID, "
				+ "A.TP_M_NAME, "
				+ "A.TP_M_EMAIL, "
				+ "A.TP_M_JOINDATE" 
				+ " FROM (" + 
				" SELECT ROW_NUMBER()OVER(ORDER BY TP_M_SEQ DESC)AS RNUM, "
				+ "TP_M_SEQ, TP_M_ID, TP_M_NAME, TP_M_EMAIL, TP_M_JOINDATE" + 
				" FROM TP_MEMBER" + 
				" ORDER BY TP_M_SEQ ASC) A"
				
				
				+ " WHERE RNUM >= ? AND RNUM <= ? ";
		
		
		int start, end;
		start = 1 + 10 * page;
		end = 10 + 10 * page;		
		
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<MemberDto> list = new ArrayList<MemberDto>();
		
	
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/4 getAllMemPaging success");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, start);
			psmt.setInt(2, end);
			System.out.println("2/4 getAllMemPaging success");
			
			rs = psmt.executeQuery();
			System.out.println("3/4 getAllMemPaging success");
			
			while(rs.next()) {
				int i = 1;
				MemberDto dto = new MemberDto(
											   rs.getInt(i++),
											   rs.getInt(i++), 
											   rs.getString(i++), 
											   rs.getString(i++), 
											   rs.getString(i++), 
											   rs.getString(i++));
				list.add(dto);
			}
			
			System.out.println("4/4 getallmem success");
			
		} catch (SQLException e) {
			System.out.println("getallmem fail");
			e.printStackTrace();
		}finally {
			DBClose.close(conn, psmt, rs);
		}	
		
		return list;
	}
	
	public int getAllMem() {
		String sql = " SELECT COUNT(*) FROM TP_MEMBER ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		int len = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 getAllMem success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("2/3 getAllMem success");
			
			rs = psmt.executeQuery();
			if(rs.next()) {
				len = rs.getInt(1);
			}
			System.out.println("3/3 getAllMem success");
			
		} catch (SQLException e) {
			System.out.println("getAllMem fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);			
		}
		return len;
	}

	public boolean deleteMem (String id) {
	      
	      String sql = " DELETE FROM TP_MEMBER" + 
	            " WHERE TP_M_ID=? ";
	      
	      Connection conn = null;
	      PreparedStatement psmt = null;
	      ResultSet rs = null;
	      
	      boolean deleteMem = false;
	      try {
	         conn = DBConnection.getConnection();
	         System.out.println("1/3 deleteMem success");   
	         conn.setAutoCommit(false);
	       
	         
	         psmt = conn.prepareStatement(sql);
	         System.out.println("2/3 deleteMem success");   
	         psmt.setString(1, id.trim());
	         
	         
	         rs = psmt.executeQuery();
	         System.out.println("3/3 deleteMem success");   
	         
	         conn.commit();
	         
	         if(rs.next()) {
	            deleteMem = true;            
	         }
	      } catch (SQLException e) {      
	         System.out.println("deleteMem fail");   
	         try {
					conn.rollback();
				} catch (SQLException e1) {				
					e1.printStackTrace();
				}	
	         e.printStackTrace();
	      }finally {
	    	  
	    	  try {
					conn.setAutoCommit(true);
				} catch (SQLException e) {				
					e.printStackTrace();
				}		
	    	  
	    	  
	    	  
	         DBClose.close(conn, psmt, rs);
	         
	         
	         
	      }
	      return deleteMem;
	      
	      
	      
	   }
	public String updateMemPwd (String id) {
	      String sql = " SELECT TP_M_PWD "
	            + " FROM TP_MEMBER "
	            + " WHERE TP_M_ID=? ";
	      
	      Connection conn = null;
	      PreparedStatement psmt = null;
	      ResultSet rs = null;
	      
	      String pwdCheck = "";   
	      
	         try {         

	            conn = DBConnection.getConnection();
	            System.out.println("1/3 updateMemPwd success");   
	            
	            psmt = conn.prepareStatement(sql);
	            System.out.println("2/3 updateMemPwd success");   
	                        
	            psmt.setString(1, id.trim());
	            
	            rs = psmt.executeQuery();
	            System.out.println("3/3 updateMemPwd success");   
	            
	            if(rs.next()){
	               pwdCheck = rs.getString(1);
	            }
	            
	         } catch (SQLException e) {
	            System.out.println("updateMemPwd fail");   
	            e.printStackTrace();
	         }finally {
	            DBClose.close(conn, psmt, rs);
	         }
	         return pwdCheck;
	   
	   }
}
