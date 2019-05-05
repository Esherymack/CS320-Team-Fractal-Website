package edu.ycp.cs320.CS320_Team_Fractal_Website.account;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CS320_Team_Fractal_Website.database.DerbyDatabase;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.StandardUser;

public class StandardUserTest{

	private StandardUser defaultUser;
	private StandardUser user;
	
	@Before
	public void setUp(){
		defaultUser = new StandardUser();
		user = new StandardUser("user", "first", "last", "e@e.e", "pass", "code");
	}
	
	@Test
	public void testDeleteFractal(){
		assertFalse(user.deleteFractal(-1, null));
		assertFalse(user.deleteFractal(-1, new DerbyDatabase()));
	}
	
	@Test
	public void testGetType(){
		assertTrue(defaultUser.getType().equals(StandardUser.TYPE));
	}
	
}
