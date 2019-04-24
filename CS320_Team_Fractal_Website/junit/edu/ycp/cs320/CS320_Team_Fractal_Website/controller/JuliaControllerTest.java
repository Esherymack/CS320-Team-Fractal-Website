package edu.ycp.cs320.CS320_Team_Fractal_Website.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.image.BufferedImage;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.JuilaController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.Complex;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Julia;

public class JuliaControllerTest {

	
	private Julia model;
	private JuilaController controller;
	
	@Before
	public void setUp(){
		model = new Julia();
		controller = new JuilaController(model);
	}

	@Test
	public void testConstructor(){
		controller = new JuilaController(model);
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
		
		params = new String[]{"1", "2", "3", "4", "5", "6", null, null, null, null};
		assertTrue(controller.acceptParameters(params));
		
		params = model.getParameters();
		assertTrue(controller.acceptParameters(params));
	}

	@Test
	public void testRenderImage(){
		assertFalse(controller.renderImage() == null);
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
		assertTrue(cnt == JuilaController.MAX_ITER);
		
		c = new Complex(0.25, 0);
		cnt = controller.computeIterCount(c);
		assertTrue(cnt == JuilaController.MAX_ITER);
		
		c = new Complex(-0.5, 0.1);
		cnt = controller.computeIterCount(c);
		assertTrue(cnt == JuilaController.MAX_ITER);

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
		assertTrue(cnt == 0);
	}
}
