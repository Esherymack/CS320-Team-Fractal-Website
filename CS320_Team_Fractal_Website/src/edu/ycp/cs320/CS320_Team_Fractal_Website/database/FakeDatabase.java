    
package edu.ycp.cs320.CS320_Team_Fractal_Website.database;

import java.util.ArrayList;

import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.Admin;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.StandardUser;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.User;

public class FakeDatabase implements IDatabase{
	
	private ArrayList<User> users;
	
	public FakeDatabase(){
		users = new ArrayList<User>();
		addDefaultData();
	}
	
	/**
	 * Adds some test data to the fake database
	 */
	public void addDefaultData(){
		addUser(new StandardUser("User0", "John", "Johnson", "user0@website.com", "12345"));
		addUser(new Admin("Admin1", "Jane", "Smith", "admin@website.com", "password"));
	}
	
	public ArrayList<User> getAccounts(){
		return users;
	}
	
	public boolean addUser(User account){
		//make sure an account with the given username doesn't already exist.
		//If an account with that name already exists, return false
		for(User a : users){
			if(a.getUsername().equals(account.getUsername())) return false;
		}
		
		//add the new user and return true
		users.add(account);
		return true;
	}
	
	@Override
	public User getUserByUsernameAndPassword(String username, String password){
		for(User a : users){
			if(a.getUsername().equals(username) && a.getPassword().equals(password)) return a;
		}
		
		return null;
	}

}