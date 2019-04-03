package edu.ycp.cs320.CS320_Team_Fractal_Website.databse;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CS320_Team_Fractal_Website.database.DerbyDatabase;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.StandardUser;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.User;

public class DerbyDatabaseTest {
	
	private static String username = "user10";
	private static String password = "12345";
	private static String email = "user1@website.com";
	private static String firstname = "Bob";
	private static String lastname = "Smith";
	
	private DerbyDatabase database;
	
	@Before
	public void setUp(){
		database = new DerbyDatabase();
	}
	
	@Test
	public void testAddUser(){
		boolean added = database.addUser(new StandardUser(username, firstname, lastname, password, email));
		assertTrue(added);
	}
	
	@Test
	public void testGetUserByUsernameAndPassword(){
		testAddUser();
		
		User u = database.getUserByUsernameAndPassword(username, password);
		
		assertFalse(u == null);
		assertTrue(u.getUsername().equals(username));
		assertTrue(u.getFirstname().equals(firstname));
		assertTrue(u.getLastname().equals(lastname));
		assertTrue(u.getPassword().equals(password));
		assertTrue(u.getEmail().equals(email));
	}


}
