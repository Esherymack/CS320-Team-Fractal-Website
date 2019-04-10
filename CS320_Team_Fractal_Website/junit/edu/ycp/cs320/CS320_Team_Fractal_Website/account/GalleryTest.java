package edu.ycp.cs320.CS320_Team_Fractal_Website.account;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.Gallery;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Fractal;

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
		Fractal f = new Fractal(){
			@Override
			public String getInfo() {
				return null;
			}

			@Override
			public String[] getParameters() {
				return null;
			}};
		gallery.addFractal(f);
		
		assertTrue(gallery.getFractals().contains(f));
	}
	
}
