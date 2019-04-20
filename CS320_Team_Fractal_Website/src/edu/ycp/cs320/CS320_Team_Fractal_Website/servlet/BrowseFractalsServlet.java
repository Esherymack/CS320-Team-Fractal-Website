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
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Fractal;

public class BrowseFractalsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		
		System.out.println("Browse Fractals Servlet: doGet");
		
		String currentlyLoggedInMessage = checkCookies(req, resp);
		
		req.setAttribute("currentlyLoggedInMessage", currentlyLoggedInMessage);
		
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
		String name = req.getParameter("name");
		
		//set up controller
		BrowseFractalsController browseController = new BrowseFractalsController();
		
		//check to see if the request was not to view all fractals
		//if the request was to view all fractals, no initial fractal should be displayed
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
		else if (req.getParameter("searchForFractals") != null) {
			fractals = browseController.getAllFractalsByName(name);
		}
		else {
			fractals = browseController.getAllFractals();
			display = true;
		}
		
		//TODO move this stuff to the derby database?
		//get fractal the user selected
		for(Fractal f : fractals){
			Object found = req.getParameter("viewFractal_" + f.getId());
			if(found != null){
				renderFractal = f;
				break;
			}
		}
		
		//if the fractal was found, render it and display it
		if(renderFractal != null){
			FractalController fractalController = renderFractal.createApproprateController();
			fractalController.render();
		}
		
		//if the fractal was not found and one should be displayed, then send an error message
		else if(display) errorMessage = "Fractal couldn't be rendered";
		
		//set attributes
		req.setAttribute("currentlyLoggedInMessage", currentlyLoggedInMessage);
		req.setAttribute("name", name);
		req.setAttribute("errorMessage", errorMessage);
		req.setAttribute("display", display);
		req.setAttribute("fractals", fractals);
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/browseFractals.jsp").forward(req, resp);
	}
	
	protected String checkCookies(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
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
		// again, check if the user is logged in:
		if(userName == null)
		{
			resp.sendRedirect("logIn");
			return null;
		}
		
		// otherwise
		return("Currently logged in as " + userName);
	}
		
}
