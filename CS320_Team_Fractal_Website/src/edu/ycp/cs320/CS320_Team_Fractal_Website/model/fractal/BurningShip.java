package edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.FractalController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.BurningShipController;

public class BurningShip extends Fractal{
	
	/**
	 * The number of times a complex number is multiplied during each Mandelbrot calculation
	 */
	private int multiplyTimes;

	public BurningShip(int multiplyTimes){
		super();
		this.multiplyTimes = multiplyTimes;
	}
	public BurningShip(){
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
		return "The Burning Ship fractal was first described and created by Michael Michelitsch and Otto E. Rössler in 1992. "
				+ "The Fractal is very similar to the Mandelbrot set, where the difference is that the absolute values of the real and "
				+ "imaginary values are calculated in the equation.";
	}
	
	@Override
	public String getParamExamples()
	{
		return "<ul><li>Change X, Y values to zoom in and out on different regions.</li>"
				+ "<li>Change multiplier to change the overall shape of the burning ship fractal.</li>"
				+ "<li>Example coordinates:</li>"
				+ "<ul><li>X1: -0.86; Y1: -1.05; X2: -0.64; Y2: -0.83; M=1</li>"
				+ "<li>X1: -1.865737; Y1: -0.009650; X2: -1.853388; Y2: 0.002699; M=1</li>"
				+ "<li>X1: -1.59884728; Y1: -0.04390816; X2: -1.55268807; Y2: 0.00225103; M=1</li>";
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
		BurningShipController controller = new BurningShipController();
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
