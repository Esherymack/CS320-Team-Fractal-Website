package edu.ycp.cs320.CS320_Team_Fractal_Website.controller.pages;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.CS320_Team_Fractal_Website.database.DatabaseProvider;
import edu.ycp.cs320.CS320_Team_Fractal_Website.database.IDatabase;
import edu.ycp.cs320.CS320_Team_Fractal_Website.database.InitDatabase;

public class CheckUserValidController 
{
	private IDatabase database;
	
	public CheckUserValidController()
	{
		InitDatabase.init();
		database = DatabaseProvider.getInstance();
	}
	
	public Boolean getUserIfExists(String username)
	{
		if(database.getUserByUsername(username) != null)
		{
			return true;
		}
		return false;
	}
	
	public void LogOut(HttpServletRequest req, HttpServletResponse resp, String redirPage) throws ServletException, IOException
	{
		resp.setContentType("text/html");
		Cookie loginCookie = null;
		Cookie[] cookies = req.getCookies();
		if(cookies != null)
		{
			for(Cookie cookie : cookies)
			{
				if(cookie.getName().equals("user"))
				{
					loginCookie = cookie;
					break;
				}
			}
		}
		if(loginCookie != null)
		{
			loginCookie.setMaxAge(0);
			resp.addCookie(loginCookie);
		}
		String requestURI = req.getRequestURI();
		String pageName = requestURI.substring(requestURI.lastIndexOf("/")+1);
		if(!redirPage.equalsIgnoreCase(pageName))
		{
			resp.sendRedirect(redirPage);
		}
	}
	
}
