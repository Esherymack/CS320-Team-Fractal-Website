package edu.ycp.cs320.CS320_Team_Fractal_Website.database;

import java.util.ArrayList;

import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.Account;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.Admin;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.StandardUser;

public class FakeDataBase implements IDatabase{
	
	private ArrayList<Account> accounts;
	
	public FakeDataBase(){
		accounts = new ArrayList<Account>();
		addDefaultData();
	}
	
	/**
	 * Adds some test data to the fake database
	 */
	public void addDefaultData(){
		addUser(new StandardUser("User0", "12345"));
		addUser(new Admin("Admin", "password"));
	}
	
	public ArrayList<Account> getAccounts(){
		System.out.println("good getAccounts call");
		return accounts;
	}
	
	public boolean addUser(Account account){
		System.out.println("good addUser call");
		//make sure an account with the given username doesn't already exist
		//if an account with that name already exists, return false
		for(Account a : accounts){
			if(a.getUsername().equals(account.getUsername())) return false;
		}
		
		//add the new user and return true
		accounts.add(new StandardUser(account.getUsername(), account.getPassword()));
		return true;
	}

}
