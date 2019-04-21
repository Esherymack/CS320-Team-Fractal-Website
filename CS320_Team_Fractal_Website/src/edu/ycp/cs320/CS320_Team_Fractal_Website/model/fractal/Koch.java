package edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.FractalController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.KochController;

public class Koch extends Fractal{
	
	private int iterations;
	
	public Koch(int iterations){
		super();
		this.iterations = iterations;
	}
	public Koch(){
		this(0);
	}
	
	@Override
	public void setDefaultParameters() {
		super.setDefaultParameters();
		iterations = 1;
	}
	
	@Override
	public String getInfo(){
		return	"The Koch set, often called the snowflake fractal, takes a line, splits it into 3 " +
				"segments, and creates a trianlge on the new line. Each of those lines has the same " +
				"process done to it fdor each iteration.";
	}

	@Override
	public String[] getParameters(){
		return new String[]{
				iterations + "",
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
				"Iterations: ",
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
		KochController controller = new KochController();
		controller.setModel(this);
		return controller;
	}
	
	/**
	 * Get the iteration count of this fractal
	 * @return the iteration count
	 */
	public int getIterations(){
		return iterations;
	}
	/**
	 * Set the iteration count of this fractal
	 * @param iterations the new iteration count
	 */
	public void setIterations(int iterations){
		this.iterations = iterations;
	}

}
