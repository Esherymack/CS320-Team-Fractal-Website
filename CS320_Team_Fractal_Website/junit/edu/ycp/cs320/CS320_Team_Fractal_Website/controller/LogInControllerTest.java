package edu.ycp.cs320.CS320_Team_Fractal_Website.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.user.LogInController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.database.DatabaseProvider;
import edu.ycp.cs320.CS320_Team_Fractal_Website.database.FakeDatabase;
import edu.ycp.cs320.CS320_Team_Fractal_Website.database.IDatabase;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.User;

public class LogInControllerTest{
	
	public static String username = "User12345";
	public static String password = "pass12345";
	public static String email = "user12345@website.com";
	public static String firstname = "Sally";
	public static String lastname = "Smith";
	
	private User model;
	private LogInController controller;
	
	@Before
	public void setUp(){
		model = new User(){};
		controller = new LogInController();
		controller.setModel(model);
		
		FakeDatabase db = new FakeDatabase();
		DatabaseProvider.setInstance(db);
	}
	
	@Test
	public void testCreateAccount(){
		model.setUsername(username);
		model.setFirstname(firstname);
		model.setLastname(lastname);
		model.setPassword(password);
		model.setEmail(email);
		controller.createAccount();
		
		IDatabase db = DatabaseProvider.getInstance();
		User user = db.getUserByUsernameAndPassword(username, password);
		
		assertFalse(user == null);
		assertTrue(user.getUsername().equals(username));
		assertTrue(user.getFirstname().equals(firstname));
		assertTrue(user.getLastname().equals(lastname));
		assertTrue(user.getPassword().equals(password));
		assertTrue(user.getEmail().equals(email));
	}
	
	@Test
	public void testLogIn(){
		model.setUsername(username);
		model.setPassword(password);
		controller.createAccount();
		
		model.setUsername(username);
		model.setPassword(password);
		
		assertTrue(controller.logIn());
	}
	
	@Test
	public void testGetModel(){
		User user = new User(){};
		controller.setModel(user);
		
		assertTrue(controller.getModel().equals(user));
	}
}
