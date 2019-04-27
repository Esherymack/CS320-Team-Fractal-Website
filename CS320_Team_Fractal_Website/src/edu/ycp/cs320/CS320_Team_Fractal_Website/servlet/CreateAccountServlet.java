package edu.ycp.cs320.CS320_Team_Fractal_Website.servlet;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.pages.CheckUserValidController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.pages.HashValidatePasswordsController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.pages.SmtpAuthenticator;
import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.user.LogInController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.database.DatabaseProvider;
import edu.ycp.cs320.CS320_Team_Fractal_Website.database.IDatabase;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.StandardUser;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.User;

public class CreateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	private HashValidatePasswordsController pwd = new HashValidatePasswordsController();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		
		CheckUserValidController isValidUser = new CheckUserValidController();
		
		System.out.println("Create Account Servlet: doGet");
		
		// There should not be a user logged in if someone is seeing this page
		String userName = null;
		// Request any cookies
		Cookie[] cookies = req.getCookies();
		// otherwise:
		if(cookies != null)
		{
			for(Cookie cookie : cookies)
			{
				if(cookie.getName().equals("user"))
				{
					userName = cookie.getValue();
				}
			}
		}
				
		// If a cookie is found, **make sure it is a valid cookie**
		// That is, check and see if a username is found in the db that matches the cookie.
		// If the cookie is valid and the user exists, the user is logged in. Redirect them.
		if(isValidUser.getUserIfExists(userName))
		{
			resp.sendRedirect("mainPage");
		}

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
		
		// Hash the password
		String generatedSecurePasswordHash = pwd.generateStrongPasswordHash(password);

		//set up controller
		LogInController controller = new LogInController();
		User model = new StandardUser();
		controller.setModel(model);
		model.setFirstname(firstname);
		model.setLastname(lastname);
		model.setUsername(username);
		model.setPassword(generatedSecurePasswordHash);
		model.setEmail(email);
		
		//create the account
		invalidMessage = controller.createNewAccount();
		
		if(invalidMessage == null){
			//get the user from the database, this will return null if no user was found and give an error to the web page
			IDatabase db = DatabaseProvider.getInstance();
			User user = db.getUserByUsernameAndPassword(username, password);
			
			if(user != null){
				//notify user that their account has been created
				accountCreatedMessage = "The account has successfully been created.";
				// on successful account creation, set the cookie
				Cookie loginCookie = new Cookie("user", user.getUsername());
				// Set the cookie to expire in 24 hours
				loginCookie.setMaxAge(60*60*24);
				// Add the cookie
				resp.addCookie(loginCookie);
				
				/*
				 * Next, send the verification email before redirecting.
				 */
				String result;
				String to = user.getEmail();
				String from = "320fractalsite@gmail.com";
				String fromUserEmailPassword = "***REMOVED***";
				String host = "smtp.gmail.com";
				
				Properties properties = System.getProperties();
				
				properties.put("mail.smtp.auth", "true");
				properties.put("mail.smtp.starttls.enable", "true");
				properties.put("mail.smtp.host", "smtp.gmail.com");
				properties.put("mail.smtp.port", "587");
				properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
				// properties.setProperty("mail.smtp.host", host);
				
				SmtpAuthenticator authentication = new SmtpAuthenticator();
				Session mailSession = Session.getDefaultInstance(properties, authentication);
				
				try
				{
					MimeMessage message = new MimeMessage(mailSession);
					message.setFrom(new InternetAddress(from));
					message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
					message.setSubject("Please verify your account.");
					message.setText("Please verify your account using the following code: <placeholder>");
					
					// Transport.send(message);
					Transport transport = mailSession.getTransport("smtp");
					transport.connect(host, from, fromUserEmailPassword);
					transport.send(message);
					transport.close();
					
					result = "Message sent successfully.";
				}
				catch(MessagingException mex)
				{
					mex.printStackTrace();
					result = "Error: unable to send message.";
				}
				
				// Redirect to the main page
				resp.sendRedirect("verifyAccount");
			}
			else{
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
