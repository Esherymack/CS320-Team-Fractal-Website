package edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.FractalController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.RocketShipController;

public class RocketShip extends Fractal{
	
	/**
	 * The number of times a complex number is multiplied during each Mandelbrot calculation
	 */
	private int multiplyTimes;

	public RocketShip(int multiplyTimes){
		super();
		this.multiplyTimes = multiplyTimes;
	}
	public RocketShip(){
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
		return "Its a rocket ship... "
				+ "Based on the mandelbrot set. "
				+ "Adds two more terms to equation of mandelbrot, where z = z^4 + z^3 + z^2 + z + c. ";
	}
	
	@Override
	public String getParamExamples()
	{
		return "<ul><li>Change X, Y values to zoom in and out on different regions.</li>"
				+ "<li>Change multiplier to change the overall shape of the mandelbrot.</li>"
				+ "<li>Example coordinates:</li>"
				+ "<ul><li>X1: -1.145; Y1: -0.415; X2: -0.4; Y2: 0.313; M=1</li>"
				+ "<li>X1: -0.744916; Y1: -0.158718; X2: -0.692459; Y2: -0.106260; M=1</li>"
				+ "<li>X1: 0.0985; Y1: -0.52125; X2: 0.125875; Y2: -0.493875; M=1</li>";
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
			"",
			"",
			"",
			"",
			"",
			"",
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
				"",
				"",
				"",
				"",
				"",
				"",
				"",
				"",
				"",
				"",
				""
		};
	}
	
	@Override
	public FractalController createApproprateController(){
		RocketShipController controller = new RocketShipController();
		controller.setModel(this);
		return controller;
	}

	@Override
	public boolean getUsesLocation() {
		return true;
	}
	
	public int getMultiplyTimes() {
		return multiplyTimes;
	}
	public void setMultiplyTimes(int multiplyTimes) {
		this.multiplyTimes = multiplyTimes;
	}
}
