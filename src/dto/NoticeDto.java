package dto;

import java.io.Serializable;

public class NoticeDto implements Serializable{
	private int noseq;
	private String noId;
	private String noDate;
	private String noTitle;
	private String noContent;
	private int noReadc;
	
	public NoticeDto() {
		
	}

	public NoticeDto(int noseq, String noId, String noDate, String noTitle, String noContent, int noReadc) {
		super();
		this.noseq = noseq;
		this.noId = noId;
		this.noDate = noDate;
		this.noTitle = noTitle;
		this.noContent = noContent;
		this.noReadc = noReadc;
	}
	
	

	public NoticeDto(String noId, String noTitle, String noContent) {
		super();
		this.noId = noId;
		this.noTitle = noTitle;
		this.noContent = noContent;
	}

	public int getNoseq() {
		return noseq;
	}

	public void setNoseq(int noseq) {
		this.noseq = noseq;
	}

	public String getNoId() {
		return noId;
	}

	public void setNoId(String noId) {
		this.noId = noId;
	}

	public String getNoDate() {
		return noDate;
	}

	public void setNoDate(String noDate) {
		this.noDate = noDate;
	}

	public String getNoTitle() {
		return noTitle;
	}

	public void setNoTitle(String noTitle) {
		this.noTitle = noTitle;
	}

	public String getNoContent() {
		return noContent;
	}

	public void setNoContent(String noContent) {
		this.noContent = noContent;
	}

	public int getNoReadc() {
		return noReadc;
	}

	public void setNoReadc(int noReadc) {
		this.noReadc = noReadc;
	}

	@Override
	public String toString() {
		return "NoticeDto [noseq=" + noseq + ", noId=" + noId + ", noDate=" + noDate + ", noTitle=" + noTitle
				+ ", noContent=" + noContent + ", noReadc=" + noReadc + "]";
	}
	
	
	
}
