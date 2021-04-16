package dto;

import java.io.Serializable;

public class TpListDto implements Serializable{
	
	private int rnum;
	
	private int seq;
	private String place;
	
	private String id;
	private String name;
	private String title;
	private String content;
	
	private String wdate;
	private int del;
	private int readcount;
	
	private String concept;
	
	private String filename;
	private String newFilename;


	public TpListDto() {
	}
	
	public TpListDto(String place, String id, String name, String title, String content, String concept) {
		super();
		this.place = place;
		this.id = id;
		this.name = name;
		this.title = title;
		this.content = content;
		this.concept = concept;
	}

	

	public TpListDto(int rnum, int seq, String place, String id, String name, String title, String content,
			String wdate, int readcount, String concept, String filename, String newFilename) {
		super();
		this.rnum = rnum;
		this.seq = seq;
		this.place = place;
		this.id = id;
		this.name = name;
		this.title = title;
		this.content = content;
		this.wdate = wdate;
		this.readcount = readcount;
		this.concept = concept;
		this.filename = filename;
		this.newFilename = newFilename;
	}

	public TpListDto(String place, String id, String name, String title, String content, String concept,
			String filename, String newFilename) {
		super();
		this.place = place;
		this.id = id;
		this.name = name;
		this.title = title;
		this.content = content;
		this.concept = concept;
		this.filename = filename;
		this.newFilename = newFilename;
	}

	public TpListDto(int seq, String place, String id, String name, String title, String content, String wdate, int del,
			int readcount, String concept) {
		super();
		this.seq = seq;
		this.place = place;
		this.id = id;
		this.name = name;
		this.title = title;
		this.content = content;
		this.wdate = wdate;
		this.del = del;
		this.readcount = readcount;
		this.concept = concept;
	}
	

	public TpListDto(int rnum, int seq, String place, String id, String name, String title, String content,
			String wdate, int del, int readcount, String concept) {
		super();
		this.rnum = rnum;
		this.seq = seq;
		this.place = place;
		this.id = id;
		this.name = name;
		this.title = title;
		this.content = content;
		this.wdate = wdate;
		this.del = del;
		this.readcount = readcount;
		this.concept = concept;
	}

	public TpListDto(int rnum, int seq, String place, String id, String name, String title, String content,
			String wdate, int del, int readcount, String concept, String filename, String newFilename) {
		super();
		this.rnum = rnum;
		this.seq = seq;
		this.place = place;
		this.id = id;
		this.name = name;
		this.title = title;
		this.content = content;
		this.wdate = wdate;
		this.del = del;
		this.readcount = readcount;
		this.concept = concept;
		this.filename = filename;
		this.newFilename = newFilename;
	}
	
	

	public TpListDto(int seq, String place, String id, String name, String title, String content, String wdate, int del,
			int readcount, String concept, String filename, String newFilename) {
		super();
		this.seq = seq;
		this.place = place;
		this.id = id;
		this.name = name;
		this.title = title;
		this.content = content;
		this.wdate = wdate;
		this.del = del;
		this.readcount = readcount;
		this.concept = concept;
		this.filename = filename;
		this.newFilename = newFilename;
	}

	public TpListDto(String id, String title, String content) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
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


	public String getWdate() {
		return wdate;
	}


	public void setWdate(String wdate) {
		this.wdate = wdate;
	}


	public int getDel() {
		return del;
	}


	public void setDel(int del) {
		this.del = del;
	}


	public int getReadcount() {
		return readcount;
	}


	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}


	public String getConcept() {
		return concept;
	}


	public void setConcept(String concept) {
		this.concept = concept;
	}

	public int getRnum() {
		return rnum;
	}

	public void setRnum(int rnum) {
		this.rnum = rnum;
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

	@Override
	public String toString() {
		return "TpListDto [rnum=" + rnum + ", seq=" + seq + ", place=" + place + ", id=" + id + ", name=" + name
				+ ", title=" + title + ", content=" + content + ", wdate=" + wdate + ", del=" + del + ", readcount="
				+ readcount + ", concept=" + concept + ", filename=" + filename + ", newFilename=" + newFilename + "]";
	}
	
	
	
	
	
	
	
	
		
	

	
}
