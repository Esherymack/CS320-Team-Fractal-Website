package edu.ycp.cs320.CS320_Team_Fractal_Website.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.pages.CheckUserValidController;

public class ChangePasswordServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
		
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		CheckUserValidController isValidUser = new CheckUserValidController();
		System.out.println("ForgotPassword Servlet: doGet");
		
		// check if user is logged in
		String currentlyLoggedInMessage = checkCookies(req, resp);

		//message for logging in
		req.setAttribute("currentlyLoggedInMessage", currentlyLoggedInMessage);
		
		req.getRequestDispatcher("/_view/forgotPassword.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		System.out.println("ForgotPassword Servlet: doPost");
		req.getRequestDispatcher("/_view/forgotPassword.jsp").forward(req, resp);
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
			if(isValidUser.getUserIsVerified(userName))
			{
				String currentlyLoggedInMessage = "Currently logged in as " + userName;
				req.setAttribute("currentlyLoggedInMessage", currentlyLoggedInMessage);
				req.setAttribute("userName", userName);
			}
			else
			{
				System.out.println("Not verified.");
				resp.sendRedirect("verifyAccount");
			}
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

