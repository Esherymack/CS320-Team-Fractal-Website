package edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.FractalController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.BakedKuribohController;

public class BakedKuriboh extends Fractal{
	
	/**
	 * The number of times a complex number is multiplied during each Mandelbrot calculation
	 */
	private int multiplyTimes;

	public BakedKuriboh(int multiplyTimes){
		super();
		this.multiplyTimes = multiplyTimes;
	}
	public BakedKuriboh(){
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
		return "Based on the mandelbrot set. "
				+ "Adds another term to equation of mandelbrot, where z = z^2 + z + c. "
				+ "Summoned by Dakota Hilbert.";
	}
	
	@Override
	public String getParamExamples()
	{
		return "<ul><li>Change X, Y values to zoom in and out on different regions.</li>"
				+ "<li>Change multiplier to change the overall shape of the baked kuriboh.</li>"
				+ "<li>Example coordinates:</li>"
				+ "<ul><li>X1: -0.978656; Y1: -0.214731; X2: -0.965531; Y2: -0.201606; M=1</li>"
				+ "<li>X1: -1.636518; Y1: 0.008475; X2: -1.608; Y2: 0.036994; M=1</li>"
				+ "<li>X1: -0.513532; Y1: -0.730888; X2: -0.370916; Y2: -0.588272; M=1</li>";
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
		BakedKuribohController controller = new BakedKuribohController();
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
