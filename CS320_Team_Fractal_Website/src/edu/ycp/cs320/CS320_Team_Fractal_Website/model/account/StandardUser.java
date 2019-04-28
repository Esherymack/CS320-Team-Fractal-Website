package edu.ycp.cs320.CS320_Team_Fractal_Website.model.account;

public class StandardUser extends User{

	public StandardUser(String username, String firstname, String lastname, String email, String password, String code){
		super(username, firstname, lastname, email, password, code);
	}
	
	public StandardUser(){
		super();
	}
}
