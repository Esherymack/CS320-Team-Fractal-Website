package edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * A generic controller for a fractal controller
 */
public abstract class FractalController{
	
	/**
	 * Renders the current state of the given fractal to a BufferedImage and sets that image to the stored location
	 * @return true if the fractal was rendered, false otherwise
	 */
	public abstract boolean render();
	
	/**
	 * Sends the given image to be saved in the database
	 * @param img
	 */
	public void sendImage(BufferedImage img){
		try{
			ImageIO.write(img, "PNG", new File("./war/img/result.png"));
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Takes an array of Strings and converts them to appropriate values to enter for the fractal. 
	 * The values from the array are stored in the model of this FractalController
	 * @param params the array of strings
	 * @return true if the parameteres were added sucessfuly, false otherwise
	 */
	public abstract boolean acceptParameters(String[] params);
}
