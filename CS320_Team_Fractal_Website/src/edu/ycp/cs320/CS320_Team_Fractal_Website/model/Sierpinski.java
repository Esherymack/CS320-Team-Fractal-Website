package edu.ycp.cs320.CS320_Team_Fractal_Website.model;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

public class Sierpinski 
{
	private int level, height, SIZE;
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
	
	public void setSize(int size)
	{
		this.SIZE = size;
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
	
	public int getHeight()
	{
		return (int)Math.round(SIZE * Math.sqrt(3.0)/2.0);
	}
	
	public int getLevel()
	{
		return level;
	}
	
	public static void drawFig(int level, Graphics g, Point p1, Point p2, Point p3)
	{
		// this is a recursive function
		if(level == 1)
		{
			Polygon p = new Polygon();
			p.addPoint(p1.x,  p1.y);
			p.addPoint(p2.x,  p2.y);
			p.addPoint(p3.x,  p3.y);
			g.fillPolygon(p);
		}
		else
		{
			Point p4 = midpoint(p1, p2);
			Point p5 = midpoint(p2, p3);
			Point p6 = midpoint(p1, p3);
			
			drawFig(level - 1, g, p1, p4, p6);
			drawFig(level - 1, g, p4, p2, p5);
			drawFig(level - 1, g, p6, p5, p3);
		}
	}
	
	public static Point midpoint(Point p1, Point p2)
	{
		return new Point((p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
	}
}
