package edu.ycp.cs320.CS320_Team_Fractal_Website.databse;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.pages.Crypto;
import edu.ycp.cs320.CS320_Team_Fractal_Website.database.DerbyDatabase;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.StandardUser;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.User;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Barnsley;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Fractal;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Koch;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Mandelbrot;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Sierpinski;

public class DerbyDatabaseTest {
	
	private static String username = "user11";
	private static String password = "12345";
	private static String email = "user1@website.com";
	private static String firstname = "Joe";
	private static String lastname = "Smith";
	private static String code = "abc123";
	
	private DerbyDatabase database;
	
	@Before
	public void setUp(){
		database = new DerbyDatabase();
	}
	
	@Test
	public void testAddUser(){
		boolean added = database.addUser(new StandardUser(username, firstname, lastname, email, password, code), true, StandardUser.TYPE);
		assertTrue(added);
	}
	
	@Test
	public void testGetUserByUsernameAndPassword(){
		database.addUser(new StandardUser(username, firstname, lastname, email, password, code), true, StandardUser.TYPE);

		Crypto cr = new Crypto();
		User u = database.getUserByUsernameAndPassword(username, password);
		
		assertFalse(u == null);
		assertTrue(u.getUsername().equals(username));
		assertTrue(u.getFirstname().equals(firstname));
		assertTrue(u.getLastname().equals(lastname));
		assertTrue(cr.match(password, u.getPassword()));
		assertTrue(u.getEmail().equals(email));
		assertTrue(u.getVerificationCode().equals(code));
	}
	
	@Test
	public void testGetUsers(){
		database.addUser(new StandardUser(username, firstname, lastname, email, password, code), true, StandardUser.TYPE);
		
		Crypto cr = new Crypto();
		
		ArrayList<User> users = database.getUsers();
		
		assertTrue(users.size() > 0);
		
		User u = database.getUserByUsername(username);
		
		assertFalse(u == null);
		assertTrue(u.getUsername().equals(username));
		assertTrue(u.getFirstname().equals(firstname));
		assertTrue(u.getLastname().equals(lastname));
		assertTrue(cr.match(password, u.getPassword()));
		assertTrue(u.getEmail().equals(email));
		assertTrue(u.getVerificationCode().equals(code));
	}
	
	@Test
	public void testGetUserByUserName(){
		database.addUser(new StandardUser(username, firstname, lastname, email, password, code), true, StandardUser.TYPE);
		
		Crypto cr = new Crypto();
		User u = database.getUserByUsername(username);

		assertFalse(u == null);
		assertTrue(u.getUsername().equals(username));
		assertTrue(u.getFirstname().equals(firstname));
		assertTrue(u.getLastname().equals(lastname));
		assertTrue(cr.match(password, u.getPassword()));
		assertTrue(u.getEmail().equals(email));
		assertTrue(u.getVerificationCode().equals(code));
	}
	
	@Test
	public void testSaveFractal(){
		database.addUser(new StandardUser(username, firstname, lastname, email, password, code), true, StandardUser.TYPE);
		
		//test adding real user
		boolean added = database.saveFractal(new Mandelbrot(), "Mandelbrot1", username);
		assertTrue(added);

		//test adding fake user
		added = database.saveFractal(new Mandelbrot(), "Mandelbrot1", "263i8t6843ti");
		assertFalse(added);
		
		//test adding null data
		added = database.saveFractal(new Mandelbrot(), "Mandelbrot1", null);
		assertFalse(added);
		added = database.saveFractal(new Mandelbrot(), null, username);
		assertFalse(added);
		added = database.saveFractal(null, "Fractal", username);
		assertFalse(added);
	}
	
	@Test
	public void testGetAllFractals(){
		ArrayList<Fractal> fractals = database.getAllFractals();
		
		assertFalse(fractals.size() == 0);
		assertFalse(fractals == null);
	}
	
	@Test
	public void testGetAllFractalsByType(){
		String name = "Koch Type Test";
		Fractal fractal = new Koch();
		database.saveFractal(fractal, name, username);
		
		ArrayList<Fractal> f = database.getAllFractalsByType(fractal.getType());
		assertFalse(f == null);
		assertFalse(f.size() <= 0);
	}
	
	@Test
	public void testGetAllFractalsWithCharSeq(){
		String giberish = "ewig7we5478";
		String name = "Barnsely " + giberish + " Test";
		Fractal fractal = new Barnsley();
		database.saveFractal(fractal, name, username);
		
		ArrayList<Fractal> f = database.getAllFractalsWithCharSeq(giberish);
		assertFalse(f == null);
		assertFalse(f.size() <= 0);
	}
	
	@Test
	public void testGetFractalById(){
		String name = "Sierpinski Id Test";
		database.saveFractal(new Sierpinski(), name, username);
		
		Fractal fName = database.getFractalByName(name);
		
		assertFalse(fName == null);
		
		Fractal fId = database.getFractalById(fName.getId());

		assertFalse(fId == null);
		assertTrue(fId.getId() == fName.getId());
	}
	
	@Test
	public void testGetFractalByName(){
		String name = "Sierpinski Name Test";
		database.saveFractal(new Sierpinski(), name, username);
		
		Fractal f = database.getFractalByName("Sierpinski Name Test");
		assertFalse(f == null);
		assertTrue(f.getName().equals(name));
	}
	
	@Test
	public void testGetUsernameByFractalId(){
		String name = "Id to username test";
		database.saveFractal(new Sierpinski(), name, username);
		
		String newName = database.getUsernameByFractalId(database.getFractalByName(name).getId());
		
		assertTrue(newName.equals(username));
	}
	
}
