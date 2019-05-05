package edu.ycp.cs320.CS320_Team_Fractal_Website.account;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CS320_Team_Fractal_Website.database.DerbyDatabase;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.Admin;

public class AdminTest{

	private Admin defaultUser;
	private Admin user;
	
	@Before
	public void setUp(){
		defaultUser = new Admin();
		user = new Admin("user", "first", "last", "e@e.e", "pass", "code");
	}
	
	@Test
	public void testDeleteFractal(){
		assertFalse(user.deleteFractal(-1, null));
		assertTrue(user.deleteFractal(-1, new DerbyDatabase()));
	}
	
	@Test
	public void testGetType(){
		assertTrue(defaultUser.getType().equals(Admin.TYPE));
	}
	
}
