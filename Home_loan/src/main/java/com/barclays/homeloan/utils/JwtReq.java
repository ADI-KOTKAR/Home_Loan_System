package com.barclays.homeloan.utils;

public class JwtReq {
	
	private String username;
	private String password;
	
	public JwtReq() {}
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public JwtReq(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	

}
