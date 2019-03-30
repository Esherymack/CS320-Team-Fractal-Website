package edu.ycp.cs320.CS320_Team_Fractal_Website.model;

public class User 
{
	private int UserId;
	private String Username;
	private String Firstname;
	private String Lastname;
	private String Email;
	private String Password;
	
	public User()
	{}
	
	public void setUserId(int userId)
	{
		this.UserId = userId;
	}
	
	public int getUserId()
	{
		return UserId;
	}
	
	public void setUsername(String username)
	{
		this.Username = username;
	}
	
	public String getUsername()
	{
		return Username;
	}
	
	public void setFirstname(String firstname)
	{
		this.Firstname = firstname;
	}
	
	public String getFirstname()
	{
		return Firstname;
	}
	
	public void setLastname(String lastname)
	{
		this.Lastname = lastname;
	}
	
	public String getLastname()
	{
		return Lastname;
	}
	
	public void setEmail(String email)
	{
		this.Email = email;
	}
	
	public String getEmail()
	{
		return Email;
	}
	
	public void setPassword(String password)
	{
		this.Password = password;
	}
	
	public String getPassword()
	{
		return Password;
	}
}
