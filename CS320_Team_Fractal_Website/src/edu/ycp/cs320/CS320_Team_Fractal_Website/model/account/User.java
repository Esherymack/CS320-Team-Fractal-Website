package edu.ycp.cs320.CS320_Team_Fractal_Website.model.account;

public abstract class User 
{
	private String username;
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	
	public User()
	{
		this("", "", "", "", "");
	}
	
	public User(String username, String firstname, String lastname, String email, String password)
	{
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public void setFirstname(String firstname)
	{
		this.firstname = firstname;
	}
	
	public String getFirstname()
	{
		return firstname;
	}
	
	public void setLastname(String lastname)
	{
		this.lastname = lastname;
	}
	
	public String getLastname()
	{
		return lastname;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	public String getPassword()
	{
		return password;
	}
}
