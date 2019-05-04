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
	 * Check if an account of the given user and email exists.
	 * @param username
	 * @param email
	 * @return the account with the given username and email, null if no account is found
	 */
	public User getUserByUsernameAndEmail(String username, String email);

	/**
	 * Get the account of the given username
	 * @param username the given user name
	 * @return the account with the given username, null if no account is found
	 */
	public User getUserByUsername(String username);

	/**
	 * Add a new user account to the database
	 * @param user the user to add to the account
	 * @param ver true if the user was verified, false otherwise
	 * @param type the type of this user as should be stored in the dtatabase, the subclass of user does not effect this
	 * @return true if the account was added successfully, false otherwise
	 */
	public boolean addUser(User user, boolean ver, String type);

	/**
	 * Saves the given fractal to the database under the name of the given username. If no user of that name exists,
	 * the fractal is not added.
	 * @param fractal the fractal to add
	 * @param name the name of the fractal to add
	 * @param username the username of the one who created the fractal
	 * @return true if the fractal was saved successfully, false otherwise
	 */
	public boolean saveFractal(Fractal fractal, String name, String username);

	/**
	 * Deletes the given fractal from the database under the name of the given username. If no user of that name exists,
	 * the fractal is not deleted.
	 * @param fractal the fractal to add
	 * @param username the username of the one who created the fractal
	 * @return true if the fractal was deleted successfully, false otherwise
	 */
	public boolean deleteFractal(Fractal fractal, String username);

	/**
	 * Get every fractal in the database
	 * @return an ArrayList of every fractal, an empty list if none are found, null if an error occured
	 */
	public ArrayList<Fractal> getAllFractals();

	/**
	 * Get every fractal in the database by type
	 * @return an ArrayList of every fractal of type, an empty list if none are found, null if an error occured
	 */
	public ArrayList<Fractal> getAllFractalsByType(String type);

	/**
	 * Get every fractal in the database containing character sequence in name
	 * @return an ArrayList of every fractal containing char sequence in name, an empty list if none are found, null if an error occured
	 */
	public ArrayList<Fractal> getAllFractalsWithCharSeq(String charSeq);

	/**
	 * Get every fractal in the database with gradient type
	 * @return an ArrayList of every fractal with gradient type, an empty list if none are found, null if an error occured
	 */
	public ArrayList<Fractal> getAllFractalsByGradientType(String gradientType);

  /**
	 * Get fractals by username
	 * @return an Arraylist of every fractal by a specific user
	 */
	public ArrayList<Fractal> getAllFractalsByUsername(String username);

	/**
	 * Get the fractal with the given id
	 * @param id the id of the fractal
	 * @return the fractal with the given id, null if no fractal was found
	 */
	public Fractal getFractalById(int id);


	/**
	 * Get the fractal with the given name
	 * @param name the name of the fractal
	 * @return the fractal with the given name, null if no fractal was found
	 */
	public Fractal getFractalByName(String name);

	/**
	 * Get the verification code of an account
	 * @param username
	 * @return the verification code of an account
	 */
	public String getVerificationCodeByUsername(String username);

	/**
	 * Change the state of the user's verified status
	 * @param user
	 * @param state
	 * @return true if status changes; false.
	 */
	public boolean changeStateOfVerification(User user, boolean state);
	
	/**
	 * Change a user's password
	 * @param user
	 * @param password
	 * @return true if password changes; false
	 */
	public boolean changePassword(User user, String password);

	/**
	 * Get the username of the user who created the specific fractal
	 * @param id the id of the fractal
	 * @return the username who created the fractal, null if user couldn't be found
	 */
	public String getUsernameByFractalId(int id);
}
