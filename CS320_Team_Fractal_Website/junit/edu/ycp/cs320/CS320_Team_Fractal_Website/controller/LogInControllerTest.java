package edu.ycp.cs320.CS320_Team_Fractal_Website.controller;

import org.junit.Before;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.pages.LogInController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.pages.LogIn;

public class LogInControllerTest{
	
	private LogIn model;
	private LogInController controller;
	
	@Before
	public void setUp(){
		model = new LogIn();
		controller = new LogInController(model);
	}
	
}
