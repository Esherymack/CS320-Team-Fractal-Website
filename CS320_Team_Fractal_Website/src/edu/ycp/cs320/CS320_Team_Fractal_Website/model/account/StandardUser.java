package edu.ycp.cs320.CS320_Team_Fractal_Website.model.account;

import edu.ycp.cs320.CS320_Team_Fractal_Website.database.IDatabase;

public class StandardUser extends User{

	public static final String TYPE = "StandardUser";
	
	public StandardUser(String username, String firstname, String lastname, String email, String password, String code){
		super(username, firstname, lastname, email, password, code);
	}
	
	public StandardUser(){
		super();
	}
	
	@Override
	public boolean deleteFractal(int fractalId, IDatabase db){
		String username = db.getUsernameByFractalId(fractalId);
		if(!username.equals(getUsername())) return false;
		
		db.deleteFractal(fractalId);
		
		return true;
	}
	
	@Override
	public String getType() {
		return TYPE;
	}
}
