package com.icia.memberboard.dto;

import org.springframework.web.multipart.MultipartFile;

public class MemberDTO {
	public String mid;
	public String mpassword;
	public String mname;
	public String mphone;
	public String memail;
	private MultipartFile mfile;
	private String mfilename;
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getMpassword() {
		return mpassword;
	}
	public void setMpassword(String mpassword) {
		this.mpassword = mpassword;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getMphone() {
		return mphone;
	}
	public void setMphone(String mphone) {
		this.mphone = mphone;
	}
	public String getMemail() {
		return memail;
	}
	public void setMemail(String memail) {
		this.memail = memail;
	}
	public MultipartFile getMfile() {
		return mfile;
	}
	public void setMfile(MultipartFile mfile) {
		this.mfile = mfile;
	}
	public String getMfilename() {
		return mfilename;
	}
	public void setMfilename(String mfilename) {
		this.mfilename = mfilename;
	}
	@Override
	public String toString() {
		return "MemberDTO [mid=" + mid + ", mpassword=" + mpassword + ", mname=" + mname + ", mphone=" + mphone
				+ ", memail=" + memail + ", mfile=" + mfile + ", mfilename=" + mfilename + "]";
	}
}