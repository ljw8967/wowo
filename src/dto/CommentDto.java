package dto;

public class CommentDto {
	private int commNum;
	private int commListSeq;
	private int commTPSeq;
	private int commMatchseq;
	private String commId;
	private String commDate;
	private String commContent;
	
	public CommentDto() {
		
	}
	
	
	public int getCommTPSeq() {
		return commTPSeq;
	}


	public void setCommTPSeq(int commTPSeq) {
		this.commTPSeq = commTPSeq;
	}
	






	public CommentDto(int commNum, int commListSeq, int commTPSeq, int commMatchseq, String commId, String commDate,
			String commContent) {
		super();
		this.commNum = commNum;
		this.commListSeq = commListSeq;
		this.commTPSeq = commTPSeq;
		this.commMatchseq = commMatchseq;
		this.commId = commId;
		this.commDate = commDate;
		this.commContent = commContent;
	}


	public CommentDto(int commListSeq, String commId, String commContent) {
		super();
		this.commListSeq = commListSeq;
		this.commId = commId;
		this.commContent = commContent;
	}
	
	
   


	


	public CommentDto(int commNum, int commListSeq, String commId, String commDate, String commContent) {
		super();
		this.commNum = commNum;
		this.commListSeq = commListSeq;
		this.commId = commId;
		this.commDate = commDate;
		this.commContent = commContent;
	}
	


	public CommentDto(int commListSeq, int commMatchseq, String commId, String commContent) {
		super();
		this.commListSeq = commListSeq;
		this.commMatchseq = commMatchseq;
		this.commId = commId;
		this.commContent = commContent;
	}


	public CommentDto(int commTPSeq, String commId, String commDate, String commContent) {
		super();
		this.commTPSeq = commTPSeq;
		this.commId = commId;
		this.commDate = commDate;
		this.commContent = commContent;
	}


	public int getCommMatchseq() {
		return commMatchseq;
	}


	public void setCommMatchseq(int commMatchseq) {
		this.commMatchseq = commMatchseq;
	}


	public int getCommNum() {
		return commNum;
	}
	public void setCommNum(int commNum) {
		this.commNum = commNum;
	}
	public int getCommListSeq() {
		return commListSeq;
	}
	public void setCommListSeq(int commListSeq) {
		this.commListSeq = commListSeq;
	}
	public String getCommId() {
		return commId;
	}
	public void setCommId(String commId) {
		this.commId = commId;
	}
	public String getCommDate() {
		return commDate;
	}
	public void setCommDate(String commDate) {
		this.commDate = commDate;
	}
	public String getCommContent() {
		return commContent;
	}
	public void setCommContent(String commContent) {
		this.commContent = commContent;
	}


	@Override
	public String toString() {
		return "CommentDto [commNum=" + commNum + ", commListSeq=" + commListSeq + ", commTPSeq=" + commTPSeq
				+ ", commMatchseq=" + commMatchseq + ", commId=" + commId + ", commDate=" + commDate + ", commContent="
				+ commContent + "]";
	}


	
	
	
	
	
}
