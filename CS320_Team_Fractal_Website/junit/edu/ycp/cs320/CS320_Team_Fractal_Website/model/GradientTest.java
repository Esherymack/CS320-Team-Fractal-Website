package edu.ycp.cs320.CS320_Team_Fractal_Website.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Gradient;

public class GradientTest{
	
	public static final double DELTA = 0.00001;
	
	private Gradient gradient;
	private Gradient defaultGradient;
	
	@Before
	public void setUp(){
		gradient = new Gradient(255, 3, 100);
		defaultGradient = new Gradient();
	}
	
	@Test
	public void testSetBaseColor(){
		Color c = new Color(255, 3, 5);
		gradient.setBaseColor(c);
		assertTrue(c.equals(gradient.getBaseColor()));
	}
	
	@Test
	public void testGetHue(){
		assertTrue(Gradient.getHue(defaultGradient.getBaseColor()) == 0);
		
		gradient.setBaseColor(new Color(255, 0, 0));
		assertEquals(Gradient.getHue(gradient.getBaseColor()), 0, DELTA);
		
		gradient.setBaseColor(new Color(0, 255, 0));
		assertEquals(Gradient.getHue(gradient.getBaseColor()), 2, DELTA);
		
		gradient.setBaseColor(new Color(0, 0, 255));
		assertEquals(Gradient.getHue(gradient.getBaseColor()), 4, DELTA);
		
		gradient.setBaseColor(new Color(255, 127, 0));
		assertEquals(Gradient.getHue(gradient.getBaseColor()), 0.498039, DELTA);
	}
	
}
