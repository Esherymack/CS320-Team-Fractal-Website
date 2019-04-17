package edu.ycp.cs320.CS320_Team_Fractal_Website.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.geom.Point2D;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.KochController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Koch;

public class KochControllerTest {
	
	public static final double DELTA = 0.000001;
	
	private Koch model;
	private KochController controller;
	
	@Before
	public void setUp(){
		model = new Koch();
		controller = new KochController();
		controller.setModel(model);
	}

	@Test
	public void testSetModel(){
		controller.setModel(model);
		assertEquals(model, controller.getModel());
	}
	
	@Test
	public void testRender(){
		assertTrue(controller.render());
	}
	
	@Test
	public void testAcceptParameters(){
		
		String[] params = new String[]{""};
		assertFalse(controller.acceptParameters(params));
		
		params = new String[]{"1"};
		assertTrue(controller.acceptParameters(params));
		
		params = new String[]{"1.0"};
		assertFalse(controller.acceptParameters(params));

		params = new String[]{"a"};
		assertFalse(controller.acceptParameters(params));

		params = new String[]{"1.0", null, null, null, null, null, null, null, null, null};
		assertFalse(controller.acceptParameters(params));
		
		params = new String[]{"1", null, null, null, null, null, null, null, null, null};
		assertTrue(controller.acceptParameters(params));
		
		params = model.getParameters();
		assertTrue(controller.acceptParameters(params));
	}
	
	@Test
	public void testGetThirdPoint(){
		Point2D.Double p = controller.getThirdPoint(new Point2D.Double(0, 0), new Point2D.Double(3, 0));
		assertEquals(1.0, p.x, DELTA);
		assertEquals(0.0, p.y, DELTA);
		
		p = controller.getThirdPoint(new Point2D.Double(0, 0), new Point2D.Double(-3, 0));
		assertEquals(-1.0, p.x, DELTA);
		assertEquals(0.0, p.y, DELTA);
		
		p = controller.getThirdPoint(new Point2D.Double(0, 0), new Point2D.Double(0, 3));
		assertEquals(0.0, p.x, DELTA);
		assertEquals(1.0, p.y, DELTA);
		
		p = controller.getThirdPoint(new Point2D.Double(0, 0), new Point2D.Double(0, -3));
		assertEquals(0.0, p.x, DELTA);
		assertEquals(-1.0, p.y, DELTA);
		
		p = controller.getThirdPoint(new Point2D.Double(0, 0), new Point2D.Double(3, -3));
		assertEquals(1.0, p.x, DELTA);
		assertEquals(-1.0, p.y, DELTA);
		
		p = controller.getThirdPoint(new Point2D.Double(-1, -1), new Point2D.Double(2, 2));
		assertEquals(0.0, p.x, DELTA);
		assertEquals(0.0, p.y, DELTA);
	}
	
	@Test
	public void testGetEquilateralPoint(){
		Point2D.Double p = controller.getEquilateralPoint(new Point2D.Double(-1, 0), new Point2D.Double(1, 0));
		assertEquals(0.0, p.x, DELTA);
		assertEquals(-2 * Math.sqrt(3.0) / 2.0, p.y, DELTA);
	
		p = controller.getEquilateralPoint(new Point2D.Double(1, 0), new Point2D.Double(-1, 0));
		assertEquals(0.0, p.x, DELTA);
		assertEquals(2 * Math.sqrt(3.0) / 2.0, p.y, DELTA);
	}
	
}
