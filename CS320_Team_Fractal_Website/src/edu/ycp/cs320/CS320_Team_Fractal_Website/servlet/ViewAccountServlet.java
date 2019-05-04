package edu.ycp.cs320.CS320_Team_Fractal_Website.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.FractalController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.pages.CheckUserValidController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.pages.ViewAccountController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.User;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Fractal;

public class ViewAccountServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public ViewAccountController controller = new ViewAccountController();
	public 	ArrayList<Fractal> fractals = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		
		System.out.println("View Account Servlet: doGet");	
		
		String currentlyLoggedInMessage = checkCookies(req, resp);
		
		String currentUser = getLoggedInUser(req, resp);
		User curUser = controller.getUserByUserName(currentUser);
		String firstName = curUser.getFirstname();
		String lastName = curUser.getLastname();
		String email = curUser.getEmail();
		fractals = controller.getUserFractals(currentUser);
		
		req.setAttribute("currentlyLoggedInMessage", currentlyLoggedInMessage);
		req.setAttribute("userName", currentUser);
		req.setAttribute("firstName",  firstName);
		req.setAttribute("lastName",  lastName);
		req.setAttribute("email",  email);
		req.setAttribute("fractals",  fractals);
		
		//
		// call JSP to generate form
		req.getRequestDispatcher("/_view/viewAccount.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		
		System.out.println("View Account Servlet: doPost");
		
		Fractal renderFractal = null;
		Fractal deleteFractal = null;
		String errorMessage = null;
		Boolean display = false;
		String currentUser = getLoggedInUser(req, resp);
		User curUser = controller.getUserByUserName(currentUser);
		String firstName = curUser.getFirstname();
		String lastName = curUser.getLastname();
		String email = curUser.getEmail();
		fractals = controller.getUserFractals(currentUser);
		
		//get fractal the user selected to render or delete from the list of fractals
		//if one of the fractals is requested then it should be rendered or deleted
		for(Fractal f : fractals)
		{
			Object found = req.getParameter("viewFractal_" + f.getId());
			Object found2 = req.getParameter("deleteFractal_" + f.getId());
			if(found != null)
			{
				renderFractal = f;
				display = true;
				break;
			}
			else if(found2 != null)
			{
				deleteFractal = f;
				display = false;
				break;
			}
		}
		
		//if the fractal needs to be deleted, remove it and update the list of fractals
		if(deleteFractal != null)
		{
			ViewAccountController controller = new ViewAccountController();
			User user = controller.getUserByUserName(getLoggedInUser(req, resp));
			controller.deleteFractal(deleteFractal.getId(), user);
			fractals = controller.getUserFractals(currentUser);
		}
		//if the fractal was found, render it and display it
		else if(renderFractal != null)
		{
			FractalController fractalController = renderFractal.createApproprateController();
			display = fractalController.render();
		}
		//if the fractal was supposed to be displayed but wasn't, provide error message
		else if(display)
		{
			errorMessage = "Fractal could not be rendered.";
		}
		
		req.setAttribute("errorMessage", errorMessage);
		req.setAttribute("display", display);
		req.setAttribute("fractals", fractals);
		req.setAttribute("firstName", firstName);
		req.setAttribute("lastName", lastName);
		req.setAttribute("email", email);
		req.setAttribute("userName", currentUser);
		
		req.getRequestDispatcher("/_view/viewAccount.jsp").forward(req,  resp);
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
			// otherwise
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
	
	
	/**
	 * Get the username of the user who is currently logged in
	 * @param req the request for the page
	 * @param resp the responce of the page
	 * @return the username of the user, null if no username was found
	 * @throws ServletException
	 * @throws IOException
	 */
	private String getLoggedInUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		//get cookies
		Cookie[] cookies = req.getCookies();
		//if no cookies were found then return null
		if(cookies == null) return null;
		//look for the username, if it is found, return it
		for(Cookie cookie : cookies){
			if(cookie.getName().equals("user")) return cookie.getValue();
		}
		//if no username is found, return null
		return null;
	}
}
