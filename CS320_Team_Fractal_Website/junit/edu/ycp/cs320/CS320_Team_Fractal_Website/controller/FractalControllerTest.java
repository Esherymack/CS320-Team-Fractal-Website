package edu.ycp.cs320.CS320_Team_Fractal_Website.controller;

import static org.junit.Assert.assertTrue;

import java.awt.image.BufferedImage;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.FractalController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Fractal;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Gradient;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Sierpinski;

public class FractalControllerTest{
	
	private FractalController controller;
	
	@Before
	public void setUp(){
		controller = new FractalController() {
			@Override
			public BufferedImage renderImage() {
				return null;
			}
			@Override
			public Fractal getModel() {
				return new Fractal() {
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
				};
			}
			@Override
			public boolean acceptParameters(String[] params) {
				return false;
			}
		};
	}
	
	@Test
	public void testSetGradientType(){
		controller.setGradientType("None");
		assertTrue(controller.getGradientType().equals("None"));
		controller.setGradientType("Rainbow");
		assertTrue(controller.getGradientType().equals("Rainbow"));
	}
	@Test
	public void testSetGradient(){
		Gradient gradient = new Gradient();
		controller.setGradient(gradient);
		assertTrue(controller.getGradient().equals(gradient));
	}
	
	@Test
	public void testSendImage(){
		BufferedImage img = new BufferedImage(10, 10, BufferedImage.TYPE_3BYTE_BGR);
		assertTrue(controller.sendImage(img));
	}
	
	@Test
	public void testRender(){
		Fractal f = new Sierpinski();
		FractalController c = f.createApproprateController();
		assertTrue(c.render());
	}
	
}
