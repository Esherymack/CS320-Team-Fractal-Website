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
		if(db == null) return false;
		
		db.deleteFractal(fractalId);
		
		return true;
	}
	
	@Override
	public boolean renameFractal(int fractalId, String newName, IDatabase db){
		if(db == null) return false;
		
		db.renameFractal(fractalId, newName);
		
		return true;
	}
	
	@Override
	public String getType() {
		return TYPE;
	}
}
