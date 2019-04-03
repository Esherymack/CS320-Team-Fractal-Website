package edu.ycp.cs320.CS320_Team_Fractal_Website.databse;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CS320_Team_Fractal_Website.database.FakeDatabase;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.User;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.StandardUser;

public class FakeDatabaseTest{
	
	private static String username = "user10";
	private static String password = "12345";
	private static String email = "user1@website.com";
	private static String firstname = "Bob";
	private static String lastname = "Smith";
	
	private FakeDatabase database;
	
	@Before
	public void setUp(){
		database = new FakeDatabase();
	}
	
	@Test
	public void testAddDefaultDataAndGetAccounts(){
		database.addDefaultData();
		
		assertFalse(database.getUsers() == null);
		assertFalse(database.getUsers().isEmpty());
	}
	
	@Test
	public void testAddUserAndGetUserByUsername(){
		database.addUser(new StandardUser(username, firstname, lastname, email, password));
		
		User a = database.getUserByUsernameAndPassword(username, password);
		
		assertFalse(a == null);
		assertTrue(a.getUsername().equals(username));
		assertTrue(a.getFirstname().equals(firstname));
		assertTrue(a.getLastname().equals(lastname));
		assertTrue(a.getPassword().equals(password));
		assertTrue(a.getEmail().equals(email));
	}
	
	@Test
	public void testGetUsers(){
		database.addUser(new StandardUser(username, firstname, lastname, email, password));
		
		ArrayList<User> users = database.getUsers();
		assertTrue(users.size() > 0);
	}
	
}
