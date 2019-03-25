package edu.ycp.cs320.CS320_Team_Fractal_Website.database;

public class PersistentDatabase{
	
	private static FakeDataBase database;
	
	public static void setUpDatabase(){
		database = new FakeDataBase();
	}
	
	public static FakeDataBase getDatabase(){
		if(database == null) throw new IllegalStateException("Database has not been created yet");
		return database;
	}
	
}
