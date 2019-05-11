package edu.ycp.cs320.CS320_Team_Fractal_Website.account;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CS320_Team_Fractal_Website.database.IDatabase;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.User;

public class UserTest{
	
	private User user;
	
	@Before
	public void setUp(){
		user = new User() {
			@Override
			public String getType() {
				return "User";
			}
			@Override
			public boolean deleteFractal(int fractalId, IDatabase db) {
				return false;
			}
			@Override
			public boolean renameFractal(int fractalId, String newName, IDatabase db) {
				return false;
			}
		};
	}
	
	@Test
	public void testSetUsername(){
		String username = "user1";
		user.setUsername(username);
		assertTrue(user.getUsername().equals(username));
	}

	@Test
	public void testSetFirstname(){
		String firstName = "first";
		user.setFirstname(firstName);
		assertTrue(user.getFirstname().equals(firstName));
	}

	@Test
	public void testSetLastname(){
		String lastname = "last";
		user.setLastname(lastname);
		assertTrue(user.getLastname().equals(lastname));
	}

	@Test
	public void testSetEmail(){
		String email = "e.e@e";
		user.setEmail(email);
		assertTrue(user.getEmail().equals(email));
	}

	@Test
	public void testSetPassword(){
		String password = "pass";
		user.setPassword(password);
		assertTrue(user.getPassword().equals(password));
	}

	@Test
	public void testItVerified(){
		boolean ver = true;
		user.setIsVerified(ver);
		assertTrue(ver = user.getIsVerified());
	}

	@Test
	public void testSetVerificationCode(){
		String code = "code";
		user.setVerificationCode(code);
		assertTrue(user.getVerificationCode().equals(code));
	}
	
	@Test
	public void testIsAdminPassword(){
		assertFalse(User.isAdminPasswod("sduygftr4"));
		assertTrue(User.isAdminPasswod("12345"));
	}
}
