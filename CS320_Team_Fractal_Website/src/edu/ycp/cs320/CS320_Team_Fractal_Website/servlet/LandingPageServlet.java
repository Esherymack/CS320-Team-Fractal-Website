package edu.ycp.cs320.CS320_Team_Fractal_Website.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LandingPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Landing Page Servlet: doGet");
		
		// User - should be logged in.
		String userName = null;
		// Request any cookies
		Cookie[] cookies = req.getCookies();
		// otherwise:
		if(cookies != null){
			for(Cookie cookie : cookies)
			{
				if(cookie.getName().equals("user")) userName = cookie.getValue();
			}
		}
		
		// otherwise
		String currentlyLoggedInMessage = "Currently logged in as " + userName;
			
		req.setAttribute("userName", userName);
		req.setAttribute("currentlyLoggedInMessage", currentlyLoggedInMessage);
		
		req.getRequestDispatcher("/_view/landingPage.jsp").forward(req, resp);	

	}
	
}
