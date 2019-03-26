package edu.ycp.cs320.CS320_Team_Fractal_Website.model.pages;

public class LogIn {
private String username, password, email;
	
	public LogIn() 
	{}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public String getEmail()
	{
		return email;
	}
}
