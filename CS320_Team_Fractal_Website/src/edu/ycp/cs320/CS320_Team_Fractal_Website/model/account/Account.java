package edu.ycp.cs320.CS320_Team_Fractal_Website.model.account;

public abstract class Account{
	
	private String username;
	private String password;
	private String email;
	
	private Gallery fractalGallery;
	
	public Account(){
		this("", "", "");
	}
	
	public Account(String username, String password, String email){
		this.username = username;
		this.password = password;
		this.email = email;
		
		this.fractalGallery = new Gallery();
	}
	
	public Gallery getFractalGallery(){
		return fractalGallery;
	}
	public void setFractalGallery(Gallery fractalGallery){
		this.fractalGallery = fractalGallery;
	}
	
	public String getUsername(){
		return username;
	}
	public void setUsername(String username){
		this.username = username;
	}
	
	public String getPassword(){
		return password;
	}
	public void setPassword(String password){
		this.password = password;
	}
	
	public String getEmail(){
		return email;
	}
	public void setEmail(String email){
		this.email = email;
	}

	
}
