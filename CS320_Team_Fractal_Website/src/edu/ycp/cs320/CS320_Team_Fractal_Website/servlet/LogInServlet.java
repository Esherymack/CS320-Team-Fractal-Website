package edu.ycp.cs320.CS320_Team_Fractal_Website.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.StandardUser;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.User;
import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.pages.CheckUserValidController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.user.LogInController;

public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		CheckUserValidController isValidUser = new CheckUserValidController();
		
		System.out.println("LogIn Servlet: doGet");
		
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
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/logIn.jsp").forward(req, resp);
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("LogIn Servlet: doPost");
		

		// holds the error message text, if there is any
		String errorMessage = null;
		// holds the log in message text, if there is any
		String logInMessage = null;
		
		// decode POSTed form parameters and dispatch to controller
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		// check for errors in the form data before using is in a calculation
		if (username.isEmpty() || password.isEmpty()){
			errorMessage = "Please specify a username and password";
		}
		// if there was no error, continue processing the request
		else{
			//set up model and controller to process request
			LogInController controller = new LogInController();
			User model = new StandardUser();
			controller.setModel(model);
			model.setUsername(username);
			model.setPassword(password);
			
			//determine if the creditidentials are valid
			boolean logedIn = controller.verifyCredidentials();
			
			if(logedIn){
				System.out.println("Login successful.");
				logInMessage = "You have successfully logged in.";
				// on successful login, set the cookie
				Cookie loginCookie = new Cookie("user", model.getUsername());
				// Set the cookie to expire in 24 hours
				loginCookie.setMaxAge(60*60*24);
				// Add the cookie
				resp.addCookie(loginCookie);
				// Redirect to the verification page.
				resp.sendRedirect("verifyAccount");
			}
			else{
				System.out.println("Login failed.");
				logInMessage = "Account was not found.";
			} 
		}
		
		// Add parameters as request attributes
		// this creates attributes named "username" and "password" for the response, and grabs the
		// values that were originally assigned to the request attributes, also named "username" and "password"
		// they don't have to be named the same, but in this case, since we are passing them back
		// and forth, it's a good idea
		req.setAttribute("username", username);
		req.setAttribute("password", password);
		
		// add result objects as attributes
		// this adds the errorMessage text and the result to the response
		req.setAttribute("errorMessage", errorMessage);
		// this adds the logInMessage text and the result to the response
		req.setAttribute("logInMessage", logInMessage);
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/logIn.jsp").forward(req, resp);
	}
}
