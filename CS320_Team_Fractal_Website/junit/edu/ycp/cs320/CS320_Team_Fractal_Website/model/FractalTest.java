package edu.ycp.cs320.CS320_Team_Fractal_Website.model;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Fractal;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Location;

public class FractalTest{

	private Fractal model;
	
	@Before
	public void setUp(){
		model = new Fractal(){
			@Override
			public String getInfo() {
				return null;
			}};
	}
	
	@Test
	public void testSetLocation(){
		Location l = new Location(3, 5, 1, -2);
		model.setLocation(l);
		assertTrue(l.getX1() == 3);
		assertTrue(l.getY1() == 5);
		assertTrue(l.getX2() == 1);
		assertTrue(l.getY2() == -2);
	}
	
	@Test
	public void testGetDefaultLocation(){
		Location l = model.getDefaultLocation();
		assertTrue(l.getX1() == -1);
		assertTrue(l.getY1() == 1);
		assertTrue(l.getX2() == 1);
		assertTrue(l.getY2() == -1);
	}
	
	@Test
	public void testSetDefaultParameters(){
		model.setDefaultParameters();
		
		Location l = model.getLocation();
		assertTrue(l.getX1() == -1);
		assertTrue(l.getY1() == 1);
		assertTrue(l.getX2() == 1);
		assertTrue(l.getY2() == -1);
	}
	
}
