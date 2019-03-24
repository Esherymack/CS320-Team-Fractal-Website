package edu.ycp.cs320.CS320_Team_Fractal_Website.model.temp;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CS320_Team_Fractal_Website.model.temp.GuessingGame;

public class GuessingGameTest 
{
	private GuessingGame model;
	
	@Before
	public void setUp() 
	{
		model = new GuessingGame();
	}
	
	@Test
	public void testSetMin() 
	{
		model.setMin(1);
		assertEquals(1, model.getMin());
	}
	
	@Test
	public void testSetMax() 
	{
		model.setMax(100);
		assertEquals(100, model.getMax());
	}
	
	@Test
	public void testGetMin()
	{
		model.setMin(1);
		assertEquals(1, model.getMin());
	}
	
	@Test
	public void testGetMax()
	{
		model.setMax(100);
		assertEquals(100, model.getMax());
	}
	
	@Test
	public void testIsDone()
	{
		model.setMin(50);
		model.setMax(50);
		assertTrue(model.isDone());
	}
	
	@Test
	public void testGetGuess()
	{
		model.setMin(1);
		model.setMax(100);
		int valAdjust = 50;
		assertEquals(model.getGuess(), valAdjust);
	}
	
	@Test
	public void testSetIsLessThan()
	{
		model.setMax(100);
		model.setIsLessThan(100);
		assertEquals(99, model.getMax());
	}
	
	@Test
	public void testSetIsGreaterThan()
	{
		model.setMin(41);
		model.setIsGreaterThan(41);
		assertEquals(42, model.getMin());
	}
}
