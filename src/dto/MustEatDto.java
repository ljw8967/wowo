package dto;

import java.io.Serializable;

public class MustEatDto implements Serializable {
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
		private String regdate;
		private int like;
		
		public MustEatDto() {
			
		}
		
		
		public MustEatDto(int rnum, int seq, String place, String id, String name, String title, String content,
				String filename, String newFilename, int readcount, int downcount, String regdate, int like) {
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
			this.regdate = regdate;
			this.like = like;
		}
		

		public MustEatDto(int rnum, int seq, String place, String id, String name, String title, String content,
				String filename, String newFilename, int readcount, int downcount, String regdate) {
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
			this.regdate = regdate;
		}

		public MustEatDto(int seq, String place, String id, String name, String title, String content, String filename,
				String newFilename, int readcount, int downcount, String regdate, int like) {
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
			this.regdate = regdate;
			this.like = like;
		}

		public MustEatDto(String place, String id, String name, String title, String content, String filename,
				String newFilename) {
			super();
			this.place = place;
			this.id = id;
			this.name = name;
			this.title = title;
			this.content = content;
			this.filename = filename;
			this.newFilename = newFilename;
		}
		
		
		

		public MustEatDto(String place, String id, String title, String content, String filename, String newFilename) {
			super();
			this.place = place;
			this.id = id;
			this.title = title;
			this.content = content;
			this.filename = filename;
			this.newFilename = newFilename;
		}
		
		public int getLike() {
			return like;
		}


		public void setLike(int like) {
			this.like = like;
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

		public String getRegdate() {
			return regdate;
		}

		public void setRegdate(String regdate) {
			this.regdate = regdate;
		}

		@Override
		public String toString() {
			return "MustEatDto [seq=" + seq + ", place=" + place + ", id=" + id + ", name=" + name + ", title=" + title
					+ ", content=" + content + ", filename=" + filename + ", newFilename=" + newFilename
					+ ", readcount=" + readcount + ", downcount=" + downcount + ", regdate=" + regdate + "]";
		}
		
		

}