package edu.ycp.cs320.CS320_Team_Fractal_Website.controller.user;

import java.io.IOException;

import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.pages.HashValidatePasswordsController;
import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.pages.SendEmail;
import edu.ycp.cs320.CS320_Team_Fractal_Website.controller.pages.VerificationCodeGenerator;
import edu.ycp.cs320.CS320_Team_Fractal_Website.database.DatabaseProvider;
import edu.ycp.cs320.CS320_Team_Fractal_Website.database.IDatabase;
import edu.ycp.cs320.CS320_Team_Fractal_Website.database.InitDatabase;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.User;

public class ForgotPasswordController 
{
	private IDatabase database;
	
	private User model;
	
	public ForgotPasswordController()
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
	
	public boolean sendForgotPasswordEmail(final String email) throws IOException
	{
		HashValidatePasswordsController pwd = new HashValidatePasswordsController();
		SendEmail emailSender = new SendEmail();
	
		String tempPass = VerificationCodeGenerator.getAlphaNumericString();
		
		if(verifyUser(this.model.getUsername(), email))
		{		
			String generatedSecurePasswordHash = pwd.generateStrongPasswordHash(tempPass);
			if(database.changePassword(this.model, generatedSecurePasswordHash))
			{
				String sub = "Password reset alert.";
				String mess = "Your password has been reset. Please change it to something new at your earliest convenience. Your new temporary password is: " + tempPass;
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
	
	public boolean verifyUser(final String userName, final String email)
	{
		User user = database.getUserByUsernameAndEmail(userName, email);
		
		return user != null;
	}
	
}
