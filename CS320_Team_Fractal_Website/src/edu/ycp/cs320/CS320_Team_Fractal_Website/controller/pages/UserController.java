package edu.ycp.cs320.CS320_Team_Fractal_Website.controller.pages;

import java.util.ArrayList;

import edu.ycp.cs320.CS320_Team_Fractal_Website.database.IDatabase;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.User;

public class UserController{
	private User model;
	
	public UserController(){
		this.model = null;
	}
	
	public User getModel(){
		return model;
	}
	public void setModel(User model){
		this.model = model;
	}
	/**
	 * Attempts to log into the web site with the password and username of the model
	 * @return true if the log in was successful, false otherwise
	 */
	public boolean User(IDatabase db)
	{
		//try to find the account in the database
		User a = db.getAccountByUsernamePassword(model.getUsername(), model.getPassword());
		
		//could not find username
		if(a == null)
		{ 
			return false;
		}
		//passwords match, return true
		if(a.getPassword().equals(model.getPassword()))
		{
			return true;
		}
		//passwords do not match, return false
		return false;
	}
	
	/**
	 * Attempts to create an account with the model's username, password, and email
	 * @return true if the account was created successfully, false otherwise
	 */
	public boolean createAccount(IDatabase db){
		
		ArrayList<User> accounts = db.getAccounts();

		//first check to make sure an account with the requested username doesn't already exist
		for(User a : accounts){
			if(a.getUsername().equals(model.getUsername())) return false;
		}
		
		//the requested username doesn't already exist, so create the account
		// Account newAccount = new StandardUser(model.getUsername(), model.getPassword(), model.getEmail());
		User newAccount = new User();
		db.addUser(newAccount);
		
		return true;
	}
	
}
