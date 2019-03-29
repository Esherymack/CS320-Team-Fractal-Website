package edu.ycp.cs320.CS320_Team_Fractal_Website.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.pages.LogInController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.pages.LogIn;

public class CreateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Create Account Servlet: doGet");
		
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/createAccount.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Create Account Servlet: doPost");
		

		// holds the error message text, if there is any
		String invalidMessage = null;
		String accountCreatedMessage = null;
		
		// decode POSTed form parameters and dispatch to controller
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");

		// check for errors in the form data before using is in a calculation
		if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
			invalidMessage = "Please specify a username, password, and email.";
		}
		else if (username.length() < 6) {
			invalidMessage = "Username must contain at least 6 characters";
		}
		else if (password.length() < 6) {
			invalidMessage = "Password must contain at least 6 characters";
		}
		else if (password.length() > 25) {
			invalidMessage = "That is not even logical. Enter a different password";
		}
		else if (username.equals(password)) {
			invalidMessage = "Your username should not be the same as your password.";
		}
		else if (username.contains(",") || username.contains(";") || username.contains(":") || username.contains("@") || username.contains("$") || username.contains("&") || username.contains("%") || username.contains("#")) {
			invalidMessage = "Your username can not include special characters, it must include only letters and numbers.";
		}
		else if (username.equals("username")) {
			invalidMessage = "That is not a good username. Enter a different one.";
		}
		else if (password.equals("password")) {
			invalidMessage = "How creative. Enter a different password.";
		}
		else if (! email.contains("@") || ! email.contains(".")) {
			invalidMessage = "The email is invalid. Ensure that it includes an @ and a .";
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
			model.setEmail(email);
			
			if(controller.createAccount()){
				accountCreatedMessage = "The account has successfully been created.";
			}
			else{
				accountCreatedMessage = "Selected username already taken";
			}
		}
		
		// Add parameters as request attributes
		// this creates attributes named "first" and "second for the response, and grabs the
		// values that were originally assigned to the request attributes, also named "first" and "second"
		// they don't have to be named the same, but in this case, since we are passing them back
		// and forth, it's a good idea
		req.setAttribute("username", username);
		req.setAttribute("password", password);
		req.setAttribute("email", email);
		
		// add result objects as attributes
		// this adds the errorMessage text and the result to the response
		req.setAttribute("invalidMessage", invalidMessage);
		req.setAttribute("accountCreatedMessage", accountCreatedMessage);
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/createAccount.jsp").forward(req, resp);
	}
}
