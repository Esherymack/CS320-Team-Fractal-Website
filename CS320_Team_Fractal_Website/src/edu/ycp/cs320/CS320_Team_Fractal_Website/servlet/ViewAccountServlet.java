package edu.ycp.cs320.CS320_Team_Fractal_Website.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.fractal.FractalController;
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

		
		
		String currentUser = getLoggedInUser(req, resp);
		User curUser = controller.getUserByUserName(currentUser);
		String firstName = curUser.getFirstname();
		String lastName = curUser.getLastname();
		String email = curUser.getEmail();
		fractals = controller.getUserFractals(currentUser);
		
		req.setAttribute("userName", currentUser);
		req.setAttribute("firstName",  firstName);
		req.setAttribute("lastName",  lastName);
		req.setAttribute("email",  email);
		req.setAttribute("fractals",  fractals);
		
		// call JSP to generate form
		req.getRequestDispatcher("/_view/viewAccount.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		
		System.out.println("View Account Servlet: doPost");
		
		Fractal renderFractal = null;
		String errorMessage = null;
		Boolean display = null;
		String currentUser = getLoggedInUser(req, resp);
		User curUser = controller.getUserByUserName(currentUser);
		String firstName = curUser.getFirstname();
		String lastName = curUser.getLastname();
		String email = curUser.getEmail();
		fractals = controller.getUserFractals(currentUser);
		
		for(Fractal f : fractals)
		{
			Object found = req.getParameter("viewFractal_" + f.getId());
			if(found != null)
			{
				renderFractal = f;
				break;
			}
		}
		
		display = req.getParameter("viewFractals") == null;
		if(renderFractal != null)
		{
			FractalController fractalController = renderFractal.createApproprateController();
			fractalController.render();
		}
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
		return(userName);
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
