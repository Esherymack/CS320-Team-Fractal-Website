package edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal;

import java.awt.geom.Point2D;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.FractalController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.VicsekController;

public class Vicsek extends Fractal{
	
	public static final int SIZE = 800;
	
	/**
	 * The number of levels of vicsek will be rendered
	 */
	private int level;
	
	/**
	 * The orientation of vicsek
	 */
	private int orientation;
	/**
	 * A point on the square
	 */
	private Point2D.Double p1, p2, p3, p4;
	
	public Vicsek(int level){
		super();
		this.level = level;
	}
	public Vicsek(){
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
		orientation = 0;
		
		//4 points of square in clockwise direction
		this.p1 = new Point2D.Double(0, 0);
		this.p2 = new Point2D.Double(SIZE, 0);
		this.p3 = new Point2D.Double(SIZE, SIZE);
		this.p4 = new Point2D.Double(0, SIZE);
	}
	
	@Override
	public String getInfo(){
		return "Also known as the Vicsek snowflake or box fractal, the Vicsek fractal is a "
				+ "fractal with the overall shape of a square, subdivided recursively"
				+ "into smaller squares in different directions depending on the orientation. ";
	}
	
	@Override
	public String getParamExamples()
	{
		return "<ul><li>Change the level of the render to get more detailed vicsek fractals.</li>"
				+ "<ul><li>Keep level under 8 to preserve detail and efficiency.</li></ul>"
				+ "<ul><li>Set the orientation to 0 to have the fractal extend in plus shape. </li></ul>"
				+ "<ul><li>Set the orientation to 1 to have the fractal extend in x shape. </li></ul>"
				+ "<li>Changing the X, Y values of the points (P1, P2, P3, and P4) will distort the square.</li>";
	}

	@Override
	public String[] getParameters(){
		return new String[]{
			"" + getLevel(),
			"" + getOrientation(),
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
			""
		};
	}

	@Override
	public FractalController createApproprateController(){
		VicsekController controller = new VicsekController();
		controller.setModel(this);
		return controller;
	}

	@Override
	public String[] getParamLabels(){
		return new String[]{
				"Level: ",
				"Orientation: ",
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
	
	public int getOrientation(){
		return orientation;
	}
	public void setOrientation(int orientationParam){
		this.orientation = orientationParam;
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
