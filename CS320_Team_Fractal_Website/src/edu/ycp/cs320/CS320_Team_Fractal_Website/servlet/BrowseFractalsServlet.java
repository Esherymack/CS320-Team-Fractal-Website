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
		req.getSession().setAttribute("fractals", fractals);

		ArrayList<Fractal> pageFractals = browseController.getFractalPageList(fractals, 10, 0);
		req.getSession().setAttribute("pageFractals", pageFractals);
		
		if(fractals != null) addFractalNames(req, browseController, fractals);
		
		String[] fractalTypes = Fractal.getAllFractalTypes();
		String[] gradientTypes = Gradient.TYPES;
		
		req.setAttribute("currentlyLoggedInMessage", currentlyLoggedInMessage);
		req.setAttribute("fractalTypes", fractalTypes);
		req.setAttribute("gradientTypes", gradientTypes);

		//get the number of fractals per page
		Integer fractalsPerPage = browseController.getFractalsPerPage(req);
		req.getSession().setAttribute("fractalsPerPage", fractalsPerPage.toString());
		req.setAttribute("pageNumber", 0);
		int max = fractals.size() / fractalsPerPage;
		req.getSession().setAttribute("maxPageNumber", max);
		
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/browseFractals.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Browse Fractals Servlet: doPost");
		//set up controller
		BrowseFractalsController browseController = new BrowseFractalsController();
		
		String currentlyLoggedInMessage = checkCookies(req, resp);
		
		//variables for attributes
		Fractal renderFractal = null;
		String errorMessage = null;
		Boolean display = false;
		//attempt to get the fractals currently loaded in the page
		ArrayList<Fractal> fractals = browseController.getFractalsFromSession(req);
		
		//get the number of fractals per page
		int fractalsPerPage = browseController.getFractalsPerPage(req);

		//get page number for fractals
		int pageNumber = browseController.getPageNumber(req);
		
		String[] fractalTypes = Fractal.getAllFractalTypes();
		String[] gradientTypes = Gradient.TYPES;
		//character sequence used to look for fractals with name with sequence
		String charSeq = req.getParameter("searchForFractals");
		
		//see if the next page should be accessed
		boolean pageChanged = false;
		int pageNumberChange = 0;
		//go to the start
		if(req.getParameter("pageStart") != null){
			pageNumber = 0;
			pageChanged = true;
		}
		//go to the page 2 pages back
		else if(req.getParameter("page-2") != null){
			pageNumberChange = -2;
			pageChanged = true;
		}
		//go to the previous page
		else if(req.getParameter("page-1") != null){
			pageNumberChange = -1;
			pageChanged = true;
		}
		//go to the next page
		else if(req.getParameter("page+1") != null){
			pageNumberChange = 1;
			pageChanged = true;
		}
		//go to the page 2 pages ahead
		else if(req.getParameter("page+2") != null){
			pageNumberChange = 2;
			pageChanged = true;
		}
		//go to the start
		else if(req.getParameter("pageEnd") != null){
			//this sets the page number far too high, but it will get fixed later on in the method
			if(fractals != null) pageNumber = fractals.size();
			else pageNumber = 0;
			pageChanged = true;
		}

		//if a page wasn't changed, continue looking for a request
		if(!pageChanged){
			//first see if the request was to view all fractals
			if(req.getParameter("viewAllFractals") != null) {
				fractals = browseController.getAllFractals();
				pageNumber = 0;
			}
			else{
				//if it wasn't, then see if the request was for a type of fractal
				boolean foundType = false;
				for(String s : Fractal.getAllFractalTypes()){
					 if (req.getParameter("viewAll" + s + "Fractals") != null) {
						fractals = browseController.getAllFractalsByType(s);
						pageNumber = 0;
						foundType = true;
					 }
				}
				if(!foundType){
					//again, if it wasn't for a type of fractal, now see if it was for a type of gradient
					foundType = false;
					for(String s : Gradient.TYPES){
						 if (req.getParameter("viewAllGradient" + s + "Fractals") != null) {
							fractals = browseController.getAllFractalsByGradientType(s);
							pageNumber = 0;
							foundType = true;
						 }
					}
					if(!foundType){
						//if it still wasn't found, see if it was for a search
						if (req.getParameter("searchForFractals") != null) {
							fractals = browseController.getAllFractalsWithCharSeq(charSeq);
							pageNumber = 0;
						}
						//otherwise the fractal should be rendered
						else{
							//set display to true so the fractal is rendered
							display = true;
						}
						//if the fractal list is still empty, get the full list of fractals
						if(fractals == null){
							fractals = browseController.getAllFractals();
							pageNumber = 0;
						}
					}
				}
			}
		}
		
		/*
		 * TODO:
		 * Add test cases
		 * Add admin delete powers
		 */
		
		int maxPageNumber;
		if(fractals != null){
			//figure out the max page number
			maxPageNumber = fractals.size() / fractalsPerPage;
			
			//determine the new page number based on the pagen umber change
			pageNumber += pageNumberChange;

			//ensure that the page number is valid
			pageNumber = Math.max(0, Math.min(maxPageNumber, pageNumber));
		}
		else{
			maxPageNumber = 0;
			pageNumber = 0;
		}
		
		if(fractals != null){
			//add the usernames of the fractal creators
			addFractalNames(req, browseController, fractals);
			
			//get fractal the user selected to render from the list of fractals
			//if one of the fractals is requested then it should be rendered
			for(Fractal f : fractals){
				Object found = req.getParameter("viewFractal_" + f.getId());
				if(found != null){
					renderFractal = f;
					break;
				}
			}
		}
		
		//if the fractal was found, render it and display it
		if(renderFractal != null){
			FractalController fractalController = renderFractal.createApproprateController();
			fractalController.render();
		}
		//if the fractal was not found and one should be displayed, then send an error message
		else if(display) errorMessage = "Fractal couldn't be rendered";
		
		//figure out the list of fractals that should be sent as the sub list of fractals to display
		ArrayList<Fractal> pageFractals = browseController.getFractalPageList(fractals, fractalsPerPage, pageNumber);
		
		//set attributes
		req.setAttribute("currentlyLoggedInMessage", currentlyLoggedInMessage);
		req.setAttribute("charSeq", charSeq);
		req.setAttribute("errorMessage", errorMessage);
		req.setAttribute("display", display);
		req.getSession().setAttribute("fractals", fractals);
		req.getSession().setAttribute("pageFractals", pageFractals);
		req.setAttribute("fractalTypes", fractalTypes);
		req.setAttribute("gradientTypes", gradientTypes);
		
		//set fractals per page attribute
		req.getSession().setAttribute("fractalsPerPage", fractalsPerPage);
		req.getSession().setAttribute("pageNumber", pageNumber);
		req.getSession().setAttribute("maxPageNumber", maxPageNumber);
		
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
