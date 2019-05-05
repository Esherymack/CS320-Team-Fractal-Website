package edu.ycp.cs320.CS320_Team_Fractal_Website.model.account;

import edu.ycp.cs320.CS320_Team_Fractal_Website.database.IDatabase;

public abstract class User 
{
	private String username;
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private boolean IsVerified;
	private String VerificationCode;
	
	public User()
	{
		this("", "", "", "", "", "");
	}
	
	public User(String username, String firstname, String lastname, String email, String password, String code)
	{
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.VerificationCode = code;
		this.IsVerified = false;
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
	
	public boolean getIsVerified()
	{
		return IsVerified;
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
	
	public void setIsVerified(boolean verification)
	{
		this.IsVerified = verification;
	}
	
	public void setVerificationCode(String code)
	{
		this.VerificationCode = code;
	}
	
	public String getVerificationCode()
	{
		return this.VerificationCode;
	}
	
	public String getPassword()
	{
		return password;
	}

	
	/**
	 * Delete a fractal in the database
	 * @param fractalId the id of the fractal
	 * @return true if the fractal was deleted, false otherwise. Also always returns false if this user doesn't have permission to delete the specified fractal
	 */
	public abstract boolean deleteFractal(int fractalId, IDatabase db);
	
	/**
	 * Get the type of this user, defines the permissions of this user
	 * @return the type
	 */
	public abstract String getType();
	
	/**
	 * Attempts to create an admin account
	 * @param password the password needed to make an admin account
	 * @return an admin account if the correct password was given, a StandardUser otherwise
	 */
	public static boolean isAdminPasswod(String password){
		return password.equals("12345");
	}
}
