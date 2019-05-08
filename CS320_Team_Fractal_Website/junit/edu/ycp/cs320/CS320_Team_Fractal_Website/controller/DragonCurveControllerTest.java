package edu.ycp.cs320.CS320_Team_Fractal_Website.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.DragonCurveController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.DragonCurve;

public class DragonCurveControllerTest{
	
	private DragonCurveController controller;
	private DragonCurveController defaultController;
	private DragonCurve model;
	
	@Before
	public void setUp(){
		model = new DragonCurve();
		
		controller = new DragonCurveController(model);
		defaultController = new DragonCurveController();
	}
	
	@Test
	public void testSetModel(){
		defaultController.setModel(model);
		assertTrue(defaultController.getModel().equals(model));
	}

	@Test
	public void testRenderImage(){
		assertFalse(controller.renderImage() == null);
	}
	
	public void testCalculateDragonDirections(){
		model.setIterations(100);
		int[] expected = new int[]{1, 1, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 1};
		int[] direc = controller.calculateDragonDirections();
		
		for(int i = 0; i < expected.length; i++){
			assertTrue(expected[i] == direc[i]);
		}
	}

	@Test
	public void acceptParameters(){
		assertFalse(controller.acceptParameters(new String[]{""}));
		assertFalse(controller.acceptParameters(new String[]{"a", "1", "2", "3"}));
		assertTrue(controller.acceptParameters(new String[]{"0", "1", "2", "3"}));
	}
}
