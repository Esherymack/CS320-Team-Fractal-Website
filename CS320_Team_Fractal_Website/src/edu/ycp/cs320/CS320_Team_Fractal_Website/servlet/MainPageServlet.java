package edu.ycp.cs320.CS320_Team_Fractal_Website.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.FractalController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.MandelbrotController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.SierpinskiController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Mandelbrot;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.fractal.Sierpinski;


public class MainPageServlet extends HttpServlet{
	
	/**
	 * The number of parameters used by the website
	 */
	public static final int NUM_PARAMS = 10;

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException
	{
		System.out.println("Main Page Servlet: doGet");
		
		// otherwise
		String currentlyLoggedInMessage = checkCookies(req, resp);
		
		req.setAttribute("currentlyLoggedInMessage", currentlyLoggedInMessage);
		req.getRequestDispatcher("/_view/mainPage.jsp").forward(req,  resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{	
		System.out.println("mainPage Servlet: doPost");

		String currentlyLoggedInMessage = checkCookies(req, resp);
		
		// holds the error message text, if any
		String errorMessage = null;
		// holds the fractla info
		String fractalInfo = null;
		
		boolean result = false;
		
		int choice = -1;
		try {
			choice = Integer.parseInt(req.getParameter("choice"));
		}catch(NumberFormatException e){
			errorMessage = "Please select a fractal";
		}
		
		//if no errors have occurred, continue processing the request
		if(errorMessage == null){
			
			//get all parameters
			String[] params = new String[NUM_PARAMS];
			for(int i = 0; i < NUM_PARAMS; i++){
				params[i] = req.getParameter("param" + i);
			}
			
			//select the correct controller and model to use
			FractalController controller = null;
			
			//Sierpinski
			if(choice == 0){
				Sierpinski sierpinskiModel = new Sierpinski();
				controller = new SierpinskiController(sierpinskiModel);
			}
			
			//Mandelbrot
			else if(choice == 1){
				Mandelbrot mandelModel = new Mandelbrot();
				controller = new MandelbrotController(mandelModel);
			}
			
			//only generate the fractal if a value choice was found
			if(choice != -1){
				//send parameters
				boolean sent = controller.acceptParameters(params);
				
				//render the fractal, only if no error occurred
				//if the request is for a submit, then just display the fractal to the site
				if(req.getParameter("submit") != null){
					if(controller != null && sent) result = controller.render();
				}
				//if the request is for a save, only save the fractal
				if(req.getParameter("save") != null){
					//get the name the user has typed in
					String name = req.getParameter("saveButton");
					
					if(name != null && controller != null && sent) controller.saveImage(name, getLoggedInUser(req, resp));
					else errorMessage = "Please provide a name and parameters";
				}
				
				//detect if invalid parameters were given
				else if(!sent) errorMessage = "Invalid parameters given";
				
				//set fractal info
				fractalInfo = controller.getModel().getInfo();
			}
			
			//send parameters back
			for(int i = 0; i < NUM_PARAMS; i++){
				req.setAttribute(params + "i", params[i]);
			}
		}
		
		//set attributes of page
		req.setAttribute("currentlyLoggedInMessage", currentlyLoggedInMessage);
		req.setAttribute("errorMessage", errorMessage);
		req.setAttribute("fractalInfo", fractalInfo);
		req.setAttribute("result", result);
		req.setAttribute("choice", choice);
		
		req.getRequestDispatcher("/_view/mainPage.jsp").forward(req, resp);
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
