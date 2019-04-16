package edu.ycp.cs320.CS320_Team_Fractal_Website.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.KochController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Koch;

public class KochControllerTest {
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
	
}
