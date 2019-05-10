package edu.ycp.cs320.CS320_Team_Fractal_Website.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.geom.Point2D;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.FractalController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.SierpinskiCarpetController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Location;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.SierpinskiCarpet;
import edu.ycp.cs320.CS320_Team_Fractal_Website.servlet.MainPageServlet;

public class SierpinskiCarpetTest {

	public static final double DELTA = 0.00001;
	
	private SierpinskiCarpet model;
	
	@Before
	public void setUp(){
		model = new SierpinskiCarpet();
	}

	@Test
	public void testConstructor(){
		model = new SierpinskiCarpet(2);
		assertTrue(model.getLevel() == 2);
	}
	
	@Test
	public void testGetDefaultLocation(){
		Location l = model.getDefaultLocation();
		assertTrue(l.getX1() == 0);
		assertTrue(l.getY1() == 0);
		assertTrue(l.getX2() == SierpinskiCarpet.SIZE);
		assertTrue(l.getY2() == SierpinskiCarpet.SIZE);
	}
	
	@Test
	public void testSetDefaultParameters(){
		
		model.setDefaultParameters();
		
		assertTrue(model.getLevel() == 2);

		Point2D.Double p1 = model.getP1();
		Point2D.Double p2 = model.getP2();
		Point2D.Double p3 = model.getP3();
		Point2D.Double p4 = model.getP4();

		assertEquals(p1.getX(), 0, DELTA);
		assertEquals(p1.getY(), 0, DELTA);

		assertEquals(p2.getX(), SierpinskiCarpet.SIZE, DELTA);
		assertEquals(p2.getY(), 0, DELTA);

		assertEquals(p3.getX(), SierpinskiCarpet.SIZE, DELTA);
		assertEquals(p3.getY(), SierpinskiCarpet.SIZE, DELTA);
		
		assertEquals(p4.getX(), 0, DELTA);
		assertEquals(p4.getY(), SierpinskiCarpet.SIZE, DELTA);
		
		Location l = model.getLocation();
		assertTrue(l.getX1() == 0);
		assertTrue(l.getY1() == 0);
		assertTrue(l.getX2() == SierpinskiCarpet.SIZE);
		assertTrue(l.getY2() == SierpinskiCarpet.SIZE);
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
		assertFalse(params[0] == null);
		assertFalse(params[0].isEmpty());
		assertFalse(params[1] == null);
		assertFalse(params[1].isEmpty());
		assertFalse(params[2] == null);
		assertFalse(params[2].isEmpty());
		assertFalse(params[3] == null);
		assertFalse(params[3].isEmpty());
		assertFalse(params[4] == null);
		assertFalse(params[4].isEmpty());
		assertFalse(params[5] == null);
		assertFalse(params[5].isEmpty());
		assertFalse(params[6] == null);
		assertFalse(params[6].isEmpty());
	}
	
	@Test
	public void testCreateAppropriateController(){
		FractalController control = model.createApproprateController();
		
		assertFalse(control == null);
		assertTrue(control.getModel().equals(model));
		assertTrue(control instanceof SierpinskiCarpetController);
	}
	
	@Test
	public void testSetLevel(){
		model.setLevel(2);
		assertTrue(model.getLevel() == 2);
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
	public void testUsesLocation(){
		assertFalse(model.getUsesLocation());
	}
	
	@Test
	public void testGetParamLabels(){
		String[] labels = model.getParamLabels();
		for(int i = 0; i < 7; i++){
			assertFalse(labels[i] == null);
			assertFalse(labels[i].isEmpty());
		}
	}
}
