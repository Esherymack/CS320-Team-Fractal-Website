package edu.ycp.cs320.CS320_Team_Fractal_Website.model.account;

public abstract class Account{
	
	private String username;
	private String password;
	
	private Gallery fractalGallery;
	
	public Account(String username, String password){
		this.username = username;
		this.password = password;
		
		this.fractalGallery = new Gallery();
	}
	
	public Gallery GalleryGetFractalGallery(){
		return fractalGallery;
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
	
}
