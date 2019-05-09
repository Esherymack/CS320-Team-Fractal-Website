package edu.ycp.cs320.CS320_Team_Fractal_Website.model;

/* Complex is Dakota's Complex class */

public class Complex 
{
	private double real;
	private double imag;
	
	public Complex(double real, double imag)
	{
		this.real = real;
		this.imag = imag;
	}
	
	public Complex add(Complex other)
	{
		return new Complex(real + other.real, imag + other.imag);
	}
	
	public Complex multiply(Complex other)
	{
		return new Complex(real * other.real - imag * other.imag, real * other.imag + imag * other.real);
	}
	
	public Complex getAbsoluteValue()
	{
		return new Complex(Math.abs(real), Math.abs(imag));
	}
	
	public Complex getSquareRoot()
	{
		return new Complex(Math.sqrt(real), Math.sqrt(imag));
	}
	
	public Complex getComplexConjugate()
	{
		return new Complex(real, -imag);
	}
	
	public double getMagnitude()
	{
		return Math.sqrt((real*real)+(imag*imag));
	}
	
	public double getRealNum()
	{
		return real;
	}
	
	public double getImagNum()
	{
		return imag;
	}
}
