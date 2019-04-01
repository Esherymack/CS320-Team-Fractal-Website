package edu.ycp.cs320.CS320_Team_Fractal_Website.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.pages.LogInController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.database.DatabaseProvider;
import edu.ycp.cs320.CS320_Team_Fractal_Website.database.FakeDataBase;
import edu.ycp.cs320.CS320_Team_Fractal_Website.database.IDatabase;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.Account;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.pages.LogIn;

public class LogInControllerTest{
	
	public static String username = "User12345";
	public static String password = "pass12345";
	public static String email = "user12345@website.com";
	
	private LogIn model;
	private LogInController controller;
	
	@Before
	public void setUp(){
		model = new LogIn();
		controller = new LogInController();
		controller.setModel(model);
		
		FakeDataBase db = new FakeDataBase();
		DatabaseProvider.setInstance(db);
	}
	
	@Test
	public void testCreateAccount(){
		model.setUsername(username);
		model.setPassword(password);
		model.setEmail(email);
		controller.createAccount();
		
		IDatabase db = DatabaseProvider.getInstance();
		Account a = db.getAccountByUsername(username);
		
		assertFalse(a == null);
		assertTrue(a.getUsername().equals(username));
		assertTrue(a.getPassword().equals(password));
		assertTrue(a.getEmail().equals(email));
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
		LogIn log = new LogIn();
		controller.setModel(log);
		
		assertTrue(controller.getModel().equals(log));
	}
}
