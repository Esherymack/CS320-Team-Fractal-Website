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
	public void setDefaultParameters(){
		this.location = getDefaultLocation();
	}
	
	/**
	 * Get the default location of this fractal. 
	 * Override this method if something other than the default values of Location() should be the default location of this fractal
	 * @return
	 */
	public Location getDefaultLocation(){
		return new Location();
	}
	
	public Location getLocation(){
		return location;
	}
	
	public void setLocation(Location l){
		this.location = l;
	}
	
}
