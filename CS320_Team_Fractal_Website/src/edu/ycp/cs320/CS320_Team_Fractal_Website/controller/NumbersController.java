package edu.ycp.cs320.CS320_Team_Fractal_Website.controller;

import edu.ycp.cs320.CS320_Team_Fractal_Website.model.Numbers;

public class NumbersController 
{	
	private Numbers model;
	
	public NumbersController(Numbers model)
	{
		this.model = model;
	}
	
	public Double add(Double first, Double second, Double third)
	{
		model.setFirst(first);
		model.setSecond(second);
		model.setThird(third);
		return model.getAdd();
	}
	
	public Double mult(Double first, Double second)
	{
		model.setFirst(first);
		model.setSecond(second);
		return model.getMult();

	}
}
