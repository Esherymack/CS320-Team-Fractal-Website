package edu.ycp.cs320.CS320_Team_Fractal_Website.account;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.Account;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.Gallery;

public class AccountTest{
	
	private static String username = "User05";
	private static String password = "password1";
	private static String email = "misterUser@fractals.frac";
	
	private Account account;
	
	@Before
	public void setUp(){
		account = new Account(){};
	}
	
	@Test
	public void testSetUsername(){
		account.setUsername(username);
		assertTrue(account.getUsername().equals(username));
	}
	
	@Test
	public void testSetPassword(){
		account.setPassword(password);
		assertTrue(account.getPassword().equals(password));
	}
	
	@Test
	public void testSetEmail(){
		account.setEmail(email);
		assertTrue(account.getEmail().equals(email));
	}
	
	@Test
	public void testSetFractalGallery(){
		Gallery gal = new Gallery();
		account.setFractalGallery(gal);
		assertTrue(account.getFractalGallery().equals(gal));
	}
	
}
