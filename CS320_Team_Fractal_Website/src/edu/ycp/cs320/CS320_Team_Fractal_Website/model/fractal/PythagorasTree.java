package edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.FractalController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.PythagorasTreeController;

public class PythagorasTree extends Fractal{
	
	private int iterations;
	private double initialSize;
	/**
	 * The angle that this pythagoras tree goes at, in degrees
	 */
	private double angle;

	public PythagorasTree(int iterations, double initialSize, double angle){
		super();
		this.iterations = iterations;
		this.initialSize = initialSize;
		this.angle = angle;
	}
	public PythagorasTree(){
		this(13, 130, 45);
	}
	
	public int getIterations(){
		return iterations;
	}
	public void setIterations(int iterations){
		this.iterations = iterations;
	}
	
	public double getInitialSize(){
		return initialSize;
	}
	public void setInitialSize(double initialSize){
		this.initialSize = initialSize;
	}
	public double getAngle(){
		return angle;
	}
	public void setAngle(double angle){
		this.angle = angle;
	}
	
	@Override
	public void setDefaultParameters(){
		super.setDefaultParameters();
		this.iterations = 13;
		this.initialSize = 130;
		this.angle = 45;
	}
	
	@Override
	public String getInfo(){
		return "The Pythagoras Tree is generated by begining with a square and placing two more "
				+ "squares at an angle so that they form an isosceles triangle. This process is continued "
				+ "on every square, up to some limit, the iteration count.";
	}

	@Override
	public String getParamExamples(){
		return "<li>The angle is in degrees</li>"
				+ "<li>Change the angle to make the tree wider or thiner</li>"
				+ "<li>Change the size to make the larger or smaller</li>"
				+ "<li>Change the iteration count to make more of the tree</li>"
				+ "<li>Don't let iterations get too big, otherwise the fractal can take some time to render</li>";
	}

	@Override
	public String[] getParameters(){
		return new String[]{
				iterations + "",
				initialSize + "",
				angle + "",
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
				"",
				"",
				""
		};
	}

	@Override
	public FractalController createApproprateController(){
		PythagorasTreeController control = new PythagorasTreeController();
		control.setModel(this);
		return control;
	}

	@Override
	public String[] getParamLabels(){
		return new String[]{
				"Iterations: ",
				"Size: ",
				"Angle: ",
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
				"",
				"",
				""
		};
	}

	@Override
	public boolean getUsesLocation(){
		return false;
	}

}
