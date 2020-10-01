/*
 * 
 * 	주제(Subject): Jsp/Servlet Maven - MyBatis 3.5.5, hikariCP 3.4.2, Oracle 19g
 * 	생성일자(Create Date): 2020-10-01
 *  파일명(Filename): CompUsers.java
 * 	저자(Author): Dodo / rabbit.white at daum dot net
 * 	설명(Description): 
 * 
 * 
 */

package com.example.web.model;

public class CompUsers {

	private String username;
	private String password;
	private int enabled;
	
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
	public int getEnabled() {
		return enabled;
	}
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}
	
}
