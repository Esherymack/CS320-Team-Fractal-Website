package edu.ycp.cs320.CS320_Team_Fractal_Website.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Originally from CS320 Lab06.

public abstract class DBUtil
{
	public static void closeQuietly(Statement stmt)
	{
		if(stmt != null)
		{
			try
			{
				stmt.close();
			}
			catch(SQLException e)
			{
				//ignore
			}
		}
	}
	
	public static void closeQuietly(ResultSet resultSet)
	{
		if(resultSet != null)
		{
			try
			{
				resultSet.close();
			}
			catch(SQLException e)
			{
				//ignore
			}
		}
	}
	
	public static void closeQuietly(Connection conn)
	{
		if(conn != null)
		{
			try
			{
				conn.close();
			}
			catch(SQLException e)
			{
				//ignore
			}
		}
	}
	
}
