package edu.ycp.cs320.CS320_Team_Fractal_Website.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.FractalController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.pages.BrowseFractalsController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.pages.CheckUserValidController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Fractal;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Gradient;

public class BrowseFractalsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		
		System.out.println("Browse Fractals Servlet: doGet");
		String currentlyLoggedInMessage = checkCookies(req, resp);
		ArrayList<Fractal> fractals = null;
		BrowseFractalsController browseController = new BrowseFractalsController();
		fractals = browseController.getAllFractals();
		req.setAttribute("fractals", fractals);

		if(fractals != null) addFractalNames(req, browseController, fractals);
		
		String[] fractalTypes = Fractal.getAllFractalTypes();
		String[] gradientTypes = Gradient.TYPES;
		
		req.setAttribute("currentlyLoggedInMessage", currentlyLoggedInMessage);
		req.setAttribute("fractalTypes", fractalTypes);
		req.setAttribute("gradientTypes", gradientTypes);
		
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/browseFractals.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Browse Fractals Servlet: doPost");
		
		String currentlyLoggedInMessage = checkCookies(req, resp);
		
		//variables for attributes
		Fractal renderFractal = null;
		String errorMessage = null;
		Boolean display = false;
		ArrayList<Fractal> fractals = null;
		String[] fractalTypes = Fractal.getAllFractalTypes();
		String[] gradientTypes = Gradient.TYPES;
		//character sequence used to look for fractals with name with sequence
		String charSeq = req.getParameter("searchForFractals");
		
		//set up controller
		BrowseFractalsController browseController = new BrowseFractalsController();
		
		//if the request was to view all fractals get all fractals and add them to array list
		//if the request was to view one of the types of fractal add the fractals of that type to array list
		//if the request was to view one of the gradient types for the fractals, add the fractals with the gradient type to the array list
		//if there was no request then all of the fractals are added to the array list
		if (req.getParameter("viewAllFractals") != null) {
			fractals = browseController.getAllFractals();
		}
		else if (req.getParameter("viewAllMandelbrotFractals") != null) {
			fractals = browseController.getAllFractalsByType("Mandelbrot");
		}
		else if (req.getParameter("viewAllSierpinskiFractals") != null) {
			fractals = browseController.getAllFractalsByType("Sierpinski");
		}
		else if (req.getParameter("viewAllKochFractals") != null) {
			fractals = browseController.getAllFractalsByType("Koch");
		}
		else if (req.getParameter("viewAllBarnsleyFractals") != null) {
			fractals = browseController.getAllFractalsByType("Barnsley");
		}
		else if (req.getParameter("viewAllJuliaFractals") != null) {
			fractals = browseController.getAllFractalsByType("Julia");
		}
		else if (req.getParameter("viewAllNoneFractals") != null) {
			fractals = browseController.getAllFractalsByGradientType("None");
		}
		else if (req.getParameter("viewAllRainbowFractals") != null) {
			fractals = browseController.getAllFractalsByGradientType("Rainbow");
		}
		else if (req.getParameter("viewAllHorizontalFractals") != null) {
			fractals = browseController.getAllFractalsByGradientType("Horizontal");
		}
		else if (req.getParameter("viewAllVerticalFractals") != null) {
			fractals = browseController.getAllFractalsByGradientType("Vertical");
		}
		else if (req.getParameter("viewAllDiagonalFractals") != null) {
			fractals = browseController.getAllFractalsByGradientType("Diagonal");
		}
		else if (req.getParameter("searchForFractals") != null) {
			fractals = browseController.getAllFractalsWithCharSeq(charSeq);
		}
		else {
			fractals = browseController.getAllFractals();
			display = true;
		}

		if(fractals != null) addFractalNames(req, browseController, fractals);
		
		//get fractal the user selected to render from the list of fractals
		//if one of the fractals is requested then it should be rendered
		for(Fractal f : fractals){
			Object found = req.getParameter("viewFractal_" + f.getId());
			if(found != null){
				renderFractal = f;
				break;
			}
		}
		
		//if the fractal was found, render it and display it
		if(renderFractal != null)
		{
			FractalController fractalController = renderFractal.createApproprateController();
			fractalController.render();
		}
		
		//if the fractal was not found and one should be displayed, then send an error message
		else if(display) errorMessage = "Fractal couldn't be rendered";
		
		//set attributes
		req.setAttribute("currentlyLoggedInMessage", currentlyLoggedInMessage);
		req.setAttribute("charSeq", charSeq);
		req.setAttribute("errorMessage", errorMessage);
		req.setAttribute("display", display);
		req.setAttribute("fractals", fractals);
		req.setAttribute("fractalTypes", fractalTypes);
		req.setAttribute("gradientTypes", gradientTypes);
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/browseFractals.jsp").forward(req, resp);
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
	
	/**
	 * Add an attribute for each of the fractals in the given list that is:
	 * fractalUsername + the id of the fractal, so
	 * "fractalUsername" + id
	 * @param controller
	 * @param fractals
	 */
	private static void addFractalNames(HttpServletRequest req, BrowseFractalsController controller, ArrayList<Fractal> fractals){
		for(Fractal f : fractals){
			String name = controller.getUsernameByFractalId(f.getId());
			req.setAttribute("fractalUsername" + f.getId(), name);
		}
		
	}
	
}
