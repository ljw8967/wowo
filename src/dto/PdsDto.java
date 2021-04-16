package dto;

import java.io.Serializable;

// PDS - Public Domain Software = 자료실
public class PdsDto implements Serializable {


	private int seq;
	private String id;		// 작성자
	private String name;	// 닉네임
	
	private int ref;		// 그룹번호
	private int step;		// 행번호
	private int depth;		// 깊이
	
	private String title;	// 제목
	private String content;	// 내용
	private String wdate;	// 작성일
	
	private int del;		// 삭제
	private int readcount;	// 조회수
	private String filename; 	// 이미지 올리기 
	private String newfilename; 	//이미지 저장 
	
	
	public PdsDto() {
		
	}


	public PdsDto(int seq, String id, String name, int ref, int step, int depth, String title, String content,
			String wdate, int del, int readcount, String filename, String newfilename) {
		super();
		this.seq = seq;
		this.id = id;
		this.name = name;
		this.ref = ref;
		this.step = step;
		this.depth = depth;
		this.title = title;
		this.content = content;
		this.wdate = wdate;
		this.del = del;
		this.readcount = readcount;
		this.filename = filename;
		this.newfilename = newfilename;
	}

	
	
	
	

	

	public PdsDto(int seq, String id, String name, int ref, int step, int depth, String title, String content,
			String wdate) {
		super();
		this.seq = seq;
		this.id = id;
		this.name = name;
		this.ref = ref;
		this.step = step;
		this.depth = depth;
		this.title = title;
		this.content = content;
		this.wdate = wdate;
	}


	public PdsDto(int seq, String title, String content, String filename, String newfilename) {
		super();
		this.seq = seq;
		this.title = title;
		this.content = content;
		this.filename = filename;
		this.newfilename = newfilename;
	}


	public PdsDto(String id, String name, String title, String content, String filename, String newfilename) {
		super();
		this.id = id;
		this.name = name;
		this.title = title;
		this.content = content;
		this.filename = filename;
		this.newfilename = newfilename;
	}
	
	


	public PdsDto(String id, String name, String title, String content, String filename) {
		super();
		this.id = id;
		this.name = name;
		this.title = title;
		this.content = content;
		this.filename = filename;
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


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getRef() {
		return ref;
	}


	public void setRef(int ref) {
		this.ref = ref;
	}


	public int getStep() {
		return step;
	}


	public void setStep(int step) {
		this.step = step;
	}


	public int getDepth() {
		return depth;
	}


	public void setDepth(int depth) {
		this.depth = depth;
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


	public String getFilename() {
		return filename;
	}


	public void setFilename(String filename) {
		this.filename = filename;
	}


	public String getNewfilename() {
		return newfilename;
	}


	public void setNewfilename(String newfilename) {
		this.newfilename = newfilename;
	}


	@Override
	public String toString() {
		return "PdsDto [seq=" + seq + ", id=" + id + ", name=" + name + ", ref=" + ref + ", step=" + step + ", depth="
				+ depth + ", title=" + title + ", content=" + content + ", wdate=" + wdate + ", del=" + del
				+ ", readcount=" + readcount + ", filename=" + filename + ", newfilename=" + newfilename + "]";
	}

	
	

}
