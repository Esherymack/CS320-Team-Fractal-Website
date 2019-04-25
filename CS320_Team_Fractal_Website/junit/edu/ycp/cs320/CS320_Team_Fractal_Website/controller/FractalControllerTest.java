package edu.ycp.cs320.CS320_Team_Fractal_Website.controller;

import static org.junit.Assert.assertTrue;

import java.awt.image.BufferedImage;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.FractalController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.SierpinskiController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Fractal;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Gradient;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Sierpinski;

public class FractalControllerTest{
	
	private FractalController controller;
	
	@Before
	public void setUp(){
		controller = new SierpinskiController(new Sierpinski());
	}
	
	@Test
	public void testSetGradientType(){
		controller.setGradientType(Gradient.RAINBOW);
		assertTrue(controller.getGradientType().equals(Gradient.RAINBOW));
		controller.setGradientType(Gradient.NONE);
		assertTrue(controller.getGradientType().equals(Gradient.NONE));
	}
	@Test
	public void testSetGradient(){
		Gradient gradient = new Gradient();
		controller.setGradient(gradient);
		assertTrue(controller.getGradient().equals(gradient));
	}
	
	@Test
	public void testSendImage(){
		BufferedImage img = new BufferedImage(10, 10, BufferedImage.TYPE_3BYTE_BGR);
		assertTrue(controller.sendImage(img));
	}
	
	@Test
	public void testRender(){
		Fractal f = new Sierpinski();
		FractalController c = f.createApproprateController();
		assertTrue(c.render());
	}
	
}
