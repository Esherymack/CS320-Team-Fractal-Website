package edu.ycp.cs320.CS320_Team_Fractal_Website.model.pages;

public class LogIn{
	
	private String username;
	private String password;
	private String email;
	
	public LogIn(){
		username = null;
		password = null;
		email = null;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	public String getUsername(){
		return username;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	public String getPassword(){
		return password;
	}
	
	public String getEmail(){
		return email;
	}
	public void setEmail(String email){
		this.email = email;
	}
}
