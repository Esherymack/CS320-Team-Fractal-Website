package edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.FractalController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.UnnamedController;

public class Unnamed extends Fractal{
	
	/**
	 * The number of times a complex number is multiplied during each Mandelbrot calculation
	 */
	private int multiplyTimes;

	public Unnamed(int multiplyTimes){
		super();
		this.multiplyTimes = multiplyTimes;
	}
	public Unnamed(){
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
		return "Insert info here. "
				+ " "
				+ "Discovered by Dakota Hilbert. ";
	}
	
	@Override
	public String getParamExamples()
	{
		return "<ul><li>Change X, Y values to zoom in and out on different regions.</li>"
				+ "<li>Change multiplier to change the overall shape of the unnamed.</li>"
				+ "<li>Example coordinates:</li>"
				+ "<ul><li>X1: ; Y1: ; X2: ; Y2: ; M=1</li>"
				+ "<li>X1: ; Y1: ; X2: ; Y2: ; M=1</li>"
				+ "<li>X1: ; Y1: ; X2: ; Y2: ; M=1</li>";
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
		UnnamedController controller = new UnnamedController();
		controller.setModel(this);
		return controller;
	}

	@Override
	public boolean usesLocation() {
		return true;
	}
	
	public int getMultiplyTimes() {
		return multiplyTimes;
	}
	public void setMultiplyTimes(int multiplyTimes) {
		this.multiplyTimes = multiplyTimes;
	}
}
