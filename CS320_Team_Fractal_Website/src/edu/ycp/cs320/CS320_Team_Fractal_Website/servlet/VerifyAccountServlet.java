package edu.ycp.cs320.CS320_Team_Fractal_Website.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.pages.CheckUserValidController;

import javax.servlet.http.Cookie;

public class VerifyAccountServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		System.out.println("VerifyAccount Servlet: doGet");
		
		// A user needs to be logged in if they're seeing this page.
		String currentlyLoggedInMessage = checkCookies(req, resp);
		
		req.getRequestDispatcher("/_view/verifyAccount.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		System.out.println("VerifyAccount Servlet: doPost");
		
		req.getRequestDispatcher("/_view/verifyAccount.jsp").forward(req, resp);
	}
	
	protected String checkCookies(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		CheckUserValidController isValidUser = new CheckUserValidController();
		// User - should be logged in.
		String userName = null;
		// Request any cookies
		Cookie[] cookies = req.getCookies();
		// If there are no cookies, redirect the user to logIn.
		if(cookies == null)
		{
			resp.sendRedirect("logIn");
			return null;
		}
		// otherwise:
		for(Cookie cookie : cookies)
		{
			if(cookie.getName().equals("user")) userName = cookie.getValue();
		}
		// If a cookie is found, **make sure it is a valid cookie**
		// That is, check and see if a username is found in the db that matches the cookie.
		if(isValidUser.getUserIfExists(userName))
		{
			String currentlyLoggedInMessage = "Currently logged in as " + userName;
			req.setAttribute("currentlyLoggedInMessage", currentlyLoggedInMessage);
			req.setAttribute("userName", userName);
		}
		// Otherwise, just to clean up, delete the cookie of the deleted/nonexistent user ("log out").
		else
		{
			isValidUser.LogOut(req, resp, "logIn");
		}
		
		// otherwise
		return("Currently logged in as " + userName);
	}
}
