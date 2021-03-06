package edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal;

import java.awt.geom.Point2D;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.DragonCurveController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.FractalController;

public class DragonCurve extends Fractal{
	
	/**
	 * The number of lines the fractal will be
	 */
	private int iterations;
	/**
	 * How zoomed in or out the fractal is
	 */
	private double scalar;
	/**
	 * The distance from the center relative to the center of the image
	 */
	private Point2D.Double center;
	
	public DragonCurve(){
		this(100000, 1, 0, 0);
	}
	public DragonCurve(int iterations, double scalar, double centerX, double centerY){
		super();
		this.iterations = iterations;
		this.scalar = scalar;
		this.center = new Point2D.Double(centerX, centerY);
	}
	
	@Override
	public void setDefaultParameters(){
		super.setDefaultParameters();
		this.iterations = 100000;
		this.scalar = 1;
		this.center = new Point2D.Double(0, 0);
	}
	
	public int getIterations(){
		return iterations;
	}
	public void setIterations(int iterations){
		this.iterations = iterations;
	}
	
	public double getScalar(){
		return scalar;
	}
	public void setScalar(double scalar){
		this.scalar = scalar;
	}
	
	public Point2D.Double getCenter(){
		return center;
	}
	public void setCenter(Point2D.Double center){
		this.center = center;
	}
	public void setCenter(double x, double y){
		this.setCenter(new Point2D.Double(x, y));
	}
	
	@Override
	public String getInfo(){
		return "The Dragon Curve is a fractal generated by simulating folding "
				+ "paper on infinite amount of times and drawing a line based "
				+ "on the direction of the resulting folds.";
	}

	@Override
	public String getParamExamples(){
		return "<ul>"
				+ "<li>Increase or decrease scalar to zoom in or out</li>"
				+ "<li>Change iterations to draw more or less of the fractal</li>"
				+ "<li>Change x and y to change the center position of the fractal</li>"
				+ "</ul>";
	}

	@Override
	public String[] getParameters(){
		return new String[]{
				iterations + "",
				scalar + "",
				center.x + "",
				center.y + "",
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
		};
	}

	@Override
	public FractalController createApproprateController(){
		DragonCurveController controller = new DragonCurveController();
		controller.setModel(this);
		return controller;
	}

	@Override
	public String[] getParamLabels(){
		return new String[]{
				"Iterations: ",
				"Scalar: ",
				"X: ",
				"Y: ",
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
		};
	}

	@Override
	public boolean getUsesLocation(){
		return false;
	}

}
