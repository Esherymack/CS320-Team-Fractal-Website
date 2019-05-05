package edu.ycp.cs320.CS320_Team_Fractal_Website.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.geom.Point2D;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.FractalController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.HexaflakeController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Hexaflake;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Location;

public class HexaflakeTest{
	
	private Hexaflake model;
	private Hexaflake defaultModel;
	
	@Before
	public void setUp(){
		model = new Hexaflake(7);
		assertTrue(model.getLevel() == 7);
		
		defaultModel = new Hexaflake();
		assertTrue(defaultModel.getLevel() == 1);
	}
	
	@Test
	public void testGetDefaultLocation(){
		Location l = model.getDefaultLocation();
		assertFalse(l == null);
		assertTrue(l.getX1() == 0);
		assertTrue(l.getY1() == 0);
		assertTrue(l.getX2() == Hexaflake.SIZE);
		assertTrue(l.getY2() == Hexaflake.SIZE);
	}

	@Test
	public void testSetDefaultParameters(){
		model.setDefaultParameters();
		assertFalse(model.getP1() == null);
		assertFalse(model.getP2() == null);
		assertFalse(model.getP3() == null);
		assertFalse(model.getP4() == null);
		assertFalse(model.getP5() == null);
		assertFalse(model.getP6() == null);
		assertTrue(model.getLevel() == 2);
	}

	@Test
	public void testGetInfo(){
		assertFalse(model.getInfo() == null);
	}

	@Test
	public void testGetParamExamples(){
		assertFalse(model.getParamExamples() == null);
	}

	@Test
	public void testGetParameters(){
		String[] params = model.getParameters();
		for(int i = 0; i < 13; i++){
			assertFalse(params[i] == null);
			assertFalse(params[i].isEmpty());
		}
	}

	@Test
	public void testCreateApproprateController(){
		FractalController control = model.createApproprateController();
		assertTrue(control instanceof HexaflakeController);
	}

	@Test
	public void testGetParamLabels(){
		String[] params = model.getParamLabels();
		for(int i = 0; i < 13; i++){
			assertFalse(params[i] == null);
			assertFalse(params[i].isEmpty());
		}
	}

	@Test
	public void testUsesLocation(){
		assertFalse(model.usesLocation());
	}

	@Test
	public void testSetLevel(){
		model.setLevel(3);
		assertTrue(model.getLevel() == 3);
	}

	@Test
	public void testSetHeight(){
		model.setHeight(430);
		assertTrue(model.getHeight() == 430);
	}

	@Test
	public void testSetP1(){
		Point2D.Double p = new Point2D.Double(2, 4);
		model.setP1(p);
		assertTrue(model.getP1().equals(p));
	}
	@Test
	public void testSetP2(){
		Point2D.Double p = new Point2D.Double(62, 4);
		model.setP2(p);
		assertTrue(model.getP2().equals(p));
	}
	@Test
	public void testSetP3(){
		Point2D.Double p = new Point2D.Double(2, 84);
		model.setP3(p);
		assertTrue(model.getP3().equals(p));
	}
	@Test
	public void testSetP4(){
		Point2D.Double p = new Point2D.Double(42, 4);
		model.setP4(p);
		assertTrue(model.getP4().equals(p));
	}
	@Test
	public void testSetP5(){
		Point2D.Double p = new Point2D.Double(2, 43);
		model.setP5(p);
		assertTrue(model.getP5().equals(p));
	}
	@Test
	public void testSetP6(){
		Point2D.Double p = new Point2D.Double(42, 44);
		model.setP6(p);
		assertTrue(model.getP6().equals(p));
	}

}
