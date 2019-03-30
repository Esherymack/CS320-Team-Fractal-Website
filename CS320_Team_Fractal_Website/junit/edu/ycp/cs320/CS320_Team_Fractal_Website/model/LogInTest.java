package edu.ycp.cs320.CS320_Team_Fractal_Website.model;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CS320_Team_Fractal_Website.model.pages.LogIn;

public class LogInTest{
	
	public static String username = "fractalMasta1337";
	public static String password = "password";
	public static String email = "user12345@website.com";
	
	private LogIn model;
	
	@Before
	public void setUp(){
		model = new LogIn();
	}

	@Test
	public void testSetUsername(){
		model.setUsername(username);
		assertTrue(model.getUsername().equals(username));
	}

	@Test
	public void testSetPassword(){
		model.setPassword(password);
		assertTrue(model.getPassword().equals(password));
	}
	
	@Test
	public void testSetEmail(){
		model.setEmail(email);
		assertTrue(model.getEmail().equals(email));
	}
	
}
