package edu.ycp.cs320.CS320_Team_Fractal_Website.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.PythagorasTreeController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Gradient;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.PythagorasTree;

public class PythagorasTreeControllerTest {
	
	private PythagorasTree model;
	
	private PythagorasTreeController controller;
	private PythagorasTreeController defaultController;
	
	@Before
	public void setUp(){
		model = new PythagorasTree();
		
		controller = new PythagorasTreeController(model);
		defaultController = new PythagorasTreeController();
	}
	
	@Test
	public void testSetModel(){
		defaultController.setModel(model);
		assertTrue(controller.getModel().equals(model));
		assertTrue(defaultController.getModel().equals(model));
	}

	@Test
	public void testRenderImage(){
		assertFalse(controller.renderImage() == null);
		
		model.setIterations(0);
		assertFalse(controller.renderImage() == null);
		model.setIterations(10);
		assertFalse(controller.renderImage() == null);
	}
	
	@Test
	public void testChooseColor(){
		controller.setGradientType(Gradient.DIAGONAL);
		assertFalse(controller.chooseColor(2, 5) == null);
		controller.setGradientType(Gradient.NONE);
		assertFalse(controller.chooseColor(29999, 5) == null);
		controller.setGradientType(Gradient.HORIZONTAL);
		assertFalse(controller.chooseColor(2, 888885) == null);
		controller.setGradientType(Gradient.VERTICAL);
		assertFalse(controller.chooseColor(0, 5) == null);
		controller.setGradientType(Gradient.RAINBOW);
		assertFalse(controller.chooseColor(2, 0) == null);
	}

	@Test
	public void testAcceptParameters(){
		assertFalse(controller.acceptParameters(new String[]{}));
		assertFalse(controller.acceptParameters(new String[]{""}));
		assertFalse(controller.acceptParameters(new String[]{"1"}));
		assertFalse(controller.acceptParameters(new String[]{"1", "2", "a"}));
		assertTrue(controller.acceptParameters(new String[]{"1", "2", "3"}));
	}
}
