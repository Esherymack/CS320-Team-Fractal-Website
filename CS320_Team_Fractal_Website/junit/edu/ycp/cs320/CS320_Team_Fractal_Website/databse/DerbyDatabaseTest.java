package edu.ycp.cs320.CS320_Team_Fractal_Website.databse;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CS320_Team_Fractal_Website.database.DerbyDatabase;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.StandardUser;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.User;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Fractal;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Mandelbrot;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Sierpinski;

public class DerbyDatabaseTest {
	
	private static String username = "user11";
	private static String password = "12345";
	private static String email = "user1@website.com";
	private static String firstname = "Joe";
	private static String lastname = "Smith";
	
	private DerbyDatabase database;
	
	@Before
	public void setUp(){
		database = new DerbyDatabase();
	}
	
	@Test
	public void testAddUser(){
		boolean added = database.addUser(new StandardUser(username, firstname, lastname, email, password));
		assertTrue(added);
	}
	
	@Test
	public void testGetUserByUsernameAndPassword(){
		database.addUser(new StandardUser(username, firstname, lastname, email, password));
		
		User u = database.getUserByUsernameAndPassword(username, password);
		
		assertFalse(u == null);
		assertTrue(u.getUsername().equals(username));
		assertTrue(u.getFirstname().equals(firstname));
		assertTrue(u.getLastname().equals(lastname));
		assertTrue(u.getPassword().equals(password));
		assertTrue(u.getEmail().equals(email));
	}
	
	@Test
	public void testGetUsers(){
		database.addUser(new StandardUser(username, firstname, lastname, email, password));
		
		ArrayList<User> users = database.getUsers();
		assertTrue(users.size() > 0);
	}
	
	@Test
	public void testGetUserByUserName(){
		database.addUser(new StandardUser(username, firstname, lastname, email, password));
		
		User u = database.getUserByUsername(username);
		
		assertFalse(u == null);
		assertTrue(u.getUsername().equals(username));
		assertTrue(u.getFirstname().equals(firstname));
		assertTrue(u.getLastname().equals(lastname));
		assertTrue(u.getPassword().equals(password));
		assertTrue(u.getEmail().equals(email));
	}
	
	@Test
	public void testSaveFractal(){
		database.addUser(new StandardUser(username, firstname, lastname, email, password));
		
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
		database.saveFractal(new Mandelbrot(), "Mandelbrot2", username);
		database.saveFractal(new Sierpinski(), "Sierpinski2", username);
		
		ArrayList<Fractal> fractals = database.getAllFractals();
		
		assertFalse(fractals.size() == 0);
		assertFalse(fractals == null);
	}

}
