package edu.ycp.cs320.CS320_Team_Fractal_Website.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
	public void testSetSecondaryColor(){
		Color c = new Color(255, 3, 5);
		gradient.setSecondaryColor(c);
		assertTrue(c.equals(gradient.getSecondaryColor()));
	}
	
	@Test
	public void testGetRainbowGradient(){
		Color c = gradient.getRainbowGradient(.5, .4, .2);
		assertFalse(c == null);
		
		c = defaultGradient.getRainbowGradient(.5, .4, .2);
		assertFalse(c == null);
	}

	@Test
	public void testGetStraightGradientColor(){
		gradient.setBaseColor(new Color(0, 0, 0));
		gradient.setSecondaryColor(new Color(255, 255, 255));
		Color c = gradient.getStraightGradientColor(1, 2);
		assertFalse(c == null);
		assertTrue(c.getRed() == 127);
		
		gradient.setBaseColor(new Color(255, 255, 255));
		gradient.setSecondaryColor(new Color(0, 0, 0));
		c = gradient.getStraightGradientColor(1, 4);
		assertFalse(c == null);
		assertTrue(c.getBlue() == 191);
	}

	@Test
	public void testGetDiagonalGradientColor(){
		gradient.setBaseColor(new Color(0, 0, 0));
		gradient.setSecondaryColor(new Color(255, 255, 255));
		Color c = gradient.getStraightGradientColor(1, 2);
		assertFalse(c == null);
		assertTrue(c.getRed() == 127);
		
		gradient.setBaseColor(new Color(255, 255, 255));
		gradient.setSecondaryColor(new Color(0, 0, 0));
		c = gradient.getStraightGradientColor(1, 4);
		assertFalse(c == null);
		assertTrue(c.getBlue() == 191);
	}
	
	@Test
	public void testGetHue(){
		assertEquals(Gradient.getHue(defaultGradient.getBaseColor()), 4, DELTA);
		
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
