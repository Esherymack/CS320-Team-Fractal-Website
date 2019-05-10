package edu.ycp.cs320.CS320_Team_Fractal_Website.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.FractalController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.PythagorasTreeController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.PythagorasTree;
import edu.ycp.cs320.CS320_Team_Fractal_Website.servlet.MainPageServlet;

public class PythagorasTreeTest{
	
	private PythagorasTree model;
	private PythagorasTree defaultModel;
	
	@Before
	public void setUp(){
		model = new PythagorasTree(5, 100, 20);
		defaultModel = new PythagorasTree();
	}
	
	@Test
	public void testSetIterations(){
		model.setIterations(3);
		assertTrue(model.getIterations() == 3);
	}
	
	@Test
	public void testSetInitialSize(){
		model.setInitialSize(120);
		assertTrue(model.getInitialSize() == 120);
	}
	
	@Test
	public void testSetAngle(){
		model.setAngle(30);
		assertTrue(model.getAngle() == 30);
	}
	
	@Test
	public void testSetDefaultParameters(){
		defaultModel.setDefaultParameters();
		assertTrue(defaultModel.getIterations() == 13);
		assertTrue(defaultModel.getInitialSize() == 130);
		assertTrue(defaultModel.getAngle() == 45);
	}
	
	@Test
	public void testGetInfo(){
		String info = model.getInfo();
		assertFalse(info == null);
		assertFalse(info.isEmpty());
	}

	@Test
	public void testGetParamExamples(){
		String examples = model.getParamExamples();
		assertFalse(examples == null);
		assertFalse(examples.isEmpty());}

	@Test
	public void testGetParameters(){
		String[] params = model.getParameters();

		assertFalse(params[0] == null);
		assertFalse(params[0].isEmpty());
		assertFalse(params[1] == null);
		assertFalse(params[1].isEmpty());
		assertFalse(params[2] == null);
		assertFalse(params[2].isEmpty());
		
		assertTrue(params.length == MainPageServlet.NUM_PARAMS);
	}

	@Test
	public void testCreateApproprateController(){
		FractalController control = model.createApproprateController();
		assertFalse(control == null);
		assertTrue(control instanceof PythagorasTreeController);
	}

	@Test
	public void testGetParamLabels(){
		String[] params = model.getParamLabels();

		assertFalse(params[0] == null);
		assertFalse(params[0].isEmpty());
		assertFalse(params[1] == null);
		assertFalse(params[1].isEmpty());
		assertFalse(params[2] == null);
		assertFalse(params[2].isEmpty());
		
		assertTrue(params.length == MainPageServlet.NUM_PARAMS);
	}

	@Test
	public void testGetUsesLocation(){
		assertFalse(model.getUsesLocation());
	}
}
