package com.first.shop.dto;
import java.util.Date;


public class QandA {
	
	private int bno;
	private int pbno;
	private String title;
	private String content;
	private String inquirytype;
	private String writer;
	private Date regdate;
	
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public int getPbno() {
		return pbno;
	}
	public void setPbno(int pbno) {
		this.pbno = pbno;
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
	public String getInquirytype() {
		return inquirytype;
	}
	public void setInquirytype(String inquirytype) {
		this.inquirytype = inquirytype;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "QandA [bno=" + bno + ", pbno=" + pbno + ", title=" + title + ", content=" + content + ", inquirytype="
				+ inquirytype + ", writer=" + writer + ", regdate=" + regdate + "]";
	}
	
	
}
