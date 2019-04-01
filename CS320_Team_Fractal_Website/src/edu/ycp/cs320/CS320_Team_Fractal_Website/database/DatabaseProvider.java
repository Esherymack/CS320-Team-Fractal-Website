package edu.ycp.cs320.CS320_Team_Fractal_Website.database;
// Originally from CS320 Lab 06

public class DatabaseProvider{
	private static IDatabase Instance;
	
	public static void setInstance(IDatabase db){
		Instance = db;
	}
	
	public static IDatabase getInstance(){
		if(Instance == null){
			throw new IllegalStateException("IDatabase instance has not been set!");
		}
		return Instance;
	}
}