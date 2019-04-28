package edu.ycp.cs320.CS320_Team_Fractal_Website.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.pages.CheckUserValidController;

public class ForgotPasswordServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		CheckUserValidController isValidUser = new CheckUserValidController();
		System.out.println("ForgotPassword Servlet: doGet");
		
		// There should not be a user logged in if someone is seeing this page
		String userName = null;
		// Request any cookies
		Cookie[] cookies = req.getCookies();
		// otherwise:
		if(cookies != null)
		{
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
		// If the cookie is valid and the user exists, the user is logged in. Redirect them.
		if(isValidUser.getUserIfExists(userName))
		{
			resp.sendRedirect("mainPage");
		}
		
		req.getRequestDispatcher("/_view/forgotPassword.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		System.out.println("ForgotPassword Servlet: doPost");
		req.getRequestDispatcher("/_view/forgotPassword.jsp").forward(req, resp);
	}
}
