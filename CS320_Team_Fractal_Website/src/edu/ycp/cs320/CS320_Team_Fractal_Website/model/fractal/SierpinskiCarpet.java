package edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal;

import java.awt.geom.Point2D;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.FractalController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.SierpinskiCarpetController;

public class SierpinskiCarpet extends Fractal{
	
	public static final int SIZE = 800;
	
	/**
	 * The number of levels of vicsek will be rendered
	 */
	private int level;
	
	/**
	 * A point on the square
	 */
	private Point2D.Double p1, p2, p3, p4;
	
	public SierpinskiCarpet(int level){
		super();
		this.level = level;
	}
	public SierpinskiCarpet(){
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
		
		//4 points of square in clockwise direction
		this.p1 = new Point2D.Double(0, 0);
		this.p2 = new Point2D.Double(SIZE, 0);
		this.p3 = new Point2D.Double(SIZE, SIZE);
		this.p4 = new Point2D.Double(0, SIZE);
	}
	
	@Override
	public String getInfo(){
		return "The Sierpinski Carpet is a plane fractal first described by Waclaw Sierpinski in 1916. "
				+ "It is a fractal with the overall shape of a square, subdivided recursively"
				+ "into 8 smaller squares, excluding the center square. ";
	}
	
	@Override
	public String getParamExamples()
	{
		return "<ul><li>Change the level of the render to get more detailed sierpinski blanket fractal.</li>"
				+ "<ul><li>Keep level under 7 to preserve detail and efficiency.</li></ul>"
				+ "<li>Changing the X, Y values of the points (P1, P2, P3, and P4) will distort the square.</li>";
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
		SierpinskiCarpetController controller = new SierpinskiCarpetController();
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
	public boolean getUsesLocation() {
		return false;
	}
	
	public int getLevel(){
		return level;
	}
	public void setLevel(int levelParam){
		this.level = levelParam;
		if(this.level < 1) this.level = 1;
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
}
