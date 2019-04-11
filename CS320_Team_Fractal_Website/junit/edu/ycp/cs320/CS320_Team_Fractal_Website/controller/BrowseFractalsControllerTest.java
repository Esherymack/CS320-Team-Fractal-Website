package edu.ycp.cs320.CS320_Team_Fractal_Website.controller;

import static org.junit.Assert.assertFalse;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.pages.BrowseFractalsController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Fractal;

public class BrowseFractalsControllerTest{
	
	private BrowseFractalsController controller;
	
	@Before
	public void setUp(){
		controller = new BrowseFractalsController();
	}
	
	@Test
	public void testGetAllFractals(){ 
		ArrayList<Fractal> fractals = controller.getAllFractals();
		
		assertFalse(fractals.size() == 0);
		assertFalse(fractals == null);
	}
	
}
