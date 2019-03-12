package edu.ycp.cs320.CS320_Team_Fractal_Website.controller;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.GuessingGameController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.GuessingGame;

public class GuessingGameControllerTest 
{
	private GuessingGame model;
	private GuessingGameController controller;
	
	@Before
	public void setUp() 
	{
		model = new GuessingGame();
		controller = new GuessingGameController();
		
		model.setMin(1);
		model.setMax(100);
		
		controller.setModel(model);
	}
	
	@Test
	public void testNumberIsGreater() 
	{
		int currentGuess = model.getGuess();
		controller.setNumberIsGreaterThanGuess();
		assertTrue(model.getGuess() > currentGuess);
	}
	
	@Test
	public void testIsLess()
	{
		int currentGuess = model.getGuess();
		controller.setNumberIsLessThanGuess();
		assertTrue(model.getGuess() < currentGuess);
	}
	
	@Test
	public void testFound()
	{
		int currentGuess = model.getGuess();
		controller.setNumberFound();
		assertTrue(model.getGuess() == currentGuess);
	}
}
