package edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal;

/**
 * An object that keeps track of the location of a portion of a fractal 
 * via the upper left-hand corner of the fractal and the lower right-hand 
 * corner of the fractal
 */
public class Location{
	
	/**
	 * The upper left-hand x coordinate
	 */
	private double x1;
	/**
	 * The upper left-hand y coordinate
	 */
	private double y1;
	/**
	 * The lower right-hand x coordinate
	 */
	private double x2;
	/**
	 * The lower right-hand y coordinate
	 */
	private double y2;
	
	/**
	 * Set the location to a default of upper left-hand = (-1, 1) and lower right-hand = (1, -1)
	 */
	public Location(){
		this(-1, 1, 1, -1);
	}

	/**
	 * Set the location to upper left-hand = (x1, y1) and lower right-hand = (x2, y2)
	 */
	public Location(double x1, double y1, double x2, double y2){
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
	}
	
	public double getX1() {
		return x1;
	}
	public void setX1(double x1) {
		this.x1 = x1;
	}
	public double getY1() {
		return y1;
	}
	public void setY1(double y1) {
		this.y1 = y1;
	}
	public double getX2() {
		return x2;
	}
	public void setX2(double x2) {
		this.x2 = x2;
	}
	public double getY2() {
		return y2;
	}
	public void setY2(double y2) {
		this.y2 = y2;
	}
	
}
