package edu.ycp.cs320.CS320_Team_Fractal_Website.controller.pages;

import java.util.ArrayList;

import edu.ycp.cs320.CS320_Team_Fractal_Website.database.DatabaseProvider;
import edu.ycp.cs320.CS320_Team_Fractal_Website.database.IDatabase;
import edu.ycp.cs320.CS320_Team_Fractal_Website.database.InitDatabase;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Fractal;

public class BrowseFractalsController{
	
	private IDatabase database;
	
	public BrowseFractalsController(){
		InitDatabase.init();
		database = DatabaseProvider.getInstance();
	}
	
	/**
	 * Get every fractal from the database
	 * @return and ArrayList of all the fractals
	 */
	public ArrayList<Fractal> getAllFractals(){
		return database.getAllFractals();
	}
	
	/**
	 * Get every fractal by type from the database
	 * @return and ArrayList of all the fractals of type
	 */
	public ArrayList<Fractal> getAllFractalsByType(String type){
		return database.getAllFractalsByType(type);
	}
	
}
