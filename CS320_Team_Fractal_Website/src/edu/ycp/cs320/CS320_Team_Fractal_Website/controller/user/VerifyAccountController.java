package edu.ycp.cs320.CS320_Team_Fractal_Website.controller.user;

import edu.ycp.cs320.CS320_Team_Fractal_Website.database.DatabaseProvider;
import edu.ycp.cs320.CS320_Team_Fractal_Website.database.IDatabase;
import edu.ycp.cs320.CS320_Team_Fractal_Website.database.InitDatabase;
import edu.ycp.cs320.CS320_Team_Fractal_Website.model.account.User;

public class VerifyAccountController 
{
	
	private IDatabase database;
	
	private User model;
	
	public VerifyAccountController()
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
	
	public boolean verifyAccountVerification(final String submittedCode)
	{
		String veriCode = database.getVerificationCodeByUsername(model.getUsername());
		System.out.println("Code: " + veriCode);
		if(veriCode.equals(submittedCode))
		{
			User user = database.getUserByUsername(model.getUsername());
			System.out.println(model.getIsVerified());
			boolean change = database.changeStateOfVerification("true", user);
			model.setIsVerified("true");
			return true;
		}
		return false;
	}
}
