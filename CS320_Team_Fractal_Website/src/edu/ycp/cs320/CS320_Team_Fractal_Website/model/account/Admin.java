package edu.ycp.cs320.CS320_Team_Fractal_Website.model.account;

import edu.ycp.cs320.CS320_Team_Fractal_Website.database.IDatabase;

public class Admin extends User{
	
	public static final String TYPE = "Admin";
	
	public Admin(String username, String firstname, String lastname, String email, String password, String code){
		super(username, firstname, lastname, email, password, code);
	}
	
	public Admin(){
		super();
	}
	
	@Override
	public boolean deleteFractal(int fractalId, IDatabase db){
		db.deleteFractal(fractalId);
		
		return true;
	}
	
	@Override
	public String getType() {
		return TYPE;
	}
}
