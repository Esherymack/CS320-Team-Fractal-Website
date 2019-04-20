package edu.ycp.cs320.CS320_Team_Fractal_Website.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.BarnsleyController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.FractalController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Barnsley;
import edu.ycp.cs320.CS320_Team_Fractal_Website.servlet.MainPageServlet;

public class BarnsleyTest {
	
	private Barnsley model;
	
	@Before
	public void setUp(){
		model = new Barnsley();
	}
	
	@Test
	public void testConstructor(){
		model = new Barnsley(1, 2, 3, 4, 5, 6);
		
		assertTrue(model.getF1() == 1);
		assertTrue(model.getF2() == 2);
		assertTrue(model.getF3() == 3);
		assertTrue(model.getF4() == 4);
		assertTrue(model.getSeed() == 5);
		assertTrue(model.getIterations() == 6);
	}
	
	@Test
	public void testGetInfo(){
		String info = model.getInfo();
		assertFalse(info == null);
		assertFalse(info.isEmpty());
	}

	@Test
	public void testSetDefaultParameters(){
		model.setDefaultParameters();
		
		assertTrue(model.getF1() == 0.01);
		assertTrue(model.getF2() == 0.85);
		assertTrue(model.getF3() == 0.07);
		assertTrue(model.getF4() == 0.07);
		assertTrue(model.getSeed() == 0);
		assertTrue(model.getIterations() == 100000);
	}
	
	@Test
	public void testGetParameters(){
		String[] params = model.getParameters();
		assertTrue(params.length == MainPageServlet.NUM_PARAMS);
		assertTrue(params[0] != null);
		assertFalse(params[0].isEmpty());
		assertTrue(params[1] != null);
		assertFalse(params[1].isEmpty());
		assertTrue(params[2] != null);
		assertFalse(params[2].isEmpty());
		assertTrue(params[3] != null);
		assertFalse(params[3].isEmpty());
		assertTrue(params[4] != null);
		assertFalse(params[4].isEmpty());
		assertTrue(params[5] != null);
		assertFalse(params[5].isEmpty());
	}
	
	@Test
	public void testCreateAppropriateController(){
		FractalController control = model.createApproprateController();
		
		assertFalse(control == null);
		assertTrue(control.getModel().equals(model));
		assertTrue(control instanceof BarnsleyController);
	}
	
	@Test
	public void testSetF(){
		model.setF1(1);
		model.setF2(2);
		model.setF3(3);
		model.setF4(4);

		assertTrue(model.getF1() == 1);
		assertTrue(model.getF2() == 2);
		assertTrue(model.getF3() == 3);
		assertTrue(model.getF4() == 4);
	}

	@Test
	public void testSetSeed(){
		model.setSeed(12345);
		assertTrue(model.getSeed() == 12345);
	}
	
	@Test
	public void testSetIterations(){
		model.setIterations(2);
		assertTrue(model.getIterations() == 2);
	}

	@Test
	public void testGetParamLabels(){
		String[] labels = model.getParamLabels();
		for(int i = 0; i < 6; i++){
			assertFalse(labels[i] == null);
			assertFalse(labels[i].isEmpty());
		}
	}
}
