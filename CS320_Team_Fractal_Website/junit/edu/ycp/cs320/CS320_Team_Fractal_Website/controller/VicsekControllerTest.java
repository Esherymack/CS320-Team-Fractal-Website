package edu.ycp.cs320.CS320_Team_Fractal_Website.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.geom.Point2D;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.VicsekController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Vicsek;

public class VicsekControllerTest{
	
	public static final double DELTA = 0.000001;
	
	private Vicsek model;
	private VicsekController controller;
	
	@Before
	public void setUp(){
		model = new Vicsek();
		controller = new VicsekController(model);
	}
	
	@Test
	public void testConstructor(){
		controller = new VicsekController(model);
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
		
		params = new String[]{"1.0"};
		assertFalse(controller.acceptParameters(params));

		params = new String[]{"a"};
		assertFalse(controller.acceptParameters(params));

		params = new String[]{"1.0", null, null, null, null, null, null, null, null, null};
		assertFalse(controller.acceptParameters(params));
		
		params = new String[]{"1", "0", "2", "2", "2", "2", "2", "2", "2", "2", null, null, null};
		assertTrue(controller.acceptParameters(params));
		
		params = model.getParameters();
		assertTrue(controller.acceptParameters(params));
	}

	@Test
	public void testRenderImage(){
		assertFalse(controller.renderImage() == null);
	}
	
	@Test
	public void testMidPoint(){
		Point2D.Double p1 = new Point2D.Double(2, 0);
		Point2D.Double p2 = new Point2D.Double(0, 0);
		Point2D.Double mid = VicsekController.midPoint(p1, p2);
		
		assertTrue(mid.getX() == 1.0);
		assertTrue(mid.getY() == 0);
		
		p1 = new Point2D.Double(0, 5);
		p2 = new Point2D.Double(0, 0);
		mid = VicsekController.midPoint(p1, p2);
		
		assertTrue(mid.getX() == 0);
		assertTrue(mid.getY() == 2.5);
		
		p1 = new Point2D.Double(-2, 4);
		p2 = new Point2D.Double(4, 3);
		mid = VicsekController.midPoint(p1, p2);
		
		assertTrue(mid.getX() == 1);
		assertTrue(mid.getY() == 3.5);
	}
	
	@Test
	public void testGetThirdPoint(){
		Point2D.Double p = VicsekController.getThirdPoint(new Point2D.Double(0, 0), new Point2D.Double(3, 0));
		assertEquals(1.0, p.x, DELTA);
		assertEquals(0.0, p.y, DELTA);
		
		p = VicsekController.getThirdPoint(new Point2D.Double(0, 0), new Point2D.Double(-3, 0));
		assertEquals(-1.0, p.x, DELTA);
		assertEquals(0.0, p.y, DELTA);
		
		p = VicsekController.getThirdPoint(new Point2D.Double(0, 0), new Point2D.Double(0, 3));
		assertEquals(0.0, p.x, DELTA);
		assertEquals(1.0, p.y, DELTA);
		
		p = VicsekController.getThirdPoint(new Point2D.Double(0, 0), new Point2D.Double(0, -3));
		assertEquals(0.0, p.x, DELTA);
		assertEquals(-1.0, p.y, DELTA);
		
		p = VicsekController.getThirdPoint(new Point2D.Double(0, 0), new Point2D.Double(3, -3));
		assertEquals(1.0, p.x, DELTA);
		assertEquals(-1.0, p.y, DELTA);
		
		p = VicsekController.getThirdPoint(new Point2D.Double(-1, -1), new Point2D.Double(2, 2));
		assertEquals(0.0, p.x, DELTA);
		assertEquals(0.0, p.y, DELTA);
	}
	
}
