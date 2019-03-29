package edu.ycp.cs320.CS320_Team_Fractal_Website.databse;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CS320_Team_Fractal_Website.database.FakeDataBase;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.Account;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.StandardUser;

public class FakeDataBaseTest{
	
	private static String username = "user1";
	private static String password = "12345";
	private static String email = "user1@website.com";
	
	private FakeDataBase database;
	
	@Before
	public void setUp(){
		database = new FakeDataBase();
	}
	
	@Test
	public void testAddDefaultDataAndGetAccounts(){
		database.addDefaultData();
		
		assertFalse(database.getAccounts() == null);
		assertFalse(database.getAccounts().isEmpty());
	}
	
	@Test
	public void testAddUserAndGetUserByUsername(){
		database.addUser(new StandardUser(username, password, email));
		
		Account a = database.getAccountByUsername(username);
		
		assertFalse(a == null);
		assertTrue(a.getUsername().equals(username));
		assertTrue(a.getPassword().equals(password));
		assertTrue(a.getEmail().equals(email));
	}
	
}
