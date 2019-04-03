package edu.ycp.cs320.CS320_Team_Fractal_Website.database;

import java.util.ArrayList;

import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.User;

public interface IDatabase{
	
	/**
	 * Get a list of all of the accounts in the database
	 * @return the list of accounts
	 */
	public ArrayList<User> getAccounts();
	
	/**
	 * Get the account of the given username and password
	 * @param username the given user name
	 * @param password associated with the given account
	 * @return the account with the given username and password, null if no account is found
	 */
	public User getUserByUsernameAndPassword(String username, String password);
	
	/**
	 * Add a new user account to the database
	 * @param user the user to add to the account
	 * @return true if the account was added successfully, false otherwise
	 */
	public boolean addUser(User user);
	
}
