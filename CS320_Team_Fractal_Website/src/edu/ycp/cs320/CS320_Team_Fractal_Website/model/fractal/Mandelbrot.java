package edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal;

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
		super.setDefaultParameters();
		multiplyTimes = 1;
	}
	
	@Override
	public String getInfo(){
		return "The Mandelbrot set is the set of complex numbers for which the function does not diverge "
				+ "when iterated from, i.e., for which the sequence, etc., remains bounded in absolute value. Its "
				+ "definition and name are due to Adrien Douady, in tribute to the mathematician Benoit Mandelbrot.";
	}
}
