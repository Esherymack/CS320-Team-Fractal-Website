package edu.ycp.cs320.CS320_Team_Fractal_Website.controller.user;

import java.io.IOException;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.pages.SendEmail;
import edu.ycp.cs320.CS320_Team_Fractal_Website.database.DatabaseProvider;
import edu.ycp.cs320.CS320_Team_Fractal_Website.database.IDatabase;
import edu.ycp.cs320.CS320_Team_Fractal_Website.database.InitDatabase;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.User;

public class ChangePasswordController 
{
	private IDatabase database;
	
	private User model;
	
	public ChangePasswordController()
	{
		model = null;
		
		InitDatabase.init();
		
		database = DatabaseProvider.getInstance();
	}
	
	public void setModel(User model)
	{
		this.model = model;
	}
	
	public User getModel()
	{
		return model;
	}
	
	public boolean sendPasswordChangeEmail(final String oldPass, final String newPass) throws IOException
	{
		SendEmail emailSender = new SendEmail();
		
		if(verifyUser(oldPass))
		{
			if(database.changePassword(this.model, newPass))
			{
				String sub = "Password reset alert.";
				String mess = "Your password has been reset. If you did not do this, please change your password immediately.";
				
				emailSender.sendEmail(this.model, sub, mess);
				return true;
			}
			return false;
		}
		else
		{
			return false;
		}
	}
	
	public boolean verifyUser(final String currPass)
	{
		User user = database.getUserByUsernameAndPassword(this.model.getUsername(), currPass);
		return user != null;
	}
}
