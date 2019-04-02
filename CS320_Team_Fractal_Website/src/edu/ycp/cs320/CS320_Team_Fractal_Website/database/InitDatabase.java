package edu.ycp.cs320.CS320_Team_Fractal_Website.database;

// Modified from CS320 Lab 06.

public class InitDatabase {
	
	public static void init()
	{
		DatabaseProvider.setInstance(new DerbyDatabase());
	}
}
