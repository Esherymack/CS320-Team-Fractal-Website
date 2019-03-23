package edu.ycp.cs320.CS320_Team_Fractal_Website.model;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ComplexTest{
	
	private Complex zero;
	private Complex c1;
	private Complex c2;
	private Complex c3;
	
	@Before
	public void setUp(){
		zero = new Complex(0, 0);
		
		c1 = new Complex(2, -2);
		c2 = new Complex(0, 1);
		c3 = new Complex(-2.5, 1);
	}
	
	@Test
	public void testGetNums(){
		assertTrue(zero.getRealNum() == 0);
		assertTrue(zero.getImagNum() == 0);
		
		assertTrue(c1.getRealNum() == 2);
		assertTrue(c1.getImagNum() == -2);

		assertTrue(c2.getRealNum() == 0);
		assertTrue(c2.getImagNum() == 1);

		assertTrue(c3.getRealNum() == -2.5);
		assertTrue(c3.getImagNum() == 1);
	}
	
	@Test
	public void testGetMagnitude(){
		assertTrue(zero.getMagnitude() == 0);
		assertTrue(c1.getMagnitude() == Math.sqrt(8));
		assertTrue(c2.getMagnitude() == 1);
		assertTrue(c3.getMagnitude() == Math.sqrt(7.25));
	}
	
	@Test
	public void testAdd(){
		Complex a = zero.add(c1);
		assertTrue(a.getRealNum() == 2);
		assertTrue(a.getImagNum() == -2);

		a = c1.add(c2);
		assertTrue(a.getRealNum() == 2);
		assertTrue(a.getImagNum() == -1);
		
		a = c1.add(c3);
		assertTrue(a.getRealNum() == -0.5);
		assertTrue(a.getImagNum() == -1);
	}
	
	@Test
	public void tetsMultiply(){
		Complex a = zero.multiply(c1);
		assertTrue(a.getRealNum() == 0);
		assertTrue(a.getImagNum() == 0);

		a = c1.multiply(c2);
		assertTrue(a.getRealNum() == 2);
		assertTrue(a.getImagNum() == 2);
		
		a = c1.multiply(c3);
		assertTrue(a.getRealNum() == -3);
		assertTrue(a.getImagNum() == 7);
	}
	
}
