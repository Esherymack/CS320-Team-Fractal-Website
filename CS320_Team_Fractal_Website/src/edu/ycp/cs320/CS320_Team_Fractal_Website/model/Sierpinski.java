package edu.ycp.cs320.CS320_Team_Fractal_Website.model;

import java.awt.Point;

public class Sierpinski 
{
	public static final int SIZE = 500;
	private int level, height;
	private Point p1, p2, p3;
	
	public Sierpinski() {}
	
	public void setLevel(int levelParam)
	{
		this.level = levelParam;
	}
	
	public void setHeight()
	{
		this.height = (int)Math.round(SIZE * Math.sqrt(3.0)/2.0);
	}
	
	public void setP1()
	{
		this.p1 = new Point(0, height);
	}
	
	public void setP2()
	{
		this.p2 = new Point(SIZE/2, 0);
	}
	
	public void setP3()
	{
		this.p3 = new Point(SIZE, height);
	}
	
	public Point getP1()
	{
		return p1;
	}
	
	public Point getP2()
	{
		return p2;
	}
	
	public Point getP3()
	{
		return p3;
	}
	public int getHeight()
	{
		return (int)Math.round(SIZE * Math.sqrt(3.0)/2.0);
	}
	
	public int getLevel()
	{
		return level;
	}
}
