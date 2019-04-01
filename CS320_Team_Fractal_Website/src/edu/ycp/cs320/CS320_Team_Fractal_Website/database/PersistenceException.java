package edu.ycp.cs320.CS320_Team_Fractal_Website.database;

// originally from CS320 lab06

public class PersistenceException extends RuntimeException
{
	private static final long serialVersionUID = 1L;
	
	public PersistenceException(String msg)
	{
		super(msg);
	}
	
	public PersistenceException(String msg, Throwable cause)
	{
		super(msg, cause);
	}
}
