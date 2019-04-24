package edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import edu.ycp.cs320.CS320_Team_Fractal_Website.database.DatabaseProvider;
import edu.ycp.cs320.CS320_Team_Fractal_Website.database.IDatabase;
import edu.ycp.cs320.CS320_Team_Fractal_Website.database.InitDatabase;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Fractal;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Gradient;

/**
 * A generic controller for a fractal controller
 */
public abstract class FractalController{
	
	/**
	 * The database this controller uses to save a fractal
	 */
	private IDatabase database;
	
	/**
	 * The gradient that this controller uses to render fractals
	 */
	private Gradient gradient;
	
	/**
	 * The type 
	 */
	private String gradientType;
	
	public FractalController(){
		gradient = new Gradient();
		gradientType = "None";
		
		//get database for logging in
		InitDatabase.init();
		database = DatabaseProvider.getInstance();
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
	 * Get the model of this fractal
	 * @return
	 */
	public abstract Fractal getModel();
	
	/**
	 * Renders the current state of the given fractal to a BufferedImage and returns that image. 
	 * This method should be overwriten and then render() should be called to save the image
	 * @return the rendered image
	 */
	public abstract BufferedImage renderImage();
	
	/**
	 * Renders the fractal and saves it to the image folder
	 * @return
	 */
	public boolean render(){
		//create a thread to render and calculate the set
		//TODO make this use multiple threads
		Thread thread = new Thread(new Runnable(){
			@Override
			public void run(){
				BufferedImage img = renderImage();
				sendImage(img);
			}
		});
		
		thread.start();
		try{
			thread.join();
		}catch (InterruptedException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Sends the given image to be saved in the database
	 * @param img
	 * @return true if the image was sent successfully, false otherwise
	 */
	public boolean sendImage(BufferedImage img){
		try{
			ImageIO.write(img, "PNG", new File("./war/img/result.png"));
		}catch(IOException e){
			e.printStackTrace();
			return false;
		}
		return true;
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
