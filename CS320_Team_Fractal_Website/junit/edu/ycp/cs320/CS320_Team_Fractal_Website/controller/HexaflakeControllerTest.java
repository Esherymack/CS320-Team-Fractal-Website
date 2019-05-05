package edu.ycp.cs320.CS320_Team_Fractal_Website.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.HexaflakeController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Hexaflake;

public class HexaflakeControllerTest{

	public static final double DELTA = 0.000001;
	
	private Hexaflake model;
	private HexaflakeController controller;
	
	@Before
	public void setUp(){
		model = new Hexaflake();
		controller = new HexaflakeController();
		controller.setModel(model);
	}
	
	@Test
	public void testSetModel(){
		controller.setModel(model);
		assertTrue(controller.getModel().equals(model));
	}

	@Test
	public void testAcceptParameters(){
		assertTrue(controller.acceptParameters(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13"}));
		assertFalse(controller.acceptParameters(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		assertFalse(controller.acceptParameters(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", null}));
		assertFalse(controller.acceptParameters(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", ""}));
		assertFalse(controller.acceptParameters(new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "a"}));
		assertFalse(controller.acceptParameters(new String[]{"1", "2", "3", null, "4", "5", "6", "7", "8", "9", "10", "11", "12", "a"}));
	}

	@Test
	public void testRenderImage(){
		assertFalse(controller.renderImage() == null);
	}

	@Test
	public void testMidPoint(){
		Point2D.Double p = HexaflakeController.midPoint(new Point2D.Double(0, 0), new Point2D.Double(2, 0));
		assertEquals(1.0, p.x, DELTA);
		assertEquals(0.0, p.y, DELTA);
		
		p = HexaflakeController.midPoint(new Point2D.Double(0, 0), new Point2D.Double(0, 2));
		assertEquals(0.0, p.x, DELTA);
		assertEquals(1.0, p.y, DELTA);
		
		p = HexaflakeController.midPoint(new Point2D.Double(0, -2), new Point2D.Double(0, 2));
		assertEquals(0.0, p.x, DELTA);
		assertEquals(0.0, p.y, DELTA);
		
		p = HexaflakeController.midPoint(new Point2D.Double(0, 0), new Point2D.Double(4, 4));
		assertEquals(2, p.x, DELTA);
		assertEquals(2, p.y, DELTA);
	}

	@Test
	public void testGetThirdPoint(){
		Point2D.Double p = HexaflakeController.getThirdPoint(new Point2D.Double(0, 0), new Point2D.Double(3, 0));
		assertEquals(1.0, p.x, DELTA);
		assertEquals(0.0, p.y, DELTA);
		
		p = HexaflakeController.getThirdPoint(new Point2D.Double(0, 0), new Point2D.Double(-3, 0));
		assertEquals(-1.0, p.x, DELTA);
		assertEquals(0.0, p.y, DELTA);
		
		p = HexaflakeController.getThirdPoint(new Point2D.Double(0, 0), new Point2D.Double(0, 3));
		assertEquals(0.0, p.x, DELTA);
		assertEquals(1.0, p.y, DELTA);
		
		p = HexaflakeController.getThirdPoint(new Point2D.Double(0, 0), new Point2D.Double(0, -3));
		assertEquals(0.0, p.x, DELTA);
		assertEquals(-1.0, p.y, DELTA);
		
		p = HexaflakeController.getThirdPoint(new Point2D.Double(0, 0), new Point2D.Double(3, -3));
		assertEquals(1.0, p.x, DELTA);
		assertEquals(-1.0, p.y, DELTA);
		
		p = HexaflakeController.getThirdPoint(new Point2D.Double(-1, -1), new Point2D.Double(2, 2));
		assertEquals(0.0, p.x, DELTA);
		assertEquals(0.0, p.y, DELTA);
	}

	@Test
	public void testGetIntersection(){
		Line2D.Double line1 = new Line2D.Double(0, 0, 1, 0);
		Line2D.Double line2 = new Line2D.Double(0, 0, 1, 0);
		Point2D.Double p = HexaflakeController.getIntersection(line1, line2);
		assertTrue(p == null);
		
		line1 = new Line2D.Double(0, 0, 1, 0);
		line2 = new Line2D.Double(0, 0, 0, 1);
		p = HexaflakeController.getIntersection(line1, line2);
		assertEquals(p.x, 0, DELTA);
		assertEquals(p.y, 0, DELTA);

		line1 = new Line2D.Double(0, 0, 0, 1);
		line2 = new Line2D.Double(0, 0, 1, 0);
		p = HexaflakeController.getIntersection(line1, line2);
		assertEquals(p.x, 0, DELTA);
		assertEquals(p.y, 0, DELTA);
		
		line1 = new Line2D.Double(0, 0, 1, 1);
		line2 = new Line2D.Double(0, 2, 2, 0);
		p = HexaflakeController.getIntersection(line1, line2);
		assertEquals(p.x, 1, DELTA);
		assertEquals(p.y, 1, DELTA);
	}

	@Test
	public void testSlope(){
		assertEquals(HexaflakeController.slope(new Line2D.Double(0, 0, 1, 0)), 0, DELTA);
		assertEquals(HexaflakeController.slope(new Line2D.Double(0, 0, 2, 2)), 1, DELTA);
		assertEquals(HexaflakeController.slope(new Line2D.Double(0, 0, -2, 1)), -.5, DELTA);
		assertEquals(HexaflakeController.slope(new Line2D.Double(0, 0, 0, 2)), Double.POSITIVE_INFINITY, DELTA);
		assertEquals(HexaflakeController.slope(new Line2D.Double(0, 0, 0, -2)), Double.NEGATIVE_INFINITY, DELTA);
	}


}
