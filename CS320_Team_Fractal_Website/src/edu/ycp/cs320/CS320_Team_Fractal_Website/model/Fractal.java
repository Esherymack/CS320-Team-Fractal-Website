package edu.ycp.cs320.CS320_Team_Fractal_Website.model;

/**
 * The abstract class that represents a fractal 
 * This class acts as a model that all other fractals will extend off of
 */
public abstract class Fractal{
	
	/**
	 * The current location of the fractal
	 */
	private Location location;
	
	public Fractal(){
		this(new Location());
	}
	
	public Fractal(Location loc){
		this.location = loc;
		setDefaultParameters();
	}
	
	/**
	 * Sets the parameters of this Fractal to a valid and default state
	 */
	public abstract void setDefaultParameters();
	
	public Location getLocation(){
		return location;
	}
	
	public void setLocation(Location l){
		this.location = l;
	}
	
}
