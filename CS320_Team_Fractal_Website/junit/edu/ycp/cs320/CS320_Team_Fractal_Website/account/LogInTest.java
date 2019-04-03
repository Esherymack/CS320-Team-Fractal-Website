package edu.ycp.cs320.CS320_Team_Fractal_Website.account;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.User;

public class LogInTest{
	
	public static String firstname = "Person";
	public static String lastname = "Smith";
	public static String username = "fractalMasta1337";
	public static String password = "password";
	public static String email = "user12345@website.com";
	
	private User model;
	
	@Before
	public void setUp(){
		model = new User(){};
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

	@Test
	public void testSetFirstname(){
		model.setFirstname(firstname);
		assertTrue(model.getFirstname().equals(firstname));
	}
	
	@Test
	public void testSetLastname(){
		model.setLastname(lastname);
		assertTrue(model.getLastname().equals(lastname));
	}
	
}
