package edu.ycp.cs320.CS320_Team_Fractal_Website.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.CS320_Team_Fractal_Website.model.Numbers;

public class NumbersTest
{
	private Numbers model;
	
	@Before
	public void setUp()
	{
		model = new Numbers();
	}
	
	@Test
	public void testSetGet()
	{
		Double num = 60.0;
		model.setFirst(num);
		assertEquals(model.getFirst(), num);
	}
	
	@Test
	public void testAdd()
	{
		model.setFirst(10.0);
		model.setSecond(20.0);
		model.setThird(30.0);
		Double num = 10.0 + 20.0 + 30.0;
		
		Double result = model.getAdd();
		assertEquals(result, num);
	}
	
	@Test
	public void testSetMult()
	{
		model.setFirst(10.0);
		model.setSecond(6.0);
		
		Double num = 60.0;
		Double result = model.getMult();
		assertEquals(result, num);
	}
}