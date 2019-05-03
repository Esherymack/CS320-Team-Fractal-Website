package edu.ycp.cs320.CS320_Team_Fractal_Website.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.pages.CheckUserValidController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.user.ChangePasswordController;

public class ChangePasswordServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
		
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		System.out.println("ChangePassword Servlet: doGet");
		
		// check if user is logged in
		checkCookies(req, resp);

		req.getRequestDispatcher("/_view/changePassword.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		System.out.println("ChangePassword Servlet: doPost");
		
		CheckUserValidController isValidUser = new CheckUserValidController();
		
		String errorMessage = null;
		String passwordResetMessage = null;
		
		String oldPass = req.getParameter("currentPassword");
		String newPass = req.getParameter("newPassword");
		
		if(oldPass.isEmpty() || newPass.isEmpty())
		{
			errorMessage = "Please specify both old and new passwords.";
		}
		
		if(oldPass.equals(newPass))
		{
			errorMessage = "Your new password cannot be the same as your old!";
		}
		
		else
		{
			ChangePasswordController controller = new ChangePasswordController();
			String userName = checkCookies(req, resp);
			controller.setModel(isValidUser.getUser(userName));
			
			if(controller.getModel() != null && controller.sendPasswordChangeEmail(oldPass, newPass))
			{
				passwordResetMessage = "Password reset email has been sent.";
			}
			else
			{
				errorMessage = "Password reset failed.";
			}
			
		}
		
		req.setAttribute("errorMessage", errorMessage);
		req.setAttribute("passwordResetMessage", passwordResetMessage);
		
		req.getRequestDispatcher("/_view/changePassword.jsp").forward(req, resp);
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
				System.out.println("Not verified.");
				resp.sendRedirect("verifyAccount");
			}
		}
		// Otherwise, just to clean up, delete the cookie of the deleted/nonexistent user ("log out").
		else
		{
			isValidUser.LogOut(req, resp, "logIn");
		}

		// otherwise
		return userName;
	}
}

