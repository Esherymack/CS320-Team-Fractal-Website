package edu.ycp.cs320.CS320_Team_Fractal_Website.model;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Location;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Mandelbrot;

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

}
