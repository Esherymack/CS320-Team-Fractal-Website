package edu.ycp.cs320.CS320_Team_Fractal_Website.controller.user;

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
	
	public boolean createAccount(){
		return true;
	}
	
	public boolean logIn(){
		return true;
	}
	
}
