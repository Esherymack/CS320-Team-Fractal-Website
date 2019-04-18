package edu.ycp.cs320.CS320_Team_Fractal_Website.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.BarnsleyController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Barnsley;


public class BarnsleyControllerTest {
	
	private Barnsley model;
	private BarnsleyController controller;
	
	@Before
	public void setUp(){
		model = new Barnsley();
		controller = new BarnsleyController();
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
		
		params = new String[]{"1", "1", "1", "1", "1.0", "1"};
		assertFalse(controller.acceptParameters(params));
		
		params = new String[]{"1.0", "1", "1", "1", "1", "1"};
		assertTrue(controller.acceptParameters(params));

		params = new String[]{"a"};
		assertFalse(controller.acceptParameters(params));

		params = new String[]{"1.0", null, null, null, null, null, null, null, null, null};
		assertFalse(controller.acceptParameters(params));
		
		params = new String[]{"1", "2", "3", "4", "5", "6", null, null, null, null};
		assertTrue(controller.acceptParameters(params));
		
		params = model.getParameters();
		assertTrue(controller.acceptParameters(params));
	}
}
