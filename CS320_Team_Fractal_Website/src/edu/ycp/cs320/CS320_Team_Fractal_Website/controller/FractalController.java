package edu.ycp.cs320.CS320_Team_Fractal_Website.controller;

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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
