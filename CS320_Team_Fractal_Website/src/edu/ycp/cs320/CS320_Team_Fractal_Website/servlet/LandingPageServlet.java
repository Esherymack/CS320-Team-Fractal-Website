package edu.ycp.cs320.CS320_Team_Fractal_Website.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.pages.CheckUserValidController;

public class LandingPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		CheckUserValidController isValidUser = new CheckUserValidController();
		
		System.out.println("Landing Page Servlet: doGet");
		
		// User - should be logged in.
		String userName = null;
		// Request any cookies
		Cookie[] cookies = req.getCookies();
		// otherwise:
		if(cookies != null){
			for(Cookie cookie : cookies)
			{
				if(cookie.getName().equals("user"))
				{
					userName = cookie.getValue();
				}
			}
		}
		
		// If a cookie is found, **make sure it is a valid cookie**
		// That is, check and see if a username is found in the db that matches the cookie.
		if(isValidUser.getUserIfExists(userName))
		{
			// otherwise
			String currentlyLoggedInMessage = "Currently logged in as " + userName;
			req.setAttribute("currentlyLoggedInMessage", currentlyLoggedInMessage);
			req.setAttribute("userName", userName);
		}
		// Otherwise, just to clean up, delete the cookie of the deleted/nonexistent user ("log out").
		else
		{
			isValidUser.LogOut(req, resp, "landingPage");
		}
		req.getRequestDispatcher("/_view/landingPage.jsp").forward(req, resp);	
	}
	
}
