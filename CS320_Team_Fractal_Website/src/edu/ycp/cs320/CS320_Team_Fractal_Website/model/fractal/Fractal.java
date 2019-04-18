package edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.FractalController;

/**
 * The abstract class that represents a fractal 
 * This class acts as a model that all other fractals will extend off of
 */
public abstract class Fractal{
	
	/**
	 * The current location of the fractal
	 */
	private Location location;
	
	/**
	 * The name of the fractal
	 */
	private String name;
	
	/**
	 * The id of the fractal, only used for identification when loaded from a database
	 */
	private int id;
	
	public Fractal(){
		this(new Location());
	}
	
	public Fractal(Location loc){
		this.location = loc;
		setDefaultParameters();
		name = "default";
		id = 0;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
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
	
	
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
	}
	
	/**
	 * Get a description of this fractal.
	 * @return the description
	 */
	public abstract String getInfo();
	
	/**
	 * Get an array of all the parameters for this fractal. 
	 * Should be the same length as MainPageServlet.NUM_PARAMS
	 * @return
	 */
	public abstract String[] getParameters();
	
	/**
	 * Get the type of the fractal used for storing in the database
	 * @return the type of this fractal
	 */
	public String getType(){
		return getClass().getSimpleName();
	}
	
	/**
	 * Make a controller that is appropriate for this fractal and set the model of that controller to this fractal
	 * @return the controller
	 */
	public abstract FractalController createApproprateController();
	/**
	 * Get a default fractal based on the given type, which is the class name of a fractal. This method must 
	 * be updated each time a new fractal is added, otherwise all code that relies on this method will
	 * only work for previously added fractal types.
	 * @param type the type of the fractal, the class name, it Mandelbrot set is "Mandelbrot"
	 * @return a default fractal of the given type, null if an invalid type is given
	 */
	public static Fractal getDefaultFractal(String type){
		if(type.equals("Mandelbrot")) return new Mandelbrot();
		else if(type.equals("Sierpinski")) return new Sierpinski();
		else if(type.equals("Koch")) return new Koch();
		else if(type.equals("Barnsley")) return new Barnsley();
		return null;
	}
	
}
