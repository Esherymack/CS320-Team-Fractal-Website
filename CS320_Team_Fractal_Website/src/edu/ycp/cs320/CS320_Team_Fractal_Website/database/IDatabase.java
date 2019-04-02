package edu.ycp.cs320.CS320_Team_Fractal_Website.database;

import java.util.ArrayList;

import edu.ycp.cs320.CS320_Team_Fractal_Website.model.User;

public interface IDatabase{
	
	/**
	 * Get a list of all of the accounts in the database
	 * @return the list of accounts
	 */
	public ArrayList<String> getAccounts();
	
	/**
	 * Get the account of the given username
	 * @param username the given user name
	 * @return the account with the given user name, null if no account is found
	 */
	public User getAccountByUsernamePassword(String username, String password);
	
	/**
	 * Add a new user account to the database
	 * @param username the name of the new account
	 * @param password the password of the new account
	 * @return true if the account was added successfully, false otherwise
	 */
	public User addUser(String firstname, String lastname, String username, String password, String email);
	
}
