package edu.ycp.cs320.CS320_Team_Fractal_Website.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.FractalController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.JuilaController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Julia;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Location;
import edu.ycp.cs320.CS320_Team_Fractal_Website.servlet.MainPageServlet;

public class JuliaTest{

	private Julia model;
	
	@Before
	public void setUp(){
		model = new Julia();
	}
	
	@Test
	public void testConstructor(){
		Complex c = new Complex(1, -2);
		model = new Julia(c);
		assertTrue(model.getConstant().equals(c));
	}
	
	@Test
	public void testSetDefaultParameters(){
		model.setDefaultParameters();
		
		Complex c = model.getConstant();
		assertTrue(c.getRealNum() == 0);
		assertTrue(c.getImagNum() == 0);
		
		Location l = model.getLocation();
		assertTrue(l.getX1() == -2);
		assertTrue(l.getY1() == -2);
		assertTrue(l.getX2() == 2);
		assertTrue(l.getY2() == 2);
	}
	
	@Test
	public void testGetDefaultLocation(){
		Location l = model.getDefaultLocation();
		
		assertTrue(l.getX1() == -2);
		assertTrue(l.getY1() == -2);
		assertTrue(l.getX2() == 2);
		assertTrue(l.getY2() == 2);
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
		assertFalse(params[4] == null);
		assertFalse(params[4].isEmpty());
	}
	
	@Test
	public void testCreateAppropriateController(){
		FractalController control = model.createApproprateController();
		
		assertFalse(control == null);
		assertTrue(control.getModel().equals(model));
		assertTrue(control instanceof JuilaController);
	}
	
	@Test
	public void testSetConstant(){
		Complex c = new Complex(1, -2);
		
		model.setConstant(c);
		
		assertTrue(model.getConstant().equals(c));
	}

	@Test
	public void testUsesLocation(){
		assertTrue(model.usesLocation());
	}

	@Test
	public void testGetParamLabels(){
		String[] labels = model.getParamLabels();
		for(int i = 0; i < 5; i++){
			assertFalse(labels[i] == null);
			assertFalse(labels[i].isEmpty());
		}
	}

}
