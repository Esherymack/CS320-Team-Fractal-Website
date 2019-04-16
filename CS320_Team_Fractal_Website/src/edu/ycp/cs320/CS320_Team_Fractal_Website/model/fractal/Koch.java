package edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.FractalController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.KochController;

public class Koch extends Fractal{
	
	private int iterations;
	
	public Koch(){
		super();
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
	
	@Override
	public void setDefaultParameters() {
		super.setDefaultParameters();
		iterations = 1;
	}
	
	@Override
	public String getInfo(){
		return "This is the Koch set (add a real description here)";
	}

	@Override
	public String[] getParameters(){
		return new String[]{
				iterations + "",
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null,
				null
		};
	}

	@Override
	public FractalController createApproprateController(){
		KochController controller = new KochController();
		controller.setModel(this);
		return controller;
	}

}
