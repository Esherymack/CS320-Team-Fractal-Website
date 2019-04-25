package edu.ycp.cs320.CS320_Team_Fractal_Website.model;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.FractalController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Fractal;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Gradient;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Location;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Mandelbrot;

public class FractalTest{

	private Fractal model;
	
	@Before
	public void setUp(){
		model = new Fractal() {
			@Override
			public String[] getParameters() {
				return null;
			}
			@Override
			public String getInfo() {
				return null;
			}
			@Override
			public FractalController createApproprateController() {
				return null;
			}
			@Override
			public String[] getParamLabels() {
				return null;
			}
			@Override
			public String getParamExamples() {
				return null;
			}
		};
	}
	
	@Test
	public void testConstructor(){
		model = new Fractal(new Location(1, 2, 3, 4)){
			@Override
			public String[] getParameters() {
				return null;
			}
			@Override
			public String getInfo() {
				return null;
			}
			@Override
			public FractalController createApproprateController() {
				return null;
			}
			@Override
			public String[] getParamLabels() {
				return null;
			}
			@Override
			public String getParamExamples() {
				return null;
			}
		};

		Location l = model.getLocation();
		assertTrue(l.getX1() == 1);
		assertTrue(l.getY1() == 2);
		assertTrue(l.getX2() == 3);
		assertTrue(l.getY2() == 4);
	}
	
	@Test
	public void testGetDefaultLocation(){
		Location l = model.getDefaultLocation();
		assertTrue(l.getX1() == -1);
		assertTrue(l.getY1() == 1);
		assertTrue(l.getX2() == 1);
		assertTrue(l.getY2() == -1);
	}
	
	@Test
	public void testSetNameAndGetName(){
		model.setName("fractal 1");
		assertTrue(model.getName().equals("fractal 1"));
	}
	
	@Test
	public void testSetIdAndGetId(){
		model.setId(1337);
		assertTrue(model.getId() == 1337);
	}
	
	@Test
	public void testSetDefaultParameters(){
		model.setDefaultParameters();
		
		Location l = model.getLocation();
		assertTrue(l.getX1() == -1);
		assertTrue(l.getY1() == 1);
		assertTrue(l.getX2() == 1);
		assertTrue(l.getY2() == -1);
	}
	
	@Test
	public void testSetLocation(){
		Location l = new Location(3, 5, 1, -2);
		model.setLocation(l);
		assertTrue(l.getX1() == 3);
		assertTrue(l.getY1() == 5);
		assertTrue(l.getX2() == 1);
		assertTrue(l.getY2() == -2);
	}
	
	@Test
	public void testGetType(){
		Fractal f = new Mandelbrot();
		assertTrue(f.getType().equals("Mandelbrot"));
	}

	@Test
	public void testSetGradientType(){
		model.setGradientType("None");
		assertTrue(model.getGradientType().equals("None"));
		model.setGradientType("Rainbow");
		assertTrue(model.getGradientType().equals("Rainbow"));
	}
	@Test
	public void testSetGradient(){
		Gradient gradient = new Gradient();
		model.setGradient(gradient);
		assertTrue(model.getGradient().equals(gradient));
	}
	
}
