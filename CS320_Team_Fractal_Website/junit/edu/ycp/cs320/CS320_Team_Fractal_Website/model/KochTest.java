package edu.ycp.cs320.CS320_Team_Fractal_Website.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.FractalController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.KochController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Koch;
import edu.ycp.cs320.CS320_Team_Fractal_Website.servlet.MainPageServlet;

public class KochTest{
	
	private Koch model;
	
	@Before
	public void setUp(){
		model = new Koch();
	}

	@Test
	public void testSetDefaultParameters(){
		
		model.setDefaultParameters();
		
		assertTrue(model.getIterations() == 1);
	}

	@Test
	public void testSetIterations(){
		model.setIterations(2);
		assertTrue(model.getIterations() == 2);
	}
	
	@Test
	public void testGetInfo(){
		String info = model.getInfo();
		assertFalse(info == null);
		assertFalse(info.isEmpty());
	}
	
	@Test
	public void testGetParameters(){
		String[] params = model.getParameters();
		assertTrue(params.length == MainPageServlet.NUM_PARAMS);
	}
	
	@Test
	public void testCreateAppropriateController(){
		FractalController control = model.createApproprateController();
		
		assertFalse(control == null);
		assertTrue(control.getModel().equals(model));
		assertTrue(control instanceof KochController);
	}

}
