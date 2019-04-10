package edu.ycp.cs320.CS320_Team_Fractal_Website.database;

import java.util.ArrayList;

import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.User;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Fractal;

public interface IDatabase{
	
	/**
	 * Get a list of all of the accounts in the database
	 * @return the list of accounts
	 */
	public ArrayList<User> getUsers();
	
	/**
	 * Get the account of the given username and password
	 * @param username the given user name
	 * @param password associated with the given account
	 * @return the account with the given username and password, null if no account is found
	 */
	public User getUserByUsernameAndPassword(String username, String password);
	
	/**
	 * Get the account of the given username
	 * @param username the given user name
	 * @return the account with the given username, null if no account is found
	 */
	public User getUserByUsername(String username);
	
	/**
	 * Add a new user account to the database
	 * @param user the user to add to the account
	 * @return true if the account was added successfully, false otherwise
	 */
	public boolean addUser(User user);
	
	/**
	 * Saves the given fractal to the database under the name of the given username. If no user of that name exists, 
	 * the fractal is not added.
	 * @param fractal the fractal to add
	 * @param name thge name of the fractal to add
	 * @param username the username of the one who created the fractal
	 * @return true if the fractal was saved successfully, false otherwise
	 */
	public boolean saveFractal(Fractal fractal, String name, String username);
	
}
