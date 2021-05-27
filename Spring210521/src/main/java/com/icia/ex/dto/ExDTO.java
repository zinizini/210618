package com.icia.ex.dto;

public class ExDTO {
	// dto 필드는 반드시 input의 name과 똑같이 할 것
	// 
	// 필드 선언 후 getter/setter, toString 선언
	private String col1;
	private String col2;
	private String col3;
	public String getCol1() {
		return col1;
	}
	public void setCol1(String col1) {
		this.col1 = col1;
	}
	public String getCol2() {
		return col2;
	}
	public void setCol2(String col2) {
		this.col2 = col2;
	}
	public String getCol3() {
		return col3;
	}
	public void setCol3(String col3) {
		this.col3 = col3;
	}
	@Override
	public String toString() {
		return "ExDTO [col1=" + col1 + ", col2=" + col2 + ", col3=" + col3 + "]";
	}
	
	

}
