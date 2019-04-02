package edu.ycp.cs320.CS320_Team_Fractal_Website.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.pages.UserController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.database.IDatabase;
import edu.ycp.cs320.CS320_Team_Fractal_Website.database.InitDatabase;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.User;
import edu.ycp.cs320.CS320_Team_Fractal_Website.database.DatabaseProvider;

public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("LogIn Servlet: doGet");
		
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/logIn.jsp").forward(req, resp);
		InitDatabase.init();

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
		if (username.isEmpty() || password.isEmpty()) {
			errorMessage = "Please specify a username and password";
			return;
		}
		
		User model = new User();
		UserController controller = new UserController();
		controller.setModel(model);
		
		IDatabase db = DatabaseProvider.getInstance();
		model.setUsername(username);
		model.setPassword(password);
		if(controller.User(db))
		{
			System.out.println("Login successful.");
		}
		else
		{
			System.out.println("Login failed.");
		}
		
		// Attempt to find the user with provided username and password
		
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
