package edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal;

import java.awt.geom.Point2D;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.FractalController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.HexaflakeController;

public class Hexaflake extends Fractal{
	
	public static final int SIZE = 800;
	
	/**
	 * The number of levels this fractal will be rendered
	 */
	private int level;
	/**
	 * The height of this fractal
	 */
	private double height;
	/**
	 * A point of the hexagon
	 */
	private Point2D.Double p1, p2, p3, p4, p5, p6;
	
	public Hexaflake(int level){
		super();
		this.level = level;
	}
	public Hexaflake(){
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
		int x_dist = (int) (SIZE / 2 * Math.sin(Math.PI / 3));
		int y_dist = (int) (SIZE / 2 * Math.cos(Math.PI / 3));
		
		//top middle point
		this.p1 = new Point2D.Double(SIZE / 2, 0);
		//upper right point
		this.p2 = new Point2D.Double(SIZE / 2 + x_dist, SIZE / 2 - y_dist);
		//lower right point
		this.p3 = new Point2D.Double(SIZE / 2 + x_dist, SIZE / 2 + y_dist);
		//bottom middle point
		this.p4 = new Point2D.Double(SIZE / 2, SIZE);
		//lower left point
		this.p5 = new Point2D.Double(SIZE / 2 - x_dist, SIZE / 2 + y_dist);
		//upper left point
		this.p6 = new Point2D.Double(SIZE / 2 - x_dist, SIZE / 2 - y_dist);
	}
	
	@Override
	public String getInfo(){
		return "The Hexaflake fractal, often refered to as a snowflake, is a set of hexagons drawn in one another. "
				+ "Each hexagon gets 7 evenly spaced hexagons in one another, creating a snowflake like shape.";
	}
	
	@Override
	public String getParamExamples()
	{
		return "<ul><li>Change the level of the render to get more detailed hexaflakes.</li>"
				+ "<ul><li>Keep level under 9 to preserve efficiency.</li></ul>"
				+ "<li>Changing the X, Y values of the points (P1 - P6) will distort the fractal.</li>";
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
			"" + getP4().x,
			"" + getP4().y,
			"" + getP5().x,
			"" + getP5().y,
			"" + getP6().x,
			"" + getP6().y,
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
		HexaflakeController controller = new HexaflakeController();
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
				"P4x: ",
				"P4y: ",
				"P5x: ",
				"P5y: ",
				"P6x: ",
				"P6y: ",
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
	public Point2D.Double getP4(){
		return p4;
	}
	public Point2D.Double getP5(){
		return p5;
	}
	public Point2D.Double getP6(){
		return p6;
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
	public void setP4(Point2D.Double p){
		this.p4 = p;
	}
	public void setP5(Point2D.Double p){
		this.p5 = p;
	}
	public void setP6(Point2D.Double p){
		this.p6 = p;
	}
}
