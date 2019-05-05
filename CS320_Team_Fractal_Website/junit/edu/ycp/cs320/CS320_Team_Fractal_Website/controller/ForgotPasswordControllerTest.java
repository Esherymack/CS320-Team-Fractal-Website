package edu.ycp.cs320.CS320_Team_Fractal_Website.controller;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.user.ForgotPasswordController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.StandardUser;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.User;

public class ForgotPasswordControllerTest{
	
	private ForgotPasswordController controller;
	private User model;
	
	@Before
	public void setUp(){
		controller = new ForgotPasswordController();
		model = new StandardUser();
	}
	
	@Test
	public void testSetModel(){
		controller.setModel(model);
		
		assertTrue(model.equals(controller.getModel()));
	}
	
	public void testSendForgotPasswordEmail(){
	}
	
	public void testVerifyUser(){
	}

}
