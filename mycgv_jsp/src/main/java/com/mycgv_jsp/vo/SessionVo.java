package com.mycgv_jsp.vo;

public class SessionVo {
	int loginresult;
	String name, id, pass;
	
	public int getLoginresult() {
		return loginresult;
	}
	public void setLoginresult(int loginresult) {
		this.loginresult = loginresult;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
}
