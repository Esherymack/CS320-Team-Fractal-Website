package edu.ycp.cs320.CS320_Team_Fractal_Website.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.pages.Crypto;
import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.user.LogInController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.database.DatabaseProvider;
import edu.ycp.cs320.CS320_Team_Fractal_Website.database.DerbyDatabase;
import edu.ycp.cs320.CS320_Team_Fractal_Website.database.IDatabase;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.StandardUser;
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
		model = new StandardUser(){};
		controller = new LogInController();
		controller.setModel(model);
		
		DerbyDatabase db = new DerbyDatabase();
		DatabaseProvider.setInstance(db);
	}
	
	@Test
	public void testCreateAccount(){
		model.setUsername(username);
		model.setFirstname(firstname);
		model.setLastname(lastname);
		model.setPassword(password);
		model.setEmail(email);
		controller.createNewAccount();
		
		IDatabase db = DatabaseProvider.getInstance();
		User user = db.getUserByUsernameAndPassword(username, password);
		
		Crypto crypto = new Crypto();
		
		assertFalse(user == null);
		assertTrue(user.getUsername().equals(username));
		assertTrue(user.getFirstname().equals(firstname));
		assertTrue(user.getLastname().equals(lastname));
		assertTrue(crypto.match(password, user.getPassword()));
		assertTrue(user.getEmail().equals(email));
	}
	
	@Test
	public void testLogIn(){
		model.setUsername(username);
		model.setPassword(password);
		controller.createNewAccount();
		
		model.setUsername(username);
		model.setPassword(password);
		assertTrue(controller.verifyCredidentials());
	}
	
	@Test
	public void testGetModel(){
		User user = new StandardUser(){};
		controller.setModel(user);
		
		assertTrue(controller.getModel().equals(user));
	}
	
	@Test
	public void testCreateNewAccount(){
		
		controller.setModel(model);
		
		model.setUsername(null);
		assertFalse(controller.createNewAccount() == null);
		model.setPassword(null);
		assertFalse(controller.createNewAccount() == null);
		model.setEmail(null);
		assertFalse(controller.createNewAccount() == null);
		model.setFirstname(null);
		assertFalse(controller.createNewAccount() == null);
		model.setLastname(null);
		assertFalse(controller.createNewAccount() == null);
		
		model.setUsername("");
		assertFalse(controller.createNewAccount() == null);
		model.setPassword("");
		assertFalse(controller.createNewAccount() == null);
		model.setEmail("");
		assertFalse(controller.createNewAccount() == null);
		model.setFirstname("");
		assertFalse(controller.createNewAccount() == null);
		model.setLastname("");
		assertFalse(controller.createNewAccount() == null);
		
		model.setUsername(username);
		model.setFirstname(firstname);
		model.setLastname(lastname);
		model.setPassword(password);
		model.setEmail(email);
		
		assertTrue(controller.createNewAccount() == null);
	}
	
	@Test
	public void testVerifyCredidentials(){
		model.setUsername(username);
		model.setPassword(password);
		controller.createNewAccount();
		
		assertTrue(controller.verifyCredidentials());
	}
	
}
