package edu.ycp.cs320.CS320_Team_Fractal_Website.database;

// Modified from CS320 Lab06

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.StandardUser;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.User;

public class InitialData 
{
	public static List<User> getUsers() throws IOException
	{
		List<User> userList = new ArrayList<User>();
		ReadCSV readUsers = new ReadCSV("users.csv");
		try
		{
			Integer userId = 1;
			while(true)
			{
				List<String> tuple = readUsers.next();
				if(tuple == null)
				{
					break;
				}
				Iterator<String> i = tuple.iterator();
				User user = new StandardUser();
				user.setUsername(i.next());
				user.setFirstname(i.next());
				user.setLastname(i.next());
				user.setEmail(i.next());
				user.setPassword(i.next());
				userList.add(user);
			}
			return userList;
		}
		finally
		{
			readUsers.close();
		}
	}
}
