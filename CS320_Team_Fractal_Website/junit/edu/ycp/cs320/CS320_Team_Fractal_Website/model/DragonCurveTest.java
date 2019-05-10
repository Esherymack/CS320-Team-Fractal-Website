package edu.ycp.cs320.CS320_Team_Fractal_Website.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.geom.Point2D;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.DragonCurveController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.FractalController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.DragonCurve;

public class DragonCurveTest {
	
	private DragonCurve model;
	private DragonCurve defaultModel;
	
	@Before
	public void setUp(){
		model = new DragonCurve(1000, 2, 100, -200);
		defaultModel = new DragonCurve();
	}
	
	@Test
	public void setDefaultParameters(){
		defaultModel.setDefaultParameters();
		
		assertTrue(defaultModel.getIterations() == 100000);
		assertTrue(defaultModel.getScalar() == 1);
		assertTrue(defaultModel.getCenter().x == 0);
		assertTrue(defaultModel.getCenter().y == 0);
	}
	
	@Test
	public void testSetIterations(){
		model.setIterations(102);
		assertTrue(model.getIterations() == 102);
	}
	
	@Test
	public void testSetScalar(){
		model.setScalar(3);
		assertTrue(model.getScalar() == 3);
	}
	
	@Test
	public void testSetCenter(){
		model.setCenter(new Point2D.Double(2, 4));
		assertTrue(model.getCenter().x == 2);
		assertTrue(model.getCenter().y == 4);
		
		model.setCenter(3, 6);
		assertTrue(model.getCenter().x == 3);
		assertTrue(model.getCenter().y == 6);
	}
	
	@Test
	public void testGetInfo(){
		assertFalse(model.getInfo() == null);
		assertFalse(model.getInfo().isEmpty());
	}

	@Test
	public void testGetParamExamples(){
		assertFalse(model.getParamExamples() == null);
		assertFalse(model.getParamExamples().isEmpty());
	}

	@Test
	public void testGetParameters(){
		String[] params = model.getParameters();
		
		assertFalse(params[0] == null);
		assertFalse(params[1] == null);
		assertFalse(params[2] == null);
		assertFalse(params[3] == null);
		
		assertFalse(params[0].isEmpty());
		assertFalse(params[1].isEmpty());
		assertFalse(params[2].isEmpty());
		assertFalse(params[3].isEmpty());
	}

	@Test
	public void testCreateApproprateController() {
		FractalController controller = model.createApproprateController();
		assertFalse(controller == null);
		assertTrue(controller instanceof DragonCurveController);
	}

	@Test
	public void testGetParamLabels(){
		String[] params = model.getParamLabels();
		
		assertFalse(params[0] == null);
		assertFalse(params[1] == null);
		assertFalse(params[2] == null);
		assertFalse(params[3] == null);
		
		assertFalse(params[0].isEmpty());
		assertFalse(params[1].isEmpty());
		assertFalse(params[2].isEmpty());
		assertFalse(params[3].isEmpty());
	}

	@Test
	public void testUsesLocation(){
		assertFalse(model.getUsesLocation());
	}
}
