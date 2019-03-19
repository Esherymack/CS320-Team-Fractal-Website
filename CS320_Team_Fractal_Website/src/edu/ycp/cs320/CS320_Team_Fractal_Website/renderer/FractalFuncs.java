package edu.ycp.cs320.CS320_Team_Fractal_Website.renderer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

public class FractalFuncs 
{
	public static void drawSierpinski(int level, Graphics g, Point p1, Point p2, Point p3)
	{	
		g.setColor(Color.RED);
		
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
			
			drawSierpinski(level - 1, g, p1, p4, p6);
			drawSierpinski(level - 1, g, p4, p2, p5);
			drawSierpinski(level - 1, g, p6, p5, p3);
		}
	}
	
	public static Point midpoint(Point p1, Point p2)
	{
		return new Point((p1.x + p2.x) / 2, (p1.y + p2.y) / 2);
	}
}
