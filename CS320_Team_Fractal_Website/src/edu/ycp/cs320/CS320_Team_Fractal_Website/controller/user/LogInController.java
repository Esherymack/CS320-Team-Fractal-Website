package edu.ycp.cs320.CS320_Team_Fractal_Website.controller.user;

import edu.ycp.cs320.CS320_Team_Fractal_Website.database.DatabaseProvider;
import edu.ycp.cs320.CS320_Team_Fractal_Website.database.IDatabase;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.StandardUser;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.User;

public class LogInController{
	
	private User model;
	
	public LogInController(){
		model = null;
	}
	
	public void setModel(User model){
		this.model = model;
	}
	
	public User getModel(){
		return model;
	}
	
	/**
	 * Determines if the given info for an account is valid, then adds the account
	 * @return A string representing the error that was encountered with the data, null if no error was found
	 */
	public String createNewAccount(){
		String responce = null;
		
		// check for errors in the form data before using is in a calculation
		if(model.getUsername() == null || model.getPassword() == null  || model.getEmail() == null || model.getFirstname() == null || model.getLastname() == null ||
		   model.getUsername().isEmpty() || model.getPassword().isEmpty() || model.getEmail().isEmpty() || model.getFirstname().isEmpty() || model.getLastname().isEmpty()){
			responce = "Please specify a username, firstname, lastname, password, and email.";
		}
		//if username is less than 6 characters it is invalid
		else if (model.getUsername().length() < 6) {
			responce = "Username must contain at least 6 characters.";
		}
		//if password is less than 6 characters it is invalid
		else if (model.getUsername().length() < 6) {
			responce = "Password must contain at least 6 characters.";
		}
		//if password length is more than 25 characters it is invalid
		else if (model.getPassword().length() > 25) {
			responce = "That is not even logical. Enter a different password.";
		}
		//if username is the same as the password it is invalid
		else if (model.getUsername().equals(model.getPassword())) {
			responce = "Your username should not be the same as your password.";
		}
		//if username contains special characters it is invalid
		else if (model.getUsername().contains(",") || model.getUsername().contains(";") || model.getUsername().contains(":")||
				 model.getUsername().contains("@") || model.getUsername().contains("$") || model.getUsername().contains("&") ||
				 model.getUsername().contains("%") || model.getUsername().contains("#")) {
			responce = "Your username can not include special characters, it must include only letters and numbers.";
		}
		//if username is username it is invalid
		else if (model.getUsername().equals("username")) {
			responce = "That is not a good username. Enter a different one.";
		}
		//if password is password it is invalid
		else if (model.getPassword().equals("password")) {
			responce = "How creative. Enter a different password.";
		}
		//if email doesn't contain an @ or . it is invalid
		else if (!model.getEmail().contains("@") || !model.getEmail().contains(".")) {
			responce = "The email is invalid. Ensure that it includes an @ and a .";
		}

		IDatabase db = DatabaseProvider.getInstance();
		
		//add the user to the database
		boolean added = db.addUser(new StandardUser(model.getUsername(), model.getFirstname(), model.getLastname(), model.getEmail(), model.getPassword()));
		
		if(!added) responce = "Failed to add user";
		
		return responce;
	}
	
	/**
	 * Verify the credentials of the model by ensuring a password and username pair exist in the database
	 * @return true if the username and password pair were found, false otherwise
	 */
	public boolean verifyCredidentials(){
		if(model.getUsername() == null || model.getPassword() == null ||
		   model.getUsername().isEmpty() || model.getPassword().isEmpty())
			return false;
		
		IDatabase db = DatabaseProvider.getInstance();
		User user = db.getUserByUsernameAndPassword(model.getUsername(), model.getPassword());
		
		return user != null;
	}
	
	/*
	 * TODO
	 * Figure out whats going on with calls to Initdatabase.init()
	 */
	
}
