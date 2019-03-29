package edu.ycp.cs320.CS320_Team_Fractal_Website.controller.pages;

import java.util.ArrayList;

import edu.ycp.cs320.CS320_Team_Fractal_Website.database.DatabaseProvider;
import edu.ycp.cs320.CS320_Team_Fractal_Website.database.IDatabase;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.Account;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.StandardUser;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.pages.LogIn;

public class LogInController{
	private LogIn model;
	
	public LogInController(){
		this.model = null;
	}
	
	public LogIn getModel(){
		return model;
	}
	public void setModel(LogIn model){
		this.model = model;
	}
	/**
	 * Attempts to log into the web site with the password and username of the model
	 * @return true if the log in was successful, false otherwise
	 */
	public boolean logIn(){
		IDatabase db = DatabaseProvider.getInstance();
		
		//try to find the account in the database
		Account a = db.getAccountByUsername(model.getUsername());
		
		//could not find username
		if(a == null) return false;
		
		//passwords match, return true
		if(a.getPassword().equals(model.getPassword())) return true;
		
		//passwords do not match, return false
		return false;
	}
	
	/**
	 * Attempts to create an account with the model's username, password, and email
	 * @return true if the account was created successfully, false otherwise
	 */
	public boolean createAccount(){
		IDatabase db = DatabaseProvider.getInstance();
		
		ArrayList<Account> accounts = db.getAccounts();

		//first check to make sure an account with the requested username doesn't already exist
		for(Account a : accounts){
			if(a.getUsername().equals(model.getUsername())) return false;
		}
		
		//the requested username doesn't already exist, so create the account
		Account newAccount = new StandardUser(model.getUsername(), model.getPassword(), model.getEmail());
		db.addUser(newAccount);
		
		return true;
	}
	
}
