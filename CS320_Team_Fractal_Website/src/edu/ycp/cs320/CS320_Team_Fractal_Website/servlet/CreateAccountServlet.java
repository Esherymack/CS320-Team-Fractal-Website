package edu.ycp.cs320.CS320_Team_Fractal_Website.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.CS320_Team_Fractal_Website.database.DatabaseProvider;
import edu.ycp.cs320.CS320_Team_Fractal_Website.database.IDatabase;
import edu.ycp.cs320.CS320_Team_Fractal_Website.database.InitDatabase;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.StandardUser;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.User;

public class CreateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		InitDatabase.init();
		
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
		// holds the account created message text, if there is any
		String accountCreatedMessage = null;
		
		// decode POSTed form parameters and dispatch to controller
		String firstname = req.getParameter("firstname");
		String lastname = req.getParameter("lastname");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");

		// check for errors in the form data before using is in a calculation
		if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
			invalidMessage = "Please specify a username, password, and email.";
		}
		//if username is less than 6 characters it is invalid
		else if (username.length() < 6) {
			invalidMessage = "Username must contain at least 6 characters.";
		}
		//if password is less than 6 characters it is invalid
		else if (password.length() < 6) {
			invalidMessage = "Password must contain at least 6 characters.";
		}
		//if password length is more than 25 characters it is invalid
		else if (password.length() > 25) {
			
			invalidMessage = "That is not even logical. Enter a different password.";
		}
		//if username is the same as the password it is invalid
		else if (username.equals(password)) {
			invalidMessage = "Your username should not be the same as your password.";
		}
		//if username contains special characters it is invalid
		else if (username.contains(",") || username.contains(";") || username.contains(":") || username.contains("@") || username.contains("$") || username.contains("&") || username.contains("%") || username.contains("#")) {
			invalidMessage = "Your username can not include special characters, it must include only letters and numbers.";
		}
		//if username is username it is invalid
		else if (username.equals("username")) {
			invalidMessage = "That is not a good username. Enter a different one.";
		}
		//if password is password it is invalid
		else if (password.equals("password")) {
			invalidMessage = "How creative. Enter a different password.";
		}
		//if email doesn't contain an @ or . it is invalid
		else if (! email.contains("@") || ! email.contains(".")) {
			invalidMessage = "The email is invalid. Ensure that it includes an @ and a .";
		}
		
		else 
		{
			IDatabase db = DatabaseProvider.getInstance();
			
			//add the user to the database
			db.addUser(new StandardUser(firstname, lastname, username, password, email));
			
			//get the user from the database, this will return null if no user was found and give an error to the web page
			User user = db.getUserByUsernameAndPassword(username, password);
			
			if(user != null)
			{
				//notify user that their account has been created
				accountCreatedMessage = "The account has successfully been created.";
				// on successful account creation, set the cookie
				Cookie loginCookie = new Cookie("user", user.getUsername());
				// Set the cookie to expire in 24 hours
				loginCookie.setMaxAge(60*60*24);
				// Add the cookie
				resp.addCookie(loginCookie);
				// Redirect to the main page
				resp.sendRedirect("mainPage");
			}
			else
			{
				accountCreatedMessage = "The account failed to generate.";
			}
		}
		
		// Add parameters as request attributes
		// this creates attributes named "username" and "password" and "email" for the response, and grabs the
		// values that were originally assigned to the request attributes, also named "username" and "password" and "email"
		// they don't have to be named the same, but in this case, since we are passing them back
		// and forth, it's a good idea
		req.setAttribute("firstname", firstname);
		req.setAttribute("lastname", lastname);
		req.setAttribute("username", username);
		req.setAttribute("password", password);
		req.setAttribute("email", email);
		
		// add result objects as attributes
		// this adds the errorMessage text and the result to the response
		req.setAttribute("invalidMessage", invalidMessage);
		// this adds the accountCreatedMessage text and the result to the response
		req.setAttribute("accountCreatedMessage", accountCreatedMessage);
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/createAccount.jsp").forward(req, resp);
	}
}
