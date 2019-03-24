package edu.ycp.cs320.CS320_Team_Fractal_Website.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.MandelbrotController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.Complex;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Mandelbrot;

public class MandelbrotControllerTest{
	
	private Mandelbrot model;
	private MandelbrotController controller;
	
	@Before
	public void setUp(){
		model = new Mandelbrot();
		controller = new MandelbrotController(model);
	}
	
	@Test
	public void testSetModel(){
		controller.setModel(model);
		assertEquals(model, controller.getModel());
	}
	
	@Test
	public void testGenerateColor(){
		Color c = controller.generateColor();

		assertTrue(c.getBlue() <= 255);
		assertTrue(c.getBlue() > 0);
		assertTrue(c.getRed() <= 255);
		assertTrue(c.getRed() > 0);
		assertTrue(c.getGreen() <= 255);
		assertTrue(c.getGreen() > 0);
	}
	
	@Test
	public void testComputeIterCount(){
		Complex c = new Complex(0, 0);
		int cnt = controller.computeIterCount(c);
		assertTrue(cnt == 0);
		
		c = new Complex(0.25, 0);
		cnt = controller.computeIterCount(c);
		assertTrue(cnt == 0);
		
		c = new Complex(-0.5, 0.1);
		cnt = controller.computeIterCount(c);
		assertTrue(cnt == 0);

		c = new Complex(-2, 0);
		cnt = controller.computeIterCount(c);
		assertTrue(cnt != 0);
		
		c = new Complex(0, 2);
		cnt = controller.computeIterCount(c);
		assertTrue(cnt != 0);
		
		c = new Complex(2, 2);
		cnt = controller.computeIterCount(c);
		assertTrue(cnt != 0);
		
		c = new Complex(4, 0);
		cnt = controller.computeIterCount(c);
		assertTrue(cnt != 0);
	}
	
}
