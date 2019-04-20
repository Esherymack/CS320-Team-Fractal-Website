package edu.ycp.cs320.CS320_Team_Fractal_Website.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.awt.image.BufferedImage;

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
	public void testConstructor(){
		controller = new MandelbrotController(model);
		assertTrue(controller.getModel().equals(model));
	}

	@Test
	public void testSetModel(){
		controller.setModel(model);
		assertEquals(model, controller.getModel());
	}
	
	@Test
	public void testAcceptParameters(){
		
		String[] params = new String[]{""};
		assertFalse(controller.acceptParameters(params));
		
		params = new String[]{"1"};
		assertFalse(controller.acceptParameters(params));

		params = new String[]{"a"};
		assertFalse(controller.acceptParameters(params));
		
		params = new String[]{"1", "2"};
		assertFalse(controller.acceptParameters(params));
		
		params = new String[]{"1", "2", "3", "4", "5", null, null, null, null, null};
		assertTrue(controller.acceptParameters(params));
		
		params = model.getParameters();
		assertTrue(controller.acceptParameters(params));
	}

	@Test
	public void testRender(){
		assertTrue(controller.render());
	}
	
	@Test
	public void testRenderIterCounts(){
		BufferedImage img = controller.renderIterCounts();
		assertFalse(img == null);
	}
	
	@Test
	public void testCalculateIterCounts(){
		int[][] counts = controller.calculateIterCounts(100, 80);
		assertFalse(counts == null);
		assertTrue(counts.length == 100);
		for(int[] i : counts){
			assertTrue(i.length == 80);
			for(int j : i){
				assertTrue(j >= 0);
			}
		}
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

	@Test
	public void testGenerateColor(){
		Color c = MandelbrotController.generateColor();
		
		assertFalse(c == null);
		assertTrue(c.getBlue() <= 255);
		assertTrue(c.getBlue() > 0);
		assertTrue(c.getRed() <= 255);
		assertTrue(c.getRed() > 0);
		assertTrue(c.getGreen() <= 255);
		assertTrue(c.getGreen() > 0);
	}
}
