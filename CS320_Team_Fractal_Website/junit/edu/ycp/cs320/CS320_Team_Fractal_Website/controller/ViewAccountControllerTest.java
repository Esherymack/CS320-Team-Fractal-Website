package edu.ycp.cs320.CS320_Team_Fractal_Website.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.SierpinskiController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.pages.BrowseFractalsController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.pages.ViewAccountController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.user.LogInController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.StandardUser;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.User;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Fractal;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Sierpinski;

public class ViewAccountControllerTest{

	private static String username = "user11";
	private static String password = "A123456";
	private static String firstname = "B";
	private static String lastname = "c";
	private static String email = "C@c.c";
	private static String code = "abcdef";
	
	private ViewAccountController controller;
	
	@Before
	public void setUp(){
		controller = new ViewAccountController();
	}
	
	@Test
	public void testGetUserByUserName(){
		newUserNameAndEmail();
		
		User user = new StandardUser(username, firstname, lastname, email + Math.random(), password, code);
		LogInController logController = new LogInController();
		logController.setModel(user);
		
		String message = logController.createNewAccount();
		assertTrue(message == null);
		
		User loadUser = controller.getUserByUserName(username);
		
		assertFalse(loadUser == null);
	}

	@Test
	public void testGetUserByUserNamePassword(){
		newUserNameAndEmail();
		User user = new StandardUser(username, firstname, lastname, email + Math.random(), password, code);
		LogInController logController = new LogInController();
		logController.setModel(user);
		
		String message = logController.createNewAccount();
		assertTrue(message == null);
		
		User loadUser = controller.getUserByUserNamePassword(username, password);
		
		assertFalse(loadUser == null);
	}

	@Test
	public void testGetUserFractals(){
		newUserNameAndEmail();
		User user = new StandardUser(username, firstname, lastname, email, password, code);
		LogInController logController = new LogInController();
		logController.setModel(user);
		logController.createNewAccount();
		
		SierpinskiController fracController = new SierpinskiController();
		Sierpinski fractal = new Sierpinski();
		fracController.setModel(fractal);
		fractal.setDefaultParameters();
		
		assertTrue(fracController.saveImage("view account test", username));
		
		ArrayList<Fractal> list = controller.getUserFractals(username);
		
		assertFalse(list == null);
		assertFalse(list.size() == 0);
	}

	@Test
	public void testDeleteFractal(){
		newUserNameAndEmail();
		User user = new StandardUser(username, firstname, lastname, email, password, code);
		LogInController logController = new LogInController();
		logController.setModel(user);
		logController.createNewAccount();
		
		SierpinskiController fracController = new SierpinskiController();
		Sierpinski fractal = new Sierpinski();
		fracController.setModel(fractal);
		fractal.setDefaultParameters();
		
		String name = "view account delete test";
		fracController.saveImage(name, username);
		
		BrowseFractalsController browseControl = new BrowseFractalsController();
		
		ArrayList<Fractal> fractals = browseControl.getAllFractals();
		
		Fractal delete = null;
		for(Fractal f : fractals){
			if(f.getName().equals(name)){
				delete = f;
				break;
			}
		}
		
		assertFalse(delete == null);
		assertTrue(controller.deleteFractal(delete.getId(), controller.getUserByUserName(username)));
	}

	private static void newUserNameAndEmail(){
		username = "user11" + (int)(Math.random() * 1000000);
		email = "C@c.c" + (int)(Math.random() * 1000000);
	}
}
