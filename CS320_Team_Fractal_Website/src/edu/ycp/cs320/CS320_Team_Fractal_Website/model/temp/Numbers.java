package edu.ycp.cs320.CS320_Team_Fractal_Website.model.temp;

public class Numbers
{
	private Double first, second, third;
	
	public Numbers() 
	{}
	
	public void setFirst(Double firstnum)
	{
		this.first = firstnum;
	}
	
	public void setSecond(Double secondnum)
	{
		this.second = secondnum;
	}
	
	public void setThird(Double thirdnum)
	{
		this.third = thirdnum;
	}
	
	public Double getFirst()
	{
		return first;
	}
	
	public Double getSecond()
	{
		return second;
	}
	
	public Double getThird()
	{
		return third;
	}
	
	public Double getAdd()
	{
		return first + second + third;
	}
	
	public Double getMult()
	{
		return first * second;
	}
}