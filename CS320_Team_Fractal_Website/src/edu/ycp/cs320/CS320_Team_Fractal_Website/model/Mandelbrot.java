package edu.ycp.cs320.CS320_Team_Fractal_Website.model;

public class Mandelbrot extends Fractal{
	
	/**
	 * The number of times a complex number is multiplied during each Mandelbrot calculation
	 */
	private int multiplyTimes;
	
	public Mandelbrot(){
		super();
	}
	
	public int getMultiplyTimes() {
		return multiplyTimes;
	}
	public void setMultiplyTimes(int multiplyTimes) {
		this.multiplyTimes = multiplyTimes;
	}
	
	@Override
	public void setDefaultParameters(){
		multiplyTimes = 1;
	}
}
