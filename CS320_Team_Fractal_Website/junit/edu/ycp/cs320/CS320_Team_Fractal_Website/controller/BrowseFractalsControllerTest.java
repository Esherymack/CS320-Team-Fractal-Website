package edu.ycp.cs320.CS320_Team_Fractal_Website.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.pages.BrowseFractalsController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Fractal;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Gradient;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Mandelbrot;

public class BrowseFractalsControllerTest{
	
	private BrowseFractalsController controller;
	
	@Before
	public void setUp(){
		controller = new BrowseFractalsController();
	}
	
	@Test
	public void testGetAllFractals(){ 
		ArrayList<Fractal> fractals = controller.getAllFractals();
		assertFalse(fractals == null);
	}
	
	@Test
	public void testGetAllFractalsByType(){
		ArrayList<Fractal> fractals = controller.getAllFractalsByType(new Mandelbrot().getType());
		assertFalse(fractals == null);
	}

	@Test
	public void testGetAllFractalsWithCharSeq(){
		ArrayList<Fractal> fractals = controller.getAllFractalsWithCharSeq("test");
		assertFalse(fractals == null);
	}

	@Test
	public void testGetAllFractalsByGradientType(){
		ArrayList<Fractal> fractals = controller.getAllFractalsByGradientType(Gradient.DIAGONAL);
		assertFalse(fractals == null);
	}

	@Test
	public void testGetUsernameByFractalId(){
		String name = controller.getUsernameByFractalId(0);
		assertFalse(name == null);
		name = controller.getUsernameByFractalId(-1);
		assertTrue(name.equals("Unknown"));
	}

	@Test
	public void testGetFractalPageList(){
		ArrayList<Fractal> fractals = new ArrayList<Fractal>();
		for(int i = 0; i < 101; i++) fractals.add(new Mandelbrot());

		ArrayList<Fractal> pageList = controller.getFractalPageList(null, 0, 0);
		assertFalse(pageList == null);
		assertTrue(pageList.size() == 0);

		pageList = controller.getFractalPageList(fractals, 2, 0);
		assertFalse(pageList == null);
		assertTrue(pageList.size() == 2);
		
		pageList = controller.getFractalPageList(fractals, 100, 0);
		assertFalse(pageList == null);
		assertTrue(pageList.size() == 100);
		
		pageList = controller.getFractalPageList(fractals, 100, 1);
		assertFalse(pageList == null);
		assertTrue(pageList.size() == 1);
		
		pageList = controller.getFractalPageList(fractals, 10, 4);
		assertFalse(pageList == null);
		assertTrue(pageList.size() == 10);
	}
}
