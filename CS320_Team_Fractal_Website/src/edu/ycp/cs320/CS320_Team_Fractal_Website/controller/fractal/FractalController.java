package edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import edu.ycp.cs320.CS320_Team_Fractal_Website.database.DatabaseProvider;
import edu.ycp.cs320.CS320_Team_Fractal_Website.database.IDatabase;
import edu.ycp.cs320.CS320_Team_Fractal_Website.database.InitDatabase;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Fractal;

/**
 * A generic controller for a fractal controller
 */
public abstract class FractalController{

	private IDatabase database;
	
	public FractalController(){
		//get database for logging in
		InitDatabase.init();
		database = DatabaseProvider.getInstance();
	}
	
	/**
	 * Get the model of this fractal
	 * @return
	 */
	public abstract Fractal getModel();
	
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
	 * Saves the fractal of the model to the account that is logged in
	 * @param username the username of the account that this fractal should be saved under
	 * @return true if the fractal was saved successfully, false otherwise
	 */
	public boolean saveImage(String name, String username){
		return database.saveFractal(getModel(), name, username);
	}
	
	/**
	 * Takes an array of Strings and converts them to appropriate values to enter for the fractal. 
	 * The values from the array are stored in the model of this FractalController
	 * @param params the array of strings
	 * @return true if the parameters were added successfully, false otherwise
	 */
	public abstract boolean acceptParameters(String[] params);
}
