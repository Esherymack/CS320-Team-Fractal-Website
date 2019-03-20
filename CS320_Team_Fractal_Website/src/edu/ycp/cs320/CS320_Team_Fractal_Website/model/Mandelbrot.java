package edu.ycp.cs320.CS320_Team_Fractal_Website.model;

public class Mandelbrot 
{
	double x1, y1, x2, y2;
	
	public Mandelbrot() {}
	
	public void setCoords(double X1, double Y1, double X2, double Y2)
	{
		this.x1 = X1;
		this.y1 = Y1;
		this.x2 = X2;
		this.y2 = Y2;
	}
	
	public double getX1()
	{
		return x1;
	}
	
	public double getX2()
	{
		return x2;
	}
	
	public double getY1()
	{
		return y1;
	}
	
	public double getY2()
	{
		return y2;
	}
}
