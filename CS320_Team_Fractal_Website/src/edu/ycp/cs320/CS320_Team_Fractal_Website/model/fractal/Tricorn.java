package edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.FractalController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.TricornController;

public class Tricorn extends Fractal{
	
	/**
	 * The number of times a complex number is multiplied during each Mandelbrot calculation
	 */
	private int multiplyTimes;

	public Tricorn(int multiplyTimes){
		super();
		this.multiplyTimes = multiplyTimes;
	}
	public Tricorn(){
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
		return "The Tricorn, often times called the Mandelbar set, is a fractal similar to the mandelbrot set. "
				+ "The Tricorn uses the complex conjugate of the complex number in the equation, whereas the Mandelbrot only uses the complex number. "
				+ "The fractal was introduced by W. D. Crowe, R. Hasson, P. J. Rippon, and P. E. D. Strain-Clark.";
	}
	
	@Override
	public String getParamExamples()
	{
		return "<ul><li>Change X, Y values to zoom in and out on different regions.</li>"
				+ "<li>Change multiplier to change the overall shape of the tricorn.</li>"
				+ "<li>Example coordinates:</li>"
				+ "<ul><li>X1: -1.5; Y1: -0.11; X2: -1.3; Y2: 0.09; M=1</li>"
				+ "<li>X1: -1.27; Y1: -0.25; X2: -1.165; Y2: -0.145; M=1</li>"
				+ "<li>X1: 0.175; Y1: -0.675; X2: 0.285; Y2: -0.515; M=1</li>";
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
		TricornController controller = new TricornController();
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
