package edu.ycp.cs320.CS320_Team_Fractal_Website.database;

public class DatabaseProvider{
	
	private static IDatabase database;
	
	public static void setDatabase(IDatabase db){
		database = db;
	}
	
	public static IDatabase getDatabase(){
		if(database == null) throw new IllegalStateException("Database has not been created yet");
		return database;
	}
}
