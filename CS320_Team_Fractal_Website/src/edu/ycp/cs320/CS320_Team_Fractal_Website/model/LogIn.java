package edu.ycp.cs320.CS320_Team_Fractal_Website.model;

public class LogIn {
private Double first, second;
	
	public LogIn() 
	{}
	
	public void setFirst(Double firstnum)
	{
		this.first = firstnum;
	}
	
	public void setSecond(Double secondnum)
	{
		this.second = secondnum;
	}
	
	public Double getFirst()
	{
		return first;
	}
	
	public Double getSecond()
	{
		return second;
	}
	
	public Double getAdd()
	{
		return first + second;
	}
}
