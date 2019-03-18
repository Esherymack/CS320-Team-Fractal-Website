package edu.ycp.cs320.CS320_Team_Fractal_Website.controller;

import edu.ycp.cs320.CS320_Team_Fractal_Website.model.Sierpinski;

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
	
	public void size(int s)
	{
		model.setSize(s);
		model.setHeight();
	}
	
	public void buildSierpinski()
	{
		
	}
}
