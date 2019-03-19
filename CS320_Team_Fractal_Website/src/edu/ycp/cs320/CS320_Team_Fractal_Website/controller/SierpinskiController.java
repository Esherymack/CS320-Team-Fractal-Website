package edu.ycp.cs320.CS320_Team_Fractal_Website.controller;

import java.awt.Point;
import java.io.IOException;

import edu.ycp.cs320.CS320_Team_Fractal_Website.model.Sierpinski;
import edu.ycp.cs320.CS320_Team_Fractal_Website.renderer.Renderer;

public class SierpinskiController 
{
	private Sierpinski model;
	
	public SierpinskiController(Sierpinski model)
	{
		this.model = model;
	}
	
	public void level(int l)
	{
		model.setLevel(l);
	}
	
	public void height()
	{
		model.setHeight();
	}
	
	public void setPoints()
	{
		model.setP1();
		model.setP2();
		model.setP3();
	}
	
	public boolean buildSierpinski(int l) throws InterruptedException, IOException
	{
		Renderer r = new Renderer("sierpinski");
		level(l);
		height();
		setPoints();
		Point p1 = model.getP1();
		Point p2 = model.getP2();
		Point p3 = model.getP3();
		Object[] arguments = {
			model.getLevel(),
			p1,
			p2,
			p3
		};
		boolean result = r.renderImage(r, arguments);
		return result;
	}
}
