package edu.ycp.cs320.CS320_Team_Fractal_Website.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.geom.Point2D;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.SierpinskiController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Sierpinski;

public class SierpinskiControllerTest{
	private Sierpinski model;
	private SierpinskiController controller;
	
	@Before
	public void setUp(){
		model = new Sierpinski();
		controller = new SierpinskiController(model);
	}

	@Test
	public void testSetModel(){
		controller.setModel(model);
		assertEquals(model, controller.getModel());
	}
	
	@Test
	public void testMidPoint(){
		Point2D.Double p1 = new Point2D.Double(2, 0);
		Point2D.Double p2 = new Point2D.Double(0, 0);
		Point2D.Double mid = SierpinskiController.midpoint(p1, p2);
		
		assertTrue(mid.getX() == 1.0);
		assertTrue(mid.getY() == 0);
		
		p1 = new Point2D.Double(0, 5);
		p2 = new Point2D.Double(0, 0);
		mid = SierpinskiController.midpoint(p1, p2);
		
		assertTrue(mid.getX() == 0);
		assertTrue(mid.getY() == 2.5);
		
		p1 = new Point2D.Double(-2, 4);
		p2 = new Point2D.Double(4, 3);
		mid = SierpinskiController.midpoint(p1, p2);
		
		assertTrue(mid.getX() == 1);
		assertTrue(mid.getY() == 3.5);
	}
	
}
