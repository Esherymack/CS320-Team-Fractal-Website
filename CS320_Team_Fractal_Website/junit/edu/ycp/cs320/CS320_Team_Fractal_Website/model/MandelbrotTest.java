package edu.ycp.cs320.CS320_Team_Fractal_Website.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.FractalController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.MandelbrotController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Location;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Mandelbrot;
import edu.ycp.cs320.CS320_Team_Fractal_Website.servlet.MainPageServlet;

public class MandelbrotTest{

	private Mandelbrot model;
	
	@Before
	public void setUp(){
		model = new Mandelbrot();
	}
	
	@Test
	public void testSetMultiplyTimes(){
		model.setMultiplyTimes(2);
		assertTrue(model.getMultiplyTimes() == 2);
	}
	
	@Test
	public void testSetDefaultParameters(){
		model.setDefaultParameters();
		assertTrue(model.getMultiplyTimes() == 1);
		
		Location l = model.getLocation();
		assertTrue(l.getX1() == -1);
		assertTrue(l.getY1() == 1);
		assertTrue(l.getX2() == 1);
		assertTrue(l.getY2() == -1);
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
		assertTrue(control instanceof MandelbrotController);
	}

}
