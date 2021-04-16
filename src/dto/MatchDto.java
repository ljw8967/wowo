package dto;

import java.io.Serializable;

public class MatchDto implements Serializable{
	private int rnum;
	private int seq;
	private String place;
	private String id;
	private String name;
	private String title;	
	private String content;
	private String filename;
	private String newFilename;
	private int readcount;
	private int downcount;
	private String firDate;
	private String lasDate;
	
	public MatchDto() {
		
	}
	
	public MatchDto(int rnum, int seq, String place, String id, String name, String title, String content,
			String filename, String newFilename, int readcount, int downcount, String firDate, String lasDate) {
		super();
		this.rnum = rnum;
		this.seq = seq;
		this.place = place;
		this.id = id;
		this.name = name;
		this.title = title;
		this.content = content;
		this.filename = filename;
		this.newFilename = newFilename;
		this.readcount = readcount;
		this.downcount = downcount;
		this.firDate = firDate;
		this.lasDate = lasDate;
	}

	public MatchDto(String place, String id, String name, String title, String content, String filename,
			String newFilename, String firDate, String lasDate) {
		super();
		this.place = place;
		this.id = id;
		this.name = name;
		this.title = title;
		this.content = content;
		this.filename = filename;
		this.newFilename = newFilename;
		this.firDate = firDate;
		this.lasDate = lasDate;
	}
	
	

	public MatchDto(int seq, String place, String id, String name, String title, String content, String filename,
			String newFilename, int readcount, int downcount, String firDate, String lasDate) {
		super();
		this.seq = seq;
		this.place = place;
		this.id = id;
		this.name = name;
		this.title = title;
		this.content = content;
		this.filename = filename;
		this.newFilename = newFilename;
		this.readcount = readcount;
		this.downcount = downcount;
		this.firDate = firDate;
		this.lasDate = lasDate;
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

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getNewFilename() {
		return newFilename;
	}

	public void setNewFilename(String newFilename) {
		this.newFilename = newFilename;
	}

	public int getReadcount() {
		return readcount;
	}

	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}

	public int getDowncount() {
		return downcount;
	}

	public void setDowncount(int downcount) {
		this.downcount = downcount;
	}

	public String getFirDate() {
		return firDate;
	}

	public void setFirDate(String firDate) {
		this.firDate = firDate;
	}

	public String getLasDate() {
		return lasDate;
	}

	public void setLasDate(String lasDate) {
		this.lasDate = lasDate;
	}
	
	
	
	
}
