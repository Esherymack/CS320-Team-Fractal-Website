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
		addUser(new StandardUser("User0", "12345", "user0@website.com"));
		addUser(new Admin("Admin", "password", "admin0@company.com"));
	}
	
	public ArrayList<Account> getAccounts(){
		return accounts;
	}
	
	public boolean addUser(Account account){
		//make sure an account with the given username doesn't already exist.
		//If an account with that name already exists, return false
		for(Account a : accounts){
			if(a.getUsername().equals(account.getUsername())) return false;
		}
		
		//add the new user and return true
		accounts.add(account);
		return true;
	}
	
	@Override
	public Account getAccountByUsername(String username){
		for(Account a : accounts){
			if(a.getUsername().equals(username)) return a;
		}
		
		return null;
	}

}
