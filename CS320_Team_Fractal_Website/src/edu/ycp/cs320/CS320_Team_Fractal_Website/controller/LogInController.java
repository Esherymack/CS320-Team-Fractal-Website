package edu.ycp.cs320.CS320_Team_Fractal_Website.controller;

import edu.ycp.cs320.CS320_Team_Fractal_Website.model.LogIn;

public class LogInController {
	private LogIn model;
	
	public LogInController(LogIn model)
	{
		this.model = model;
	}
	
	public Double add(Double first, Double second)
	{
		model.setFirst(first);
		model.setSecond(second);
		return model.getAdd();
	}
}
