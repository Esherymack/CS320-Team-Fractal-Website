package edu.ycp.cs320.CS320_Team_Fractal_Website.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.*;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.*;

public class RenderTest 
{
	private Sierpinski sierModel;
	private Mandelbrot mandelModel;
	private SierpinskiController sierController;
	private MandelbrotController mandelController;
	
	@Before
	public void setUp()
	{
		sierModel = new Sierpinski();
		sierController = new SierpinskiController(sierModel);
		
		mandelModel = new Mandelbrot();
		mandelController = new MandelbrotController(mandelModel);
		
		sierModel.setLevel(8);
	}
	
	@Test
	public void sierpinskiRenderedTest()
	{
		boolean isRendered = true;
		assertEquals(sierController.render(), isRendered);
	}
	
	@Test
	public void mandelbrotRenderedTest()
	{
		boolean isRendered = true;
		assertEquals(mandelController.render(), isRendered);
	}
	
}
