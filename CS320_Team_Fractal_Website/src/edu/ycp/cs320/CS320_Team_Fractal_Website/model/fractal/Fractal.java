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

	/**
	 * The gradient that this fractal uses when drawn
	 */
	private Gradient gradient;

	/**
	 * The gradient type that this fractal uses when drawn
	 */
	private String gradientType;
	
	public Fractal(){
		this(new Location());
		setDefaultParameters();
	}
	public Fractal(Location loc){
		this.location = loc;
		name = "default";
		id = 0;

		gradient = new Gradient();
		gradientType = "None";
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id = id;
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
	
	public Gradient getGradient(){
		return gradient;
	}
	public void setGradient(Gradient gradient){
		this.gradient = gradient;
	}
	
	public String getGradientType(){
		return gradientType;
	}
	public void setGradientType(String gradientType){
		this.gradientType = gradientType;
	}
	/**
	 * Find out if this fractal should render with a gradient or not
	 * @return true if it should render without a gradient, false otherwise
	 */
	public boolean noGradient(){
		return gradientType == null || gradientType.equals(Gradient.NONE) || gradientType.isEmpty();
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
	
	/**
	 * Get a list of all the labels for the parameters used for display. 
	 * The list length should = MainPageServlet.NUM_PARAMS. 
	 * Use an empty string for a parameter that shouldn't be displayed
	 * @return the label list
	 */
	public abstract String[] getParamLabels();
	
	public static Fractal getDefaultFractal(String type){
		if(type.equals("Mandelbrot")) return new Mandelbrot();
		else if(type.equals("Sierpinski")) return new Sierpinski();
		else if(type.equals("Koch")) return new Koch();
		else if(type.equals("Barnsley")) return new Barnsley();
		else if(type.equals("Julia")) return new Julia();
		return null;
	}
	
	/**
	 * Get a list of strings containing the type of every avaliable fractal
	 * @return the list of types
	 */
	public static String[] getAllFractalTypes(){
		return new String[]{
			"Mandelbrot",
			"Sierpinski",
			"Koch",
			"Barnsley",
			"Julia"
		};
	}
	
}
