package edu.ycp.cs320.CS320_Team_Fractal_Website.controller.temp;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.temp.NumbersController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.temp.Numbers;

public class NumbersControllerTest
{
	private Numbers model;
	private NumbersController controller;
	
	@Before
	public void setUp()
	{
		model = new Numbers();
		controller = new NumbersController(model);
		
		model.setFirst(10.0);
		model.setSecond(20.0);
		model.setThird(30.0);

	}

	@Test
	public void testAdd()
	{
		Double firstNum = model.getFirst();
		Double secondNum = model.getSecond();
		Double thirdNum = model.getThird();
		Double result = firstNum + secondNum + thirdNum;
		assertEquals(model.getAdd(), result);
	}
	
	@Test
	public void testMult()
	{
		Double firstNum = model.getFirst();
		Double secondNum = model.getSecond();
		Double result = firstNum * secondNum;
		assertEquals(model.getMult(), result);
	}
}
