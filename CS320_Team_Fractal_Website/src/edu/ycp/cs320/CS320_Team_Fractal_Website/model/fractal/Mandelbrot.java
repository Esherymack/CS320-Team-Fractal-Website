package edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.FractalController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.MandelbrotController;

public class Mandelbrot extends Fractal{
	
	/**
	 * The number of times a complex number is multiplied during each Mandelbrot calculation
	 */
	private int multiplyTimes;

	public Mandelbrot(int multiplyTimes){
		super();
		this.multiplyTimes = multiplyTimes;
	}
	public Mandelbrot(){
		this(0);
	}

	@Override
	public void setDefaultParameters(){
		super.setDefaultParameters();
		multiplyTimes = 1;
	}
	
	@Override
	public Location getDefaultLocation() {
		return new Location(-2, -2, 2, 2);
	}
	
	@Override
	public String getInfo(){
		return "The Mandelbrot set is the set of complex numbers for which the function does not diverge "
				+ "when iterated from, i.e., for which the sequence, etc., remains bounded in absolute value. Its "
				+ "definition and name are due to Adrien Douady, in tribute to the mathematician Benoit Mandelbrot.";
	}

	@Override
	public String[] getParameters(){
		return new String[]{
			"" + getLocation().getX1(),
			"" + getLocation().getY1(),
			"" + getLocation().getX2(),
			"" + getLocation().getY2(),
			"" + getMultiplyTimes(),
			"",
			"",
			"",
			"",
			""
		};
	}

	@Override
	public String[] getParamLabels(){
		return new String[]{
				"X1: ",
				"Y1: ",
				"X2: ",
				"Y2: ",
				"Multiplier: ",
				"",
				"",
				"",
				"",
				""
		};
	}
	
	@Override
	public FractalController createApproprateController(){
		MandelbrotController controller = new MandelbrotController();
		controller.setModel(this);
		return controller;
	}
	
	public int getMultiplyTimes() {
		return multiplyTimes;
	}
	public void setMultiplyTimes(int multiplyTimes) {
		this.multiplyTimes = multiplyTimes;
	}
}
