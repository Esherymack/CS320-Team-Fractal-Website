package edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal;

import java.awt.geom.Point2D;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.FractalController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.SierpinskiController;

public class Sierpinski extends Fractal{
	
	public static final int SIZE = 800;
	
	/**
	 * The number of levels this triangle will be rendered
	 */
	private int level;
	/**
	 * The height of this triangle
	 */
	private double height;
	/**
	 * A point on the triangle
	 */
	private Point2D.Double p1, p2, p3;
	
	public Sierpinski(int level){
		super();
		this.level = level;
	}
	public Sierpinski(){
		this(1);
	}
	
	@Override
	public Location getDefaultLocation(){
		return new Location(0, 0, SIZE, SIZE);
	}
	
	@Override
	public void setDefaultParameters(){
		super.setDefaultParameters();
		
		level = 2;
		height = Math.round(SIZE * Math.sqrt(3.0) / 2.0);

		this.p1 = new Point2D.Double(0, height);
		this.p2 = new Point2D.Double(SIZE / 2, 0);
		this.p3 = new Point2D.Double(SIZE, height);
	}
	
	@Override
	public String getInfo(){
		return "The Sierpinski triangle, also called the Sierpinski gasket or the Sierpinski sieve, is a "
				+ "fractal and attractive fixed set with the overall shape of an equilateral triangle, subdivided "
				+ "recursively into smaller equilateral triangles. ";
	}
	
	@Override
	public String getParamExamples()
	{
		return "<ul><li>Change the level of the render to get more detailed sierpinski triangles.</li>"
				+ "<ul><li>Keep level under 10 to preserve detail and efficiency.</li></ul>"
				+ "<li>Changing the X, Y values of the points (P1, P2, and P3) will distort the triangle.</li>";
	}

	@Override
	public String[] getParameters(){
		return new String[]{
			"" + getLevel(),
			"" + getP1().x,
			"" + getP1().y,
			"" + getP2().x,
			"" + getP2().y,
			"" + getP3().x,
			"" + getP3().y,
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
		SierpinskiController controller = new SierpinskiController();
		controller.setModel(this);
		return controller;
	}

	@Override
	public String[] getParamLabels(){
		return new String[]{
				"Level: ",
				"P1x: ",
				"P1y: ",
				"P2x: ",
				"P2y: ",
				"P3x: ",
				"P3y: ",
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
	public boolean usesLocation() {
		return false;
	}
	
	public int getLevel(){
		return level;
	}
	public void setLevel(int levelParam){
		this.level = levelParam;
		if(this.level < 1) this.level = 1;
	}

	public double getHeight(){
		return this.height;
	}
	public void setHeight(double height){
		this.height = height;
	}
	
	public Point2D.Double getP1(){
		return p1;
	}
	public Point2D.Double getP2(){
		return p2;
	}
	public Point2D.Double getP3(){
		return p3;
	}
	
	public void setP1(Point2D.Double p){
		this.p1 = p;
	}
	public void setP2(Point2D.Double p){
		this.p2 = p;
	}
	public void setP3(Point2D.Double p){
		this.p3 = p;
	}
}
