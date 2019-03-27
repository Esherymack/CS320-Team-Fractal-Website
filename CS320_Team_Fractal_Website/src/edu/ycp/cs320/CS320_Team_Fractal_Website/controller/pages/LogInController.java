package edu.ycp.cs320.CS320_Team_Fractal_Website.controller.pages;

import java.util.ArrayList;

import edu.ycp.cs320.CS320_Team_Fractal_Website.database.DatabaseProvider;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.Account;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.pages.LogIn;

public class LogInController{
	private LogIn model;
	
	public LogInController(LogIn model){
		this.model = model;
	}
	
	/**
	 * Attempts to log into the web site
	 * @param username the username of the user
	 * @param password the password of the user
	 * @return true if the log in was successful, false otherwise
	 */
	public boolean logIn(String username, String password){
		
		ArrayList<Account> accounts = DatabaseProvider.getDatabase().getAccounts();

		//try to find the account in the database
		for(Account a : accounts){
			//if the account is found, log them in
			if(a.getUsername().equals(model.getUsername()) && a.getPassword().equals(model.getPassword())){
				return true;
			}
		}
		
		//could not find username and password combo, return false
		return false;
	}
	
}
