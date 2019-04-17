package edu.ycp.cs320.CS320_Team_Fractal_Website.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.geom.Point2D;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.FractalController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.SierpinskiController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Location;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Sierpinski;
import edu.ycp.cs320.CS320_Team_Fractal_Website.servlet.MainPageServlet;

public class SierpinskiTest {

	public static final double DELTA = 0.00001;
	
	private Sierpinski model;
	
	@Before
	public void setUp(){
		model = new Sierpinski();
	}

	@Test
	public void testSetDefaultParameters(){
		
		model.setDefaultParameters();
		
		assertTrue(model.getHeight() == Math.round(Sierpinski.SIZE * Math.sqrt(3.0)/2.0));
		assertTrue(model.getLevel() == 0);

		Point2D.Double p1 = model.getP1();
		Point2D.Double p2 = model.getP2();
		Point2D.Double p3 = model.getP3();

		assertEquals(p1.getX(), 0, DELTA);
		assertEquals(p1.getY(), model.getHeight(), DELTA);

		assertEquals(p2.getX(), Sierpinski.SIZE / 2, DELTA);
		assertEquals(p2.getY(), 0, DELTA);

		assertEquals(p3.getX(), Sierpinski.SIZE, DELTA);
		assertEquals(p3.getY(), model.getHeight(), DELTA);
		
		Location l = model.getLocation();
		assertTrue(l.getX1() == 0);
		assertTrue(l.getY1() == 0);
		assertTrue(l.getX2() == Sierpinski.SIZE);
		assertTrue(l.getY2() == Sierpinski.SIZE);
	}

	@Test
	public void testSetLevel(){
		model.setLevel(2);
		assertTrue(model.getLevel() == 2);
	}

	@Test
	public void testSetHeight(){
		model.setHeight(300.1);
		assertTrue(model.getHeight() == 300.1);
	}
	
	@Test
	public void testSetPoints(){
		model.setP1(new Point2D.Double(2, 4));
		model.setP2(new Point2D.Double(3, -4));
		model.setP3(new Point2D.Double(4.2, 1337));


		Point2D.Double p1 = model.getP1();
		Point2D.Double p2 = model.getP2();
		Point2D.Double p3 = model.getP3();

		assertEquals(p1.getX(), 2, DELTA);
		assertEquals(p1.getY(), 4, DELTA);

		assertEquals(p2.getX(), 3, DELTA);
		assertEquals(p2.getY(), -4, DELTA);

		assertEquals(p3.getX(), 4.2, DELTA);
		assertEquals(p3.getY(), 1337, DELTA);
		
	}
	
	@Test
	public void testGetInfo(){
		String info = model.getInfo();
		assertFalse(info == null);
		assertFalse(info.isEmpty());
	}
	
	@Test
	public void testGetParameters(){
		String[] params = model.getParameters();
		assertTrue(params.length == MainPageServlet.NUM_PARAMS);
	}
	
	@Test
	public void testCreateAppropriateController(){
		FractalController control = model.createApproprateController();
		
		assertFalse(control == null);
		assertTrue(control.getModel().equals(model));
		assertTrue(control instanceof SierpinskiController);
	}
	
}
