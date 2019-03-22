package edu.ycp.cs320.CS320_Team_Fractal_Website.controller;

/**
 * A generic controller for a fractal controller
 */
public abstract class FractalController{
	
	/**
	 * Renders the current state of the given fractal to a BufferedImage and sets that image to the stored location
	 * @return true if the fractal was rendered, false otherwise
	 */
	public abstract boolean render();
	
}
