package edu.ycp.cs320.CS320_Team_Fractal_Website.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.pages.LogInController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.pages.LogIn;

public class LogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("LogIn Servlet: doGet");
		
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/logIn.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("LogIn Servlet: doPost");
		

		// holds the error message text, if there is any
		String errorMessage = null;
		String logInMessage = null;
		
		// decode POSTed form parameters and dispatch to controller
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		// check for errors in the form data before using is in a calculation
		if (username.isEmpty() || password.isEmpty()) {
			errorMessage = "Please specify a username and password";
		}
		// otherwise, data is good, do the calculation
		// must create the controller each time, since it doesn't persist between POSTs
		// the view does not alter data, only controller methods should be used for that
		// thus, always call a controller method to operate on the data
		else {
			
			LogIn model = new LogIn();
			LogInController controller = new LogInController();
			controller.setModel(model);
			model.setUsername(username);
			model.setPassword(password);
			
			//attempt to log in
			//the attempt was successful
			if(controller.logIn()){
				logInMessage = "You have successfully logged in.";
			}
			//the attempt fails
			else{
				logInMessage = "Could not find username and password combination";
			}
		}
		
		// Add parameters as request attributes
		req.setAttribute("username", username);
		req.setAttribute("password", password);
		
		// add result objects as attributes
		// this adds the errorMessage text and the result to the response
		req.setAttribute("errorMessage", errorMessage);
		req.setAttribute("logInMessage", logInMessage);
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/logIn.jsp").forward(req, resp);
	}
}
