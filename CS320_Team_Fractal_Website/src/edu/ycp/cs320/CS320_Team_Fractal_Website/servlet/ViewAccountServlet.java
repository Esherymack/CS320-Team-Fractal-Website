package edu.ycp.cs320.CS320_Team_Fractal_Website.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.pages.ViewAccountController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.User;

public class ViewAccountServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		
		System.out.println("View Account Servlet: doGet");

		ViewAccountController controller = new ViewAccountController();
		
		String currentUser = getLoggedInUser(req, resp);
		User curUser = controller.getUserByUserName(currentUser);
		String firstName = curUser.getFirstname();
		String lastName = curUser.getLastname();
		String email = curUser.getEmail();
		
		req.setAttribute("userName", currentUser);
		req.setAttribute("firstName",  firstName);
		req.setAttribute("lastName",  lastName);
		req.setAttribute("email",  email);
		
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/viewAccount.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		
		System.out.println("View Account Servlet: doPost");
		
		String currentUser = getLoggedInUser(req, resp);
		req.setAttribute("userName", currentUser);
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
