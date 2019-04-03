package edu.ycp.cs320.CS320_Team_Fractal_Website.model.account;

public class Admin extends User{
	
	public Admin(String username, String firstname, String lastname, String email, String password){
		super(username, firstname, lastname, email, password);
	}
	
	public Admin(){
		super();
	}
}
