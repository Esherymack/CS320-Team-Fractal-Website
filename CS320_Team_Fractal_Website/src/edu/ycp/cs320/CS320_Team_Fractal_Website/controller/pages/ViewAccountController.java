package edu.ycp.cs320.CS320_Team_Fractal_Website.controller.pages;

import java.util.ArrayList;

import edu.ycp.cs320.CS320_Team_Fractal_Website.database.DatabaseProvider;
import edu.ycp.cs320.CS320_Team_Fractal_Website.database.IDatabase;
import edu.ycp.cs320.CS320_Team_Fractal_Website.database.InitDatabase;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.User;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Fractal;

public class ViewAccountController {
	private IDatabase database;
	
	public ViewAccountController(){
		InitDatabase.init();
		database = DatabaseProvider.getInstance();
	}

	public User getUserByUserName(String username){
		return database.getUserByUsername(username);
	}

	public User getUserByUserNamePassword(String username, String password){
		return database.getUserByUsernameAndPassword(username, password);
	}
	
	public ArrayList<Fractal> getUserFractals(String username)
	{
		return database.getAllFractalsByUsername(username);
	}
	
	/** 
	 * Deletes the fractal of the model of account that is logged in
	 * @param id the fractal_id of the fractal
	 * @return true if fractal was deleted successfully, false otherwise
	 */
	public boolean deleteFractal(int id, User user){
		return user.deleteFractal(id, database);
	}
}
