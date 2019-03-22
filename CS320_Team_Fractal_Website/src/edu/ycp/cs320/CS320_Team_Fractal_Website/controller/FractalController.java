package edu.ycp.cs320.CS320_Team_Fractal_Website.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

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
		OutputStream os = null;
		try{
			os = new BufferedOutputStream(new FileOutputStream("./war/img/result.png"));
			ImageIO.write(img, "PNG", os);
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}
		if(os != null){
			try{
				os.close();
			}catch (IOException e){
				e.printStackTrace();
			}
		}
	}
	
}
