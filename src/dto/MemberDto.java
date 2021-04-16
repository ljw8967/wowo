package dto;

import java.io.Serializable;
/*
 
 */

public class MemberDto implements Serializable {
	
	private int rnum;
	private int seq;
	private String id;
	private String pwd;
	private String name;
	private String email;
	private String joinDate;
	private String lastLogin;
	private int del;
	private int auth;	// 사용자:3 관리자:1
	private String userPic;
	private String newuserPic;
	
	public MemberDto() {
		
	}

	public MemberDto(MemberDto dto) {
		
	}
	
	public MemberDto(String id, String pwd, String name, String email, String userPic) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
		this.userPic = userPic;
	}

	public MemberDto(String id, String pwd, String name, String email, String userPic, String newuserPic) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
		this.userPic = userPic;
		this.newuserPic = newuserPic;
	}

	public MemberDto(int seq, String id, String pwd, String name, String email, int auth, String joinDate) {
		super();
		this.seq = seq;
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
		this.auth = auth;
		this.joinDate = joinDate;
	}
	

	
	public MemberDto(int seq, String id, String name, String email, int auth, String joinDate) {
		super();
		this.seq = seq;
		this.id = id;
		this.name = name;
		this.email = email;
		this.auth = auth;
		this.joinDate = joinDate;
	}

	public MemberDto(int seq, String id, String pwd, String email,  String name, String joinDate, String lastLogin, int del, int auth, String userPic, String newuserPic) {
		super();
		this.seq = seq;
		this.id = id;
		this.pwd = pwd;
		this.email = email;
		this.name = name;
		this.joinDate = joinDate;
		this.lastLogin = lastLogin;
		this.del = del;
		this.auth = auth;
		this.userPic = userPic;
		this.newuserPic = newuserPic;
	}
	
	
	public MemberDto(String id, String pwd, String name, String email, int del, int auth) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.email = email;
		this.del = del;
		this.auth = auth;
	}

	
	public MemberDto(int rnum, int seq, String id, String name, String email, String joindate) {
		super();
		this.rnum = rnum;
		this.seq = seq;
		this.id = id;
		this.name = name;
		this.email = email;
		this.joinDate = joindate;
	}



	public int getRnum() {
		return rnum;
	}



	public void setRnum(int rnum) {
		this.rnum = rnum;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

	public int getAuth() {
		return auth;
	}

	public void setAuth(int auth) {
		this.auth = auth;
	}
	
	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	public String getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}
	
	public String getUserPic() {
		return userPic;
	}

	public void setUserPic(String userPic) {
		this.userPic = userPic;
	}
	
	public String getNewuserPic() {
		return newuserPic;
	}

	public void setNewuserPic(String newuserPic) {
		this.newuserPic = newuserPic;
	}

	@Override
	public String toString() {
		return "MemberDto [seq=" + seq + ", id=" + id + ", pwd=" + pwd + ", name=" + name + ", email=" + email
				+ ", joinDate=" + joinDate + ", lastLogin=" + lastLogin + ", del=" + del + ", auth=" + auth
				+ ", userPic=" + userPic + ", newuserPic=" + newuserPic + "]";
	}

	
	

	
}
	
	


	
	

