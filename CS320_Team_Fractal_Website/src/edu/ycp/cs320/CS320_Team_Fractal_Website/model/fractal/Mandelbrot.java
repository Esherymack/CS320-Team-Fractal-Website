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
	public String getParamExamples()
	{
		return "<ul><li>Change X, Y values to zoom in and out on different regions.</li>"
				+ "<li>Change multiplier to change the overall shape of the mandelbrot.</li>"
				+ "<li>Example coordinates:</li>"
				+ "<ul><li>X1: -1.3595507; Y1: 0.0652734; X2: -1.35213867; Y2: 0.07268554; M=1</li>"
				+ "<li>X1: 0.298271942; Y1: 0.0184661865; X2: 0.305683593; Y2: 0.025877838; M=1</li>"
				+ "<li>X1: -1.3180998166; Y1: 0.0667306582; X2: -1.3096934159; Y2: 0.07513705889; M=1</li>";
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
