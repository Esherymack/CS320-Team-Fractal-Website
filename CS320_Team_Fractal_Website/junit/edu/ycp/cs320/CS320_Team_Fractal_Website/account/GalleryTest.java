package edu.ycp.cs320.CS320_Team_Fractal_Website.account;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.Gallery;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Fractal;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Mandelbrot;

public class GalleryTest{
	
	private Gallery gallery;
	
	@Before
	public void setUp(){
		gallery = new Gallery();
	}
	
	@Test
	public void testGetFractals(){
		assertFalse(gallery.getFractals() == null);
	}
	
	@Test
	public void testAddFractals(){
		Fractal f = new Mandelbrot();
		gallery.addFractal(f);
		
		assertTrue(gallery.getFractals().contains(f));
	}
	
}
