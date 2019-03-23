package edu.ycp.cs320.CS320_Team_Fractal_Website.model;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CS320_Team_Fractal_Website.model.pages.LogIn;

public class LogInTest{
	
	private LogIn model;
	
	@Before
	public void setUp(){
		model = new LogIn();
	}

	@Test
	public void testSetUsername(){
		model.setUsername("fractalMasta1337");
		assertTrue(model.getUsername().equals("fractalMasta1337"));
	}

	@Test
	public void testSetPassword(){
		model.setUsername("password");
		assertTrue(model.getUsername().equals("password"));
	}
	
}
